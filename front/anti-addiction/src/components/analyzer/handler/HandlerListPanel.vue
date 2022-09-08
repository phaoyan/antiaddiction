<script setup>
import {ref, inject} from "vue"
import DisplayHandlerPanel from "./DisplayHandlerPanel.vue"
import CreateHandlerPanel from "./CreateHandlerPanel.vue"

const patternList = inject('patternList')
const selectedPattern = inject('selectedPattern')

</script>

<template>
    <div class="box">
        <div class="header">
            <div class="label">Handlers&nbsp;</div>
        </div>
        <div class="main">
            <display-handler-panel v-if="selectedPattern == null"/>
            <create-handler-panel v-if="selectedPattern != null" :selectedPattern="selectedPattern"/>
        </div>
    </div>
</template>

<style scoped>
.box{
    display:block
}
</style>