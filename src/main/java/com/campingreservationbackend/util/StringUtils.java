package com.campingreservationbackend.util;

public class StringUtils {

    public static String getString(Object obj){

        String value = "";
        try {
            if (obj != null) {
                value = String.valueOf(obj);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return value;
        }
    }

    public static boolean isEmpty(String value){

        if(value != null || "".equals(value)){
            return value.isEmpty();
        }else{
            return true;
        }
    }

    public static boolean isNotEmpty(String value){

        return !StringUtils.isEmpty(value);
    }

    public static String join(String join,String ...params){
        StringBuffer strBuffer = new StringBuffer();
        for(String param : params){
            strBuffer.append(param);
            strBuffer.append(join);
        }
        return strBuffer.toString();
    }
}
