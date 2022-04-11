package HashiPackage;

import javax.swing.JOptionPane;

/*************************************************************************************************
* Le solveur pour le HashiGame. Un utilisateur crée d'abord le jeu dans une classe différente, puis
* il est résolu dans celui-ci

 *************************************************************************************************/
public class SolveurHashi {

	/** Le jeu à résoudre**/
	private JeuHashi game;
	
	/**Tableau de tous les nœuds du jeu**/
	private Noeud[] nodes;
	
	/**le nombre de nœuds dans le tableau de nœuds**/
	private int numOfNodes;
	
	/**Nœud utilisé à des fins de test**/
	private Noeud testNode;
	
	/**vrai si le jeu est résolu**/
	private boolean isSolved;
	
	/**************************************************************
	 * Constructeur pour le HashiSolver
	 * @param g le jeu à résoudre
	 *************************************************************/
	public SolveurHashi(JeuHashi g){
		game = g;
		testNode = new Noeud(0);
		createArray();
		numOfNodes = nodes.length;
		isSolved = false;
		
		//résout
		int n = 1;	//cela représentera le nœud qui se connecte aux nœuds [0]
		while(n <= (numOfNodes - 1) && !isSolved){
			if(!resoudre(nodes[0], nodes[n])){
				n++;
			}else{
				isSolved = true;
			}
		}
		if(!isSolved){
			JOptionPane.showMessageDialog(null, "Il n'y a pas de solution pour ce jeu Hashi "," Pas de solution", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/******************************************************************
	 * Remplit le tableau "nœuds" avec tous les nœuds du jeu
	 ******************************************************************/
	public void createArray(){
		Object[][] board = game.getBoard();
		int count = 0;
		for(int x = 0; x < 7; x++){
			for(int y = 0; y < 7; y++){
				if(board[x][y].getClass().equals(testNode.getClass())){
					count++;
				}
			}
		}
		nodes = new Noeud[count];
		int num = 0;
		for(int x = 0; x < 7; x++){
			for(int y = 0; y < 7; y++){
				if(board[x][y].getClass().equals(testNode.getClass())){
					nodes[num] = (Noeud)board[x][y];
					num++;
				}
			}
		}
	}
	
	/***********************************************************************
	 * Méthode récursive qui utilise le retour en arrière pour résoudre le HashiGame. 
     * retourne vrai lorsque le jeu est résolu.
     * @return boolean Vrai si résolu
	 ***********************************************************************/
	public boolean resoudre(Noeud n1, Noeud n2){
		//avant tout, assurez-vous que les nœuds ne sont pas les mêmes
		if(n1 == n2){
			return false;
		}
		//première étape: mettre à jour le jeu en ajoutant une arrete
		if(game.isValidAdd(n1, n2)){
			game.ajoutarrete(n1, n2);
		}
		else{
			//si ce bord n'est pas un ajout valide, renvoyez simplement false
			return false;
		}
		if(game.checkIfWon()){
			//si le jeu est gagné, retournez vrai!
			return true;
		}
		else if(!game.checkIfPossibleMoves()){
			//s'il n'y a pas de coups possibles, mais que la partie n'est pas gagnée, renvoie false
			game.supprimeArrete(n1, n2);
			return false;
		}
		//ÉTAPE RÉCURSIVE
		else{
			//définir les valeurs de pnode pour correspondre à n1 et n2
			int pnode1 = 0;
			int pnode2 = 0;
			for(int i = 0; i < numOfNodes -1; i++){
				if(n1 == nodes[i])
					pnode1 = i;
				if(n2 == nodes[i])
					pnode2 = i;
			}
			//le processus récursif réel
			do{
				//si n2 est le dernier noeud de la liste, passer au n1 suivant
				if(nodes[pnode1].getDegree() == 0 || pnode2 == numOfNodes - 1){
					do{
						pnode1++;
						pnode2 = pnode1;
					}while(nodes[pnode1].getDegree() == 0 && pnode1 < numOfNodes-1);
				}
				//autrement..
				else{
					pnode2++;
				}
				//puis appelez récursivement ces ensembles de noeuds
				if(nodes[pnode1].getNoeudNord() == nodes[pnode2] || nodes[pnode1].getNoeudEast() == nodes[pnode2] || 
						nodes[pnode1].getNoeudSud() == nodes[pnode2] || nodes[pnode1].getNoeudOuest() == nodes[pnode2]){
					if(resoudre(nodes[pnode1], nodes[pnode2])){
						return true;
					}
				}
			}while(!(pnode2 >= (numOfNodes - 2) && pnode1 >= (numOfNodes -1)));

			//si tous les résolveurs avec cette arête ne retournent jamais vrai, vérifiez si c'est le cas avec une double arête, le cas échéant
			if(!resoudre(n1, n2)){
				game.supprimeArrete(n1, n2);
				return false;
			}
		}
		return true;
	}
}
