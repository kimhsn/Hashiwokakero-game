package HashiPackage;

/*******************************************************************************
 * Une classe tr�s simple qui repr�sente les arrete dans le jeu Hashi

 *******************************************************************************/
public class Arrete{

	/**repr�sente les diff�rents types d'ar�tes. (0 = pas d'arrete, 1 = une arret, 2 = deux arrete)**/
	private int typeArrete;
	
	/**repr�sente la direction de l'arrete (0 = pas de direction, 1 = horizontal, 2 = vertical)**/
	private int directionArrete;
	
	public Arrete(){
		typeArrete = 0;
		directionArrete = 0;
	}

	public int gettypeArrete() {
		return typeArrete;
	}

	public void settypeArrete(int typeArrete) {
		this.typeArrete = typeArrete;
	}

	public int getdirectionArrete() {
		return directionArrete;
	}

	public void setdirectionArrete(int directionArrete) {
		this.directionArrete = directionArrete;
	}
}
