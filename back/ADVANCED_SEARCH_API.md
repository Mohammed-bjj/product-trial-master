# API de Recherche Avancée de Produits

## Vue d'ensemble

L'API de recherche avancée permet de filtrer les produits selon plusieurs critères combinables. Elle supporte la pagination et offre des endpoints séparés pour les utilisateurs publics et les administrateurs.

## Endpoints disponibles

### 1. Recherche publique
```
GET /products/search
```

### 2. Recherche administrateur
```
GET /products/search/admin
```
**Autorisation requise :** `ROLE_ADMIN`

### 3. Liste des catégories
```
GET /products/categories
```

## Paramètres de recherche

Tous les paramètres sont optionnels et peuvent être combinés :

| Paramètre | Type | Description | Validation |
|-----------|------|-------------|------------|
| `keyword` | String | Recherche dans le nom, description et code du produit | 2-100 caractères |
| `category` | String | Filtrage par catégorie exacte | Caractères alphanumériques et espaces |
| `minPrice` | BigDecimal | Prix minimum | ≥ 0.0 |
| `maxPrice` | BigDecimal | Prix maximum | ≥ 0.0 |
| `inventoryStatus` | String | Statut d'inventaire | `INSTOCK`, `LOWSTOCK`, `OUTOFSTOCK` |
| `page` | Integer | Numéro de page (commence à 0) | ≥ 0 |
| `size` | Integer | Taille de la page | 1-50 |

## Exemples d'utilisation

### Recherche par mot-clé
```bash
GET /products/search?keyword=smartphone&page=0&size=10
```

### Recherche par catégorie
```bash
GET /products/search?category=Electronics&page=0&size=10
```

### Recherche par fourchette de prix
```bash
GET /products/search?minPrice=100&maxPrice=500&page=0&size=10
```

### Recherche par statut d'inventaire
```bash
GET /products/search?inventoryStatus=INSTOCK&page=0&size=10
```

### Recherche combinée
```bash
GET /products/search?keyword=phone&category=Electronics&minPrice=200&maxPrice=800&inventoryStatus=INSTOCK&page=0&size=10
```

### Obtenir toutes les catégories
```bash
GET /products/categories
```

## Réponses

### Recherche de produits
```json
{
  "content": [
    {
      "id": 1,
      "code": "PROD001",
      "name": "Smartphone XYZ",
      "description": "Un excellent smartphone",
      "image": "smartphone.jpg",
      "price": 299.99,
      "category": "Electronics",
      "quantity": 50,
      "shellId": 1,
      "internalReference": "REF001",
      "inventoryStatus": "INSTOCK",
      "rating": 4.5
    }
  ],
  "pageable": {
    "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
    },
    "pageNumber": 0,
    "pageSize": 10,
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalElements": 1,
  "totalPages": 1,
  "last": true,
  "first": true,
  "numberOfElements": 1,
  "size": 10,
  "number": 0,
  "sort": {
    "sorted": false,
    "unsorted": true,
    "empty": true
  },
  "empty": false
}
```

### Liste des catégories
```json
[
  "Electronics",
  "Clothing",
  "Books",
  "Home & Garden"
]
```

## Codes d'erreur

| Code | Description |
|------|-------------|
| 200 | Succès |
| 400 | Paramètres de requête invalides |
| 401 | Non authentifié (pour les endpoints admin) |
| 403 | Accès refusé (pour les endpoints admin) |
| 500 | Erreur serveur interne |

## Fonctionnalités de recherche

### Recherche textuelle
- **Insensible à la casse** : "smartphone" trouvera "Smartphone"
- **Recherche partielle** : "phone" trouvera "smartphone"
- **Multi-champs** : recherche dans le nom, la description et le code produit

### Filtrage par prix
- Supporte les fourchettes de prix avec `minPrice` et/ou `maxPrice`
- Peut être utilisé seul ou en combinaison avec d'autres filtres

### Filtrage par catégorie
- Correspondance exacte (insensible à la casse)
- Utilise l'endpoint `/products/categories` pour obtenir la liste des catégories disponibles

### Filtrage par statut d'inventaire
- Trois valeurs possibles : `INSTOCK`, `LOWSTOCK`, `OUTOFSTOCK`
- Filtrage exact

## Optimisations

### Performance
- Utilisation d'index sur les colonnes fréquemment recherchées
- Requêtes JPQL optimisées avec des conditions conditionnelles
- Pagination pour éviter le chargement de grandes quantités de données

### Sécurité
- Validation des paramètres d'entrée
- Autorisation basée sur les rôles pour les endpoints admin
- Protection contre l'injection SQL via JPQL paramétré

## Notes techniques

### Architecture
- **Repository** : `ProductRepository` avec requêtes JPQL personnalisées
- **Service** : `ProductService` avec logique métier de recherche
- **Controller** : `ProductRestAPI` avec endpoints REST
- **DTO** : `ProductSearchRequestDTO` pour la validation des paramètres

### Mapping
- Utilisation de MapStruct pour la conversion Entity ↔ DTO
- Gestion automatique des énumérations (InventoryStatus)

### Tests
- Tests unitaires pour le service (`ProductServiceAdvancedSearchTest`)
- Tests d'intégration pour l'API (`ProductRestAPIAdvancedSearchTest`)
