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
        <handler-label :name="handlerData.handler.name" @deleteHandler="deleteHandler(index)"/>
        <div class="content">
            {{handlerData.handler.target.processName}}
        </div>
    </div>
</template>

<style scoped>

.content{
    height: 17vh;
    line-height: 17vh;
    text-align: center;
    font-weight: 200;
    font-size: 150%;
    color:burlywood
}
</style>