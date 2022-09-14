<script setup>
import {ref, inject} from "vue"
import { Check, Edit, MoreFilled, Delete } from '@element-plus/icons-vue'
import axios from "axios"

const datumTable = ref()
const bodyHeight = ref(72)
const environmentDatum = ref()
const selectedDatumList = ref([])

const behaviorsTable = ref()
const behaviorList = ref([])
const selectedBehaviorList = ref([])

const update = inject('update')

const emit = defineEmits(['bodyHeight','behaviorList'])

const confirm = async ()=>{
    datumTable.value.clearSelection()
    behaviorList.value.push({
        name:"undefined",
        overallEnvironment:{
            moment:"1000-01-01 01:01:01",
            datum:selectedDatumList.value
        }
    })
    postBehaviorList()
}

const deleteDatum = async()=>{
    for(let i = 0; i < selectedDatumList.value.length; i ++){
        if(selectedDatumList.value[i].processName != null){
            await axios.post("http://localhost:8080/ignoreList",selectedDatumList.value[i].processName,{    
                headers: {
                    'Content-Type':'application/json'
                }
            })
        }
        if(selectedDatumList.value[i].url != null){
            await axios.post("http://localhost:8080/ignoreList",selectedDatumList.value[i].url,{    
                headers: {
                    'Content-Type':'application/json'
                }
            })
        }
    }
    datumTable.value.clearSelection()
    await axios.get("http://localhost:8080/environment").then(e=>{
        environmentDatum.value = e.data
    })
    update.value = !update.value

}

const deleteBehavior = (index)=>{
    behaviorList.value = behaviorList.value.filter((behavior)=>behaviorList.value.indexOf(behavior) != index)
    postBehaviorList()
}

const select = (selection)=>{
    selectedDatumList.value = selection
}

const postBehaviorList = async ()=>{
    console.log(behaviorList.value)
    await axios.post("http://localhost:8080/behavior/registered", behaviorList.value)
    await axios.post("http://localhost:8080/priorityList", behaviorList.value)
}

const move = (index,direction)=>{
    if((index == 0 && direction==-1) || (index == behaviorList.value.length)){
        return
    }
    let temp = behaviorList.value[index]
    behaviorList.value[index] = behaviorList.value[index + direction]
    behaviorList.value[index + direction] = temp
    postBehaviorList()
}

const display = (data)=>{
    if(data.processName != undefined){
        // console.log(data.processName)
        return data.processName
    }else if(data.url != undefined){
        // console.log(data.url)
        return data.urlWithoutParams
    }
}

const init = async ()=>{
    await axios.get("http://localhost:8080/environment").then(e=>{
        environmentDatum.value = e.data
    })
    await axios.get("http://localhost:8080/behavior/registered").then(e=>{
        behaviorList.value = e.data
    })

    emit('behaviorList',behaviorList)
}



init()

</script>

<template>
    <el-container id="body">
        <el-aside id="environment-datum">
            <div class="header">
                <p class="label" style="display:inline">Environment &nbsp; </p>
                <el-button id="confirm" type="primary" :icon="Check" color="green" plain @click="confirm()"/>
                <el-icon class="icon-button" @click="deleteDatum()"><Delete /></el-icon>
            </div>
            <el-scrollbar :height="(bodyHeight - 3) + 'vh'">
                <el-table 
                id="datum-table" 
                :data="environmentDatum" 
                :show-header="false" 
                ref="datumTable"
                @select="selection=>select(selection)">
                    <el-table-column type="selection" />
                    <el-table-column>
                        <template v-slot="scope">
                            {{display(environmentDatum[scope.$index])}}
                        </template>
                    </el-table-column>
                </el-table>
            </el-scrollbar>

        </el-aside>
        <el-divider class="vertical-divider" direction="vertical"/>
        <el-aside id="registered-behaviors">
            <p class="label">Registered Behaviors</p>
            <el-scrollbar height="69vh">
                <el-table 
                :data="behaviorList"
                :show-header="false"
                ref="behaviorsTable">
                    <el-table-column prop="name">
                        <template v-slot="scope">
                            <el-collapse>
                                <el-collapse-item :title="behaviorList[scope.$index].name">
                                    <template v-for="data in behaviorList[scope.$index].overallEnvironment.datum" :key="data">
                                        <div class = "data-display">&nbsp;&nbsp;&nbsp;{{display(data)}}</div>
                                    </template>
                                    <div class="delete-row">
                                        <el-icon class="icon-button" @click="deleteBehavior(scope.$index)"><Delete /></el-icon>
                                    </div>
                                </el-collapse-item>
                            </el-collapse>                            
                        </template>
                    </el-table-column>
                    <el-table-column label="edit" width="40%" >
                        <template v-slot="scope">
                            <el-popover
                            placement="top-start"
                            :width="200"
                            trigger="hover">
                                <template #reference>
                                    <el-icon class="icon-button"><Edit /></el-icon>
                                </template>
                                <el-form :model="behaviorList[scope.$index]">
                                    <el-form-item label="name">
                                        <el-input 
                                        v-model="behaviorList[scope.$index].name"
                                        @change="postBehaviorList()"/>
                                    </el-form-item>
                                    <div class="move">
                                        <el-icon class="icon-button" @click="move(scope.$index,-1)"><SortUp /></el-icon>
                                        &nbsp;&nbsp;&nbsp;
                                        <el-icon class="icon-button" @click="move(scope.$index,1)"><SortDown /></el-icon>
                                    </div>
                                </el-form>
                            </el-popover>
                        </template>
                    </el-table-column>
                </el-table>
            </el-scrollbar>
        </el-aside>
    </el-container>
</template>

<style scoped>
#body{
    justify-content: space-between;
}
#environment-datum, #registered-behaviors{
    height: 72vh;
    width: 40vw;
    overflow: hidden;
}
#confirm{
    height: 1em;
    padding-right: 1em;
}
.vertical-divider{
    height: 72vh;
}
.delete-row{
    overflow: unset;
    height: 2vh;
    display: flex;
    flex-direction: row-reverse;
}
.delete-row > .icon-button{
    margin-right: 0.8vw;
    position: relative;
    bottom: -20px;
}
.move{
    display: flex;
    flex-direction: row-reverse;
}

</style>

