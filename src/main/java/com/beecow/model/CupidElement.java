package com.beecow.model;

import com.beecow.utils.Utils;

/**
 * Created by Hoang Nguyen on Jan 16 2017
  */

public class CupidElement {

    //region Cupid_1.png
    public static String tab_Cupid(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.FrameLayout[contains(@resource-id,'bottom_navigation_tab_cupid')]";
        }
        return "ios";
    }

    public static String hint_TurnONCupid(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.TextView[contains(@text,'Would you like to make new\n" +
                    "friends? Turn on Cupid feature') and contains(@resource-id,'fragment_cupid_tooltip')]";
        }
        return "ios";
    }

    public static String switch_CupidFeatureON(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.Switch[@text='ON' and contains(@resource-id,'fragment_cupid_sh_toggle')]";
        }
        return "ios";
    }

    public static String switch_CupidFeatureOFF(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.Switch[@text='OFF' and contains(@resource-id,'fragment_cupid_sh_toggle')]";
        }
        return "ios";
    }
    //endregion

    //region Cupid_2.png
    public static String input_CupidAlias(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.EditText[contains(@resource-id,'fragment_cupid_et_my_alias')]";
        }
        return "ios";
    }

    public static String select_CupidGender(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.RelativeLayout[contains(@resource-id,'fragment_cupid_st_user_gender')]";
        }
        return "ios";
    }

    public static String select_CupidGender_Man(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.RelativeLayout[contains(@resource-id,'fragment_cupid_st_user_gender')]//android.widget.TextView[@text='Man']";
        }
        return "ios";
    }
    public static String select_CupidGender_Woman(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.RelativeLayout[contains(@resource-id,'fragment_cupid_st_user_gender')]//android.widget.TextView[@text='Woman']";
        }
        return "ios";
    }

    public static String select_CupidLookingFor(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.RelativeLayout[contains(@resource-id,'fragment_cupid_st_looking_for')]";
        }
        return "ios";
    }

    public static String select_CupidLookingFor_Woman(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.RelativeLayout[contains(@resource-id,'fragment_cupid_st_looking_for')]//android.widget.TextView[@text='Woman']";
        }
        return "ios";
    }

    public static String select_CupidLookingFor_Man(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.RelativeLayout[contains(@resource-id,'fragment_cupid_st_looking_for')]//android.widget.TextView[@text='Man']";
        }
        return "ios";
    }

    public static String select_CupidLookingFor_Both(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.RelativeLayout[contains(@resource-id,'fragment_cupid_st_looking_for')]//android.widget.TextView[@text='Both']";
        }
        return "ios";
    }
    public static String select_CupidBirthday(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.EditText[contains(@resource-id,'fragment_cupid_et_birthday')]";
        }
        return "ios";
    }

    public static String btn_CupidSelectPhoto(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.ImageView[contains(@resource-id,'fragment_cupid_ib_choose_photo')]";
        }
        return "ios";
    }

    public static String btn_CupidSaveEnable(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.TextView[contains(@resource-id,'fragment_cupid_btn_save') and @enabled='true']";
        }
        return "ios";
    }

    public static String btn_CupidSaveDisable(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.TextView[contains(@resource-id,'fragment_cupid_btn_save') and @enabled='false']";
        }
        return "ios";
    }
    //endregion

}

