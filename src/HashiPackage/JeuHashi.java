package HashiPackage;

import javax.swing.JOptionPane;

public class JeuHashi  {

	/** Le plateau de jeu qui contient à la fois des objets arrete et Noeud **/
	private Object[][] board;
	
	/**Nœud utilisé à des fins de test**/
	private Noeud NoeudTest;
	
	/**
	 *  arrete utilisé à des fins de test**/
	private Arrete arreteTest;
	
	/**************************************************************************
	 * Le constructeur du HashiGame
	 **************************************************************************/
	public JeuHashi(){
		NoeudTest = new Noeud(0);
		board = new Object[7][7];	//nous jouerons avec un plateau 7 x 7
		for(int row = 0; row < 7; row++){
			for(int col = 0; col < 7; col++){
				board[row][col] = new Arrete();
			}
		}
	}
	
	/*********************************************************************
	 
* ajoute un nœud à ce plateau de jeu Hashi
* @return int renvoie 1 si le nœud a été ajouté, retourne -1 si le nœud a été annulé
* @param x la coordonnée X sur le tableau
* @param y la coordonnée Y sur le tableau
	 *********************************************************************/
	public int addNode(int x, int y,String a){
		
		if(a == null || (a != null && (a.equals(""))))   
		{
		    return -1;
		}
		int deg;
		try{
			deg = Integer.parseInt(a);
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Votre entrée n'est pas valide", "Entrée non valide", JOptionPane.WARNING_MESSAGE);
			return -1;
		}
		board[x][y] = new Noeud(deg);
		return 1;
	}
	
	/****************************************************************************************************************
	 * Trouve les connexions possibles qui peuvent se produire entre les nœuds et définit les valeurs de chaque nœud*
	 * Valeurs NoeudNord, NoeudEast, NoeudSud et NoeudOuest                                                         *
	 ****************************************************************************************************************/
	public void findPossibleConnections(){
		for(int x = 0; x < 7; x++){
			for(int y = 0; y < 7; y++){
				//vérifie si l'objet est un nœud
				if(board[x][y].getClass().equals(NoeudTest.getClass())){
					//vérifie au-dessus de ce nœud pour d'autres nœuds
					for(int y2 = y - 1; y2 >= 0; y2--){
						if(board[x][y2].getClass().equals(NoeudTest.getClass())){
							((Noeud) board[x][y]).setNoeudNord((Noeud)board[x][y2]);
							y2 = -1;
						}
					}
					//vérifie à l'ouest de ce nœud les autres nœuds
					for(int x2 = x - 1; x2 >= 0; x2--){
						if(board[x2][y].getClass().equals(NoeudTest.getClass())){
							((Noeud) board[x][y]).setNoeudOuest((Noeud)board[x2][y]);
							x2 = -1;
						}
					}
					//vérifie à l'est de ce nœud les autres nœuds
					for(int x2 = x + 1; x2 < 7; x2++){
						if(board[x2][y].getClass().equals(NoeudTest.getClass())){
							((Noeud) board[x][y]).setNoeudEast((Noeud)board[x2][y]);
							x2 = 10;
						}
					}
					//vérifie sous ce nœud pour les autres nœuds
					for(int y2 = y + 1; y2 < 7; y2++){
						if(board[x][y2].getClass().equals(NoeudTest.getClass())){
							((Noeud) board[x][y]).setNoeudSud((Noeud)board[x][y2]);
							y2 = 10;
						}
					}
				}
			}
		}
	}
	
