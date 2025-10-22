package com.netease.nis.quicklogin.cordovaplugin;

import static com.netease.nis.quicklogin.QuickLogin.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netease.nis.basesdk.Logger;
import com.netease.nis.quicklogin.QuickLogin;
import com.netease.nis.quicklogin.helper.UnifyUiConfig;
import com.netease.nis.quicklogin.listener.ActivityLifecycleCallbacks;
import com.netease.nis.quicklogin.listener.LoginListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

/**
 * Created by hzhuqi on 2020/1/2
 */
public class JsonConfigParser {
    private String statusBarColor;
    private boolean isStatusBarDarkColor = false;
    private String navBackIcon;
    private int navBackIconWidth = 25;
    private int navBackIconHeight = 25;
    private int navBackIconGravity = 0;
    // 导航栏返回距离左边的距离
    private int navBackIconMargin = 0;
    private boolean isHideBackIcon = false;
    private int navHeight;
    private String navBackgroundColor;
    private String navTitle;
    private int navTitleSize = 18;
    // 导航栏标题大小，单位 dp
    private int navTitleDpSize = 0;
    private boolean isNavTitleBold;
    private String navTitleColor;
    private boolean isHideNav = false;
    private String logoIconName;
    private int logoWidth = 50;
    private int logoHeight = 50;
    private int logoTopYOffset;
    private int logoBottomYOffset;
    private int logoXOffset;
    private boolean isHideLogo = false;
    private String maskNumberColor;
    private int maskNumberSize;
    private int maskNumberDpSize;
    private int maskNumberTopYOffset;
    private int maskNumberBottomYOffset;
    private int maskNumberXOffset;
    private boolean isMaskNumberBold = false;
    private int sloganSize = 14;
    private int sloganDpSize;
    private String sloganColor;
    private int sloganTopYOffset;
    private int sloganBottomYOffset;
    private int sloganXOffset;
    private boolean isSloganBold = false;
    private String loginBtnText = "本机号码一键登录";
    private int loginBtnTextSize = 15;
    private int loginBtnTextDpSize;
    private String loginBtnTextColor;
    private int loginBtnWidth;
    private int loginBtnHeight;
    // 登录按钮左边距
    private int loginBtnMarginLeft;
    // 登录按钮右边距
    private int loginBtnMarginRight;
    private String loginBtnBackgroundRes;
    private int loginBtnTopYOffset;
    private int loginBtnBottomYOffset;
    private int loginBtnXOffset;
    private boolean isLoginBtnBold = false;
    private boolean isPrivacyBold = false;
    private String privacyTextColor;
    // 协议未勾选弹窗隐私栏文本颜色，不包括协议
    private String privacyDialogTextColor;
    private String privacyProtocolColor;
    // 协议未勾选弹窗隐私栏协议颜色
    private String privacyDialogProtocolColor;
    private int privacySize = 13;
    private int privacyDpSize;
    private int privacyTopYOffset;
    private int privacyBottomYOffset;
    // 隐私协议区域宽度
    private int privacyWidth;
    private int privacyTextMarginLeft = 0;
    private int privacyMarginLeft;
    private int privacyMarginRight;
    private boolean privacyState = true;
    private boolean isHidePrivacySmh = false;
    private boolean isHidePrivacyCheckBox = false;
    private boolean isPrivacyTextGravityCenter = false;
    private int checkBoxGravity;
    private int checkBoxWith = 0;
    private int checkBoxHeight = 0;
    private String checkedImageName;
    private String unCheckedImageName;
    private String privacyTextStart = "登录即同意";
    // 隐私协议开始文本字体大小
    private int privacyTextStartSize;
    // 隐私文本行间距
    private int privacyLineSpacingAdd;
    // 隐私文本行间距倍数
    private int privacyLineSpacingMul;
    // 运营商协议和自定义协议的连接符
    private String protocolConnect = "和";
    // 自定义协议之间的连接符
    private String userProtocolConnect = "、";
    // 运营商协议是否在末尾
    private boolean operatorPrivacyAtLast = false;
    private String protocolText;
    private String protocolLink;
    private String protocol2Text;
    private String protocol2Link;
    private String protocol3Text;
    private String protocol3Link;
    private String privacyTextEnd = "且授权使用本机号码登录";
    private String protocolNavTitle;
    // 协议Web页面导航栏标题颜色
    private String protocolNavTitleColor = "#080808";
    private String protocolNavBackIcon;
    private int protocolNavHeight;
    private int protocolNavTitleSize;
    private int protocolNavTitleDpSize;
    private int protocolNavBackIconWidth = 25;
    private int protocolNavBackIconHeight = 25;
    // 协议 Web 页面导航栏返回按钮距离左边的距离
    private int protocolNavBackIconMargin;
    private String protocolNavColor;
    private String backgroundImage;
    private String backgroundGif;
    private String backgroundVideo;
    private String backgroundVideoImage;
    private boolean isLandscape;
    private boolean isProtocolNavTitleBold = false;
    private boolean isDialogMode;
    // dialog模式点击空白处是否自动关闭
    private boolean isDialogHideOnTouchOutside = true;
    private int dialogWidth;
    private int dialogHeight;
    private int dialogX;
    private int dialogY;
    private boolean isBottomDialog;
    private boolean isProtocolDialogMode = false;
    private boolean isPrivacyDialogAuto = false;
    private boolean isShowPrivacyDialog = true;
    // 隐私弹窗宽度
    private int privacyDialogWidth;
    // 隐私弹窗高度
    private int privacyDialogHeight;
    // 隐私弹窗背景
    private String privacyDialogBg;
    // 隐私弹窗标题
    private String privacyDialogTitle = "服务协议及隐私";
    // 隐私弹窗标题字体大小
    private float privacyDialogTitleSize = 18;
    // 隐私弹窗标题颜色
    private String privacyDialogTitleColor;
    // 隐私弹窗标题是否加粗
    private boolean isPrivacyDialogTitleBold = true;
    // 隐私弹窗标题距离顶部的距离
    private int privacyDialogTitleMarginTop = 24;
    // 隐私弹窗开始文本
    private String privacyDialogContentStart = "请您仔细阅读";
    // 隐私弹窗结束文本
    private String privacyDialogContentEnd = "，点击“确认”，表示您已经阅读并同意以上协议";
    // 隐私弹窗文本距离左边的距离
    private int privacyDialogContentMarginLeft = 12;
    // 隐私弹窗文本距离右边的距离
    private int privacyDialogContentMarginRight = 12;
    // 隐私弹窗文本距离顶部的距离
    private int privacyDialogContentMarginTop = 20;
    // 隐私弹窗文本是否加粗
    private boolean isPrivacyDialogContentBold = false;
    // 隐私弹窗按钮宽度
    private int privacyDialogBtnWidth;
    // 隐私弹窗按钮高度
    private int privacyDialogBtnHeight;
    // 隐私弹窗按钮文本大小
    private float privacyDialogBtnTextSize;
    // 隐私弹窗按钮同意文本颜色
    private String privacyDialogBtnAgreeTextColor;
    // 隐私弹窗按钮拒绝文本颜色
    private String privacyDialogBtnDisagreeTextColor = "#585858";
    // 隐私弹窗按钮距离左边距离
    private int privacyDialogBtnMarginLeft = 12;
    // 隐私弹窗按钮距离右边距离
    private int privacyDialogBtnMarginRight = 12;
    // 隐私弹窗按钮距离顶部距离
    private int privacyDialogBtnMarginTop = 20;
    // 隐私弹窗按钮距离底部距离
    private int privacyDialogBtnMarginBottom = 12;
    // 隐私弹窗按钮同意文本
    private String privacyDialogBtnAgreeText = "同意";
    // 隐私弹窗按钮拒绝文本
    private String privacyDialogBtnDisagreeText = "拒绝";
    // 隐私弹窗按钮同意文本背景
    private String privacyDialogBtnAgreeBg;
    // 隐私弹窗按钮拒绝文本背景
    private String privacyDialogBtnDisagreeBg;
    private String privacyDialogText;
    private float privacyDialogSize = 15;
    private JSONArray widgets;
    private String enterAnimation;
    private String exitAnimation;
    private boolean isShowLoading;
    private boolean isBackPressedAvailable = true;
    // 是否隐藏虚拟键
    private boolean isVirtualButtonHidden;

