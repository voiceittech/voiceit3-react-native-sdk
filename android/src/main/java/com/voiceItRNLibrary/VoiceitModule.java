package com.voiceItRNLibrary;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.voiceit.voiceit2.VoiceItAPI2;
import org.json.JSONObject;
import java.io.File;
import cz.msebera.android.httpclient.Header;
import android.graphics.Color;


public class VoiceitModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private VoiceItAPI2 myVoiceIt;

    public VoiceitModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "Voiceit";
    }

    @ReactMethod
    public void initVoiceIt(String apiKey, String apiToken, String baseUrl, final Callback successCallback){
        myVoiceIt = new VoiceItAPI2(apiKey, apiToken, baseUrl);
        successCallback.invoke("Initialized");
    }

    @ReactMethod
    public void initVoiceIt(String apiKey, String apiToken, String host, final Callback successCallback){
        myVoiceIt = new VoiceItAPI2(apiKey, apiToken, host);
        successCallback.invoke("Initialized");
    }

    @ReactMethod
    public void initVoiceItWithTheme(String apiKey, String apiToken, String themeColor, final Callback successCallback){
        myVoiceIt = new VoiceItAPI2(apiKey,apiToken, Color.parseColor(themeColor));
        successCallback.invoke("Initialized");
    }

    @ReactMethod
    public void initVoiceItWithTheme(String apiKey, String apiToken, String themeColor, String host, final Callback successCallback){
        myVoiceIt = new VoiceItAPI2(apiKey, apiToken, Color.parseColor(themeColor), host);
		myVoiceIt.setURL(baseUrl);
        successCallback.invoke("Initialized");
    }

    @ReactMethod
    public void setNotificationURL(String notificationURL, final Callback successCallback){
        myVoiceIt.setNotificationURL(notificationURL);
        successCallback.invoke("Notification URL set!");
    }

    //Encapsulated Methods
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
        myVoiceIt.encapsulatedVideoEnrollment(getCurrentActivity(), userId, contentLanguage, phrase,new JsonHttpResponseHandler(){
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
        myVoiceIt.encapsulatedVideoVerification(getCurrentActivity(),userId,contentLanguage,phrase, new JsonHttpResponseHandler(){
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

    //User Management Methods
    @ReactMethod
    public void getAllUsers(final Callback successCallback, final Callback failureCallback){
        myVoiceIt.getAllUsers(new JsonHttpResponseHandler() {
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
    public void createUser(final Callback successCallback, final Callback failureCallback){
        myVoiceIt.createUser(new JsonHttpResponseHandler(){
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
    public void checkUserExists(String userId, final Callback successCallback, final Callback failureCallback){
        myVoiceIt.checkUserExists(userId, new JsonHttpResponseHandler(){
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
    public void deleteuser(String userId, final Callback successCallback, final Callback failureCallback){
        myVoiceIt.deleteUser(userId, new JsonHttpResponseHandler(){
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
    public void getGroupsForUser(String userId, final Callback successCallback, final Callback failureCallback){
        myVoiceIt.getGroupsForUser(userId, new JsonHttpResponseHandler(){
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

    //Group Management Methods
    @ReactMethod
    public void getAllGroups(final Callback successCallback, final Callback failureCallback){
        myVoiceIt.getAllGroups(new JsonHttpResponseHandler(){
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
    public void getGroup (String groupId , final Callback successCallback, final Callback failureCallback){
        myVoiceIt.getGroup(groupId, new JsonHttpResponseHandler(){
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
    public void groupExists(String groupId, final Callback successCallback, final Callback failureCallback){
        myVoiceIt.groupExists(groupId, new JsonHttpResponseHandler(){
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
    public void createGroup(String groupDescription, final Callback successCallback, final Callback failureCallback){
        myVoiceIt.createGroup(groupDescription, new JsonHttpResponseHandler(){
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
    public void addUserToGroup(String groupId, String userId, final Callback successCallback, final Callback failureCallback){
        myVoiceIt.addUserToGroup(groupId, userId, new JsonHttpResponseHandler(){
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
    public void removeUserFromGroup(String groupId, String userId, final Callback successCallback, final Callback failureCallback){
        myVoiceIt.removeUserFromGroup(groupId, userId, new JsonHttpResponseHandler(){
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
    public void deleteGroup(String groupId, final Callback successCallback, final Callback failureCallback){
        myVoiceIt.deleteGroup(groupId, new JsonHttpResponseHandler(){
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

    //Enrollment API Methods
    @ReactMethod
    public void getAllVoiceEnrollments(String userId, final Callback successCallback, final Callback failureCallback){
        myVoiceIt.getAllVoiceEnrollments(userId, new JsonHttpResponseHandler(){
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
    public void getAllVideoEnrollments(String userId, final Callback successCallback, final Callback failureCallback){
        myVoiceIt.getAllVideoEnrollments(userId, new JsonHttpResponseHandler(){
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
    public void getAllFaceEnrollments(String userId, final Callback successCallback, final Callback failureCallback){
        myVoiceIt.getAllFaceEnrollments(userId, new JsonHttpResponseHandler(){
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
    public void deleteAllEnrollments(String userId, final Callback successCallback, final Callback failureCallback){
        myVoiceIt.deleteAllEnrollments(userId,  new JsonHttpResponseHandler(){
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
