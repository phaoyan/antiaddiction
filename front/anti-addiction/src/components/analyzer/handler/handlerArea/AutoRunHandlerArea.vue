<script setup>
import {inject} from "vue"
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
        <handler-label :name="listenerData.handler.simplifiedName" @deleteHandler="deleteHandler(index)"/>
        <div class="content">
            <div class="name">&nbsp;&nbsp;&nbsp;&nbsp;{{listenerData.handler.path.split("/")[listenerData.handler.path.split("/").length-1]}}</div>
            <div class="path">&nbsp;&nbsp;&nbsp;&nbsp;{{ listenerData.handler.path}}</div>
        </div>
    </div>
</template>

<style scoped>

.name{
    height: 10vh;
    line-height: 10vh;
    text-align: center;
    font-weight: 200;
    font-size: 200%;
    color:burlywood
}
.path{
    text-align: center;
    color:burlywood;
    font-size: 110%;
    font-weight: 100;
}
</style>