    function menu(form_name) {
  var x = document.getElementById(form_name);
  if (x.style.display === "none") {
  var formsCollection = document.forms;
    for(var i=0;i<formsCollection.length;i++)
    {
    formsCollection[i].style.display = "none";
    }
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}