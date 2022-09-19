<script setup>
import {inject,provide} from "vue"
import axios from "axios"
import CeaseComputerProcessHandlerArea from "./handlerArea/CeaseComputerProcessHandlerArea.vue"
import WebsiteRedirectionHandlerArea from "./handlerArea/WebsiteRedirectionHandlerArea.vue"
import AutoRunHandlerArea from "./handlerArea/AutoRunHandlerArea.vue"

const handlerList = inject('handlerList')
const update = inject('update')

const deleteHandler = async (index)=>{
    await axios.post("http://localhost:8080/handler/delete",index,{
        headers: {
                    'Content-Type':'application/json'
                }
    })
    update.value = !update.value
}

provide('deleteHandler', deleteHandler)

</script>

<template>
    <el-scrollbar height="72vh">
        <el-table
        :data="handlerList"
        :show-header="false">
            <el-table-column>
                <template v-slot="scope">
                    <el-scrollbar height="20vh">
                        <cease-computer-process-handler-area 
                        v-if="handlerList[scope.$index][0] == 'Cease Computer Process'"
                        :index="scope.$index"/>
                        <website-redirection-handler-area 
                        v-if="handlerList[scope.$index][0] == 'Website Redirection'"
                        :index="scope.$index"/>
                        <auto-run-handler-area
                        v-if="handlerList[scope.$index][0] == 'Auto Run'"
                        :index="scope.$index"/>
                    </el-scrollbar>
                </template>
            </el-table-column>
        </el-table>
    </el-scrollbar>
</template>

<style scoped>
.empty{
    height:20vh;
    line-height: 20vh;
    text-align: center;
    font-weight: 100;
    font-size: 200%;
    font-style: oblique;
    color:gainsboro;

}
</style>