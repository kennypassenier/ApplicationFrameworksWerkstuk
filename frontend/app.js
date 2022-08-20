const BACKEND_URL = "http://localhost:8080/";
const logoutLink = document.querySelector("#logoutLink");

let JWT = "";
let USER = "";


function saveToStorage(key, value){
  localStorage.setItem(key, value);
}

function loadFromStorage(key){
  return localStorage.getItem(key);
}



function logout(){
  // Reset de data omtrent de user
  saveToStorage("JWT", "");
  saveToStorage("username", "");
  // Refresh pagina
  location.reload();
}

async function main(){
  // Eerst kijken we of we nog een JWT token hebben van een vorige sessie
  JWT = loadFromStorage("JWT");
  USER = loadFromStorage("username");
  console.log(JWT, USER);
  // Als deze niet null zijn, dan is de user nog ingelogd
  if(JWT !== "" && USER !== ""){
    console.log("User is ingelogd");
    // Enkel de logout knop tonen
    logoutLink.style.display = "block";
  }
  else{
    console.log("User is niet ingelogd");
    // Anders tonen we de login en registreer knoppen
    loginLink.style.display = "block";    
    registerLink.style.display = "block";
  }

  
}


// Event listeners
logoutLink.addEventListener("click", logout);



main();