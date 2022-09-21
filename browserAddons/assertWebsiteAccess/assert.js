const req = new XMLHttpRequest()
const url = "http://localhost:8080/website/redirection"

req.open("GET",url)
req.onload = ()=>{
    let sourceList = JSON.parse(req.responseText)[0]
    let targetList = JSON.parse(req.responseText)[1]
    for(let i = 0; i < sourceList.length; i ++){
        if(window.location.href == sourceList[i]){
            window.location = targetList[i]
        } 
    }

}
req.send()