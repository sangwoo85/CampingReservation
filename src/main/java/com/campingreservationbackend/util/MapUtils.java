package com.campingreservationbackend.util;

import java.util.Map;

public class MapUtils {

    public static String getString(Map<String,Object> map, String key){

        String value = "";

        if(map != null){

            try{
                Object obj = map.get(key);
                if(obj.getClass().isInstance(String.class)){
                    value = StringUtils.getString(obj);
                }
            }catch(Exception e){
                e.printStackTrace();
            }

        }
        return value;
    }
}
