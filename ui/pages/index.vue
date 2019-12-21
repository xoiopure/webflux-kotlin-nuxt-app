<template>
  <v-layout>
    <v-flex class="text-center">
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
        <Tweet v-if="messageState" :message="messageState"/>
      </p>
      <ul v-for="(msg, index) in stateMessagesMessages">
        <li :key="index">
          <Tweet :message="msg" />
        </li>
      </ul>
    </v-flex>
  </v-layout>
</template>

<script>
  import { mapMutations, mapState, mapActions } from 'vuex';
  import Tweet from '../components/Tweet';

  export default {
    components: {
      Tweet,
    },
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
    padding: 0;
  }
  li {
    margin: 0 auto 1em;
  }
</style>
