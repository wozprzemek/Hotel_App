<template>
    <div id="full">
        <div id="left_bar">
        </div>
        <div id="content">
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

                <button id="button">Edit</button>
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
                <button id="button">Edit</button>
            </div>
            <div class="details_box" id="rooms_details_box">
                <div id="title_box">Rooms</div>
                <ag-grid-vue
                  class="ag-theme-alpine" id="table_rooms"
                  :columnDefs="columnDefs"
                  :rowData="rowData.value">
                </ag-grid-vue>
                <button id="button_add">Add</button>
            </div>
            <div class="details_box" id="orders_details_box">
                <div id="title_box">Orders</div>
                <ag-grid-vue
                  class="ag-theme-alpine" id="table_orders"
                  :columnDefs="columnDefs"
                  :rowData="rowData.value">
                </ag-grid-vue>
                <button id="button_add">Add</button>
            </div>
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
  setup() {
    let rowData = reactive([]);

    onMounted(() => {
        fetch('https://www.ag-grid.com/example-assets/small-row-data.json')
          .then(result => result.json())
          .then(remoteRowData => rowData.value = remoteRowData);

          fetch("https://api.npoint.io/4954ac84ccd9bb0388a6")
            .then(res => res.json())
            .then(data => console.log(data));
    })

    return {
        columnDefs: [
            { field: 'make', sortable: true, filter: true, width: 200 },
            { field: 'model', sortable: true, filter: true, width: 200 },
            { field: 'price', sortable: true, filter: true, width: 150 }
      ],
      rowData
    };
  },
  created() {
    this.rowSelection = 'single';
  },
  methods: {
    onSelectionChanged() {
      const selectedRows = this.rowData.getSelectedRows();
      console.log(selectedRows.length === 1 ? selectedRows[0].reservation_id : '');
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
        grid-area: 2/1/-2/-1;
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
        background-color: #C3C8D5;
        color: #080A0D;
        font-size: 18px;
        font-weight: 300;
        text-align: left;
        line-height: 55px;
        padding-left: 15px;
        grid-area: 1/1/1/-1;
    }
</style>