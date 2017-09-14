function clearVisits() {
    var vis = document.getElementById('visits');
    while (vis.firstChild) {
        vis.removeChild(vis.firstChild);
    }
}

function getVisits(id){
    var req = new XMLHttpRequest();
    req.open('GET', '/visits?doctorId=' + id, true);
    req.onreadystatechange = function(){
    if (req.readyState === XMLHttpRequest.DONE && req.status === 500){
        clearVisits(); //dlaczego tu jest clear?
    }
    if (req.readyState === XMLHttpRequest.DONE && req.status === 200) {
        var visits = JSON.parse(req.responseText);
        var vis = document.getElementById('visits');

        clearVisits();

        var ul = document.createElement('ul');
        ul.setAttribute("name", "visitId");

        visits.forEach(function(visit){
        var li = document.createElement('li');
        li.textContent = new Date(visit.date).toLocaleString();
        li.setAttribute("value", visit.id);
        var button = document.createElement('button');
        var t = document.createTextNode("Umów wizytę");
        button.appendChild(t);
        document.body.appendChild(button);
        button.onclick = function(){setAppointment(visit.id, id);}
        li.appendChild(button);
        ul.append(li);
        });
        if(visits.length > 0){
        vis.append(ul);
        }
    }
    };
    req.send(null);
}

function setAppointment(visitId, doctorId){
    alert("Umówić wizytę w tym terminie?");
    var req = new XMLHttpRequest();
    req.open('POST', '/visits?visitId=' + visitId, true);
    req.onreadystatechange = function(){
    if (req.readyState === XMLHttpRequest.DONE && req.status === 500){
        alert(req.responseText)
    }
    if (req.readyState === XMLHttpRequest.DONE && req.status === 200) {
        location.href = '/?visitReserved'
    }
    }
    req.send(null);
}