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
           <div class="details_box" id="categories_box" @click="showPopUpWindow(); showCategoriesContent();">Categories</div>
           <div class="details_box" id="products_box" @click="showPopUpWindow(); showProductsContent();">Products</div>
           <div class="details_box" id="payments_box" @click="showPopUpWindow(); showPaymentContent();">Payment Methods</div>
           <div class="details_box" id="admins_box" @click="showPopUpWindow(); showAdminsContent();">Admins</div>
           <div class="details_box" id="countries_box" @click="showPopUpWindow(); showCountriesContent();">Countries</div>
           <div class="details_box" id="cities_box" @click="showPopUpWindow(); showCitiesContent();">Cities</div>
        </div>
    </div>
    <div id="pop_up_window">
        <div id="order_window_title_bar">
            <div id="order_window_navbar">
                <div id="order_window_title"><span>New Category</span></div>
            </div>
            <img id="order_window_exit_icon" src="../assets/delete.png" @click="popUpWindow = false; closePopUpWindow()"/>
        </div>
        <!-- CATEGORIES -->
        <div class="pop_up_window_content" id="categories_content">
            <form class="order_list_row">
                <label for="category" id="category">Category Name</label>
                <br>
                <input name="category" id="category" type="text">
                <button class="pop_up_window_add_edit_button">Add</button>
            </form>
            <ag-grid-vue
                class="ag-theme-alpine" id="pop_up_window_table"
                :columnDefs="columnDefsReservations"
                :rowData="rowDataReservations.value"
                @rowClicked="onRowClickedOrders">
            </ag-grid-vue>
        </div>

        <!-- PRODUCTS -->
        <div class="pop_up_window_content" id="products_content">
            <form class="order_list_row">
                <label for="category" id="category">Category Name</label>
                <br>
                <input name="category" id="category" type="text">
                <button class="pop_up_window_add_edit_button">Add</button>
            </form>
            <ag-grid-vue
                class="ag-theme-alpine" id="pop_up_window_table"
                :columnDefs="columnDefsReservations"
                :rowData="rowDataReservations.value"
                @rowClicked="onRowClickedOrders">
            </ag-grid-vue>
        </div>

        <!-- PAYMENT METHODS -->
        <div class="pop_up_window_content" id="payments_content">
            <form class="order_list_row">
                <label for="category" id="category">Category Name</label>
                <br>
                <input name="category" id="category" type="text">
                <button class="pop_up_window_add_edit_button">Add</button>
            </form>
            <ag-grid-vue
                class="ag-theme-alpine" id="pop_up_window_table"
                :columnDefs="columnDefsReservations"
                :rowData="rowDataReservations.value"
                @rowClicked="onRowClickedOrders">
            </ag-grid-vue>
        </div>

        <!-- ADMINS -->
        <div class="pop_up_window_content" id="admins_content">
            <form class="order_list_row">
                <label for="category" id="category">Category Name</label>
                <br>
                <input name="category" id="category" type="text">
                <button class="pop_up_window_add_edit_button">Add</button>
            </form>
            <ag-grid-vue
                class="ag-theme-alpine" id="pop_up_window_table"
                :columnDefs="columnDefsReservations"
                :rowData="rowDataReservations.value"
                @rowClicked="onRowClickedOrders">
            </ag-grid-vue>
        </div>
        
        <!-- COUNTRIES -->
        <div class="pop_up_window_content" id="countries_content">
            <form class="order_list_row">
                <label for="category" id="category">Category Name</label>
                <br>
                <input name="category" id="category" type="text">
                <button class="pop_up_window_add_edit_button">Add</button>
            </form>
            <ag-grid-vue
                class="ag-theme-alpine" id="pop_up_window_table"
                :columnDefs="columnDefsReservations"
                :rowData="rowDataReservations.value"
                @rowClicked="onRowClickedOrders">
            </ag-grid-vue>
        </div>
        
        <!-- CITIES -->
        <div class="pop_up_window_content" id="cities_content">
            <form class="order_list_row">
                <label for="category" id="category">Category Name</label>
                <br>
                <input name="category" id="category" type="text">
                <button class="pop_up_window_add_edit_button">Add</button>
            </form>
            <ag-grid-vue
                class="ag-theme-alpine" id="pop_up_window_table"
                :columnDefs="columnDefsReservations"
                :rowData="rowDataReservations.value"
                @rowClicked="onRowClickedOrders">
            </ag-grid-vue>
        </div>
        <div id="pop_up_window_footer">
            <button class="pop_up_window_confirm_edit_button" @click="confirmOrder()">Confirm</button>
        </div>
    </div>
