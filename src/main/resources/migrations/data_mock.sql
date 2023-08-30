INSERT INTO author (id_author,name,first_name) VALUES
(1,"Sutherland","Krystal"),
(2,"Steel","Daniel"),
(3,"Buchi","Angelina"),
(4,"Combes","Bruno"),
(5,"Todd","Anna"),
(6,"Roberts","Emma"),
(7,"Garcia","Miguel"),
(8,"Lopez","Isabella"),
(9,"Nguyen","David"),
(10,"Kim","Sophia");


INSERT INTO book (id_book,title,description,type,id_author,id_category) VALUES
(1,"Nos coeurs en déssacord","Le livre suit l'histoire de Henry Page, un adolescent animé par un rêve romantique intense.","fiction romantique",1,3),
(2,"Double reflet","Le livres suit l'histoire de deux soeurs jumelles pendant la deuxième guerre","aventure",2,2),
(3,"Tatoo moi","Le livre suit l'histoire d'une femme soumise d'un mari violent","violance conjugale",3,1),
(4,"Ce que je n'oserai jamias te dire","Le livre  suit l'histoire du destin de deux amants qui s'aime avec des lourds secret","roman",4,3),
(5,"After","Le livre suit l'histoire de Tessa Young,une adolescente promise à une avenir tout tracé jusqu'à son rencontre avec Hardin Smith.","fiction romantique",5,3),
(6,"L'Énigme du passé","Un thriller captivant où un détective doit résoudre un mystère complexe.","thriller",6,3),
(7,"Les Chemins de l'aventure","Trois amis partent dans une aventure inattendue à travers le monde.","aventure",7,2),
(8,"Échappée nocturne","Un roman sombre qui explore les côtés obscurs de la vie urbaine.","roman noir",8,1),
(9,"Le Sourire oublié","L'histoire émouvante d'une quête pour retrouver un sourire perdu.","drame",9,3),
(10,"Le Souffle du vent","Un récit poétique sur la nature et la philosophie de l'existence.","poésie",10,3);

-- Ajouter des catégories 
INSERT INTO category (id_category,name) VALUES
(1,"Adult"),
(2,"Jeune"),
(3,"Fiction"),
(4,"Mystère"),
(5,"Science-Fiction"),
(6,"Biographie");

-- Ajouter des membres
INSERT INTO member (id_member,name,first_name) VALUES
(1, 'Smith', 'John'),
(2, 'Johnson', 'Emily'),
(3, 'Williams', 'Michael'),
(4, 'Brown', 'Jessica'),
(5, 'Jones', 'William'),
(6, 'Davis', 'Olivia'),
(7, 'Martinez', 'Liam'),
(8, 'Miller', 'Ava'),
(9, 'Garcia', 'Noah'),
(10, 'Rodriguez', 'Emma');

-- Insérer des emprunts
INSERT INTO borrow (idBook, idMember, startDate, endDate, isReturned)
VALUES
(1, 1, '2023-08-01', '2023-08-15', 1),
(2, 2, '2023-08-02', '2023-08-16', 0),
(3, 3, '2023-08-03', '2023-08-17', 1),
(4, 4, '2023-08-04', '2023-08-18', 0),
(5, 5, '2023-08-05', '2023-08-19', 1),
(1, 6, '2023-08-06', '2023-08-20', 0),
(2, 7, '2023-08-07', '2023-08-21', 1),
(3, 8, '2023-08-08', '2023-08-22', 0),
(4, 9, '2023-08-09', '2023-08-23', 1),
(5, 10, '2023-08-10', '2023-08-24', 0);
