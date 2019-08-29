package com.tima.admin.util;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.tima.admin.dto.AAADTO;
import com.tima.admin.dto.ThirdPartyRequestDTO;
import com.tima.admin.enums.ThirdPartyEnum;
import lombok.extern.slf4j.Slf4j;


/*
*  @url请求地址
*  @param 请求参数类
*  @respEntity 返回数据 类型类
*  @methodFlag 请求方式  0 =POST , 1=GET
*
* */
@Slf4j
public class thirdPartyRequestUtil {
	
	public static <T, K> ThirdPartyRequestDTO requestAAA(String url, T param, String token, String authorization,
			K respEntity, Integer methodFlag) {
		Map<String, Object> resultMap=new HashMap<>();
		ThirdPartyRequestDTO tprRes=new ThirdPartyRequestDTO();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json; charset=UTF-8");
		if(null!=token) {
			headers.add("Token", token);
			tprRes.setToken(token);
		}
		if(null!=authorization) {
			headers.add("Authorization", authorization);
			tprRes.setAuthorization(authorization);
		}
		RestTemplate restTemplate =  new RestTemplate(new HttpComponentsClientHttpRequestFactory());
	     ResponseErrorHandler responseErrorHandler = new ResponseErrorHandler() {
	            @Override
	            public boolean hasError(ClientHttpResponse clientHttpResponse){
	                return true;
	            }
	            @Override
	            public void handleError(ClientHttpResponse clientHttpResponse){

	            }
	        };
		restTemplate.setErrorHandler(responseErrorHandler);
		String paramStr = "";
		HttpEntity<T> formEntity =null;
		ResponseEntity res = null;
		if (null != param) {
			formEntity = new HttpEntity<T>(param, headers);
			try {
				paramStr = JsonUtil.parseURLPairByMap((Map<String, Object>) param);
			} catch (Exception e) {
				log.info("接口错误描述",e);
			}
			if (!"".equals(paramStr) && paramStr != null)
				url += "?" + paramStr;
		}
		
		for (int i = 0; i < 3; i++) {
			log.info("调用AAA接口：：：：请求第  " + i + "  次");
			log.info(url);
			try {
				res =  restTemplate.exchange(url, methodFlag == 0 ? HttpMethod.POST : HttpMethod.GET, formEntity,JSONObject.class);
				if (res.getStatusCodeValue() == 200) {
					tprRes.setErrorCode(res.getStatusCodeValue());
					resultMap.put("status", res.getStatusCodeValue());
					resultMap.put("responseJson", res.getBody());
					log.info("--------------------   接口返回   -------------------");
					if(null!=res.getBody()) {
						log.info(res.getBody().toString());
						Map<String, String> responseMap=(Map<String, String>) res.getBody();
						tprRes.setToken(responseMap.get("access_token"));
						tprRes.setRefreshToken(responseMap.get("refresh_token"));
						System.out.println(responseMap.toString());
						AAADTO result =JsonUtil.jsonToPojo(JSONUtils.toJSONString(responseMap), new TypeReference<AAADTO>() {});
						tprRes.setAAAdto(result);
					}else {
						log.info(res.toString());
						tprRes.setResponseJson(res.toString());
					}
					tprRes.setRequestJson(url);
					tprRes.setRequestSource(ThirdPartyEnum.AAA.getThirdPartyNo());
					
					break;
				}else{
					ThirdPartyRequestDTO errObj=new ThirdPartyRequestDTO();
					System.out.println(res.getBody().toString());
					Map<String, String> responseMap=(Map<String, String>) res.getBody();
					errObj.setErrorCode(res.getStatusCodeValue());
					errObj.setErrorDescription(responseMap.get("error_description"));
					return errObj;
				}
			} catch (Exception e) {
				log.info("接口错误描述",e);
				tprRes=null;
			}
		}

		return  tprRes;
	}
	
	public static <T, K> ThirdPartyRequestDTO requestTSP(String url, T param, String token,
			K respEntity, Integer methodFlag) {
		Map<String, Object> resultMap=new HashMap<>();
		ThirdPartyRequestDTO tprRes=new ThirdPartyRequestDTO();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json; charset=UTF-8");
		if(null!=token) {
			headers.add("identityParam", token);
			tprRes.setToken(token);
		}
		RestTemplate restTemplate =  new RestTemplate(new HttpComponentsClientHttpRequestFactory());
	     ResponseErrorHandler responseErrorHandler = new ResponseErrorHandler() {
	            @Override
	            public boolean hasError(ClientHttpResponse clientHttpResponse){
	                return true;
	            }
	            @Override
	            public void handleError(ClientHttpResponse clientHttpResponse){

	            }
	        };
		restTemplate.setErrorHandler(responseErrorHandler);
		String paramStr = "";
		HttpEntity<T> formEntity =null;
		ResponseEntity res = null;
		if (null != param) {
			formEntity = new HttpEntity<T>(param, headers);
			try {
				paramStr = JsonUtil.parseURLPairByMap((Map<String, Object>) param);
			} catch (Exception e) {
				log.info("接口错误描述",e);
			}
			if (!"".equals(paramStr) && paramStr != null)
				url += "?" + paramStr;
		}
		boolean falg=false;
		for (int i = 0; i < 3; i++) {
			log.info("调用AAA接口：：：：请求第  " + i + "  次");
			log.info(url);
			tprRes.setRequestJson(url);
			tprRes.setRequestSource(ThirdPartyEnum.TSP.getThirdPartyNo());
			try {
				res =  restTemplate.exchange(url, methodFlag == 0 ? HttpMethod.POST : HttpMethod.GET, formEntity,JSONObject.class);
				Map<String, Object> responseMap=(Map<String, Object>) res.getBody();
				tprRes.setResponseJson(responseMap.toString());
				log.info("--------------------   接口返回   -------------------");
				log.info(res.getBody().toString());
				if((boolean) responseMap.get("returnSuccess")) {
					tprRes.setErrorCode(200);
					tprRes.setErrorDescription("TSP用户修改成功");
					break;
				}else {
					tprRes.setErrorCode(500);
					tprRes.setErrorDescription("TSP用户修改失败");
				}
			} catch (Exception e) {
				log.info("接口错误描述",e);
				tprRes=null;
			}
		}

		return  tprRes;
	}
	
//	public static void main(String[] args) {
//		String a="http://test.jac.timanetwork.net/jvconnectedcar/vehicle/set-vehicle-urgent-contacts";
//		Map<String, String> map=new HashMap<>();
//		map.put("urgentPersonNum", "1344423335");
//		String c="{\"token\":\"sdfasdfasdfasd\",\"phone\":\"1231341234\",\"userId\":\"123131\"}";
//		boolean obj=thirdPartyRequestUtil.requestTSP(a, map, c, new Object(), 0);
//		System.out.println(obj);
//	}

}