	/**********************************************************************************************************
	 * Ajoute une arrete entre Noeud1 et Noeud2.
	 * @param n1 Nœud 1
	 * @param n2 Nœud 2
	 **********************************************************************************************************/
	public void ajoutarrete(Noeud n1, Noeud n2){
		//décrémente le nombre de degrés nécessaires aux nœuds
		n1.dec();
		n2.dec();
		//trouve la position de n1 sur la carte
		int x = 0;
		int y = 0;
		while(board[x][y] != n1){
			y = 0;
			while(board[x][y] != n1 && y < 6){
				y++;
			}
			if(board[x][y] != n1)
				x++;
		}
		//met à jour la valeur de l'arête (0, 1 ou 2)
		if(n1.getNoeudNord() == n2){
			n1.setArreteNord(n1.getArreteNord() + 1);
			n2.setArreteSud(n2.getArreteSud() + 1);
			for(int y2 = y-1; y2 >= 0; y2--){
				if(board[x][y2] == n2){
					break;
				}
				else{
					((Arrete)board[x][y2]).settypeArrete(((Arrete)board[x][y2]).gettypeArrete() + 1);
					((Arrete)board[x][y2]).setdirectionArrete(2);
				}
			}
		}
		else if(n1.getNoeudEast() == n2){
			n1.setArreteEast(n1.getArreteEast() + 1);
			n2.setArreteOuest(n2.getArreteOuest() + 1);
			for(int x2 = x+1; x2 < 7; x2++){
				if(board[x2][y] == n2){
					break;
				}
				else{
					((Arrete)board[x2][y]).settypeArrete(((Arrete)board[x2][y]).gettypeArrete() + 1);
					((Arrete)board[x2][y]).setdirectionArrete(1);
				}
			}
		}
		else if(n1.getNoeudSud() == n2){
			n1.setArreteSud(n1.getArreteSud() + 1);
			n2.setArreteNord(n2.getArreteNord() + 1);
			for(int y2 = y+1; y2 < 7; y2++){
				if(board[x][y2] == n2){
					break;
				}
				else{
					((Arrete)board[x][y2]).settypeArrete(((Arrete)board[x][y2]).gettypeArrete() + 1);
					((Arrete)board[x][y2]).setdirectionArrete(2);
				}
			}
		}
		else if(n1.getNoeudOuest() == n2){
			n1.setArreteOuest(n1.getArreteOuest() + 1);
			n2.setArreteEast(n2.getArreteEast() + 1);
			for(int x2 = x-1; x2 >= 0; x2--){
				if(board[x2][y] == n2){
					break;
				}
				else{
					((Arrete)board[x2][y]).settypeArrete(((Arrete)board[x2][y]).gettypeArrete() + 1);
					((Arrete)board[x2][y]).setdirectionArrete(1);
				}
			}
		}
		else
			System.out.println("Erreur...!!");
	}
	
	/***********************************************************************************************************
	* Supprime une arrete entre Noeud1 et Noeud 2.
	* @param n1 Nœud 1
	* @param n2 Nœud 2
	 ***********************************************************************************************************/
	public void supprimeArrete(Noeud n1, Noeud n2){
		//incrémente le nombre de degrés nécessaires aux nœuds
		n1.inc();
		n2.inc();
		//trouve la position de n1 sur la carte
		int x = 0;
		int y = 0;
		while(board[x][y] != n1){
			y = 0;
			while(board[x][y] != n1 && y < 6){
				y++;
			}
			if(board[x][y] != n1)
				x++;
		}
		//met à jour la valeur de l'arête (0, 1 ou 2)
		if(n1.getNoeudNord() == n2){
			n1.setArreteNord(n1.getArreteNord() - 1);
			n2.setArreteSud(n2.getArreteSud() - 1);
			for(int y2 = y-1; y2 >= 0; y2--){
				if(board[x][y2] == n2){
					break;
				}
				else{
					((Arrete)board[x][y2]).settypeArrete(((Arrete)board[x][y2]).gettypeArrete() - 1);
					if(((Arrete)board[x][y2]).gettypeArrete() == 0)
						((Arrete)board[x][y2]).setdirectionArrete(0);
				}
			}
		}
		else if(n1.getNoeudEast() == n2){
			n1.setArreteEast(n1.getArreteEast() - 1);
			n2.setArreteOuest(n2.getArreteOuest() - 1);
			for(int x2 = x+1; x2 < 7; x2++){
				if(board[x2][y] == n2){
					break;
				}
				else{
					((Arrete)board[x2][y]).settypeArrete(((Arrete)board[x2][y]).gettypeArrete() - 1);
					if(((Arrete)board[x2][y]).gettypeArrete() == 0)
						((Arrete)board[x2][y]).setdirectionArrete(0);
				}
			}
		}
		else if(n1.getNoeudSud() == n2){
			n1.setArreteSud(n1.getArreteSud() - 1);
			n2.setArreteNord(n2.getArreteNord() - 1);
			for(int y2 = y+1; y2 < 7; y2++){
				if(board[x][y2] == n2){
					break;
				}
				else{
					((Arrete)board[x][y2]).settypeArrete(((Arrete)board[x][y2]).gettypeArrete() - 1);
					if(((Arrete)board[x][y2]).gettypeArrete() == 0)
						((Arrete)board[x][y2]).setdirectionArrete(0);
				}
			}
		}
		else if(n1.getNoeudOuest() == n2){
			n1.setArreteOuest(n1.getArreteOuest() - 1);
			n2.setArreteEast(n2.getArreteEast() - 1);
			for(int x2 = x-1; x2 >= 0; x2--){
				if(board[x2][y] == n2){
					break;
				}
				else{
					((Arrete)board[x2][y]).settypeArrete(((Arrete)board[x2][y]).gettypeArrete() - 1);
					if(((Arrete)board[x2][y]).gettypeArrete() == 0)
						((Arrete)board[x2][y]).setdirectionArrete(0);
				}
			}
		}
		else
			System.out.println("Error: HashiGame.ajoutarrete() method: n2 is not a valid parameter for n1");
	}
	
