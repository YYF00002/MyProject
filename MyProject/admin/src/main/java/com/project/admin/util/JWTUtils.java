package com.project.admin.util;

import com.project.admin.entity.UserBaseInformation;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* @Description:  JWT Token工具
* @Author:         YYF
* @CreateDate:     2019/7/15 14:33
* @Version:        1.0
*/
@Slf4j
@Component
public class JWTUtils {

    //JWT加密秘钥
    private final String SECRET = "IntcImlkXCI6MTExMTExMSxcIm5vX";


    //Token头信息
    private final String TOKEN_HEADER = "Tima";

    //指定TOKEN过期时间，默认10天
    private final Long expiration = 864000000L;
//	@Value("${jwt.config.secret}")
//	private  String SECRET;
//	@Value("${jwt.config.header}")
//	private  String TOKEN_HEADER;
//	@Value("${jwt.config.expiration}")
//	private  Long expiration ;

    private static final String CLAIM_KEY_USERNAME = "userName";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String CLAIM_KEY_USERID = "userId";
//    private static final String CLAIM_KEY_USERNO = "userNo";
//    private static final String CLAIM_KEY_USERTYPE = "userType";
    private static final String CLAIM_KEY_USERINFO = "userInfo";


    /**
     * 根据用户信息生成令牌信息
     *
     * @param token 用户信息
     * @return
     */
    public String generateToken(UserBaseInformation token) {

        Map<String, Object> claims = new HashMap<String, Object>();

        claims.put(CLAIM_KEY_CREATED, new Date());
        String userName = token.getUserName();
        	claims.put(CLAIM_KEY_USERNAME, userName);

        	claims.put(CLAIM_KEY_USERID, token.getId());

       String userInfo=JsonUtil.pojoToJson(token);
       claims.put(CLAIM_KEY_USERINFO, userInfo);
        return TOKEN_HEADER + " " + Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate()) //设置Token永不过期，过期判断全部通过代码控制
                .signWith(SignatureAlgorithm.HS256, SECRET)//指定算法方式，使用RSA算法需要注意特殊字符造成的异常
                .compact();
    }


    /**
     * 生成令牌信息
     *
     * @param claims 令牌信息
     * @return
     */
    private String generateToken(Claims claims) {

        return TOKEN_HEADER + " " + Jwts.builder()
                .setClaims(claims)
                 .setExpiration(generateExpirationDate()) //设置Token永不过期，过期判断通过代码控制
                .signWith(SignatureAlgorithm.HS512, SECRET)//指定算法方式，使用RSA算法需要注意特殊字符造成的异常
                .compact();
    }

    /**
     * 刷新TOKEN
     *
     * @param token TOKEN信息
     * @return
     */
    public String refreshToken(String token) {
        String refreshedToken = null;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
           log.error(e.getMessage()); 
        }
        return refreshedToken;
    }

    /**
     * 解析令牌信息
     *
     * @param token TOKEN信息
     * @return
     */
    public Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            if (token.startsWith(TOKEN_HEADER)) {
                token = token.substring(TOKEN_HEADER.length());
            }
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
        	 log.error(e.getMessage());  
        }
        return claims;
    }


    /**
     * 生成过期时间信息
     *
     * @return
     */
    public Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration);
    }

    /**
     * 获取用户名
     *
     * @param token TOKEN信息
     * @return
     */
    public String getUsernameFromToken(String token) {
        String username = null;
        try {
            Claims claims = getClaimsFromToken(token);
            username = (String) claims.get(CLAIM_KEY_USERNAME);
        } catch (Exception e) {
        	 log.error(e.getMessage());  
        }
        return username;
    }


    /**
     * 获取用户ID
     *
     * @param token TOKEN信息
     * @return
     */
    public Long getUserIdFromToken(String token) {
        Long userid = null;
        try {
            Claims claims = getClaimsFromToken(token);
            userid = Long.valueOf("" + claims.get(CLAIM_KEY_USERID));
        } catch (Exception e) {
        	 log.error(e.getMessage());  
        }
        return userid;
    }
    
    /**
     * 获取用户编号
     *
     * @param token TOKEN信息
     * @return
     */
    public UserBaseInformation getUserInfoFromToken(String token) {
    	UserBaseInformation userInfo = null;
        try {
            Claims claims = getClaimsFromToken(token);
            userInfo =JsonUtil.jsonToPojo((String)claims.get(CLAIM_KEY_USERINFO), UserBaseInformation.class);
        } catch (Exception e) {
        	 log.error(e.getMessage());  
        }
        return userInfo;
    }


    /**
     * 获取创建时间
     *
     * @param token TOKEN信息
     * @return
     */
    public Date getCreatedDateFromToken(String token) {
        Date created = null;
        try {
            Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
        	 log.error(e.getMessage());  
        }
        return created;
    }


    /**
     * 获取TOKEN失效时间
     *
     * @param token TOKEN信息
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration = null;
        try {
            Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
        	 log.error(e.getMessage()); 
        }
        return expiration;
    }

    /**
     * 校验TOKEN是否过期
     *
     * @param token TOKEN信息
     * @return
     */
    public Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    /**
     * TOKEN创建时间是否在密码最后修改时间之前
     *
     * @param created           创建时间
     * @param lastPasswordReset 密码最后修改时间
     * @return
     */
    public Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (null != lastPasswordReset && created.before(lastPasswordReset));
    }


    /**
     * 检验是否需要刷新TOKEN信息
     *
     * @param token             TOKEN信息
     * @param lastPasswordReset 密码修改时间
     * @return
     */
    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && !isTokenExpired(token);
    }


    /**
     * 验证TOKEN是否过期
     *
     * @param token TOKEN信息
     * @return
     */
    public Boolean validateToken(String token) {
        String username = getUsernameFromToken(token);
        Date created = getCreatedDateFromToken(token);
        return (!isTokenExpired(token));
    }


    /**
     * 根据请求信息获取用户TOKEN信息
     *
     * @param request 请求头信息
     * @return
     */
  /*  public UserBaseInformationDTO getTokenInfo(HttpServletRequest request) {
    	UserBaseInformationDTO userInfo = new UserBaseInformationDTO();
        //判断请求头是否包含授权TOKEN
        String token = request.getHeader("_ImpToken_");
        if (null == token) {
            //判断COOKIE是否包含授权TOKEN
            Cookie[] cookies = request.getCookies();
            if (null != cookies) {
                for (Cookie cookie : cookies) {
                    if ("_ImpToken_".toLowerCase().equals(cookie.getName().toLowerCase())) {
                        token = cookie.getValue();
                    }
                }
            }

        }

        if (null == token) {
           // userInfo.setErrCode(StatusCode.TOKEN_NOT_FIND);
        } else {
            //通过TOKEN获取用户信息
           // userInfo = redisUtils.getTokenInfo(token);
            if (null == userInfo) {
                userInfo = new UserBaseInformationDTO();
               // userInfo.setErrCode(StatusCode.TOKEN_INVALID);
            }
        }

        return userInfo;

    }*/
    public static void main(String args[]) {
    	JWTUtils test=new JWTUtils();
    	UserBaseInformation input=new UserBaseInformation();
    	input.setUserName("zwh");
        input.setId(1);
    	String token=test.generateToken(input);
    	System.out.println(token);
    	System.out.println(test.validateToken(token));
    	System.out.println(test.getUsernameFromToken(token));
    	System.out.println(test.getExpirationDateFromToken(token));
    	System.out.println(JsonUtil.pojoToJson(test.getUserInfoFromToken(token)));
    	System.out.println(new Date(System.currentTimeMillis() + 864000000L));
    	
    }

}
