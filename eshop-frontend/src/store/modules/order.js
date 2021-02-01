import axios from "axios";

export default {
    namespaced: true,
    state: {
        orderResult: {},
        orders: [],
    },
    getters: {
        orderResult: state => state.orderResult,
        orders: state => state.orders,
        ordersCount: state => (state.orders).length,
    },
    actions: {
        async fetchOrders(context) {
            console.log('fetchOrderItems');
            let orders = (await axios.get(process.env.VUE_APP_BASE_URL + "/checkouts/orders")).data;

            context.commit("setOrders", orders)
        },
        setOrders(context, orders) {
            context.commit('orders', orders)
        },
        async deleteOrders(context,orderId) {
            console.log(`Delete Order Id : ${orderId}`);
            //Delete Order
            const result = (await axios.delete(process.env.VUE_APP_BASE_URL + `/checkouts/orders/${orderId}`)).data;
            if(result) {
                context.dispatch('fetchOrders');
            }
        }
    },
    mutations: {
        setOrder(state, orderResult) {
            state.orderResult = orderResult
        },
        setOrders(state, orders) {
            state.orders = orders
        },
    }
}
