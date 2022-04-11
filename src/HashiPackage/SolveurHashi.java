package HashiPackage;

import javax.swing.JOptionPane;

/*************************************************************************************************
* Le solveur pour le HashiGame. Un utilisateur cr�e d'abord le jeu dans une classe diff�rente, puis
* il est r�solu dans celui-ci

 *************************************************************************************************/
public class SolveurHashi {

	/** Le jeu � r�soudre**/
	private JeuHashi game;
	
	/**Tableau de tous les n�uds du jeu**/
	private Noeud[] nodes;
	
	/**le nombre de n�uds dans le tableau de n�uds**/
	private int numOfNodes;
	
	/**N�ud utilis� � des fins de test**/
	private Noeud testNode;
	
	/**vrai si le jeu est r�solu**/
	private boolean isSolved;
	
	/**************************************************************
	 * Constructeur pour le HashiSolver
	 * @param g le jeu � r�soudre
	 *************************************************************/
	public SolveurHashi(JeuHashi g){
		game = g;
		testNode = new Noeud(0);
		createArray();
		numOfNodes = nodes.length;
		isSolved = false;
		
		//r�sout
		int n = 1;	//cela repr�sentera le n�ud qui se connecte aux n�uds [0]
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
	 * Remplit le tableau "n�uds" avec tous les n�uds du jeu
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
	 * M�thode r�cursive qui utilise le retour en arri�re pour r�soudre le HashiGame. 
     * retourne vrai lorsque le jeu est r�solu.
     * @return boolean Vrai si r�solu
	 ***********************************************************************/
	public boolean resoudre(Noeud n1, Noeud n2){
		//avant tout, assurez-vous que les n�uds ne sont pas les m�mes
		if(n1 == n2){
			return false;
		}
		//premi�re �tape: mettre � jour le jeu en ajoutant une arrete
		if(game.isValidAdd(n1, n2)){
			game.ajoutarrete(n1, n2);
		}
		else{
			//si ce bord n'est pas un ajout valide, renvoyez simplement false
			return false;
		}
		if(game.checkIfWon()){
			//si le jeu est gagn�, retournez vrai!
			return true;
		}
		else if(!game.checkIfPossibleMoves()){
			//s'il n'y a pas de coups possibles, mais que la partie n'est pas gagn�e, renvoie false
			game.supprimeArrete(n1, n2);
			return false;
		}
		//�TAPE R�CURSIVE
		else{
			//d�finir les valeurs de pnode pour correspondre � n1 et n2
			int pnode1 = 0;
			int pnode2 = 0;
			for(int i = 0; i < numOfNodes -1; i++){
				if(n1 == nodes[i])
					pnode1 = i;
				if(n2 == nodes[i])
					pnode2 = i;
			}
			//le processus r�cursif r�el
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
				//puis appelez r�cursivement ces ensembles de noeuds
				if(nodes[pnode1].getNoeudNord() == nodes[pnode2] || nodes[pnode1].getNoeudEast() == nodes[pnode2] || 
						nodes[pnode1].getNoeudSud() == nodes[pnode2] || nodes[pnode1].getNoeudOuest() == nodes[pnode2]){
					if(resoudre(nodes[pnode1], nodes[pnode2])){
						return true;
					}
				}
			}while(!(pnode2 >= (numOfNodes - 2) && pnode1 >= (numOfNodes -1)));

			//si tous les r�solveurs avec cette ar�te ne retournent jamais vrai, v�rifiez si c'est le cas avec une double ar�te, le cas �ch�ant
			if(!resoudre(n1, n2)){
				game.supprimeArrete(n1, n2);
				return false;
			}
		}
		return true;
	}
}
