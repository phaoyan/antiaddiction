<script setup>
import {ref, provide} from "vue"
import AssignHandlerPanel from "./handler/AssignHandlerPanel.vue"
import RegisterBehaviorPanel from "./RegisterBehaviorPanel.vue"
import RegisterPatternPanel from "./RegisterPatternPanel.vue"

const options = [
    {
        value:"register behaviors",
        label:"register behaviors"
    },{
        value:"assign handlers",
        label:"assign handlers"
    },{
        value:"register patterns",
        label:"register patterns"
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
                <assign-handler-panel v-if="display == 'assign handlers'" :key="update"/>
                <register-behavior-panel v-if="display == 'register behaviors'" @behaviorList="behaviors=>behaviorList=behaviors" :key="update"/>
                <register-pattern-panel v-if="display == 'register patterns'" :behaviorList="behaviorList"/>

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