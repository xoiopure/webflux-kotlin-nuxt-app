<template>
  <v-layout>
    <v-flex class="text-center">
      <h1>Twimmer</h1>
      <v-btn @click="getMessageAction">
        <v-icon>email</v-icon>
      </v-btn>
      <v-btn @click="connectAction">
        <v-icon>text_rotate_vertical</v-icon>
      </v-btn>
      <v-btn @click="disconnectAction">
        <v-icon>cancel</v-icon>
      </v-btn>
      <p :key="Date.now()">
        {{ messageState }}
      </p>
      <ul v-for="(msg, index) in stateMessagesMessages">
        <li :key="index">{{ index }}: {{ msg }}</li>
      </ul>
    </v-flex>
  </v-layout>
</template>

<script>
  import { mapMutations, mapState, mapActions } from 'vuex';

  export default {
    methods: {
      ...mapActions({
        connectAction: 'messages/connect',
        disconnectAction: 'messages/disconnect',
        getMessageAction: 'messages/getMessage',
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
