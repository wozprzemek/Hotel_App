<template>
    <div id="full">
        <div id="top">
            <div id="title">Hotel</div>
        </div>
        <div id="bottom">
            <div id="menu">
                <div class="selection_area" id="start_date">
                    <div class="empty_alert" v-if="startDateAlert">Select the start date</div>
                    <div class="selection_text">Start Date</div>
                    <input type="date" :min=addDays(today,1) v-model="startDate" v-on:change="handleDates" class="selection_box" id="start_date_input">
                </div>
                <div class="selection_area" id="end_date">
                    <div class="empty_alert" v-if="endDateAlert">Select the end date</div>
                    <div class="selection_text">End Date</div>
                    <input type="date" :min=addDays(startDate,1) :disabled="startDate == ''" v-model="endDate" class="selection_box" id="end_date_input">
                </div>
                <div class="selection_area" id="rooms">
                    <div class="empty_alert" v-if="roomsAlert">Add at least 1 room</div>
                    <div class="selection_text">Rooms</div>
                    <button class="selection_box" id="room_selection_box" @click="toggleMenu()">{{this.rooms_added.length}} Rooms | {{this.rooms_added.reduce((a,b)=>a+b,0)}} People</button>
                    <div id="room_add_menu">
                        <div id="room_add_list">
                            <div class="add_room_row" :key="room" v-for="(room, index) in rooms_added">
                                <div class="room_number">Room {{index+1}}</div>
                                <input type='number' min=1 v-model="rooms_added[index]" class="guest_number_input">
                                <img class="delete_icon" src="../assets/delete.png" @click="removeRoom(index)"/>
                            </div>
                        </div>
                        <button id="add_room_button" @click="addRoom()">Add Room</button>
                    </div>
                </div>
                <button id="check_button" @click="findRoomsRequest()">Check Availability</button>
            </div>
        </div>
    </div>
    <div v-if="popUpWindow" id="room_selection_window">
        <div id="room_selection_window_title_bar">
                <div id="navbar">
                    <div id="room_selection_window_title" @click="if(personalInfoWindow){personalInfoWindow = false; roomWindow = true}"><span>Rooms</span></div>
                    <div id="room_selection_window_title" v-if="personalInfoWindow"><span>&nbsp;/&nbsp;Personal info</span></div>
                </div>
            <img id="exit_icon" src="../assets/delete.png" @click="popUpWindow = false; roomWindow = false; personalInfoWindow = false"/>
        </div>
        <div id="room_window" v-if="roomWindow">
            <div id="room_configuration_selection_bar">
                <div class="room_configuration" :key="room" v-for="(room, index) in rooms_added" @click="selectedConfiguration=index; selectConfiguration()">Room {{index+1}} | {{room}} Guests</div>
            </div>
            <div id="room_selection_window_room_list">
                <div class="room_container" :key="room" v-for="room in availableRooms(selectedConfiguration)">
                    <img class="room_image" src="../assets/background.jpg"/>
                    <div class="room_name">{{room.roomName}}</div>
                    <div class="room_price">${{room.pricePerNight}}</div>
                    <div class="room_description">Single Beds: {{room.singleBeds}} <br> Double Beds: {{room.doubleBeds}}</div>
                    <button class="room_select_button_active" @click="selectRoom(room, $event)" v-if="isRoomSelected(room)">Selected</button>
                    <button class="room_select_button_inactive" @click="selectRoom(room, $event)" v-else>Select Room</button>
                </div>
            </div>
        </div>

        <div id="personal_info_window" v-if="personalInfoWindow">
            <form id="reservation_form" name="reservation_form">
                <label for="fname">First name</label><br>
                <input type="text" class="form_input" name="fname"><br>
                <label for="lname">Last name</label><br>
                <input type="text" class="form_input" name="lname"><br>
                <label for="birth">Date of Birth</label><br>
                <input type="date" class="form_input" name="birth"><br>
                <label for="country">Country</label><br>
                <input type="text" class="form_input" name="country"><br>
                <label for="city">City</label><br>
                <input type="text" class="form_input" name="city"><br>
                <label for="street">Street</label><br>
                <input type="text" class="form_input" name="street"><br>
                <label for="telephone">Telephone</label><br>
                <input type="text" class="form_input" name="telephone"><br>
                <label for="payment">Payment Method</label><br>
                <select name="payment" class="form_input">
                    <!-- <option v-for="category in items.map(a => a.category)" :key=category>{{ category }}</option>> -->
                </select>
            </form> 
            <div id="reservation_summary">
                <div id="reservation_summary_title">Reservation Summary</div>
                <div id="reservation_info">
                    <div class="reservation_summary_row">
                        <div class="reservation_summary_label">Start Date:</div>
                        <div class="reservation_summary_value">{{startDate}}</div>
                    </div>
                    <div class="reservation_summary_row">
                        <div class="reservation_summary_label">End Date:</div>
                        <div class="reservation_summary_value">{{endDate}}</div>
                    </div>
                </div>
                <div class="separator" style="grid-area: 3/2/4/4"></div>
                <div id="room_info">
                    <div class="reservation_summary_row" :key="room" v-for="room in rooms_added">
                        <div class="reservation_summary_label">Double Premium</div>
                        <div class="reservation_summary_value">$100</div>
                    </div>
                </div>
                <div class="separator" style="grid-area: 5/2/6/4"></div>
                <div id="total_price">
                    <div class="reservation_summary_row">
                        <div class="reservation_summary_label">Total:</div>
                        <div class="reservation_summary_value">${{rooms_added.length * 100}}</div>
                    </div>
                </div>
            </div>
        </div>
        <button class="room_confirm_button" @click="continueReservation()">Continue</button>
    </div>
