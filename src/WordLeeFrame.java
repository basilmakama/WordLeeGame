/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author Basil
 */
public class WordLeeFrame extends JFrame {
    	private final WordLeeIntro wordleeIntro = new WordLeeIntro();
        
        public WordLeeFrame() {
		super("WordLee");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
                addWordLeeIntro();
        }
        
        private void addWordLeeIntro() {
		super.getContentPane().removeAll();
		super.add(wordleeIntro);
		super.revalidate();
		super.repaint();
		super.setSize(new Dimension(800, 450));
		super.setMinimumSize(super.getSize());
		super.setResizable(false);
		super.setVisible(true);
	}
        
        
}
