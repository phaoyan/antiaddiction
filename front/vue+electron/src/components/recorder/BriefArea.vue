<script setup>
import TimeBar from "./TimeBar.vue"

const props = defineProps({
    behavior: Object
})

const emit = defineEmits(['changeDisplay'])
</script>

<template>
    <div class="box">
        <time-bar :behavior = "behavior" @changeDisplay = "()=>{ emit('changeDisplay', 'image')}"/>
        <p class="name">{{behavior.name}}</p>
    </div>
</template>

<style scoped>
.box{
    margin-bottom: 2vh;
}

.name, .description{
    margin-top: 1vh;
    margin-left: 2vw;
    font-weight:200;
    color:#3587e4;
    font-family: 'Courier New', Courier, monospace;
}

</style>