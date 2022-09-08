<script setup>

import {ref, inject} from "vue"
import axios from "axios"
import UntimedSequenceArea from "./UntimedSequenceArea.vue"
import PatternTable from "./PatternTable.vue"

const patternList = ref()
const display = inject('display')


const emit = defineEmits(['changeDisplayMode'])

const changeToAssignHandler = ()=>{
    display.value = 'assign handlers'
}

const changeDisplayMode = (mode)=>{
    emit('changeDisplayMode', mode)
}

const getPatternList = async ()=>{
    axios.get("http://localhost:8080/patternList").then(e=>{
        patternList.value = e.data
    })
}

const init = ()=>{
    getPatternList()
}

init()

</script>

<template>
    <div class="container">
        <div class="header">
            <div class="label">Behavior Patterns&nbsp;</div>
                <el-dropdown>
                    <el-icon class="icon-button" size="120%" ><Plus /></el-icon>
                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item>
                                <div class="dropdown" @click="changeDisplayMode('register untimed')">untimed sequence</div>
                            </el-dropdown-item>
                            <el-dropdown-item>
                                <div class="dropdown" @click="changeDisplayMode('register timed')">timed sequence</div>
                            </el-dropdown-item>
                            <el-dropdown-item>
                                <div class="dropdown" @click="changeDisplayMode('register set')">behavior set</div>
                            </el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
        </div>
        <pattern-table :patternList="patternList">
            <div class="wrapper">
                    <el-tooltip
                effect="light"
                content="assign handler">
                    <el-icon class="icon-button guide" size="150%" @click="changeToAssignHandler()"><Guide /></el-icon>
                </el-tooltip>
                
            </div>

        </pattern-table>
    </div>
</template>

<style scoped>
.dropdown{
    margin: 0 auto;
}
</style>