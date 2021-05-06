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
    public void initVoiceIt(String apiKey, String apiToken, final Callback callback){
        myVoiceIt = new VoiceItAPI2(apiKey,apiToken);
        callback.invoke("Initialized");
    }

    @ReactMethod
    public void setNotificationURL(String notificationURL, final Callback callback){
        myVoiceIt.setNotificationURL(notificationURL);
        callback.invoke("Notification URL set!");
    }

    //Encapsulated Methods
    @ReactMethod
    public void encapsulatedVoiceEnrollment(String userId, String contentLanguage, String phrase, final Callback callback){
        myVoiceIt.encapsulatedVoiceEnrollment(getCurrentActivity(), userId, contentLanguage, phrase, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void encapsulatedVoiceVerification(String userId, String contentLanguage, String phrase, final Callback callback){
        myVoiceIt.encapsulatedVoiceVerification(getCurrentActivity(), userId, contentLanguage, phrase, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void encapsulatedVoiceIdentification(String groupId, String contentLanguage, String phrase, final Callback callback){
        myVoiceIt.encapsulatedVoiceIdentification(getCurrentActivity(), groupId, contentLanguage, phrase, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse){
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void encapsulatedFaceEnrollment(String userId,final Callback callback){
        myVoiceIt.encapsulatedFaceEnrollment(getCurrentActivity(), userId, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void encapsulatedFaceVerification(String userId, String contentLanguage, Boolean liveness, Boolean audioLiveness, final Callback callback){
        myVoiceIt.encapsulatedFaceVerification(getCurrentActivity(), userId, contentLanguage, liveness, audioLiveness,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void encapsulatedVideoVerificationWithoutLivenessTutorial(String userId, String contentLanguage, String phrase, Boolean liveness, Boolean audioLiveness, final Callback callback){
      myVoiceIt.encapsulatedVideoVerification(getCurrentActivity(),userId,contentLanguage,phrase,liveness, audioLiveness, false, new JsonHttpResponseHandler(){
          @Override
          public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            callback.invoke(response.toString());
          }
          @Override
          public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
              if (errorResponse != null) {
                callback.invoke(errorResponse.toString());
            }
        }
      });
    }

    @ReactMethod
    public void encapsulatedVideoEnrollment(String userId, String contentLanguage, String phrase, final Callback callback){
        myVoiceIt.encapsulatedVideoEnrollment(getCurrentActivity(), userId, contentLanguage, phrase,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void encapsulatedVideoVerification(String userId, String contentLanguage, String phrase, Boolean liveness, Boolean audioLiveness, final Callback callback){
        myVoiceIt.encapsulatedVideoVerification(getCurrentActivity(),userId,contentLanguage,phrase,liveness, audioLiveness, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void encapsulatedFaceVerificationWithoutLivenessTutorial(String userId, String contentLanguage, Boolean liveness, Boolean audioLiveness, final Callback callback){
      myVoiceIt.encapsulatedFaceVerification(getCurrentActivity(), userId, contentLanguage, liveness, audioLiveness, false, new JsonHttpResponseHandler(){
          @Override
          public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
              callback.invoke(response.toString());
            }
          @Override
          public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
              if (errorResponse != null) {
                callback.invoke(errorResponse.toString());
            }
          }
        });
      }

    //User Management Methods
    @ReactMethod
    public void getAllUsers(final Callback callback){
        myVoiceIt.getAllUsers(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void createUser(final Callback callback){
        myVoiceIt.createUser(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void checkUserExists(String userId, final Callback callback){
        myVoiceIt.checkUserExists(userId, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void deleteuser(String userId, final Callback callback){
        myVoiceIt.deleteUser(userId, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void getGroupsForUser(String userId, final Callback callback){
        myVoiceIt.getGroupsForUser(userId, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    //Group Management Methods
    @ReactMethod
    public void getAllGroups(final Callback callback){
        myVoiceIt.getAllGroups(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void getGroup (String groupId , final Callback callback){
        myVoiceIt.getGroup(groupId, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void groupExists(String groupId, final Callback callback){
        myVoiceIt.groupExists(groupId, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void createGroup(String groupDescription, final Callback callback){
        myVoiceIt.createGroup(groupDescription, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void addUserToGroup(String groupId, String userId, final Callback callback){
        myVoiceIt.addUserToGroup(groupId, userId, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void removeUserFromGroup(String groupId, String userId, final Callback callback){
        myVoiceIt.removeUserFromGroup(groupId, userId, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void deleteGroup(String groupId, final Callback callback){
        myVoiceIt.deleteGroup(groupId, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    //Enrollment API Methods
    @ReactMethod
    public void getAllVoiceEnrollments(String userId, final Callback callback){
        myVoiceIt.getAllVoiceEnrollments(userId, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void getAllVideoEnrollments(String userId, final Callback callback){
        myVoiceIt.getAllVideoEnrollments(userId, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void getAllFaceEnrollments(String userId, final Callback callback){
        myVoiceIt.getAllFaceEnrollments(userId, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void deleteAllEnrollments(String userId, final Callback callback){
        myVoiceIt.deleteAllEnrollments(userId,  new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void createVoiceEnrollment(String userId, String contentLanguage, String phrase, String path, final Callback callback){
        File video = new File(path);
        if (!video.exists()){
            callback.invoke("File Doesn't exist: " + path);
        } else {
            myVoiceIt.createVoiceEnrollment(userId, contentLanguage, phrase,video, new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    callback.invoke(response.toString());
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    if (errorResponse != null) {
                        callback.invoke(errorResponse.toString());
                    }
                }
            });
        }
    }

    @ReactMethod
    public void createVoiceEnrollmentByUrl(String userId, String contentLanguage, String phrase, String url, final Callback callback){
        myVoiceIt.createVoiceEnrollment(userId, contentLanguage, phrase, url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void createFaceEnrollment(String userId, String path, final Callback callback){
        File video = new File(path);
        if (!video.exists()){
            callback.invoke("File Doesn't exist: " + path);
        } else {
            myVoiceIt.createFaceEnrollment(userId, video, new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    callback.invoke(response.toString());
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    if (errorResponse != null) {
                        callback.invoke(errorResponse.toString());
                    }
                }
            });
        }
    }

    @ReactMethod
    public void createFaceEnrollmentByUrl(String userId, String url, final Callback callback){
        myVoiceIt.createFaceEnrollmentByUrl(userId, url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void createVideoEnrollment(String userId, String contentLanguage, String phrase, String path, final Callback callback){
        File video = new File(path);
        if (!video.exists()){
            callback.invoke("File Doesn't exist: " + path);
        } else {
            myVoiceIt.createVideoEnrollment(userId, contentLanguage, phrase,video, new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    callback.invoke(response.toString());
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    if (errorResponse != null) {
                        callback.invoke(errorResponse.toString());
                    }
                }
            });
        }
    }

    @ReactMethod
    public void createVideoEnrollmentByUrl(String userId, String contentLanguage, String phrase, String url, final Callback callback){
        myVoiceIt.createVideoEnrollmentByUrl(userId, contentLanguage, phrase,url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    //Verification API Calls
    @ReactMethod
    public void voiceVerification(String userId, String contentLanguage, String phrase, String path, final Callback callback){
        File video = new File(path);
        if (!video.exists()){
            callback.invoke("File Doesn't exist: " + path);
        } else {
            myVoiceIt.voiceVerification(userId, contentLanguage, phrase,video, new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    callback.invoke(response.toString());
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    if (errorResponse != null) {
                        callback.invoke(errorResponse.toString());
                    }
                }
            });
        }
    }

    @ReactMethod
    public void voiceVerificationByUrl(String userId, String contentLanguage, String phrase, String url, final Callback callback){
        myVoiceIt.voiceVerificationByUrl(userId, contentLanguage, phrase, url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void faceVerification(String userId, String path, final Callback callback){
        File video = new File(path);
        if (!video.exists()){
            callback.invoke("File Doesn't exist: " + path);
        } else {
            myVoiceIt.faceVerification(userId, video, new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    callback.invoke(response.toString());
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    if (errorResponse != null) {
                        callback.invoke(errorResponse.toString());
                    }
                }
            });
        }
    }

    @ReactMethod
    public void faceVerificationByUrl(String userId, String url, final Callback callback){
        myVoiceIt.faceVerificationByUrl(userId, url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    @ReactMethod
    public void videoVerification(String userId, String contentLanguage, String phrase, String path, final Callback callback){
        File video = new File(path);
        if (!video.exists()){
            callback.invoke("File Doesn't exist: " + path);
        } else {
            myVoiceIt.videoVerification(userId, contentLanguage, phrase,video, new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    callback.invoke(response.toString());
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    if (errorResponse != null) {
                        callback.invoke(errorResponse.toString());
                    }
                }
            });
        }
    }

    @ReactMethod
    public void videoVerificationByUrl(String userId, String contentLanguage, String phrase, String url, final Callback callback){
        myVoiceIt.videoVerificationByUrl(userId, contentLanguage, phrase,url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }

    //Identification API Calls
    @ReactMethod
    public void voiceIdentification(String groupId, String contentLanguage, String phrase, String path, final Callback callback){
        File video = new File(path);
        if (!video.exists()){
            callback.invoke("File Doesn't exist: " + path);
        } else {
            myVoiceIt.voiceIdentification(groupId, contentLanguage, phrase,video, new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    callback.invoke(response.toString());
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    if (errorResponse != null) {
                        callback.invoke(errorResponse.toString());
                    }
                }
            });
        }
    }

    @ReactMethod
    public void voiceIdentificationByUrl(String groupId, String contentLanguage, String phrase, String url, final Callback callback){
        myVoiceIt.voiceIdentificationByUrl(groupId, contentLanguage, phrase, url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.invoke(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.invoke(errorResponse.toString());
                }
            }
        });
    }


}
