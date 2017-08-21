function sendVisitCancel(visitId) {
    var req = new XMLHttpRequest();
    var r = confirm("Jesteś pewien że chcesz odwołać wizytę?");
    if (r == true) {
    req.open('POST', '/my_visits/' + visitId, true);
      req.onreadystatechange = function () {
       if (req.readyState === XMLHttpRequest.DONE && req.status === 500){
              alert(req.responseText)
          }
          if(req.readyState == XMLHttpRequest.DONE && req.status == 200) {
          location.href = '/my_visits?visitCanceled';
          }
        };
        }
    else{
    }
    req.send(null);
    }


