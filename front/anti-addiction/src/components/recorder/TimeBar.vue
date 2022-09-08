<script setup>
const props = defineProps({
    behavior:Object
})


const emit = defineEmits(["changeDisplay"])


</script>


<template>
    <div class="time">
        <p class="time-label">from&#8194;&#8194;&#8194;{{behavior.startTime.split(" ")[1]}}&#8194;&#8194;&#8194; to &#8194;&#8194;&#8194;{{behavior.endTime.split(" ")[1]}}</p>
        <el-icon class="icon-button" @click="emit('changeDisplay')"><MoreFilled /></el-icon>
    </div>
</template>

<style scoped>
.time{
    font-weight: 200;
    color:cadetblue;
    display: flex;
    justify-content: space-between;
}
.time-label{
    font-family:Consolas;
}
</style>