    private static final String LIFECYCLE = "lifecycle";
    private static final String CLICK_VIEW_TYPE = "clickViewType";

    private static final String CHECKBOX_CHECKED = "isCheckboxChecked";

    private static final String WHITE = "#FFFFFF";

    void parser(JSONObject jsonObject, Context context) throws JSONException {
        statusBarColor = jsonObject.has("statusBarColor") ? jsonObject.optString("statusBarColor") : "";
        isStatusBarDarkColor = jsonObject.has("isStatusBarDarkColor") && jsonObject.optBoolean("isStatusBarDarkColor");

        parseNav(jsonObject);

        parseLogo(jsonObject);

        parseMaskNumber(jsonObject);

        parseSlogan(jsonObject);

        parseLoginBtn(jsonObject);


        parsePrivacy(jsonObject);

        parsePrivacyDialog(jsonObject);

        parsePrivacySplit(jsonObject);

        parseProtocol(jsonObject);

        parseProtocolNav(jsonObject);

        parseBackGround(jsonObject);

        parseDialog(context, jsonObject);

        parseOther(jsonObject);
        Log.d(TAG, "json config parser finished");
    }

    /**
     * 解析导航栏
     *
     * @param jsonObject json配置文件
     */
    private void parseNav(JSONObject jsonObject) {
        navBackIcon = jsonObject.has("navBackIcon") ? jsonObject.optString("navBackIcon") : "";
        navBackIconWidth = jsonObject.has("navBackIconWidth") ? jsonObject.optInt("navBackIconWidth") : 25;
        navBackIconHeight = jsonObject.has("navBackIconHeight") ? jsonObject.optInt("navBackIconHeight") : 25;
        navBackIconGravity = jsonObject.has("navBackIconGravity") ? jsonObject.optInt("navBackIconGravity") : Gravity.START;
        navBackIconMargin = jsonObject.has("navBackIconMargin") ? jsonObject.optInt("navBackIconMargin") : 0;
        isHideBackIcon = jsonObject.has("isHideBackIcon") && jsonObject.optBoolean("isHideBackIcon");
        navHeight = jsonObject.has("navHeight") ? jsonObject.optInt("navHeight") : 50;
        navBackgroundColor = jsonObject.has("navBackgroundColor") ? jsonObject.optString("navBackgroundColor") : WHITE;
        navTitle = jsonObject.has("navTitle") ? jsonObject.optString("navTitle") : "免密登录";
        navTitleSize = jsonObject.has("navTitleSize") ? jsonObject.optInt("navTitleSize") : 18;
        navTitleDpSize = jsonObject.has("navTitleDpSize") ? jsonObject.optInt("navTitleDpSize") : 0;
        isNavTitleBold = jsonObject.has("isNavTitleBold") && jsonObject.optBoolean("isNavTitleBold");
        navTitleColor = jsonObject.has("navTitleColor") ? jsonObject.optString("navTitleColor") : "#000000";
        isHideNav = jsonObject.has("isHideNav") && jsonObject.optBoolean("isHideNav");
    }

    /**
     * 解析应用logo
     *
     * @param jsonObject json配置文件
     */
    private void parseLogo(JSONObject jsonObject) {
        logoIconName = jsonObject.has("logoIconName") ? jsonObject.optString("logoIconName") : "";
        logoWidth = jsonObject.has("logoWidth") ? jsonObject.optInt("logoWidth") : 50;
        logoHeight = jsonObject.has("logoHeight") ? jsonObject.optInt("logoHeight") : 50;
        logoTopYOffset = jsonObject.has("logoTopYOffset") ? jsonObject.optInt("logoTopYOffset") : 0;
        logoBottomYOffset = jsonObject.has("logoBottomYOffset") ? jsonObject.optInt("logoBottomYOffset") : 0;
        logoXOffset = jsonObject.has("logoXOffset") ? jsonObject.optInt("logoXOffset") : 0;
        isHideLogo = jsonObject.has("isHideLogo") && jsonObject.optBoolean("isHideLogo");
    }

    /**
     * 解析手机掩码
     *
     * @param jsonObject json配置文件
     */
    private void parseMaskNumber(JSONObject jsonObject) {
        maskNumberColor = jsonObject.has("maskNumberColor") ? jsonObject.optString("maskNumberColor") : "#000000";
        maskNumberSize = jsonObject.has("maskNumberSize") ? jsonObject.optInt("maskNumberSize") : 18;
        maskNumberDpSize = jsonObject.has("maskNumberDpSize") ? jsonObject.optInt("maskNumberDpSize") : 18;
        maskNumberTopYOffset = jsonObject.has("maskNumberTopYOffset") ? jsonObject.optInt("maskNumberTopYOffset") : 0;
        maskNumberBottomYOffset = jsonObject.has("maskNumberBottomYOffset") ? jsonObject.optInt("maskNumberBottomYOffset") : 0;
        maskNumberXOffset = jsonObject.has("maskNumberXOffset") ? jsonObject.optInt("maskNumberXOffset") : 0;
        isMaskNumberBold = jsonObject.has("isMaskNumberBold") && jsonObject.optBoolean("isMaskNumberBold");
    }

