var apiURL = "http://localhost:7007/emp/view_Me";

fetch(apiURL)
	.then(response => response.json)
	.then(json => populateInfo(json))
	.catch(err => console.log('Request Failed', err));
	
console.log("now we here");


function populateInfo(){
	console.log(response)
  	var dataSection = document.getElementById('responseBod');
    dataSection.innerHTML='';
	
	for(i=0;i<response.length;i++){
        //alert(response[i].id)
        var idTag = document.createElement('h3');
        var data=response[i].firstName +"  "+response[i].lastName;

        idTag.innerHTML = data;
        dataSection.appendChild(idTag);
    }   
};

