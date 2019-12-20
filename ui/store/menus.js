/**
 * Private API: State container. Uses directly by mutations.
 */
export const state = () => ({
  title: 'Twimmer',
  sidebarEnabled: false,
});

/**
 * Private API: State mutations. Uses directly only by actions.
 */
export const mutations = {
  setSidebarEnabled: (state, value) => state.sidebarEnabled = value,
};

/**
 * Public API: Can be directly used in App and in Actions.
 */
export const getters = {
  getTitle: state => state.title,
  isSidebarEnabled: state => state.sidebarEnabled,
};

/**
 * Public API: Can be directly used in App.
 */
export const actions = {
  toggleSidebar: ({ commit, getters, state, dispatch }) =>
    commit('setSidebarEnabled', !getters.isSidebarEnabled),
  setSidebarEnabled: ({ commit, getters, state, dispatch }, value) =>
    commit('setSidebarEnabled', value),
};
