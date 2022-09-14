<script setup>
import {ref, inject} from "vue"

const path = ref()

const getHandlerOriginal = inject('getHandlerOriginal')
const postHandlerToBack = inject('postHandler')

const postHandler = async ()=>{
    let handler = await getHandlerOriginal('run computer process')
    handler.path = path.value
    postHandlerToBack(handler, 'onStartup')
}

</script>

<template>
    <div class="main">
        <div class="input">
            <input v-model="path" placeholder="path"/>
        </div>
        <div class="confirm">
            <el-icon class="icon-button" size="150%" @click="postHandler()"><Check /></el-icon>
        </div>
    </div>
</template>