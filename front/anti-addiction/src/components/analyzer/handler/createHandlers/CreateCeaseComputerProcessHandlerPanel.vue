<script setup>
import {ref, inject} from "vue"
import axios from "axios"

const patternList = inject('patternList')
const patternData = inject('selectedPattern')
const index = inject('selectedIndex')
const update = inject('update')
const processName = ref()

const getHandlerOriginal = async ()=>{
    let handler
    await axios.get("http://localhost:8080/handler/original",{params:{simplifiedName:"cease computer process"}}).then(e=>{
        handler = e.data
    })

    return handler
}

const postHandler = async()=>{
    let handler = await getHandlerOriginal()
    
    handler.target.processName=processName.value
    let json = {
        index:index.value,
        handler:handler
    }
    await axios.post("http://localhost:8080/handler/assign/onLoop",json)
    patternData.value = null
    update.value = !update.value

}

</script>

<template>
    <div class="main">
        <div class="input">
            <input v-model="processName" placeholder="process name"/>
        </div>
        <div class="confirm">
            <el-icon class="icon-button" size="150%" @click="postHandler()"><Check /></el-icon>
        </div>
    </div>
</template>

<style scoped>
.main{
    display: flex;
    justify-content: space-between;
}

</style>

