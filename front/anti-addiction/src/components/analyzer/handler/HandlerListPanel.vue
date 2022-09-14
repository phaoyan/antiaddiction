<script setup>
import {ref, inject} from "vue"
import DisplayHandlerPanel from "./DisplayHandlerPanel.vue"
import CreateHandlerPanel from "./CreateHandlerPanel.vue"

const mode = ref('display')

const changeDisplayMode = ()=>{
    if(mode.value === 'display')
        mode.value = 'create';
    else if(mode.value === 'create')
        mode.value = 'display'
}

</script>

<template>
    <div class="box">
        <div class="header">
            <div class="label">Handlers&nbsp;</div>
            <el-icon class="icon-button" size="120%" @click="changeDisplayMode()" v-if="mode === 'display'"><Plus /></el-icon>
            <el-icon class="icon-button" size="120%" @click="changeDisplayMode()" v-if="mode === 'create'"><Back /></el-icon>
        </div>
        <div class="main">
            <display-handler-panel v-if="mode == 'display'"/>
            <create-handler-panel v-if="mode == 'create'"/>
        </div>
    </div>
</template>

<style scoped>
.box{
    display:block
}
</style>