    /**
     * 解析slogan
     *
     * @param jsonObject json配置文件
     */
    private void parseSlogan(JSONObject jsonObject) {
        sloganSize = jsonObject.has("sloganSize") ? jsonObject.optInt("sloganSize") : 14;
        sloganDpSize = jsonObject.has("sloganDpSize") ? jsonObject.optInt("sloganDpSize") : 0;
        sloganColor = jsonObject.has("sloganColor") ? jsonObject.optString("sloganColor") : "#9A9A9A";
        sloganTopYOffset = jsonObject.has("sloganTopYOffset") ? jsonObject.optInt("sloganTopYOffset") : 0;
        sloganBottomYOffset = jsonObject.has("sloganBottomYOffset") ? jsonObject.optInt("sloganBottomYOffset") : 0;
        sloganXOffset = jsonObject.has("sloganXOffset") ? jsonObject.optInt("sloganXOffset") : 0;
        isSloganBold = jsonObject.has("isSloganBold") && jsonObject.optBoolean("isSloganBold");
    }

    /**
     * 解析登录按钮
     *
     * @param jsonObject json配置文件
     */
    private void parseLoginBtn(JSONObject jsonObject) {
        loginBtnText = jsonObject.has("loginBtnText") ? jsonObject.optString("loginBtnText") : "一键登录";
        loginBtnTextSize = jsonObject.has("loginBtnTextSize") ? jsonObject.optInt("loginBtnTextSize") : 15;
        loginBtnTextDpSize = jsonObject.has("loginBtnTextDpSize") ? jsonObject.optInt("loginBtnTextDpSize") : 0;
        loginBtnTextColor = jsonObject.has("loginBtnTextColor") ? jsonObject.optString("loginBtnTextColor") : WHITE;
        loginBtnMarginLeft = jsonObject.has("loginBtnMarginLeft") ? jsonObject.optInt("loginBtnMarginLeft") : 0;
        loginBtnMarginRight = jsonObject.has("loginBtnMarginRight") ? jsonObject.optInt("loginBtnMarginRight") : 0;
        loginBtnWidth = jsonObject.has("loginBtnWidth") ? jsonObject.optInt("loginBtnWidth") : 0;
        loginBtnHeight = jsonObject.has("loginBtnHeight") ? jsonObject.optInt("loginBtnHeight") : 0;
        loginBtnBackgroundRes = jsonObject.has("loginBtnBackgroundRes") ? jsonObject.optString("loginBtnBackgroundRes") : "";
        loginBtnTopYOffset = jsonObject.has("loginBtnTopYOffset") ? jsonObject.optInt("loginBtnTopYOffset") : 0;
        loginBtnBottomYOffset = jsonObject.has("loginBtnBottomYOffset") ? jsonObject.optInt("loginBtnBottomYOffset") : 0;
        loginBtnXOffset = jsonObject.has("loginBtnXOffset") ? jsonObject.optInt("loginBtnXOffset") : 0;
        isLoginBtnBold = jsonObject.has("isLoginBtnBold") && jsonObject.optBoolean("isLoginBtnBold");
    }

    /**
     * 解析隐私协议
     *
     * @param jsonObject json配置文件
     */
    private void parsePrivacy(JSONObject jsonObject) {
        privacyTextColor = jsonObject.has("privacyTextColor") ? jsonObject.optString("privacyTextColor") : "#292929";
        privacyDialogTextColor = jsonObject.has("privacyDialogTextColor") ? jsonObject.optString("privacyDialogTextColor") : "#252525";
        privacyProtocolColor = jsonObject.has("privacyProtocolColor") ? jsonObject.optString("privacyProtocolColor") : "#888888";
        privacyDialogProtocolColor = jsonObject.has("privacyDialogProtocolColor") ? jsonObject.optString("privacyDialogProtocolColor") : "#888888";
        privacySize = jsonObject.has("privacySize") ? jsonObject.optInt("privacySize") : 13;
        privacyDpSize = jsonObject.has("privacyDpSize") ? jsonObject.optInt("privacyDpSize") : 0;
        privacyTopYOffset = jsonObject.has("privacyTopYOffset") ? jsonObject.optInt("privacyTopYOffset") : 0;
        privacyBottomYOffset = jsonObject.has("privacyBottomYOffset") ? jsonObject.optInt("privacyBottomYOffset") : 0;
        privacyWidth = jsonObject.has("privacyWidth") ? jsonObject.optInt("privacyWidth") : 0;
        privacyTextMarginLeft = jsonObject.has("privacyTextMarginLeft") ? jsonObject.optInt("privacyTextMarginLeft") : 0;
        privacyMarginLeft = jsonObject.has("privacyMarginLeft") ? jsonObject.optInt("privacyMarginLeft") : 0;
        privacyMarginRight = jsonObject.has("privacyMarginRight") ? jsonObject.optInt("privacyMarginRight") : 0;
        privacyState = !jsonObject.has("privacyState") || jsonObject.optBoolean("privacyState");
        isHidePrivacySmh = jsonObject.has("isHidePrivacySmh") && jsonObject.optBoolean("isHidePrivacySmh");
        isHidePrivacyCheckBox = jsonObject.has("isHidePrivacyCheckBox") && jsonObject.optBoolean("isHidePrivacyCheckBox");
    }

    /**
     * 解析隐私协议弹窗
     *
     * @param jsonObject json配置文件
     */
    private void parsePrivacyDialog(JSONObject jsonObject) {
        isPrivacyBold = jsonObject.has("isPrivacyBold") && jsonObject.optBoolean("isPrivacyBold");
        privacyDialogWidth = jsonObject.has("privacyDialogWidth") ? jsonObject.optInt("privacyDialogWidth") : 0;
        privacyDialogHeight = jsonObject.has("privacyDialogHeight") ? jsonObject.optInt("privacyDialogHeight") : 0;
        privacyDialogBg = jsonObject.has("privacyDialogBg") ? jsonObject.optString("privacyDialogBg") : "";
        privacyDialogTitle = jsonObject.has("privacyDialogTitle") ? jsonObject.optString("privacyDialogTitle") : "服务协议及隐私";
        privacyDialogTitleSize = jsonObject.has("privacyDialogTitleSize") ? jsonObject.optInt("privacyDialogTitleSize") : 18;
        privacyDialogTitleColor = jsonObject.has("privacyDialogTitleColor") ? jsonObject.optString("privacyDialogTitleColor") : "#292929";
        isPrivacyDialogTitleBold = jsonObject.has("isPrivacyDialogTitleBold") && jsonObject.optBoolean("isPrivacyDialogTitleBold");
        privacyDialogTitleMarginTop = jsonObject.has("privacyDialogTitleMarginTop") ? jsonObject.optInt("privacyDialogTitleMarginTop") : 24;
        privacyDialogContentStart = jsonObject.has("privacyDialogContentStart") ? jsonObject.optString("privacyDialogContentStart") : "请您仔细阅读";
        privacyDialogContentEnd = jsonObject.has("privacyDialogContentEnd") ? jsonObject.optString("privacyDialogContentEnd") : "，点击“确认”，表示您已经阅读并同意以上协议";
        privacyDialogContentMarginLeft = jsonObject.has("privacyDialogContentMarginLeft") ? jsonObject.optInt("privacyDialogContentMarginLeft") : 12;
        privacyDialogContentMarginRight = jsonObject.has("privacyDialogContentMarginRight") ? jsonObject.optInt("privacyDialogContentMarginRight") : 12;
        privacyDialogContentMarginTop = jsonObject.has("privacyDialogContentMarginTop") ? jsonObject.optInt("privacyDialogContentMarginTop") : 20;
        isPrivacyDialogContentBold = jsonObject.has("isPrivacyDialogContentBold") && jsonObject.optBoolean("isPrivacyDialogContentBold");
        parsePrivacyDialogSplit(jsonObject);
    }

