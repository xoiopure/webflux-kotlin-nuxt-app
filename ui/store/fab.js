/**
 * Private API: State container. Uses directly by mutations.
 */
export const state = () => ({
  showFab: false,
});

/**
 * Private API: State mutations. Uses directly only by actions.
 */
export const mutations = {
  setFabVisibility: (state, value) => state.showFab = value,
};

/**
 * Public API: Can be directly used in App and in Actions.
 */
export const getters = {
  isFabVisible: state => state.showFab,
};

/**
 * Public API: Can be directly used in App.
 */
export const actions = {
  setFabVisibility: ({ commit, getters, state, dispatch }, value) =>
    commit('setFabVisibility', value),
};
