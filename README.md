# Consignes

- Vous êtes développeur front-end : vous devez réaliser les consignes décrites dans le chapitre [Front-end](#Front-end)

- Vous êtes développeur back-end : vous devez réaliser les consignes décrites dans le chapitre [Back-end](#Back-end) (*)

- Vous êtes développeur full-stack : vous devez réaliser les consignes décrites dans le chapitre [Front-end](#Front-end) et le chapitre [Back-end](#Back-end) (*)

(*) Afin de tester votre API, veuillez proposer une stratégie de test appropriée.

## Front-end

Le site de e-commerce d'Alten a besoin de s'enrichir de nouvelles fonctionnalités.

### Partie 1 : Shop

- Afficher toutes les informations pertinentes d'un produit sur la liste
- Permettre d'ajouter un produit au panier depuis la liste 
- Permettre de supprimer un produit du panier
- Afficher un badge indiquant la quantité de produits dans le panier
- Permettre de visualiser la liste des produits qui composent le panier.

### Partie 2

- Créer un nouveau point de menu dans la barre latérale ("Contact")
- Créer une page "Contact" affichant un formulaire
- Le formulaire doit permettre de saisir son email, un message et de cliquer sur "Envoyer"
- Email et message doivent être obligatoirement remplis, message doit être inférieur à 300 caractères.
- Quand le message a été envoyé, afficher un message à l'utilisateur : "Demande de contact envoyée avec succès".

### Bonus : 

- Ajouter un système de pagination et/ou de filtrage sur la liste des produits
- On doit pouvoir visualiser et ajuster la quantité des produits depuis la liste et depuis le panier 

## Back-end

### Partie 1

Développer un back-end permettant la gestion de produits définis plus bas.
Vous pouvez utiliser la technologie de votre choix parmi la liste suivante :

- Node.js/Express
- Java/Spring Boot
- C#/.net Core
- PHP/Symphony : Utilisation d'API Platform interdite

Un produit a les caractéristiques suivantes : 

``` typescript
class Product {
  id: number;
  code: string;
  name: string;
  description: string;
  image: string;
  category: string;
  price: number;
  quantity: number;
  internalReference: string;
  shellId: number;
  inventoryStatus: "INSTOCK" | "LOWSTOCK" | "OUTOFSTOCK";
  rating: number;
  createdAt: number;
  updatedAt: number;
}   
```

Le back-end créé doit pouvoir gérer les produits dans une base de données SQL/NoSQL ou dans un fichier json.

### Partie 2

- Imposer à l'utilisateur de se connecter pour accéder à l'API.
  La connexion doit être gérée en utilisant un token JWT.  
  Deux routes devront être créées :
  * [POST] /account -> Permet de créer un nouveau compte pour un utilisateur avec les informations fournies par la requête.   
    Payload attendu : 
    ```
    {
      username: string,
      firstname: string,
      email: string,
      password: string
    }
    ```
  * [POST] /token -> Permet de se connecter à l'application.  
    Payload attendu :  
    ```
    {
      email: string,
      password: string
    }
    ```
    Une vérification devra être effectuée parmi tout les utilisateurs de l'application afin de connecter celui qui correspond aux infos fournies. Un token JWT sera renvoyé en retour de la reqûete.
- Faire en sorte que seul l'utilisateur ayant le mail "admin@admin.com" puisse ajouter, modifier ou supprimer des produits. Une solution simple et générique devra être utilisée. Il n'est pas nécessaire de mettre en place une gestion des accès basée sur les rôles.
- Ajouter la possibilité pour un utilisateur de gérer un panier d'achat pouvant contenir des produits.
- Ajouter la possibilité pour un utilisateur de gérer une liste d'envie pouvant contenir des produits.

## Bonus

Vous pouvez ajouter des tests Postman ou Swagger pour valider votre API


# Travail réalisé


La partie back-end est terminée conformément aux consignes. L'API permet la gestion :
 - ### Produit (CRUD)
 - ### Panier  (CRUD)
 - ### WishList (CRUD)

Sur le plan technique :

- Architecture en couches

- DTO (input et output)

- Mapper

- Gestion centralisée des exceptions

- Entités

 - Validator

 - Documentation Swagger
 - DockerFile et Docker compose
 - batterie teste postman

 [Scénario du test pour user ](./droit%20user.postman_collection.json)
[Scénario du test pour admin](./droit%20admin.postman_collection.json)

![Swagger](./Capture%20d’écran%20du%202025-08-29%2010-17-03.png)




La partie front-end a bien avancé, notamment :

- L'interface Admin

 - La page de connexion (sign in et sign out)

 - Je travaille sur l'interface utilisateur, l'amélioration du code et de l'architecture de l'application, ainsi que la connexion entre le back-end et le front-end



Pour lancer l'application facilement, il faut installer Docker et Docker Compose, puis l'exécuter avec une seule commande :
     -  docker-compose up --build  pour démarrer
     -  docker-compose down -v pour arreter


Compte Admin :
 - email : admin@admin.com
 - password : figuig

Compte User :
- email : user1@example.com
- password : figuig


Interfaces Admin  : 


![Etat de stock](./stateStocke.png)
![Gestion des produit](./gestion-produit.png)



Interface User : 

![List Produit](./list-product.png)
![Gestion Panier](./gestion-panier.png)
![Gestion mes Favoris](./gestion-wishlist.png)
![Contact](./contact.png)