    private void parsePrivacyDialogSplit(JSONObject jsonObject) {
        privacyDialogBtnWidth = jsonObject.has("privacyDialogBtnWidth") ? jsonObject.optInt("privacyDialogBtnWidth") : 0;
        privacyDialogBtnHeight = jsonObject.has("privacyDialogBtnHeight") ? jsonObject.optInt("privacyDialogBtnHeight") : 0;
        privacyDialogBtnTextSize = jsonObject.has("privacyDialogBtnTextSize") ? jsonObject.optInt("privacyDialogBtnTextSize") : 0;
        privacyDialogBtnAgreeTextColor = jsonObject.has("privacyDialogBtnAgreeTextColor") ? jsonObject.optString("privacyDialogBtnAgreeTextColor") : "#ffffff";
        privacyDialogBtnDisagreeTextColor = jsonObject.has("privacyDialogBtnDisagreeTextColor") ? jsonObject.optString("privacyDialogBtnDisagreeTextColor") : "#585858";
        privacyDialogBtnMarginLeft = jsonObject.has("privacyDialogBtnMarginLeft") ? jsonObject.optInt("privacyDialogBtnMarginLeft") : 12;
        privacyDialogBtnMarginRight = jsonObject.has("privacyDialogBtnMarginRight") ? jsonObject.optInt("privacyDialogBtnMarginRight") : 12;
        privacyDialogBtnMarginTop = jsonObject.has("privacyDialogBtnMarginTop") ? jsonObject.optInt("privacyDialogBtnMarginTop") : 20;
        privacyDialogBtnMarginBottom = jsonObject.has("privacyDialogBtnMarginBottom") ? jsonObject.optInt("privacyDialogBtnMarginBottom") : 12;
        privacyDialogBtnAgreeText = jsonObject.has("privacyDialogBtnAgreeText") ? jsonObject.optString("privacyDialogBtnAgreeText") : "同意";
        privacyDialogBtnDisagreeText = jsonObject.has("privacyDialogBtnDisagreeText") ? jsonObject.optString("privacyDialogBtnDisagreeText") : "拒绝";
        privacyDialogBtnAgreeBg = jsonObject.has("privacyDialogBtnAgreeBg") ? jsonObject.optString("privacyDialogBtnAgreeBg") : "";
        privacyDialogBtnDisagreeBg = jsonObject.has("privacyDialogBtnDisagreeBg") ? jsonObject.optString("privacyDialogBtnDisagreeBg") : "";
    }

    /**
     * 解析隐私协议拆分
     *
     * @param jsonObject json配置文件
     */
    private void parsePrivacySplit(JSONObject jsonObject) {
        isPrivacyTextGravityCenter = jsonObject.has("isPrivacyTextGravityCenter") && jsonObject.optBoolean("isPrivacyTextGravityCenter");
        checkBoxGravity = jsonObject.has("checkBoxGravity") ? jsonObject.optInt("checkBoxGravity") : Gravity.CENTER;
        checkedImageName = jsonObject.has("checkedImageName") ? jsonObject.optString("checkedImageName") : "yd_checkbox_checked";
        unCheckedImageName = jsonObject.has("unCheckedImageName") ? jsonObject.optString("unCheckedImageName") : "yd_checkbox_unchecked";
        checkBoxWith = jsonObject.has("checkBoxWith") ? jsonObject.optInt("checkBoxWith") : 15;
        checkBoxHeight = jsonObject.has("checkBoxHeight") ? jsonObject.optInt("checkBoxHeight") : 15;
        privacyTextStart = jsonObject.has("privacyTextStart") ? jsonObject.optString("privacyTextStart") : "";
        privacyTextStartSize = jsonObject.has("privacyTextStartSize") ? jsonObject.optInt("privacyTextStartSize") : 0;
        privacyLineSpacingAdd = jsonObject.has("privacyLineSpacingAdd") ? jsonObject.optInt("privacyLineSpacingAdd") : 0;
        privacyLineSpacingMul = jsonObject.has("privacyLineSpacingMul") ? jsonObject.optInt("privacyLineSpacingMul") : 0;
        protocolConnect = jsonObject.has("protocolConnect") ? jsonObject.optString("protocolConnect") : "和";
        userProtocolConnect = jsonObject.has("userProtocolConnect") ? jsonObject.optString("userProtocolConnect") : "、";
        operatorPrivacyAtLast = jsonObject.has("operatorPrivacyAtLast") && jsonObject.optBoolean("operatorPrivacyAtLast");
    }

    /**
     * 解析隐私协议
     *
     * @param jsonObject json配置文件
     */
    private void parseProtocol(JSONObject jsonObject) {
        protocolText = jsonObject.has("protocolText") ? jsonObject.optString("protocolText") : "";
        protocolLink = jsonObject.has("protocolLink") ? jsonObject.optString("protocolLink") : "";
        protocol2Text = jsonObject.has("protocol2Text") ? jsonObject.optString("protocol2Text") : "";
        protocol2Link = jsonObject.has("protocol2Link") ? jsonObject.optString("protocol2Link") : "";
        protocol3Text = jsonObject.has("protocol3Text") ? jsonObject.optString("protocol3Text") : "";
        protocol3Link = jsonObject.has("protocol3Link") ? jsonObject.optString("protocol3Link") : "";
        privacyTextEnd = jsonObject.has("privacyTextEnd") ? jsonObject.optString("privacyTextEnd") : "";
    }

