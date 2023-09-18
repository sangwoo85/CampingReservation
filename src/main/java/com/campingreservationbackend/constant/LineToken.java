package com.campingreservationbackend.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum LineToken {

    KIM("Kim","7YPpeAwicOBcv9MoZfCGdL4eHfBx9FoW2nmJrbWc8mG"
            ,Arrays.asList("imgingak")
            ,Arrays.asList("김선우","김상우")),

    IMGINGAK("IMGINGAK","AdhTq2voezpmUwFB8ClfjgjpXkNZczERTecYvOfoPuT"
            ,Arrays.asList("imgingak")
            ,Arrays.asList("김상우","강윤희","유인성","박강민"));
    ;

    String name;
    String token;
    List<String> campList;
    List<String> members;

    LineToken(String name, String token, List<String> campList,List<String> members){
        this.name = name;
        this.token = token;
        this.campList = campList;
        this.members = members;
    }

    public String TOKEN(){
        return token;
    }

    public String NAME(){
        return name;
    }

    public List<String> CAMPLIST(){
        return campList;
    }

    public List<String> MEMBERS(){
        return members;
    }

    public static List<String> getNameByToekns(String campName){
        List<String> tokens = new ArrayList<String>();
        LineToken[] lineTokens = LineToken.class.getEnumConstants();
        for(LineToken lineToken : lineTokens){
            if(lineToken.CAMPLIST().contains(campName)){
                tokens.add(lineToken.TOKEN());
            }
        }
        return tokens;
    }
}
