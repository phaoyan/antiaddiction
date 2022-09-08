<script setup>
import axios from "axios"
import {ref} from "vue"
import { Check, Plus } from '@element-plus/icons-vue'


const behaviorHistory = ref({})
const history = ref([])
const selectedBehaviorList = ref([])

const emit = defineEmits(['changeDisplayMode'])

const addToSelected = (index)=>{
    selectedBehaviorList.value.push(history.value[index])
}
const registerUntimedSequence = async ()=>{
    let untimedSequence
    await axios.get("http://localhost:8080/patternOriginal",{params:{patternType:"untimedSequence"}}).then((e)=>{
        untimedSequence = e.data
        untimedSequence.behaviorList = selectedBehaviorList.value
    })
    await axios.post("http://localhost:8080/patternList",untimedSequence)
    selectedBehaviorList.value = []
    emit('changeDisplayMode', 'display pattern')
}

const init = async ()=>{
    await axios.get("http://localhost:8080/history").then(e=>{
        behaviorHistory.value = e.data
        history.value = behaviorHistory.value.history
        })
}


init()

</script>

<template>
    <div class="container">
        <div class="header">
            <div class="label">Register Untimed Sequence&nbsp;</div>
            <el-icon class="icon-button" size="120%" @click="registerUntimedSequence()"><Check /></el-icon>
        </div>
        <el-container>
            <div class="behavior-list">
                <el-scrollbar>
                    <el-table
                    :data="history"
                    :show-header="false">
                        <el-table-column width="40%">
                            <template v-slot="scope">
                                <el-icon class="icon-button" @click="addToSelected(scope.$index)" ><Plus /></el-icon>
                            </template>
                        </el-table-column>
                        <el-table-column>
                            <template v-slot="scope">
                                {{history[scope.$index].name}}
                            </template>
                        </el-table-column>
                    </el-table>
                </el-scrollbar>
            </div>
            <el-divider class="divider" direction="vertical"/>
            <div class="selected-list">
                <el-table
                :data="selectedBehaviorList"
                :show-header="false">
                    <el-table-column width="40%">
                        <template v-slot="scope">
                            <div class="index">{{scope.$index + 1}}</div>
                        </template>
                    </el-table-column>
                    <el-table-column>
                        <template v-slot="scope">
                            {{selectedBehaviorList[scope.$index].name}}
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </el-container>
        
    </div>
</template>

<style scoped>

.divider{
    height:72vh !important;
}
.behavior-list, .selected-list{
    width:38vw;
    height:70vh;
    /* overflow: hidden; */
}

</style>