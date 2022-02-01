<template>
    <div>
        <h1>This is a profile</h1>
        <h3>{{$route.params.id}}</h3>
        <router-link :to="{name: 'Profile', params: {id: 'eniu'}}">Another route</router-link>
    </div>
</template>

<script>
    export default {
        mounted(){
            console.log(this.$route.params.id)
        },
        watch: {
            $route(to, from){
                console.log("to", to)
                console.log("from", from)
            }
        }
    }
</script>

<style scoped>

</style>