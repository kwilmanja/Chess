import java.awt.Color;
public class Board { 

	private Square[][] squares;

	public Board() {
		squares = new Square[8][8];

		for (int row = 0; row < squares.length; row++) {
			for (int col = 0; col < squares[row].length; col++) {
				int rank = 8 - row;
				int file = col + 1;
				if ((rank % 2 == 0 && file % 2 == 0) || (rank % 2 == 1 && file % 2 == 1)) {
					squares[row][col] = new Square(rank, file, Color.BLACK);
				} else {
					squares[row][col] = new Square(rank, file, Color.WHITE);
				}
			}
		}
	}

	public Square getSquare(int rank, int file) {
		if (rank <= 8 && rank > 0 && file <= 8 && file > 0) {
			return squares[8 - rank][file - 1];
		} else {
			return null;
		}
	}

	public void clearBoard() {
		for (Square[] row : squares){
			for (Square square : row){
				if(square.isHighlighted()) square.toggleHighlight();
				if(square.getPiece() != null) square.setPiece(null);
			}
		}
	}

}