<script setup>
import {ref, inject} from "vue"
import axios from "axios"

const patternList = inject('patternList')
const patternData = inject('selectedPattern')
const index = inject('selectedIndex')
const update = inject('update')
const sourceUrl = ref();
const targetUrl = ref();

const getHandlerOriginal = async ()=>{
    let handler
    await axios.get("http://localhost:8080/handler/original",{params:{simplifiedName:"limit website access"}}).then(e=>{
        handler = e.data
        console.log(e.data)
    })
    return handler
}

const postHandler = async()=>{
    let handler = await getHandlerOriginal()
    handler.sourceUrl = sourceUrl.value
    handler.targetUrl = targetUrl.value
    let json = {
        index:index.value,
        handler:handler
    }
    await axios.post("http://localhost:8080/handler/assign/onCreateAndDelete",json)
    patternData.value = null
    update.value = !update.value

}

</script>

<template>
    <div class="main">
        <div class="urls">
            <input class="url" v-model="sourceUrl" placeholder="source url"/>
            <input class="url" v-model="targetUrl" placeholder="target url"/>
        </div>
       
        <el-icon class="icon-button confirm" size="150%" @click="postHandler()"><Check /></el-icon>
        
    </div>
</template>

<style scoped>
.main{
    display: flex;
}
.urls{
    width:30vw;
}
.url{
    margin-bottom: 2vh;
}
.confirm{
    margin:auto 0;
}

</style>