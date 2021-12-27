var apiURL ='http://localhost:7007/emp/pending_Reimb'


    var userID = document.getElementById('userID').value;

    fetch(apiURL) 
    .then(response => response.json())  
    .then(json => pendList(json))    
    .catch(err => console.log('Request Failed', err)); 
    
    console.log("get them pending");


function pendList(response){
	console.log(response)
	let pend = document.getElementById("pendBod");
	dataSection.innerHTML = '';

	for (i = 0; i < response.length; i++) {
		//alert()response[i].id)
		var idTag = document.createElement('li');
		var data = "Id: " + response[i].reimbID +", Amount: " + response[i].amount + 
		",  Status: " + response[i].status;
		//",  Password: " + response[i].pswd;

		idTag.innerHTML = data;
		dataSection.appendChild(idTag);
	}
	
	
}