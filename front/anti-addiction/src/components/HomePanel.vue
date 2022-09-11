<script setup>
import {ref} from "vue"
import axios from "axios"

const backendRunning = ref(true)

const switchBack = async (flag)=>{
    //todo
    await axios.get("http://localhost:8080/switch",{params:{flag:flag}}).then((isRunning)=>{
        backendRunning.value = isRunning;
        console.log(isRunning)
    })
}

</script>


<template>
    <div class="body">
        <div class="backend-switch-on switch-bar" v-if="backendRunning==true">
            <el-icon size="200%" class="switch-on" @click="switchBack(false)"><SwitchButton /></el-icon>
            <div class="label">backend program is running ...</div>
        </div>
        <div class="backend-switch-off switch-bar" v-if="backendRunning==false">
            <el-icon size="200%" class="switch-off" @click="switchBack(on)"><SwitchButton /></el-icon>
            <div class="label">backend program is stopped ...</div>
        </div>
        
    </div>
</template>

<style scoped>
.body{
    background-color:#fcfdfa;
    height:89vh;
}
.switch-bar{
    display:flex;
    margin-left:3vw;
    margin-top: 3vh;
}
.backend-switch-on{
    color:cadetblue;
}
.backend-switch-off{
    color:brown;
}
.switch-on{
    cursor: pointer;
}
.switch-on:hover{
    color:brown;
}
.switch-off{
    cursor: pointer;
}
.switch-off:hover{
    color:cadetblue;
}
.backend-switch-on .label{
    margin-left:3vw;
    height:4vh;
    line-height: 4vh;
    color: cadetblue;
}
.backend-switch-off .label{
    margin-left:3vw;
    height:4vh;
    line-height: 4vh;
    color: brown;
}
</style>
