import java.awt.Color;
public class ChessGame {

	private Board board;

	public ChessGame() {
		this.board = board;
	}

	public void placeRook(int rank, int file) {
		board.clearBoard();
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if ((board.getSquare(row, col).getRank() == rank) && (board.getSquare(row, col).getFile() == file)) {
					board.getSquare(row, col).setPiece("r");
				} else if ((board.getSquare(row, col).getRank() == rank || board.getSquare(row, col).getFile() == file)
					&& (board.getSquare(row, col).getRank() != rank && board.getSquare(row, col).getFile() != file)) {
					board.getSquare(row, col).toggleHighlight();
				}
			}
		}
	}

	public void placeKnight(int rank, int file) {
		board.clearBoard();
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if ((board.getSquare(row, col).getRank() == rank) && (board.getSquare(row, col).getFile() == file)) {
					board.getSquare(row, col).setPiece("k");
				} else if (((Math.abs(board.getSquare(row, col).getRank()) - rank == 2) && (Math.abs(board.getSquare(row, col).getFile() - rank) == 1))
				|| ((Math.abs(board.getSquare(row, col).getRank()) - rank == 1) && (Math.abs(board.getSquare(row, col).getFile() - rank) == 2))) {
					board.getSquare(row, col).toggleHighlight();
				}
			}
		}
	}

		public void placeBishop(int rank, int file) {
		board.clearBoard();
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if ((board.getSquare(row, col).getRank() == rank) && (board.getSquare(row, col).getFile() == file)) {
					board.getSquare(row, col).setPiece("b");
				} 
				for (int i = 1; i <= 8 - board.getSquare(row, col).getRank() && i <= 8 - board.getSquare(row, col).getFile(); i++) {
					if (Math.abs(board.getSquare(row, col).getRank() - rank) == i && Math.abs(board.getSquare(row, col).getFile() - file) == i) {
						board.getSquare(row, col).toggleHighlight();
					}
				}
			}
		}

		
	}

}