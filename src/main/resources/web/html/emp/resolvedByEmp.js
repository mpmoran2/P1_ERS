var apiURL ='http://localhost:7007/emp/resolved_Reimb'

    var userID = document.getElementById('userID').value;

    fetch(apiURL) 
    .then(response => response.json())  
    .then(json => pendList(json))    
    .catch(err => console.log('Request Failed', err)); 
    
    console.log("get them pending");


function resoList(response){
	console.log(response)
  	let reso = document.getElementById("resoBod");
	
	
}