	/*************************************************************************
	 
* Renvoie vrai si le jeu est gagné
* @return booléen  du jeu Hashi (Vrai = Gagné; Faux = Non terminé)
	 *************************************************************************/
	public boolean checkIfWon(){
		for(int x = 0; x < 7; x++){
			for(int y = 0; y < 7; y++){
				if(board[x][y].getClass().equals(NoeudTest.getClass())){
					if(((Noeud) board[x][y]).getDegree() > 0){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/***************************************************************************************
	 * Utilisé après que checkIfWon () retourne false pour vérifier s'il y en a encore
	 *  mouvements possibles à gauche. Sinon, l'état actuel du jeu n'est pas
	 * Solution.
	 * @return boolean Vrai s'il reste des mouvements possibles dans le jeu
	 ***************************************************************************************/
	public boolean checkIfPossibleMoves(){
		for(int x = 0; x < 7; x++){
			for(int y = 0; y < 7; y++){
				if(board[x][y].getClass().equals(NoeudTest.getClass())){
					if(((Noeud)board[x][y]).getDegree() > 0){
						//s'il y a un déplacement valide avec ce nœud, renvoie true
						if(isValidAdd((Noeud) board[x][y], ((Noeud) board[x][y]).getNoeudNord())){
							return true;
						}
						if(isValidAdd((Noeud) board[x][y], ((Noeud) board[x][y]).getNoeudEast())){
							return true;
						}
						if(isValidAdd((Noeud) board[x][y], ((Noeud) board[x][y]).getNoeudSud())){
							return true;
						}
						if(isValidAdd((Noeud) board[x][y], ((Noeud) board[x][y]).getNoeudOuest())){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/*************************************************************************************
	* Renvoie vrai si vous pouvez ajouter une arrete entre ces nœuds
	* @return boolean Vrai si vous pouvez ajouter une arête entre ces nœuds
	 *************************************************************************************/
	public boolean isValidAdd(Noeud n1, Noeud n2){
		//si l'un des nœuds a déjà atteint son degré MAX, ou est nul, renvoie false
		if(n1 == null || n2 == null){
			return false;
		}
		if(n1.getDegree() == 0 || n2.getDegree() == 0){
			return false;
		}
		//renvoie false si les nœuds ne sont pas liés
		if(n1.getNoeudNord() != n2 && n1.getNoeudEast() != n2 && n1.getNoeudSud() != n2 && n1.getNoeudOuest() != n2){
			return false;
		}
		//trouve la position de n1 sur la carte
		int x = 0;
		int y = 0;
		while(board[x][y] != n1){
			y = 0;
			while(board[x][y] != n1 && y < 6){
				y++;
			}
			if(board[x][y] != n1)
				x++;
		}
		//renvoie false si l'arête à ajouter est déjà à une valeur de 2
		if(n1.getNoeudNord() == n2){
			if(n1.getArreteNord() == 2)
				return false;
			else{
				//returns false if there is already an edge crossing between these two nodes
				for(int y2 = y-1; y2 >= 0; y2--){
					if(board[x][y2] == n2){
						break;
					}
					else{
						if(((Arrete)board[x][y2]).getdirectionArrete() == 1)
							return false;
					}
				}
			}
		}
		if(n1.getNoeudEast() == n2){
			if(n1.getArreteEast() == 2)
				return false;
			else{
				//renvoie false s'il y a déjà un croisement d'arête entre ces deux nœuds
				for(int x2 = x+1; x2 < 7; x2++){
					if(board[x2][y] == n2){
						break;
					}
					else{
						if(((Arrete)board[x2][y]).getdirectionArrete() == 2)
							return false;
					}
				}
			}
		}
		if(n1.getNoeudSud() == n2){
			if(n1.getArreteSud() == 2)
				return false;
			else{
				//renvoie false s'il y a déjà un croisement d'arête entre ces deux nœuds
				for(int y2 = y+1; y2 < 7; y2++){
					if(board[x][y2] == n2){
						break;
					}
					else{
						if(((Arrete)board[x][y2]).getdirectionArrete() == 1)
							return false;
					}
				}
			}
		}
		if(n1.getNoeudOuest() == n2){
			if(n1.getArreteOuest() == 2)
				return false;
			else{
				//renvoie false s'il y a déjà un croisement d'arête entre ces deux nœuds
				for(int x2 = x-1; x2 >= 0; x2--){
					if(board[x2][y] == n2){
						break;
					}
					else{
						if(((Arrete)board[x2][y]).getdirectionArrete() == 2)
							return false;
					}
				}
			}
		}
		
		return true;
	}
	
	/******************************************************************************************
	* Renvoie la plateau
	* @return board
	 ******************************************************************************************/
	public Object[][] getBoard(){
		return board;
	}
}
