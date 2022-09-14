<script setup>
import {ref, inject} from "vue"
import axios from "axios"
import PatternArea from "./patternArea/PatternArea.vue";


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
        <el-table
        :data="patternList"
        :show-header="false">
            <el-table-column>
                <template v-slot="scope">
                    <pattern-area :patternData="patternList[scope.$index]">
                        <slot :index="scope.$index"/>
                    </pattern-area>
                </template>
            </el-table-column>
        </el-table>
    </el-scrollbar>
</template>

<style scoped>

</style>