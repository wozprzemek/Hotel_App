<template>
    <div id="full">
        <div id="left_bar">
            <div id="left_bar_content">
                <div class="left_bar_text"><router-link to="/admin/reservations">Reservations</router-link></div>
                <hr style="width: 200px; height: 0px;
        border: none;
        border-top: 2px solid rgba(60,60,73,1);">
                <div class="left_bar_text"><router-link to="/admin/guests">Guests</router-link></div>
                <hr style="width: 200px; height: 0px;
        border: none;
        border-top: 2px solid rgba(60,60,73,1);">
                <div class="left_bar_text"><router-link to="/admin/rooms">Rooms</router-link></div>
                <hr style="width: 200px; height: 0px;
        border: none;
        border-top: 2px solid rgba(60,60,73,1);">
                <div class="left_bar_text"><router-link to="/admin/orders">Orders</router-link></div>
                <hr style="width: 200px; height: 0px;
        border: none;
        border-top: 2px solid rgba(60,60,73,1);">
                <div class="left_bar_text"><router-link to="/admin/manage">Manage other data</router-link></div>
            </div>
        </div>
        <div id="content">
           <div id="title">Manage Other Data</div>
           <div class="details_box" id="categories_box">Categories</div>
           <div class="details_box" id="products_box">Products</div>
           <div class="details_box" id="payment_box">Payment Methods</div>
           <div class="details_box" id="admins_box">Admins</div>
           <div class="details_box" id="countries_box">Countries</div>
           <div class="details_box" id="cities_box">Cities</div>
        </div>
    </div>
</template>

