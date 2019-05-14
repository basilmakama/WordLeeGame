/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;




/**
 *
 * @author Basil
 */
public class WordLeeIntro extends JPanel{
    private final WordLee wordlee = WordLee.getInstance();
    
    public WordLeeIntro(){
        super();
        super.setLayout(new CardLayout());
        JPanel cards = this;

        
        // ARRANGEMENT OF STARTING PANEL
        
        JPanel card1 = new JPanel();
        card1.setLayout(new BoxLayout(card1, BoxLayout.Y_AXIS));
        card1.setBackground(new Color(82,53,148));
        
        JLabel title = new JLabel(new ImageIcon("/Users/Basil/Desktop/wordlee.png"));
        
       // JLabel title = new JLabel("WordLee");
        //title.setFont(new Font("Times Italic", Font.ITALIC, 60));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        //title.setForeground(Color.RED);
        
        JPanel buttonsec = new JPanel();
        buttonsec.setLayout(new BoxLayout(buttonsec, BoxLayout.X_AXIS));
        buttonsec.setSize(new Dimension(780,265));
        buttonsec.setBackground(new Color(82,53,148));
        JButton firstboard = new JButton("11 X 11 Board");
        JButton secondboard = new JButton("15 X 15 Board");
        JButton gamerules = new JButton("Game Rules");
        
        //Action Listeners for the buttons on the introduction frame of the game
        
        firstboard.addActionListener(new ActionListener(){
            @Override
            //Swap to info retrieval for current game
            public void actionPerformed(ActionEvent e) {
                    CardLayout layout = (CardLayout) (cards.getLayout());
                    layout.show(cards, "game1card");
            }
        
        });
        
        secondboard.addActionListener(new ActionListener(){
            @Override
            //Swap to info retrieval for current game
            public void actionPerformed(ActionEvent e) {
                    CardLayout layout = (CardLayout) (cards.getLayout());
                    layout.show(cards, "game2card");            
            }
        });
        
        
        gamerules.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout layout = (CardLayout) (cards.getLayout());
                layout.show(cards, "gamerulescard");
            }
        
        });
                card1.add(title);
                card1.add(buttonsec);
		buttonsec.add(menuButton(firstboard));
		buttonsec.add(Box.createHorizontalStrut(30));
		buttonsec.add(menuButton(secondboard));
		buttonsec.add(Box.createHorizontalStrut(30));
		buttonsec.add(menuButton(gamerules));
                buttonsec.add(Box.createHorizontalStrut(30));
                
                // ARRANGEMENT OF GAMES RULES PANEL
                
                JPanel card2 = new JPanel();
                card2.setLayout(new BorderLayout());
                card2.setBackground(new Color(90,53,148));

                
                JLabel rulesPanel = new JLabel("<html>This is a word game to be played between a minimum of two players and a maximum of four players,there are two board sizes which the players van choose from and each board has a different number of tiles"+
                                            "<br/>The rules of the game are as follows:"+
                                            "<br/>"+
                                            "<br/>• The game must start with a tile on the middle square."+
                                            "<br/>• The player must form a valid word on the board by combining two or more letters from the rack."+
                                            "<br/>• Words can only be placed horizontally or vertically on the board and not horizontally."+
                                            "<br/>• Each word played must contain at least one letter already placed on the board."+
                                            "<br/>• After each turn, the tile values are added and placed on the score sheet."+
                                            "<br/>• When all tiles have been used, the game ends and the player with the most points wins.");
                rulesPanel.setFont(new Font("Helvetica", Font.PLAIN, 20));
                rulesPanel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                rulesPanel.setSize(500, 400);
                rulesPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
                
               JButton returnButton = new JButton("Back");
               returnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) (cards.getLayout());
				layout.show(cards, "startCard");
			}
		});
		
               card2.add(rulesPanel, BorderLayout.CENTER);
               card2.add(menuButton(returnButton), BorderLayout.SOUTH);
               
               // Panel for first board 11 x 11 board
               
               JPanel card3 = new JPanel();
               card3.setLayout(new BoxLayout(card3,BoxLayout.Y_AXIS));
               card3.setBackground(new Color(90,53,148));

               
               JLabel newGameInfo = new JLabel ("<html>Please choose the Player Number and Enter Player Names for this Round</html>");
               
               
               newGameInfo.setFont(new Font("Helvetica", Font.PLAIN, 20));
               newGameInfo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
               newGameInfo.setSize(500, 400);
               newGameInfo.setBorder(new EmptyBorder(20, 40, 20, 40));
               
              // Radio Buttons and ButtonGroup to choose numberof players for each round on the board
               
               JRadioButton twoplay = new JRadioButton("Two - 2");
               twoplay.setMnemonic(KeyEvent.VK_B);
               twoplay.setAlignmentX(JLabel.CENTER_ALIGNMENT);
               twoplay.setActionCommand("2");
               twoplay.setSelected(false);
               
               
               
               JRadioButton threeplay = new JRadioButton("Three - 3");
               threeplay.setMnemonic(KeyEvent.VK_B);
               threeplay.setAlignmentX(JLabel.CENTER_ALIGNMENT);
               threeplay.setActionCommand("3");
               threeplay.setSelected(false);
               
               JRadioButton fourplay = new JRadioButton("Four - 4");
               fourplay.setMnemonic(KeyEvent.VK_B);
               fourplay.setAlignmentX(JLabel.CENTER_ALIGNMENT);
               fourplay.setActionCommand("4");
               fourplay.setSelected(false);
               
               ButtonGroup bgroup = new ButtonGroup();
               bgroup.add(twoplay);
               bgroup.add(threeplay);
               bgroup.add(fourplay);
  
               
            JButton startGame = new JButton("Start Game");
            JButton playernumber = new JButton("Select");

           // Action listener on the startgame button after player names have been entered 
            startGame.setVisible(true);
		startGame.addActionListener(new ActionListener() {
			@Override
			// Tells the JFrame that it can begin the game
			public void actionPerformed(ActionEvent e) {
                            if (wordlee.getPlayerCount() == 0) 
                                
                                JOptionPane.showMessageDialog(null, "You need to select and enter players name to start the game");
                                
                            else {
                                        
				JFrame frame = (JFrame) SwingUtilities.getRoot(cards);
				frame.getContentPane().removeAll();
				frame.add(new WordLeeBoard2());
				frame.revalidate();
				frame.repaint();
				frame.setSize(new Dimension(1300, 735));
				frame.setMinimumSize(frame.getSize());
				frame.setResizable(false);
				frame.setVisible(true);
                                        }       
			}
                        
		});
                
            // Action Listener that takes player names and initialized them in the player arraylist
            
         
            playernumber.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (twoplay.isSelected()) {
                   String player1 =  JOptionPane.showInputDialog("Enter Player 1 Name");
                   String player2 =  JOptionPane.showInputDialog("Enter Player 2 Name");
                   
                   wordlee.addPlayer(player1);
                   wordlee.addPlayer(player2);
                   
                   
                }
                
                
                else if (threeplay.isSelected()) {
                   String player1 =  JOptionPane.showInputDialog("Enter Player 1 Name");
                   String player2 =  JOptionPane.showInputDialog("Enter Player 2 Name");
                   String player3 =  JOptionPane.showInputDialog("Enter Player 3 Name");
                   
                   wordlee.addPlayer(player1);
                   wordlee.addPlayer(player2);
                   wordlee.addPlayer(player3);
                   
                   
}
                if (fourplay.isSelected()){
                  String player1 =  JOptionPane.showInputDialog("Enter Player 1 Name");
                   String player2 =  JOptionPane.showInputDialog("Enter Player 2 Name");
                   String player3 =  JOptionPane.showInputDialog("Enter Player 3 Name");
                    String player4 =  JOptionPane.showInputDialog("Enter Player 4 Name");
                    
                   wordlee.addPlayer(player1);
                   wordlee.addPlayer(player2);
                   wordlee.addPlayer(player3);
                   wordlee.addPlayer(player4);
                   
}
            }
            });
            

            JButton returnButton2 = new JButton("Back");
               returnButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) (cards.getLayout());
				layout.show(cards, "startCard");
			}
		});
               
               
                //JPanel lastButtons = new JPanel();
		//lastButtons.add(menuButton(startGame));
            
       
              card3.add(newGameInfo, BorderLayout.NORTH);
              card3.add(Box.createVerticalStrut(30));
              card3.add(twoplay);
              card3.add(Box.createVerticalStrut(10));
              card3.add(threeplay);
              card3.add(Box.createVerticalStrut(10));
              card3.add(fourplay);
              card3.add(Box.createVerticalStrut(20));
              card3.add(menuButton(playernumber));
              card3.add(Box.createVerticalStrut(30));
              card3.add(menuButton(startGame));
              card3.add(Box.createVerticalStrut(90));
              card3.add(backButton(returnButton2), BorderLayout.SOUTH);

              


              // Panel for the second board 15 x 15 board
              
               JPanel card4 = new JPanel();
               card4.setLayout(new BoxLayout(card4,BoxLayout.Y_AXIS));
               card4.setBackground(new Color(90,53,148));               


               
               JLabel newGameInfo2 = new JLabel ("<html>Please enter player names for this round</html>");
               
               newGameInfo2.setFont(new Font("Helvetica", Font.PLAIN, 16));
               newGameInfo2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
               newGameInfo2.setSize(400, 300);
               newGameInfo2.setBorder(new EmptyBorder(20, 40, 20, 40));
               
               JRadioButton twoplay2 = new JRadioButton("Two - 2");
               twoplay2.setMnemonic(KeyEvent.VK_B);
               twoplay2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
               twoplay2.setActionCommand("2");
               twoplay2.setSelected(false);
               
               
               
               JRadioButton threeplay2 = new JRadioButton("Three - 3");
               threeplay2.setMnemonic(KeyEvent.VK_B);
               threeplay2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
               threeplay2.setActionCommand("3");
               threeplay2.setSelected(false);
               
               JRadioButton fourplay2 = new JRadioButton("Four - 4");
               fourplay2.setMnemonic(KeyEvent.VK_B);
               fourplay2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
               fourplay2.setActionCommand("4");
               fourplay2.setSelected(false);
               
               ButtonGroup bgroup1 = new ButtonGroup();
               bgroup1.add(twoplay2);
               bgroup1.add(threeplay2);
               bgroup1.add(fourplay2);
  
               
            JButton playernumber2 = new JButton("Select");   
            JButton startGame2 = new JButton("Start Game");
            
            startGame2.setVisible(true);
		startGame2.addActionListener(new ActionListener() {
			@Override
			// Tells the JFrame that it can begin the game
			public void actionPerformed(ActionEvent e) {
                            if (wordlee.getPlayerCount() == 0) 
                                JOptionPane.showMessageDialog(null, "You need to select and enter players name to start the game");
                            else {
				JFrame frame = (JFrame) SwingUtilities.getRoot(cards);
				frame.getContentPane().removeAll();
				frame.add(new WordLeeBoard());
				frame.revalidate();
				frame.repaint();
				frame.setSize(new Dimension(1300, 735));
				frame.setMinimumSize(frame.getSize());
				frame.setResizable(false);
				frame.setVisible(true);
                                        }       
			}
		});
                
                 
            playernumber2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (twoplay2.isSelected()) {
                   String player1 =  JOptionPane.showInputDialog("Enter Player 1 Name");
                   String player2 =  JOptionPane.showInputDialog("Enter Player 2 Name");
                   
                   wordlee.addPlayer(player1);
                   wordlee.addPlayer(player2);

                }
                
                
                else if (threeplay2.isSelected()) {
                   String player1 =  JOptionPane.showInputDialog("Enter Player 1 Name");
                   String player2 =  JOptionPane.showInputDialog("Enter Player 2 Name");
                   String player3 =  JOptionPane.showInputDialog("Enter Player 3 Name");
                   
                   wordlee.addPlayer(player1);
                   wordlee.addPlayer(player2);
                   wordlee.addPlayer(player3);
                   
                   
}
                if (fourplay2.isSelected()){
                  String player1 =  JOptionPane.showInputDialog("Enter Player 1 Name");
                   String player2 =  JOptionPane.showInputDialog("Enter Player 2 Name");
                   String player3 =  JOptionPane.showInputDialog("Enter Player 3 Name");
                    String player4 =  JOptionPane.showInputDialog("Enter Player 4 Name");
                    
                   wordlee.addPlayer(player1);
                   wordlee.addPlayer(player2);
                   wordlee.addPlayer(player3);
                   wordlee.addPlayer(player4);
                   
}
            }
            });
            
        
         JButton returnButton3 = new JButton("Back");
               returnButton3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) (cards.getLayout());
				layout.show(cards, "startCard");
			}
		});
            
             
            
                
              //JPanel lastButtons2 = new JPanel();
              //lastButtons2.add(menuButton(startGame2));
       
              card4.add(newGameInfo2, BorderLayout.NORTH);
              card4.add(Box.createVerticalStrut(30));
              card4.add(twoplay2);
              card4.add(Box.createVerticalStrut(10));
              card4.add(threeplay2);
              card4.add(Box.createVerticalStrut(10));
              card4.add(fourplay2);
              card4.add(Box.createVerticalStrut(20));
              card4.add(menuButton(playernumber2));
              card4.add(Box.createVerticalStrut(30));
              card4.add(menuButton(startGame2));
              card4.add(Box.createVerticalStrut(90));
              card4.add(backButton(returnButton3), BorderLayout.SOUTH);
              
         
               // Adding the cards to the card layout/window
                super.add(card1, "startCard");
                super.add(card2, "gamerulescard");
                super.add(card3, "game1card");
                super.add(card4, "game2card");
    }

    private JButton menuButton(JButton jb) {
                jb.setFont(new Font("Serif", Font.BOLD, 20));
		jb.setAlignmentX(JButton.CENTER_ALIGNMENT);
		jb.setOpaque(true);
		jb.setBackground(new Color(111,148,53));
		jb.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.RED), new EmptyBorder(20, 20, 20, 20)));
		
		return jb;    
    }
    
    private JButton backButton(JButton bb) {
                bb.setFont(new Font("Serif", Font.BOLD, 20));
		bb.setAlignmentX(JButton.CENTER_ALIGNMENT);
		bb.setOpaque(true);
                bb.setSize(new Dimension(780,50));
		bb.setBackground(new Color(111,148,53));
		bb.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.RED), new EmptyBorder(20, 364, 20, 364)));
		
		return bb;    
    }
}
