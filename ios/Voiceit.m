#import "Voiceit.h"
#import "VoiceItAPITwo.h"
#import "React/RCTUtils.h"


@implementation Voiceit

VoiceItAPITwo *myVoiceit;

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(initVoiceIt:(NSString *)apiKey tokenParameter:(NSString *)apiToken callback:(RCTResponseSenderBlock)callback)
{
    UIViewController *presentedViewController = RCTPresentedViewController();
    // get the root view of the app and then pass it
    myVoiceit = [[VoiceItAPITwo alloc] init: presentedViewController apiKey:apiKey apiToken:apiToken];
    callback(@[@"Initialized"]);
}

RCT_EXPORT_METHOD(encapsulatedFaceEnrollment:(NSString *)userId callback:(RCTResponseSenderBlock)callback)
{
    dispatch_async(dispatch_get_main_queue(), ^(){
      [myVoiceit encapsulatedFaceEnrollUser: userId userEnrollmentsCancelled:^{
          callback(@[@"Cancelled"]);
      } userEnrollmentsPassed:^(NSString * jsonResponse){
          callback(@[jsonResponse]);
      }];
    });

}

RCT_EXPORT_METHOD(encapsulatedVideoEnrollment:(NSString *)userId contentLanguage:(NSString*)contentLanguage
voicePrintPhrase:(NSString*)voicePrintPhrase callback:(RCTResponseSenderBlock)callback)
{
      dispatch_async(dispatch_get_main_queue(), ^(){
    [myVoiceit encapsulatedVideoEnrollUser: userId contentLanguage: contentLanguage
    voicePrintPhrase: voicePrintPhrase userEnrollmentsCancelled:^{
        callback(@[@"Cancelled"]);
    } userEnrollmentsPassed:^(NSString * jsonResponse){
        callback(@[jsonResponse]);
    }];
    });
}

RCT_EXPORT_METHOD(encapsulatedVoiceEnrollment:(NSString *)userId contentLanguage:(NSString*)contentLanguage
voicePrintPhrase:(NSString*)voicePrintPhrase callback:(RCTResponseSenderBlock)callback)
{
        dispatch_async(dispatch_get_main_queue(), ^(){
    [myVoiceit encapsulatedVoiceEnrollUser: userId contentLanguage: contentLanguage
   voicePrintPhrase: voicePrintPhrase userEnrollmentsCancelled:^{
        callback(@[@"Cancelled"]);
    } userEnrollmentsPassed:^(NSString * jsonResponse){
        callback(@[jsonResponse]);
    }];
        });
}

RCT_EXPORT_METHOD(encapsulatedFaceVerification:(NSString *)userId
                          contentLanguage:(NSString *) contentLanguage
                          doLivenessDetection:(BOOL)doLivenessDetection
                          doAudioPrompts:(BOOL)doAudioPrompts
                          callback:(RCTResponseSenderBlock)callback)
{
        dispatch_async(dispatch_get_main_queue(), ^(){
    [myVoiceit encapsulatedFaceVerification: userId doLivenessDetection: doLivenessDetection
                          doAudioPrompts: doAudioPrompts
                         contentLanguage: contentLanguage
    userVerificationCancelled:^{
        callback(@[@"User Verication Cancelled"]);
    }  userVerificationSuccessful:^(float faceConfidence, NSString * jsonResponse){
        callback(@[jsonResponse]);
    } userVerificationFailed:^(float faceConfidence, NSString * jsonResponse){
        callback(@[jsonResponse]);
    }];
    });
}

RCT_EXPORT_METHOD(encapsulatedVideoVerification:(NSString *)userId
  contentLanguage:(NSString*)contentLanguage
  voicePrintPhrase:(NSString*)voicePrintPhrase
doLivenessDetection:(BOOL)doLivenessDetection
 doAudioPrompts:(BOOL)doAudioPrompts
 callback:(RCTResponseSenderBlock)callback)
{
        dispatch_async(dispatch_get_main_queue(), ^(){
    [myVoiceit encapsulatedVideoVerification: userId
    contentLanguage:contentLanguage
   voicePrintPhrase:voicePrintPhrase
   doLivenessDetection:doLivenessDetection
     doAudioPrompts:doAudioPrompts
                   userVerificationCancelled:^{
                       callback(@[@"User Verication Cancelled"]);
                   }  userVerificationSuccessful:^(float faceConfidence, float voiceConfidence, NSString * jsonResponse){
                       callback(@[jsonResponse]);
                   } userVerificationFailed:^(float faceConfidence, float voiceConfidence, NSString * jsonResponse){
                       callback(@[jsonResponse]);
                   }];
                       });
}


