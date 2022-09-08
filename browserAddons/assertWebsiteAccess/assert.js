const req = new XMLHttpRequest()
const url = "http://localhost:8080/website/redirection"


req.open("GET",url)
req.onload = ()=>{
    let sourceList = JSON.parse(req.responseText)[0]
    let targetList = JSON.parse(req.responseText)[1]
    for(let i = 0; i < sourceList.length; i ++){
        console.log(sourceList)
        console.log(targetList)
        // console.log(sourceList[i])
        // console.log(window.location.href)
        if(window.location.href == sourceList[i]){
            window.location = targetList[i]
            // console.log("success")
        } 
        // console.log("test")


    }

}
req.send()