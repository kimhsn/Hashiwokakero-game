package HashiPackage;

/*******************************************************************************
 * Une classe très simple qui représente les arrete dans le jeu Hashi

 *******************************************************************************/
public class Arrete{

	/**représente les différents types d'arêtes. (0 = pas d'arrete, 1 = une arret, 2 = deux arrete)**/
	private int typeArrete;
	
	/**représente la direction de l'arrete (0 = pas de direction, 1 = horizontal, 2 = vertical)**/
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
