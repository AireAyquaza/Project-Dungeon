************************************************************************************************************
											 Projet Dongon
											Matthieu Lepers
												   &
											Vershaeve Théo
												 2015
************************************************************************************************************

*** 1/ Introduction

	Le but du projet est de répondre à un cahier des charges imposé par nos profeseurs, d'écrire
	du "beau code" et d'utiliser son imagination.

*** 2/ Usage

	Pour utiliser correctement l'application il faut ouvrir un terminal, se rendre dans le dossier où
	se situe l'application et executer la ligne suivante:
	java -jar tp01-lepers-matthieu-verschaeve-theo.jar

	L'application se lance et vous demandera de choisir un personnage en tapant son numéro, numéro que vous 
	pouvez trouver au dessus du nom de chaque personnage.
	Un personnage aléatoire est choisi pour vous si vous ne respectez pas l'intervalle de valeur [1, 5].

	Ensuite l'application vous affiche qu'en utilisant la commande 'help' (sans les ' ), vous pouvez voir
	la liste de toutes les commandes implémentées. Commandes que vous serez sûrement forcé à utiliser un 
	moment ou un autre.

*** 2.1/ Informations sur le commande 'use item [index]'

	La commande 'use item [index]' est à préciser, en effet celle-ci ne s'éxecutera que si un nombre entier 
	compris entre 1 et 3 pour executer un effet.

	Cet index peut être trouvé en utilisant la commande 'see inventory' qui vous listera chaque objet, 
	sa quantité ainsi que son numéro d'index dans votre inventaire.

*** 2.2/ Informations sur la commande 'use spell [spell name]'

	Cette commande vous permet d'utiliser un sort, pour lister les sorts utilisables, il est hautement 
	conseillé de taper la commande 'see spells' avant d'utiliser 'use spell'.

	Cette commande n'est pas sensible à la casse ainsi qu'à l'espace des noms de sort.

*** 2.3/ Informations sur la commande 'go [direction]'

	Cette commande vous permet de vous déplacer, quand cela est possible, dans le cas contraire, il vous
	faudra réfléchir à comment vous débloquer.

	Cette commande n'est pas sensible à la casse mais est sensible à l'espaçage entre les mots, utilisez 
	plutôt les underscores _.

*** 2.4/ Les couleurs

	Les couleurs sont implémentées dans ce projet, elles sont simplement des séquences échapées en ANSI.
	Grâce à la library jansi de Hiram Chirino (http://hiramchirino.com), elles sont compatible sous tous OS.
	Elles permettent une meilleur expérience et une meilleure lisibilité.

*** 3/ Architecture

	Classes abstraites:
	- Command:			(packetage dungeon.command):
		Permet de représenter une commande saisie sur l'entrée standard. Une expression régulière permet de
		vérifier si la commande saisie est implémentée ou non, si tel est le cas, une instance de la classe
		de cette commande est créée puis éxécutée.
	- EntityLiving:		(packetage dungeon.entity):
		Représente une entité vivante, comme un monstre ou un joueur.
	- Monster:			(packetage dungeon.entity.monster):
		Représente une entité vivante qui est un monstre. Un monstre aléatoire est selectionné depuis une
		énumération dans laquelle chaque monstre y est présent, puis une instance de ce monstre est créée 
		quand besoin est.
	- ItemStack:		(packetage dungeon.inventory):
		Cette classe représente tous les objets que vous pouvez possédé. Implémentant une méthode 
		polymorphique use() qui permet d'executer l'effet de l'objet.
	- Room: 			(packetage dungeon.rooms):
		Elle est importante pour la génération du donjon car elle représente abstraitement une salle 
		quelconque, salle qui est générée aléatoirement à la manière des monstres.

	Méthodes polymorphiques:
	- execute()			(classes du packetage dungeon.commands):
		Chaque classe qui étend de Command possède cette méthode, elle définit l'action de la commande.
	- use()				(classes du packetage dungeon.inventory):
		Chaque classe qui étend de ItemStack possède cette méthode, elle définit comment est utilisé l'objet.
	- getDescription()	(classes du packetage dungeon.rooms):
		Chaque classe qui étend de Room possède cette méthode, elle affiche sur la sortie standard, la 
		description de la salle actuelle.
	- entering()		(classes du packetage dungeon.rooms):
		Chaque classe qui étend de Room possède cette méthode, elle définit ce qu'il se passe lorsque vous 
		entrez dans une salle.

	Interface:
	- Type: 			(packetage dungeon.types):
		Elle permet la compatibilité des types d'entitées lors de la création d'une EntityLiving, personnages 
		comme monstres.

*** 4/ Code Sample

	1/ Un algorithme qui a besoin d'être clarifié est le suivant:
	
	(packetage dungeon, classe Main, méthode generateRoomLinks())
	
	public void generateRoomLinks(int nbLayers)
	{
		Room bossRoom = new BossRoom("Boss Room", this.player, this);
		Room exit = new ExitRoom("Exit Room", this.player, this);
		
		int nbRooms = (3 * ((int) Math.pow(2, nbLayers - 1)));
		
		//Link the two final rooms
		bossRoom.addNextRoom(exit, Direction.values()[Direction.randomDirection()].name().toString().toLowerCase());
		
		//Root of the tree
		this.currentRoom.addNextRoom(rooms.get(1), "north");
		this.currentRoom.addNextRoom(rooms.get(2), "east");
		this.currentRoom.addNextRoom(rooms.get(3), "west");
		
		int start = 1;
		int offset = 3;
		
		//Tree layer generation
		//One iteration = one tree layer generated
		//calculation: layerRoom(n) = 2 * layerRoom(n-1)
		// with layerRoom(n) = number of rooms at the layer n
		// with n the generated layer
		while ((start + offset) <= (nbRooms / 2))
		{
			for (int i = start; i < (start + offset); i++)
			{
				int d = Direction.randomDirection();
				int d1 = (d + 1) % Direction.values().length;
				rooms.get(i).addNextRoom(rooms.get(i + offset), Direction.values()[d].name().toString().toLowerCase());
				rooms.get(i).addNextRoom(rooms.get(i + (offset + 1)), Direction.values()[d1].name().toString().toLowerCase());
			}
			
			start = start + offset;
			offset = 2 * offset;
		}
		
		//Link all tree leaf to the boss room
		for (int i = start; i < (start + offset); i++)
		{
			int d = Direction.randomDirection();
			rooms.get(i).addNextRoom(bossRoom, Direction.values()[d].name().toString().toLowerCase());
		}
	}
	
	Commençons par le paramètre, il permet de définir le nombre total de salle qui ont été générées via la 
	méthode dungeon::Main.generateRoomsList(). Pour obtenir ce nombre, il suffit de suivre l'expression
	suivante:
	
		int nbRooms = (3 * ((int) Math.pow(2, nbLayers - 1)));
	
	nbLayer est connue, il est choisi aléatoirement dans l'interval [5, 10].
	
	Le but de cette méthode est de créé un arbre de salles comme suit:
	- La racine est la salle d'entrée
	- La premier niveau de l'arbre est composé de 3 salles
	- le nombre de salles L(n) est défini avec la suite : L(n) = 2 * L(n - 1) avec L(0) = 3.
	
	La génération ce termine quand on a atteind nbRooms / 2 itérations
	L'étape finale est de faire pointer chaque feuille de l'arbre vers la salle du Boss.
	La salle du Boss est toujours l'avant dernière salle, elle est toujours liée à la salle de sortie.
	
	2/ Une optimisation:
	
	L'optimisation suivante apparait 3 fois dans le projet pour les classes suivantes:
	- Command
	- ItemStack
	- Room
	
	(packetage dungeon, classe Main, méthode generateRoomLinks())
	
	public void interpretCommand(String c)
	{
		String regex = CommandType.craftRegex();
		
		if (c.matches(regex))
		{
			String commandLabel = c.replaceAll(regex, "$1");
			commandLabel = commandLabel.toUpperCase().replaceAll(" ","_");
			String commandArgv = c.replaceAll(regex, "$2");
			
			Command command;
			try
			{
				command = (Command) CommandType.valueOf(commandLabel).getCommandClass().newInstance();
				command.setAttributes(commandArgv, this);
				command.execute();
			}
			catch (Exception e)
			{
				//Will not appear
			}
		}
		else
		{
			System.out.println("This command is not implemented!");
		}
	}
	
	Il est important de souligner que le bloc catch est vide, pour la simple raison que deux exceptions
	peuvent être levées en utilisant la méthode newInstance(), ces deux exception ont été "annulées" en
	quelque sorte grâce à la gestion faite sur la classe abtraite 'Command'. Ces exception peuvent être
	produites à cause de l'absence d'un constructeur nul ou d'un problème de visibilité de la classe, ce
	qui ne sera ici jamais le cas.
	
	L'énumération CommandType regroupe toutes les commandes, leur possible argument ainsi que leur 
	description. La methode generateRegex() permet de créer une expression régulière qui va filtrer une entrée
	utilisateur affin de savoir si la commande saisie est implémentée ou non. Dans le cas où elle ne l'est 
	pas, l'application en prévient l'utilisateur. Dans le cas où elle est implémentée, on récupère la 
	constante énumérée. À partir de cette constante on récupère la classe associé à la commande tapée et on
	instancie un objet du type de la commande tapée (exemple: une instance de GoCommand si l'utilisateur à
	tapé 'go'). La commande se voit attribuée ces attributs grâce à un setter afin de permettre l'éxécution 
	de celle-ci sans risque d'exception. Enfin elle est executée.
	
************************************************************************************************************