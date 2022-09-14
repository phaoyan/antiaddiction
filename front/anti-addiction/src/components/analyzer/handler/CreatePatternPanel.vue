<script setup>
import {ref, provide, inject} from "vue"
import axios from "axios"
import CreateUntimedSequencePanel from "./createPatterns/CreateUntimedSequencePanel.vue"

const behaviorList = ref([]);
provide('behaviorList',behaviorList)

const patternList = inject('patternList')

const options = ref([
    {
        value:"untimed sequence",
        label:"untimed sequence"
    },
    {
        value:"timed sequence",
        label:"timed sequence"
    },
    {
        value:"behavior set",
        label:"behavior set"
    },
])
const option = ref(['default'])
provide('option',option)

const getBehaviorList = async ()=>{
    await axios.get("http://localhost:8080/behavior/registered").then((e)=>{
        behaviorList.value = e.data
        console.log(behaviorList.value)
    })
}

const postPatternList = async ()=>{
    await axios.post("http://localhost:8080/pattern/list", patternList.value)
}

const init = async ()=>{
    await getBehaviorList()
}
init()

</script>

<template>
    <div class="main">
        <div class="select">
            <el-cascader
            :options="options"
            v-model="option"
            placeholder="select a pattern type"
            :show-all-levels="false"/>
        </div>
        <create-untimed-sequence-panel v-if="option[option.length-1]=='untimed sequence'"/>
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