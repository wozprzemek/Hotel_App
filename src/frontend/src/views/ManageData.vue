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
                <div id="order_window_title"><span>New {{currentItem}}</span></div>
            </div>
            <img id="order_window_exit_icon" src="../assets/delete.png" @click="popUpWindow = false; closePopUpWindow()"/>
        </div>
        <!-- CATEGORIES -->
        <div class="pop_up_window_content" id="categories_content">
            <form class="new_item_row" id="category_item_row">
                <label for="category" id="category">Category Name</label>
                <br>
                <input name="category" id="category" type="text">
                <button class="pop_up_window_add_edit_button" type="button" @click="addCategory()">Add</button>
            </form>
            <ag-grid-vue
                class="ag-theme-alpine" id="pop_up_window_table"
                :columnDefs="columnDefsCategories"
                :rowData="rowDataCategories.value"
                @rowClicked="onRowClickedOrders">
            </ag-grid-vue>
        </div>

        <!-- PRODUCTS -->
        <div class="pop_up_window_content" id="products_content">
            <form class="new_item_row"  id="product_item_row">
                <label for="category" id="category">Category Name</label>
                <br>
                <select name="categoryName" id="category">
                    <option v-for="category in typeof rowDataCategories.value != 'undefined' ? rowDataCategories.value.map(el => el.categoryName): []" :key=category>{{ category }}</option>
                </select>
                <label for="productName" id="product">Product Name</label>
                <br>
                <input name="productName" id="product" type="text">
                <label for="productPrice" id="product_price">Product Name</label>
                <br>
                <input id="price" type="number" name="productPrice" min=1>
                <button class="pop_up_window_add_edit_button" type="button" @click="addProduct()">Add</button>
            </form>
            <ag-grid-vue
                class="ag-theme-alpine" id="pop_up_window_table"
                :columnDefs="columnDefsProducts"
                :rowData="rowDataProducts.value"
                @rowClicked="onRowClickedOrders">
            </ag-grid-vue>
        </div>

        <!-- PAYMENT METHODS -->
        <div class="pop_up_window_content" id="payments_content">
            <form class="new_item_row" id="payment_item_row">
                <label for="paymentMethodName" id="payment">Payment Method Name</label>
                <br>
                <input name="paymentMethodName" id="payment" type="text">
                <button class="pop_up_window_add_edit_button" type="button" @click="addPayment()">Add</button>
            </form>
            <ag-grid-vue
                class="ag-theme-alpine" id="pop_up_window_table"
                :columnDefs="columnDefsPayments"
                :rowData="rowDataPayments.value"
                @rowClicked="onRowClickedOrders">
            </ag-grid-vue>
        </div>

        <!-- ADMINS -->
        <div class="pop_up_window_content" id="admins_content">
            <form class="new_item_row" id="admin_item_row">
                <label for="login" id="login">Login</label>
                <br>
                <input name="login" id="login" type="text">
                <label for="password" id="password">Password</label>
                <br>
                <input name="password" id="password" type="text">
                <label for="firstName" id="fname">First Name</label>
                <br>
                <input name="firstName" id="fname" type="text">
                <label for="lastName" id="lname">Last Name</label>
                <br>
                <input name="lastName" id="lname" type="text">
                <button class="pop_up_window_add_edit_button" type="button" @click="addAdmin()">Add</button>
            </form>
            <ag-grid-vue
                class="ag-theme-alpine" id="pop_up_window_table"
                :columnDefs="columnDefsAdmins"
                :rowData="rowDataAdmins.value"
                @rowClicked="onRowClickedOrders">
            </ag-grid-vue>
        </div>
        
        <!-- COUNTRIES -->
        <div class="pop_up_window_content" id="countries_content">
            <form class="new_item_row" id="country_item_row">
                <label for="countryName" id="country">Country Name</label>
                <br>
                <input name="countryName" id="country" type="text">
                <button class="pop_up_window_add_edit_button" type="button" @click="addCountry()">Add</button>
            </form>
            <ag-grid-vue
                class="ag-theme-alpine" id="pop_up_window_table"
                :columnDefs="columnDefsCountries"
                :rowData="rowDataCountries.value"
                @rowClicked="onRowClickedOrders">
            </ag-grid-vue>
        </div>
        
        <!-- CITIES -->
        <div class="pop_up_window_content" id="cities_content">
            <form class="new_item_row" id="city_item_row">
                <label for="countryName" id="country">Country Name</label>
                <br>
                <select name="countryName" id="country">
                    <option v-for="country in typeof rowDataCountries.value != 'undefined' ? rowDataCountries.value.map(el => el.countryName): []" :key=country>{{ country }}</option>
                </select>
                <label for="cityName" id="city">City Name</label>
                <br>
                <input name="cityName" id="city" type="text">
                <button class="pop_up_window_add_edit_button" type="button" @click="addCity()">Add</button>
            </form>
            <ag-grid-vue
                class="ag-theme-alpine" id="pop_up_window_table"
                :columnDefs="columnDefsCities"
                :rowData="rowDataCities.value"
                @rowClicked="onRowClickedOrders">
            </ag-grid-vue>
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
            rowDataCategories: reactive([]),
            columnDefsCategories: [
                { headerName: "Category ID", field: "id",  resizable: true, type: 'rightAligned', width: 130},
                { headerName: "Category Name", field: "categoryName" , resizable: true, type: 'rightAligned', width: 200},
                { headerName: "Start Serving Time", field: "startServingTime" , resizable: true, type: 'rightAligned', width: 200},
                { headerName: "End Serving Time", field: "endServingTime" , resizable: true, type: 'rightAligned',width: 200},
            ],
            rowDataProducts: reactive([]),
            columnDefsProducts: [
                { headerName: "Product ID", field: "id",  resizable: true, type: 'rightAligned', width: 130},
                { headerName: "Product Name", field: "productName" , resizable: true, type: 'rightAligned', width: 200},
                { headerName: "Product Price", field: "productPrice" , resizable: true, type: 'rightAligned', width: 200},
                { headerName: "Category Name", field: "categoryName" , resizable: true, type: 'rightAligned',width: 200},
            ],
            rowDataPayments: reactive([]),
            columnDefsPayments: [
                { headerName: "Payment Method ID", field: "id",  resizable: true, type: 'rightAligned', width: 130},
                { headerName: "Payment Method Name", field: "paymentMethodName" , resizable: true, type: 'rightAligned', width: 200},
            ],
            rowDataAdmins: reactive([]),
            columnDefsAdmins: [
                { headerName: "Login", field: "login",  resizable: true, type: 'rightAligned', width: 130},
                { headerName: "Password", field: "password" , resizable: true, type: 'rightAligned', width: 200},
                { headerName: "First Name", field: "firstName" , resizable: true, type: 'rightAligned', width: 200},
                { headerName: "Last Name", field: "lastName" , resizable: true, type: 'rightAligned',width: 200},
                { headerName: "Last Login", field: "lastLogin" , resizable: true, type: 'rightAligned',width: 200},
            ],
            rowDataCountries: reactive([]),
            columnDefsCountries: [
                { headerName: "Country ID", field: "countryID",  resizable: true, type: 'rightAligned', width: 130},
                { headerName: "Country Name", field: "countryName" , resizable: true, type: 'rightAligned', width: 200},
            ],
            rowDataCities: reactive([]),
            columnDefsCities: [
                { headerName: "City ID", field: "id",  resizable: true, type: 'rightAligned', width: 130},
                { headerName: "City Name", field: "cityName" , resizable: true, type: 'rightAligned', width: 200},
                { headerName: "Country Name", field: "countryName" , resizable: true, type: 'rightAligned', width: 200},
            ],
            reservationDetails: {},
            roomDetails: {},
            hasReservation: true,
            currentItem: "",
        }
    },
    created() {
        this.rowSelection = 'single';
        this.roomNumber = this.$route.params.id;
    },
    methods: {
        onSelectionChanged() {
        const selectedRows = this.rowData.getSelectedRows();
        console.log(selectedRows.length === 1 ? selectedRows[0].reservation_id : '');
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
        showCategoriesContent(){
            this.currentItem = 'Category';
            console.log('aaa');
            document.querySelector('#categories_content').style.display = "grid";
            fetch('/api/cat/all').then(result => result.json()).then(remoteRowData => this.rowDataCategories.value = remoteRowData);
        },
        showProductsContent(){
            this.currentItem = 'Product';
            document.querySelector('#products_content').style.display = "grid";
            fetch('/api/cat/all').then(result => result.json()).then(
                remoteRowData => this.rowDataCategories.value = remoteRowData);
            fetch('/api/product/all').then(result => result.json()).then(remoteRowData => this.rowDataProducts.value = remoteRowData);
            
        },
        showPaymentContent(){
            this.currentItem = 'Payment Method';
            document.querySelector('#payments_content').style.display = "grid";
            fetch('/api/pay/details').then(result => result.json()).then(remoteRowData => this.rowDataPayments.value = remoteRowData);
        },
        showAdminsContent(){
            this.currentItem = 'Admin';
            document.querySelector('#admins_content').style.display = "grid";
            fetch('/api/admin/all').then(result => result.json()).then(remoteRowData => this.rowDataAdmins.value = remoteRowData);
        },
        showCountriesContent(){
            this.currentItem = 'Country';
            document.querySelector('#countries_content').style.display = "grid";
            fetch('/api/country/details').then(result => result.json()).then(remoteRowData => this.rowDataCountries.value = remoteRowData);
        },
        showCitiesContent(){
            this.currentItem = 'City';
            document.querySelector('#cities_content').style.display = "grid";
            fetch('/api/city/all').then(result => result.json()).then(remoteRowData => this.rowDataCities.value = remoteRowData);
        },
        addCategory(){
            var formData = new FormData(document.querySelector("#category_item_row"));
                var object = {};
                formData.forEach((value, key) => object[key] = value);
                console.log(object);
            fetch('/api/cat/add?' + new URLSearchParams(object), {
                method: "POST"}).then(data => {
                console.log(data);
                fetch('/api/cat/all').then(result => result.json()).then(remoteRowData => this.rowDataCategories.value = remoteRowData);
            });
        },
        addProduct(){
            var formData = new FormData(document.querySelector("#product_item_row"));
                var object = {};
                formData.forEach((value, key) => object[key] = value);
                console.log(object);
            fetch('/api/product/add?' + new URLSearchParams(object), {
                method: "POST"}).then(data => {
                console.log(data);
                fetch('/api/product/all').then(result => result.json()).then(remoteRowData => this.rowDataProducts.value = remoteRowData);
            });
        },
        addPayment(){
            var formData = new FormData(document.querySelector("#payment_item_row"));
                var object = {};
                formData.forEach((value, key) => object[key] = value);
                console.log(object);
            fetch('/api/pay/add?' + new URLSearchParams(object), {
                method: "POST"}).then(data => {
                console.log(data);
                fetch('/api/pay/details').then(result => result.json()).then(remoteRowData => this.rowDataPayments.value = remoteRowData);
            });
        },
        addAdmin(){
            var formData = new FormData(document.querySelector("#admin_item_row"));
                var object = {};
                formData.forEach((value, key) => object[key] = value);
                console.log(object);
            fetch('/api/admin/add?' + new URLSearchParams(object), {
                method: "POST"}).then(data => {
                console.log(data);
                fetch('/api/admin/all').then(result => result.json()).then(remoteRowData => this.rowDataAdmins.value = remoteRowData);
            }); 
        },
        addCountry(){
            var formData = new FormData(document.querySelector("#country_item_row"));
                var object = {};
                formData.forEach((value, key) => object[key] = value);
                console.log(object);
            fetch('/api/country/add?' + new URLSearchParams(object), {
                method: "POST"}).then(data => {
                console.log(data);
                fetch('/api/country/details').then(result => result.json()).then(remoteRowData => this.rowDataCountries.value = remoteRowData);
            }); 
        },
        addCity(){
            var formData = new FormData(document.querySelector("#city_item_row"));
                var object = {};
                formData.forEach((value, key) => object[key] = value);
                console.log(object);
            fetch('/api/city/add?' + new URLSearchParams(object), {
                method: "POST"}).then(data => {
                console.log(data);
                fetch('/api/city/all').then(result => result.json()).then(remoteRowData => this.rowDataCities.value = remoteRowData);
            }); 
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

    select{
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
        grid-area: 1/-1/2/-2;
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

    .new_item_row{
        grid-area: 1/2/2/3;
        width: 970px;
        height: 72px;
        display: grid;
        grid-template-rows: 1fr;
        grid-template-columns: 192.5px 192.5px 192.5px 192.5px 1fr;
        column-gap: 20px;
        justify-items: start;
        text-align: left;
        line-height: 36px;
    }

    #category{
        grid-area: 1/1/2/2;
        width: 100%;
        height: 36px;
    }

    #payment{
        grid-area: 1/1/2/2;
        width: 100%;
        height: 36px;
    }

    #country{
        grid-area: 1/1/2/2;
        width: 100%;
        height: 36px;
    }

    #login{
        grid-area: 1/1/2/2;
        width: 100%;
        height: 36px;
    }

    #product{
        grid-area: 1/2/2/3;
        width: 100%;
        height: 36px;
    }

    #city{
        grid-area: 1/2/2/3;
        width: 100%;
        height: 36px;
    }

    #password{
        grid-area: 1/2/2/3;
        width: 100%;
        height: 36px;
    }

    #price{
        grid-area: 1/3/2/4;
        width: 100%;
        height: 36px;
    }

    #fname{
        grid-area: 1/3/2/4;
        width: 100%;
        height: 36px;
    }

    #lname{
        grid-area: 1/4/2/5;
        width: 100%;
        height: 36px;
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