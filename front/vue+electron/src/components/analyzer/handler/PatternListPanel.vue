<script setup>
import {ref, inject} from "vue"
import axios from "axios"
import PatternTable from "../pattern/PatternTable.vue"


const patternList = inject('patternList')
const selectedPattern = inject('selectedPattern')
const selectedIndex = inject('selectedIndex')


const addHandler = (index)=>{
    selectedPattern.value = patternList.value[index]
    selectedIndex.value = index
}



</script>

<template>
    <el-scrollbar height="72vh">
        <div class="header">
            <div class="label">Patterns&nbsp;</div>
        </div>
        <pattern-table 
        :patternList="patternList"
        v-slot="scope">
            <el-icon class="icon-button" size="150%" @click="addHandler(scope.index)" v-if="selectedIndex != scope.index"><Guide /></el-icon>
            <el-icon class="icon-button" size="150%" @click="selectedIndex=-1;selectedPattern=null" v-if="selectedIndex == scope.index"><EditPen /></el-icon>
        </pattern-table>
    </el-scrollbar>
</template>

<style scoped>

</style>