const registerButton = document.querySelector("#registerButton");
const username = document.querySelector("#usernameInput");
const password = document.querySelector("#passwordInput");
const firstName = document.querySelector("#firstNameInput");
const lastName = document.querySelector("#lastNameInput");
const street = document.querySelector("#streetInput");
const houseNr = document.querySelector("#houseNrInput");
const city = document.querySelector("#cityInput");
const postalCode = document.querySelector("#postalCodeInput");

async function register(){
  let myHeaders = new Headers();
  myHeaders.append("type", "application/json");
  myHeaders.append("Access-Control-Allow-Origin", "*")
  myHeaders.append("Content-Type", "application/json");
  

  let raw = JSON.stringify({
    "userName": username.value,
    "firstName": firstName.value,
    "lastName": lastName.value,
    "password": password.value,
    "street": street.value,
    "city": city.value,
    "houseNumber": houseNr.value,
    "postalCode": postalCode.value
  });

  let requestOptions = {
    method: 'POST',
    headers: myHeaders,
    body: raw,
    redirect: 'follow',
    "mode": "no-cors",
  };

  const response = await fetch("http://localhost:8080/registerNewUser", requestOptions)

  if(!response.ok){
    const message = `An error has occured: ${response.status}`;
    console.error(message);
    return;
  }
  const data = await response.json();
  return data;
}

registerButton.addEventListener("click", async function(){
  // Credentials uit het formulier halen
  const username = usernameInput.value;
  const password = passwordInput.value;
  console.log(username, password);

  loginInfo = await register(username, password);
  // Als de credentials fout waren, stopt het hier
  console.log(loginInfo);
  if(loginInfo == undefined){
    return;
  } 
  // Redirect to login page
  window.location.replace(window.location.href.replace("register", "login"))
});