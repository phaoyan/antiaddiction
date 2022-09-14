<script setup>
import {ref} from "vue"
import CreateCeaseComputerProcessHandlerPanel from "./createHandlers/CreateCeaseComputerProcessHandlerPanel.vue"
import CreateWebsiteRedirectionHandlerPanel from "./createHandlers/CreateWebsiteRedirectionHandlerPanel.vue"

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
    }
])
const option = ref(['default'])

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