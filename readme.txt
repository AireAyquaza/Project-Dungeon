************************************************************************************************************
											 Projet Dongon
											Matthieu Lepers
												   &
											Vershaeve Th�o
												 2015
************************************************************************************************************

*** 1/ Introduction

	Le but du projet est de r�pondre � un cahier des charges impos� par nos profeseurs, d'�crire
	du "beau code" et d'utiliser son imagination.

*** 2/ Usage

	Pour utiliser correctement l'application il faut ouvrir un terminal, se rendre dans le dossier o�
	se situe l'application et executer la ligne suivante:
	java -jar tp01-lepers-matthieu-verschaeve-theo.jar

	L'application se lance et vous demandera de choisir un personnage en tapant son num�ro, num�ro que vous 
	pouvez trouver au dessus du nom de chaque personnage.
	Un personnage al�atoire est choisi pour vous si vous ne respectez pas l'intervalle de valeur [1, 5].

	Ensuite l'application vous affiche qu'en utilisant la commande 'help' (sans les ' ), vous pouvez voir
	la liste de toutes les commandes impl�ment�es. Commandes que vous serez s�rement forc� � utiliser un 
	moment ou un autre.

*** 2.1/ Informations sur le commande 'use item [index]'

	La commande 'use item [index]' est � pr�ciser, en effet celle-ci ne s'�xecutera que si un nombre entier 
	compris entre 1 et 3 pour executer un effet.

	Cet index peut �tre trouv� en utilisant la commande 'see inventory' qui vous listera chaque objet, 
	sa quantit� ainsi que son num�ro d'index dans votre inventaire.

*** 2.2/ Informations sur la commande 'use spell [spell name]'

	Cette commande vous permet d'utiliser un sort, pour lister les sorts utilisables, il est hautement 
	conseill� de taper la commande 'see spells' avant d'utiliser 'use spell'.

	Cette commande n'est pas sensible � la casse ainsi qu'� l'espace des noms de sort.

*** 2.3/ Informations sur la commande 'go [direction]'

	Cette commande vous permet de vous d�placer, quand cela est possible, dans le cas contraire, il vous
	faudra r�fl�chir � comment vous d�bloquer.

	Cette commande n'est pas sensible � la casse mais est sensible � l'espa�age entre les mots, utilisez 
	plut�t les underscores _.

*** 2.4/ Les couleurs

	Les couleurs sont impl�ment�es dans ce projet, elles sont simplement des s�quences �chap�es en ANSI.
	Gr�ce � la library jansi de Hiram Chirino (http://hiramchirino.com), elles sont compatible sous tous OS.
	Elles permettent une meilleur exp�rience et une meilleure lisibilit�.

*** 3/ Architecture

	Classes abstraites:
	- Command:			(packetage dungeon.command):
		Permet de repr�senter une commande saisie sur l'entr�e standard. Une expression r�guli�re permet de
		v�rifier si la commande saisie est impl�ment�e ou non, si tel est le cas, une instance de la classe
		de cette commande est cr��e puis �x�cut�e.
	- EntityLiving:		(packetage dungeon.entity):
		Repr�sente une entit� vivante, comme un monstre ou un joueur.
	- Monster:			(packetage dungeon.entity.monster):
		Repr�sente une entit� vivante qui est un monstre. Un monstre al�atoire est selectionn� depuis une
		�num�ration dans laquelle chaque monstre y est pr�sent, puis une instance de ce monstre est cr��e 
		quand besoin est.
	- ItemStack:		(packetage dungeon.inventory):
		Cette classe repr�sente tous les objets que vous pouvez poss�d�. Impl�mentant une m�thode 
		polymorphique use() qui permet d'executer l'effet de l'objet.
	- Room: 			(packetage dungeon.rooms):
		Elle est importante pour la g�n�ration du donjon car elle repr�sente abstraitement une salle 
		quelconque, salle qui est g�n�r�e al�atoirement � la mani�re des monstres.

	M�thodes polymorphiques:
	- execute()			(classes du packetage dungeon.commands):
		Chaque classe qui �tend de Command poss�de cette m�thode, elle d�finit l'action de la commande.
	- use()				(classes du packetage dungeon.inventory):
		Chaque classe qui �tend de ItemStack poss�de cette m�thode, elle d�finit comment est utilis� l'objet.
	- getDescription()	(classes du packetage dungeon.rooms):
		Chaque classe qui �tend de Room poss�de cette m�thode, elle affiche sur la sortie standard, la 
		description de la salle actuelle.
	- entering()		(classes du packetage dungeon.rooms):
		Chaque classe qui �tend de Room poss�de cette m�thode, elle d�finit ce qu'il se passe lorsque vous 
		entrez dans une salle.

	Interface:
	- Type: 			(packetage dungeon.types):
		Elle permet la compatibilit� des types d'entit�es lors de la cr�ation d'une EntityLiving, personnages 
		comme monstres.

*** 4/ Code Sample

	1/ Un algorithme qui a besoin d'�tre clarifi� est le suivant:
	
	(packetage dungeon, classe Main, m�thode generateRoomLinks())
	
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
	
	Commen�ons par le param�tre, il permet de d�finir le nombre total de salle qui ont �t� g�n�r�es via la 
	m�thode dungeon::Main.generateRoomsList(). Pour obtenir ce nombre, il suffit de suivre l'expression
	suivante:
	
		int nbRooms = (3 * ((int) Math.pow(2, nbLayers - 1)));
	
	nbLayer est connue, il est choisi al�atoirement dans l'interval [5, 10].
	
	Le but de cette m�thode est de cr�� un arbre de salles comme suit:
	- La racine est la salle d'entr�e
	- La premier niveau de l'arbre est compos� de 3 salles
	- le nombre de salles L(n) est d�fini avec la suite : L(n) = 2 * L(n - 1) avec L(0) = 3.
	
	La g�n�ration ce termine quand on a atteind nbRooms / 2 it�rations
	L'�tape finale est de faire pointer chaque feuille de l'arbre vers la salle du Boss.
	La salle du Boss est toujours l'avant derni�re salle, elle est toujours li�e � la salle de sortie.
	
	2/ Une optimisation:
	
	L'optimisation suivante apparait 3 fois dans le projet pour les classes suivantes:
	- Command
	- ItemStack
	- Room
	
	(packetage dungeon, classe Main, m�thode generateRoomLinks())
	
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
	peuvent �tre lev�es en utilisant la m�thode newInstance(), ces deux exception ont �t� "annul�es" en
	quelque sorte gr�ce � la gestion faite sur la classe abtraite 'Command'. Ces exception peuvent �tre
	produites � cause de l'absence d'un constructeur nul ou d'un probl�me de visibilit� de la classe, ce
	qui ne sera ici jamais le cas.
	
	L'�num�ration CommandType regroupe toutes les commandes, leur possible argument ainsi que leur 
	description. La methode generateRegex() permet de cr�er une expression r�guli�re qui va filtrer une entr�e
	utilisateur affin de savoir si la commande saisie est impl�ment�e ou non. Dans le cas o� elle ne l'est 
	pas, l'application en pr�vient l'utilisateur. Dans le cas o� elle est impl�ment�e, on r�cup�re la 
	constante �num�r�e. � partir de cette constante on r�cup�re la classe associ� � la commande tap�e et on
	instancie un objet du type de la commande tap�e (exemple: une instance de GoCommand si l'utilisateur �
	tap� 'go'). La commande se voit attribu�e ces attributs gr�ce � un setter afin de permettre l'�x�cution 
	de celle-ci sans risque d'exception. Enfin elle est execut�e.
	
************************************************************************************************************