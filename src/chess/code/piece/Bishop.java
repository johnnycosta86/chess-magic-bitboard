package chess.code.piece;

import chess.code.bitboard.Bitboard;
import chess.code.bitboard.Square;
import chess.code.magic.bitboard.MagicBitboard;

public class Bishop implements Piece {
	
	private Square square;
	
	private byte color;
	
	public static Bishop at(final Square square) {
		final Bishop bishop = new Bishop();
		bishop.square = square;
		
		return bishop;
	}
	
	public Bishop of(final byte color) {
		this.color = color;
		return this;
	}
	
	public static long move(final long allPieces, final long myPieces, long moveSpecial, final long squaresAttackedByEnemy, final int index, byte color) {
		return MagicBitboard.getBishopMoves(index, allPieces) & ~myPieces;
	}
	
	public static long[] attack(final long[][] bitboard, final byte color, final long enemy) { 
		final long[] array = new long[3];
		
		final long bishops = bitboard[Piece.BISHOP][color]; 
		final int[] squares = Bitboard.indexBitOn(bishops);
		final long allPieces = Bitboard.allPieces(bitboard);
		
		for(int i = 0; i < squares.length; i++) {
			final long result = (MagicBitboard.getBishopMoves(squares[i], allPieces));	
			
			if((result & enemy) > 0) {
				array[1] |= result;
				array[2] |= squares[i];
			}
			
			array[0] |= result;
		}
		
		return array;
	}
	
	public static long attack(final long[][] bitboard, final byte color) {
		final long bishops = bitboard[Piece.BISHOP][color]; 
		final int[] squares = Bitboard.indexBitOn(bishops);
		long table = 0L;
		final long allPieces = Bitboard.allPieces(bitboard);
		
		for(int i = 0; i < squares.length; i++) {
			table |= (MagicBitboard.getBishopMoves(squares[i], allPieces));	
		}
		
		return table;
	}
	
	public static long attack(final long bishop, final long allPieces, long moveSpecial, final byte color) {
		final int square = Bitboard.indexBitOn(bishop)[0];
		
		return (MagicBitboard.getBishopMoves(square, allPieces));			
	}
	
	public static long attack(final long bishop, final long allPieces) {
		final int square = Bitboard.indexBitOn(bishop)[0];
		
		return (MagicBitboard.getBishopMoves(square, allPieces));			
	}
	
	public static long move(long[][] bitboard, final Square square, byte color) {
		final int index = square.index;
		final long allPieces = Bitboard.allPieces(bitboard);
		final long myPieces = Bitboard.allMyPieces(bitboard, color);
		
		return MagicBitboard.getBishopMoves(index, allPieces) & ~myPieces;
	}
	
	public static long[][][] attack(final long[][][] chessboard, final long[][] bitboard, final byte color) {
		final long bishops = bitboard[Piece.BISHOP][color];
		final int[] squares = Bitboard.indexBitOn(bishops);
		final long allPieces = Bitboard.allPieces(bitboard);
		final long myPieces = Bitboard.allMyPieces(bitboard, color);
		
		for(int i = 0; i < squares.length; i++) {
			chessboard[squares[i]][color][0] = MagicBitboard.getBishopMoves(squares[i], allPieces) & ~myPieces;
		}
		
		return chessboard;
	}

	@Override
	public byte getType() {
		return Piece.BISHOP;
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