RCT_EXPORT_METHOD(encapsulatedVoiceVerification:(NSString *)userId
contentLanguage:(NSString*)contentLanguage
voicePrintPhrase:(NSString*)voicePrintPhrase
callback:(RCTResponseSenderBlock)callback)
{
    dispatch_async(dispatch_get_main_queue(), ^(){
    [myVoiceit encapsulatedVoiceVerification: userId
    contentLanguage:contentLanguage
     voicePrintPhrase:voicePrintPhrase
    userVerificationCancelled:^{
        callback(@[@"User Verication Cancelled"]);
    } userVerificationSuccessful:^(float voiceConfidence, NSString * jsonResponse){
        callback(@[jsonResponse]);
    } userVerificationFailed:^(float voiceConfidence, NSString * jsonResponse){
                callback(@[jsonResponse]);
    }];
    });
}

RCT_EXPORT_METHOD(getAllUsers:(RCTResponseSenderBlock)callback) {
    [myVoiceit getAllUsers:^(NSString *jsonResponse) {
        callback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(createUser:(RCTResponseSenderBlock)callback) {
    [myVoiceit createUser:^(NSString *jsonResponse) {
        callback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(checkUserExists:(NSString *)userId callback:(RCTResponseSenderBlock)callback) {
    [myVoiceit checkUserExists:userId callback:^(NSString *jsonResponse) {
        callback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(getGroupsForUser:(NSString *)userId callback:(RCTResponseSenderBlock)callback) {
    [myVoiceit getGroupsForUser:userId callback:^(NSString *jsonResponse) {
        callback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(deleteUser:(NSString *) userId callback:(RCTResponseSenderBlock)callback) {
    [myVoiceit deleteUser:userId callback:^(NSString *jsonResponse) {
        callback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(getAllGroups:(RCTResponseSenderBlock)callback) {
    [myVoiceit getAllGroups:^(NSString *jsonResponse) {
        callback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(getGroup:(NSString *) groupId callback:(RCTResponseSenderBlock)callback) {
    [myVoiceit getGroup:groupId callback:^(NSString *jsonResponse) {
        callback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(groupExists:(NSString *) groupId callback:(RCTResponseSenderBlock)callback) {
    [myVoiceit groupExists:groupId callback:^(NSString *jsonResponse) {
        callback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(createGroup:(NSString *) groupDescription callback:(RCTResponseSenderBlock)callback) {
    [myVoiceit createGroup:^(NSString *jsonResponse) {
        callback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(addUserToGroup:(NSString *) groupId userId:(NSString *) userId callback:(RCTResponseSenderBlock)callback) {
    [myVoiceit addUserToGroup:groupId userId:userId callback:^(NSString *jsonResponse) {
        callback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(removeUserFromGroup:(NSString *) groupId userId:(NSString *) userId callback:(RCTResponseSenderBlock)callback) {
    [myVoiceit removeUserFromGroup:groupId userId:userId callback:^(NSString *jsonResponse) {
        callback(@[jsonResponse]);
    }];

}

RCT_EXPORT_METHOD(deleteGroup:(NSString *) groupId callback:(RCTResponseSenderBlock)callback) {
    [myVoiceit deleteGroup:groupId callback:^(NSString *jsonResponse) {
        callback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(getAllVoiceEnrollments:(NSString *) userId callback:(RCTResponseSenderBlock)callback) {
    [myVoiceit getAllVoiceEnrollments:userId callback:^(NSString *jsonResponse) {
        callback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(getAllFaceEnrollments:(NSString *) userId callback:(RCTResponseSenderBlock)callback) {
    [myVoiceit getAllFaceEnrollments:userId callback:^(NSString *jsonResponse) {
        callback(@[jsonResponse]);
    }];
}

RCT_EXPORT_METHOD(getAllVideoEnrollments:(NSString *) userId callback:(RCTResponseSenderBlock)callback) {
    [myVoiceit getAllVideoEnrollments:userId callback:^(NSString *jsonResponse) {
        callback(@[jsonResponse]);
    }];
}


@end
