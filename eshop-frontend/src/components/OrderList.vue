<template>
    <div class="py-5">
        <b-container class="bg-light py-3 px-lg-5">
            <b-row class="mt-5 py-2">
                <h3>My Orders</h3>
            </b-row>
            <b-row>
                <div>
                    <b-table striped hover :items="orders" :fields="fields">
                        <template #cell(cancel)="row">
                            <b-button size="sm" class="mr-2" @click="onClickCancelOrderItem(row)">
                                Cancel
                            </b-button>
                        </template>
                    </b-table>
                </div>
            </b-row>
            <hr/>
        </b-container>
    </div>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex'

    export default {
        name: 'OrderList',
        data: () => {
            return {
                fields: ['id', 'productIds', 'emailAddress', 'address', 'cancel'],
                products: []
            }
        },
        components: {},
        methods: {
            ...mapActions('order', ['deleteOrders']),
            async onClickCancelOrderItem(row) {
                const targetId = row.item.id;
                await this.deleteOrders(targetId);
            }
        },
        computed: mapGetters({
            orders: 'order/orders'
        }),

        mounted() {
            console.log(this.orders);
        },
        watch: {
            //orders(orders) {}
        }
    }
</script>
