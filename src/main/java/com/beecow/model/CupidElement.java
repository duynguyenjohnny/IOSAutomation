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

    public static String btn_CupidSelectBigPhoto(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.ImageButton[contains(@resource-id,'fragment_cupid_ib_choose_photo')]";
        }
        return "ios";
    }

    public static String btn_CupidSaveEnable(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.TextView[@text='Save' and @enabled='true']";
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


    //region Cupid_3.png
    public static String btn_Calendar_OK(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.Button[contains(@text,'OK')]";
        }
        return "ios";
    }
    //endregion

    //region Cupid_4.png
    public static String btn_CupidSelectSmallPhoto(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.ImageButton[contains(@resource-id,'item_cupid_choose_photo_iv_image')";
        }
        return "ios";
    }
    //endregion

    //region Cupid_5.png
    public static String btn_ChooseEnabled(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.Button[contains(@resource-id,'fragment_choose_photo_dialog_btn_choose') and @enabled='true']";
        }
        return "ios";
    }

    public static String btn_ChooseDisabled(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.Button[contains(@resource-id,'fragment_choose_photo_dialog_btn_choose') and @enabled='false']";
        }
        return "ios";
    }

    public static String btn_Camera(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.View[contains(@resource-id,'item_choose_photo_dialog_film_layer')]";
        }
        return "ios";
    }
    //endregion

    //region Cupid_6.png
    public static String btn_GiftTab(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.TextView[contains(@resource-id,'fragment_cupid_btn_gift_list']";
        }
        return "ios";
    }

    public static String btn_SavedTab(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.TextView[contains(@resource-id,'fragment_cupid_btn_saved_list']";
        }
        return "ios";
    }

    public static String btn_MatchedTab(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.TextView[contains(@resource-id,'fragment_cupid_btn_matched_list']";
        }
        return "ios";
    }

    public static String txt_UserNameOnCard(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.TextView[contains(@resource-id,'cupid_swipe_card_layout_tv_user_name']";
        }
        return "ios";
    }

    public static String btn_SaveProfile(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.ImageButton[contains(@resource-id,'cupid_swipe_card_layout_btn_save']";
        }
        return "ios";
    }

    public static String btn_DislikeEnabled(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.ImageButton[contains(@resource-id,'fragment_cupid_btn_dislike_enabled']";
        }
        return "ios";
    }

    public static String btn_ChatEnabled(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.ImageButton[contains(@resource-id,'fragment_cupid_btn_stay_hi_enabled']";
        }
        return "ios";
    }

    public static String btn_GiftEnabled(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.ImageButton[contains(@resource-id,'fragment_cupid_btn_gift_enabled']";
        }
        return "ios";
    }
    public static String btn_LikeDisabled(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.ImageButton[contains(@resource-id,'fragment_cupid_btn_like_enabled']";
        }
        return "ios";
    }
    public static String txt_YearOldDistanceOnCard(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.TextView[contains(@resource-id,'cupid_swipe_card_layout_tv_old_and_distance']";
        }
        return "ios";
    }
    //endregionn

    //region Cupid_7.png
    public static String txt_LimitReached(){
        if(Utils.getInstance().isAndroidDevice()){
            return "xpath:://android.widget.TextView[contains(@resource-id,'cupid_swipe_card_oops_limit_reached_tv_opps') and @text='Oops! Limit reached!']";
        }
        return "ios";
    }
    //endregion
}