</template>

<script>
    import {AgGridVue} from "ag-grid-vue3";
    import {reactive} from "vue";
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

        // FETCH RESERVATION HISTORY AT LOAD
        fetch('/api/rsv/all').then(result => result.json()).then(remoteRowData => this.rowDataReservations.value = remoteRowData);
        console.log( this.rowDataReservations);
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
        showPopUpWindow(){
            this.popUpWindow = true;
            var popUpWindow = document.querySelector("#pop_up_window");
            popUpWindow.style.visibility = "visible";
        },
        closePopUpWindow(){
            this.popUpWindow = false;
            var popUpWindow = document.querySelector("#pop_up_window");
            popUpWindow.style.visibility = "hidden";
            document.querySelector('#categories_content').style.display = "none";
            document.querySelector('#products_content').style.display = "none";
            document.querySelector('#payments_content').style.display = "none";
            document.querySelector('#admins_content').style.display = "none";
            document.querySelector('#countries_content').style.display = "none";
            document.querySelector('#cities_content').style.display = "none";
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
        },
        showCategoriesContent(){
            document.querySelector('#categories_content').style.display = "grid";
        },
        showProductsContent(){
            document.querySelector('#products_content').style.display = "grid";
        },
        showPaymentContent(){
            document.querySelector('#payments_content').style.display = "grid";
        },
        showAdminsContent(){
            document.querySelector('#admins_content').style.display = "grid";
        },
        showCountriesContent(){
            document.querySelector('#countries_content').style.display = "grid";
        },
        showCitiesContent(){
            document.querySelector('#cities_content').style.display = "grid";
        },
  },
}
</script>

<style scoped>

    label{
        align-self: start;
    }

    input{
        align-self: end;
    }

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

    #payments_box{
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

    #pop_up_window{
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

    .pop_up_window_content{
        grid-area: 2/2/3/3;
        display: grid;
        grid-template-rows: 100px 1fr;
        grid-template-columns: 1fr;
    }

    #pop_up_window_table{
        grid-area: 2/1/3/3;
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

    #pop_up_window_footer{
        grid-area: 3/2/4/3;
        display: grid;
        grid-template-rows: 1fr;
        grid-template-columns: 1fr 200px 200px;
        grid-gap: 20px;
        justify-items: center;
        align-items: center;
    }

    .pop_up_window_confirm_edit_button{
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

    .pop_up_window_add_edit_button{
        grid-area: 1/-1/2/-1;
        width: 100px;
        height: 40px;
        background: #35d38c;
        color: white;
        font-size: 16px;
        align-self: end;
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
        grid-area: 1/2/2/3;
        width: 970px;
        height: 72px;
        display: grid;
        grid-template-rows: 1fr;
        grid-template-columns: 300px 1fr;
        grid-gap: 20px;
        justify-items: start;
        text-align: left;
        line-height: 36px;
    }

    #category{
        grid-area: 1/1/2/2;
        width: 100%;
        height: 36px;
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

    #categories_content{
        display: none;
    }

    #products_content{
        display: none;
    }

    #payments_content{
        display: none;
    }

    #admins_content{
        display: none;
    }

    #countries_content{
        display: none;
    }

    #cities_content{
        display: none;
    }

</style>