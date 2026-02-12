#import "Voiceit.h"
#import "VoiceItAPIThree.h"
#import "React/RCTUtils.h"


@implementation Voiceit

VoiceItAPIThree *myVoiceit;

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(initVoiceIt:(NSString *)apiKey tokenParameter:(NSString *)apiToken successCallback:(RCTResponseSenderBlock)successCallback)
{
    UIViewController *presentedViewController = RCTPresentedViewController();
    myVoiceit = [[VoiceItAPIThree alloc] init: presentedViewController apiKey:apiKey apiToken:apiToken];
    successCallback(@[@"Initialized"]);
}

RCT_EXPORT_METHOD(initVoiceItWithTheme:(NSString *)apiKey tokenParameter:(NSString *)apiToken
    themeColorParameter:(NSString *) themeColor successCallback:(RCTResponseSenderBlock)successCallback)
{
    UIViewController *presentedViewController = RCTPresentedViewController();
    NSMutableDictionary * styles = [[NSMutableDictionary alloc] init];
    [styles setObject:themeColor forKey:@"kThemeColor"];
    [styles setObject:@"default" forKey:@"kIconStyle"];

    myVoiceit = [[VoiceItAPIThree alloc] init: presentedViewController apiKey:apiKey apiToken:apiToken styles: styles];
    successCallback(@[@"Initialized"]);
}

RCT_EXPORT_METHOD(setNotificationURL:(NSString *)notificationURL successCallback:(RCTResponseSenderBlock)successCallback)
{
    [myVoiceit setNotificationURL:notificationURL];
    successCallback(@[@"Notification URL set"]);
}

RCT_EXPORT_METHOD(encapsulatedFaceEnrollment:(NSString *)userId contentLanguage:(NSString*)contentLanguage successCallback:(RCTResponseSenderBlock)successCallback
    failureCallback:(RCTResponseSenderBlock)failureCallback)
{
    dispatch_async(dispatch_get_main_queue(), ^(){
      [myVoiceit encapsulatedFaceEnrollUser: userId contentLanguage: contentLanguage userEnrollmentsCancelled:^{
          failureCallback(@[@"\"message\": \"User Enrollment Cancelled\""]);
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
        failureCallback(@[@"\"message\": \"User Enrollment Cancelled\""]);
    } userEnrollmentsPassed:^(NSString * jsonResponse){
        successCallback(@[jsonResponse]);
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
        failureCallback(@[@"\"message\": \"User Enrollment Cancelled\""]);
    } userEnrollmentsPassed:^(NSString * jsonResponse){
        successCallback(@[jsonResponse]);
    }];
        });
}

RCT_EXPORT_METHOD(encapsulatedFaceVerification:(NSString *)userId
    contentLanguage:(NSString *) contentLanguage
    successCallback:(RCTResponseSenderBlock)successCallback
    failureCallback:(RCTResponseSenderBlock)failureCallback)
{
        dispatch_async(dispatch_get_main_queue(), ^(){
    [myVoiceit encapsulatedFaceVerification: userId
                         contentLanguage: contentLanguage
    userVerificationCancelled:^{
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
successCallback:(RCTResponseSenderBlock)successCallback
failureCallback:(RCTResponseSenderBlock)failureCallback)
{
        dispatch_async(dispatch_get_main_queue(), ^(){
    [myVoiceit encapsulatedVideoVerification: userId
    contentLanguage:contentLanguage
   voicePrintPhrase:voicePrintPhrase
    userVerificationCancelled:^{
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
failureCallback:(RCTResponseSenderBlock)failureCallback)
{
    dispatch_async(dispatch_get_main_queue(), ^(){
    [myVoiceit encapsulatedVoiceVerification: userId
    contentLanguage:contentLanguage
     voicePrintPhrase:voicePrintPhrase
    userVerificationCancelled:^{
    } userVerificationSuccessful:^(float voiceConfidence, NSString * jsonResponse){
        successCallback(@[jsonResponse]);
    } userVerificationFailed:^(float voiceConfidence, NSString * jsonResponse){
        failureCallback(@[jsonResponse]);
    }];
    });
}

@end
