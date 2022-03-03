<template>
    <div id="full">
        <div id="left_bar">
        </div>
        <div id="content">
            <button id="delete_button">Delete Reservation</button>
            <div id="navbar">
                <router-link to="/admin/reservations">Reservations</router-link> /
                <span> Reservation #{{$route.params.id}}</span>
            </div>
            <div id="title">Reservation #{{$route.params.id}}</div>
            <div class="details_box" id="reservation_details_box">
                <div id="title_box">Reservation Details</div>
                <div class="details_text" id="details_field_name">Reservation ID</div>
                <div class="details_text" id="details_field_name">No. of Guests</div>
                <div class="details_text" id="details_field_name">Start Date</div>
                <div class="details_text" id="details_field_name">End Date</div>
                <div class="details_text" id="details_field_name">Payment Method</div>
                <div class="details_text" id="details_field_name">Reservation State</div>

                <div class="details_text" id="details_field_value">1</div>
                <div class="details_text" id="details_field_value">4</div>
                <div class="details_text" id="details_field_value">22/01/2022</div>
                <div class="details_text" id="details_field_value">27/01/2022</div>
                <div class="details_text" id="details_field_value">Cash</div>
                <div class="details_text" id="details_field_value">Registered</div>
            </div>
            <div class="details_box" id="guest_details_box">
                <div id="title_box">Guest Details</div>
                <div class="details_text" id="details_field_name">Guest ID</div>
                <div class="details_text" id="details_field_name">First Name</div>
                <div class="details_text" id="details_field_name">Last Name</div>
                <div class="details_text" id="details_field_name">Date of Birth</div>
                <div class="details_text" id="details_field_name">Telephone</div>
                <div class="details_text" id="details_field_name">Address</div>

                <div class="details_text" id="details_field_value">1</div>
                <div class="details_text" id="details_field_value">Przemysław</div>
                <div class="details_text" id="details_field_value">Woźniak</div>
                <div class="details_text" id="details_field_value">17/08/2000</div>
                <div class="details_text" id="details_field_value">48123654879</div>
                <div class="details_text" id="details_field_value">Polanka 30b, 61-131 Poznan</div>
            </div>
            <div class="details_box" id="rooms_details_box">
                <div id="title_box">Rooms</div>
                <ag-grid-vue
                  class="ag-theme-alpine" id="table_rooms"
                  :columnDefs="columnDefsRooms"
                  :rowData="rowDataRooms.value">
                </ag-grid-vue>
            </div>
            <div class="details_box" id="orders_details_box">
                <div id="title_box">Orders</div>
                <ag-grid-vue
                  class="ag-theme-alpine" id="table_orders"
                  :columnDefs="columnDefsOrders"
                  :rowData="rowDataOrders.value">
                </ag-grid-vue>
                <button id="button_add" @click="showOrderWindow()">Add</button>
            </div>
        </div>
    </div>
    <div id="order_window">
        <div id="order_window_title_bar">
                <div id="order_window_navbar">
                    <div id="order_window_title"><span>Add order</span></div>
                </div>
            <img id="order_window_exit_icon" src="../assets/delete.png" @click="popUpWindow = false; closeOrderWindow()"/>
        </div>
        <div id="order_list">
            <div class="order_list_row" style="margin-bottom: 0;">
                <div id="category">Category</div>
                <div id="product">Product</div>
                <div id="qty">Qty</div>
                <div id="time">Time</div>
                <div id="subtotal">Subtotal</div>
            </div>
            <hr>
            <form class="order_list_row" :key="item.id" v-for="(item, index) in orderItems" name=index>
                <select name="category" id="category" v-model=orderItems[index].category>
                    <option v-for="category in items.map(a => a.category)" :key=category>{{ category }}</option>
                </select>
                <select name="product" id="product" v-model=orderItems[index].product>
                    <option v-for="product in orderItems[index].category.length != 0 ? (this.items.find(o => o.category === orderItems[index].category).products.map(a => a.productName)) : []" :key=product>{{product}}</option>
                </select>
                <input id="qty" type="number" name="qty" min=1 v-model=orderItems[index].qty>
                <input id="time" type="datetime-local" name="time" v-model=orderItems[index].time>
                <div id="subtotal">$15</div>
            </form>
        </div>
        <div id="order_window_footer">
            <button class="order_window_add_item_button" @click="addOrderItem()">Add item</button>
            <button class="order_window_confirm_order_button" @click="confirmOrder()">Confirm order</button>
        </div>
    </div>

