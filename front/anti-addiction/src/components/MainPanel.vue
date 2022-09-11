<script setup>
import {ref} from "vue"
import HomePanel from "./HomePanel.vue"
import RecorderPanel from "./recorder/RecorderPanel.vue"
import AnalyzerPanel from "./analyzer/AnalyzerPanel.vue"
    
const items = ref([
    {
        title:"home"
    },
    {
        title:"recorder"
    },
    {
        title:"analyzer"
    }
])
const mode = ref("home")


function changePage(title){
    mode.value = title
}
    
</script>

<template>
    <div id="header">
        <ul id="item-bar">
            <li v-for="item in items" :key="item" id="item" @click="changePage(item.title)">
                {{item.title}}
            </li>
        </ul>
    </div>
    <home-panel v-if=" mode === 'home'" />
    <recorder-panel v-else-if=" mode === 'recorder'" />
    <analyzer-panel v-else-if=" mode === 'analyzer'" />
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