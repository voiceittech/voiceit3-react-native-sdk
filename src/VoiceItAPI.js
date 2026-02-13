const BASE_URL = 'https://qpi.voiceit.io';

class VoiceItAPI {
  constructor(apiKey, authToken) {
    this.apiKey = apiKey;
    this.authToken = authToken;
    this.authHeader = 'Basic ' + this._btoa(apiKey + ':' + authToken);
    this.notificationUrl = '';
  }

  _btoa(str) {
    // Use Buffer in Node/RN environments, fallback to global btoa
    if (typeof Buffer !== 'undefined') {
      return Buffer.from(str, 'utf-8').toString('base64');
    }
    return btoa(str);
  }

  async _request(method, path, { query, body, headers: extraHeaders } = {}) {
    let url = BASE_URL + path;
    if (query) {
      const params = new URLSearchParams();
      for (const [k, v] of Object.entries(query)) {
        if (v !== undefined && v !== null) params.append(k, v);
      }
      const qs = params.toString();
      if (qs) url += '?' + qs;
    }

    const headers = {
      Authorization: this.authHeader,
      ...extraHeaders,
    };

    if (this.notificationUrl) {
      headers['X-Notification-URL'] = this.notificationUrl;
    }

    const opts = { method, headers };

    if (body) {
      if (body instanceof FormData) {
        opts.body = body;
      } else {
        headers['Content-Type'] = 'application/json';
        opts.body = JSON.stringify(body);
      }
    }

    const res = await fetch(url, opts);
    return res.json();
  }

  setNotificationUrl(notificationUrl) {
    this.notificationUrl = notificationUrl;
  }

  // ─── Users ──────────────────────────────────────────────────────────

  getAllUsers() {
    return this._request('GET', '/users');
  }

  createUser() {
    return this._request('POST', '/users');
  }

  checkUserExists(userId) {
    return this._request('GET', `/users/${userId}`);
  }

  deleteUser(userId) {
    return this._request('DELETE', `/users/${userId}`);
  }

  getGroupsForUser(userId) {
    return this._request('GET', `/users/${userId}/groups`);
  }

  createUserToken(userId, timeOut = '3600') {
    return this._request('POST', `/users/${userId}/token`, {
      query: { timeOut },
    });
  }

  expireUserTokens(userId) {
    return this._request('POST', `/users/${userId}/expireTokens`);
  }

  // ─── Groups ─────────────────────────────────────────────────────────

  getAllGroups() {
    return this._request('GET', '/groups');
  }

  getGroup(groupId) {
    return this._request('GET', `/groups/${groupId}`);
  }

  checkGroupExists(groupId) {
    return this._request('GET', `/groups/${groupId}/exists`);
  }

  createGroup(description) {
    return this._request('POST', '/groups', {
      query: description ? { description } : undefined,
    });
  }

  deleteGroup(groupId) {
    return this._request('DELETE', `/groups/${groupId}`);
  }

  // ─── Associations ───────────────────────────────────────────────────

  addUserToGroup(groupId, userId) {
    return this._request('PUT', '/groups/addUser', {
      query: { groupId, userId },
    });
  }

  removeUserFromGroup(groupId, userId) {
    return this._request('DELETE', '/groups/removeUser', {
      query: { groupId, userId },
    });
  }

  // ─── Enrollment Listing ─────────────────────────────────────────────

  getAllVoiceEnrollments(userId) {
    return this._request('GET', `/enrollments/voice/${userId}`);
  }

  getAllFaceEnrollments(userId) {
    return this._request('GET', `/enrollments/face/${userId}`);
  }

  getAllVideoEnrollments(userId) {
    return this._request('GET', `/enrollments/video/${userId}`);
  }

  // ─── Enrollment Deletion ────────────────────────────────────────────

  deleteVoiceEnrollment(enrollmentId) {
    return this._request('DELETE', `/enrollments/${enrollmentId}/voice`);
  }

