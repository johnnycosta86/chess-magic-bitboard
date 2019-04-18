package chess.code.piece;

import chess.code.bitboard.Square;

public interface Piece {
	
	byte KING = 0;
	byte QUEEN = 1;
	byte ROOK = 2;
	byte BISHOP = 3;
	byte KNIGHT = 4;
	byte PAWN = 5;
	byte SPECIAL = 6;
	
	byte KING_KNIGHT = 2;
	byte KING_BISHOP = 3;
	byte KING_ROOK = 4;
	byte QUEEN_ROOK = 5;
	byte QUEEN_BISHOP = 6;
	byte QUEEN_KNIGHT = 7;
	
	byte EMTPY = 20;
	
	String[][] of = new String[][]{{"K", "k"}, {"Q", "q"}, {"R", "r"}, {"B", "b"}, {"N", "n"}, {"P", "p"}};

	
	byte getType();
	
	byte getColor();
	
	long bit();
	
	Square getSquare();
}
