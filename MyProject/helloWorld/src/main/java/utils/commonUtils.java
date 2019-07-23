package utils;

import java.util.Optional;

public class commonUtils {

    public static Object getOptional(Object object,Object defaultParam){
        return Optional.ofNullable(object).orElse(defaultParam);
    }


}