  deleteFaceEnrollment(enrollmentId) {
    return this._request('DELETE', `/enrollments/${enrollmentId}/face`);
  }

  deleteVideoEnrollment(enrollmentId) {
    return this._request('DELETE', `/enrollments/${enrollmentId}/video`);
  }

  // ─── Voice Enrollment ───────────────────────────────────────────────

  createVoiceEnrollment(userId, contentLanguage, phrase, recording) {
    const form = new FormData();
    form.append('userId', userId);
    form.append('contentLanguage', contentLanguage);
    form.append('phrase', phrase);
    form.append('recording', recording);
    return this._request('POST', '/enrollments/voice', { body: form });
  }

  createVoiceEnrollmentByUrl(userId, contentLanguage, phrase, fileUrl) {
    return this._request('POST', '/enrollments/voice/byUrl', {
      query: { userId, contentLanguage, phrase, fileUrl },
    });
  }

  // ─── Face Enrollment ────────────────────────────────────────────────

  createFaceEnrollment(userId, { photo, video, liveness, livenessThreshold } = {}) {
    const form = new FormData();
    form.append('userId', userId);
    if (photo) form.append('photo', photo);
    if (video) form.append('video', video);
    const headers = {};
    if (liveness !== undefined) headers['X-Require-Liveness'] = String(liveness);
    if (livenessThreshold !== undefined) headers['X-Liveness-Threshold'] = String(livenessThreshold);
    return this._request('POST', '/enrollments/face', { body: form, headers });
  }

  createFaceEnrollmentByUrl(userId, fileUrl, { liveness, livenessThreshold } = {}) {
    const headers = {};
    if (liveness !== undefined) headers['X-Require-Liveness'] = String(liveness);
    if (livenessThreshold !== undefined) headers['X-Liveness-Threshold'] = String(livenessThreshold);
    return this._request('POST', '/enrollments/face/byUrl', {
      query: { userId, fileUrl },
      headers,
    });
  }

  // ─── Video Enrollment ───────────────────────────────────────────────

  createVideoEnrollment(userId, contentLanguage, phrase, video, { liveness, livenessThreshold } = {}) {
    const form = new FormData();
    form.append('userId', userId);
    form.append('contentLanguage', contentLanguage);
    form.append('phrase', phrase);
    form.append('video', video);
    const headers = {};
    if (liveness !== undefined) headers['X-Require-Liveness'] = String(liveness);
    if (livenessThreshold !== undefined) headers['X-Liveness-Threshold'] = String(livenessThreshold);
    return this._request('POST', '/enrollments/video', { body: form, headers });
  }

  createVideoEnrollmentByUrl(userId, contentLanguage, phrase, fileUrl, { liveness, livenessThreshold } = {}) {
    const headers = {};
    if (liveness !== undefined) headers['X-Require-Liveness'] = String(liveness);
    if (livenessThreshold !== undefined) headers['X-Liveness-Threshold'] = String(livenessThreshold);
    return this._request('POST', '/enrollments/video/byUrl', {
      query: { userId, contentLanguage, phrase, fileUrl },
      headers,
    });
  }

  // ─── Voice Verification ─────────────────────────────────────────────

  voiceVerification(userId, contentLanguage, phrase, recording) {
    const form = new FormData();
    form.append('userId', userId);
    form.append('contentLanguage', contentLanguage);
    form.append('phrase', phrase);
    form.append('recording', recording);
    return this._request('POST', '/verification/voice', { body: form });
  }

  voiceVerificationByUrl(userId, contentLanguage, phrase, fileUrl) {
    return this._request('POST', '/verification/voice/byUrl', {
      query: { userId, contentLanguage, phrase, fileUrl },
    });
  }

  // ─── Face Verification ──────────────────────────────────────────────

