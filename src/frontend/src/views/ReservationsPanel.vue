<template>
    <div id="full">
      <div id="left_bar">
      </div>
          <div id="content">
              <div id="navbar">
                  <span> Reservations</span> /
              </div>
              <div id="title">Reservations</div>
              <ag-grid-vue
                  class="ag-theme-alpine" id="table"
                  :columnDefs="columnDefs"
                  :rowData="rowData.value"
                  @rowClicked="onRowClicked"
              >
              </ag-grid-vue>
          </div>
    </div>
</template>

<script>
import "ag-grid-community/dist/styles/ag-grid.css";
import "ag-grid-community/dist/styles/ag-theme-alpine.css";
import { AgGridVue } from "ag-grid-vue3";
import {reactive, onMounted} from "vue";


export default {
  name: "ReservationsPanel",
  components: {
    AgGridVue,
  },
  setup() {
    let rowData = reactive([]);


    onMounted(() => {
        fetch('/api/reservations')
          .then(result => result.json())
          .then(remoteRowData => rowData.value = remoteRowData);
    })

    return {
        columnDefs: [
        { headerName: "Reservation ID", field: "RESERVATION_ID",  resizable: true, type: 'rightAligned', width: 150},
        { headerName: "Guest ID", field: "GUEST_ID" , resizable: true, type: 'rightAligned', width: 150},
        { headerName: "No. of Guests", field: "NUMBER_OF_GUESTS" , resizable: true, type: 'rightAligned', width: 150},
        { headerName: "Start Date", field: "START_DATE" , resizable: true, type: 'rightAligned',width: 200},
        { headerName: "End Date", field: "END_DATE" , resizable: true, type: 'rightAligned', width: 200},
        { headerName: "Price", field: "PRICE" , resizable: true, type: 'rightAligned', width: 150},
        { headerName: "Payment Method", field: "PAYMENT_METHOD_NAME" , resizable: true, type: 'rightAligned', width: 200},
        { headerName: "State", field: "RESERVATION_STATE_NAME" , resizable: true, type: 'rightAligned', width: 200},
      ],
      rowData
    };
    // return {
    //   columnDefs: [
    //     { headerName: "Reservation ID", field: "reservation_id",  resizable: true, type: 'rightAligned', width: 150, cellRenderer: 'btnCellRenderer',
    //     cellRendererParams: {
    //       clicked: function(field) {
    //         alert(`${field} was clicked`);
    //       }
    //     },},
    //     { headerName: "Guest ID", field: "guest_id" , resizable: true, type: 'rightAligned', width: 150},
    //     { headerName: "No. of Guests", field: "no_guests" , resizable: true, type: 'rightAligned', width: 150},
    //     { headerName: "Start Date", field: "start_date" , resizable: true, type: 'rightAligned',width: 200},
    //     { headerName: "End Date", field: "end_date" , resizable: true, type: 'rightAligned', width: 200},
    //     { headerName: "Price", field: "price" , resizable: true, type: 'rightAligned', width: 150},
    //     { headerName: "Payment Method", field: "payment_method" , resizable: true, type: 'rightAligned', width: 200},
    //     { headerName: "State", field: "state" , resizable: true, type: 'rightAligned', width: 200},
    //   ],
      // rowData: [
      //   { reservation_id: "1", guest_id: "1", no_guests: 2, start_date: "27-12-2021", end_date: "31-12-2021",
      //   price: "$250", payment_method: "Cash", state: "Registered"},
      //   { reservation_id: "2", guest_id: "1", no_guests: 2, start_date: "27-12-2021", end_date: "31-12-2021",
      //   price: "$250", payment_method: "Cash", state: "Registered"},
      //   { reservation_id: "3", guest_id: "1", no_guests: 2, start_date: "27-12-2021", end_date: "31-12-2021",
      //   price: "$250", payment_method: "Cash", state: "Registered"},
      //   { reservation_id: "4", guest_id: "1", no_guests: 2, start_date: "27-12-2021", end_date: "31-12-2021",
      //   price: "$250", payment_method: "Cash", state: "Registered"},
      //   { reservation_id: "5", guest_id: "1", no_guests: 2, start_date: "27-12-2021", end_date: "31-12-2021",
      //   price: "$250", payment_method: "Cash", state: "Registered"},
      //   { reservation_id: "6", guest_id: "1", no_guests: 2, start_date: "27-12-2021", end_date: "31-12-2021",
      //   price: "$250", payment_method: "Cash", state: "Registered"},
      //   { reservation_id: "7", guest_id: "1", no_guests: 2, start_date: "27-12-2021", end_date: "31-12-2021",
      //   price: "$250", payment_method: "Cash", state: "Registered"},
      //   { reservation_id: "8", guest_id: "1", no_guests: 2, start_date: "27-12-2021", end_date: "31-12-2021",
      //   price: "$250", payment_method: "Cash", state: "Registered"},
      //   { reservation_id: "9", guest_id: "1", no_guests: 2, start_date: "27-12-2021", end_date: "31-12-2021",
      //   price: "$250", payment_method: "Cash", state: "Registered"},
      //   { reservation_id: "10", guest_id: "1", no_guests: 2, start_date: "27-12-2021", end_date: "31-12-2021",
      //   price: "$250", payment_method: "Cash", state: "Registered"},
      //   { reservation_id: "11", guest_id: "1", no_guests: 2, start_date: "27-12-2021", end_date: "31-12-2021",
      //   price: "$250", payment_method: "Cash", state: "Registered"},
      //   { reservation_id: "12", guest_id: "1", no_guests: 2, start_date: "27-12-2021", end_date: "31-12-2021",
      //   price: "$250", payment_method: "Cash", state: "Registered"},
      //   { reservation_id: "13", guest_id: "1", no_guests: 2, start_date: "27-12-2021", end_date: "31-12-2021",
      //   price: "$250", payment_method: "Cash", state: "Registered"},
      //   { reservation_id: "14", guest_id: "1", no_guests: 2, start_date: "27-12-2021", end_date: "31-12-2021",
      //   price: "$250", payment_method: "Cash", state: "Registered"},
      //   { reservation_id: "15", guest_id: "1", no_guests: 2, start_date: "27-12-2021", end_date: "31-12-2021",
      //   price: "$250", payment_method: "Cash", state: "Registered"},
      //   { reservation_id: "16", guest_id: "1", no_guests: 2, start_date: "27-12-2021", end_date: "31-12-2021",
      //   price: "$250", payment_method: "Cash", state: "Registered"},
      // ],
    // };
  },
  created() {
    this.rowSelection = 'single';
  },
  methods: {
    onSelectionChanged() {
      const selectedRows = this.rowData.getSelectedRows();
      console.log(selectedRows.length === 1 ? selectedRows[0].reservation_id : '');
    },
    onRowClicked(params) {
      console.log(params);
      console.log(params.node.data);
    }
  },
};
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
        grid-template-columns: 50px 400px 400px 600px 1fr;
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

    #table{
        background: #EBEBEB;
        grid-area: 3/2/5/5;
    }

</style>