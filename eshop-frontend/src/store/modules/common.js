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
            console.log('# : ', isLogin);
            state.isLogin = isLogin
        },
    }
}
