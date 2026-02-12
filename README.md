# react-native-voiceit

* [Getting Started](#getting-started)
* [Billing](#billing)
* [Voiceprint Phrases](#voiceprint-phrases)
* [Plist Permissions](#plist-permissions)
* [Local Installation ](#local-installation)
  * [Android](#android)
  * [IOS](#ios)
* [Usage](#usage)
* [Funtions](#functions)
* [User Token Initializing](#user-token)
* [Theme Color](#theme)

## Getting started
`$ npm install react-native-voiceit --save`

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

#### Plist Permissions 
For IOS, please make sure your project has Camera and Microphone permissions in the info.plist file. Also, make sure that the app runs in light theme. Add the following inside the <dict> XML:
```
<key>NSCameraUsageDescription</key>
<string>This app requires to access your camera for biometric services</string>
<key>NSMicrophoneUsageDescription</key>
<string>This app requires to access your microphone for biometric services</string>
<key>UIUserInterfaceStyle</key>
<string>Light</string>
```

### Mostly automatic installation

`$ react-native link react-native-voiceit`

## Local Installation 
Firstly, to use the SDK locally, please clone the repo and drag it into your root React Native project. In the Package.json, replace to "react-native-voiceit": "file:./VoiceIt3-React-Native-SDK"

#### Android 
Get the <a href="https://github.com/voiceittech/VoiceIt3-AndroidSDK#local-installation"> Android-SDK</a> locally by doing the following: 

<ul>
 <li> Clone the Android-SDK repo
 <li> Open your main android project/folder in android studio, and navigate to File -> New -> Import Module
 <li> Select the Android SDK repo that you just cloned. Check off the app module, only include the voiceit2 module
 <li> Navigate to you local VoiceIt3-React-Native-SDK Folder
 <li> In the android/build.gradle, add 
  ```
  implementation project(path: ':voiceit2')
  ```
  under dependencies 
</ul>

#### IOS

Please navigate to the podfile of your main IOS folder, and replace to:
```
  pod 'react-native-voiceit', :path => '../VoiceIt3-React-Native-SDK'
  pod "VoiceIt3-IosSDK", :path => './VoiceIt3-IosSDK'
```



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

#### Functions
Initialize the module as follows:
```
import {NativeModules} from 'react-native';
const Voiceit = NativeModules.Voiceit;
Voiceit.initVoiceIt("API_KEY","API_TOKEN",(res)=>{
console.log(res);
};
```
The React Native SDK wraps the IOS and Android SDKs, and hence the methods exposed are the same as those.
Please refer to the [The Android SDK](https://github.com/voiceittech/VoiceIt3-AndroidSDK)/[The IOS SDK](https://github.com/voiceittech/VoiceIt3-IosSDK) for complete method reference. For instance

```
voiceItModule.encapsulatedFaceVerification("USER_ID","CONTENT_LANGUAGE", LIVENESS_BOOLEAN, AUDIO_LIVENESS_BOOLEAN, (res)=>{
callback(res);
});
```
 
#### User Token 
 To initialize Voiceit Module with User token and not use your API key and token, first generate a user token: https://api.voiceit.io/#user-token-generation
 Initialize Voiceit by placing the generated user token in palce of the API Key, and leave the API token as blank: 
```
Voiceit.initVoiceIt("GENERATED_USER_TOKEN","",(res)=>{
console.log(res);
};
```
 
 #### Theme
 To set a theme color, please initialize the voiceit Object as follows:
 ```
Voiceit.initVoiceItWithTheme("API_KEY","API_TOKEN", "HEX_COLOR_VALUE",(res)=>{
console.log(res);
};
 ```
 
 Example: 
 ```
Voiceit.initVoiceItWithTheme("key_...","tok_.....", "#fbc132",(res)=>{
console.log(res);
};
 ```

