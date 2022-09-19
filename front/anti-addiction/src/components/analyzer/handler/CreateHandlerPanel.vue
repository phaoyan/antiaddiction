<script setup>
import {inject, provide, ref} from "vue"
import axios from "axios"
import VueForm from "@lljj/vue3-form-element"
import ceaseComputerProcessHandlerSchema from "@/assets/json/schema/handler/ceaseComputerProcessHandler.json"
import websiteRedirectionHandlerSchema from "@/assets/json/schema/handler/websiteRedirectionHandler.json"
import autoRunHandlerSchema from "@/assets/json/schema/handler/AutoRunHandler.json"
import footer from "@/assets/json/schema/ui/formFooter.json"


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
        value:"auto run",
        label:"auto run"
    }
])
const option = ref(['default'])

const handler = ref({})
const postHandler = async (handler,schema)=>{
    let json = {
        handler:handler,
        schema:schema
    }
    await axios.post("http://localhost:8080/handler/create",json)
    patternData.value = null
    update.value = !update.value
    console.log("test")
}

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
    </div>
    <vue-form
    v-if="option[option.length-1]=='cease computer process'"
    v-model="handler"
    :schema="ceaseComputerProcessHandlerSchema"
    :formFooter="footer"
    @submit="postHandler(handler, ceaseComputerProcessHandlerSchema)"/>
    <vue-form
    v-if="option[option.length-1]=='limit website access'"
    v-model="handler"
    :schema="websiteRedirectionHandlerSchema"
    :formFooter="footer"
    @submit="postHandler(handler, websiteRedirectionHandlerSchema)"/>
    <vue-form
    v-if="option[option.length-1]=='auto run'"
    v-model="handler"
    :schema="autoRunHandlerSchema"
    :formFooter="footer"
    @submit="postHandler(handler, autoRunHandlerSchema)"/>

    
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