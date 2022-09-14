<script setup>
import {ref, inject} from "vue"


const processName = ref()

const getHandlerOriginal = inject('getHandlerOriginal')
const postHandlerToBack = inject('postHandler')

const postHandler = async()=>{
    let handler = await getHandlerOriginal('cease computer process')
    handler.target.processName=processName.value
    postHandlerToBack(handler, "onLoop")
}

</script>

<template>
    <div class="main">
        <div class="input">
            <input v-model="processName" placeholder="process name"/>
        </div>
        <div class="confirm">
            <el-icon class="icon-button" size="150%" @click="postHandler()"><Check /></el-icon>
        </div>
    </div>
</template>

<style scoped>
.main{
    display: flex;
    justify-content: space-between;
}

</style>

