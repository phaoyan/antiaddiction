<script setup>
import UntimedSequenceArea from "./UntimedSequenceArea.vue"

const prop = defineProps({
    patternData:Object
})

const deletePattern = async ()=>{

}

</script>

<template>
    <el-scrollbar height="20vh">
        <untimed-sequence-area v-if="patternData.patternType == 'untimedSequence'" :patternData="patternData">
            <slot/>
        </untimed-sequence-area>
        <div class="delete-box">
            <el-icon class="icon-button delete" size="120%" @click="deletePattern()"><Delete /></el-icon>
        </div>
    </el-scrollbar>
    
</template>

<style scoped>
.delete-box{
    display: flex;
    flex-direction: row-reverse;
}
</style>