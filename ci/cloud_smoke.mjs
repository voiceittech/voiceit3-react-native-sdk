// Cloud smoke: exercises the React Native SDK's API layer (src/VoiceItAPI.js,
// copied to ./_voiceitapi.mjs by CI so Node treats it as ESM) against the real
// api.voiceit.io. Non-media cycle (createUser -> getAllUsers -> deleteUser):
// media enroll/verify needs a device/RN runtime, but this proves auth + the
// HTTP/response layer work end-to-end with the cloud.
import VoiceItAPI from './_voiceitapi.mjs';
const { VOICEIT_API_KEY: K, VOICEIT_API_TOKEN: T } = process.env;
if (!K || !T) { console.error('Missing VOICEIT_API_KEY/VOICEIT_API_TOKEN'); process.exit(1); }
const v = new VoiceItAPI(K, T);
const need = (r, label) => { console.log(label + ':', r && r.responseCode); if (!r || r.responseCode !== 'SUCC') throw new Error(label + ' ' + JSON.stringify(r)); return r; };
(async () => {
  let userId, ok = true;
  try {
    const u = need(await v.createUser(), 'createUser'); userId = u.userId;
    need(await v.getAllUsers(), 'getAllUsers');
    console.log('PASSED: react-native SDK API layer completed a real cloud cycle against api.voiceit.io');
  } catch (e) { console.error('FAILED:', e.message); ok = false; }
  finally { if (userId) { try { await v.deleteUser(userId); console.log('cleanup: deleted ' + userId); } catch (_) {} } process.exit(ok ? 0 : 1); }
})();
