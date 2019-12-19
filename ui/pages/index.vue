<template>
  <v-layout>
    <v-flex class="text-center">
      <h1>Twimmer</h1>
      <button @click="getMessageAction">+</button>
      <button @click="connectAction">++</button>
      <button @click="disconnectAction">-</button>
      <p :key="Date.now()">
        {{ messageState }}
      </p>
      <ul v-for="(msg, index) in stateMessagesMessages">
        <li :key="index">{{ msg }}</li>
      </ul>
    </v-flex>
  </v-layout>
</template>

<script>
  import { mapMutations, mapState, mapActions } from 'vuex';

  export default {
    methods: {
      ...mapActions({
        getMessageAction: 'messages/getMessage',
        connectAction: 'messages/connect',
        disconnectAction: 'messages/disconnect',
      }),
    },
    computed: {
      ...mapState({
        //       (store/messages.js).(const state).(message: '')
        messageState: state => state.messages     .message,
        //                (store/messages.js).(const state).(messages: [])
        stateMessagesMessages: state => state.messages     .messages,
      }),
    },
    beforeDestroy() {
      this.disconnectAction();
    },
  };
</script>

<style scoped>
  ul {
    list-style: none;
  }
</style>
