<script setup>
import {ref, provide} from "vue"
import axios from "axios"
import PatternListPanel from "./PatternListPanel.vue"
import HandlerListPanel from "./HandlerListPanel.vue"

const selectedPattern = ref(null)
const selectedIndex = ref(-1)
const patternList = ref()
const listenerList = ref()
provide('selectedPattern', selectedPattern)
provide('selectedIndex',selectedIndex)
provide('patternList',patternList)
provide('listenerList',listenerList)

const getPatternList = async ()=>{
    axios.get("http://localhost:8080/pattern/list").then(e=>{
        patternList.value = e.data
    })
}
const getListenerList = async ()=>{
    axios.get("http://localhost:8080/listenerList").then(e=>{
        listenerList.value = e.data
    })
}

const init = async ()=>{
    getPatternList()
    getListenerList()
}
init()

</script>

<template>
    <div class="container">
        <el-scrollbar height="76vh">
            <div class="main">
                <pattern-list-panel class="pattern-list"/>
                <el-divider direction="vertical" class="divider"/>
                <handler-list-panel class="handler-list"/>
            </div>
        </el-scrollbar>
    </div>
</template>

<style scoped>
.main{
    display: flex;
}
.pattern-list{
    width:43vw;
}
.handler-list{
    width: 45vw;
}
.divider{
    height:72vh;
}
</style>