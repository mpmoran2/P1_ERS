var apiURL = 'http://localhost:8081/mngr/approvals'

    fetch(apiURL)  
    .then(response => response.json())  
    .then(json => populateInfoBox(json))    
    .catch(err => console.log('Request Failed', err)); 