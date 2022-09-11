<script setup>
import {ref} from "vue"
import axios from "axios"
import BriefArea from "./BriefArea.vue"
import ImageArea from "./ImageArea.vue"
import ProcessDatumArea from "./ProcessDatumArea.vue"

const props = defineProps({
    displayMode: String
})
//向recorder panel返回日期以在header中显示
const emit = defineEmits(['getDate'])


const behaviorHistory = ref(null);
const behaviorList = ref(null);
const date = ref(null);




const init = async ()=>{
    await axios.get("http://localhost:8080/history").then((history)=>{
        behaviorHistory.value = history.data   
    })
    behaviorList.value = behaviorHistory.value.history
    date.value = behaviorHistory.value.startTime.split('T')[0]
    behaviorList.value.forEach(timedBehavior => {
        timedBehavior["display"] = ref("brief")
    });
    emit('getDate', date)
}


init()

</script>

<template>
    <el-icon class="icon-button refresh" @click = "init()"><RefreshRight /></el-icon>
    <div class="box" v-for="timedBehavior in behaviorList" :key="timedBehavior">
        <brief-area :behavior="timedBehavior" 
            v-if="timedBehavior.display === 'brief'" 
            @changeDisplay="(mode)=>{timedBehavior.display = mode}"/>
        <image-area :behavior="timedBehavior"
            v-if="timedBehavior.display === 'image'" 
            @changeDisplay="(mode)=>{timedBehavior.display = mode}"/>
        <process-datum-area :behavior="timedBehavior"
            v-if="timedBehavior.display === 'process'" 
            @changeDisplay="(mode)=>{timedBehavior.display = mode}"/>
    </div>
</template>


<style scoped>
.refresh{
    margin-bottom: 3vh;
}
</style>