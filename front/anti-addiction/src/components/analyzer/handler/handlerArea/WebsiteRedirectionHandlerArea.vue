<script setup>
import {inject} from "vue"
import axios from "axios"
import HandlerLabel from "./HandlerLabel.vue"

const prop = defineProps({
    index:Number
})

const listenerList = inject('listenerList')
const deleteHandler = inject('deleteHandler')
const listenerData = listenerList.value[prop.index]

</script>

<template>
    <div class="main">
        <handler-label :name= listenerData.handler.simplifiedName @deleteHandler="deleteHandler(index)"/>
        <div class="urls">
            <div class="content">from:</div>
            <div class="content">&nbsp;&nbsp;&nbsp;&nbsp;{{listenerData.handler.sourceUrl}}</div>
            <div class="content">to:</div>
            <div class="content">&nbsp;&nbsp;&nbsp;&nbsp;{{ listenerData.handler.targetUrl}}</div>
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