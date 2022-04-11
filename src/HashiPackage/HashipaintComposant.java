package HashiPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

/***********************************************************************************************************
 * Utilisé pour rendre l'InterfaceHashi visuellement attrayant en le faisant ressembler à un vrai jeuhashi*
 ***********************************************************************************************************/
public class HashipaintComposant extends JButton {

	/**Détermine le type de composant**/
	private TypeHashi type;
	
	/**texte affiché sur les composants noeud**/
	private String deg;
	
	/****************************************************************
	 * Constructeur
	 * @param t type de composant
	 * @param d degré du nœud, le cas échéant
	 ****************************************************************/
	public HashipaintComposant(TypeHashi t, String d){
		super();
		setVisible(true);
		type = t;
		deg = d;
		setPreferredSize(new Dimension(50,50));
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, 400, 400);
		if(type == TypeHashi.noeud){
			g.setColor(Color.white);
			g.drawOval(0, 0, 49, 49);
			g.drawString(deg, 22, 29);
		}
		else if(type == TypeHashi.arreteHorizontal1){
			g.setColor(Color.YELLOW);
			g.fillRect(0, 20, 50, 10);
		}
		else if(type == TypeHashi.arreteVertical1){
			g.setColor(Color.red);
			g.fillRect(20, 0, 10, 50);
		}
		else if(type == TypeHashi.arreteHorizontal2){
			g.setColor(Color.yellow);
			g.fillRect(0, 10, 50, 10);
			g.fillRect(0, 30, 50, 10);
		}
		else if(type == TypeHashi.arreteVertical2){
			g.setColor(Color.red);
			g.fillRect(10, 0, 10, 50);
			g.fillRect(30, 0, 10, 50);
		}
		setBorderPainted(false);
	}
	

}
/******************************************************************************
 * 
 * Utilisé pour déterminer le type d'objet de chaque composant
 *
 ******************************************************************************/
enum TypeHashi {
	noeud, arreteVertical1, arreteHorizontal1, arreteVertical2, arreteHorizontal2, blanc
}

