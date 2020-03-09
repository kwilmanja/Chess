import java.util.Scanner;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
 
import javax.swing.JFrame;
import javax.swing.JTextField;

import javax.swing.*;

public class ChessGame{ 

	private Board board;
	private static int selectedRank;
	private static int selectedFile;
	private static JFrame frame = new JFrame("CHESS TRAINER"); //initiates window

	public ChessGame(int r, int f) {
		this.board = new Board(r,f);
	}

	public Board getBoard(){
		return this.board;
	}

	public void placeRook(int rank, int file) {
		board.clearBoard();
		board.getSquare(rank, file).setPiece("r");
		for(int r = 1; r<=board.getRank(); r++){
			for(int f = 1; f<=board.getFile(); f++){
				if((r == rank && f != file) || (f == file && r != rank)) board.getSquare(r,f).toggleHighlight();
			}
		}
	}

	public void placeKnight(int rank, int file) {
		board.clearBoard();
		board.getSquare(rank, file).setPiece("k");
		for (int i = -1; i<2; i += 2){
			for(int n = -1; n<2; n += 2){
				int r = rank + i;
				int f = file + (2 * n);
				if (r > 0 &&  r <= board.getRank() && f >0 && f <= board.getFile()){
					board.getSquare(r,f).toggleHighlight();	
				}
				r = rank + (2 * i);
				f = file + n;
				if (r > 0 &&  r <= board.getRank() && f >0 && f <= board.getFile()){
					board.getSquare(r,f).toggleHighlight();
				}
			}
		}
	}

	public void placeBishop(int rank, int file) {
		board.clearBoard();
		board.getSquare(rank, file).setPiece("b");

		for (int i = 1; i <= board.getRank()-rank && i <= board.getFile()-file; i++){
			board.getSquare(rank + i, file + i).toggleHighlight();
		} 
		for (int i = 1; i < rank && i <= board.getFile()-file; i++){
			board.getSquare(rank - i, file + i).toggleHighlight();
		} 
		for (int i = 1; i <= board.getRank()-rank && i < file; i++){
			board.getSquare(rank + i, file - i).toggleHighlight();
		} 
		for (int i = 1; i < rank && i < file; i++){
			board.getSquare(rank - i, file - i).toggleHighlight();
		}
	}

	public void placeQueen(int rank, int file){
		board.clearBoard();
		board.getSquare(rank, file).setPiece("q");	
		for (int i = 1; i <= board.getRank()-rank && i <= board.getFile()-file; i++){
			board.getSquare(rank + i, file + i).toggleHighlight();
		} 
		for (int i = 1; i < rank && i <= board.getFile()-file; i++){
			board.getSquare(rank - i, file + i).toggleHighlight();
		} 
		for (int i = 1; i <= board.getRank()-rank && i < file; i++){
			board.getSquare(rank + i, file - i).toggleHighlight();
		} 
		for (int i = 1; i < rank && i < file; i++){
			board.getSquare(rank - i, file - i).toggleHighlight();
		}
		for(int r = 1; r<=board.getRank(); r++){
			for(int f = 1; f<=board.getFile(); f++){
				if((r == rank && f != file) || (f == file && r != rank)) board.getSquare(r,f).toggleHighlight();
			}
		}
	}

	public void display(){
		//String border = "\033[45m"; //background
		//String black = "\033[40m"; //backgroud
		//String white = "\033[47m"; //backgroud
		//String highlight = "\033[43m"; //backgroud
		//String whiteText = "\033[1;37m";
		//String blackText = "\033[1;90m";
		//String str = "";
		//str += border + "          ";
		//str += black + "\n";
		//int moveNum = 0;
		//char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		//for (int r = 8; r >= 1; r--) {
		//	str += border + " ";
		//	for (int f = 1; f <= 8; f++) {
		//		//Check Highlight
		//		if(board.getSquare(r,f).isHighlighted()) {
		//			str += highlight + blackText + " "; //alphabet[moveNum];
		//			moveNum++;
		//		}
		//		else{
		//			//Set Color:
		//			if(board.getSquare(r,f).getColor() == Color.WHITE) { str += white + blackText;}
		//			else {str += black + whiteText;}
		//			//Set Piece:
		//			if(board.getSquare(r,f).getPiece() != null){ str += board.getSquare(r,f).getPiece();}
		//			else {str += " ";}
		//		}
		//	}
		//	str += border + " ";
		//	str += black + "\n";
		//}
		//str += border + "          ";
		//str += black;
		//System.out.println(str);



		Squares squares = new Squares(board);
		frame.getContentPane().add(squares);
		frame.setSize(50 + getBoard().getFile() * 25, 75 + getBoard().getRank() * 25);
		//frame.pack();
      	frame.setVisible(true);
	}



	public static void main(String[] args){
		ChessGame game = new ChessGame(15,15);
		selectedRank = 5;
		selectedFile = 5;
		game.placeKnight(selectedRank, selectedFile);
		//game.placeQueen(selectedRank, selectedFile);
		//game.placeBishop(selectedRank,selectedFile);
		//game.place(selectedRank, selectedFile);

		/////////////////////////////////////////////////////////////////////////////////////////////////////////

    	//Container contentPane = frame.getContentPane(); //used to contain text field
    	
    	KeyListener listener = new KeyListener() {
    	  @Override 
    	  public void keyPressed(KeyEvent event) {
    	      printEventInfo("Key Pressed", event);
    	      if (KeyEvent.getKeyText(event.getKeyCode()).equals("A") && selectedFile > 1) selectedFile--;
    	      if (KeyEvent.getKeyText(event.getKeyCode()).equals("W") && selectedRank < game.getBoard().getRank()) selectedRank++;
    	      if (KeyEvent.getKeyText(event.getKeyCode()).equals("D") && selectedFile < game.getBoard().getFile()) selectedFile++;
    	      if (KeyEvent.getKeyText(event.getKeyCode()).equals("S") && selectedRank > 1) selectedRank--;
    	      game.placeKnight(selectedRank,selectedFile);
    	      game.display();
    	  }
    	  @Override
    	  public void keyReleased(KeyEvent event) {
    	      //printEventInfo("Key Released", event);
    	  }
    	  @Override
    	  public void keyTyped(KeyEvent event) {
    	      printEventInfo("Key Typed", event);
    	  }
    	  private void printEventInfo(String str, KeyEvent e) {
    	      System.out.println("   Code: " + KeyEvent.getKeyText(e.getKeyCode())); //This is what is important!!!
    	  }
    	  private String keyboardLocation(int keybrd) {
    	    switch (keybrd) {
    	      case KeyEvent.KEY_LOCATION_RIGHT:
    	        return "Right";
    	      case KeyEvent.KEY_LOCATION_LEFT:
    	        return "Left";
    	      case KeyEvent.KEY_LOCATION_NUMPAD:
    	        return "NumPad";
    	      case KeyEvent.KEY_LOCATION_STANDARD:
    	        return "Standard";
    	      case KeyEvent.KEY_LOCATION_UNKNOWN:
    	      default:
    	      return "Unknown";
    	    }
    	  }
    	};

    	///////////////////////////////////////////////////////////////////////////////////////////////////////////////

   		JTextField textField = new JTextField(); //create text field
    	textField.addKeyListener(listener); //add newly adopted key listener to text field
    	frame.getContentPane().add(textField, BorderLayout.NORTH); //add text field to pane
		game.display();
	}

}