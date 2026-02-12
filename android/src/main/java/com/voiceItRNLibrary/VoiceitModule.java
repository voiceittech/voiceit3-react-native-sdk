package com.voiceItRNLibrary;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.voiceit.voiceit3.VoiceItAPI3;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;
import android.graphics.Color;


public class VoiceitModule extends ReactContextBaseJavaModule {

    private static final String BASE_URL = "https://api.voiceit.io";
    private final ReactApplicationContext reactContext;
    private VoiceItAPI3 myVoiceIt;

    public VoiceitModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "Voiceit";
    }

    @ReactMethod
    public void initVoiceIt(String apiKey, String apiToken, final Callback successCallback){
        myVoiceIt = new VoiceItAPI3(apiKey, apiToken, BASE_URL);
        successCallback.invoke("Initialized");
    }

    @ReactMethod
    public void initVoiceItWithTheme(String apiKey, String apiToken, String themeColor, final Callback successCallback){
        myVoiceIt = new VoiceItAPI3(apiKey, apiToken, Color.parseColor(themeColor), BASE_URL);
        successCallback.invoke("Initialized");
    }

    @ReactMethod
    public void setNotificationURL(String notificationURL, final Callback successCallback){
        myVoiceIt.setNotificationURL(notificationURL);
        successCallback.invoke("Notification URL set!");
    }

    // Encapsulated Methods

    @ReactMethod
    public void encapsulatedVoiceEnrollment(String userId, String contentLanguage, String phrase, final Callback successCallback, final Callback failureCallback){
        myVoiceIt.encapsulatedVoiceEnrollment(getCurrentActivity(), userId, contentLanguage, phrase, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                successCallback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    failureCallback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void encapsulatedVoiceVerification(String userId, String contentLanguage, String phrase, final Callback successCallback, final Callback failureCallback){
        myVoiceIt.encapsulatedVoiceVerification(getCurrentActivity(), userId, contentLanguage, phrase, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                successCallback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    failureCallback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void encapsulatedVoiceIdentification(String groupId, String contentLanguage, String phrase, final Callback successCallback, final Callback failureCallback){
        myVoiceIt.encapsulatedVoiceIdentification(getCurrentActivity(), groupId, contentLanguage, phrase, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                successCallback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse){
                if (errorResponse != null) {
                    failureCallback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void encapsulatedFaceEnrollment(String userId, String contentLanguage, final Callback successCallback, final Callback failureCallback){
        myVoiceIt.encapsulatedFaceEnrollment(getCurrentActivity(), userId, contentLanguage, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                successCallback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    failureCallback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void encapsulatedFaceVerification(String userId, String contentLanguage, final Callback successCallback, final Callback failureCallback){
        myVoiceIt.encapsulatedFaceVerification(getCurrentActivity(), userId, contentLanguage, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                successCallback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    failureCallback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void encapsulatedVideoEnrollment(String userId, String contentLanguage, String phrase, final Callback successCallback, final Callback failureCallback){
        myVoiceIt.encapsulatedVideoEnrollment(getCurrentActivity(), userId, contentLanguage, phrase, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                successCallback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    failureCallback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void encapsulatedVideoVerification(String userId, String contentLanguage, String phrase, final Callback successCallback, final Callback failureCallback){
        myVoiceIt.encapsulatedVideoVerification(getCurrentActivity(), userId, contentLanguage, phrase, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                successCallback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    failureCallback.invoke(errorResponse.toString());
                }
            }
        });
    }
}
