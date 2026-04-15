import React, {Component} from 'react';
import {
  TouchableOpacity,
  StyleSheet,
  Text,
  View,
  SafeAreaView,
  StatusBar,
  Alert,
  TextInput,
  ScrollView,
} from 'react-native';
import {VoiceItNative} from '@voiceittech/voiceit3-react-native';

const voiceItModule = VoiceItNative;

// SECURITY WARNING: Never ship an app with your API Key and API Token
// hardcoded or embedded in client-side code. These are long-lived server
// credentials — anyone who extracts them from the app binary gains full
// access to your VoiceIt account.
//
// In production, your mobile app should call YOUR backend server, which
// holds the credentials securely and proxies requests to the VoiceIt API
// (or creates short-lived user tokens via the createUserToken endpoint).
//
// This demo uses text inputs for convenience only. Replace this pattern
// with a backend-proxy architecture before releasing to users.

export default class App extends Component {
  constructor() {
    super();
    this.state = {
      apiKey: '',
      apiToken: '',
      userId: '',
      phrase: 'Never forget tomorrow is a new day',
      contentLanguage: 'en-US',
    };
  }

  initSDK() {
    const {apiKey, apiToken, userId} = this.state;
    if (!apiKey || !apiToken) {
      Alert.alert('Result', 'Please enter your API Key and API Token');
      return false;
    }
    if (!userId) {
      Alert.alert('Result', 'Please enter a User ID');
      return false;
    }
    voiceItModule.initVoiceIt(apiKey, apiToken, () => {});
    return true;
  }

  doEnrollment(type) {
    if (!this.initSDK()) return;
    const {userId, contentLanguage, phrase} = this.state;
    const cb = (res) => Alert.alert('Result', JSON.stringify(res));

    if (type === 'voice') {
      voiceItModule.encapsulatedVoiceEnrollment(userId, contentLanguage, phrase, cb, cb);
    } else if (type === 'face') {
      voiceItModule.encapsulatedFaceEnrollment(userId, contentLanguage, cb, cb);
    } else {
      voiceItModule.encapsulatedVideoEnrollment(userId, contentLanguage, phrase, cb, cb);
    }
  }

  doVerification(type) {
    if (!this.initSDK()) return;
    const {userId, contentLanguage, phrase} = this.state;
    const cb = (res) => Alert.alert('Result', JSON.stringify(res));

    if (type === 'voice') {
      voiceItModule.encapsulatedVoiceVerification(userId, contentLanguage, phrase, cb, cb);
    } else if (type === 'face') {
      voiceItModule.encapsulatedFaceVerification(userId, contentLanguage, cb, cb);
    } else {
      voiceItModule.encapsulatedVideoVerification(userId, contentLanguage, phrase, cb, cb);
    }
  }

