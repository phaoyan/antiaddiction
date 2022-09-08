<script setup>

const prop = defineProps({
    patternData: Object
})



</script>

<template>
    <div class="header">
        <div class="title">untimed sequence&nbsp;</div>
        <slot></slot>
    </div>
    <el-scrollbar>
        <div class="details">
            <div class="behaviors" v-for="behavior in patternData.behaviorList" :key="behavior">
                {{behavior.name}}
            </div>    
        </div>
    </el-scrollbar>
</template>

<style scoped>
.behaviors{
    margin-top: 1vh;
    margin-left: 2vw;
    font-weight:200;
    color:#3587e4;
}

</style>