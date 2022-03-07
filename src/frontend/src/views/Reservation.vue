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
            <button v-if="reservationState == 'BOOKED'" id="start_button" @click="startReservation()">Start Reservation</button>
            <button v-if="reservationState == 'BOOKED'" id="cancel_button" @click="cancelReservation()">Cancel Reservation</button>

            <button v-if="reservationState == 'IN_PROGRESS'" id="complete_button" @click="completeReservation()">Complete Reservation</button>
            <button v-if="reservationState == 'IN_PROGRESS'" id="cancel_button" @click="cancelReservation()">Cancel Reservation</button>
            <div id="navbar">
                <router-link to="/admin/reservations">Reservations</router-link> /
                <span> Reservation #{{$route.params.id}}</span>
            </div>
            <div id="title">Reservation #{{$route.params.id}}</div>
            <div class="details_box" id="reservation_details_box">
                <div id="title_box">Reservation Details</div>
                <div class="details_content">
                    <div class="details_row">
                        <div class="details_field_name">Reservation ID</div>
                        <div class="details_field_value" id="reservation_id"></div>
                    </div>
                    <div class="details_row">
                        <div class="details_field_name">No. of Guests</div>
                        <div class="details_field_value" id="no_of_guests"></div>
                    </div>
                    <div class="details_row">
                        <div class="details_field_name">Start Date</div>
                        <div class="details_field_value" id="start_date"></div>
                    </div>
                    <div class="details_row">
                        <div class="details_field_name">End Date</div>
                        <div class="details_field_value" id="end_date"></div>
                    </div>
                    <div class="details_row">
                        <div class="details_field_name">Payment Method</div>
                        <div class="details_field_value" id="payment_method"></div>
                    </div>
                    <div class="details_row">
                        <div class="details_field_name">Reservation State</div>
                        <div class="details_field_value" id="reservation_state"></div>
                    </div>
                </div>
                
            </div>
            <div class="details_box" id="guest_details_box">
                <div id="title_box">Guest Details</div>
                <div class="details_content">
                    <div class="details_row">
                        <div class="details_field_name">Guest ID</div>
                        <div class="details_field_value" id="guest_id"></div>
                    </div>
                    <div class="details_row">
                        <div class="details_field_name">First Name</div>
                        <input type="text" class="details_field_value" id="first_name" v-if="editMode" v-model="editGuestDetails.firstName">
                        <div class="details_field_value" id="first_name" v-else></div>
                    </div>
                    <div class="details_row">
                        <div class="details_field_name">Last Name</div>
                        <input type="text" class="details_field_value" id="last_name" v-if="editMode" v-model="editGuestDetails.lastName">
                        <div class="details_field_value" id="last_name" v-else></div>
                    </div>
                    <div class="details_row">
                        <div class="details_field_name">Date of Birth</div>
                        <input type="text" class="details_field_value" id="date_of_birth" v-if="editMode" v-model="editGuestDetails.dateOfBirth">
                        <div class="details_field_value" id="date_of_birth" v-else></div>
                    </div>
                    <div class="details_row">
                        <div class="details_field_name">Telephone</div>
                        <input type="text" class="details_field_value" id="telephone" v-if="editMode" v-model="editGuestDetails.telephone">
                        <!-- <input type="text" class="details_field_value" name="telephone" maxlength="11" v-if="editMode" oninput="this.value = this.value.replace(/[^0-9]/g, '');"><br>  -->
                        <div class="details_field_value" id="telephone" v-else></div>
                    </div>
                    <div class="details_row">
                        <div class="details_field_name">Address</div>
                        <input type="text" class="details_field_value" id="address" v-if="editMode" v-model="editGuestDetails.address">
                        <div class="details_field_value" id="address" v-else></div>
                    </div>
                    <button id="button_confirm" v-if="editMode" @click="editGuestConfirm()">Confirm</button>
                    <button id="button" v-else @click="editGuest()">Edit</button>
                </div>
            </div>
            <div class="details_box" id="rooms_details_box">
                <div id="title_box">Rooms</div>
                <ag-grid-vue
                  class="ag-theme-alpine" id="table_rooms"
                  :columnDefs="columnDefsRooms"
                  :rowData="rowDataRooms.value"
                  @rowClicked="onRowClickedRooms"
                  >
                </ag-grid-vue>
            </div>
            <div class="details_box" id="orders_details_box">
                <div id="title_box">Orders</div>
                <ag-grid-vue
                  class="ag-theme-alpine" id="table_orders"
                  :columnDefs="columnDefsOrders"
                  :rowData="rowDataOrders.value"
                  @rowClicked="onRowClickedOrders">
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
            <form class="order_list_row" :key="item" v-for="(item, index) in orderItems" name=index>
                <select name="category" id="category" v-model=orderItems[index].category @change=getOrderItemPrice(index)>
                    <option v-for="category in items.map(a => a.category)" :key=category>{{ category }}</option>
                </select>
                <select name="productName" id="product" v-model=orderItems[index].productName @change=getOrderItemPrice(index)>
                    <option v-for="product in orderItems[index].category.length != 0 ? (this.items.find(o => o.category === orderItems[index].category).products.map(a => a.productName)) : []" :key=product>{{product}}</option>
                </select>
                <input id="qty" type="number" name="productQuantity" min=1 v-model=orderItems[index].productQuantity @change=getOrderItemPrice(index)>
                <input id="time" type="datetime-local" name="serviceTime" v-model=orderItems[index].serviceTime>
                <input type="number" id="subtotal" name="subtotalPrice" v-model=orderItems[index].subtotalPrice readonly>
                <div id="subtotal" style="font-size: 20px">${{orderItems[index].subtotalPrice}}</div>
                <img id="remove_order_icon" src="../assets/remove.png" @click="removeOrder(index)"/>
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
    import {reactive} from "vue";
    export default {
        name: "Reservation",
        components: {
    AgGridVue,
    },
    data(){
        return{
            editMode: false,
            popUpWindow: false,
            orderItems: [
                {
                    category: "",
                    productName: "",
                    productQuantity: 1,
                    serviceTime: "",
                    subtotalPrice: 0,
                },
            ],
            items: [],
            itemsFetched: [],
            selectedCategories: [],
            selectedProducts: [],
            selectedQty: [],
            selectedItems: [{
                category: "",
                productName: "",
                productQuantity: 1,
                serviceTime: "",
            }],
            reservationId: "",
            reservationState: "",
            rowDataRooms: reactive([]),
            rowDataOrders: reactive([]),
            columnDefsRooms: [
                { field: 'roomNumber', sortable: true, filter: true, type: 'rightAligned', width: 150 },
                { field: 'roomName', sortable: true, filter: true, type: 'rightAligned', width: 200 },
                { field: 'pricePerNight', sortable: true, filter: true, type: 'rightAligned',width: 150 },
                { field: 'totalPrice', sortable: true, filter: true, type: 'rightAligned', width: 125 },
                { field: 'singleBeds', sortable: true, filter: true, type: 'rightAligned', width: 125 },
                { field: 'doubleBeds', sortable: true, filter: true, type: 'rightAligned', width: 140 },
            ],
            columnDefsOrders: [
                { field: 'orderID', sortable: true, filter: true, width: 150 },
                { field: 'totalPrice', sortable: true, filter: true, width: 150 },
                { field: 'timeOfOrder', sortable: true, filter: true, width: 200 },
            ],
            reservationDetails: {},
            guestDetails: {},
            editGuestDetails: {
                guestID: "",
                firstName: "",
                lastName: "",
                dateOfBirth: "",
                telephone: "",
                address: "",
            },
        }
    },
    created() {
        this.rowSelection = 'single';
        this.reservationId = this.$route.params.id;
        console.log(this.reservationId);

        // FETCH RESERVATION DETAILS AT LOAD
        fetch('/api/rsv/all?' + new URLSearchParams({
                reservationID: this.reservationId,
        })).then(response => response.json()).then(data => {
            this.reservationDetails = data[0];
            console.log(this.reservationDetails);
            document.querySelector("#reservation_id").textContent = this.reservationDetails.reservationID;
            document.querySelector("#no_of_guests").textContent = this.reservationDetails.numberOfGuests;
            document.querySelector("#start_date").textContent = this.reservationDetails.startDate.split('T')[0];
            document.querySelector("#end_date").textContent = this.reservationDetails.endDate.split('T')[0];
            document.querySelector("#payment_method").textContent = this.reservationDetails.paymentMethod;
            document.querySelector("#reservation_state").textContent = this.reservationDetails.reservationState;
            this.reservationState = this.reservationDetails.reservationState;
        });

        // FETCH GUEST DETAILS AT LOAD
        fetch('/api/guest/details?' + new URLSearchParams({
                reservationID: this.reservationId,
        })).then(response => response.json()).then(data => {
            this.guestDetails = data;
            document.querySelector("#guest_id").textContent = this.guestDetails.guestID;
            document.querySelector("#first_name").textContent = this.guestDetails.firstName;
            document.querySelector("#last_name").textContent = this.guestDetails.lastName;
            document.querySelector("#date_of_birth").textContent = this.guestDetails.dateOfBirth.split('T')[0];
            document.querySelector("#telephone").textContent = this.guestDetails.telephone;

            var address = this.guestDetails.streetName + " " + this.guestDetails.buildingNumber;
            if (this.guestDetails.apartmentNumber != null){
                address += " / " + this.guestDetails.apartmentNumber;
            }
            address += ", " + this.guestDetails.city + ", " + this.guestDetails.country
            document.querySelector("#address").textContent = address;
            console.log(this.guestDetails);
        });

        // FETCH ROOMS AT LOAD
        fetch('/api/roominrsv/all?' + new URLSearchParams({
                reservationID: this.reservationId,
        })).then(result => result.json()).then(remoteRowData => this.rowDataRooms.value = remoteRowData);

        // FETCH ORDERS AT LOAD
        fetch('/api/order/all?' + new URLSearchParams({
            reservationID: this.reservationId,
        })) .then(result => result.json()).then(remoteRowData => this.rowDataOrders.value = remoteRowData);

        // FETCH ALL CATEGORIES AND PRODUCTS AT LOAD
        fetch("/api/cat/products", {
            method: "GET",
            headers: {'Content-Type': 'application/json'},
        }).then(response => response.json()).then(data => {
            this.items = data;
            console.log(this.items);
        });
        
    },
    methods: {
        editGuest(){
            this.editMode = true;
            // FETCH GUEST DETAILS AT LOAD
            fetch('/api/guest/details?' + new URLSearchParams({
                    reservationID: this.reservationId,
            })).then(response => response.json()).then(data => {
                this.guestDetails = data;
                this.editGuestDetails = this.guestDetails;

                var address = this.guestDetails.streetName + " " + this.guestDetails.buildingNumber;
                if (this.guestDetails.apartmentNumber != null){
                    address += " / " + this.guestDetails.apartmentNumber;
                }
                address += ", " + this.guestDetails.city + ", " + this.guestDetails.country
                document.querySelector("#address").value = address;
                console.log(this.guestDetails);
            });
        },
        editGuestConfirm(){
            console.log(this.editGuestDetails);
            var address = this.editGuestDetails.streetName + " " + this.editGuestDetails.buildingNumber;
            if (this.editGuestDetails.apartmentNumber != null){
                address += " / " + this.editGuestDetails.apartmentNumber;
            }
            address += ", " + this.editGuestDetails.city + ", " + this.editGuestDetails.country
            this.editGuestDetails.country = address.split(',')[2];
            this.editGuestDetails.city = address.split(',')[1];
            this.editGuestDetails.streetName = address.split(',')[0].split(' ')[0];
            this.editGuestDetails.buildingNumber = address.split(',')[0].split(' ')[1].split('/')[0];
            this.editGuestDetails.apartmentNumber = null;
            this.editMode = false;
            console.log(this.editGuestDetails);
            fetch("/api/guest/change", {
                method: "POST",
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(this.editGuestDetails)
            }).then(data => {
                console.log(data);
                window.location.reload();
            })

            fetch('/api/guest/details?' + new URLSearchParams({
                reservationID: this.reservationId,
            })).then(response => response.json()).then(data => {
            this.guestDetails = data;
                document.querySelector("#guest_id").textContent = this.guestDetails.guestID;
                document.querySelector("#first_name").textContent = this.guestDetails.firstName;
                document.querySelector("#last_name").textContent = this.guestDetails.lastName;
                document.querySelector("#date_of_birth").textContent = this.guestDetails.dateOfBirth.split('T')[0];
                document.querySelector("#telephone").textContent = this.guestDetails.telephone;

            var address = this.guestDetails.streetName + " " + this.guestDetails.buildingNumber;
            if (this.guestDetails.apartmentNumber != null){
                address += " / " + this.guestDetails.apartmentNumber;
            }
            address += ", " + this.guestDetails.city + ", " + this.guestDetails.country
            document.querySelector("#address").textContent = address;
            console.log(this.guestDetails);
            });
        },
        onSelectionChanged() {
        const selectedRows = this.rowData.getSelectedRows();
        console.log(selectedRows.length === 1 ? selectedRows[0].reservation_id : '');
        },
        addOrderItem(){
            this.orderItems.push({
                category: "",
                productName: "",
                productQuantity: 1,
                serviceTime: "",
                subtotalPrice: 0,
            });
            console.log('categories: ', this.selectedCategories);
            console.log('products: ', this.selectedProducts);
            console.log('items: ', this.selectedItems);
        },
        emptyOrderItems(){
            this.orderItems = [
                {
                    category: "",
                    productName: "",
                    productQuantity: 0,
                    serviceTime: "",
                    subtotalPrice: 0,
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
                + String((currentdate.getMonth()+1)).padStart(2, '0')  + "-" 
                + String(currentdate.getDate()).padStart(2, '0') + "T"
                + String(currentdate.getHours()).padStart(2, '0') + ":"  
                + String(currentdate.getMinutes()).padStart(2, '0')

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
            console.log(orderItems.map(el => Object.values(el).filter(el => el.length != 0)).filter(el => el.length != 4).length);
            console.log(orderItems);
            if (orderItems.map(el => Object.values(el).filter(el => el.length != 0)).filter(el => el.length != 5).length === 0){
                
                var orderId;
                var orderJson = {};
                console.log(JSON.stringify(objectEmptyOrder));

                fetch("/api/order/add", {
                    method: "POST",
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify(objectEmptyOrder)
                }).then(response => response.json()).then(data => {
                    orderId = data;
                    console.log(orderId);
                    console.log('orderItems: ',orderItems);
                    orderJson.orderID = orderId;
                    orderJson.products = orderItems;
                    console.log(JSON.stringify(orderJson));
                    fetch("/api/prodinord/add", {
                        method: "POST",
                        headers: {'Content-Type': 'application/json'},
                        body: JSON.stringify(orderJson)
                    }).then(data => {
                        console.log(data);
                        // FETCH ORDERS ON ORDER ADD
                        fetch('/api/order/all?' + new URLSearchParams({
                        reservationID: this.reservationId,
                        })).then(result => result.json()).then(remoteRowData => this.rowDataOrders.value = remoteRowData);
                    });
                });
                this.closeOrderWindow
            }
            else{
                alert("You have unfinished order items.");
            }
        },
        cancelReservation(){
            if (confirm('Are you sure you want to CANCEL THIS RESERVATION? \nThis action is irreversible!')) {
                fetch('/api/rsv/state?' + new URLSearchParams({
                    reservationID: this.reservationId,
                    stateName: "CANCELED",
                }),{
                    method: "POST"}).then(data => {
                    console.log(data);
                    window.location.reload();
                });
                console.log('canceled');
            }
        },
        startReservation(){
            if (confirm('Are you sure you want to START THIS RESERVATION? \nThis action is irreversible!')) {
                fetch('/api/rsv/state?' + new URLSearchParams({
                    reservationID: this.reservationId,
                    stateName: "IN_PROGRESS",
                }),{
                    method: "POST"}).then(data => {
                    console.log(data);
                    window.location.reload();
                });
            }
        },
        completeReservation(){
            if (confirm('Are you sure you want to COMPLETE THIS RESERVATION? \nThis action is irreversible!')) {
                fetch('/api/rsv/state?' + new URLSearchParams({
                    reservationID: this.reservationId,
                    stateName: "COMPLETED",
                }),{
                    method: "POST"}).then(data => {
                    console.log(data);
                    window.location.reload();
                });
                console.log('completed');
            }
        },
        onRowClickedRooms(params) {
            console.log(params.node.data.roomNumber);
            this.$router.push({ path: '/admin/rooms/' + params.node.data.roomNumber });
        },
        onRowClickedOrders(params) {
            console.log(params.node.data.orderID);
            this.$router.push({ path: '/admin/orders/' + params.node.data.orderID });
        },
        removeOrder(index){
            if (this.orderItems.length > 1){
                this.orderItems.splice(index, 1);
            }
        },
        getOrderItemPrice(index){
            if(this.orderItems[index].category.length > 0 && this.orderItems[index].productName.length > 0){
                this.orderItems[index].subtotalPrice = parseInt(this.items.find(o => o.category === this.orderItems[index].category).products.find(o => o.productName === this.orderItems[index].productName).productPrice * this.orderItems[index].productQuantity);
            }
            else{
                this.orderItems[index].subtotalPrice = 0;
            }
        },

  },
}
</script>

<style scoped>

    input[type="number"]#subtotal::-webkit-outer-spin-button,
    input[type="number"]#subtotal::-webkit-inner-spin-button {
        -webkit-appearance: none;
        margin: 0;
    }
    input[type="number"]#subtotal {
        -moz-appearance: textfield;
        display: none;
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

    .details_content{
        grid-area: 2/1/3/2;
    }

    #reservation_details_box{
        grid-area: 3/2/4/3;
        grid-template-rows: 50px 1fr;
        grid-template-columns: 1fr;
    }

    #guest_details_box{
        grid-area: 3/3/4/4;
        grid-template-rows: 50px 1fr;
        grid-template-columns: 1fr;
    }

    .details_text{
        font-size: 14px;
        
    }

    .details_row{
        display: grid;
        grid-template-rows: 1fr;
        grid-template-columns: 1fr 1.5fr;
        height: 36px;
        align-items: start;
    }

    .details_row:first-child{
        margin-top: 20px;
    }

    .details_field_name{
        grid-column: 1/2;
        font-weight: 600;
        text-align: right;
        font-size: 14px;
        justify-self: end;
        margin-right: 20px;
    }

    .details_field_value{
        grid-column: 2/3;
        text-align: left;
        margin-right: 25px;
        font-size: 14px;
        justify-self: start;
        margin-left: 20px;
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

    #button_confirm{
        width: 120px;
        height: 35px;
        grid-area: -2/2/-1/3;
        background: #35d38c;
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
        grid-template-columns: 210px 210px 50px 210px 110px 100px;
        grid-gap: 20px;
        justify-items: center;
        margin-bottom: 15px;
        text-align: left;
        line-height: 36px;
    }

    #remove_order_icon{
        grid-area: 1/6/2/7; 
        width: 32px;
        height: auto;   
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

    input#subtotal{
        grid-area: 1/5/2/6;
        width: 100%;
        padding-left: 35px;
        border: none;
        margin-left: 10px;
        font-size: 16px;
    }

    input#subtotal:focus{
        outline: none;
    }

    hr{
        margin: 0;
        margin-bottom: 20px;
        height: 0px;
        border: none;
        border-top: 1px solid rgba(40,50,63,0.3);
    }

    #cancel_button{
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

    #start_button{
        background: #0BEA86;
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
        margin-right: 225px;
    }

    #complete_button{
        background: #1993f0;
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
        margin-right: 225px;
    }

</style>