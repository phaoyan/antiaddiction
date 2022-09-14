<script setup>
import {ref, provide} from "vue"
import DisplayPatternPanel from './DisplayPatternPanel.vue';
import CreatePatternPanel from "./CreatePatternPanel.vue";


const mode = ref('display')
provide('mode', mode)

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
            <div class="label">Patterns&nbsp;</div>
            <el-icon class="icon-button" size="120%" @click="changeDisplayMode()" v-if="mode === 'display'"><Plus /></el-icon>
            <el-icon class="icon-button" size="120%" @click="changeDisplayMode()" v-if="mode === 'create'"><Back /></el-icon>
        </div>
        <div class="main">
            <display-pattern-panel v-if="mode == 'display'"/>
            <create-pattern-panel v-if="mode == 'create'"/>
        </div>
    </div>
</template>

<style scoped>
.box{
    display: block;
}
</style>