<template>
  <ion-page>
    <ion-header>
      <ion-toolbar class="toolbar-md-primary">
        <ion-title>twimmer</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-button class="todo-fab" @click="getMessageAction">
      <i class="ion-md-add ion-ios-add" />
    </ion-button>
    <ion-button class="todo-fab" @click="connectAction">
      <i class="ion-md-add ion-ios-add" />
      <i class="ion-md-add ion-ios-add" />
    </ion-button>
    <ion-button class="todo-fab" @click="disconnectAction">
      <i class="ion-ios-remove ion-md-remove" />
    </ion-button>
    <ion-list>
      <ion-item :key="Date.now()">
        {{ messageState }}
      </ion-item>
      <ion-item v-for="(msg, index) in stateMessagesMessages" :key="index">
        {{ msg }}
      </ion-item>
    </ion-list>
  </ion-page>
</template>

<script>
  import { mapMutations, mapState, mapActions } from 'vuex';
  import Logo from '~/components/Logo.vue';

  export default {
    components: {
      Logo
    },
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
