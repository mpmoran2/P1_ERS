let apiURL = "http://localhost:7007/emp/view_Me";

fetch(apiURL)
	.then(response => response.json)
	.then(json => populateInfo(json))
	.catch(err => console.log('Request Failed', err));
	
console.log("now we getting info");


function populateInfo(response){
	console.log(response)
	let userName = document.getElementById("userName");
  	let firstName = document.getElementById("firstName");
    let lastName = document.getElementById("lastName");
    let title = document.getElementById("title");

	userName.innerHTML(response.userName)
    firstName.innerHTML(response.firstName)
    lastName.innerHTML(response.lastName);  
    title.innerHTML(response.title);
};

