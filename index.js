import VoiceItAPI from './src/VoiceItAPI';
import { NativeModules } from 'react-native';

const { Voiceit: VoiceItNative } = NativeModules;

export { VoiceItAPI, VoiceItNative };
export default VoiceItAPI;
