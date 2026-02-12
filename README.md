# react-native-voiceit

VoiceIt's React Native SDK v2.0 — a hybrid architecture with a **pure JS HTTP client** for CRUD operations and **native modules** for encapsulated biometric capture UI (camera/mic).

* [Getting Started](#getting-started)
* [Requirements](#requirements)
* [Installation](#installation)
* [Usage](#usage)
  * [JS API Client (VoiceItAPI)](#js-api-client)
  * [Native Biometric UI (VoiceItNative)](#native-biometric-ui)
* [API Reference](#api-reference)
* [Theme Color](#theme-color)

## Getting Started

```
npm install react-native-voiceit --save
```

## Requirements

### iOS
- Minimum Deployment Target: iOS 11
- Camera and Microphone permissions in `Info.plist`:
```xml
<key>NSCameraUsageDescription</key>
<string>This app requires access to your camera for biometric services</string>
<key>NSMicrophoneUsageDescription</key>
<string>This app requires access to your microphone for biometric services</string>
<key>UIUserInterfaceStyle</key>
<string>Light</string>
```

### Android
- Minimum SDK version 17 in `build.gradle`:
```
minSdkVersion: 17
```

### Billing
In order to use en-US or other content languages you need a developer account with funds. Add funds at: https://voiceit.io/billing

### Voiceprint Phrases
Review your Voiceprint Phrases at: https://voiceit.io/phraseManagement

## Installation

```
npm install react-native-voiceit --save
cd ios && pod install
```

## Usage

```javascript
import VoiceItAPI, { VoiceItNative } from 'react-native-voiceit';
```

### JS API Client

Use `VoiceItAPI` for all CRUD operations (users, groups, enrollments, verification, identification) via direct HTTP calls:

```javascript
const api = new VoiceItAPI('API_KEY', 'AUTH_TOKEN');

// Users
const users = await api.getAllUsers();
const newUser = await api.createUser();
const exists = await api.checkUserExists('usr_...');
await api.deleteUser('usr_...');
const groups = await api.getGroupsForUser('usr_...');
const token = await api.createUserToken('usr_...', '3600');
await api.expireUserTokens('usr_...');

// Groups
const allGroups = await api.getAllGroups();
const group = await api.getGroup('grp_...');
const newGroup = await api.createGroup('My Group');
await api.deleteGroup('grp_...');
await api.addUserToGroup('grp_...', 'usr_...');
await api.removeUserFromGroup('grp_...', 'usr_...');

// Enrollments (list & delete)
const voiceEnrollments = await api.getAllVoiceEnrollments('usr_...');
const faceEnrollments = await api.getAllFaceEnrollments('usr_...');
const videoEnrollments = await api.getAllVideoEnrollments('usr_...');
await api.deleteVoiceEnrollment(enrollmentId);
await api.deleteFaceEnrollment(enrollmentId);
await api.deleteVideoEnrollment(enrollmentId);

// Programmatic enrollment (with file)
await api.createVoiceEnrollment('usr_...', 'en-US', 'my phrase', audioFile);
await api.createFaceEnrollment('usr_...', { photo: photoFile });
await api.createVideoEnrollment('usr_...', 'en-US', 'my phrase', videoFile);

// Enrollment by URL
await api.createVoiceEnrollmentByUrl('usr_...', 'en-US', 'my phrase', 'https://...');
await api.createFaceEnrollmentByUrl('usr_...', 'https://...');
await api.createVideoEnrollmentByUrl('usr_...', 'en-US', 'my phrase', 'https://...');

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

// Notification URL
api.setNotificationUrl('https://your-webhook.com/callback');
```

### Liveness Options

Face and video biometric methods support liveness detection via options:

```javascript
await api.createFaceEnrollment('usr_...', {
  photo: photoFile,
  liveness: true,
  livenessThreshold: 90,
});

await api.faceVerification('usr_...', {
  photo: photoFile,
  liveness: true,
  livenessThreshold: 90,
});
```

### Native Biometric UI

Use `VoiceItNative` for encapsulated biometric capture with built-in camera/mic UI. You must initialize the native module first:

```javascript
// Initialize
VoiceItNative.initVoiceIt('API_KEY', 'AUTH_TOKEN', (res) => {
  console.log(res);
});

// Voice enrollment (with built-in recording UI)
VoiceItNative.encapsulatedVoiceEnrollment('usr_...', 'en-US', 'my phrase',
  (res) => console.log('Success:', res),
  (err) => console.log('Failed:', err)
);

// Face verification (with built-in camera UI)
VoiceItNative.encapsulatedFaceVerification('usr_...', 'en-US',
  (res) => console.log('Success:', res),
  (err) => console.log('Failed:', err)
);

// Video verification (with built-in camera + mic UI)
VoiceItNative.encapsulatedVideoVerification('usr_...', 'en-US', 'my phrase',
  (res) => console.log('Success:', res),
  (err) => console.log('Failed:', err)
);
```

#### Available native methods:
- `initVoiceIt(apiKey, apiToken, callback)`
- `initVoiceItWithTheme(apiKey, apiToken, hexColor, callback)`
- `setNotificationURL(url, callback)`
- `encapsulatedVoiceEnrollment(userId, contentLanguage, phrase, successCb, failureCb)`
- `encapsulatedFaceEnrollment(userId, contentLanguage, successCb, failureCb)`
- `encapsulatedVideoEnrollment(userId, contentLanguage, phrase, successCb, failureCb)`
- `encapsulatedVoiceVerification(userId, contentLanguage, phrase, successCb, failureCb)`
- `encapsulatedFaceVerification(userId, contentLanguage, successCb, failureCb)`
- `encapsulatedVideoVerification(userId, contentLanguage, phrase, successCb, failureCb)`
- `encapsulatedVoiceIdentification(groupId, contentLanguage, phrase, successCb, failureCb)`

## Theme Color

To set a theme color for the native biometric UI:

```javascript
VoiceItNative.initVoiceItWithTheme('API_KEY', 'AUTH_TOKEN', '#fbc132', (res) => {
  console.log(res);
});
```
