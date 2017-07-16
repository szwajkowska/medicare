function sendSpecializationDelete(id) {
    var req = new XMLHttpRequest();
    req.open('DELETE', '/admin/specialization/' + id, true);
    req.send(null);

    if(req.status == 200) {
        console.log(req.responseText);
        location.href = '/admin/specialization?specializationDeleted';
//alert("Usunięto specjalizację");
    }
}

function sendDoctorDelete(id) {
    var req = new XMLHttpRequest();
    req.open('DELETE', '/admin/doctor/' + id, true);
    req.send(null);

    if(req.status == 200) {
        console.log(req.responseText);
        location.href = '/admin/doctor?doctorDeleted';
//alert("Usunięto lekarza");
    }
}