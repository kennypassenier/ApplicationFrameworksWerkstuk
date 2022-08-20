# ApplicationFrameworksWerkstuk

De frontend is te vinden in de map frontend en vereist geen extra server. Je kan gewoon de index.html pagina openen in een browser en vandaar uit vertrekken.
In de map frontend kan je ook een postman collectie vinden, klaar om te importeren. Hier staan nog 4 routes in die niet gebruikt werden in de frontend.

http://localhost:8080/forAdminsOnly is een endpoint die alleen door users met de admin rol kan bereikt worden
http://localhost:8080/forUsersOnly doet hetzelfde maar is alleen beschikbaar voor gewone users (niet voor admins)
http://localhost:8080/products/{id} is een DELETE request die enkel de admin kan bereiken
http://localhost:8080/products/ is een POST request die enkel de admin kan bereiken

###	Mogelijkheid om alle producten weer te geven in een catalogus 

De index.html pagina geeft een overzicht van alle items.

### Mogelijkheid om enkel producten van een specifieke categorie weer te geven (voeding, speelgoed, habitat), de voor jou relevante filters 

De index.html pagina laat verschillende knoppen zien, met de naam van alle beschikbare categorieën. Hierop klikken stuurt een GET request naar de backend om enkel 
de relevante items weer te geven. De knop om alles weer te geven maakt dan weer gewoon gebruik van de initiële data die door de API werd verstuurd.

### Toevoegen product aan winkelmandje

Kan ook worden teruggevonden op de index.html pagina

### Aankoop bevestigen (checkout systeem) 

Op de shoppingcart.html pagina kan men alle toegevoegde items vinden. Onderaan de tabel is een knop die alle items als bestelling doorstuurt naar de backend. 
Onder deze tabel zie je dan de bestelde items verschijnen

# Een registratiesysteem voor users

Users kunnen registreren op register.html en inloggen op de login.html pagina's.

### Een veilig login systeem(oauth2, salting, bcrypt, mogelijkheden genoeg)

Hiervoor heb ik gebruik gemaakt van JSON Web Tokens. Deze manier zorgt ervoor dat de API op een veilige manier te bereiken is.
Hiermee kunnen we ook routes beschermen aan de hand van hun  rol in het systeem. Hiervan zijn er enkele voorbeelden toegevoegd.
Om paswoorden op te slaan wordt er bcrypt gebruikt. 

Als kleine extra heb ik ook seeders toegevoegd om enkele users, rollen en producten aan te maken. 
