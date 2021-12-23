var apiURL = 'http://localhost:7007/emp/reimb'

function submitReimb(){
	var userID = document.getElementById("userID")
	var amount = document.getElementById("amount")
	var category = document.getElementById("category")
	
	fetch(apiURL+''+userID+''+amount+''+category)
	.then(response => response.json())
	.then(json=> passIt(json))
	.catch(err => console.log('Somethin wrong',err))
	
}

function passIt(){
	var data = document.getElementById('thebox');
	data.innerHTML='Submitted';
}