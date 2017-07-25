function clear() {
    var doc = document.getElementById('doctors');
    while (doc.firstChild) {
        doc.removeChild(doc.firstChild);
    }
}

function getDoctors(id){
    var req = new XMLHttpRequest();
    req.open('GET', '/doctors?specializationId=' + id, true);
    req.onreadystatechange = function(){
    if (req.readyState === XMLHttpRequest.DONE && req.status === 500){
        clear();
    }
    if (req.readyState === XMLHttpRequest.DONE && req.status === 200) {
        var doctors = JSON.parse(req.responseText);
        var doc = document.getElementById('doctors');

        clear();
        var sel = document.createElement('select')
        sel.setAttribute("form", "newVisit");
        sel.name = "doctorId";

        doctors.forEach(function(doctor) {
                var opt = document.createElement('option')
                opt.text = doctor.firstName + ' ' + doctor.lastName;
                opt.value = doctor.id;
                sel.append(opt);
        });
        if(doctors.length > 0 ) {
        doc.append(sel);
        }

    }};
    req.send(null);
}