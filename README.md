# react-native-voiceit

## Getting started
Firstly, make sure that the deployment target for your root project's IOS is >= 11, and for Android the SDK version is >= 17

`$ npm install react-native-voiceit --save`

### Mostly automatic installation

`$ react-native link react-native-voiceit`

## Usage
```javascript
import {NativeModules} from 'react-native';
const Voiceit = NativeModules.Voiceit;

//  Initialize the VoiceIt Native Module
//  Voiceit.init("API_KEY","API_TOKEN",(res)=>{
//  console.log(res);
//  } ;

//  Call the methods with the same signature as the IOS/Android SDK. For instance
//  Voiceit.encapsulatedFaceEnrollment("USR_ID",(res)=>{
//  console.log(res);
//  });
```

## Requirements

#### IOS Deployment Target (IOS Version)
Please make sure that the minimum Deployment Target for your project is IOS 11

#### Android SDK Version
The minumum Android SDK version (API level) should be set to 17 in your build.gradle file: 
```
minSdkVersion: 17
```

#### Billing 
In order to use en-US or other content languages you need to have a developer account that has funds. In order to add funds to your account please login at: https://voiceit.io/login and navigate to: https://voiceit.io/billing and add funds.

#### Voiceprint Phrases 
Make sure you review your Voiceprint Phrases by navigating to: https://voiceit.io/phraseManagement in order to know what to pass for voicePrintPhrase parameter

## Plist Permissions 
For IOS, please make sure your project has Camera and Microphone permissions in the info.plist file. Also, make sure that the app runs in light theme. Add the following inside the <dict> XML:
```
<key>NSCameraUsageDescription</key>
<string>This app requires to access your camera for biometric services</string>
<key>NSMicrophoneUsageDescription</key>
<string>This app requires to access your microphone for biometric services</string>
<key>UIUserInterfaceStyle</key>
<string>Light</string>
```
  
## (For Android) Liveness Tutorial 
The liveness tutorial can be disabled by passing a boolean as the 6th argument in EncapsulatedVideoVerification:
```
voiceItModule.encapsulatedVideoVerification(
      options.user_id,options.content_language, 
      options.phrase, 
      this.props.liveness,
      this.props.audioLiveness, 
      this.props.livenessTutorial, 
      (res)=>{callback(res);});
```
And as the 5th argument for encapsulatedFaceVerification:
```
voiceItModule.encapsulatedFaceVerification(
      options.user_id,options.content_language, 
      this.props.liveness,
      this.props.audioLiveness, 
      this.props.livenessTutorial,
      (res)=>{callback(res);});
```

## Functions
Initialize the module as follows:
```
import {NativeModules} from 'react-native';
const Voiceit = NativeModules.Voiceit;
Voiceit.init("API_KEY","API_TOKEN",(res)=>{
console.log(res);
};
```
The React Native SDK wraps the IOS and Android SDKs, and hence the methods exposed are the same as those.
Please refer to the [The Android SDK](https://github.com/voiceittech/VoiceIt2-AndroidSDK)/[The IOS SDK](https://github.com/voiceittech/VoiceIt2-IosSDK) for complete method reference. For instance

```
voiceItModule.encapsulatedFaceVerification("USER_ID","CONTENT_LANGUAGE", LIVENESS_BOOLEAN, AUDIO_LIVENESS_BOOLEAN, (res)=>{
callback(res);
});
```
