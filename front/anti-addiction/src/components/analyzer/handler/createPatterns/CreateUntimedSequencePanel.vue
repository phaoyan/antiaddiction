<script setup>
import {ref, inject} from "vue"
import axios from "axios"

const behaviorList = inject('behaviorList')
const patternList = inject('patternList')
const option = inject('option')
const mode = inject('mode')
const selectedIndexList = ref([])



const display = (data)=>{
    if(data.processName != null)
        return data.processName
    if(data.url != null)
        return data.url
}

const select = (index)=>{
    selectedIndexList.value.push(index)
}

const confirm = async ()=>{
    let untimedSequence
    await axios.get("http://localhost:8080/pattern/original",{params:{patternType:"untimedSequence"}}).then((e)=>{
        untimedSequence = e.data
    })
    selectedIndexList.value.forEach((index)=>{
        untimedSequence.behaviorList.push(behaviorList.value[index])
    })
    patternList.value.push(untimedSequence)
    axios.post("http://localhost:8080/pattern/list", patternList.value)
    option.value = []
    mode.value = 'display'
}
</script>

<template>
    <div>
        <el-table 
        :data="behaviorList"
        :show-header="false"
        class="behavior-list">
            <el-table-column width="40%">
                <template v-slot="scope">
                    <el-icon 
                    class="icon-button" 
                    @click="select(scope.$index)"
                    v-if="selectedIndexList.indexOf(scope.$index)===-1"><Position /></el-icon>
                    <div 
                    class="index"
                    v-if="selectedIndexList.indexOf(scope.$index)!=-1">
                        {{selectedIndexList.indexOf(scope.$index) + 1}}
                    </div>
                </template>
            </el-table-column>
            <el-table-column prop="name">
                <template v-slot="scope">
                    <el-collapse>
                        <el-collapse-item :title="behaviorList[scope.$index].name">
                            <template v-for="data in behaviorList[scope.$index].overallEnvironment.datum" :key="data">
                                <div class = "data-display">&nbsp;&nbsp;&nbsp;{{display(data)}}</div>
                            </template>
                        </el-collapse-item>
                    </el-collapse>                            
                </template>
            </el-table-column>
        </el-table>  
        <div class="confirm">
            <el-icon class="icon-button check" size="150%" @click="confirm()"><Check /></el-icon>
        </div>
    </div>
</template>

<style scoped>
.index{
    color:cadetblue;
    font-style:oblique;
}
.confirm{
    display:flex;
    flex-direction: row-reverse;
}
.check{
    margin-right:3vw;
    margin-top:1vh;
}
</style>