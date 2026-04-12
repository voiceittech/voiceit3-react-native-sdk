<img src="https://raw.githubusercontent.com/voiceittech/voiceit3-react-native-sdk/main/react-native.png" width="100%" style="width:100%" />

[![Build](https://github.com/voiceittech/voiceit3-react-native-sdk/actions/workflows/test.yml/badge.svg)](https://github.com/voiceittech/voiceit3-react-native-sdk/actions/workflows/test.yml)
[![Dependabot](https://img.shields.io/github/issues-pr/voiceittech/voiceit3-react-native-sdk/dependencies?label=dependabot&logo=dependabot&color=025e8c)](https://github.com/voiceittech/voiceit3-react-native-sdk/pulls?q=is%3Apr+label%3Adependencies)
[![Version](https://img.shields.io/badge/version-3.1.0-blue)](https://github.com/voiceittech/voiceit3-react-native-sdk)
[![License: MIT](https://img.shields.io/badge/license-MIT-green)](https://github.com/voiceittech/voiceit3-react-native-sdk/blob/main/LICENSE)
[![Platform](https://img.shields.io/badge/platform-iOS%20%7C%20Android-lightgrey)](https://github.com/voiceittech/voiceit3-react-native-sdk)
[![VoiceIt API](https://img.shields.io/badge/VoiceIt-API%203.0-blue)](https://voiceit.io)

A fully comprehensive React Native SDK that gives you access to VoiceIt's API 3.0 featuring Voice + Face Verification and Identification with built-in native biometric capture UI.

* [UI Screenshots](#ui-screenshots)
* [Getting Started](#getting-started)
* [Requirements](#requirements)
* [Installation](#installation)
* [API Calls](#api-calls)
  * [Initialization](#initialization)
  * [Encapsulated Methods](#encapsulated-methods)
      * [Encapsulated Voice Enrollment](#encapsulated-voice-enrollment)
      * [Encapsulated Face Enrollment](#encapsulated-face-enrollment)
      * [Encapsulated Video Enrollment](#encapsulated-video-enrollment)
      * [Encapsulated Voice Verification](#encapsulated-voice-verification)
      * [Encapsulated Face Verification](#encapsulated-face-verification)
      * [Encapsulated Video Verification](#encapsulated-video-verification)
  * [JS API Client](#js-api-client)
* [Theme Color](#theme-color)

## UI Screenshots

The SDK includes a built-in example app in `example/` with credential input, language selection, and all enrollment/verification features.

The native biometric capture UI is provided by the underlying [iOS SDK](https://github.com/voiceittech/voiceit3-iossdk) and [Android SDK](https://github.com/voiceittech/voiceit3-androidsdk):

<div>
  <img width="250px" src="https://raw.githubusercontent.com/voiceittech/voiceit3-androidsdk/main/Graphics/demo_top.png?v=5" style="display: inline-block !important;"/><img width="250px" src="https://raw.githubusercontent.com/voiceittech/voiceit3-androidsdk/main/Graphics/demo_bottom.png?v=5" style="display: inline-block !important;"/><img width="250px" src="https://raw.githubusercontent.com/voiceittech/voiceit3-androidsdk/main/Graphics/voiceEnrollment.png?v=5" style="display: inline-block !important;"/><img width="250px" src="https://raw.githubusercontent.com/voiceittech/voiceit3-androidsdk/main/Graphics/faceEnrollment.png?v=5" style="display: inline-block !important;"/><img width="250px" src="https://raw.githubusercontent.com/voiceittech/voiceit3-androidsdk/main/Graphics/videoEnrollment.png?v=5" style="display: inline-block !important;"/>
</div>

## Getting Started

Sign up at [voiceit.io/pricing](https://voiceit.io/pricing) to get your API Key and Token, then log in to the [Dashboard](https://dashboard.voiceit.io) to manage your account.

<img src="https://raw.githubusercontent.com/voiceittech/voiceit3-react-native-sdk/main/Graphics/getcredentials.png" alt="API Key and Token" width="400px" />

## Requirements

### iOS
- Minimum Deployment Target: iOS 15.1
- Camera and Microphone permissions in `Info.plist`:
```xml
<key>NSCameraUsageDescription</key>
<string>This app requires access to your camera for biometric services</string>
<key>NSMicrophoneUsageDescription</key>
<string>This app requires access to your microphone for biometric services</string>
```

### Android
- Minimum SDK version 24 in `build.gradle`:
```
minSdkVersion: 24
```

### Voiceprint Phrases
Review your Voiceprint Phrases at: https://dashboard.voiceit.io/phraseManagement

## Installation

This package is hosted on [GitHub Packages](https://github.com/orgs/voiceittech/packages). Add the registry to your `.npmrc`:

```
@voiceittech:registry=https://npm.pkg.github.com
```

Then install:

```
npm install @voiceittech/voiceit3-react-native
cd ios && pod install
```

## API Calls

```javascript
import VoiceItAPI, { VoiceItNative } from '@voiceittech/voiceit3-react-native';
```

### Initialization

Initialize the native module before using encapsulated methods:

```javascript
VoiceItNative.initVoiceIt('API_KEY', 'AUTH_TOKEN', (res) => {
  console.log(res);
});
```

### Encapsulated Methods

These methods launch built-in native UI for biometric capture (camera/microphone).

#### Encapsulated Voice Enrollment

```javascript
VoiceItNative.encapsulatedVoiceEnrollment('usr_...', 'en-US', 'Never forget tomorrow is a new day',
  (res) => console.log('Success:', res),
  (err) => console.log('Failed:', err)
);
```

#### Encapsulated Face Enrollment

```javascript
VoiceItNative.encapsulatedFaceEnrollment('usr_...', 'en-US',
  (res) => console.log('Success:', res),
  (err) => console.log('Failed:', err)
);
```

#### Encapsulated Video Enrollment

```javascript
VoiceItNative.encapsulatedVideoEnrollment('usr_...', 'en-US', 'Never forget tomorrow is a new day',
  (res) => console.log('Success:', res),
  (err) => console.log('Failed:', err)
);
```

#### Encapsulated Voice Verification

```javascript
VoiceItNative.encapsulatedVoiceVerification('usr_...', 'en-US', 'Never forget tomorrow is a new day',
  (res) => console.log('Success:', res),
  (err) => console.log('Failed:', err)
);
```

#### Encapsulated Face Verification

```javascript
VoiceItNative.encapsulatedFaceVerification('usr_...', 'en-US',
  (res) => console.log('Success:', res),
  (err) => console.log('Failed:', err)
);
```

#### Encapsulated Video Verification

```javascript
VoiceItNative.encapsulatedVideoVerification('usr_...', 'en-US', 'Never forget tomorrow is a new day',
  (res) => console.log('Success:', res),
  (err) => console.log('Failed:', err)
);
```

### JS API Client

Use `VoiceItAPI` for all CRUD operations via direct HTTP calls:

```javascript
const api = new VoiceItAPI('API_KEY', 'AUTH_TOKEN');

// Users
const users = await api.getAllUsers();
const newUser = await api.createUser();
const exists = await api.checkUserExists('usr_...');
await api.deleteUser('usr_...');
const groups = await api.getGroupsForUser('usr_...');

// Groups
const allGroups = await api.getAllGroups();
const group = await api.getGroup('grp_...');
const newGroup = await api.createGroup('My Group');
await api.deleteGroup('grp_...');
await api.addUserToGroup('grp_...', 'usr_...');
await api.removeUserFromGroup('grp_...', 'usr_...');

// Enrollments
const voiceEnrollments = await api.getAllVoiceEnrollments('usr_...');
const faceEnrollments = await api.getAllFaceEnrollments('usr_...');
const videoEnrollments = await api.getAllVideoEnrollments('usr_...');
await api.deleteAllEnrollments('usr_...');

// Verification
await api.voiceVerification('usr_...', 'en-US', 'my phrase', audioFile);
await api.faceVerification('usr_...', { photo: photoFile });
await api.videoVerification('usr_...', 'en-US', 'my phrase', videoFile);

// Identification
await api.voiceIdentification('grp_...', 'en-US', 'my phrase', audioFile);
await api.faceIdentification('grp_...', { photo: photoFile });
await api.videoIdentification('grp_...', 'en-US', 'my phrase', videoFile);

// Phrases
const phrases = await api.getPhrases('en-US');
```

## Theme Color

To set a custom theme color for the native biometric UI:

```javascript
VoiceItNative.initVoiceItWithTheme('API_KEY', 'AUTH_TOKEN', '#FBC132', (res) => {
  console.log(res);
});
```

## License

voiceit3-react-native-sdk is available under the MIT license. See the LICENSE file for more info.
