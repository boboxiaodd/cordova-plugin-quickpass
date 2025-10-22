package com.netease.nis.quicklogin.cordovaplugin;

import android.util.Log;

import com.netease.nis.quicklogin.QuickLogin;
import com.netease.nis.quicklogin.listener.QuickLoginPreMobileListener;
import com.netease.nis.quicklogin.listener.QuickLoginTokenListener;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hzhuqi on 2020/12/10
 */
public class QuickLoginPlugin extends CordovaPlugin {
    private static final String TAG = "QuickLoginPlugin";
    private KeepAliveCallbackContext callbackContext;
    private QuickLogin loginHelper;
    private JsonConfigParser parser;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        super.execute(action, args, callbackContext);
        this.callbackContext = KeepAliveCallbackContext.newInstance(callbackContext);
        boolean isSuccess = false;
        Log.d(TAG, "action:" + action + " args:" + args);
        if ("shouldQuickLogin".equals(action)) {
            final JSONObject callBackJson = new JSONObject();
            callBackJson.put("success", true);
            this.callbackContext.success(callBackJson, true);
            isSuccess = true;
        } else if ("init".equals(action)) {
            String businessId = args.getString(0);
            int timeOut = args.getInt(1);
            init(businessId, timeOut);
            isSuccess = true;
        } else if ("setUiConfig".equals(action)) {
            JSONObject uiConfig = new JSONObject(args.getString(0));
            setUiConfig(uiConfig);
            isSuccess = true;
        } else if ("preFetchNumber".equals(action)) {
            callMethodInThreadPool("preFetchNumber", null);
            isSuccess = true;
        } else if ("login".equals(action)) {
            callMethodInThreadPool("login", null);
            isSuccess = true;
        } else if ("closeAuthController".equals(action)) {
            closeAuthController();
            isSuccess = true;
        } else if ("numberVerify".equals(action)) {
            String mobileNumber = args.getString(0);
            callMethodInThreadPool("numberVerify", mobileNumber);
            isSuccess = true;
        } else {
            callbackContext.error("invalid action");
        }
        return isSuccess;
    }

    public void init(String businessId, int timeOut) throws JSONException {
        loginHelper = QuickLogin.getInstance();
        loginHelper.init(cordova.getContext(), businessId == null ? "" : businessId);
        loginHelper.setPrefetchNumberTimeout(timeOut);
        final JSONObject callBackJson = new JSONObject();
        callBackJson.put("success", true);
        callbackContext.success(callBackJson, true);
    }

    public void setUiConfig(JSONObject uiConfig) {
        if (parser == null) {
            parser = new JsonConfigParser();
        }
        loginHelper.setUnifyUiConfig(parser.getUiConfig(cordova.getContext(), uiConfig, callbackContext));
    }

    public void preFetchNumber() {
        final JSONObject callBackJson = new JSONObject();
        loginHelper.prefetchMobileNumber(new QuickLoginPreMobileListener() {
            @Override
            public void onGetMobileNumberSuccess(String ydToken, final String mobileNumber) {
                try {
                    callBackJson.put("token", ydToken);
                    callBackJson.put("mobileNumber", mobileNumber);
                    callBackJson.put("success", true);
                    callbackContext.success(callBackJson, true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onGetMobileNumberError(String ydToken, String msg) {
                try {
                    callBackJson.put("success", false);
                    callBackJson.put("token", ydToken);
                    callBackJson.put("msg", msg);
                    callbackContext.success(callBackJson, true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void login() {
        final JSONObject callBackJson = new JSONObject();
        loginHelper.onePass(new QuickLoginTokenListener() {
            @Override
            public void onGetTokenSuccess(String ydToken, String accessCode) {
                try {
                    callBackJson.put("success", true);
                    callBackJson.put("token", ydToken);
                    callBackJson.put("accessToken", accessCode);
                    callbackContext.success(callBackJson, true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onGetTokenError(String ydToken, int code, String msg) {

                try {
                    callBackJson.put("success", false);
                    callBackJson.put("token", ydToken);
                    callBackJson.put("msg", msg);
                    callbackContext.success(callBackJson, true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void closeAuthController() {
        loginHelper.quitActivity();
    }

    public void numberVerify(String mobileNumber) {
        final JSONObject callBackJson = new JSONObject();
        loginHelper.getToken(mobileNumber, new QuickLoginTokenListener() {
            @Override
            public void onGetTokenSuccess(final String ydToken, final String accessCode) {
                try {
                    callBackJson.put("success", true);
                    callBackJson.put("token", ydToken);
                    callBackJson.put("accessCode", accessCode);
                    callbackContext.success(callBackJson, true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onGetTokenError(String ydToken, int code, String msg) {
                try {
                    callBackJson.put("success", false);
                    callBackJson.put("token", ydToken);
                    callBackJson.put("errorMsg", msg);
                    callbackContext.success(callBackJson, true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void callMethodInThreadPool(String methodName, String args) {
        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                switch (methodName) {
                    case "preFetchNumber":
                        preFetchNumber();
                        break;
                    case "login":
                        login();
                        break;
                    case "numberVerify":
                        numberVerify(args);
                        break;
                }
            }
        });

    }
}
