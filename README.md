# PacMan
Ce projet a été réalisé dans le but de mettre en oeuvre les connaissances acquises dans le cours de design pattern.<br/>Dans notre cas c'est un jeu Pacman intégrant un système de recherche de chemin grâce à l'algorithme A*. <br/>
Dans un premier temps les pacmans cherchent à manger des gommes tandis que les fantomes veulent manger les pacmans. <br/>
Si un pacman mange une capsule, alors il devient rouge (et les fantomes bleus) et il cherche alors à manger les fantomes. <br/>
Pour jouer il suffit de choisir un niveau, un mode de jeu (automatique, clavier ou A*) et appuyer sur la barre espace. <br/>
Le mode A* sert à tester les niveaux où il n'y a pas de fantomes (et donc ne pas enclencher de gameover) afin de tester la recherche de gommes de la part du Pacman. <br/>
Le joueur dispose de 3 vies par partie. Lorsqu'elles sont écoulées il devra relancer une partie en cliquant sur le bouton Restart. <br/>
Un slider permet de choisir la vitesse du jeu.