</template>

<script>
    export default {
        name: "Client",
        components: {},
        data(){
            return{
                roomDropdownVisible: false,
                popUpWindow: false,
                roomWindow: false,
                personalInfoWindow: false,
                startDate: "",
                endDate: "",
                startDateAlert: false,
                endDateAlert: false,
                roomsAlert: false,
                selectedConfiguration: 0,
                rooms_added: [],
                today: new Date(),
                addresses: [{}],  // array of countries + [cities] objects
                paymentMethods: [],
            }
            
        },

        created(){
            this.rooms_added,
            this.rooms_returned,
            this.rooms_requested = [];
            this.rooms_selected = [];
            this.roomRequestJson = {};
            console.log(this.rooms_selected);
            var dd = String(this.today.getDate()).padStart(2, '0');
            var mm = String(this.today.getMonth() + 1).padStart(2, '0'); //January is 0!
            var yyyy = this.today.getFullYear();

            this.today = yyyy + '-' + mm + '-' + dd;

        },
        methods: {
            toggleMenu(){
                let room_add_menu = document.querySelector('#room_add_menu');
                let room_selection_box = document.querySelector('#room_selection_box');
                this.roomDropdownVisible = !this.roomDropdownVisible;
                room_add_menu.style.visibility = this.roomDropdownVisible ? "visible" : "hidden";
                room_selection_box.style.border = this.roomDropdownVisible ? "2px #3A86FF solid" : "1px #9CA7AB solid";
                console.log(this.roomDropdownVisible);
                this.updateAddRoomMenu();
            },
            updateAddRoomMenu(){
                let room_add_menu = document.querySelector('#room_add_menu');
                room_add_menu.style.height = (this.rooms_added.length*50 + 100).toString() + "px";
                if(this.rooms_added.length == 5){
                    let add_room_button = document.querySelector('#add_room_button');
                    add_room_button.style.background = "rgb(200,200,200)";
                    add_room_button.style.color = "white";
                    add_room_button.style.cursor = "not-allowed";
                }
                else{
                    let add_room_button = document.querySelector('#add_room_button');
                    add_room_button.style.background = "#28323F";
                    add_room_button.style.color = "white";
                    add_room_button.style.cursor = "pointer";
                }
            },
            addRoom(){
                if(this.rooms_added.length <= 4){
                this.rooms_added.push(1);
                this.updateAddRoomMenu();
                }
                
            },
            removeRoom(index){
                this.rooms_added.splice(index, 1)
                let room_add_menu = document.querySelector('#room_add_menu');
                room_add_menu.style.height = (this.rooms_added.length*50 + 100).toString() + "px";
                this.updateAddRoomMenu();
            },

            selectConfiguration(){
                var index = (this.selectedConfiguration + 1).toString();
                console.log(index);
                const all = document.querySelectorAll('.room_configuration');
                // Change the style of multiple elements with a loop
                all.forEach(element => {
                    element.style.background = "white";
                    element.style.color = "#0A141F";
                });

                var child = document.querySelector(".room_configuration:nth-child(" +  index + ")")
                child.style.background = '#080A0D';
                child.style.color = 'white';
            },
            
            findRoomsRequest(){
                console.log(this.addDays(this.today, 2))
                if (this.rooms_added.length == 0){
                    this.roomsAlert = true;
                } else this.roomsAlert = false;
                if (this.startDate == ""){
                    this.startDateAlert = true;
                } else this.startDateAlert = false;
                if (this.endDate == ""){
                    this.endDateAlert = true;
                } else this.endDateAlert = false;
                
                if (this.rooms_added.length != 0 & this.startDate != "" & this.endDate != ""){
                    // fill the array with empty arrays for storing selected rooms for each configuration
                    for (var i = 0; i<this.rooms_added.length; i++){
                        this.rooms_selected.push([]);
                    }
                    this.roomRequestJson.startDate = this.startDate;
                    this.roomRequestJson.endDate = this.endDate;
                    this.roomRequestJson.roomsDetailsList = this.rooms_added;
                    console.log(JSON.stringify(this.roomRequestJson));

                    fetch("/api/room/av", {
                        method: "POST",
                        headers: {'Content-Type': 'application/json'},
                        body: JSON.stringify(this.roomRequestJson)
                    }).then(response => response.json()).then(data => {
                        this.rooms_returned = data;

                        //make all rooms initially visible for selection
                        for (var configuration in this.rooms_returned){
                            for (var room in this.rooms_returned[configuration]){
                                this.rooms_returned[configuration][room].available = true;
                            }
                        }
                        this.popUpWindow = true;
                        this.roomWindow = true;
                    });
                }
            },
            addDays(date, days) {
                var result = new Date(date);
                result.setDate(result.getDate() + days);
                var dd = String(result.getDate()).padStart(2, '0');
                var mm = String(result.getMonth() + 1).padStart(2, '0'); //January is 0!
                var yyyy = result.getFullYear();
                result = yyyy + '-' + mm + '-' + dd;
                return result;
            },
            handleDates(){
                if(this.startDate >= this.endDate){
                    this.endDate = "";
                }
            },
            continueReservation(){
                if(this.roomWindow && this.allRoomsSelected()){
                    this.roomWindow = false;
                    this.personalInfoWindow = true;
                }
                else if (this.personalInfoWindow){
                    this.confirmReservation();
                    console.log('Reservation succesful!');
                }
            },
            isRoomSelected(room){
                if (!this.rooms_selected[this.selectedConfiguration].includes(room)){
                    return false;
                }
                else{
                    return true;
                }
            },
            allRoomsSelected(){
                if(this.roomWindow && this.rooms_selected.filter(el => el.length != 0).length == this.rooms_added.length){
                    return true;
                }
                else{
                    return false;
                }
            },
            selectRoom(room, event){
                if (!this.isRoomSelected(room)){
                    if (this.rooms_selected[this.selectedConfiguration].length == 0){
                        event.target.style.background = "#6A8EAE";
                        event.target.textContent = "Selected";
                        this.rooms_selected[this.selectedConfiguration].push(room);
                        for (var configuration in this.rooms_returned){
                            for (var room_in_configuration in this.rooms_returned[configuration]){
                                if (this.compareRooms(this.rooms_returned[configuration][room_in_configuration], room) && configuration != this.selectedConfiguration){
                                    this.rooms_returned[configuration][room_in_configuration].available = false;
                                }
                            }
                        }
                    }
                }
                else{
                    event.target.style.background = "#28323F";
                    event.target.textContent = "Select Room";
                    this.rooms_selected[this.selectedConfiguration].splice(this.rooms_selected[this.selectedConfiguration].indexOf(room), 1);
                    for (configuration in this.rooms_returned){
                        for (room_in_configuration in this.rooms_returned[configuration]){
                            if (this.compareRooms(this.rooms_returned[configuration][room_in_configuration], room)){
                                this.rooms_returned[configuration][room_in_configuration].available = true;
                                console.log(this.rooms_returned[configuration][room_in_configuration]);
                            }
                        }
                    }
                }
                var button = document.querySelector(".room_confirm_button");
                if(this.allRoomsSelected()){
                    console.log(button);
                    button.style.background = "#28323F";
                    button.style.cursor = "pointer";
                }
                else{
                    button.style.background = "#6A8EAE";
                    button.style.cursor = "not-allowed";
                }
            },
            availableRooms(selectedConfiguration){
                console.log(this.rooms_returned);
                var result = this.rooms_returned[selectedConfiguration].filter(function (el) {
                    return el.available == true;
                });
                return result;
            },
            compareRooms(a, b) {
                return a.doubleBeds == b.doubleBeds && 
                a.floor == b.floor &&
                a.number == b.number &&
                a.pricePerNight == b.pricePerNight &&
                a.roomName == b.roomName &&
                a.singleBeds == b.singleBeds
            },
            confirmReservation(){
                // convert personal data form into JSON
                var formData = new FormData(document.querySelector("#reservation_form"));
                var object = {};
                formData.forEach((value, key) => object[key] = value);

                // create an address JSON and POST
                var addressObject = {
                    country: object.country,
                    city: object.city,
                    street: object.street
                };
                
                var addressId;

                fetch("/api/address/add", {
                    method: "POST",
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify(addressObject)
                }).then(response => response.json()).then(data => {
                    addressId = data;
                });

                // create a guest JSON and POST
                var guestObject = {
                    firstName: object.fname,
                    lastName: object.lname,
                    dateOfBirth: object.birth,
                    telephone: object.telephone,
                    addressId: addressId
                };

                var guestId;

                fetch("/api/guest/add", {
                    method: "POST",
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify(guestObject)
                }).then(response => response.json()).then(data => {
                    guestId = data;
                });

                // create a reservation JSON and POST

                var numberOfGuests = this.rooms_added.reduce((a,b)=>a+b,0);
                var startDate = this.startDate;
                var endDate = this.endDate;

                var reservationObject = {
                    numberOfGuests: numberOfGuests,
                    startDate: startDate,
                    endDate: endDate,
                    guestId: guestId,
                    paymentMethodName: object.payment
                };
                
                var reservationId;

                fetch("/api/rsv/add", {
                    method: "POST",
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify(reservationObject)
                }).then(response => response.json()).then(data => {
                    reservationId = data;
                });

                // create a room list JSON and POST

                var roomListObject = {
                    reservationId: reservationId,
                    rooms: [1,2]
                };

                fetch("/api/roominrsv/add", {
                    method: "POST",
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify(roomListObject)
                }).then(response => response.json()).then(data => {
                    console.log(data);
                });
            },
        }
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
        background-image: url("../assets/background.jpg");
        background-position: bottom;
        background-size: cover;
        display: grid;
        grid-template-columns: 1fr;
        grid-template-rows: 40fr 60fr;
    }

    #top{
        grid-area: 1/1/2/2;
        background: rgba(10,20,31,0.65);
        display: grid;
        grid-template-columns: 1fr 1fr 1fr;
        grid-template-rows: 1fr 1fr 2fr;
    }

    #title{
        font-size: 60px;
        font-weight: 600;
        color: white;
        grid-area: 3/2/4/3;
        align-self: center;
        justify-self: center;
    }
    
    #bottom{
        grid-area: 2/1/3/2;
        background: white;
        display: grid;
        grid-template-columns: 1fr 900px 1fr;
        grid-template-rows: 20px 1fr 1fr;
    }

    #menu{
        grid-area: 2/2/2/3;
        display: grid;
        grid-template-columns: 1fr 1fr 1fr;
        grid-template-rows: 1fr 1fr;
    }

    .selection_area{
        display:grid;
        grid-template-columns: 1fr 220px 1fr;
        grid-template-rows: 1fr 32px 32px 1fr;
    }

    .selection_box{
        grid-area:3/2/4/3;
        width: 220px;
        height: 32px;
        box-sizing: border-box;
        border: 1px #9CA7AB solid;
        /* border-radius: 2px; */
        align-self: center;
        justify-self: center;
        font-family: sans-serif;
        font-size: 14px;
        color: #0A141F;
        background-color: white;
        z-index: 2;
        cursor: pointer;
    }

    *:focus {
        outline: none;
    }

    .selection_box_active{
        border-color: #3A86FF;
        box-shadow: 0px 0px 2px #3A86FF;
        border-width: 2px;
    }

    #room_selection_box{
        border: 1px #9CA7AB solid;
    }

    #room_add_menu{
        grid-area:3/2/4/3;
        justify-self: center;
        width: 220px;
        height: 150px;
        z-index: 1;
        background-color: white;    
        box-sizing: border-box;
        border: 2px #3A86FF solid;
        box-shadow: 0px 0px 2px #3A86FF;
        border-top: none;
        /* border-radius: 2px; */
        padding-top: 32px;
        display: grid;
        grid-template-rows: 1fr 60px;
        grid-template-columns: 1fr;
        text-align: center;
        visibility: hidden;
    }

    #room_add_list{
        grid-area: 1/1/2/2;
        height: auto;
        text-align: right;
    }

    .guest_number_input{
        grid-area: 1/3/2/4;
        align-self: center;
        justify-self: center;
        height: 24px;
        width: 50px;
    }

    #add_room_button{
        width: 120px;
        height: 32px;
        background: #28323F;
        color: white;
        font-size: 14px;
        align-self: center;
        justify-self: center;
        /* border-radius: 5px; */
        border: none;
	    padding: 0;
        cursor: pointer;
    }

    .room_number{
        grid-area: 1/2/2/3;
        align-self: center;
        justify-self: start;
    }

    .add_room_row{
        width: 220px;
        height: 30px;
        display: grid;
        grid-template-rows: 1fr;
        grid-template-columns: 30px 1.5fr 1.5fr 1fr 10px;
        font-size: 14px;
        border-bottom: 1px rgba(187, 187, 187, 0.7) solid;
        padding-bottom: 10px;
        padding-top: 10px;
    }

    .delete_icon{
        grid-area: 1/4/2/5;
        width: 24px;
        justify-self: center;
        align-self: center;
        cursor: pointer;
    }

    .selection_text{
        grid-area:2/2/3/3;
        font-size: 16px;
        font-weight: 300;
        justify-self: start;
    }

    #start_date{
        grid-area: 1/1/1/2;
    }

    #end_date{
        grid-area: 1/2/1/3;
    }

    #rooms{
        grid-area: 1/3/1/4;
    }

    #check_button{
        grid-area: 2/2/3/3;
        width: 200px;
        height: 50px;
        background: #28323F;
        color: white;
        font-size: 16px;
        align-self: center;
        justify-self: center;
        /* border-radius: 5px; */
        border: none;
	    padding: 0;
        cursor: pointer;
    }

    #check_button:hover{
        background-color: #181E26;
    }

    #check_button:active{
        background-color: #080A0D;
    }

    .empty_alert{
        color: #ED1C24;
        font-size: 14px;
        align-self: start;
        justify-self: center;
        margin-top: 10px;
        grid-area: 4/2/5/3;
    }
    

    #room_selection_window{
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
        visibility: visible;
    }

    #room_selection_window_title_bar{
        grid-area: 1/1/2/-2;
        font-size: 20px;
        display: grid;
        grid-template-columns: 1fr 50px;
        grid-template-rows: 1fr;
    }

    #room_selection_window_title span{
        cursor:pointer;
    }

    #navbar{
        text-align: left;
        grid-area: 1/1/2/2;
        display: inline-flex;
        margin-left: 20px;
        margin-top: 25px;
    }



    #exit_icon{
        grid-area: 1/3/2/4;
        width: 40px;
        margin-top: 10px;
        justify-self: end;
        cursor: pointer;
    }

    #room_configuration_selection_bar{
        grid-area: 1/1/2/2;
        display: inline-flex;
        gap: 10px;
        padding-left: 5px;
    }

    .room_configuration{
        height: 100%;
        width: 184px;
        border: 1px #9CA7AB solid;
        /* border-radius: 4px; */
        font-family: sans-serif;
        font-size: 14px;
        color: #0A141F;
        background-color: white;
        z-index: 2;
        cursor: pointer;
        box-sizing: border-box;
        text-align: center;
        line-height: 36px;
    }

    #selected_configuration{
        background: #28323F;
        color: white;
        border: none;
    }

    #room_selection_window_room_list{
        grid-area: 3/1/5/4;
        padding-top: 10px;
        overflow-y: scroll;
    }

    .room_container{
        width: 98%;
        height: 200px;
        /* border-radius: 10px; */
        box-shadow: 0px 2px 5px #9CA7AB;
        margin-bottom: 20px;
        margin-left: 5px;
        display: grid;
        grid-template-columns: 300px 1fr 1fr;
        grid-template-rows: 75px 1fr 1fr;
        overflow: hidden;
    }

    .room_image{
        grid-area: 1/1/-1/2;
        width: 300px;
        height: 200px;
    }

    .room_name{
        grid-area: 1/2/2/3;
        font-size: 24px;
        font-weight: 500;
        justify-self: start;
        align-self: start;
        margin-left: 20px;
        margin-top: 20px;
    }

    .room_price{
        grid-area: 1/3/2/4;
        font-size: 32px;
        font-weight: 500;
        justify-self: end;
        align-self: start;
        margin-right: 20px;
        margin-top: 20px;
    }
    
    .room_select_button_inactive{
        grid-area: 3/3/4/4;
        width: 200px;
        height: 40px;
        background: #28323F;
        color: white;
        font-size: 16px;
        align-self: center;
        justify-self: end;
        /* border-radius: 5px; */
        border: none;
	    padding: 0;
        cursor: pointer;
        margin-right: 20px;
    }

    .room_select_button_active{
        grid-area: 3/3/4/4;
        width: 200px;
        height: 40px;
        background: #6A8EAE;
        color: white;
        font-size: 16px;
        align-self: center;
        justify-self: end;
        /* border-radius: 5px; */
        border: none;
	    padding: 0;
        cursor: pointer;
        margin-right: 20px;
    }

    .room_description{
        grid-area: 2/2/4/3;
        font-size: 16px;
        font-weight: 500;
        justify-self: start;
        align-self: start;
        margin-left: 20px;
        margin-top: 20px;
        text-align: left;
    }

    .room_confirm_button{
        grid-area: 3/2/4/3;
        width: 200px;
        height: 40px;
        background: #6A8EAE;
        color: white;
        font-size: 16px;
        align-self: center;
        justify-self: center;
        /* border-radius: 5px; */
        border: none;
	    padding: 0;
        cursor: not-allowed;
        margin-right: 20px;
    }


    #room_window{
        grid-area: 2/2/3/5;
        display: grid;
        grid-template-rows: 40px 10px 1fr 100px;
        grid-template-columns: 1fr;
        overflow-y: scroll;
    }

    #personal_info_window{
        grid-area: 2/2/3/5;
        display: grid;
        grid-template-rows: 1fr 539px 1fr;
        grid-template-columns: 70px 410px 1fr 360px 70px;
    }

    /*  FORM  */

    #reservation_form{
        grid-area: 2/2/3/3;
        text-align: start;
        align-self: center;
    }
    
    .form_input{
        margin-bottom: 20px;
        width: 400px;
        height: 32px;
    }

    label{
        font-size: 16px;
    }

    #reservation_summary{
        grid-area: 2/4/3/5;
        border: #28323F 0.5px solid;
        display: grid;
        grid-template-columns: 20px 1fr 1fr 20px;
        grid-template-rows: 65px 100px 1px 280px 1px 1fr;
    } 

    #reservation_summary_title{
        grid-area: 1/1/2/5;
        background: #181E26;
        color: white;
        text-align: center;
        padding-top: 22.5px;
        font-size: 20px;
    }


    #reservation_info{
        grid-area: 2/2/3/4;
    }

    .reservation_summary_row{
        display: grid;
        grid-template-columns: 1fr 1fr;
        margin-top: 20px;
    }

    .reservation_summary_label{
        font-size: 18px;
        align-self: center;
        justify-self: start;
        margin-left: 10px;
        grid-column: 1/2;
    }

    .reservation_summary_value{
        font-size: 18px;
        font-weight: 600;
        align-self: center;
        justify-self: end;
        margin-right: 10px;
        grid-column: 2/3;
    }

    #room_info{
        grid-area: 4/2/5/4;
    }
    

    #total_price{
        grid-area: 6/2/7/4;
    }
    
    .separator{
        background: #181E26;
        opacity: 0.5;
    }
    
    .room_configuration:first-child{
        background: #080A0D;
        color: white;
    }

</style>