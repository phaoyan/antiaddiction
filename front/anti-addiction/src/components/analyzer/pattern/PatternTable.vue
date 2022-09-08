<script setup>
import UntimedSequenceArea from "./UntimedSequenceArea.vue"
import PatternArea from "./PatternArea.vue"

const prop = defineProps({
    patternList:[],
    from:String
})


</script>

<template>
    <el-scrollbar>
        <el-table
        :data="patternList"
        :show-header="false">
            <el-table-column>
                <template v-slot="scope">
                    <pattern-area :patternData="patternList[scope.$index]">
                        <slot :index="scope.$index"/>
                    </pattern-area>
                </template>
            </el-table-column>
        </el-table>
    </el-scrollbar>
</template>

<style scoped>

</style>