const loginButton = document.querySelector("#loginButton");
const usernameInput = document.querySelector("#usernameInput");
const passwordInput = document.querySelector("#passwordInput");


async function login(username, password) {
  // Deze functie returned een User object, samen met het JWT token
  let myHeaders = new Headers();
  myHeaders.append("type", "application/json");
  myHeaders.append("Content-Type", "application/json");
  
  
  let raw = JSON.stringify({
    "userName": username,
    "userPassword": password
  });
  
  let requestOptions = {
    method: 'POST',
    headers: myHeaders,
    body: raw,
    redirect: 'follow'
  };
  
  const loginUrl = `${BACKEND_URL}authenticate`
  
  const response = await fetch(loginUrl, requestOptions)
  
  
  if(!response.ok){
    const message = `An error has occured: ${response.status}`;
    console.error(message);
    return;
  }
  
  const data = await response.json();
  return data;  
}

loginButton.addEventListener("click", async function(){
  // Credentials uit het formulier halen
  const username = usernameInput.value;
  const password = passwordInput.value;
  console.log(username, password);

  loginInfo = await login(username, password);
  // Als de credentials fout waren, stopt het hier
  console.log(loginInfo);
  if(loginInfo == undefined){
    return;
  }  
  saveToStorage("JWT", loginInfo.jwtToken);
  saveToStorage("username", loginInfo.user.userName);  
  // Redirect to index
  window.location.replace(window.location.href.replace("login", "index"))
});

  