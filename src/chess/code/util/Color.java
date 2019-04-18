package chess.code.util;

import chess.code.bitboard.Bitboard;
import chess.code.bitboard.Square;
import chess.code.piece.Piece;

public class Color {
	public static final byte WHITE = 0;
	public static final byte BLACK = 1;
	
	public static byte get(final String type) {
		if(type.equals("w")) {
			return WHITE;
		} else {
			return BLACK;
		}
	}
	
	public static String name(final byte color) {
		if(color == WHITE) {
			return "WHITE";
		} 
		else {
			return "BLACK";
		}
	}
	
	public static String colorMoving(final long[][] bitboard) {
		if(Bitboard.onBit(Square.D1.id, bitboard[Piece.SPECIAL][Color.WHITE])){ 
			return "w";
		} else {
			return "b";
		}
	}
	
	public static long[][] colorMoving(final long[][] bitboard, final byte color) {
		if(color == Color.WHITE) {
			bitboard[Piece.SPECIAL][Color.WHITE] = 
					Bitboard.setBit(Square.D1.id, bitboard[Piece.SPECIAL][Color.WHITE]);
		}
		else {
			bitboard[Piece.SPECIAL][Color.WHITE] = 
					Bitboard.clearBit(Square.D1.id, bitboard[Piece.SPECIAL][Color.WHITE]);
		}
		
		return bitboard;
	}
}
