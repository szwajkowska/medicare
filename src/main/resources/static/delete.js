function sendSpecializationDelete(id) {
    var req = new XMLHttpRequest();
    req.open('DELETE', '/admin/specialization/' + id, true);
    req.onreadystatechange = function () {
      if(req.readyState == XMLHttpRequest.DONE && req.status == 200) {
        console.log(req.responseText);
        location.href = '/admin/specialization?specializationDeleted';

      }
    };
    req.send(null);

}

function sendDoctorDelete(id) {
    var req = new XMLHttpRequest();
    req.open('DELETE', '/admin/doctor/' + id, false);
      req.onreadystatechange = function () {
          if(req.readyState == XMLHttpRequest.DONE && req.status == 200) {
            console.log(req.responseText);
        location.href = '/admin/doctor?doctorDeleted';

          }
        };
    req.send(null);

    }
}