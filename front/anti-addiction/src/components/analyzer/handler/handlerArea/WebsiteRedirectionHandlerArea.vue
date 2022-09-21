<script setup>
import {inject} from "vue"
import axios from "axios"
import HandlerLabel from "./HandlerLabel.vue"

const prop = defineProps({
    index:Number
})

const handlerList = inject('handlerList')
const deleteHandler = inject('deleteHandler')
const handlerData = handlerList.value[prop.index]

</script>

<template>
    <div class="main">
        <handler-label :name= handlerData[0] @deleteHandler="deleteHandler(index)"/>
        <div class="urls">
            <div class="content">from:</div>
            <div class="content">&nbsp;&nbsp;&nbsp;&nbsp;{{handlerData[1]}}</div>
            <div class="content">to:</div>
            <div class="content">&nbsp;&nbsp;&nbsp;&nbsp;{{ handlerData[2]}}</div>
        </div>
    </div>
</template>

<style scoped>
.urls{
    margin-top:2vh;
}
.content{
    color:burlywood;
    font-weight: 100;
}
</style>