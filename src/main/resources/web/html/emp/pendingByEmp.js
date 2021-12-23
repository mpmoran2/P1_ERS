var apiURL ='http://localhost:7007/emp/pending_Reimb'

function pendingByEmp() {
    var userID = document.getElementById('userID').value;

    fetch(apiURL+'/'+userID) 
    .then(response => response.json())  
    .then(json => pendList(json))    
    .catch(err => console.log('Request Failed', err)); 
    
    console.log("get them things");
}

function pendList(response){
   //* var data = document.getElementById('listy');
//	data.innerHTML='';
//
//	for(i=0;i<response.length;i++){
//    	var idTag = document.createElement('h4');
//       var info= response[i].userID +"  "+response[i].reimbID +" "+response[i].category +" "+response[i].amount +" "+response[i].status +" "
//        
//        idTag.innerHTML=info;
//        data.appendChild(idTag);
//    }
	 console.log(response)
	 var dataSection = document.getElementById('tableBody');
	 dataSection.innerHTML='';
	 
	 for(i=0;i<response.length;i++){
		var row = document.createElement('tr');
		row.innerHTML='';
	  
		var col1 = document.createElement('td');
		var data = response[i].userID;
		col1.innerHTML=data;
		row.appendChild(col1)
		
		var col2 = document.createElement('td');
		var data = response[i].reimbID;
		col2.innerHTML=data;
		row.appendChild(col2);
		 
		var col3 = document.createElement('td');
		var data = response[i].amount;
		col3.innerHTML=data;
		row.appendChild(col3);
			 
		var col4 = document.createElement('td');
		var data = response[i].category;
		col4.innerHTML=data;
		row.appendChild(col4);
		 
		var col5 = document.createElement('td');
		var data = response[i].status;
		col5.innerHTML=data;
		row.appendChild(col5);
		
			
		dataSection.appendChild(row);
	}	
}