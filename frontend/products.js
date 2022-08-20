let AllProducts = [];
const productsTableBody = document.querySelector("#productsTableBody");
const categories = [];

async function getAllProducts(){
  var myHeaders = new Headers();
  myHeaders.append("type", "application/json");
  myHeaders.append("Access-Control-Allow-Origin", "*")
  myHeaders.append("Content-Type", "application/json");
  let requestOptions = {
    method: 'GET',
    redirect: 'follow',
    "headers": myHeaders,
  };

  const response = await fetch("http://localhost:8080/products", requestOptions)

  if(!response.ok){
    const message = `An error has occured: ${response.status}`;
    console.error("Couldn't get all products");
    console.log(response);
    console.error(message);
    return;
  }

  const data = await response.json();
  
  // Voor we de data teruggeven, vullen we de categories array
  // Met alle mogelijkheden
  data.forEach((product) => {
    if(!categories.includes(product.category)){
      categories.push(product.category);
    }
  });
  console.log(categories);
  
  return data;
}

function populateProducts(products){
  // Hier vullen we een tabel met alle producten
  // Eerst alles leegmaken
  productsTableBody.innerHTML = "";

  for(let product of products){
    const row = document.createElement("tr");
    // Naam
    const nameEl = document.createElement("td");
    nameEl.textContent = product.name;
    row.appendChild(nameEl);
    // Omschrijving
    const descEl = document.createElement("td");
    descEl.textContent = product.description;
    row.appendChild(descEl);
    // Categorie
    const catEl = document.createElement("td");
    catEl.textContent = product.category;
    row.appendChild(catEl);
    // Prijs
    const priceEl = document.createElement("td");
    priceEl.textContent = `€ ${product.price}`
    row.appendChild(priceEl);

    const addtoCartEl = document.createElement("td");
    const addToCartButton = document.createElement("button");
    addToCartButton.textContent = "Toevoegen";
    addToCartButton.classList = "btn btn-outline-secondary"
    addToCartButton.addEventListener("click", addToCart);
    addtoCartEl.appendChild(addToCartButton);
    row.appendChild(addtoCartEl);

    productsTableBody.appendChild(row);
  }
}

function addToCart(e){
  const productName = e.srcElement.parentElement.parentElement.querySelector("td").textContent;
  console.log(productName);
  // Als de user nog niet is ingelogd dan wordt hij naar de login pagina verwezen
  if(JWT === "" && USER === ""){
    console.log("User needs to log in");
    // Redirect to login page
  window.location.replace(window.location.href.replace("index", "login"))
  }
  console.log("We can add something to the cart");
  // Find the full item object from the array of all products
  let shoppingItem = AllProducts.filter((item) => item.name === productName)[0];
  console.log("Shopping item: " + shoppingItem);
  console.log(shoppingItem);
  // Toevoegen aan de shoppingcart items in localstorage
  let shoppingCart = JSON.parse(localStorage.getItem("shoppingcart")) ?? [];
  console.log(shoppingCart);
  shoppingCart.push(shoppingItem);
  localStorage.setItem("shoppingcart", JSON.stringify(shoppingCart));
}

async function main(){
  // Eerst halen we alle producten op en tonen we deze op de site
  const allProducts = await getAllProducts();
  console.log(allProducts);
  if(allProducts && allProducts.length > 0){
    AllProducts = allProducts;
    populateProducts(AllProducts);
  }
}

main();