</template>

<script>
    import { AgGridVue } from "ag-grid-vue3";
    import {reactive, onMounted} from "vue";
    export default {
        name: "Reservation",
        components: {
    AgGridVue,
    },
    data(){
        return{
            popUpWindow: false,
            orderItems: [
                {
                    id: 1,
                    category: "",
                    product: "",
                    qty: 1,
                    time: "",
                    subtotal: 0,
                },
            ],
            items: [],
            itemsFetched: [],
            selectedCategories: [],
            selectedProducts: [],
            selectedQty: [],
            selectedItems: [{
                category: "",
                product: "",
                qty: 1,
                time: "",
            }],
            reservationId: "",
        }
    },
    setup() {
        let rowDataRooms = reactive([]);
        let rowDataOrders = reactive([]);

        onMounted(() => {
            fetch('/api/roominrsv/all?' + new URLSearchParams({
                reservationID: 7,
            })).then(result => result.json()).then(remoteRowData => rowDataRooms.value = remoteRowData);

            fetch('/api/roominrsv/all?' + new URLSearchParams({
                reservationID: 7,
            })) .then(result => result.json()).then(remoteRowData => rowDataOrders.value = remoteRowData);
        })

        return {
            columnDefsRooms: [
                { field: 'roomNumber', sortable: true, filter: true, type: 'rightAligned', width: 150 },
                { field: 'roomName', sortable: true, filter: true, type: 'rightAligned', width: 200 },
                { field: 'pricePerNight', sortable: true, filter: true, type: 'rightAligned',width: 150 },
                { field: 'totalPrice', sortable: true, filter: true, type: 'rightAligned', width: 125 },
                { field: 'singleBeds', sortable: true, filter: true, type: 'rightAligned', width: 125 },
                { field: 'doubleBeds', sortable: true, filter: true, type: 'rightAligned', width: 140 },
        ],
        rowDataRooms,
        columnDefsOrders: [
                { field: 'orderID', sortable: true, filter: true, width: 200 },
                { field: 'totalPrice', sortable: true, filter: true, width: 200 },
                { field: 'timeOfOrder', sortable: true, filter: true, width: 150 },
        ],
        rowDataOrders,
        };
    },
    created() {
        this.rowSelection = 'single';
        console.log(this.orderItems[0].category);
        console.log(this.items.find(o => o.category === this.orderItems[0].category));

        // FETCH ALL CATEGORIES AT LOAD
        fetch("/api/cat/products", {
            method: "GET",
            headers: {'Content-Type': 'application/json'},
        }).then(response => response.json()).then(data => {
            this.items = data;
            console.log(this.items);
        });
        
        this.reservationId = this.$route.params.id;
    },
    methods: {
        onSelectionChanged() {
        const selectedRows = this.rowData.getSelectedRows();
        console.log(selectedRows.length === 1 ? selectedRows[0].reservation_id : '');
        },
        addOrderItem(){
            this.orderItems.push({
                id: this.orderItems[this.orderItems.length-1].id,
                category: "",
                product: "",
                qty: 1,
                time: "",
                subtotal: 0,
            });
            console.log('categories: ', this.selectedCategories);
            console.log('products: ', this.selectedProducts);
            console.log('items: ', this.selectedItems);
        },
        emptyOrderItems(){
            this.orderItems = [
                {
                    id: 1,
                    category: "",
                    product: "",
                    qty: 0,
                    time: "",
                    subtotal: 0,
                },
            ]
        },
        showOrderWindow(){
            this.popUpWindow = true;
            var orderWindow = document.querySelector("#order_window");
            orderWindow.style.visibility = "visible";
        },
        closeOrderWindow(){
            if (confirm('Are you sure you want to cancel this order?')) {
                this.popUpWindow = false;
                var orderWindow = document.querySelector("#order_window");
                orderWindow.style.visibility = "hidden";
                this.emptyOrderItems();
                this.selectedCategories =  [],
                this.selectedProducts = [],
                this.selectedQty = [],
                this.selectedItems = []
                console.log('Order canceled');
            }
        },
        confirmOrder(){
            // first POST an empty order
            var currentdate = new Date(); 
            var datetime = 
                currentdate.getFullYear() + "-"
                + (currentdate.getMonth()+1)  + "-" 
                + currentdate.getDate() + "T"
                + currentdate.getHours() + ":"  
                + currentdate.getMinutes()


            var objectEmptyOrder = {
                timeOfOrder: datetime,
                reservationID: 1 // CHANGE IT!!
            }

            console.log(objectEmptyOrder);
            
            const all = document.querySelectorAll('form.order_list_row');
                // Change the text of multiple elements with a loop
                var orderJson = [];
                all.forEach(element => {
                    var formData = new FormData(element);
                    var orderRow = {};
                    formData.forEach((value, key) => orderRow[key] = value);
                    orderJson.push(orderRow);
            });
            console.log(orderJson);
            console.log(orderJson.filter(el => Object.keys(el).length != 4));
            console.log(orderJson.map(el => Object.values(el).filter(el => el.length != 0)).filter(el => el.length != 4).length); // number of not-full order rows; must be 0 to confirm order

            if (orderJson.map(el => Object.values(el).filter(el => el.length != 0)).filter(el => el.length != 4).length === 0){
                
                var orderId;

                fetch("/api/order/add", {
                    method: "POST",
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify(objectEmptyOrder)
                }).then(response => response.json()).then(data => {
                    orderId = data;
                    console.log(orderId);

                    fetch("/api/prodinord/add", {
                        method: "POST",
                        headers: {'Content-Type': 'application/json'},
                        body: JSON.stringify(orderJson)
                    }).then(response => response.json()).then(data => {
                        console.log(data);
                    });
                });
            }
            else{
                alert("You have unfinished order items.");
            }
        },
  },
}
</script>

