# react-native-voiceit

## Getting started

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