    /**
     * 解析隐私协议导航栏
     *
     * @param jsonObject json配置文件
     */
    private void parseProtocolNav(JSONObject jsonObject) {
        protocolNavTitle = jsonObject.has("protocolNavTitle") ? jsonObject.optString("protocolNavTitle") : "协议详情";
        protocolNavTitleColor = jsonObject.has("protocolNavTitleColor") ? jsonObject.optString("protocolNavTitleColor") : "#080808";
        protocolNavBackIcon = jsonObject.has("protocolNavBackIcon") ? jsonObject.optString("protocolNavBackIcon") : "";
        protocolNavHeight = jsonObject.has("protocolNavHeight") ? jsonObject.optInt("protocolNavHeight") : 50;
        protocolNavTitleSize = jsonObject.has("protocolNavTitleSize") ? jsonObject.optInt("protocolNavTitleSize") : 18;
        protocolNavTitleDpSize = jsonObject.has("protocolNavTitleDpSize") ? jsonObject.optInt("protocolNavTitleDpSize") : 0;
        protocolNavBackIconWidth = jsonObject.has("protocolNavBackIconWidth") ? jsonObject.optInt("protocolNavBackIconWidth") : 25;
        protocolNavBackIconHeight = jsonObject.has("protocolNavBackIconHeight") ? jsonObject.optInt("protocolNavBackIconHeight") : 25;
        protocolNavBackIconMargin = jsonObject.has("protocolNavBackIconMargin") ? jsonObject.optInt("protocolNavBackIconMargin") : 0;
        protocolNavColor = jsonObject.has("protocolNavColor") ? jsonObject.optString("protocolNavColor") : WHITE;
        isProtocolNavTitleBold = jsonObject.has("isProtocolNavTitleBold") && jsonObject.optBoolean("isProtocolNavTitleBold");
    }

    /**
     * 解析背景图片
     *
     * @param jsonObject json配置文件
     */
    private void parseBackGround(JSONObject jsonObject) {
        backgroundImage = jsonObject.has("backgroundImage") ? jsonObject.optString("backgroundImage") : "";
        backgroundGif = jsonObject.has("backgroundGif") ? jsonObject.optString("backgroundGif") : "";
        backgroundVideo = jsonObject.has("backgroundVideo") ? jsonObject.optString("backgroundVideo") : "";
        backgroundVideoImage = jsonObject.has("backgroundVideoImage") ? jsonObject.optString("backgroundVideoImage") : "";
    }

    /**
     * 解析dialog模式
     *
     * @param jsonObject json配置文件
     */
    private void parseDialog(Context context, JSONObject jsonObject) {
        isLandscape = jsonObject.has("isLandscape") && jsonObject.optBoolean("isLandscape");
        isDialogMode = jsonObject.has("isDialogMode") && jsonObject.optBoolean("isDialogMode");
        if (jsonObject.has("isDialogHideOnTouchOutside")) {
            isDialogHideOnTouchOutside = jsonObject.optBoolean("isDialogHideOnTouchOutside");
        }
        dialogWidth = jsonObject.has("dialogWidth") ? jsonObject.optInt("dialogWidth") : getScreenWidth(context);
        dialogHeight = jsonObject.has("dialogHeight") ? jsonObject.optInt("dialogHeight") : 0;
        dialogX = jsonObject.has("dialogX") ? jsonObject.optInt("dialogX") : 0;
        dialogY = jsonObject.has("dialogY") ? jsonObject.optInt("dialogY") : 0;
        isBottomDialog = jsonObject.has("isBottomDialog") && jsonObject.optBoolean("isBottomDialog");
        isProtocolDialogMode = jsonObject.has("isProtocolDialogMode") && jsonObject.optBoolean("isProtocolDialogMode");
        isPrivacyDialogAuto = jsonObject.has("isPrivacyDialogAuto") && jsonObject.optBoolean("isPrivacyDialogAuto");
        isShowPrivacyDialog = !jsonObject.has("isShowPrivacyDialog") || jsonObject.optBoolean("isShowPrivacyDialog");
        privacyDialogText = jsonObject.has("privacyDialogText") ? jsonObject.optString("privacyDialogText") : "";
        privacyDialogSize = jsonObject.has("privacyDialogSize") ? jsonObject.optInt("privacyDialogSize") : 15;
    }

    /**
     * 解析其他
     *
     * @param jsonObject json配置文件
     */
    private void parseOther(JSONObject jsonObject) throws JSONException {
        widgets = jsonObject.getJSONArray("widgets");
        enterAnimation = jsonObject.has("enterAnimation") ? jsonObject.optString("enterAnimation") : "";
        exitAnimation = jsonObject.has("exitAnimation") ? jsonObject.optString("exitAnimation") : "";
        isShowLoading = !(jsonObject.has("isShowLoading") && !jsonObject.optBoolean("isShowLoading"));
        isBackPressedAvailable = !jsonObject.has("isBackPressedAvailable") || jsonObject.optBoolean("isBackPressedAvailable");
        isVirtualButtonHidden = jsonObject.has("isVirtualButtonHidden") && jsonObject.optBoolean("isVirtualButtonHidden");
    }

    public UnifyUiConfig getUiConfig(Context context, JSONObject jsonConfig, KeepAliveCallbackContext callbackContext) {
        try {
            parser(jsonConfig, context);
        } catch (JSONException e) {
            Logger.e(e.getMessage());
        }
        return buildUiConfig(context, callbackContext);
    }