<style scoped>
    body{
        margin: 0;
        padding: 0;
        font-family: sans-serif;
    }

    #full{
        width: 100vw;
        height: 100vh;
        display: grid;
        grid-template-columns: 250px 1fr;
        grid-template-rows: 1fr;
    }

    #left_bar{
        background-color: #080A0D;
        grid-area: 1/1/1/1;
    }

    #navbar{
        grid-area: 1/2/2/3;
        padding-top: 20px;
        text-align: left;
    }

    #navbar a:focus {
        color: #005EF5;
    }

    #navbar a:visited {
        color: #005EF5;
    }

    #content{
        background-color: #DBDEE6;
        grid-area: 1/2/1/2;
        display: grid;
        grid-template-columns: 50px 440px 440px 600px 1fr;
        grid-template-rows: 70px 1fr 340px 340px 1fr;
        grid-gap: 20px;
    }

    #title{
        font-size: 30px;
        font-weight: 600;
        grid-area: 2/2/3/3;
        align-self: end;
        justify-self: start;
    }

    .details_box{
        background-color: white;
        display: grid;
        box-shadow: 0px 1px 2px rgba(50,50,50,0.2);
    }

    #reservation_details_box{
        grid-area: 3/2/4/3;
        grid-template-rows: 50px 25px repeat(6, 30px) 1fr;
        grid-template-columns: 1.5fr 1fr 1.5fr;
        grid-column-gap: 10px;
    }

    #guest_details_box{
        grid-area: 3/3/4/4;
        grid-template-rows: 50px 25px repeat(6, 30px) 1fr;
        grid-template-columns: 1.5fr 1fr 1.5fr;
        grid-template-columns: 1fr 1fr 1fr;
    }

    .details_text{
        font-size: 14px;
        
    }

    #details_field_name{
        grid-column: 1/2;
        font-weight: 600;
        text-align: right;
    }

    #details_field_value{
        grid-column: 2/3;
        text-align: left;
        padding-left: 25px
    }

    .details_box .details_text:nth-child(6n+2){
        grid-row: 3/4;   
    }
    .details_box .details_text:nth-child(6n+3){
        grid-row: 4/5;   
    }
    .details_box .details_text:nth-child(6n+4){
        grid-row: 5/6;   
    }
    .details_box .details_text:nth-child(6n+5){
        grid-row: 6/7;   
    }
    .details_box .details_text:nth-child(6n+6){
        grid-row: 7/8;   
    }
    .details_box .details_text:nth-child(6n+7){
        grid-row: 8/9;   
    }


    #rooms_details_box{
        grid-area: 4/2/4/4;
        grid-template-rows: 50px 1fr 100px;
        grid-template-columns: 1fr 1fr 1fr;
    }

    #table_rooms{
        grid-area: 2/1/-1/-1;
    }

    #table_orders{
        grid-area: 2/1/-2/-1;
        overflow-x: hidden;
    }

    #orders_details_box{
        grid-area: 3/4/5/5;
        grid-template-rows: 50px 1fr 100px;
        grid-template-columns: 1fr 1fr 1fr;
    }

    #button{
        width: 120px;
        height: 35px;
        grid-area: -2/2/-1/3;
        background: #3A86FF;
        color: white;
        font-size: 16px;
        align-self: center;
        justify-self: center;
        border-radius: 2px;
        border: none;
        cursor: pointer;
    }

    #button:hover{
        background-color: #1F75FF;
    }

    #button:active{
        background-color: #005EF5;
    }

    #button_add{
        width: 120px;
        height: 35px;
        grid-area: -2/2/-1/3;
        background: #3A86FF;
        color: white;
        font-size: 16px;
        align-self: center;
        justify-self: center;
        border-radius: 2px;
        border: none;
        cursor: pointer;
    }

    #button_add:hover{
        background-color: #1F75FF;
    }

    #button_add:active{
        background-color: #005EF5;
    }

    #title_box{
        background-color: #28323F;
        color: #C3C8D5;
        font-size: 18px;
        font-weight: 300;
        text-align: left;
        line-height: 55px;
        padding-left: 15px;
        grid-area: 1/1/1/-1;
    }

    #order_window{
        width: 1000px;
        z-index: 100;
        height: 800px;
        position: absolute;
        top: calc(50% - 400px);
        left: calc(50% - 500px);
        background-color: white;
        box-shadow: 0px 1px 5px rgba(50,50,50,0.4);
        display: grid;
        grid-template-columns: 15px 1fr 15px;
        grid-template-rows: 80px 1fr 80px;
        overflow: hidden;
        visibility: hidden;
    }

    #order_window_title_bar{
        grid-area: 1/1/2/-2;
        font-size: 20px;
        display: grid;
        grid-template-columns: 1fr 50px;
        grid-template-rows: 1fr;
    }

    #order_window_navbar{
        text-align: left;
        grid-area: 1/1/2/2;
        display: inline-flex;
        margin-left: 20px;
        margin-top: 25px;
    }

    #order_window_exit_icon{
        grid-area: 1/3/2/4;
        width: 40px;
        margin-top: 10px;
        justify-self: end;
        cursor: pointer;
    }

    #order_window_footer{
        grid-area: 3/2/4/3;
        display: grid;
        grid-template-rows: 1fr;
        grid-template-columns: 1fr 200px 200px;
        grid-gap: 20px;
        justify-items: center;
        align-items: center;
    }

    .order_window_confirm_order_button{
        grid-area: 1/4/2/5;
        width: 200px;
        height: 40px;
        background: #28323F;
        color: white;
        font-size: 16px;
        align-self: center;
        justify-self: center;
        border: none;
	    padding: 0;
        cursor: pointer;
    }

    .order_window_add_item_button{
        grid-area: 1/3/2/4;
        width: 200px;
        height: 40px;
        background: #35d38c;
        color: white;
        font-size: 16px;
        align-self: center;
        justify-self: center;
        border: none;
	    padding: 0;
        cursor: pointer;
    }

    #order_list{
        grid-area: 2/2/3/3;
        padding-top: 20px;
    }

    .order_list_row{
        width: 970px;
        height: 36px;
        display: grid;
        grid-template-rows: 1fr;
        grid-template-columns: 210px 210px 50px 210px 210px;
        grid-gap: 20px;
        justify-items: start;
        margin-bottom: 15px;
        text-align: left;
        line-height: 36px;
    }

    #category{
        grid-area: 1/1/2/2;
        width: 100%;
    }

    #product{
        grid-area: 1/2/2/3;
        width: 100%;
    }

    #qty{
        grid-area: 1/3/2/4;
        width: 100%;
    }

    #time{
        grid-area: 1/4/2/5;
        width: 100%;
    }

    #subtotal{
        grid-area: 1/5/2/6;
        width: 100%;
        padding-left: 35px;
    }

    hr{
        margin: 0;
        margin-bottom: 20px;
        color: rgba(50,50,50,0.4);
    }

    #delete_button{
        background: #D01117;
        width: 200px;
        height: 40px;
        color: white;
        font-size: 16px;
        align-self: center;
        justify-self: end;
        border-radius: 2px;
        border: none;
        cursor: pointer;
        grid-area: 2/4/3/5;
    }

</style>