  faceVerification(userId, { photo, video, liveness, livenessThreshold } = {}) {
    const form = new FormData();
    form.append('userId', userId);
    if (photo) form.append('photo', photo);
    if (video) form.append('video', video);
    const headers = {};
    if (liveness !== undefined) headers['X-Require-Liveness'] = String(liveness);
    if (livenessThreshold !== undefined) headers['X-Liveness-Threshold'] = String(livenessThreshold);
    return this._request('POST', '/verification/face', { body: form, headers });
  }

  faceVerificationByUrl(userId, fileUrl, { liveness, livenessThreshold } = {}) {
    const headers = {};
    if (liveness !== undefined) headers['X-Require-Liveness'] = String(liveness);
    if (livenessThreshold !== undefined) headers['X-Liveness-Threshold'] = String(livenessThreshold);
    return this._request('POST', '/verification/face/byUrl', {
      query: { userId, fileUrl },
      headers,
    });
  }

  // ─── Video Verification ─────────────────────────────────────────────

  videoVerification(userId, contentLanguage, phrase, video, { liveness, livenessThreshold } = {}) {
    const form = new FormData();
    form.append('userId', userId);
    form.append('contentLanguage', contentLanguage);
    form.append('phrase', phrase);
    form.append('video', video);
    const headers = {};
    if (liveness !== undefined) headers['X-Require-Liveness'] = String(liveness);
    if (livenessThreshold !== undefined) headers['X-Liveness-Threshold'] = String(livenessThreshold);
    return this._request('POST', '/verification/video', { body: form, headers });
  }

  videoVerificationByUrl(userId, contentLanguage, phrase, fileUrl, { liveness, livenessThreshold } = {}) {
    const headers = {};
    if (liveness !== undefined) headers['X-Require-Liveness'] = String(liveness);
    if (livenessThreshold !== undefined) headers['X-Liveness-Threshold'] = String(livenessThreshold);
    return this._request('POST', '/verification/video/byUrl', {
      query: { userId, contentLanguage, phrase, fileUrl },
      headers,
    });
  }

  // ─── Voice Identification ───────────────────────────────────────────

  voiceIdentification(groupId, contentLanguage, phrase, recording) {
    const form = new FormData();
    form.append('groupId', groupId);
    form.append('contentLanguage', contentLanguage);
    form.append('phrase', phrase);
    form.append('recording', recording);
    return this._request('POST', '/identification/voice', { body: form });
  }

  voiceIdentificationByUrl(groupId, contentLanguage, phrase, fileUrl) {
    return this._request('POST', '/identification/voice/byUrl', {
      query: { groupId, contentLanguage, phrase, fileUrl },
    });
  }

  // ─── Face Identification ────────────────────────────────────────────

  faceIdentification(groupId, { photo, video } = {}) {
    const form = new FormData();
    form.append('groupId', groupId);
    if (photo) form.append('photo', photo);
    if (video) form.append('video', video);
    return this._request('POST', '/identification/face', { body: form });
  }

  faceIdentificationByUrl(groupId, fileUrl) {
    return this._request('POST', '/identification/face/byUrl', {
      query: { groupId, fileUrl },
    });
  }

  // ─── Video Identification ───────────────────────────────────────────

  videoIdentification(groupId, contentLanguage, phrase, video) {
    const form = new FormData();
    form.append('groupId', groupId);
    form.append('contentLanguage', contentLanguage);
    form.append('phrase', phrase);
    form.append('video', video);
    return this._request('POST', '/identification/video', { body: form });
  }

  videoIdentificationByUrl(groupId, contentLanguage, phrase, fileUrl) {
    return this._request('POST', '/identification/video/byUrl', {
      query: { groupId, contentLanguage, phrase, fileUrl },
    });
  }

  // ─── Phrases ────────────────────────────────────────────────────────

  getPhrases(languageCode) {
    return this._request('GET', `/phrases/${languageCode}`);
  }
}

export default VoiceItAPI;
