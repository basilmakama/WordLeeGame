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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
        wordlee.setUpPlayers2();
        rPanel.setRack();
        butPanel.playerturn();

        
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
							board2[i][j].setFont(new Font("Arial Black", Font.BOLD, 10));
							break;
						case 2:
							board2[i][j].setText("Letter Double");
							board2[i][j].setFont(new Font("Arial Black", Font.BOLD, 10));
							break;
						case 3:
							board2[i][j].setText("Letter Triple");
							board2[i][j].setFont(new Font("Arial Black", Font.BOLD, 10));
							break;
						case 4:
							board2[i][j].setText("Extra Turn");
							board2[i][j].setFont(new Font("Arial Black", Font.BOLD, 10));
							break;
						case 5:
							board2[i][j].setText("Word Double");
							board2[i][j].setFont(new Font("Arial Black", Font.BOLD, 10));
							break;
                                                case 6:
							board2[i][j].setText("Word Triple");
							board2[i][j].setFont(new Font("Arial Black", Font.BOLD, 10));
							break;
                                                case 7:
							board2[i][j].setText("Letter Point Reducer");
							board2[i][j].setFont(new Font("Arial Black", Font.BOLD, 10));
							break;
						case 0:
							board2[i][j].setText("");
							break;
					}
						board2[i][j].setText("<html><div style='text-align: center;'>" + board2[i][j].getText() + "</div></html>");

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
							// Functionality for blank pieces
                                                        if(rPanel.getCurrentPiece().getText().charAt(0) == '*'){
                                                            String blanktile = JOptionPane.showInputDialog("Enter Letter for the Blank Tile");
                                                                if (wordlee.isValid2(pos1, pos2)){
                                                                    rPanel.getCurrentPiece().setText(blanktile.toUpperCase());
                                                                    board2[pos1][pos2].setText(rPanel.getCurrentPiece().getText());
                                                                    board2[pos1][pos2].setFont(new Font("Arial Black", Font.BOLD, 30));
                                                                    board2[pos1][pos2].setBackground(Color.BLACK);
                                                                    board2[pos1][pos2].setForeground(Color.red);
                                                                    wordlee.removePlayerLetter(rPanel.getCurrentPiece().getText().charAt(0));
                                                                    rPanel.getCurrentPiece().removeAll();
                                                                    rPanel.resetCurrentPiece();
                                                                    wordlee.placeLetter2(board2[pos1][pos2].getText().charAt(0), pos1, pos2);
                                                                }
                                                        }
                                                   
                                                        else{   
								
									wordlee.isValid2(pos1, pos2);
										board2[pos1][pos2].setText(rPanel.getCurrentPiece().getText());
										board2[pos1][pos2].setFont(new Font("Times", Font.BOLD, 30));
										rPanel.getCurrentPiece().setVisible(false);
										wordlee.removePlayerLetter(rPanel.getCurrentPiece().getText().charAt(0));
										rPanel.getCurrentPiece().removeAll();
										rPanel.resetCurrentPiece();
										wordlee.placeLetter2(board2[pos1][pos2].getText().charAt(0), pos1, pos2);
									
								}
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
					board2[i][j].setFont(new Font("Arial Black", Font.BOLD, 10));
					this.add(board2[i][j]);
    }
                        }
                }
    }
 
    private class RackPanel extends JPanel{
   
        private JButton currentPiece;
        private ArrayList<Character> rack;
        
        public void setRack(){
                        removeAll();
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
          
                  private JLabel currentplayer;

          
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
                    resetTiles.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					wordlee.resetState2();
					wordlee.resetPresentPlayer();
					bPanel.reset();
					rPanel.setRack();
				}
			});
                    
                    
                    JButton skipTurn = new JButton("Skip Turn");
                    skipTurn.setFocusPainted(false);
                    skipTurn.setOpaque(true);
                    skipTurn.setBackground(Color.red);
                    skipTurn.setContentAreaFilled(false);
                    skipTurn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					wordlee.resetState2();
					wordlee.resetPresentPlayer();
					wordlee.nextPlayer();
					bPanel.reset();
					rPanel.setRack();
					butPanel.playerturn();
					dPanel.updatePlayerInfo();
				}
			});
                    
                    
                    JButton play = new JButton("Play");
                    play.setFocusPainted(false);
                    play.setOpaque(true);
                    play.setBackground(Color.red);
                    play.setContentAreaFilled(false);
                    play.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e){
                           if(wordlee.endTurn2()) {
						wordlee.refillPlayerRack2();
						wordlee.nextPlayer();
						bPanel.reset();
						rPanel.setRack();
                                                butPanel.playerturn();
						dPanel.updatePlayerInfo();
                                               
					}
					else {
                                                JOptionPane.showMessageDialog(null, "This is an invalid word! Please try again");
						wordlee.resetState2();
						wordlee.resetPresentPlayer();
						bPanel.reset();
						rPanel.setRack();
					}
                        }
                    });
                    
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
                    super.add(buttons, BorderLayout.WEST);
                    
                      }
                     public void playerturn(){
                if (currentplayer == null){
                    currentplayer = new JLabel(wordlee.getPresentName()+" Turn", SwingConstants.CENTER);
                    currentplayer.setFont(new Font("Serif", Font.BOLD, 30));
                    super.add(currentplayer, BorderLayout.EAST);
                }
                else currentplayer.setText(wordlee.getPresentName() + "'s Turn" );
			repaint();            
            }
                }

    
    

    
    
    private class DetailPanel extends JPanel{
            
            private JLabel tileInfo2;
            private JLabel playersInfo2;
            private JLabel tileNumber;

            
            public DetailPanel(){
                    super();
                    tileInfo2 = new JLabel("<html>Tile Points<br/><pre>A-1  J-8  S-2"
					+ "<br/>B-5  K-6  T-1<br/>C-4  L-4  U-2<br/>D-4  M-4  V-6"
					+ "<br/>E-1 N-1  W-5<br/>F-5  O-1  X-8<br/>G-5  P-5  Y-5"
					+ "<br/>H-2  Q-10  Z-10<br/>I-1  R-1<br/>Blank-0</pre></html>");
                    tileInfo2.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 14));
                    
                    
                    playersInfo2 = new JLabel ();
                    playersInfo2.setFont(new Font("Lucida Sans Unicode", Font.ITALIC, 30));

                    tileNumber = new JLabel ();
                    tileNumber.setFont(new Font("Lucida Sans Unicode", Font.ITALIC, 30));
            
                    super.setLayout(new BorderLayout());
                    super.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 0, 2, Color.BLACK), new EmptyBorder(10, 10, 10, 10)));
                    super.add(tileInfo2, BorderLayout.SOUTH);
                    super.add(playersInfo2, BorderLayout.NORTH);
                    updatePlayerInfo();
            }
            
        public void updatePlayerInfo() {
			removeAll();
			super.add(tileInfo2, BorderLayout.SOUTH);
			String playerdata = "<html><b>Scores</b><br/><pre>";
			for(int i = 0; i < wordlee.getPlayerCount(); i++) {
			playerdata += wordlee.getPlayerName(i) + ": " + wordlee.getPlayerScore(i)+"<br/><br/>";
			}
                        
                        String tiledata = "<html><b>Tile Count</b><br/><pre>";
                        tiledata += wordlee.getTileCount2() + " Tiles Left";
                        
                        playerdata += "</pre></html>";
			playersInfo2 = new JLabel(playerdata, SwingConstants.LEFT);
			playersInfo2.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 14));
			super.add(playersInfo2, BorderLayout.NORTH);
                        
                        
                        tiledata += "</pre></html>";
			tileNumber = new JLabel(tiledata, SwingConstants.LEFT);
			tileNumber.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 14));
			super.add(tileNumber, BorderLayout.CENTER);

    }
           
    }
    }


