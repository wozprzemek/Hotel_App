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
                    <button class="selection_box" id="room_selection_box" @click="toggleMenu()">{{this.rooms_added.length}} Rooms | {{sum(this.rooms_added, 'no_of_guests')}} People</button>
                    <div id="room_add_menu">
                        <div id="room_add_list">
                            <div class="add_room_row" :key="room.id" v-for="(room, index) in rooms_added">
                            <div class="room_number">Room {{index+1}}</div>
                            <input type='text' v-model="room.no_of_guests" class="guest_number_input">
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
    <div v-if="roomWindow" id="room_selection_window">
        <div id="room_selection_window_title_bar">
            <div id="room_selection_window_title">Rooms</div>
            <img id="exit_icon" src="../assets/delete.png" @click="roomWindow = false"/>
        </div>
        <div id="room_configuration_selection_bar">
            <div class="room_configuration" :key="room.id" v-for="(room, index) in rooms_added">Room {{index+1}} | {{room.no_of_guests}} Guests</div>
        </div>
        <div id="room_selection_window_room_list">
            <div class="room_container" :key="room.id" v-for="room in rooms_added">
                <img class="room_image" src="../assets/background.jpg"/>
                <div class="room_name">Double Premium</div>
                <div class="room_price">$100</div>
                <div class="room_description">Description description description description description description description description</div>
                <button class="room_select_button">Select Room</button>
            </div>
        </div>
        <button class="room_confirm_button">Confirm</button>
    </div>
</template>

<script>
    export default {
        name: "Client",
        components: {},
        data(){
            return{
                roomDropdownVisible: false,
                roomWindow: false,
                startDate: "",
                endDate: "",
                startDateAlert: false,
                endDateAlert: false,
                roomsAlert: false,
                rooms_added: [],
                today: new Date(),
            }
            
        },
        created(){
            this.rooms_added,
            this.rooms_requested = [
                {
                    id: null,
                    name: null,
                    price: null,
                    description: null,
                }
            ];
            this.rooms_selected = [
            {
                    id: null,
                    name: null,
                    price: null,
                    description: null,
                }
            ];
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
                this.rooms_added.push({
                    id: this.rooms_added.length > 0 ? this.rooms_added.at(-1).id + 1 : 1,
                    no_of_guests: 1,
                });
                this.updateAddRoomMenu();
                }
                
            },
            removeRoom(index){
                this.rooms_added.splice(index, 1)
                let room_add_menu = document.querySelector('#room_add_menu');
                room_add_menu.style.height = (this.rooms_added.length*50 + 100).toString() + "px";
                this.updateAddRoomMenu();
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
                    this.roomWindow = true;
                    var rooms_to_find = this.rooms_added.filter(function (el) {
                            return el.no_of_guests != null
                        });
                    console.log(rooms_to_find)
            
                }

                let data = {element: "barium"};

                fetch("/api/test", {
                method: "POST",
                headers: {'Content-Type': 'application/json'}, 
                body: JSON.stringify(data)
                }).then(res => {
                console.log("Request complete! response:", res);
                });

  
            },
            sum(items, prop){
            return items.reduce( function(a, b){
                if(b[prop] != null){
                    return a + parseInt(b[prop]);
                }
                else{
                    return a
                }

            }, 0);

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
                    this.endDate = ""
                }
            }

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
        border-radius: 2px;
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
        border-radius: 2px;
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
        border-radius: 5px;
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
        border-radius: 5px;
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
        grid-template-rows: 80px 40px 10px 1fr 100px;
        overflow: hidden;
        visibility: visible;
    }

    #room_selection_window_title_bar{
        grid-area: 1/1/2/-2;
        font-size: 24px;
        display: grid;
        grid-template-columns: 50px 1fr 50px;
        grid-template-rows: 1fr;
    }

    #room_selection_window_title{
        grid-area: 1/2/2/3;
        margin-top: 20px;
    }

    #exit_icon{
        grid-area: 1/3/2/4;
        width: 40px;
        margin-top: 10px;
        justify-self: end;
        cursor: pointer;
    }

    #room_configuration_selection_bar{
        grid-area: 2/2/3/3;
        display: inline-flex;
        gap: 10px;
        padding-left: 5px;
    }

    .room_configuration{
        height: 100%;
        width: 184px;
        border: 1px #9CA7AB solid;
        border-radius: 4px;
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
        grid-area: 4/2/5/3;
        padding-top: 10px;
        overflow-y: scroll;
    }

    .room_container{
        width: 98%;
        height: 200px;
        border-radius: 10px;
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
    
    .room_select_button{
        grid-area: 3/3/4/4;
        width: 200px;
        height: 40px;
        background: #28323F;
        color: white;
        font-size: 16px;
        align-self: center;
        justify-self: end;
        border-radius: 5px;
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
        grid-area: 5/2/6/3;
        width: 200px;
        height: 40px;
        background: #28323F;
        color: white;
        font-size: 16px;
        align-self: center;
        justify-self: center;
        border-radius: 5px;
        border: none;
	    padding: 0;
        cursor: pointer;
        margin-right: 20px;
    }

</style>