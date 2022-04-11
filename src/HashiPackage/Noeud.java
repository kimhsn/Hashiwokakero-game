package HashiPackage;

/***********************************************************************
 * Repr�sente les sommets (ou n�uds) dans le jeu Hash

 **********************************************************************/
public class Noeud {

	/** repr�sente le degr� du n�ud, comme le nombre d'ar�tes � y connecter**/
	private int MAXdegree;
	
	/** repr�sente combien d'ar�tes suppl�mentaires doivent �tre connect�es � ce n�ud pour correspondre au MAXdegree**/
	private int degree;
	
	/** les n�uds auxquels ce n�ud peut �tre connect�**/
	private Noeud NoeudNord, NoeudEast, NoeudSud, NoeudOuest;
	
	/** l'�tat d'une ar�te entre deux n�uds. 0 = pas d'arret, 1 = une arrete, 2 = deux arrete**/
	private int ArreteNord, ArreteEast, ArreteSud, ArreteOuest;
	
	/********************************************************************
	* Constructeur pour la classe Noeud. D�finit le degr� du n�ud
	* @param d Degr� du n�ud
	 ********************************************************************/
	public Noeud(int d){
		MAXdegree = d;
		degree = d;
		
		//le n�ud d�marre et n'est connect� � aucun autre n�ud
		ArreteNord = 0;
		ArreteEast = 0;
		ArreteSud = 0;
		ArreteOuest = 0;
	}
	
	/**********************************************************************
	 * D�cr�mente le degr� de 1
	 **********************************************************************/
	public void dec(){
		degree--;
	}
	
	/**********************************************************************
	* Incr�mente le degr� de 1
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