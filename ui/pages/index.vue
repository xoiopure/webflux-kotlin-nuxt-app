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
          <Tweet :message="msg"/>
        </li>
      </ul>
    </v-flex>
    <v-fab-transition>
      <v-btn
        aria-label="Scroll to top"
        title="Scroll to top"
        v-show="isFabVisible"
        v-scroll="onScroll"
        @click="toTop"
        bottom
        color="red"
        fixed
        large
        right
        dark
        fab
      >
        <v-icon>keyboard_arrow_up</v-icon>
      </v-btn>
    </v-fab-transition>
  </v-layout>
</template>

<script>
  import { mapActions, mapGetters, mapState } from 'vuex';
  import Tweet from '../components/Tweet';

  export default {
    components: {
      Tweet,
    },
    methods: {
      toTop() {
        this.$router.push({ hash: '' });
        this.$vuetify.goTo(0);
      },
      onScroll(e) { // https://github.com/vuetifyjs/vuetify/blob/47847224a4a94fdbf39208932b1c631dd0b39b63/packages/docs/src/layouts/documentation/Fab.vue
        if (!!process.client || !!process.browser) {
          if (typeof window === 'undefined') return;
          const top = (
            window.pageYOffset ||
            document.documentElement.offsetTop ||
            0
          );
          this.setFabVisibility(top > 300);
        }
      },
      ...mapActions({
        connectAction: 'messages/connect',
        disconnectAction: 'messages/disconnect',
        getMessageAction: 'messages/getMessage',
        setFabVisibility: 'fab/setFabVisibility',
      }),
    },
    computed: {
      ...mapGetters({
        isFabVisible: 'fab/isFabVisible',
      }),
      ...mapState({
        //       (store/messages.js).(const state).(message: '')
        messageState: state => state.messages.message,
        //                (store/messages.js).(const state).(messages: [])
        stateMessagesMessages: state => state.messages.messages,
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
