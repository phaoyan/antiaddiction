<script setup>
import {ref, provide} from "vue"
import AssignHandlerPanel from "./handler/PatternAndHandlerPanel.vue"
import RegisterBehaviorPanel from "./RegisterBehaviorPanel.vue"

const options = [
    {
        value:"register behaviors",
        label:"register behaviors"
    },{
        value:"patterns & handlers",
        label:"patterns & handlers"
    }
]

const display = ref("register behaviors");
const update = ref(true)
const behaviorList = ref();

provide('display', display)
provide('update',update)

</script>

<template>
    <el-scrollbar height="89vh">
        <el-container>
            <el-main id="main">
                <el-cascader :options="options" placeholder="register behaviors" @change="value=>display=value"/>
                <el-divider />
                <assign-handler-panel v-if="display == 'patterns & handlers'" :key="update"/>
                <register-behavior-panel v-if="display == 'register behaviors'" @behaviorList="behaviors=>behaviorList=behaviors" :key="update"/>

            </el-main>
        </el-container>
    </el-scrollbar>
</template>

<style scoped>
#main{
    background-color: #fcfdfa;
    min-height: 89vh;
    overflow: hidden;
}
</style>