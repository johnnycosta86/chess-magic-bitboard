package chess.code.piece;

import static chess.code.bitboard.Square.*;
import static chess.code.util.Color.*;

import java.util.HashMap;
import java.util.Map;

import chess.code.bitboard.Bitboard;
import chess.code.bitboard.Square;

public class King implements Piece {
	private static Map<Long, Long> position = new HashMap<>();
	
	private static final long[][] values = new long[2][8];
	
	static {
		
		values[WHITE][KING] = Square.E1.value;
		values[WHITE][QUEEN] = Square.D1.value;
		values[WHITE][KING_KNIGHT] = Square.G1.value;
		values[WHITE][KING_BISHOP] = Square.F1.value; 
		values[WHITE][KING_ROOK] = Square.H1.value;
		values[WHITE][QUEEN_ROOK] = Square.A1.value;
		values[WHITE][QUEEN_BISHOP] = Square.C1.value;
		values[WHITE][QUEEN_KNIGHT] = Square.B1.value;
		values[BLACK][KING] = Square.E8.value;
		values[BLACK][QUEEN] = Square.D8.value;
		values[BLACK][KING_KNIGHT] = Square.G8.value;
		values[BLACK][KING_BISHOP] = Square.F8.value; 
		values[BLACK][KING_ROOK] = Square.H8.value;
		values[BLACK][QUEEN_ROOK] = Square.A8.value;
		values[BLACK][QUEEN_BISHOP] = Square.C8.value;
		values[BLACK][QUEEN_KNIGHT] = Square.B8.value;
		
		position.put(A1.value, Long.valueOf(0xc040L)); 
		position.put(A2.value, Long.valueOf(0xc040c0L)); 
		position.put(A3.value, Long.valueOf(0xc040c000L)); 
		position.put(A4.value, Long.valueOf(0xc040c00000L)); 
		position.put(A5.value, Long.valueOf(0xc040c0000000L)); 
		position.put(A6.value, Long.valueOf(0xc040c000000000L));
		position.put(A7.value, Long.valueOf(0xc040c00000000000L)); 
		position.put(A8.value, Long.valueOf(0x40c0000000000000L)); 
		
		position.put(B1.value, Long.valueOf(0xe0a0L)); 
		position.put(B2.value, Long.valueOf(0xe0a0e0L)); 
		position.put(B3.value, Long.valueOf(0xe0a0e000L)); 
		position.put(B4.value, Long.valueOf(0xe0a0e00000L)); 
		position.put(B5.value, Long.valueOf(0xe0a0e0000000L)); 
		position.put(B6.value, Long.valueOf(0xe0a0e000000000L)); 
		position.put(B7.value, Long.valueOf(0xe0a0e00000000000L)); 
		position.put(B8.value, Long.valueOf(0xa0e0000000000000L)); 
		
		position.put(C1.value, Long.valueOf(0x7050L)); 
		position.put(C2.value, Long.valueOf(0x705070L)); 
		position.put(C3.value, Long.valueOf(0x70507000L)); 
		position.put(C4.value, Long.valueOf(0x7050700000L)); 
		position.put(C5.value, Long.valueOf(0x705070000000L)); 
		position.put(C6.value, Long.valueOf(0x70507000000000L)); 
		position.put(C7.value, Long.valueOf(0x7050700000000000L)); 
		position.put(C8.value, Long.valueOf(0x5070000000000000L)); 
		
		position.put(D1.value, Long.valueOf(0x3828L)); 
		position.put(D2.value, Long.valueOf(0x382838L)); 
		position.put(D3.value, Long.valueOf(0x38283800L)); 
		position.put(D4.value, Long.valueOf(0x3828380000L)); 
		position.put(D5.value, Long.valueOf(0x382838000000L)); 
		position.put(D6.value, Long.valueOf(0x38283800000000L)); 
		position.put(D7.value, Long.valueOf(0x3828380000000000L)); 
		position.put(D8.value, Long.valueOf(0x2838000000000000L)); 
		
		position.put(E1.value, Long.valueOf(0x1c14L)); 
		position.put(E2.value, Long.valueOf(0x1c141cL)); 
		position.put(E3.value, Long.valueOf(0x1c141c00L)); 
		position.put(E4.value, Long.valueOf(0x1c141c0000L)); 
		position.put(E5.value, Long.valueOf(0x1c141c000000L)); 
		position.put(E6.value, Long.valueOf(0x1c141c00000000L)); 
		position.put(E7.value, Long.valueOf(0x1c141c0000000000L)); 
		position.put(E8.value, Long.valueOf(0x141c000000000000L)); 
		
		position.put(F1.value, Long.valueOf(0xe0aL)); 
		position.put(F2.value, Long.valueOf(0xe0a0eL)); 
		position.put(F3.value, Long.valueOf(0xe0a0e00L)); 
		position.put(F4.value, Long.valueOf(0xe0a0e0000L)); 
		position.put(F5.value, Long.valueOf(0xe0a0e000000L)); 
		position.put(F6.value, Long.valueOf(0xe0a0e00000000L)); 
		position.put(F7.value, Long.valueOf(0xe0a0e0000000000L)); 
		position.put(F8.value, Long.valueOf(0xa0e000000000000L)); 
		
		position.put(G1.value, Long.valueOf(0x705L)); 
		position.put(G2.value, Long.valueOf(0x70507L)); 
		position.put(G3.value, Long.valueOf(0x7050700L)); 
		position.put(G4.value, Long.valueOf(0x705070000L)); 
		position.put(G5.value, Long.valueOf(0x70507000000L)); 
		position.put(G6.value, Long.valueOf(0x7050700000000L)); 
		position.put(G7.value, Long.valueOf(0x705070000000000L)); 
		position.put(G8.value, Long.valueOf(0x507000000000000L)); 
		
		position.put(H1.value, Long.valueOf(0x302L)); 
		position.put(H2.value, Long.valueOf(0x30203L)); 
		position.put(H3.value, Long.valueOf(0x3020300L)); 
		position.put(H4.value, Long.valueOf(0x302030000L)); 
		position.put(H5.value, Long.valueOf(0x30203000000L)); 
		position.put(H6.value, Long.valueOf(0x3020300000000L)); 
		position.put(H7.value, Long.valueOf(0x302030000000000L)); 
		position.put(H8.value, Long.valueOf(0x203000000000000L)); 
		
	}
	
