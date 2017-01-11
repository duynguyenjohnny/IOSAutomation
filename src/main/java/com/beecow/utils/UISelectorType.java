package com.beecow.utils;

/**
 * Created by HangPham on 12/18/2016.
 */
public enum UISelectorType {
    RESOURCE_ID("resourceID"),
    TEXT_CONTAINS("textContains"),
    TEXT("text"),
    TEXT_START_WITH("textStartWith"),
    SELECTED("selected"),
    CLASS_NAME("className"),
    XPATH("xpath"),
    ANDROID_PACKAGE("android"),
    VALUE("value")
    ;

    private String UISelectorCode;

    /**
     * init new UISelectorType
     * @param s
     */
    private UISelectorType(String s) {
        UISelectorCode = s;
    }

    /**
     * get UISelectorCode
     * @return String
     */
    public String getUISelectorCode(){
        return UISelectorCode;
    }

    /**
     * init new UISelectorType from String
     * @param selectorTypeStr
     * @return UISelectorType
     */
    public static UISelectorType fromString(String selectorTypeStr){
        if(selectorTypeStr!=null){
            for(UISelectorType b: UISelectorType.values()){
                if (selectorTypeStr.equalsIgnoreCase(b.UISelectorCode)){
                    return b;
                }
            }
        }
        return null;
    }

}
