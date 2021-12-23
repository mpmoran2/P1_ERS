var apiURL = 'http://localhost:8081/mngLogin'

function LogMng(){
	var userName = document.getElementById('userName').value;
	var userPass = document.getElementById('userPass').value;
	
	fetch(apiURL+''+userName+''+userPass) 
	.then(response => response.json())
	.catch(err => console.log('Request Failed',err));
}