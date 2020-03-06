import java.util.Scanner;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
 
import javax.swing.JFrame;
import javax.swing.JTextField;

public class ChessGame{ 

	private Board board;
	private static int selectedRank;
	private static int selectedFile;

	public ChessGame() {
		this.board = new Board();
	}

	public Board getBoard(){
		return this.board;
	}

	public void placeRook(int rank, int file) {
		board.clearBoard();
		board.getSquare(rank, file).setPiece("r");
		for(int r = 1; r<=8; r++){
			for(int f = 1; f<=8; f++){
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
				if (r > 0 &&  r <= 8 && f >0 && f <= 8){
					board.getSquare(r,f).toggleHighlight();	
				}
				r = rank + (2 * i);
				f = file + n;
				if (r > 0 &&  r <= 8 && f >0 && f <= 8){
					board.getSquare(r,f).toggleHighlight();
				}
			}
		}
	}

	public void placeBishop(int rank, int file) {
		board.clearBoard();
		board.getSquare(rank, file).setPiece("b");

		for (int i = 1; i <= 8-rank && i <= 8-file; i++){
			board.getSquare(rank + i, file + i).toggleHighlight();
		} 
		for (int i = 1; i < rank && i <= 8-file; i++){
			board.getSquare(rank - i, file + i).toggleHighlight();
		} 
		for (int i = 1; i <= 8-rank && i < file; i++){
			board.getSquare(rank + i, file - i).toggleHighlight();
		} 
		for (int i = 1; i < rank && i < file; i++){
			board.getSquare(rank - i, file - i).toggleHighlight();
		}
	}

	public void display(){
		String border = "\033[45m"; //background
		String black = "\033[40m"; //backgroud
		String white = "\033[47m"; //backgroud
		String highlight = "\033[43m"; //backgroud
		String whiteText = "\033[1;37m";
		String blackText = "\033[1;90m";

		String str = "";
		str += border + "          ";
		str += black + "\n";
		int moveNum = 0;
		char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();


		for (int r = 8; r >= 1; r--) {
			str += border + " ";
			for (int f = 1; f <= 8; f++) {
				//Check Highlight
				if(board.getSquare(r,f).isHighlighted()) {
					str += highlight + blackText + " "; //alphabet[moveNum];
					moveNum++;
				}
				else{
					//Set Color:
					if(board.getSquare(r,f).getColor() == Color.WHITE) { str += white + blackText;}
					else {str += black + whiteText;}
					//Set Piece:
					if(board.getSquare(r,f).getPiece() != null){ str += board.getSquare(r,f).getPiece();}
					else {str += " ";}
				}

			}
			str += border + " ";
			str += black + "\n";
		}
		str += border + "          ";
		str += black;

		System.out.println(str);	

	}



	public static void main(String[] args){
		ChessGame game = new ChessGame();
		selectedRank = 5;
		selectedFile = 5;
		//game.placeKnight(selectedRank, selectedFile);
		game.placeRook(selectedRank, selectedFile);
		//game.placeBishop(selectedRank,selectedFile);
		game.display();

		JFrame frame = new JFrame("Key Listener"); //initiates window
    	Container contentPane = frame.getContentPane(); //used to contain text field
    	KeyListener listener = new KeyListener() {
    	  @Override 
    	  public void keyPressed(KeyEvent event) {
    	      printEventInfo("Key Pressed", event);
    	      if (KeyEvent.getKeyText(event.getKeyCode()).equals("A") && selectedFile > 1) selectedFile--;
    	      if (KeyEvent.getKeyText(event.getKeyCode()).equals("W") && selectedRank < 8) selectedRank++;
    	      if (KeyEvent.getKeyText(event.getKeyCode()).equals("D") && selectedFile < 8) selectedFile++;
    	      if (KeyEvent.getKeyText(event.getKeyCode()).equals("S") && selectedRank > 1) selectedRank--;
    	      game.placeRook(selectedRank,selectedFile);
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
    	      System.out.println("   Code: " + KeyEvent.getKeyText(e.getKeyCode())); //This is what is important for Arrow Keys!!!
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
    	JTextField textField = new JTextField(); //create text field
    	textField.addKeyListener(listener); //add newly adopted key listener to text field
    	contentPane.add(textField, BorderLayout.NORTH); //add text field to pane
    	frame.pack(); //help ensure size of frame
    	frame.setVisible(true); //display frame




	}

}