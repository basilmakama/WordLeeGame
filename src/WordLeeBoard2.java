/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Basil
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;


/**
 *
 * @author Basil
 */
public class WordLeeBoard2 extends JPanel {
    
  
    private final WordLee wordlee = WordLee.getInstance();
    
    private final RackPanel rPanel;
    private final BoardPanel bPanel;
    private final DetailPanel dPanel;
    private final ButtonPanel butPanel;

    
    public WordLeeBoard2(){
        
        
        
        setPreferredSize(new Dimension(1200,600));

        

        rPanel = new RackPanel();
        bPanel = new BoardPanel();
        dPanel = new DetailPanel();
        butPanel = new ButtonPanel();
        bPanel.setSize(1200, 600);
        
        
        
        super.setLayout(new BorderLayout());
        super.add(rPanel, BorderLayout.SOUTH);
        super.add(bPanel, BorderLayout.CENTER);
        super.add(dPanel, BorderLayout.EAST);
        super.add(butPanel, BorderLayout.NORTH);
        
        //initialize rack
        wordlee.setUpPlayers();
        rPanel.setRack();

        
    }

    @Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
	}
    
    
    private class BoardPanel extends JPanel{
     
        private JButton[][] board2;

		public BoardPanel() {
			super();
			board2 = new JButton[11][11];
			buildPanel();
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			this.setBackground(Color.DARK_GRAY);
		}
		
		public void reset() {
			for(int i = 0; i < 11; i++) {
				for(int j = 0; j < 11; j++) {
					switch (wordlee.getPosition2(i, j)) {
						case 1:
							board2[i][j].setText("*");
							board2[i][j].setFont(new Font("Papyrus", Font.ITALIC, 8));
							break;
						case 2:
							board2[i][j].setText("Letter Double");
							board2[i][j].setFont(new Font("Papyrus", Font.ITALIC, 8));
							break;
						case 3:
							board2[i][j].setText("Letter Triple");
							board2[i][j].setFont(new Font("Papyrus", Font.ITALIC, 8));
							break;
						case 4:
							board2[i][j].setText("Extra Turn");
							board2[i][j].setFont(new Font("Papyrus", Font.ITALIC, 8));
							break;
						case 5:
							board2[i][j].setText("Word Double");
							board2[i][j].setFont(new Font("Papyrus", Font.ITALIC, 8));
							break;
                                                case 6:
							board2[i][j].setText("Word Triple");
							board2[i][j].setFont(new Font("Papyrus", Font.ITALIC, 8));
							break;
                                                case 7:
							board2[i][j].setText("Letter Point Reducer");
							board2[i][j].setFont(new Font("Papyrus", Font.ITALIC, 8));
							break;
						default:
							board2[i][j].setText(Character.toString(wordlee.getPosition2(i, j)));
							break;
					}
					board2[i][j].setText("<html><div style='text-align: center;'>" + board2[i][j].getText() + "</div></html>");
                                        board2[i][j].setFont(new Font("Papyrus", Font.ITALIC, 8));

				}
			}
		}
                
                private void buildPanel() {
			// Formatting Panel and Generating Grid
			super.setLayout(new GridLayout(11, 11));
			for(int i = 0; i < 11; i++) {
				for(int j = 0; j < 11; j++) {
					final int pos1 = i, pos2 = j;
					board2[i][j] = new JButton();
                                        board2[i][j].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
                                                   
                                                    if(rPanel.getCurrentPiece() != null) {
								
									wordlee.isValid(pos1, pos2);
										board2[pos1][pos2].setText(rPanel.getCurrentPiece().getText());
										board2[pos1][pos2].setFont(new Font("Times", Font.BOLD, 30));
										rPanel.getCurrentPiece().setVisible(false);
										wordlee.removePlayerLetter(rPanel.getCurrentPiece().getText().charAt(0));
										rPanel.getCurrentPiece().removeAll();
										rPanel.resetCurrentPiece();
										wordlee.placeLetter(board2[pos1][pos2].getText().charAt(0), pos1, pos2);
									
								}
                                                    
                                                }
                                        });
   
        
                                        switch (wordlee.getPosition2(i, j)) {
						case 1:
							board2[i][j].setText("*");
							board2[i][j].setBackground(Color.RED);
							break;
						case 2:
							board2[i][j].setText("Letter Double");
							board2[i][j].setBackground(Color.BLUE);
							break;
						case 3:
							board2[i][j].setText("Letter Triple");
							board2[i][j].setBackground(Color.ORANGE);
							break;
						case 4:
							board2[i][j].setText("Extra Turn");
							board2[i][j].setBackground(Color.CYAN);
							break;
						case 5:
							board2[i][j].setText("Word Double");
							board2[i][j].setBackground(Color.GREEN);
							break;
                                                case 6:
							board2[i][j].setText("Word Triple");
							board2[i][j].setBackground(Color.YELLOW);
							break;
                                                case 7:
							board2[i][j].setText("Letter Point Reducer");
							board2[i][j].setBackground(Color.GRAY);
							break;
						default:
							board2[i][j].setText(" ");
							board2[i][j].setBackground(Color.WHITE);
							break;
					}

					board2[i][j].setOpaque(true);
					board2[i][j].setBorder(new MatteBorder(1, 1, 0, 0, Color.BLACK));
					board2[i][j].setHorizontalAlignment(SwingConstants.CENTER);
					board2[i][j].setVerticalAlignment(SwingConstants.CENTER);
					board2[i][j].setText("<html><div style='text-align: center;'>" + board2[i][j].getText() + "</div></html>");
					board2[i][j].setFont(new Font("Serif", Font.BOLD, 10));
					this.add(board2[i][j]);
    }
                        }
                }
    }
 
    private class RackPanel extends JPanel{
   
        private JButton currentPiece;
        private ArrayList<Character> rack;
        
        public void setRack(){
			rack = wordlee.getCurrentPlayerHand();
			super.setLayout(new FlowLayout(FlowLayout.CENTER));
			for(Character i : rack) {
				JButton button = new JButton(i.toString());
                                button.setOpaque(true);
                                button.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(currentPiece != null)
                                                   // currentPiece.setBackground(new Color(255, 228, 174));
						
						button.setBackground(Color.RED);
						currentPiece = button;
					}
				});
                              super.add(button);

                }
                                  super.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(2, 0, 0, 0, Color.BLACK), new EmptyBorder(10, 10, 10, 10)));

    }
        @Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			this.setBackground(Color.WHITE);
		}
                
        public JButton getCurrentPiece() {
			return currentPiece;
		}
		
		public void resetCurrentPiece() {
			currentPiece = null;
		}
    }
    
     private class ButtonPanel extends JPanel{
          
          
                    public ButtonPanel(){
                    super();
                    
                   JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
                   buttons.setOpaque(false);
                   
                   
                   //INITIALIZING BUTTONS ON PANEL
                   
                    JButton resetTiles = new JButton("Reset Tiles");
                    resetTiles.setFocusPainted(false);
                    resetTiles.setOpaque(true);
                    resetTiles.setBackground(Color.red);
                    resetTiles.setContentAreaFilled(false);
                    
                    
                    JButton skipTurn = new JButton("Skip Turn");
                    skipTurn.setFocusPainted(false);
                    skipTurn.setOpaque(true);
                    skipTurn.setBackground(Color.red);
                    skipTurn.setContentAreaFilled(false);
                    
                    
                    JButton play = new JButton("Play");
                    play.setFocusPainted(false);
                    play.setOpaque(true);
                    play.setBackground(Color.red);
                    play.setContentAreaFilled(false);
                    
                    JButton endGame = new JButton("End Game");
                    endGame.setFocusPainted(false);
                    endGame.setOpaque(true);
                    endGame.setBackground(Color.red);
                    endGame.setContentAreaFilled(false);
                    
                    
                    

                    
                    
                    buttons.add(play);
                    buttons.add(Box.createHorizontalStrut(20));
                    buttons.add(resetTiles);
                    buttons.add(Box.createHorizontalStrut(20));
                    buttons.add(skipTurn);
                    buttons.add(Box.createHorizontalStrut(20));
                    buttons.add(endGame);
                    
                    super.setLayout(new BorderLayout());
                    super.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK), new EmptyBorder(10, 10, 10, 10)));
                    super.add(buttons, BorderLayout.EAST);

                    
                      }
                }

    
    

    
    
    private class DetailPanel extends JPanel{
            
            private JLabel tileInfo;
            private JLabel playersInfo;
            
            public DetailPanel(){
                    super();
                    tileInfo = new JLabel("<html>Tile Points<br/><pre>A-1  J-8  S-2"
					+ "<br/>B-5  K-6  T-1<br/>C-4  L-4  U-2<br/>D-4  M-4  V-6"
					+ "<br/>E-1 N-1  W-5<br/>F-5  O-1  X-8<br/>G-5  P-5  Y-5"
					+ "<br/>H-2  Q-10  Z-10<br/>I-1  R-1<br/>Blank-0</pre></html>");
                    tileInfo.setFont(new Font("Serif", Font.ITALIC, 10));
                    
                    
                    playersInfo = new JLabel ();
                    playersInfo.setFont(new Font("Serif", Font.BOLD, 20));

                    
                    
                    
                    super.setLayout(new BorderLayout());
                    super.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 0, 2, Color.BLACK), new EmptyBorder(10, 10, 10, 10)));
                    super.add(tileInfo, BorderLayout.SOUTH);
                    super.add(playersInfo, BorderLayout.NORTH);
                    
                    updatePlayersInfo();
                    
            
            }
        public void updatePlayersInfo() {
			removeAll();
			super.add(tileInfo, BorderLayout.SOUTH);
			String data = "<html><b>Scores</b><br/><pre>";
			for(int i = 0; i < wordlee.getPlayerCount(); i++) {
				data += wordlee.getPlayerName(i) + ": " + wordlee.getPlayerScore(i)+"<br/>";
			}
			data += "</pre></html>";
			playersInfo = new JLabel(data, SwingConstants.CENTER);
			playersInfo.setFont(new Font("Serif", Font.PLAIN, 14));
			super.add(playersInfo, BorderLayout.NORTH);
			
		}
           
    }
    }


