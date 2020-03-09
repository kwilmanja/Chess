import java.awt.Color;
public class Board { 

	private Square[][] squares;

	public Board(int r, int c) {
		squares = new Square[r][c];

		for (int row = 0; row < squares.length; row++) {
			for (int col = 0; col < squares[row].length; col++) {
				int rank = r - row;
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
		if (rank <= getRank() && rank > 0 && file <= getFile() && file > 0) {
			return squares[getRank() - rank][file - 1];
		} else {
			return null;
		}
	}

	public int getRank(){
		return this.squares.length;
	}

	public int getFile(){
		return this.squares[0].length;
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