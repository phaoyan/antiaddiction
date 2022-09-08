<script setup>
import {inject} from "vue"
import CeaseComputerProcessHandlerArea from "./handlerArea/CeaseComputerProcessHandlerArea.vue"
import WebsiteRedirectionHandlerArea from "./handlerArea/WebsiteRedirectionHandlerArea.vue"

const patternList = inject('patternList')

</script>

<template>
    <el-scrollbar height="72vh">
        <el-table
        :data="patternList"
        :show-header="false">
            <el-table-column>
                    <template v-slot="scope">
                        <el-scrollbar height="20vh">
                            <cease-computer-process-handler-area 
                            v-if="patternList[scope.$index].handler != null && patternList[scope.$index].handler.simplifiedName == 'cease computer process'"
                            :index="scope.$index"/>
                            <website-redirection-handler-area 
                            v-if="patternList[scope.$index].handler != null && patternList[scope.$index].handler.simplifiedName == 'limit website access'"
                            :index="scope.$index"/>
                            <div class="empty" v-if="patternList[scope.$index].handler == null">
                                    no handler
                            </div>
                        </el-scrollbar>
                    </template>
            </el-table-column>
        </el-table>
    </el-scrollbar>
</template>

<style scoped>
.empty{
    height:20vh;
    line-height: 20vh;
    text-align: center;
    font-weight: 100;
    font-size: 200%;
    font-style: oblique;
    color:gainsboro;

}
</style>