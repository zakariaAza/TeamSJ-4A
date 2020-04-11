# Système initial
> ___But du jeu___ : Champ de bataille et être le dernier en vie

* Le jeu se déroulera en temps réel pour optimiser son dynamisme. Chaque joueur pourra interagir avec les autres pour s'amuser pleinement !

* Toutes les données seront stockées avec la base de donnée embarquée H2 (base de données relationnelles écrite en Java). Les informations confidentielles seront encryptées et elles aussi stockées dans la BDD embarquée afin de respecter au mieux la confidentialité des données des utilisateurs.

* Au vu du futur succès international du jeu gérer plusieurs parties en même temps (traduit par de multiples requêtes sur l'API) sera indispensable. Nous utiliserons donc le serveur d’application Wildfly (bonne performance en général, configuration rapide et consommation optimisée de la mémoire).

* Afin de limiter les risques de défaillance système nous veillerons dans un premier temps à une haute fiabilité du matériel utilisé. Dans un second temps en cas de logiciels malveillants, DDOS ou sabotages la reprise du serveur sera assurée par un système de secours (deux serveurs minimum prêts à l'emploi pour basculement).

* Pour garantir une authentification propre et épurée chaque joueur pourra se connecter avec son login et mot de passe. Pour ne pas perdre les multiples récompenses et autres bonus inédits gagnés après un dur travail de destruction des joueurs adverses une sauvegarde automatique sera mise en place. 

* Afin de gérer toute charge potentielle imprévue, nous veillerons à anticiper au mieux en mettant en place le hardware nécessaire pour le/les serveurs utilisés. 
