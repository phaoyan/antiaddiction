<script>
</script>

<template>
<div id="menu">
    <div id="title">
        anti-addiction
    </div>
    <div id="exit">
        <el-icon onclick="window.close()" id="exit-img"><Close /></el-icon>
    </div>
</div>
</template>

<style scoped>
#menu {
    -webkit-app-region: drag;
    width: 100vw;
    height: 4vh;
    display: flex;
    justify-content: space-between;
    background-color: #ecf1de;
}
#title {
    line-height: 4vh;
    margin-left: 1vh;
    font-weight: 300;
}
#exit {
    width: 8vw;
    height: 4vh;
    text-align: center;

}
#exit-img {
    height: 70%;
    margin-top: 10%;
    -webkit-app-region: no-drag;
    cursor: pointer;
}
#exit:hover {
    background-color: lightcoral;
}
</style>