  render() {
    const languages = ['en-US', 'es-ES', 'no-STT'];

    return (
      <SafeAreaView style={styles.container}>
        <StatusBar barStyle="light-content" backgroundColor="#212B36" />
        <ScrollView
          style={styles.scroll}
          contentContainerStyle={styles.scrollContent}
          keyboardShouldPersistTaps="handled">

          {/* Header */}
          <Text style={styles.lockIcon}>{'\u{1F512}'}</Text>
          <Text style={styles.title}>VoiceIt API 3.0</Text>
          <Text style={styles.subtitle}>Biometric Verification Demo</Text>

          {/* Credentials */}
          <View style={styles.inputRow}>
            <Text style={styles.inputIcon}>ℹ️</Text>
            <TextInput
              style={styles.input}
              placeholder="API Key"
              placeholderTextColor="#919EAB"
              value={this.state.apiKey}
              onChangeText={(t) => this.setState({apiKey: t})}
              autoCapitalize="none"
              autoCorrect={false}
            />
          </View>

          <View style={styles.inputRow}>
            <Text style={styles.inputIcon}>🔒</Text>
            <TextInput
              style={styles.input}
              placeholder="API Token"
              placeholderTextColor="#919EAB"
              value={this.state.apiToken}
              onChangeText={(t) => this.setState({apiToken: t})}
              secureTextEntry
              autoCapitalize="none"
              autoCorrect={false}
            />
          </View>

          <View style={styles.inputRow}>
            <Text style={styles.inputIcon}>👤</Text>
            <TextInput
              style={styles.input}
              placeholder="User ID"
              placeholderTextColor="#919EAB"
              value={this.state.userId}
              onChangeText={(t) => this.setState({userId: t})}
              autoCapitalize="none"
              autoCorrect={false}
            />
          </View>

          {/* Phrase */}
          <Text style={styles.fieldLabel}>Phrase</Text>
          <TextInput
            style={styles.phraseInput}
            value={this.state.phrase}
            onChangeText={(t) => this.setState({phrase: t})}
            autoCapitalize="none"
            autoCorrect={false}
          />

          {/* Language Picker */}
          <View style={styles.languageRow}>
            {languages.map((lang) => (
              <TouchableOpacity
                key={lang}
                style={[
                  styles.langButton,
                  this.state.contentLanguage === lang && styles.langButtonSelected,
                ]}
                onPress={() => this.setState({contentLanguage: lang})}>
                <Text
                  style={[
                    styles.langText,
                    this.state.contentLanguage === lang && styles.langTextSelected,
                  ]}>
                  {lang}
                </Text>
              </TouchableOpacity>
            ))}
          </View>

          {/* Divider */}
          <View style={styles.divider} />

          {/* Enrollment */}
          <Text style={styles.sectionHeader}>Enrollment</Text>

          <TouchableOpacity
            style={styles.enrollButton}
            activeOpacity={0.8}
            onPress={() => this.doEnrollment('voice')}>
            <Text style={styles.buttonText}>Voice Enrollment</Text>
          </TouchableOpacity>

          <TouchableOpacity
            style={styles.enrollButton}
            activeOpacity={0.8}
            onPress={() => this.doEnrollment('face')}>
            <Text style={styles.buttonText}>Face Enrollment</Text>
          </TouchableOpacity>

          <TouchableOpacity
            style={styles.enrollButton}
            activeOpacity={0.8}
            onPress={() => this.doEnrollment('video')}>
            <Text style={styles.buttonText}>Video Enrollment</Text>
          </TouchableOpacity>

          {/* Verification */}
          <Text style={[styles.sectionHeader, {marginTop: 4}]}>Verification</Text>

          <TouchableOpacity
            style={styles.verifyButton}
            activeOpacity={0.8}
            onPress={() => this.doVerification('voice')}>
            <Text style={styles.buttonText}>Voice Verification</Text>
          </TouchableOpacity>

          <TouchableOpacity
            style={styles.verifyButton}
            activeOpacity={0.8}
            onPress={() => this.doVerification('face')}>
            <Text style={styles.buttonText}>Face Verification</Text>
          </TouchableOpacity>

          <TouchableOpacity
            style={styles.verifyButton}
            activeOpacity={0.8}
            onPress={() => this.doVerification('video')}>
            <Text style={styles.buttonText}>Video Verification</Text>
          </TouchableOpacity>

        </ScrollView>
      </SafeAreaView>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#212B36',
  },
  scroll: {
    flex: 1,
  },
  scrollContent: {
    paddingHorizontal: 24,
    paddingTop: 20,
    paddingBottom: 24,
  },
  lockIcon: {
    fontSize: 40,
    textAlign: 'center',
    marginBottom: 8,
  },
  title: {
    color: '#FFFFFF',
    fontSize: 28,
    fontWeight: '700',
    textAlign: 'center',
  },
  subtitle: {
    color: '#919EAB',
    fontSize: 15,
    fontWeight: '500',
    textAlign: 'center',
    marginTop: 4,
    marginBottom: 24,
  },
  inputRow: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: '#38424F',
    borderRadius: 8,
    borderWidth: 1,
    borderColor: 'rgba(251, 193, 50, 0.3)',
    paddingHorizontal: 12,
    marginBottom: 12,
    height: 52,
  },
  inputIcon: {
    fontSize: 18,
    marginRight: 10,
    width: 24,
    textAlign: 'center',
  },
  input: {
    flex: 1,
    color: '#FFFFFF',
    fontSize: 14,
  },
  fieldLabel: {
    color: '#919EAB',
    fontSize: 12,
    marginBottom: 4,
  },
  phraseInput: {
    backgroundColor: '#38424F',
    borderRadius: 8,
    borderWidth: 1,
    borderColor: 'rgba(251, 193, 50, 0.3)',
    paddingHorizontal: 12,
    height: 52,
    color: '#FFFFFF',
    fontSize: 14,
    marginBottom: 12,
  },
  languageRow: {
    flexDirection: 'row',
    marginBottom: 16,
  },
  langButton: {
    flex: 1,
    paddingVertical: 12,
    alignItems: 'center',
    backgroundColor: '#38424F',
    marginHorizontal: 4,
    borderRadius: 8,
  },
  langButtonSelected: {
    backgroundColor: '#FBC132',
  },
  langText: {
    color: '#919EAB',
    fontSize: 14,
    fontWeight: '500',
  },
  langTextSelected: {
    color: '#000000',
    fontWeight: '700',
  },
  divider: {
    height: 1,
    backgroundColor: '#38424F',
    marginBottom: 16,
  },
  sectionHeader: {
    color: '#919EAB',
    fontSize: 13,
    fontWeight: '600',
    marginBottom: 12,
  },
  enrollButton: {
    borderRadius: 12,
    height: 52,
    alignItems: 'center',
    justifyContent: 'center',
    marginBottom: 16,
    backgroundColor: '#505050',
  },
  verifyButton: {
    borderRadius: 12,
    height: 52,
    alignItems: 'center',
    justifyContent: 'center',
    marginBottom: 16,
    backgroundColor: '#FBC132',
  },
  buttonText: {
    color: '#FFFFFF',
    fontSize: 15,
    fontWeight: '600',
  },
});
