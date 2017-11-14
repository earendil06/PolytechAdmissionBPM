# Admission à Polytech
##### Authors :
 - Anthony Fusco
 - Florent Pastor
 - Jérémy Froment
 - Aurélien Curtis

#### Description du projet :

Ce projet présente l'implémentation du processus d'admission à l'école Polytech.
Nous avons avons restreint notre scope au cas de l'école Polytech Nice-Sophia pour une admission en 3ème année du cycle ingénieur.
Nous avons utilisé [Flowable][df1] comme moteur BPMN.

#### Pros/Cons :
Nous avons listé les principaux avantages et inconvénients de notre solution :
 - Pros :
    -  Automatisation de parties qui étaient faites à la main
    -  Nous facilitons le transfert de données entre les acteurs
    -  La partie d'organisation des entretiens est mockée et le flot est implémenté
 - Cons :
    - Processus d'organisation non implémenté
    - Pour le moment le contrôle se fait par ligne de commande mais qu'il doit être controllable en REST plus tard
    - Les différentes filières pour candidater n'ont pas été implémentées 
    - Il faut ajouter des évènement dans le temps
    - Ajouter les cas d'erreur



#### Deploiement :
Notre solution utilise peut être utilisée avec docker.
Il suffit tout d'abord de build le projet avec la commande :
```sh
$ ./build.sh
```

Ensuite il est possible de lancer la solution par la commande :
```sh
$ cd ./start.sh
```

[df1]: <http://www.flowable.org>