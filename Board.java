import java.awt.Color;
public class Board {

	private Square[][] squares;

	public Board() {
		squares = new Square[8][8];

		for (int row = 0; row <squares.length; row++) {
			for (int col = 0; col < squares[0].length; col++) {
				int rank = 8 - row;
				int file = col + 1;
				if ((rank % 2 == 0 && file % 2 == 0) || (rank % 2 == 1 && file % 2 == 1)) {
					squares[row][col] = new Square(rank, file, Color.BLACK);
					System.out.print("~");
				} else {
					squares[row][col] = new Square(rank, file, Color.WHITE);
					System.out.print("*");
				}
			}
		}
	}

	public Square getSquare(int rank, int file) {
		if (rank <= 8 && file <= 8) {
			return squares[8 - rank][file + 1];
		} else {
			return null;
		}
	}

	public void clearBoard() {
		for (int rank = 1; rank <= 8; rank++) {
			for (int file = 1; file <= 8; file++) {
				if (this.getSquare(rank, file).isHighlighted()) {
					this.getSquare(rank, file).toggleHighlight();
				}
			}
		}
	}

}