	private Square square;
	
	private byte color;
	
	public static King at(final Square square) {
		final King king = new King();
		king.square = square;
		
		return king;
	}
	
	public King of(final byte color) {
		this.color = color;
		return this;
	}

	public static long attack(final long[][] bitboard, final byte color) {
		final long kings = bitboard[KING][color];
		
		final long[] squares = Bitboard.pieces(kings);
				
		long table = 0L;
		
		for(int i = 0; i < squares.length; i++) {
			table |= position.get(squares[i]);
		}
		
		return table;		
	}
	
	public static long attack(final long king) {				
		return position.get(king);
	}
	
	public static long attack(final long king, final long allPieces, final long moveSpecial, final byte color) {				
		return position.get(king);
	}

	public static long move(final long allPieces, final long myPieces, long moveSpecial, final long squaresAttackedByEnemy, final int index, byte color) {
		final long table = ((position.get(Bitboard.board[index]) & ~myPieces) & ~squaresAttackedByEnemy);
		
		return table | castling(moveSpecial, color, squaresAttackedByEnemy, myPieces);
	}
	
//	public static long move(long[][] bitboard, final Square square, byte color, final long squaresAttackedByEnemy) {
//		final long index = square.value;
//		final long myPieces = Bitboard.allMyPieces(bitboard, color);
//		final long table = ((position.get(index) & ~myPieces) & ~squaresAttackedByEnemy);
//		
//		return table | castling(bitboard[SPECIAL][color], color, squaresAttackedByEnemy, myPieces);
//	}
	
	private static long castling(final long castlingMoved, byte color, final long squaresAttackedByEnemy, final long myPieces) {
		long result = 0L;
		
		// rei em check ou rei mexeu
		if(((values[color][KING] & squaresAttackedByEnemy) > 0) || ((values[color][KING] & castlingMoved) == 0)) { 
			return 0; // sem roque
		}
		
		// bispo ou cavalo do rei nao estao no meio
		if((values[color][KING_BISHOP] & myPieces) == 0 && (values[color][KING_KNIGHT] & myPieces) == 0) { 
			// torre do rei nao mexeu
			if((values[color][KING_ROOK] & castlingMoved) > 0) { 
				// casa do bispo nao atacada
				if((values[color][KING_BISHOP] & squaresAttackedByEnemy) == 0) { 
					// casa do cavalo nao atacada
					if((values[color][KING_KNIGHT] & squaresAttackedByEnemy) == 0) {  
						result = values[color][KING_KNIGHT];
					}
				}
			}	
		}
		
		// rainha, bispo ou cavalo da rainha nao estao no meio
		if((values[color][QUEEN] & myPieces) == 0 && (values[color][QUEEN_BISHOP] & myPieces) == 0 && (values[color][QUEEN_KNIGHT] & myPieces) == 0) { 
			// torre da rainha nao mexeu
			if((values[color][QUEEN_ROOK] & castlingMoved) != 0) { 
				// casa do bispo nao atacada 
				if((values[color][QUEEN_BISHOP] & squaresAttackedByEnemy) == 0) { 
					// casa da rainha nao atacada
					if((values[color][QUEEN] & squaresAttackedByEnemy) == 0) {
						result |= values[color][QUEEN_BISHOP];
					}
				}
			}
		}
		
		return result;
	}
	
	
	public static long[][][] attack(final long[][][] chessboard, final long kings, final byte color) {
		final long[] squares = Bitboard.pieces(kings);
		
		for(int i = 0; i < squares.length; i++) {
			chessboard[Square.get(squares[i]).index][color][0] = position.get(squares[i]);
		}
		
		return chessboard;
	}
	
	@Override
	public byte getType() {
		return KING;
	}

	@Override
	public byte getColor() {
		return color;
	}

	@Override
	public long bit() {
		return square.value;
	}
	
	@Override
	public Square getSquare() {
		return square;
	}
}
