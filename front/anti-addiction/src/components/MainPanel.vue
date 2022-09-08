<script setup>
import {ref} from "vue"
import HomePanel from "./HomePanel.vue"
import RecorderPanel from "./recorder/RecorderPanel.vue"
import AnalyzerPanel from "./analyzer/AnalyzerPanel.vue"
    
let id = 0
const items = ref([
    {
        index: id++,
        title:"home"
    },
    {
        index: id++,
        title:"recorder"
    },
    {
        index: id++,
        title:"analyzer"
    }
])
const mode = ref(1)


function changePage(title, index){
    mode.value = index
}
    
</script>

<template>
    <div id="header">
        <ul id="item-bar">
            <li v-for="item in items" :key="item.index" id="item" @click="changePage(item.title, item.index)">
                {{item.title}}
            </li>
        </ul>
    </div>
    <home-panel v-if=" mode === 0" />
    <recorder-panel v-else-if=" mode === 1" />
    <analyzer-panel v-else-if=" mode === 2" />
</template>

<style scoped>
#header{
    background-color: #f3f5e9;
    height: 7vh;
}
#item-bar{
    height: 7vh;
    display:flex;
}
#item{
    height: 7vh;
    line-height: 7vh;
    width: 100%;
    text-align: center;
    font-weight: 200;
    font-size: large;
    cursor: pointer;
}
#item:hover{
    background-color: antiquewhite;
}
</style>