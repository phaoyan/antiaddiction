<script setup>
import {inject} from "vue"
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
        <handler-label :name="handlerData[0]" @deleteHandler="deleteHandler(index)"/>
        <div class="content">
            <div class="type">&nbsp;&nbsp;&nbsp;&nbsp;{{handlerData[1]}}</div>
            <div class="uri">&nbsp;&nbsp;&nbsp;&nbsp;{{ handlerData[2]}}</div>
        </div>
    </div>
</template>

<style scoped>
.type{
    height: 10vh;
    line-height: 10vh;
    text-align: center;
    font-weight: 200;
    font-size: 200%;
    color:burlywood
}
.uri{
    text-align: center;
    color:burlywood;
    font-size: 100%;
    font-weight: 100;
}
</style>