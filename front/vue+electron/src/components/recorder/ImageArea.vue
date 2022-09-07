<script setup>
import TimeBar from "./TimeBar.vue"
import {ref} from "vue"

const props = defineProps({
    behavior: Object,
    index: Number
})

const mBehaviors = ref(props.behavior.details)
const enviorments = ref(new Array())
const imgSrcs = ref(new Array())
const imgUrls = ref(new Array())

mBehaviors.value.forEach(element => {
    enviorments.value.push(element.overallEnvironment)
});

enviorments.value.forEach(environment =>{
    environment.datum.forEach(data =>{
        if(data.imgSrcString != undefined)
            imgSrcs.value.push(data.imgSrcString)
    })
})
imgSrcs.value.forEach(src =>{
    imgUrls.value.push("http://localhost:8080/" + /static.*?.jpg/.exec(src))
})

const emit = defineEmits(["changeDisplay"])
</script>

<template>
    <div>
        <time-bar @changeDisplay = "emit('changeDisplay', 'process')" :behavior="behavior"/>
        <div class="imgs" v-for="imgUrl in imgUrls" :key="imgUrl">
            <el-image :src="imgUrl" />   
         
        </div>
    
    </div>
</template>