<script>
    // import {AgGridVue} from "ag-grid-vue3";
    import {reactive} from "vue";
    export default {
        name: "Reservation",
        components: {
    // AgGridVue,
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
            roomNumber: "",
            rowDataReservations: reactive([]),
            columnDefsReservations: [
                { headerName: "Reservation ID", field: "reservationID",  resizable: true, type: 'rightAligned', width: 130},
                { headerName: "Guest ID", field: "guestID" , resizable: true, type: 'rightAligned', width: 100},
                { headerName: "No. of Guests", field: "numberOfGuests" , resizable: true, type: 'rightAligned', width: 150},
                { headerName: "Start Date", field: "startDate" , resizable: true, type: 'rightAligned',width: 200},
                { headerName: "End Date", field: "endDate" , resizable: true, type: 'rightAligned', width: 200},
                { headerName: "Price", field: "price" , resizable: true, type: 'rightAligned', width: 150},
                { headerName: "Payment Method", field: "paymentMethod" , resizable: true, type: 'rightAligned', width: 200},
                { headerName: "State", field: "reservationState" , resizable: true, type: 'rightAligned', width: 200},
            ],
            reservationDetails: {},
            roomDetails: {},
            hasReservation: true,
        }
    },
    created() {
        this.rowSelection = 'single';
        this.roomNumber = this.$route.params.id;

        // FETCH ROOM DETAILS AT LOAD
        fetch('/api/room/details?' + new URLSearchParams({
            roomNumber: this.roomNumber,
        })).then(result => result.json()).then(data => {
            this.roomDetails = data;
            document.querySelector("#room_number").textContent = this.roomDetails.roomNumber;
            document.querySelector("#room_name").textContent = this.roomDetails.roomName;
            document.querySelector("#floor").textContent = this.roomDetails.floor;
            document.querySelector("#price_per_night").textContent = this.roomDetails.pricePerNight;
            document.querySelector("#single_beds").textContent = this.roomDetails.singleBeds;
            document.querySelector("#double_beds").textContent = this.roomDetails.doubleBeds;
        });

        // FETCH ACTIVE RESERVATION DETAILS AT LOAD
        fetch('/api/rsv/current?' + new URLSearchParams({
            roomNumber: this.roomNumber,
        })).then(response => response.json()).then(data => {
            this.reservationDetails = data;
            if(this.reservationDetails.reservationID != 0){
                this.hasReservation = true;
                document.querySelector("#reservation_id").textContent = this.reservationDetails.reservationID;
                document.querySelector("#no_of_guests").textContent = this.reservationDetails.numberOfGuests;
                document.querySelector("#start_date").textContent = this.reservationDetails.startDate.split('T')[0];
                document.querySelector("#end_date").textContent = this.reservationDetails.endDate.split('T')[0];
                document.querySelector("#payment_method").textContent = this.reservationDetails.paymentMethod;
                document.querySelector("#reservation_state").textContent = this.reservationDetails.reservationState;
            }
            else{
                this.hasReservation = false;
            }
            
        });

        // FETCH RESERVATION HISTORY AT LOAD
        fetch('/api/rsv/history?' + new URLSearchParams({
            roomNumber: this.roomNumber,
        })).then(result => result.json()).then(remoteRowData => this.rowDataReservations.value = remoteRowData);
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
                reservationID: this.reservationId,
            }

            console.log(objectEmptyOrder);
            
            const all = document.querySelectorAll('form.order_list_row');
                // Change the text of multiple elements with a loop
                var orderItems = [];
                all.forEach(element => {
                    var formData = new FormData(element);
                    var orderRow = {};
                    formData.forEach((value, key) => orderRow[key] = value);
                    orderItems.push(orderRow);
            });
    

            if (orderItems.map(el => Object.values(el).filter(el => el.length != 0)).filter(el => el.length != 4).length === 0){
                
                var orderId;
                var orderJson = {};

                fetch("/api/order/add", {
                    method: "POST",
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify(objectEmptyOrder)
                }).then(response => response.json()).then(data => {
                    orderId = data;
                    console.log(orderId);
                    orderJson.orderID = orderId;
                    orderJson.products = orderItems;
                    fetch("/api/prodinord/add", {
                        method: "POST",
                        headers: {'Content-Type': 'application/json'},
                        body: JSON.stringify(orderJson)
                    }).then(response => response.json()).then(data => {
                        console.log(data);

                        // FETCH ORDERS ON ORDER ADD
                        fetch('/api/order/all?' + new URLSearchParams({
                        reservationID: this.reservationId,
                        })) .then(result => result.json()).then(remoteRowData => this.rowDataOrders.value = remoteRowData);
                    });
                });
            }
            else{
                alert("You have unfinished order items.");
            }
        },
        deleteReservation(){
            if (confirm('Are you sure you want to DELETE THIS RESERVATION? \nThis action is irreversible!')) {
                console.log('deleted');
                this.$router.push({ path: '/admin/reservations/'})
            }
        },
        onRowClicked(params) {
            console.log(params.node.data.reservationID);
            this.$router.push({ path: '/admin/reservations/' + params.node.data.reservationID });
        }
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
        display: grid;
        grid-template-rows: 200px 1fr;
    }

    #left_bar_content{
        grid-area: 2/1/3/2;
    }

    .left_bar_text{
        font-size: 18px;
        text-align: left;
        margin-left: 30px;
        margin-bottom: 10px;
    }

    a{
      text-decoration: none !important;
      color: #DBDEE6;
    }

    a:hover{
      color: #abb1c2;
    }

    hr{
        margin: 0;
        margin-bottom: 20px;
        height: 0px;
        border: none;
        border-top: 1px solid rgba(40,50,63,0.3);
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
        grid-template-rows: 70px 50px 180px 180px 180px 1fr;
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
        background-color: #28323F;
        color: white;
        box-shadow: 0px 1px 2px rgba(50,50,50,0.2);
        text-align: center;
        font-size: 20px;
        padding-top: 80px;
    }

    #categories_box{
        grid-area: 3/2/4/3;
        grid-template-rows: 50px 1fr;
        grid-template-columns: 1fr;
    }

    #products_box{
        grid-area: 3/3/4/4;
        grid-template-rows: 50px 1fr;
        grid-template-columns: 1fr;
    }

    #payment_box{
        grid-area: 4/2/5/3;
        grid-template-rows: 50px 1fr;
        grid-template-columns: 1fr;
    }

    #countries_box{
        grid-area: 5/2/6/3;
        grid-template-rows: 50px 1fr 100px;
        grid-template-columns: 1fr 1fr 1fr;
    }

    #cities_box{    
        grid-area: 5/3/6/4;
        grid-template-rows: 50px 1fr 100px;
        grid-template-columns: 1fr 1fr 1fr;
    }

    #admins_box{
        grid-area: 4/3/5/4;
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
        height: 0px;
        border: none;
        border-top: 1px solid rgba(40,50,63,0.3);;
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