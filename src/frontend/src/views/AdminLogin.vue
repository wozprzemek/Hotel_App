<template>
    <div id="container">
        <div id="title">Hotel Admin </div>
        <div id="content">
            <form id="login_form">
                <label for="login">Username</label>
                <br>
                <input type="text" name="login" placeholder="Enter Username" id="box">
                <br>
                <label for="login">Password</label>
                <br>
                <input type="password" name="password" placeholder="Enter Password" id="box">
            </form>

            <button id="login_button" @click="signIn()">Sign In</button>
        </div>
    </div>
</template>

<script>
    export default {
        name: "OrderPanel",

        data(){
            return{
                
            }
        },
        created(){

        },
        methods: {
            signIn(){
                var formData = new FormData(document.querySelector("#login_form"));
                var object = {};
                formData.forEach((value, key) => object[key] = value);
                console.log(object);
                fetch('/api/admin/auth?' + new URLSearchParams(object), {
                method: "POST"}).then(result => result.json()).then(data => {
                    console.log(data);
                    if (data == 1){
                        this.$router.push({ path: '/admin/reservations' });
                    }
                    else{
                        alert('Wrong username or password!');
                    }
                    // fetch('/api/cat/all').then(result => result.json()).then(remoteRowData => this.rowDataCategories.value = remoteRowData);
                });
            },
        },
    }
</script>

<style scoped>

    label{
        margin-bottom: 10px;
        display:inline-block;
    }

    input{
        margin-bottom: 50px;
    }

    body{
        padding: 0;
        margin: 0;
        font-family: sans-serif;
    }

    #container{
        width: 100vw;
        height: 100vh;
        display: grid;
        grid-template-columns: 1fr 600px 1fr;
        grid-template-rows: 0.5fr 0.5fr 300px 1fr;
    }

    #title{
        grid-area: 2/2/3/3;
        font-size: 32px;
        color: #0A141F;
        justify-self: center;
        align-self: center;
    }

    #content{
        grid-area: 3/2/4/3;
        display: grid;
        grid-template-columns: 1fr;
        grid-template-rows: 1fr 1fr 1fr;
        grid-gap: 20px;
    }

    #login_area{
        grid-area: 1/1/2/1;
        display: grid;
        grid-template-columns: 1fr;
        grid-template-rows: 1fr 1fr;
    }

    #password_area{
        grid-area: 2/1/3/1;
        display: grid;
        grid-template-columns: 1fr;
        grid-template-rows: 1fr 1fr;
    }

    #text{
        grid-area: 1/1/2/1;
        font-size: 16px;
        color: #0A141F;
        justify-self: center;
        align-self: end;
        margin-bottom: 10px;
    }

    #box{
        grid-area: 2/1/3/1;
        width: 320px;
        height: 32px;
        border: 1px #9CA7AB solid;
        border-radius: 2px;
        align-self: start;
        justify-self: center;
    }

    #login_button{
        grid-area: 3/1/4/1;
        width: 200px;
        height: 50px;
        background: #0A141F;
        color: white;
        font-size: 16px;
        align-self: center;
        justify-self: center;
        border-radius: 5px;
        border: none;
	    padding: 0;
        cursor: pointer;
    }


</style>