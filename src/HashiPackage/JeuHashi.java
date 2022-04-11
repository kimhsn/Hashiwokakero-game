package HashiPackage;

import javax.swing.JOptionPane;

public class JeuHashi  {

	/** Le plateau de jeu qui contient � la fois des objets arrete et Noeud **/
	private Object[][] board;
	
	/**N�ud utilis� � des fins de test**/
	private Noeud NoeudTest;
	
	/**
	 *  arrete utilis� � des fins de test**/
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
	 
* ajoute un n�ud � ce plateau de jeu Hashi
* @return int renvoie 1 si le n�ud a �t� ajout�, retourne -1 si le n�ud a �t� annul�
* @param x la coordonn�e X sur le tableau
* @param y la coordonn�e Y sur le tableau
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
			JOptionPane.showMessageDialog(null, "Votre entr�e n'est pas valide", "Entr�e non valide", JOptionPane.WARNING_MESSAGE);
			return -1;
		}
		board[x][y] = new Noeud(deg);
		return 1;
	}
	
	/****************************************************************************************************************
	 * Trouve les connexions possibles qui peuvent se produire entre les n�uds et d�finit les valeurs de chaque n�ud*
	 * Valeurs NoeudNord, NoeudEast, NoeudSud et NoeudOuest                                                         *
	 ****************************************************************************************************************/
	public void findPossibleConnections(){
		for(int x = 0; x < 7; x++){
			for(int y = 0; y < 7; y++){
				//v�rifie si l'objet est un n�ud
				if(board[x][y].getClass().equals(NoeudTest.getClass())){
					//v�rifie au-dessus de ce n�ud pour d'autres n�uds
					for(int y2 = y - 1; y2 >= 0; y2--){
						if(board[x][y2].getClass().equals(NoeudTest.getClass())){
							((Noeud) board[x][y]).setNoeudNord((Noeud)board[x][y2]);
							y2 = -1;
						}
					}
					//v�rifie � l'ouest de ce n�ud les autres n�uds
					for(int x2 = x - 1; x2 >= 0; x2--){
						if(board[x2][y].getClass().equals(NoeudTest.getClass())){
							((Noeud) board[x][y]).setNoeudOuest((Noeud)board[x2][y]);
							x2 = -1;
						}
					}
					//v�rifie � l'est de ce n�ud les autres n�uds
					for(int x2 = x + 1; x2 < 7; x2++){
						if(board[x2][y].getClass().equals(NoeudTest.getClass())){
							((Noeud) board[x][y]).setNoeudEast((Noeud)board[x2][y]);
							x2 = 10;
						}
					}
					//v�rifie sous ce n�ud pour les autres n�uds
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
	 * @param n1 N�ud 1
	 * @param n2 N�ud 2
	 **********************************************************************************************************/
	public void ajoutarrete(Noeud n1, Noeud n2){
		//d�cr�mente le nombre de degr�s n�cessaires aux n�uds
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
		//met � jour la valeur de l'ar�te (0, 1 ou 2)
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
	* @param n1 N�ud 1
	* @param n2 N�ud 2
	 ***********************************************************************************************************/
	public void supprimeArrete(Noeud n1, Noeud n2){
		//incr�mente le nombre de degr�s n�cessaires aux n�uds
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
		//met � jour la valeur de l'ar�te (0, 1 ou 2)
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
	 
* Renvoie vrai si le jeu est gagn�
* @return bool�en  du jeu Hashi (Vrai = Gagn�; Faux = Non termin�)
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
	 * Utilis� apr�s que checkIfWon () retourne false pour v�rifier s'il y en a encore
	 *  mouvements possibles � gauche. Sinon, l'�tat actuel du jeu n'est pas
	 * Solution.
	 * @return boolean Vrai s'il reste des mouvements possibles dans le jeu
	 ***************************************************************************************/
	public boolean checkIfPossibleMoves(){
		for(int x = 0; x < 7; x++){
			for(int y = 0; y < 7; y++){
				if(board[x][y].getClass().equals(NoeudTest.getClass())){
					if(((Noeud)board[x][y]).getDegree() > 0){
						//s'il y a un d�placement valide avec ce n�ud, renvoie true
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
	* Renvoie vrai si vous pouvez ajouter une arrete entre ces n�uds
	* @return boolean Vrai si vous pouvez ajouter une ar�te entre ces n�uds
	 *************************************************************************************/
	public boolean isValidAdd(Noeud n1, Noeud n2){
		//si l'un des n�uds a d�j� atteint son degr� MAX, ou est nul, renvoie false
		if(n1 == null || n2 == null){
			return false;
		}
		if(n1.getDegree() == 0 || n2.getDegree() == 0){
			return false;
		}
		//renvoie false si les n�uds ne sont pas li�s
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
		//renvoie false si l'ar�te � ajouter est d�j� � une valeur de 2
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
				//renvoie false s'il y a d�j� un croisement d'ar�te entre ces deux n�uds
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
				//renvoie false s'il y a d�j� un croisement d'ar�te entre ces deux n�uds
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
				//renvoie false s'il y a d�j� un croisement d'ar�te entre ces deux n�uds
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
