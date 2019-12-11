import axios from 'axios';

export const state = () => ({
  message: '',
  messages: [],
  eventSource: null,
});

export const getters = {
  // messageExist() {
  //   //
  // },
  // messagesAmount() {
  //   //
  // }
};

export const actions = {
  getMessage({ commit }) {
    axios
      .get('/api/message')
      .catch(reason => console.log('error', reason))
      .then(value => value.data)
      .then(({ message }) => commit('setMessage', message));
      // .then(({ message }) => commit({
      //   type: 'setMessage',
      //   message,
      // }));
  },
  connect({ state, getters, commit, dispatch }) {
    dispatch('disconnect')
      .catch(err => dispatch('error', { err }))
      .then(done => {
        const eventSource = new EventSource('/api/messages');
        eventSource.onopen = () => console.log('open EventSource');
        eventSource.onerror = err => dispatch('error', { err: 'oops, EventSource error' + JSON.stringify(err) });
        eventSource.onmessage = message => commit('addMessage', message.data);
        commit('connect', eventSource);
      })
  },
  disconnect({ commit }) {
    commit('disconnect');
  },
  error(context, { err }) {
    console.log('oops', err);
  },
};

export const mutations = {
  setMessage(state, message) {
    state.message = message;
  },
  addMessage(state, message) {
    // state.messages.push(message);
    state.messages = [message, ...state.messages];
  },
  clearMessages(state) {
    state.messages = [];
  },
  connect(state, eventSource) {
    state.eventSource = eventSource;
  },
  disconnect(state) {
    if (!!state.eventSource) {
      state.eventSource.close();
    }
    state.eventSource = null;
  },
};
