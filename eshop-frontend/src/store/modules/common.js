export default {
    namespaced: true,
    state: {
        isLogin: false,
    },
    getters: {
        isLogin: state => state.isLogin,
    },
    actions: {
    },
    mutations: {
        setIsLogin(state, isLogin) {
            state.isLogin = isLogin
        },
    }
}

