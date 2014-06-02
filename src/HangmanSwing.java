import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class HangmanSwing extends JFrame {

	public static void main(String args[]) throws IOException {
		new HangmanSwing();
	}
	
	HangmanSwing() throws IOException {
		
		//setting frame settings and layout
		final JFrame frame = new JFrame("Hangman");
		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		frame.setBounds((int)(dim.getHeight()/2),(int)(dim.getWidth()/9), 500, 80);
		frame.setSize(540, 250);
		frame.setLayout(new GridLayout(3,1));
		
		frame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	             System.exit(0);
	          }        
	       });  
		
		//setting button functionality
		final Hangman h = new Hangman();
		
		final JPanel area = new JPanel();
		area.setLayout(null);
		
		final JLabel result = new JLabel("Used letters:",SwingConstants.LEFT);
		final JScrollPane scroll = new JScrollPane(result);
		final ImageIcon ic = new ImageIcon(ImageIO.read(ResourceLoader.load("images/b0.png")));
		
		final JLabel image = new JLabel(ic);
		final JLabel text = new JLabel("Choose a letter:");
		final JTextField textArea = new JTextField("");
		final JButton submit = new JButton("Submit");
		final JTextField answerArea = new JTextField(h.getCurrent().toString());
		
		image.setLocation(10,0);
		image.setSize(300, 200);
		scroll.setLocation(320, 11);
		scroll.setSize(200, 180);
		text.setLocation(100, 200);
		text.setSize(90, 10);
		textArea.setLocation(195, 198);
		textArea.setSize(90, 20);
		submit.setLocation(290, 197);
		submit.setSize(80, 20);
		answerArea.setLocation(130, 20);
		answerArea.setSize(169, 50);
		answerArea.setEditable(false);
		answerArea.setFocusable(false);
		answerArea.setFont(new Font("Serif", Font.PLAIN, 40));
		
		area.add(answerArea);
		area.add(submit);
		area.add(textArea);
		area.add(text);
		area.add(image);
		area.add(scroll);
		area.setOpaque(true);
		
		frame.setContentPane(area);
		
		submit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
       	 
        	try{
        		
        		String message="",word="";
        		if(!Character.isLetter(textArea.getText().charAt(0))){
        			
        			JOptionPane.showMessageDialog(frame, "Please enter a valid character!", "Error", 0);
             		 textArea.setText("");
        		}
        		else{
        			
        			word = h.getWord();
        			message=h.guess(textArea.getText().charAt(0));
        		}
        		
        		if(message.equals("wrong")){
              		 
              		 result.setText("Used letters: "+h.getPastGuesses());
              		 textArea.setText("");
              	 }
              	 else if(message.equals("past")){
              		 
              		 JOptionPane.showMessageDialog(frame, "You have already tried this letter!", "Error", 0);
              		 textArea.setText("");
              	 }
              	 else if(message.equals("dead")){
              		 
              		answerArea.setText(h.getCurrent().toString());             		 
              		textArea.setText("");
              		try {
    					image.setIcon(new ImageIcon(ImageIO.read(ResourceLoader.load("images/b6.png"))));
    				} catch (IOException e1) {
    					// TODO Auto-generated catch block
    					JOptionPane.showMessageDialog(frame, "PICTURE DIDNT LOAD", "Error", 0);
    				}
           		 	JOptionPane.showMessageDialog(frame, "You lost the game! Your word was "+word+"!", "Whoops!", 2);
           		 	result.setText("Used letters: ");
              	 }
              	 else if(message.equals("success")){
              		 
              		 answerArea.setText(h.getCurrent().toString());             		 
            		 textArea.setText("");
            		 JOptionPane.showMessageDialog(frame, "You won the game!", "Congratulations!", 3);
            		 result.setText("Used letters: ");
               	 }
              	 else{
              		 
              		 answerArea.setText(h.getCurrent().toString());
              		 result.setText("Used letters: "+h.getPastGuesses());
             		 textArea.setText("");
              	 }
        	}
        	catch(Exception x){
        		String message="Please enter a valid value!";
        		
        		JOptionPane.showMessageDialog(frame, message, "Error", 0);
        	}
        	
        	if(h.getNumOfGuesses()==1){
        		
        		try {
					image.setIcon(new ImageIcon(ImageIO.read(ResourceLoader.load("images/b1.png"))));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame, "PICTURE DIDNT LOAD", "Error", 0);
				}
        	}
        	else if(h.getNumOfGuesses()==2){
        		
        		try {
					image.setIcon(new ImageIcon(ImageIO.read(ResourceLoader.load("images/b2.png"))));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame, "PICTURE DIDNT LOAD", "Error", 0);
				};
        	}
        	else if(h.getNumOfGuesses()==3){
        		
        		try {
					image.setIcon(new ImageIcon(ImageIO.read(ResourceLoader.load("images/b3.png"))));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame, "PICTURE DIDNT LOAD", "Error", 0);
				}
        	}
        	else if(h.getNumOfGuesses()==4){
        		
        		try {
					image.setIcon(new ImageIcon(ImageIO.read(ResourceLoader.load("images/b4.png"))));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame, "PICTURE DIDNT LOAD", "Error", 0);
				}
        	}
        	else if(h.getNumOfGuesses()==5){
        		
        		try {
					image.setIcon(new ImageIcon(ImageIO.read(ResourceLoader.load("images/b5.png"))));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame, "PICTURE DIDNT LOAD", "Error", 0);
				}
        	}
        	else if(h.getNumOfGuesses()==6){
        		
        		try {
					image.setIcon(new ImageIcon(ImageIO.read(ResourceLoader.load("images/b6.png"))));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame, "PICTURE DIDNT LOAD", "Error", 0);
				}
        	}
        	else{
        		
        		try {
					image.setIcon(new ImageIcon(ImageIO.read(ResourceLoader.load("images/b0.png"))));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame, "PICTURE DIDNT LOAD", "Error", 0);
				}
        	}
        		
       	 
       	 
        }          
     });
		
		frame.setVisible(true);

	}

}
