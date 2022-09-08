<script setup>
import {inject} from "vue"
import axios from "axios"
import HandlerLabel from "./HandlerLabel.vue"

const prop = defineProps({
    index:Number
})

const patternList = inject('patternList')
const patternData = patternList.value[prop.index]

const deleteHandler = async ()=>{
    patternList.value[prop.index].handler = null
    await axios.post("http://localhost:8080/patternList",patternList.value)

}

</script>

<template>
    <div class="main">
        <handler-label :name="patternData.handler.simplifiedName" @deleteHandler="deleteHandler()"/>
        <div class="urls">
            <div class="content">from:</div>
            <div class="content">&nbsp;&nbsp;&nbsp;&nbsp;{{patternData.handler.sourceUrl}}</div>
            <div class="content">to:</div>
            <div class="content">&nbsp;&nbsp;&nbsp;&nbsp;{{patternData.handler.targetUrl}}</div>
        </div>
    </div>
</template>

<style scoped>
.urls{
    margin-top:2vh;
}
.content{
    color:burlywood;
    font-size: 110%;
    font-weight: 100;
}
</style>