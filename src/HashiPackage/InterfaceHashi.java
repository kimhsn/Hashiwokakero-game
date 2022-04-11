package HashiPackage;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class InterfaceHashi extends JPanel {

	

    /**represente la fenetre principale de l'application**/
	private static JFrame mainframe ;
	/**le Hashimodel joué**/
	private JeuHashi model ;
	
	/**la table qui represente les noeuds**/
	private JTextField[][] board;
	
	/**Utilisé pour l'attrait visuel, une maquette de composants **/
	private HashipaintComposant[][] paintedBoard;
	
	/**Active le solveur pour résoudre le jeu**/
	private JButton solveButton;
	
	/**Définit tous les nœuds du modèle comme définitifs et prêts à jouer**/
	private JButton setButton;
	
	/**Réinitialise le Hashimodel pour en créer un nouveau**/
	private JButton resetButton;
	
	/**Détermine si le modèle est défini ou non**/
	private boolean isSet;
	
	/**le titre**/
	private JLabel titleLabel;
	
	private JLabel c1;
	private JLabel c2;
	/**Le panneau qui contient la carte de JTexteField**/
	private JPanel boardPanel;
	
	/**Le panneau qui contient tous les autres panneaux**/
	private JPanel masterPanel;
	
	/**Nœud utilisé à des fins de test**/
	private Noeud NoeudTest;
	
	/**solveur pour le HashiGame**/
	private SolveurHashi solver;
	
	/***************************************************************************
	 * Constructeur pour la classe InterfaceHshi, qui crée l'interface graphique
	 ***************************************************************************/
	
	
	public InterfaceHashi(){
		createModel();
		createView();
		placeComponent();
		createController();
		
	}

		public void createModel(){
			model= new JeuHashi();
			NoeudTest = new Noeud(0);

		}
		
		private void createView() {
			mainframe= new JFrame("Hashiwokakera");
			board = new JTextField[7][7];
			paintedBoard = new HashipaintComposant[7][7];
			Icon iconButton = new ImageIcon(getClass().getResource("key.png"));
			Icon iconButton1 = new ImageIcon(getClass().getResource("set.png"));
			Icon iconButton2 = new ImageIcon(getClass().getResource("reset.png"));
			solveButton = new JButton("Résoudre",iconButton);
			setButton = new JButton("Fixer",iconButton1);
			resetButton = new JButton("Réinitialiser",iconButton2);
			titleLabel = new JLabel(" Le jeu du Hashiwokakera ");
			titleLabel.setFont(new Font("TimesNewRoman", Font.BOLD, 22));
			c1= new JLabel();
			c2= new JLabel();
			Icon icon1 = new ImageIcon(getClass().getResource("jeu.png"));
			Icon icon2 = new ImageIcon(getClass().getResource("jeu.png"));
			c1.setIcon(icon1);
			c2.setIcon(icon2);
			isSet = false;
		
		}

		private void placeComponent() {
			
			masterPanel = new JPanel(new BorderLayout());

			JPanel p=new JPanel(new FlowLayout(FlowLayout.CENTER));{
				
				p.add(c1);
				p.add(titleLabel);
				p.add(c2);
						
			}
			masterPanel.add(p, BorderLayout.NORTH);
			
			boardPanel =new JPanel();{
				 createBoard();
			}
			

			masterPanel.add(boardPanel, BorderLayout.CENTER);
			

			p=new JPanel(new FlowLayout(FlowLayout.CENTER));{
				p.add(setButton);
			    p.add(solveButton);
				p.add(resetButton);
				
				
			}
			masterPanel.add(p, BorderLayout.SOUTH);
			mainframe.add(masterPanel);	
			
		}
		
		
			public void display() {
				mainframe.getContentPane();			
				mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainframe.pack();
				mainframe.setVisible(true);
				mainframe.setLocationRelativeTo(null);
			
			}
			
			
			private void createController(){
			     ActionListener a =new ActionListener() {
						
						public void actionPerformed(ActionEvent e) {
							//pour ajouter des nœuds au plateau de JTexteField
							for(int row = 0; row < 7; row++){
								for(int col = 0; col < 7; col++){
									String aa=board[row][col].getText();
											if(board[row][col] == e.getSource()){
										int added = model.addNode(row, col,aa);
										if(added == 1)
											board[row][col].setText(""+ ((Noeud)model.getBoard()[row][col]).getMAXDegree());
									}
								}
							}
							
							if(e.getSource() == solveButton){
								if(!isSet){
									JOptionPane.showMessageDialog(null, "Vous devez fixé les iles avant de pouvoir continuer", 
											"iles non fixer", JOptionPane.WARNING_MESSAGE);
								}
								else{
									solver = new SolveurHashi(model);
									clearPaintedBoard();
									setBoard();
								}
							}
							
						}
					};
					solveButton.addActionListener(a);
					
					ActionListener b=new ActionListener() {
						
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							for(int row = 0; row < 7; row++){
								for(int col = 0; col < 7; col++){
									String aa=board[row][col].getText();
											if(board[row][col] == e.getSource()){
										int added = model.addNode(row, col,aa);
										if(added == 1)
											board[row][col].setText(""+ ((Noeud)model.getBoard()[row][col]).getMAXDegree());
									}
								}
							}
						
						if(e.getSource() == setButton){
							if(!isSet){
								isSet = true;
								model.findPossibleConnections();
								clearBoard();
								setBoard();
							}
						}
					}};
					setButton.addActionListener(b);
					
					ActionListener c = new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							for(int row = 0; row < 7; row++){
								for(int col = 0; col < 7; col++){
									String aa=board[row][col].getText();
											if(board[row][col] == e.getSource()){
										int added = model.addNode(row, col,aa);
										if(added == 1)
											board[row][col].setText(""+ ((Noeud)model.getBoard()[row][col]).getMAXDegree());
									}
								}
						}
							
							if(e.getSource() == resetButton){
								model= new JeuHashi ();
								if(isSet)
									clearPaintedBoard();
								else
									clearBoard();
								createBoard();
								isSet = false;
							}
					}};
					resetButton.addActionListener(c);
					
					
				}
			
			
			
			
		
		/***************************************************************************
		 * Crée la maquette de JTextefield
		 ***************************************************************************/
		public void createBoard(){
			for(int row = 0; row < 7; row++){
				for(int col = 0; col < 7; col++){
					board[row][col] = new JTextField();
					board[row][col].setHorizontalAlignment(JTextField.CENTER);
					board[row][col].setFont(new Font("Serif", Font.BOLD, 30));
					board[row][col].setBackground(Color.BLACK);	
					board[row][col].setForeground(Color.yellow);
					board[row][col].setPreferredSize(new Dimension(50,50));
					board[row][col].setCaretColor(Color.YELLOW);
					
					ActionListener a =new ActionListener() {
						
						public void actionPerformed(ActionEvent e) {
							//pour ajouter des nœuds au plateau
							for(int row = 0; row < 7; row++){
								for(int col = 0; col < 7; col++){
									String aa=board[row][col].getText();
											if(board[row][col] == e.getSource()){
										int added = model.addNode(row, col,aa);
										if(added == 1)
											board[row][col].setText(""+ ((Noeud)model.getBoard()[row][col]).getMAXDegree());
									}
								}
							}
							
							if(e.getSource() == solveButton){
								if(!isSet){
									JOptionPane.showMessageDialog(null, "Vous devez définir le modèle avant de pouvoir continuer", 
											"ile non fixer", JOptionPane.WARNING_MESSAGE);
								}
								else{
									solver = new SolveurHashi(model);
									clearPaintedBoard();
									setBoard();
								}
							}
							
						}
					};
					ActionListener b=new ActionListener() {
						
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							for(int row = 0; row < 7; row++){
								for(int col = 0; col < 7; col++){
									String aa=board[row][col].getText();
											if(board[row][col] == e.getSource()){
										int added = model.addNode(row, col,aa);
										if(added == 1)
											board[row][col].setText(""+ ((Noeud)model.getBoard()[row][col]).getMAXDegree());
									}
								}
							}
						
						if(e.getSource() == setButton){
							if(!isSet){
								isSet = true;
								model.findPossibleConnections();
								clearBoard();
								setBoard();
							}
						}
					}};
					
	                ActionListener c = new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							for(int row = 0; row < 7; row++){
								for(int col = 0; col < 7; col++){
									String aa=board[row][col].getText();
											if(board[row][col] == e.getSource()){
										int added = model.addNode(row, col,aa);
										if(added == 1)
											board[row][col].setText(""+ ((Noeud)model.getBoard()[row][col]).getMAXDegree());
									}
								}
						}
							
							if(e.getSource() == resetButton){
								model= new JeuHashi();
								if(isSet)
									clearPaintedBoard();
								else
									clearBoard();
								createBoard();
								isSet = false;
							}
					}};
					
					board[row][col].addActionListener(a);
					board[row][col].addActionListener(b);
					board[row][col].addActionListener(c);
					boardPanel.add(board[row][col]);
				}
			}
			boardPanel.setLayout(new GridLayout(7,7));
			repaint();
			reset();
		}
		
		/***************************************************************************
		 * Efface la grille actuelle de l'interface graphique
		 ***************************************************************************/
		public void clearBoard(){
			for(int row = 0; row < 7; row++){
				for(int col = 0; col < 7; col++){
					boardPanel.remove(board[row][col]);
				}
			}
		}
		
		/***************************************************************************
		 * Efface le tableau peint actuel pour l'interface graphique
		 ***************************************************************************/
		public void clearPaintedBoard(){
			for(int row = 0; row < 7; row++){
				for(int col = 0; col < 7; col++){
					boardPanel.remove(paintedBoard[row][col]);
				}
			}
		}
		
		/**************************************************************************************
		 * Appelé après avoir cliqué sur setButton, 
		 **************************************************************************************/
		public void setBoard(){
			TypeHashi type;
			String deg;
			
			for(int row = 0; row < 7; row++){
				for(int col = 0; col < 7; col++){
					//détermine le type de composant à afficher
					//si l'objet est un nœud
					if(model.getBoard()[row][col].getClass().equals(NoeudTest.getClass())){
						type = TypeHashi.noeud;
						deg = ((Noeud)model.getBoard()[row][col]).getMAXDegree() + "";
						paintedBoard[row][col] = new HashipaintComposant(type, deg);
					}
					//si l'objet est une seul arrete
					else if(((Arrete)model.getBoard()[row][col]).getdirectionArrete() == 2){
						if(((Arrete)model.getBoard()[row][col]).gettypeArrete() == 1){
							type = TypeHashi.arreteHorizontal1;
							paintedBoard[row][col] = new HashipaintComposant(type, null);
						}
						else if(((Arrete)model.getBoard()[row][col]).gettypeArrete() == 2){
							type = TypeHashi.arreteHorizontal2;
							paintedBoard[row][col] = new HashipaintComposant(type, null);
						}
					}
					//si l'objet est une double arrete
					else if(((Arrete)model.getBoard()[row][col]).getdirectionArrete() == 1){
						if(((Arrete)model.getBoard()[row][col]).gettypeArrete() == 1){
							type = TypeHashi.arreteVertical1;
							paintedBoard[row][col] = new HashipaintComposant(type, null);
						}
						else if(((Arrete)model.getBoard()[row][col]).gettypeArrete() == 2){
							type = TypeHashi.arreteVertical2;
							paintedBoard[row][col] = new HashipaintComposant(type, null);
						}
					}
					else{
						type = TypeHashi.blanc;
						paintedBoard[row][col] = new HashipaintComposant(type, null);
					}
					//puis ajoutez ce composant à l'interface graphique
					boardPanel.add(paintedBoard[row][col]);
				}
			}
			repaint();
			reset();
		}
		
		

		
		public static void reset(){
			mainframe.pack();
			mainframe.setLocationRelativeTo(null);
		}
		
		
		public static void main(String [] args){

	    SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                new InterfaceHashi().display();
		
	            }
	    	});
		}
	 





}
