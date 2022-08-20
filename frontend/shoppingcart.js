const shoppingTableBody = document.querySelector("#shoppingTableBody");
const deliveredTableBody = document.querySelector("#deliveredTableBody");
const orderButton = document.querySelector("#orderButton");

async function main(){
  // Elke keer we op deze pagina komen kijken we of we de relevante data
  // Uit de localstorage kunnen halen
  let cart = JSON.parse(localStorage.getItem("shoppingcart")) ?? [];
  console.log(cart);
  // Hiermee bevolken we dan de tabel met items
  populateShoppingTable(cart);

  // We ontvangen ook alle items die we al ooit besteld hebben
  // Vanuit de backend en zetten al die data in een andere table
  let oldDeliveries = await getDeliveriesByName(USER);
  populateDeliveredTable(oldDeliveries);
}

async function getDeliveriesByName(username){
  var myHeaders = new Headers();
  myHeaders.append("Authorization", `Bearer ${JWT}`);  

  var requestOptions = {
    method: 'GET',
    headers: myHeaders,
    redirect: 'follow'
  };
  let newUrl = `http://localhost:8080/deliveries/owner/${USER}`;
  const response = await fetch(newUrl, requestOptions)
  if(!response.ok){
    const message = `An error has occured: ${response.status}`;
    console.error(message);
    return;
  }
  const data = await response.json();
  return data;
}

function populateShoppingTable(items){
  shoppingTableBody.innerHTML = "";

  for(let item of items){
    const row = document.createElement("tr");
    // Naam
    const nameEl = document.createElement("td");
    nameEl.textContent = item.name;
    row.appendChild(nameEl);
    // Omschrijving
    const descEl = document.createElement("td");
    descEl.textContent = item.description;
    row.appendChild(descEl);
    // Categorie
    const catEl = document.createElement("td");
    catEl.textContent = item.category;
    row.appendChild(catEl);
    // Prijs
    const priceEl = document.createElement("td");
    priceEl.textContent = `€ ${item.price}`
    row.appendChild(priceEl);

    const removeFromCartEl = document.createElement("td");
    const removeFromCartButton = document.createElement("button");
    removeFromCartButton.textContent = "Verwijder";
    removeFromCartButton.classList = "btn btn-outline-secondary"
    removeFromCartButton.addEventListener("click", removeFromCart);
    removeFromCartEl.appendChild(removeFromCartButton);
    row.appendChild(removeFromCartEl);

    shoppingTableBody.appendChild(row);
    
  }
}

function removeFromCart(e){
  const productName = e.srcElement.parentElement.parentElement.querySelector("td").textContent;
  console.log(productName);  
  console.log("We can remove something to the cart");
  
  let shoppingCart = JSON.parse(localStorage.getItem("shoppingcart")) ?? [];
  // Verwijder element van array
  let deleted = false;
  shoppingCart.forEach((item, index, arr) => {
    if(item.name === productName && deleted === false){
      arr.splice(index, 1);    
      deleted = true;
    }
  });
  // Save the new list and update the UI
  localStorage.setItem("shoppingcart", JSON.stringify(shoppingCart));
  populateShoppingTable(shoppingCart);
}

orderButton.addEventListener("click", async function(){
  console.log("Afrekenen");
  // Om af te rekenen maken we een fetch request naar de backend
  // Met daarin alle items uit onze cart
  let shoppingCart = JSON.parse(localStorage.getItem("shoppingcart")) ?? [];
  // Enkel als er daadwerkelijk items in de mand zitten
  if(shoppingCart.length === 0){ return; }

  // Voor elk item in de winkelmand maken we een nieuwe Delivery aan op de backend
  for(let item of shoppingCart){
    await postDelivery(item);
    //De items die zijn verzonden naar de server kunnen
    // bij de afgelopen bestelllingen staan
    let deliveredItems = JSON.parse(localStorage.getItem("delivered")) ?? [];
    deliveredItems.push(item);
    localStorage.setItem("delivered", JSON.stringify(deliveredItems));    
  }
  shoppingCart = [];
  localStorage.setItem("shoppingcart", JSON.stringify(shoppingCart));
  populateShoppingTable(shoppingCart);
  let oldDeliveries = await getDeliveriesByName(USER);
  populateDeliveredTable(oldDeliveries);
});

function populateDeliveredTable(deliveredItems){
  deliveredTableBody.innerHTML = "";  
  for(let item of deliveredItems){
    const row = document.createElement("tr");
    // Naam
    const nameEl = document.createElement("td");
    nameEl.textContent = item.productName;
    row.appendChild(nameEl);    
    // Prijs
    const priceEl = document.createElement("td");
    priceEl.textContent = `€ ${item.price}`
    row.appendChild(priceEl);    

    deliveredTableBody.appendChild(row);
  }
}


async function postDelivery(item){
  var myHeaders = new Headers();
  myHeaders.append("type", "application/json");  
  myHeaders.append("Authorization", `Bearer ${JWT}`);

  myHeaders.append("Content-Type", "application/json");

  console.log(USER, item.price, item.name);
  
  
  var raw = JSON.stringify({
    "owner": USER,
    "price": item.price,
    "productName": item.name
  });
  console.log(raw);
  
  var requestOptions = {
    method: 'POST',
    headers: myHeaders,
    body: raw,
    redirect: 'follow'
  };
  
  const response = await fetch("http://localhost:8080/deliveries", requestOptions);
  if(!response.ok){
    const message = `An error has occured: ${response.status}`;
    console.error(message);
    return;
  }
  return response.ok;  
}

main();