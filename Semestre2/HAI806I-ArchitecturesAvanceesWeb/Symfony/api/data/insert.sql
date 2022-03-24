INSERT INTO `user` (`id`, `email`, `first_name`, `last_name`, `nickname`, `password`) VALUES (1, 'jean.dupont@email.fr', 'Jean', 'Dupont', 'JeannotLapin', '1234'), (2, 'marine.le.stylo@email.com', 'Marine', 'LeStylo', 'papa>Z', '1234');

INSERT INTO `recipe` (`id`, `user_id`, `name`, `quantity_person`, `cooking_mode`, `difficulty`) VALUES (1, '1', 'Sandwich Parisien', '1', 'Crue', '1');

INSERT INTO `step` (`id`, `recipe_id`, `description`, `number`) VALUES (1, '1', 'Couper la baguette en deux', '1'), (2, '1', 'Tartiner le beurre', '2'), (3, '1', 'Placer la tranche de jambon', '3'), (4, '1', 'Déguster sur le bord de la Seine avec un petit verre de vin', '4');

INSERT INTO `ingredient` (`id`, `recipe_id`, `name`, `quantity`, `unit`, `price`) VALUES ('1', '1', 'Baguette de Pain', '1', 'unit', '0.95'), ('2', '1', 'Tranche de Jambon Fleury Michon', '4', 'unit', '2.30'), ('3', '1', 'Beurre Demi-Sel', '15', 'g', '0.18');

INSERT INTO `opinion` (`id`, `recipe_id`, `user_id`, `score`, `comments`, `date`) VALUES (1, '1', '2', '5', 'Ca c\'est la France que j\'aime ! -MLS', '2022-03-24');