    /**
     * 组装配置信息
     *
     * @param context         Context
     * @param callbackContext KeepAliveCallbackContext
     * @return UnifyUiConfig
     */
    private UnifyUiConfig buildUiConfig(final Context context, KeepAliveCallbackContext callbackContext) {
        UnifyUiConfig.Builder builder = new UnifyUiConfig.Builder()
                .setStatusBarDarkColor(isStatusBarDarkColor)
                .setNavigationIconGravity(navBackIconGravity)
                .setNavigationBackIconWidth(navBackIconWidth)
                .setNavigationBackIconHeight(navBackIconHeight)
                .setNavigationIconMargin(navBackIconMargin)
                .setHideNavigationBackIcon(isHideBackIcon)
                .setNavigationHeight(navHeight)
                .setNavigationBackgroundColor(Color.parseColor(navBackgroundColor))
                .setNavigationTitle(navTitle)
                .setNavTitleSize(navTitleSize)
                .setNavTitleDpSize(navTitleDpSize)
                .setNavTitleBold(isNavTitleBold)
                .setNavigationTitleColor(Color.parseColor(navTitleColor))
                .setHideNavigation(isHideNav)

                .setLogoWidth(logoWidth)
                .setLogoHeight(logoHeight)
                .setLogoXOffset(logoXOffset)
                .setHideLogo(isHideLogo)

                .setMaskNumberSize(maskNumberSize)
                .setMaskNumberDpSize(maskNumberDpSize)
                .setMaskNumberColor(Color.parseColor(maskNumberColor))
                .setMaskNumberXOffset(maskNumberXOffset)
                .setMaskNumberBold(isMaskNumberBold)

                .setSloganSize(sloganSize)
                .setSloganDpSize(sloganDpSize)
                .setSloganColor(Color.parseColor(sloganColor))
                .setSloganXOffset(sloganXOffset)
                .setSloganBold(isSloganBold)

                .setLoginBtnText(loginBtnText)
                .setLoginBtnTextColor(Color.parseColor(loginBtnTextColor))
                .setLoginBtnTextSize(loginBtnTextSize)
                .setLoginBtnTextDpSize(loginBtnTextDpSize)
                .setLoginBtnXOffset(loginBtnXOffset)
                .setLoginBtnBold(isLoginBtnBold)

                .setPrivacyBold(isPrivacyBold)
                .setPrivacyDialogWidth(privacyDialogWidth)
                .setPrivacyDialogHeight(privacyDialogHeight)
                .setPrivacyDialogBg(privacyDialogBg)
                .setPrivacyDialogTitle(privacyDialogTitle)
                .setPrivacyDialogTitleSize(privacyDialogTitleSize)
                .setPrivacyDialogTextSize(privacyDialogSize)
                .setPrivacyDialogTitleColor(Color.parseColor(privacyDialogTitleColor))
                .setPrivacyDialogTitleMarginTop(privacyDialogTitleMarginTop)
                .setIsPrivacyDialogTitleBold(isPrivacyDialogTitleBold)
                .setPrivacyDialogContentStart(privacyDialogContentStart)
                .setPrivacyDialogContentEnd(privacyDialogContentEnd)
                .setIsPrivacyDialogContentBold(isPrivacyDialogContentBold)
                .setPrivacyDialogContentMarginLeft(privacyDialogContentMarginLeft)
                .setPrivacyDialogContentMarginRight(privacyDialogContentMarginRight)
                .setPrivacyDialogContentMarginTop(privacyDialogContentMarginTop)
                .setPrivacyDialogBtnTextSize(privacyDialogBtnTextSize)
                .setPrivacyDialogBtnWidth(privacyDialogBtnWidth)
                .setPrivacyDialogBtnHeight(privacyDialogBtnHeight)
                .setPrivacyDialogBtnMarginLeft(privacyDialogBtnMarginLeft)
                .setPrivacyDialogBtnMarginRight(privacyDialogBtnMarginRight)
                .setPrivacyDialogBtnMarginTop(privacyDialogBtnMarginTop)
                .setPrivacyDialogBtnMarginBottom(privacyDialogBtnMarginBottom)
                .setPrivacyDialogBtnAgreeText(privacyDialogBtnAgreeText)
                .setPrivacyDialogBtnAgreeTextColor(Color.parseColor(privacyDialogBtnAgreeTextColor))
                .setPrivacyDialogBtnAgreeBg(privacyDialogBtnAgreeBg)
                .setPrivacyDialogBtnDisagreeText(privacyDialogBtnDisagreeText)
                .setPrivacyDialogBtnDisagreeTextColor(Color.parseColor(privacyDialogBtnDisagreeTextColor))
                .setPrivacyDialogBtnDisagreeBg(privacyDialogBtnDisagreeBg)

                .setPrivacyTextColor(Color.parseColor(privacyTextColor))
                .setPrivacyDialogTextColor(Color.parseColor(privacyDialogTextColor))
                .setPrivacyProtocolColor(Color.parseColor(privacyProtocolColor))
                .setPrivacyDialogProtocolColor(Color.parseColor(privacyDialogProtocolColor))
                .setPrivacySize(privacySize)
                .setPrivacyDpSize(privacyDpSize)
                .setPrivacyTextMarginLeft(privacyTextMarginLeft)
                .setPrivacyMarginLeft(privacyMarginLeft)
                .setPrivacyMarginRight(privacyMarginRight)
                .setPrivacyState(privacyState)
                .setHidePrivacySmh(isHidePrivacySmh)
                .setHidePrivacyCheckBox(isHidePrivacyCheckBox)
                .setPrivacyTextGravityCenter(isPrivacyTextGravityCenter)
                .setCheckBoxGravity(checkBoxGravity)
                .setCheckedImageDrawable(getDrawable(checkedImageName, context))
                .setUnCheckedImageDrawable(getDrawable(unCheckedImageName, context))
                .setPrivacyCheckBoxWidth(checkBoxWith)
                .setPrivacyCheckBoxHeight(checkBoxHeight)
                .setPrivacyTextStart(privacyTextStart)
                .setProtocolConnect(protocolConnect)
                .setUserProtocolConnect(userProtocolConnect)
                .setOperatorPrivacyAtLast(operatorPrivacyAtLast)
                .setProtocolText(protocolText)
                .setProtocolLink(protocolLink)
                .setProtocol2Text(protocol2Text)
                .setProtocol2Link(protocol2Link)
                .setProtocol3Text(protocol3Text)
                .setProtocol3Link(protocol3Link)
                .setPrivacyTextEnd(privacyTextEnd)
                .setProtocolPageNavTitle(protocolNavTitle, protocolNavTitle, protocolNavTitle)
                .setProtocolPageNavTitleColor(Color.parseColor(protocolNavTitleColor))
                .setProtocolPageNavColor(Color.parseColor(protocolNavColor))
                .setProtocolPageNavHeight(protocolNavHeight)
                .setProtocolPageNavTitleSize(protocolNavTitleSize)
                .setProtocolPageNavTitleDpSize(protocolNavTitleDpSize)
                .setProtocolPageNavBackIconWidth(protocolNavBackIconWidth)
                .setProtocolPageNavBackIconHeight(protocolNavBackIconHeight)
                .setProtocolPageNavBackIconMargin(protocolNavBackIconMargin)
                .setProtocolNavTitleBold(isProtocolNavTitleBold)

                .setLandscape(isLandscape)
                .setDialogMode(isDialogMode, dialogWidth, dialogHeight, dialogX, dialogY, isBottomDialog)
                .setDialogHideOnTouchOutside(isDialogHideOnTouchOutside)
                .setProtocolDialogMode(isProtocolDialogMode)
                .setPrivacyDialogAuto(isPrivacyDialogAuto)
                .setPrivacyDialogTextSize(privacyDialogSize)
                .setLoadingVisible(isShowLoading)
                .setBackPressedAvailable(isBackPressedAvailable)
                .setVirtualButtonHidden(isVirtualButtonHidden)
                .setLoginListener(new LoginListener() {
                    @Override
                    public boolean onDisagreePrivacy(TextView privacyTv, Button btnLogin) {
                        return !isShowPrivacyDialog;
                    }
                })
                .setClickEventListener((viewType, code) -> dealWithClickEvent(viewType, code, callbackContext))
                .setActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
                    @Override
                    public void onCreate(Activity activity) {
                        Log.d(QuickLogin.TAG, "lifecycle onCreate回调------>" + activity.getLocalClassName());
                        final JSONObject callBackJson = new JSONObject();
                        try {
                            callBackJson.put(LIFECYCLE, "onCreate");
                            callbackContext.success(callBackJson, true);
                        } catch (JSONException e) {
                            Logger.e(e.getMessage());
                        }
                    }

                    @Override
                    public void onResume(Activity activity) {
                        Log.d(QuickLogin.TAG, "lifecycle onResume回调------>" + activity.getLocalClassName());
                        final JSONObject callBackJson = new JSONObject();
                        try {
                            callBackJson.put(LIFECYCLE, "onResume");
                            callbackContext.success(callBackJson, true);
                        } catch (JSONException e) {
                            Logger.e(e.getMessage());
                        }
                    }

                    @Override
                    public void onStart(Activity activity) {
                        Log.d(QuickLogin.TAG, "lifecycle onStart回调------>" + activity.getLocalClassName());
                        final JSONObject callBackJson = new JSONObject();
                        try {
                            callBackJson.put(LIFECYCLE, "onStart");
                            callbackContext.success(callBackJson, true);
                        } catch (JSONException e) {
                            Logger.e(e.getMessage());
                        }
                    }

                    @Override
                    public void onPause(Activity activity) {
                        Log.d(QuickLogin.TAG, "lifecycle onPause回调------>" + activity.getLocalClassName());
                        final JSONObject callBackJson = new JSONObject();
                        try {
                            callBackJson.put(LIFECYCLE, "onPause");
                            callbackContext.success(callBackJson, true);
                        } catch (JSONException e) {
                            Logger.e(e.getMessage());
                        }

                    }

                    @Override
                    public void onStop(Activity activity) {
                        Log.d(QuickLogin.TAG, "lifecycle onStop回调------>" + activity.getLocalClassName());
                        final JSONObject callBackJson = new JSONObject();
                        try {
                            callBackJson.put(LIFECYCLE, "onStop");
                            callbackContext.success(callBackJson, true);
                        } catch (JSONException e) {
                            Logger.e(e.getMessage());
                        }
                    }

                    @Override
                    public void onDestroy(Activity activity) {
                        Log.d(QuickLogin.TAG, "lifecycle onDestroy回调------>" + activity.getLocalClassName());
                        final JSONObject callBackJson = new JSONObject();
                        try {
                            callBackJson.put(LIFECYCLE, "onDestroy");
                            callbackContext.success(callBackJson, true);
                        } catch (JSONException e) {
                            Logger.e(e.getMessage());
                        }
                    }
                });
        try {
            if (!TextUtils.isEmpty(statusBarColor)) {
                builder.setStatusBarColor(Color.parseColor(statusBarColor));
            }
            buildUiConfigSplit(context, builder);
            buildUiPrivacy(context, builder);
            setCustomView(context, builder, widgets, callbackContext);
        } catch (JSONException e) {
            Logger.e(e.getMessage());
        }
        return builder.build(context);
    }

    /**
     * 处理授权页点击事件回调
     *
     * @param viewType        view类型
     * @param code            复选框状态
     * @param callbackContext KeepAliveCallbackContext
     */
    private void dealWithClickEvent(int viewType, int code, KeepAliveCallbackContext callbackContext) {
        try {
            JSONObject callBackJson = new JSONObject();
            if (viewType == UnifyUiConfig.CLICK_PRIVACY) {
                Log.d(QuickLogin.TAG, "点击了隐私协议");
                callBackJson.put(CLICK_VIEW_TYPE, "privacy");
                callbackContext.success(callBackJson, true);
            } else if (viewType == UnifyUiConfig.CLICK_CHECKBOX) {
                if (code == 0) {
                    Log.d(QuickLogin.TAG, "点击了复选框,且复选框未勾选");
                    callBackJson.put(CLICK_VIEW_TYPE, "checkbox");
                    callBackJson.put(CHECKBOX_CHECKED, false);
                    callbackContext.success(callBackJson, true);
                } else if (code == 1) {
                    Log.d(QuickLogin.TAG, "点击了复选框,且复选框已勾选");
                    callBackJson.put(CLICK_VIEW_TYPE, "checkbox");
                    callBackJson.put(CHECKBOX_CHECKED, true);
                    callbackContext.success(callBackJson, true);
                }

            } else if (viewType == UnifyUiConfig.CLICK_LOGIN_BUTTON) {
                if (code == 0) {
                    Log.d(QuickLogin.TAG, "点击了登录按钮,且复选框未勾选");
                    callBackJson.put(CLICK_VIEW_TYPE, "loginButton");
                    callBackJson.put(CHECKBOX_CHECKED, false);
                    callbackContext.success(callBackJson, true);
                } else if (code == 1) {
                    Log.d(QuickLogin.TAG, "点击了登录按钮,且复选框已勾选");
                    callBackJson.put(CLICK_VIEW_TYPE, "loginButton");
                    callBackJson.put(CHECKBOX_CHECKED, true);
                    callbackContext.success(callBackJson, true);
                }
            } else if (viewType == UnifyUiConfig.CLICK_TOP_LEFT_BACK_BUTTON) {
                Log.d(QuickLogin.TAG, "点击了左上角返回");
                callBackJson.put(CLICK_VIEW_TYPE, "leftBackButton");
                callbackContext.success(callBackJson, true);
            }
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
    }

    private void buildUiConfigSplit(Context context, UnifyUiConfig.Builder builder) {
        if (!TextUtils.isEmpty(navBackIcon)) {
            builder.setNavigationIconDrawable(getDrawable(navBackIcon, context));
        }
        if (logoTopYOffset != 0) {
            builder.setLogoTopYOffset(logoTopYOffset);
        }
        if (logoBottomYOffset != 0) {
            builder.setLogoBottomYOffset(logoBottomYOffset);
        }
        if (!TextUtils.isEmpty(logoIconName)) {
            builder.setLogoIconDrawable(getDrawable(logoIconName, context));
        }
        if (maskNumberTopYOffset != 0) {
            builder.setMaskNumberTopYOffset(maskNumberTopYOffset);
        }
        if (maskNumberBottomYOffset != 0) {
            builder.setMaskNumberBottomYOffset(maskNumberBottomYOffset);
        }
        if (sloganTopYOffset != 0) {
            builder.setSloganTopYOffset(sloganTopYOffset);
        }
        if (sloganBottomYOffset != 0) {
            builder.setSloganBottomYOffset(sloganBottomYOffset);
        }
        if (loginBtnTopYOffset != 0) {
            builder.setLoginBtnTopYOffset(loginBtnTopYOffset);
        }
        if (loginBtnBottomYOffset != 0) {
            builder.setLoginBtnBottomYOffset(loginBtnBottomYOffset);
        }
        if (loginBtnWidth != 0) {
            builder.setLoginBtnWidth(loginBtnWidth);
        }
        if (loginBtnHeight != 0) {
            builder.setLoginBtnHeight(loginBtnHeight);
        }
        if (loginBtnMarginLeft != 0) {
            builder.setLoginBtnMarginLeft(loginBtnMarginLeft);
        }
        if (loginBtnMarginRight != 0) {
            builder.setLoginBtnMarginRight(loginBtnMarginRight);
        }
        if (!TextUtils.isEmpty(loginBtnBackgroundRes)) {
            builder.setLoginBtnBackgroundDrawable(getDrawable(loginBtnBackgroundRes, context));
        }
    }

    private void buildUiPrivacy(Context context, UnifyUiConfig.Builder builder) {
        if (privacyTopYOffset != 0) {
            builder.setPrivacyTopYOffset(privacyTopYOffset);
        }
        if (privacyBottomYOffset != 0) {
            builder.setPrivacyBottomYOffset(privacyBottomYOffset);
        }
        if (privacyWidth != 0) {
            builder.setPrivacyWidth(privacyWidth);
        }
        if (privacyTextStartSize != 0) {
            builder.setPrivacyTextStartSize(privacyTextStartSize);
        }
        if (privacyLineSpacingAdd != 0 && privacyLineSpacingMul != 0) {
            builder.setPrivacyLineSpacing(privacyLineSpacingAdd, privacyLineSpacingMul);
        }
        if (!TextUtils.isEmpty(backgroundImage)) {
            builder.setBackgroundImageDrawable(getDrawable(backgroundImage, context));
        }
        if (!TextUtils.isEmpty(backgroundVideo)) {
            builder.setBackgroundVideo(backgroundVideo, backgroundVideoImage);
        }
        if (!TextUtils.isEmpty(privacyDialogText)) {
            builder.setPrivacyDialogText(privacyDialogText);
        }
        if (!TextUtils.isEmpty(backgroundGif)) {
            builder.setBackgroundGifDrawable(
                    getDrawable(
                            backgroundGif,
                            context
                    )
            );
        }
        if (!TextUtils.isEmpty(protocolNavBackIcon)) {
            builder.setProtocolPageNavBackIconDrawable(getDrawable(protocolNavBackIcon, context));
        }
        if (!TextUtils.isEmpty(enterAnimation) && !TextUtils.isEmpty(exitAnimation)) {
            builder.setActivityTranslateAnimation(enterAnimation, exitAnimation);
        }
    }

    /**
     * 设置自定义View
     *
     * @param context         Context
     * @param builder         UnifyUiConfig.Builder
     * @param jsonArray       JSONArray
     * @param callbackContext KeepAliveCallbackContext
     * @throws JSONException JSONException
     */
    private void setCustomView(Context context, UnifyUiConfig.Builder builder, JSONArray jsonArray, final KeepAliveCallbackContext callbackContext) throws JSONException {
        if (jsonArray == null) {
            return;
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            ViewParams viewParams = new ViewParams();
            viewParams.viewId = jsonObject.optString("viewId");
            viewParams.type = jsonObject.optString("type");
            if ("Button".equalsIgnoreCase(viewParams.type)) {
                viewParams.view = new Button(context);
            } else if ("TextView".equalsIgnoreCase(viewParams.type)) {
                viewParams.view = new TextView(context);
            } else if ("ImageView".equalsIgnoreCase(viewParams.type)) {
                viewParams.view = new ImageView(context);
            }
            viewParams.text = jsonObject.optString("text");
            viewParams.left = jsonObject.optInt("left");
            viewParams.top = jsonObject.optInt("top");
            viewParams.right = jsonObject.optInt("right");
            viewParams.bottom = jsonObject.optInt("bottom");
            viewParams.width = jsonObject.optInt("width");
            viewParams.height = jsonObject.optInt("height");
            viewParams.font = jsonObject.optInt("font");
            viewParams.textColor = jsonObject.optString("textColor");
            viewParams.clickable = jsonObject.optBoolean("clickable");
            viewParams.backgroundColor = jsonObject.optString("backgroundColor");
            viewParams.positionType = jsonObject.optInt("positionType");
            viewParams.backgroundImgPath = jsonObject.optString("backgroundImgPath");

            setCustomViewSplit(context, viewParams);
            builder.addCustomView(viewParams.view, viewParams.viewId, viewParams.positionType == 0 ? UnifyUiConfig.POSITION_IN_BODY : UnifyUiConfig.POSITION_IN_TITLE_BAR, (context1, activity, view) -> {
                Log.d(TAG, "CustomViewListener onClick:" + view.getTag());
                final JSONObject callBackJson = new JSONObject();
                try {
                    callBackJson.put("viewId", view.getTag());
                    callbackContext.success(callBackJson, true);
                } catch (JSONException e) {
                    Logger.e(e.getMessage());
                }
            });
        }
        Log.d(TAG, "set custom view finished");
    }

    private void setCustomViewSplit(Context context, ViewParams viewParams) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dip2px(context, viewParams.width), dip2px(context, viewParams.height));
        if (viewParams.left != 0) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.CENTER_VERTICAL);
            layoutParams.leftMargin = dip2px(context, viewParams.left);
        }
        if (viewParams.top != 0) {
            layoutParams.topMargin = dip2px(context, viewParams.top);
        }
        if (viewParams.right != 0) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.CENTER_VERTICAL);
            layoutParams.rightMargin = dip2px(context, viewParams.right);
        }
        if (viewParams.bottom != 0) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.CENTER_VERTICAL);
            layoutParams.bottomMargin = dip2px(context, viewParams.bottom);
        }
        viewParams.view.setLayoutParams(layoutParams);
        viewParams.view.setTag(viewParams.viewId);
        if (viewParams.view instanceof TextView) {
            ((TextView) viewParams.view).setText(viewParams.text);
            if (viewParams.font != 0) {
                ((TextView) viewParams.view).setTextSize(viewParams.font);
            }
            if (!TextUtils.isEmpty(viewParams.textColor)) {
                ((TextView) viewParams.view).setTextColor(Color.parseColor(viewParams.textColor));
            }
        }
        if (!viewParams.clickable) {
            viewParams.view.setClickable(false);
        }
        if (!TextUtils.isEmpty(viewParams.backgroundColor)) {
            viewParams.view.setBackgroundColor(Color.parseColor(viewParams.backgroundColor));
        }
        if (!TextUtils.isEmpty(viewParams.backgroundImgPath)) {
            viewParams.view.setBackground(getDrawable(viewParams.backgroundImgPath, context));
        }
    }

    public static class ViewParams {
        View view;
        String viewId;
        String type;
        String text;
        int left;
        int top;
        int right;
        int bottom;
        int width;
        int height;
        int font;
        String textColor;
        boolean clickable = true;
        String backgroundColor;
        String backgroundImgPath;
        int positionType;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static float px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return pxValue / scale + 0.5f;
    }

    private Drawable getDrawable(String resPath, Context context) {
        BitmapDrawable drawable = null;
        Bitmap bitmap;
        try {
            String assetsPath = "www/" + resPath;
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(assetsPath);
            bitmap = BitmapFactory.decodeStream(inputStream);
            drawable = new BitmapDrawable(context.getResources(), bitmap);
        } catch (Exception localException) {
            Logger.e(localException.getMessage());
        }
        return drawable;
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return (int) px2dip(context, outMetrics.widthPixels);
    }
}
