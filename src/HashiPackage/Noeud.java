package HashiPackage;

/***********************************************************************
 * Représente les sommets (ou nœuds) dans le jeu Hash

 **********************************************************************/
public class Noeud {

	/** représente le degré du nœud, comme le nombre d'arêtes à y connecter**/
	private int MAXdegree;
	
	/** représente combien d'arêtes supplémentaires doivent être connectées à ce nœud pour correspondre au MAXdegree**/
	private int degree;
	
	/** les nœuds auxquels ce nœud peut être connecté**/
	private Noeud NoeudNord, NoeudEast, NoeudSud, NoeudOuest;
	
	/** l'état d'une arête entre deux nœuds. 0 = pas d'arret, 1 = une arrete, 2 = deux arrete**/
	private int ArreteNord, ArreteEast, ArreteSud, ArreteOuest;
	
	/********************************************************************
	* Constructeur pour la classe Noeud. Définit le degré du nœud
	* @param d Degré du nœud
	 ********************************************************************/
	public Noeud(int d){
		MAXdegree = d;
		degree = d;
		
		//le nœud démarre et n'est connecté à aucun autre nœud
		ArreteNord = 0;
		ArreteEast = 0;
		ArreteSud = 0;
		ArreteOuest = 0;
	}
	
	/**********************************************************************
	 * Décrémente le degré de 1
	 **********************************************************************/
	public void dec(){
		degree--;
	}
	
	/**********************************************************************
	* Incrémente le degré de 1
	 **********************************************************************/
	public void inc(){
		degree++;
	}
	
	public int getDegree(){
		return degree;
	}
	
	public int getMAXDegree(){
		return MAXdegree;
	}
	
	public void setNoeudNord(Noeud n){
		NoeudNord = n;
	}
	
	public Noeud getNoeudNord(){
		return NoeudNord;
	}
	
	public void setNoeudEast(Noeud n){
		NoeudEast = n;
	}
	
	public Noeud getNoeudEast(){
		return NoeudEast;
	}
	
	public void setNoeudSud(Noeud n){
		NoeudSud = n;
	}
	
	public Noeud getNoeudSud(){
		return NoeudSud;
	}
	
	public void setNoeudOuest(Noeud n){
		NoeudOuest = n;
	}
	
	public Noeud getNoeudOuest(){
		return NoeudOuest;
	}

	public int getArreteNord() {
		return ArreteNord;
	}

	public void setArreteNord(int ArreteNord) {
		this.ArreteNord = ArreteNord;
	}

	public int getArreteEast() {
		return ArreteEast;
	}

	public void setArreteEast(int ArreteEast) {
		this.ArreteEast = ArreteEast;
	}

	public int getArreteSud() {
		return ArreteSud;
	}

	public void setArreteSud(int ArreteSud) {
		this.ArreteSud = ArreteSud;
	}

	public int getArreteOuest() {
		return ArreteOuest;
	}

	public void setArreteOuest(int ArreteOuest) {
		this.ArreteOuest = ArreteOuest;
	}

}