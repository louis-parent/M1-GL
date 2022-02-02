
# VM Lisp

Le projet se compose de deux fichiers différents : le compilateur (`compiler.lisp`) et la VM (`vm.lisp`).

## Compilation

Afin de compiler du code *Lisp*, il faut se rendre dans le fichier `compiler.lisp` contenant le code du compilateur.
À la fin de ce fichier un morceau de code permet, lors de l'exécution du compilateur, d'afficher le résultat. Il est donc nécessaire de placer le code *Lisp* à cet endroit avant d'exécuter le compilateur :

```
	(write (compile_lisp '(
		; Saisir du code Lisp ici
	)))
```
 
 Le résultat du compilateur en pseudo-assembleur sera affiché dans la sortie standard.
 
## Exécution

Afin d'exécuter du pseudo-assembleur, il faut se rendre dans le fichier `vm.lisp` contenant le code de la VM.
À la fin de ce fichier 3 morceaux de code permettent respectivement d'initialiser, de charger et d'exécuter la VM :

```
	(vm_create 'Roger 40000)
```

Ce premier morceau de code permet d'initialiser une VM en lui donant un nom (ici Roger) et une taille pour la mémoire (ici 40000) dont 90% seront utilisé pour la pile et 10% pour le code.
Si celui-ci est exécuté plusieurs fois, il écrasera la VM précédemment créée avec le même nom.

```
	(vm_load 'Roger '(
		; Placer le pseudo-assembleur ici
	))
```

Ce second morceau de code permet de charger du code assembleur dans la VM. Il peut être exécuté autant de fois que nécessaire, afin d'ajouter du code dans la VM, dans la limite de la taille attribuée à la mémoire de la VM.

```
	(vm_run 'Roger)
```

Ce dernier morceau de code permet d'exécuter une VM. Il retourne la valeur finale située dans *R0*. Il n'est pas possible de l'exécuter plusieurs fois sans réinitialiser la VM.
La version alternative suivante permet d'afficher le résultat de la VM dans la sortie standard : 

```
	(write (vm_run 'Roger))
```

## Instructions Pseudo-Assembleur

En plus des instructions du pseudo-assembleur vu en cours, nous avons ajouté deux instructions supplémentaires notamment à des fins de débogage :

- `(WRITE R1)` / `(WRITE (:CONST 42))` : qui permet, respectivement, d'afficher sur la sortie standard la valeur d'un registre et la valeur d'un littéral
- `(DUMP)` : qui permet d'afficher un dump complet de la mémoire de la VM sur la sortie standard