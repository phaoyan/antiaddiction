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
        <div class="content">
            {{patternData.handler.target.processName}}
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