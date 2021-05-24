#import "Voiceit.h"
#import "VoiceItAPITwo.h"
#import "React/RCTUtils.h"


@implementation Voiceit

VoiceItAPITwo *myVoiceit;

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(initVoiceIt:(NSString *)apiKey tokenParameter:(NSString *)apiToken successCallback:(RCTResponseSenderBlock)successCallback)
{
    UIViewController *presentedViewController = RCTPresentedViewController();
    // get the root view of the app and then pass it
    myVoiceit = [[VoiceItAPITwo alloc] init: presentedViewController apiKey:apiKey apiToken:apiToken];
    successCallback(@[@"Initialized"]);
}

RCT_EXPORT_METHOD(setNotificationURL:(NSString *)notificationURL successCallback:(RCTResponseSenderBlock)successCallback)
{
    [myVoiceit setNotificationURL:notificationURL];
    successCallback(@[@"Notification URL set"]);
}

RCT_EXPORT_METHOD(encapsulatedFaceEnrollment:(NSString *)userId successCallback:(RCTResponseSenderBlock)successCallback
    failureCallback:(RCTResponseSenderBlock)failureCallback)
{
    dispatch_async(dispatch_get_main_queue(), ^(){
      [myVoiceit encapsulatedFaceEnrollUser: userId userEnrollmentsCancelled:^{
          failureCallback(@[@"Cancelled"]);
      } userEnrollmentsPassed:^(NSString * jsonResponse){
          successCallback(@[jsonResponse]);
      }];
    });

}

RCT_EXPORT_METHOD(encapsulatedVideoEnrollment:(NSString *)userId contentLanguage:(NSString*)contentLanguage
voicePrintPhrase:(NSString*)voicePrintPhrase successCallback:(RCTResponseSenderBlock)successCallback
    failureCallback:(RCTResponseSenderBlock)failureCallback)
{
      dispatch_async(dispatch_get_main_queue(), ^(){
    [myVoiceit encapsulatedVideoEnrollUser: userId contentLanguage: contentLanguage
    voicePrintPhrase: voicePrintPhrase userEnrollmentsCancelled:^{
        successCallback(@[@"Cancelled"]);
    } userEnrollmentsPassed:^(NSString * jsonResponse){
        failureCallback(@[jsonResponse]);
    }];
    });
}

RCT_EXPORT_METHOD(encapsulatedVoiceEnrollment:(NSString *)userId contentLanguage:(NSString*)contentLanguage
voicePrintPhrase:(NSString*)voicePrintPhrase successCallback:(RCTResponseSenderBlock)successCallback
    failureCallback:(RCTResponseSenderBlock)failureCallback)
{
        dispatch_async(dispatch_get_main_queue(), ^(){
    [myVoiceit encapsulatedVoiceEnrollUser: userId contentLanguage: contentLanguage
   voicePrintPhrase: voicePrintPhrase userEnrollmentsCancelled:^{
        successCallback(@[@"Cancelled"]);
    } userEnrollmentsPassed:^(NSString * jsonResponse){
        failureCallback(@[jsonResponse]);
    }];
        });
}

RCT_EXPORT_METHOD(encapsulatedFaceVerification:(NSString *)userId
    contentLanguage:(NSString *) contentLanguage
    doLivenessDetection:(BOOL)doLivenessDetection
    doAudioPrompts:(BOOL)doAudioPrompts
    successCallback:(RCTResponseSenderBlock)successCallback
    failureCallback:(RCTResponseSenderBlock)failureCallback
    cancelledCallback:(RCTResponseSenderBlock)cancelledCallback)
{
        dispatch_async(dispatch_get_main_queue(), ^(){
    [myVoiceit encapsulatedFaceVerification: userId doLivenessDetection: doLivenessDetection
                          doAudioPrompts: doAudioPrompts
                         contentLanguage: contentLanguage
    userVerificationCancelled:^{
        cancelledCallback(@[@"User Verication Cancelled"]);
    }  userVerificationSuccessful:^(float faceConfidence, NSString * jsonResponse){
        successCallback(@[jsonResponse]);
    } userVerificationFailed:^(float faceConfidence, NSString * jsonResponse){
        failureCallback(@[jsonResponse]);
    }];
    });
}

RCT_EXPORT_METHOD(encapsulatedVideoVerification:(NSString *)userId
  contentLanguage:(NSString*)contentLanguage
  voicePrintPhrase:(NSString*)voicePrintPhrase
doLivenessDetection:(BOOL)doLivenessDetection
 doAudioPrompts:(BOOL)doAudioPrompts
successCallback:(RCTResponseSenderBlock)successCallback
failureCallback:(RCTResponseSenderBlock)failureCallback
cancelledCallback:(RCTResponseSenderBlock)cancelledCallback)
{
        dispatch_async(dispatch_get_main_queue(), ^(){
    [myVoiceit encapsulatedVideoVerification: userId
    contentLanguage:contentLanguage
   voicePrintPhrase:voicePrintPhrase
   doLivenessDetection:doLivenessDetection
     doAudioPrompts:doAudioPrompts
    userVerificationCancelled:^{
        cancelledCallback(@[@"User Verication Cancelled"]);
    }userVerificationSuccessful:^(float faceConfidence, float voiceConfidence, NSString * jsonResponse){
                       successCallback(@[jsonResponse]);
    } userVerificationFailed:^(float faceConfidence, float voiceConfidence, NSString * jsonResponse){
                       failureCallback(@[jsonResponse]);
    }];
    });
}


