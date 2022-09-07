<script setup>

const prop = defineProps({
    name:String
})

const emit = defineEmits(['deleteHandler'])

const deleteHandler = ()=>{
    emit('deleteHandler')
}

</script>

<template>
    <div class="header">
        <div class="title">{{name}}&nbsp;</div>
        <el-icon class="icon-button" size="120%" @click="deleteHandler()"><Delete /></el-icon>
    </div>
</template>