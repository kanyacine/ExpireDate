# ExpireDate
CB+ Android stage developpeur test

ExpireDate affiche une liste de GTIN associer à une date d'expiration.
On peut ajouter un GTIN et lui donner une date d'expiration.
Faire une recherche de GTIN.
Mettre à jour une date d'expiration d'un GTIN qui existe déjà dans la liste.

Les données ne sont pas persistantes.



L'application se compose d'un champ de recherche, de deux boutons et d'un recyclerview.
Les données sont stockées dans une liste de GTINModel (objet composé d'une string répresentant le GTIN au format GTIN14 et une string représentant la date)
Le choix d'utilisation d'une liste a été fait car il est simple à mettre en place techniquement et que les données n'avaient pas besoin d'être conservées.
Le recyclerview affiche la liste de GTINModel en temps normal.
Lorsque l'on veut ajouter un GTIN un dialog permettant de choisir la date s'affiche uniquement avec des dates ultérieures car c'est simple pour l'utilisateur.
Le bouton de recherche ou la touche entrée du clavier permet d'afficher si il existe le GTIN et sa date d'expiration associée.
Un gtin invalide ou qui n'existe pas lors de la recherche ne fait rien et un message s'affiche en bas de l'application pour prevenir l'utilisateur.

Amélioration:
Utiliser une Date au lieu d'une String pour enregistrer la date dans les GTINModel pour permettre de l'afficher au format local
Utiliser une base de données pour en faire des données persisantes
Utiliser l'appareil photo pour scanner directement les produits
