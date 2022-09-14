<script setup>
import {ref, inject} from "vue"


const sourceUrl = ref();
const targetUrl = ref();

const getHandlerOriginal = inject('getHandlerOriginal')
const postHandlerToBack = inject('postHandler')

const postHandler = async ()=>{
    let handler = await getHandlerOriginal('limit website access')
    handler.sourceUrl = sourceUrl.value
    handler.targetUrl = targetUrl.value
    postHandlerToBack(handler, 'onCreateAndDelete')
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