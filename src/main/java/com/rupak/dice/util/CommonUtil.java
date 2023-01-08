package com.rupak.dice.util;

public class CommonUtil {
	
    public static Boolean isValueNotNullAndEmpty(Object key){
        if(key != null && !key.toString().trim().equals("")){
            return true;
        }
        return false;
    }
}