RCT_EXPORT_METHOD(encapsulatedVoiceVerification:(NSString *)userId
contentLanguage:(NSString*)contentLanguage
voicePrintPhrase:(NSString*)voicePrintPhrase
successCallback:(RCTResponseSenderBlock)successCallback
failureCallback:(RCTResponseSenderBlock)failureCallback
cancelledCallback:(RCTResponseSenderBlock)cancelledCallback)
{
    dispatch_async(dispatch_get_main_queue(), ^(){
    [myVoiceit encapsulatedVoiceVerification: userId
    contentLanguage:contentLanguage
     voicePrintPhrase:voicePrintPhrase
    userVerificationCancelled:^{
        cancelledCallback(@[@"User Verication Cancelled"]);
    } userVerificationSuccessful:^(float voiceConfidence, NSString * jsonResponse){
        successCallback(@[jsonResponse]);
    } userVerificationFailed:^(float voiceConfidence, NSString * jsonResponse){
        failureCallback(@[jsonResponse]);
    }];
    });
}

RCT_EXPORT_METHOD(getAllUsers:(RCTResponseSenderBlock)successCallback) {
    [myVoiceit getAllUsers:^(NSString *jsonResponse) {
        successCallback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(createUser:(RCTResponseSenderBlock)successCallback) {
    [myVoiceit createUser:^(NSString *jsonResponse) {
        successCallback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(checkUserExists:(NSString *)userId successCallback:(RCTResponseSenderBlock)successCallback) {
    [myVoiceit checkUserExists:userId callback:^(NSString *jsonResponse) {
        successCallback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(getGroupsForUser:(NSString *)userId successCallback:(RCTResponseSenderBlock)successCallback) {
    [myVoiceit getGroupsForUser:userId callback:^(NSString *jsonResponse) {
        successCallback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(deleteUser:(NSString *) userId successCallback:(RCTResponseSenderBlock)successCallback) {
    [myVoiceit deleteUser:userId callback:^(NSString *jsonResponse) {
        successCallback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(getAllGroups:(RCTResponseSenderBlock)successCallback) {
    [myVoiceit getAllGroups:^(NSString *jsonResponse) {
        successCallback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(getGroup:(NSString *) groupId successCallback:(RCTResponseSenderBlock)successCallback) {
    [myVoiceit getGroup:groupId callback:^(NSString *jsonResponse) {
        successCallback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(groupExists:(NSString *) groupId successCallback:(RCTResponseSenderBlock)successCallback) {
    [myVoiceit groupExists:groupId callback:^(NSString *jsonResponse) {
        successCallback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(createGroup:(NSString *) groupDescription successCallback:(RCTResponseSenderBlock)successCallback) {
    [myVoiceit createGroup:^(NSString *jsonResponse) {
        successCallback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(addUserToGroup:(NSString *) groupId userId:(NSString *) userId successCallback:(RCTResponseSenderBlock)successCallback) {
    [myVoiceit addUserToGroup:groupId userId:userId callback:^(NSString *jsonResponse) {
        successCallback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(removeUserFromGroup:(NSString *) groupId userId:(NSString *) userId successCallback:(RCTResponseSenderBlock)successCallback) {
    [myVoiceit removeUserFromGroup:groupId userId:userId callback:^(NSString *jsonResponse) {
        successCallback(@[jsonResponse]);
    }];

}

RCT_EXPORT_METHOD(deleteGroup:(NSString *) groupId successCallback:(RCTResponseSenderBlock)successCallback) {
    [myVoiceit deleteGroup:groupId callback:^(NSString *jsonResponse) {
        successCallback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(getAllVoiceEnrollments:(NSString *) userId successCallback:(RCTResponseSenderBlock)successCallback) {
    [myVoiceit getAllVoiceEnrollments:userId callback:^(NSString *jsonResponse) {
        successCallback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(getAllFaceEnrollments:(NSString *) userId successCallback:(RCTResponseSenderBlock)successCallback) {
    [myVoiceit getAllFaceEnrollments:userId callback:^(NSString *jsonResponse) {
        successCallback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(getAllVideoEnrollments:(NSString *) userId successCallback:(RCTResponseSenderBlock)successCallback) {
    [myVoiceit getAllVideoEnrollments:userId callback:^(NSString *jsonResponse) {
        successCallback(@[jsonResponse]);
    }];
}


@end
