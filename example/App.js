import React, {Component} from 'react';
import {TouchableOpacity, StyleSheet, Image, Text, View, SafeAreaView, StatusBar} from 'react-native';
import {VoiceItNative} from '@voiceittech/voiceit3-react-native';
import logo from "./res/logo.png";

const voiceItModule = VoiceItNative;
const options = {
  user_id: "USER_ID_HERE",
  group_id: "GROUP_ID_HERE",
  content_language: "CONTENT_LANGUAGE_HERE",
  phrase: "PHRASE",
  apiKey: "API_KEY_HERE",
  apiToken: "API_TOKEN_HERE"
  };

export default class App extends Component{
  constructor () {
    super();
    this.state = {
      index: 0
    }
  }
  componentDidMount() {
    voiceItModule.initVoiceIt(options.apiKey, options.apiToken, (response)=>{
      //
    });
  }
  render() {
    const tabs = ['Voice', 'Face', 'Video'];
    return(
      <SafeAreaView style={styles.container}>
      <StatusBar barStyle="light-content" backgroundColor="#212B36" />
      <Image resizeMode='contain' style={styles.image} source={logo}/>
      <Text style={styles.title}>VoiceIt API 3.0</Text>
      <Text style={styles.subtitle}>Biometric Verification Demo</Text>
      <View style={styles.buttonPanel}>
      {tabs.map((tab, i) => (
        <TouchableOpacity
          key={tab}
          activeOpacity={.8}
          style={[styles.tabButton, this.state.index == i && styles.tabButtonSelected]}
          onPress={()=>{this.setState({ index: i });}}>
         <Text style={[styles.tabText, this.state.index == i && styles.tabTextSelected]}>{tab}</Text>
        </TouchableOpacity>
      ))}
      </View>
      <View style={styles.action}>
      <Action index={this.state.index}></Action>
      </View>
      </SafeAreaView>
    );
  }
}

 class Action extends Component {
  enrollVoice(callback){
    voiceItModule.encapsulatedVoiceEnrollment(options.user_id,options.content_language, options.phrase,
      (successResponse)=>{callback(successResponse);},
      (failureResponse)=>{callback(failureResponse);}
    );
  }
  verifyVoice(callback){
    voiceItModule.encapsulatedVoiceVerification(options.user_id,options.content_language, options.phrase,
      (successResponse)=>{callback(successResponse);},
      (failureResponse)=>{callback(failureResponse);}
    );
  }
  enrollFace(callback){
      voiceItModule.encapsulatedFaceEnrollment(options.user_id,
      (successResponse)=>{callback(successResponse);},
      (failureResponse)=>{callback(failureResponse);}
    );
  }
  verifyFace(callback){
      voiceItModule.encapsulatedFaceVerification(options.user_id,options.content_language,
      (successResponse)=>{callback(successResponse);},
      (failureResponse)=>{callback(failureResponse);}
    );
  }
  enrollVideo(callback){
    voiceItModule.encapsulatedVideoEnrollment(options.user_id,options.content_language, options.phrase,
      (successResponse)=>{callback(successResponse);},
      (failureResponse)=>{callback(failureResponse);}
    );
  }
  verifyVideo(callback){
    voiceItModule.encapsulatedVideoVerification(options.user_id,options.content_language, options.phrase,
      (successResponse)=>{callback(successResponse);},
      (failureResponse)=>{callback(failureResponse);}
    );
  }
  resolveEnrollment(index, callback){
    if (index == 0){
      this.enrollVoice((res)=>{callback(res);});
    } else if (index == 1){
      this.enrollFace((res)=>{callback(res);});
    } else {
      this.enrollVideo((res)=>{callback(res);});
    }
  }
  resolveVerification(index, callback){
    if (index == 0){
      this.verifyVoice((res)=>{callback(res);});
    } else if (index == 1){
      this.verifyFace((res)=>{callback(res);});
    } else {
      this.verifyVideo((res)=>{callback(res);});
    }
  }
  render() {
    const labels = ['Voice', 'Face', 'Video'];
    const label = labels[this.props.index];
    return (
      <View style={styles.actionContainer}>
        <TouchableOpacity
          activeOpacity={.8}
          style={styles.enrollButton}
          onPress={() => this.resolveEnrollment(this.props.index, (res)=>{console.log(res);})}
          >
          <Text style={styles.buttonText}>{label} Enrollment</Text>
        </TouchableOpacity>
        <TouchableOpacity
          activeOpacity={.8}
          style={styles.verifyButton}
          onPress={() => this.resolveVerification(this.props.index, (res)=>{console.log(res);})}
          >
          <Text style={styles.buttonText}>{label} Verification</Text>
        </TouchableOpacity>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    backgroundColor: '#212B36',
    justifyContent: 'center',
    paddingHorizontal: 30,
  },
  title: {
    color: '#FFFFFF',
    fontSize: 24,
    fontWeight: '700',
    marginTop: 12,
  },
  subtitle: {
    color: '#FBC132',
    fontSize: 14,
    fontWeight: '500',
    marginTop: 4,
    marginBottom: 24,
  },
  action: {
    width: '100%',
    marginTop: 20,
  },
  actionContainer: {
    width: '100%',
  },
  enrollButton: {
    borderRadius: 12,
    height: 52,
    alignItems: 'center',
    justifyContent: 'center',
    marginVertical: 6,
    backgroundColor: '#505050',
  },
  verifyButton: {
    borderRadius: 12,
    height: 52,
    alignItems: 'center',
    justifyContent: 'center',
    marginVertical: 6,
    backgroundColor: '#FBC132',
  },
  buttonText: {
    color: '#FFFFFF',
    fontSize: 16,
    fontWeight: '600',
  },
  tabButton: {
    flex: 1,
    paddingVertical: 12,
    alignItems: 'center',
    backgroundColor: '#505050',
    marginHorizontal: 4,
    borderRadius: 10,
  },
  tabButtonSelected: {
    backgroundColor: '#FBC132',
  },
  tabText: {
    color: '#FFFFFF',
    fontSize: 15,
    fontWeight: '500',
  },
  tabTextSelected: {
    color: '#FFFFFF',
    fontWeight: '700',
  },
  buttonPanel: {
    flexDirection: 'row',
    width: '100%',
  },
  image: {
    width: '60%',
    height: '15%',
  },
});
