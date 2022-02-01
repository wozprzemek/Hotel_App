<template>
    <div id="container">
        <div id="title">Hotel Admin </div>
        <div id="content">
            <div id="login_area">
                <div id="text">Username</div>
                <input type="text" placeholder="Enter Username" id="box">
            </div>
            
            <div id="password_area">
                <div id="text">Password</div>
                <input type="password" placeholder="Enter Password" id="box">
            </div>

            <button id="login_button">Sign In</button>
        </div>
    </div>
</template>

<script>
    export default {
        
    }
</script>

<style scoped>
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