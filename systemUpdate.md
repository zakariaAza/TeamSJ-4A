## Mise à jour du système


On mettra en place un système de supervision. Cela nous permettra de comprendre le comportement du jeu en condition réelle et d'être alerté lorsqu'un problème apparaît.
La supervision pourra être également utilisée pour prévenir une charge imprévue.

---

Si une coupure de service intervient lors d'une partie en cours, celle-ci sera interrompue. La progression de la partie aura été sauvegardée (à chaque récompense) pour que le joueur n'ait pas à repartir de zéro.

---

Pour se faire, il faudrait que l'API soit rétrocompatible. Entre autre, que les nouvelles fonctions implémenter puissent aussi permettre aux anciennes d'être fonctionnels, et pour se faire on pourra utiliser du polymorphisme ou la surcharge de méthodes.

---

Les joueurs pourront être averti par un envoi de mail qui leurs fera savoir qu'une nouveauté est apparue dans le jeu.

--- 
 
*Bonus* 
> Système d'objets bonus, des objets pourront apparaître aléatoirement sur la carte pour permettre aux joueurs de tirer des projectiles plus rapides, sur plusieurs directoires différents, ou être plus rapide.