<script setup>
import DisplayPatternPanel from "./pattern/DisplayPatternPanel.vue"
import RegisterBehaviorSetPanel from "./pattern/RegisterBehaviorSetPanel.vue"
import RegisterUntimedSequencePanel from "./pattern/RegisterUntimedSequencePanel.vue"
import RegisterTimedSequencePanel from "./pattern/RegisterTimedSequencePanel.vue"
import {ref} from "vue"

const displayMode = ref('display pattern')

const changeDisplay = (mode)=>{
    displayMode.value = mode
}

</script>

<template>
    <display-pattern-panel v-if="displayMode === 'display pattern'" @changeDisplayMode="mode=>changeDisplay(mode)"/>
    <register-behavior-set-panel v-if="displayMode === 'register set'" @changeDisplayMode="mode=>changeDisplay(mode)"/>
    <register-untimed-sequence-panel v-if="displayMode === 'register untimed'" @changeDisplayMode="mode=>changeDisplay(mode)"/>
    <register-timed-sequence-panel v-if="displayMode === 'register timed'" @changeDisplayMode="mode=>changeDisplay(mode)"/>
</template>

<style scoped>

</style>