function logFormat(data, div) {
    for (const key of Object.keys(data)) {
        const createdAt = data[key].createdAt ? JSON.stringify(data[key].createdAt) : '';
        const value = data[key].value ? JSON.stringify(data[key].value) : '';
        div.innerHTML += "Date" + ":&nbsp;&nbsp;" + createdAt.replace('"', "").replace('"', "") + " &nbsp;&nbsp;- Log:&nbsp;&nbsp;" + value.replace('"', "").replace('"', "") + "<br/>";
    }
}


function submitLogPost(name) {
    let url = "/logs";
    let xhttp = new XMLHttpRequest();
    
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
           
            let div = document.getElementById("listLogs");
            div.innerHTML = "10 Logs: <br/>";
            let data = JSON.parse(this.responseText);
            console.log(data);
            logFormat(data, div);
        }
    };
    
    xhttp.open("POST", url, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    let requestBody = JSON.stringify({ value: name.value });
    xhttp.send(requestBody);
}

function submitLogGet() {
    let url = "/logs";
    let xhttp = new XMLHttpRequest();
    
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
           
            let div = document.getElementById("listLogs");
            div.innerHTML = "10 Logs: <br/>";
            let data = JSON.parse(this.responseText);
            console.log(data);
            logFormat(data, div);
        }
    };
    
    xhttp.open("GET", url, true);
    xhttp.send();
}



function sendMessage(name) {
    event.preventDefault();
    
    method = name.value == "" ? 'GET' : 'POST';
   
    if (method == 'POST') {
        submitLogPost(name);
    } else {
        submitLogGet();
    }


}