const reqOnload = new XMLHttpRequest();
const reqOnUnload = new XMLHttpRequest();
const urlAdd = "http://localhost:8080/website/record/add"
const urlRemove = "http://localhost:8080/website/record/remove"


window.onload = ()=>{
    reqOnload.open("POST",urlAdd)
    reqOnload.setRequestHeader("Content-type","application/json");
    reqOnload.onload = ()=>{
        if (reqOnload.readyState==4 && reqOnload.status==200){
                console.log(window.location.href)
        }
    }
    reqOnload.send(window.location.href)
}

window.onbeforeunload = ()=>{
    reqOnload.open("POST",urlRemove)
    reqOnload.setRequestHeader("Content-type","application/json");
    reqOnload.onload = ()=>{
        if (reqOnload.readyState==4 && reqOnload.status==200){
                console.log(window.location.href)
        }
    }
    reqOnload.send(window.location.href)
}