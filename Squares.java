import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JFrame;

class Squares extends JComponent {
	private Board board;
	private Color color;

	public Squares(Board board){
		super();
		this.board = board;
	}

  public void paint(Graphics g) {
    for (int r = board.getRank(); r >= 1; r--) {
		for (int f = 1; f <= board.getFile(); f++) {
			//Check Highlight
			if(board.getSquare(r,f).isHighlighted()) {
				this.color = Color.YELLOW;
				g.setColor(color);
				g.fillRect(f * 25 - 25, (25 * board.getRank()) - r * 25, 25, 25);
			}
			else{
				//Set Color:
				if(board.getSquare(r,f).getColor() == Color.WHITE) { this.color = Color.WHITE;}
				else {this.color = Color.BLACK;}
				//Set Piece:
				g.setColor(color);
				g.fillRect(f * 25 - 25, (25 * board.getRank()) - r * 25, 25, 25);
				if(board.getSquare(r,f).getPiece() != null){
					this.color = Color.GREEN;
					g.setColor(color);
					g.fillRect(f * 25 - 25, (25 * board.getRank()) - r * 25, 25, 25);
					this.color = Color.BLACK;
					g.setColor(color);
					g.fillRect(f * 25 - 20, (25 * board.getRank()) + 5 - r * 25, 15, 15);
				}
			}
		}
	}
  }

}