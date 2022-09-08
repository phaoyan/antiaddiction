<script setup>
import {ref} from "vue"
import HistoryPanel from "./HistoryPanel.vue"

const visibleHeight = ref(89)
const height = ref(89)

const date = ref(null)

const displayMode = ref("brief")

const update = (realDate)=>{
    date.value = realDate.value.split(" ")[0]
}

</script>

<template>
    <el-scrollbar :height="visibleHeight + 'vh'">
        <el-container id="container">
            <el-aside id="aside"></el-aside>
            <el-main id="main">
                <div id="header">
                    <div class="first-row">
                        <div class="date" :date="date">{{date}}&nbsp;</div>
                        <el-icon size="25px" class="icon-button setting"><Setting /></el-icon>  
                    </div>
                    <div class="label hello-world">Hello World!&nbsp;</div>
                </div>
                <el-divider />
                <history-panel :displayMode="displayMode" @getDate="(realDate)=>{update(realDate)}"/>
            </el-main>
        </el-container>
    </el-scrollbar>
</template>

<style scoped>
#container{
    min-height: v-bind(height + "vh");
    background-color: #fcfdfa;
}
#aside{
    width:20vw;
    background-color: aliceblue;
    border-right: 2px solid #ffffff;
}
#main{
    overflow: hidden;
}
#header{
    height: 20vh;
    
}
.first-row{
    display: flex;
    justify-content: space-between;
}
.date{
    font-size: 30px;
    font-weight: 100;
    font-style: oblique;
    color:cadetblue;
    margin-left:3vw;
    margin-top:3vh;
}
.setting{
    margin-top:3vh;
}
.hello-world{
    text-align: center;
    height: 15vh;
    line-height: 15vh;
    font-size: 200%;
}

</style>