package sixteenSquares;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class sixteenSquares extends JFrame implements KeyListener, ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1378890472257439767L;
	static JPanel game, replayGame;
	static JLabel winner;
	static ArrayList<String> textArrayList = new ArrayList<String>();
	static String[] keyList1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"};
	static ArrayList<JButton> squares = new ArrayList<JButton>();
	static Random r = new Random();
	static File chicken = new File("chicken.png");
	static int hotSquare;
	static String hotSquare2;
	static boolean shiftcheat;
	static boolean win = false;
	public static void main(String[] args) {
		sixteenSquares window = new sixteenSquares();
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	sixteenSquares(){
		super("16 Squares");
		replayGame = new JPanel();
		winner = new JLabel("WINNER WINNER CHICKEN DINNER!");
		winner.setFont(new Font("Arial", Font.BOLD, 25));
		replayGame.add(winner);
		//replayGame.add(chicken);
		game = new JPanel(new GridLayout(4,4));
		this.add(game);
		initiateTextArrayList();
		initiateButtons();
		buttonColor();
		this.setSize(500,500);
		this.setVisible(true);
		this.setLayout(new GridLayout(1,0));
	}
	
	void buttonColor() {
		for (int i = 0; i <= 15; i++){
			if (squares.get(i).getText().equals("1") || squares.get(i).getText().equals("2") || squares.get(i).getText().equals("3") || squares.get(i).getText().equals("4")){
				squares.get(i).setBackground(Color.GREEN);
			}
			if (squares.get(i).getText().equals("5") || squares.get(i).getText().equals("6") || squares.get(i).getText().equals("7") || squares.get(i).getText().equals("8")){
				squares.get(i).setBackground(Color.ORANGE);
			}
			if (squares.get(i).getText().equals("9") || squares.get(i).getText().equals("10") || squares.get(i).getText().equals("11") || squares.get(i).getText().equals("12")){
				squares.get(i).setBackground(Color.PINK);
			}
			if (squares.get(i).getText().equals("13") || squares.get(i).getText().equals("14") || squares.get(i).getText().equals("15") || squares.get(i).getText().equals("16")){
				squares.get(i).setBackground(Color.CYAN);
			}
		}
		squares.get(hotSquare).setBackground(Color.yellow);
	}
	
	void initiateButtons(){
		for (int i = 0; i <= 15; i++){
			squares.add(new JButton());
			squares.get(i).setFont(new Font("Arial", Font.BOLD, 15));
			squares.get(i).addKeyListener(this);
			game.add(squares.get(i));
			if (i <= 15){
				squares.get(i).setText(textArrayList.get(i));
				if (!textArrayList.get(i).equals("1")){			
				}
				else {
					squares.get(i).setBackground(Color.YELLOW);
					hotSquare = i;
					//System.out.println(hotSquare);
				}

			}
		}
	}
	
	void initiateTextArrayList(){
		for (int i = 1; i <= 16; i++){
			textArrayList.add(i + "");			
		}
		Collections.shuffle(textArrayList, r);
	}
	
	void winGame(){
		int score = 0;
		for (int i = 0; i <=15; i++){
			if (textArrayList.get(i).equals(keyList1[i])){
					score += 1;
					//System.out.println(score);
			}
		}
		if (score == 16){
			this.add(replayGame);
			game.setVisible(false);
			this.remove(game);
			replayGame.setVisible(true);
		}
	}
	
	void switchSquares(int motion){
		if (((hotSquare + motion) <= 15) && ((hotSquare + motion) >= 0)) {
			if (shiftcheat == false){
				//a
				hotSquare2 = textArrayList.get(hotSquare + motion);
				
				//b
				squares.get(hotSquare + motion).setText(textArrayList.get(hotSquare));
				textArrayList.set(hotSquare + motion, textArrayList.get(hotSquare));
				
				//c
				squares.get(hotSquare).setText(hotSquare2);
				textArrayList.set(hotSquare, hotSquare2);				
			}
			
			hotSquare += motion;
			buttonColor();
			//System.out.println(textArrayList);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_SHIFT){
			//System.out.println("Shift Cheat On");
			shiftcheat = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_SHIFT){
			shiftcheat = false;
			//System.out.println("Shift Cheat Off");
		}

		if (event.getKeyCode() == KeyEvent.VK_UP){
			switchSquares(-4);
		}
		else if (event.getKeyCode() == KeyEvent.VK_DOWN){
			switchSquares(4);
		}
		else if (event.getKeyCode() == KeyEvent.VK_LEFT){
			switchSquares(-1);
		}
		else if (event.getKeyCode() == KeyEvent.VK_RIGHT){
			switchSquares(1);
		}
		
		winGame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
