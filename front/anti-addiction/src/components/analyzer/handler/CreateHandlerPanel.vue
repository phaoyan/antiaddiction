<script setup>
import {inject, provide, ref} from "vue"
import axios from "axios"
import CreateCeaseComputerProcessHandlerPanel from "./createHandlers/CreateCeaseComputerProcessHandlerPanel.vue"
import CreateWebsiteRedirectionHandlerPanel from "./createHandlers/CreateWebsiteRedirectionHandlerPanel.vue"
import CreateAutoRunHandlerPanel from "./createHandlers/CreateAutoRunHandlerPanel.vue"

const patternData = inject('selectedPattern')
const index = inject('selectedIndex')
const update = inject('update')

const options = ref([
    {
        value:"cease behavior",
        label:"cease behavior",
        children:[
            {
                value:"cease computer process",
                label:"cease computer process"
            },
            {
                value:"limit website access",
                label:"limit website access"
            }
        ]
    },
    {
        value:"interrupt behavior sequence",
        label:"interrupt behavior sequence",
        children:[
            {
                value:"interrupt untimed sequence",
                label:"interrupt untimed sequence"
            }
        ]
    },
    {
        value:"auto run computer process",
        label:"auto run computer process"
    }
])
const option = ref(['default'])

const getHandlerOriginal = async (simplifiedName)=>{
    let handler
    await axios.get("http://localhost:8080/handler/original",{params:{simplifiedName:simplifiedName}}).then(e=>{
        handler = e.data
    })

    return handler
}
const postHandler = async (handler, event)=>{
    let json = {
        index:index.value,
        handler:handler
    }
    await axios.post("http://localhost:8080/handler/assign/" + event,json)
    patternData.value = null
    update.value = !update.value
}
provide('getHandlerOriginal', getHandlerOriginal)
provide('postHandler', postHandler)

</script>

<template>
    <div class="main">
        <div class="select">
            <el-cascader
            :options="options"
            v-model="option"
            placeholder="select a handler"
            :show-all-levels="false"/>
        </div>
        <create-cease-computer-process-handler-panel v-if="option[option.length-1]=='cease computer process'"/>
        <create-website-redirection-handler-panel v-if="option[option.length-1]=='limit website access'"/>
        <create-auto-run-handler-panel v-if="option[option.length-1]=='auto run computer process'"/>

    </div>
    
</template>

<style scoped>
.main{
    margin-left: 2vw;
}
.select{
    margin-top:2vh;
    margin-bottom: 2vh;
}
</style>