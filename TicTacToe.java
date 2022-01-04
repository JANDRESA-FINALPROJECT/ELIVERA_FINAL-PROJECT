import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
/**
 * This is a simple version of Tictactoe application
 * @author Jandresa Elivera
 *
 */
public class TicTacToe implements ActionListener {
  
    JFrame board = new JFrame();
    JPanel title = new JPanel(); //panel to hold the title of the game
    JPanel button = new JPanel(); //panel to hold all the tictactoe_buttons
    JLabel text = new JLabel(); //label to hold the text that can display any message
    JButton[] tictactoe_buttons = new JButton[9]; //button that hold the array of tictactoe_buttons specifically 9 tictactoe_buttons
    int chance = 0;
    Random random = new Random(); //instance of a random class for randomly selecting turns of players
    boolean player1turn; //true if player 1 turn, otherwise, false
/**
 * Create the application    
 *  Creating the board, the title and button panel and the text field
 */
    TicTacToe() { //constructor

    	//creating the board or the frame
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setSize(1000,1000); //setting size of board
        board.getContentPane().setBackground(Color.BLACK); //setting background color
        board.setTitle("Tic Tac Toe"); //setting title
        board.setLayout(new BorderLayout()); //setting layout
        board.setVisible(true); //setting visibility of board to true
        
        //creating title panel
        title.setLayout(new BorderLayout());
        title.setBounds(0, 0, 1000, 200); //setting bounds to the panel (starting to the top left corner (0,0), length of 1000 and height of 200
        
        //creating button panel
        button.setLayout(new GridLayout(3, 3)); //setting the grid layout to 3x3
        button.setBackground(new Color(150, 150, 150)); //setting background color
        //adding all of the tictactoe_buttons
        for (int i = 0; i < 9; i++) { //for loop
            tictactoe_buttons[i] = new JButton(); //tictactoe_buttons with index i
            button.add(tictactoe_buttons[i]); //adding specific button one by one through for loop to button panel
            tictactoe_buttons[i].setFont(new Font("Ink Free", Font.PLAIN, 200)); //setting font for the x and o
            tictactoe_buttons[i].setFocusable(false); //setting tictactoe_buttons not focusable
            tictactoe_buttons[i].addActionListener(this); //add action listener to each of the tictactoe_buttons
        }
        
        //creating text
        text.setBackground(new Color(173,216,230)); //setting background color to light blue
        text.setForeground(new Color(153,153,153)); //setting background color to gray
        text.setFont(new Font("TimesRoman", Font.BOLD, 80)); //setting font of the text
        text.setHorizontalAlignment(JLabel.CENTER); //setting text to horizontal alignment and positioning to the center
        text.setText("Tic Tac Toe"); //setting text to have the title of the game
        text.setOpaque(true); //setting opaque to true
        
        title.add(text); //adding text to title panel
        board.add(title, BorderLayout.NORTH); //adding title panel to board and setting the border layout to north to make the title panel position to the top
        board.add(button); //adding button panel to board
        first_turn(); //calling first_turn method
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) { //for loop
            if (e.getSource() == tictactoe_buttons[i]) { //if-else statement
                if (player1turn) {
                    if (tictactoe_buttons[i].getText() == "") { //check if there is no text place on that button
                        tictactoe_buttons[i].setForeground(new Color(0,204,0)); //setting the foreground color to blue
                        tictactoe_buttons[i].setText("x"); //setting text to x
                        player1turn = false; //setting to false and reflecting a message that says o turn
                        text.setText("O turn"); 
                        chance++; //incrementing chance 
                        Check(); //calling the method check
                    }
                } 
                else {
                    if (tictactoe_buttons[i].getText() == "") { //check if there is no text place on that button
                        tictactoe_buttons[i].setForeground(new Color(255,0,0)); //setting the foreground color to red
                        tictactoe_buttons[i].setText("o"); //setting text to o
                        player1turn = true; //setting to false and reflecting a message that says x turn
                        text.setText("X turn");
                        chance++; //incrementing chance 
                        Check(); //calling the method check
                    }
                }
            }
        }
    }
    public void first_turn() { //method that determines who is the first turn
        try {
            text.setText("TicTacToe");	//displaying the title 
            Thread.sleep(1500); //displaying the title for 1500 milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int chance=random.nextInt(500); //implementing random to randomly select if x will be going first or o
        if (chance%2 == 0) { //if chance equals to even, then player 1 or x turn
            player1turn = true;
            text.setText("X turn");
        } else { //otherwise player 2 or o turn
            player1turn = false;
            text.setText("O turn");
        }
    }
    
    
    public void Check() { //method to check if any of the player won by having if statements for winning conditions
    	//winning combination (0,1,2 and 3,4,5 and 6,7,8 and 0,3,6 and 1,4,7 and 2,5,8 and 0,4,8 and 2,4,6)
    	//calling winX method and receiving the parameter of the winning combination if it is equal to x
    	//horizontal alignment of combination
        if ((tictactoe_buttons[0].getText() == "x") && (tictactoe_buttons[1].getText() == "x") && (tictactoe_buttons[2].getText() == "x")) {
            winX(0, 1, 2);
        }
        if ((tictactoe_buttons[3].getText() == "x") && (tictactoe_buttons[4].getText() == "x") && (tictactoe_buttons[5].getText() == "x")) {
            winX(3, 4, 5);
        }
        if ((tictactoe_buttons[6].getText() == "x") && (tictactoe_buttons[7].getText() == "x") && (tictactoe_buttons[8].getText() == "x")) {
            winX(6, 7, 8);
        }
        //vertical alignment of combination
        if ((tictactoe_buttons[0].getText() == "x") && (tictactoe_buttons[3].getText() == "x") && (tictactoe_buttons[6].getText() == "x")) {
            winX(0, 3, 6);
        }
        if ((tictactoe_buttons[1].getText() == "x") && (tictactoe_buttons[4].getText() == "x") && (tictactoe_buttons[7].getText() == "x")) {
            winX(1, 4, 7);
        }
        if ((tictactoe_buttons[2].getText() == "x") && (tictactoe_buttons[5].getText() == "x") && (tictactoe_buttons[8].getText() == "x")) {
            winX(2, 5, 8);
        }
        //cross-over alignment of combination
        if ((tictactoe_buttons[0].getText() == "x") && (tictactoe_buttons[4].getText() == "x") && (tictactoe_buttons[8].getText() == "x")) {
            winX(0, 4, 8);
        }
        
        if ((tictactoe_buttons[2].getText() == "x") && (tictactoe_buttons[4].getText() == "x") && (tictactoe_buttons[6].getText() == "x")) {
            winX(2, 4, 6);
        }
        
      //calling winO method and receiving the parameter of the winning combination if it is equal to o
      //horizontal alignment of combination
        if ((tictactoe_buttons[0].getText() == "o") && (tictactoe_buttons[1].getText() == "o") && (tictactoe_buttons[2].getText() == "o")) {
            winO(0, 1, 2);
        }
        if ((tictactoe_buttons[3].getText() == "o") && (tictactoe_buttons[4].getText() == "o") && (tictactoe_buttons[5].getText() == "o")) {
            winO(3, 4, 5);
        } 
        if ((tictactoe_buttons[6].getText() == "o") && (tictactoe_buttons[7].getText() == "o") && (tictactoe_buttons[8].getText() == "o")) {
            winO(6, 7, 8);
       //vertical alignment of combination
        }
        if ((tictactoe_buttons[0].getText() == "o") && (tictactoe_buttons[3].getText() == "o") && (tictactoe_buttons[6].getText() == "o")) {
            winO(0, 3, 6);
        }
        if ((tictactoe_buttons[1].getText() == "o") && (tictactoe_buttons[4].getText() == "o") && (tictactoe_buttons[7].getText() == "o")) {
            winO(1, 4, 7);
        }
        if ((tictactoe_buttons[2].getText() == "o") && (tictactoe_buttons[5].getText() == "o") && (tictactoe_buttons[8].getText() == "o")) {
            winO(2, 5, 8);
        }
       //cross-over alignment of combination
        if ((tictactoe_buttons[0].getText() == "o") && (tictactoe_buttons[4].getText() == "o") && (tictactoe_buttons[8].getText() == "o")) {
            winO(0, 4, 8);
        }
        
        if ((tictactoe_buttons[2].getText() == "o") && (tictactoe_buttons[4].getText() == "o") && (tictactoe_buttons[6].getText() == "o")) {
            winO(2, 4, 6);
        } 
        if(chance==9) {
            text.setText("Draw"); //setting Draw message
             game_over("Draw"); //calling the game_over method and setting draw as its string result parameter
        }
    }
    //checking who wins in the check method and instantly calls either of the two methods below (winX or winO)
    public void winO(int a, int b, int c) { // method for winO and parameters to receive the combination that make either of the players win
    	//setting background color to red if o wins
    	tictactoe_buttons[a].setBackground(Color.RED);
        tictactoe_buttons[b].setBackground(Color.RED);
        tictactoe_buttons[c].setBackground(Color.RED);
        for (int i = 0; i < 9; i++) { //for loop
            tictactoe_buttons[i].setEnabled(false); //disable all the tictactoe_buttons so players cannot keep playing if either of the players win
        }
        text.setText("O Wins"); //set text to o wins
        game_over("O Wins"); //setting game over method's message to o wins
    }
    public void winX(int a, int b, int c) { //method for winX and parameters to receive the combination that make either of the players win
        //setting background color to green if x wins
    	tictactoe_buttons[a].setBackground(Color.GREEN);
        tictactoe_buttons[b].setBackground(Color.GREEN);
        tictactoe_buttons[c].setBackground(Color.GREEN);
        for (int i = 0; i < 9; i++) { //for loop
            tictactoe_buttons[i].setEnabled(false); //disable all the tictactoe_buttons so players cannot keep playing if either of the players win
        }
        text.setText("X wins"); //set text to x wins
        game_over("X Wins"); //setting game over method's message to x wins
    }
    
    public void game_over(String result){ //game_over method
        chance = 0; //initializing chance to 0
        Object[] choice={"YES","NO"};
        int x=JOptionPane.showOptionDialog(board, result+ "\n Do You Want To Play Again?","Game Over",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,choice,choice[0]);
        if(x==0){
            board.dispose(); //disposing or clean up board
            new TicTacToe(); //displaying another TicTacToe game
        }
        else{
            board.dispose(); //otherwise, board dispose or clean up and exit
        }
    
    }
    
  }