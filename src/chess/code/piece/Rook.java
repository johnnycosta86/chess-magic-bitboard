package chess.code.piece;

import chess.code.bitboard.Bitboard;
import chess.code.bitboard.Square;
import chess.code.magic.bitboard.MagicBitboard;

public class Rook implements Piece {
	
	private Square square;
	
	private byte color;
	
	public static Rook at(final Square square) {
		final Rook rook = new Rook();
		rook.square = square;
		
		return rook;
	}
	
	public Rook of(final byte color) {
		this.color = color;
		return this;
	}
	
	public static long attack(final long rook, final long allPieces, final long color, long moveSpecial) {
		final int square = Bitboard.indexBitOn(rook)[0];
		
		return (MagicBitboard.getRookMoves(square, allPieces));			
	}
	
	public static long attack(final long rook, final long allPieces) {
		final int square = Bitboard.indexBitOn(rook)[0];
		
		return (MagicBitboard.getRookMoves(square, allPieces));			
	}
	
	public static long[] attack(final long[][] bitboard, final byte color, final long enemyKing) { 
		final long[] array = new long[3];
		
		final long rooks = bitboard[Piece.ROOK][color];
		final int[] squares = Bitboard.indexBitOn(rooks);
		final long allPieces = Bitboard.allPieces(bitboard);
		
		for(int i = 0; i < squares.length; i++) {
			final long result = (MagicBitboard.getRookMoves(squares[i], allPieces));	
			
			if((result & enemyKing) > 0) {
				array[1] |= result;
				array[2] |= squares[i];
			}
			
			array[0] |= result;
		}
		
		return array;
	}
	
	public static long attack(final long[][] bitboard, final byte color) {
		final long rooks = bitboard[Piece.ROOK][color];
		final int[] squares = Bitboard.indexBitOn(rooks);
		long table = 0L;
		final long allPieces = Bitboard.allPieces(bitboard);
				
		for(int i = 0; i < squares.length; i++) {
			table |= (MagicBitboard.getRookMoves(squares[i], allPieces));	
		}
		
		return table;
	}
	
	public static long move(final long allPieces, final long myPieces, long moveSpecial, final long squaresAttackedByEnemy, final int index, byte color) {
		return MagicBitboard.getRookMoves(index, allPieces) & ~myPieces;
	}
	
	public static long move(long[][] bitboard, final Square square, byte color) {
		final int index = square.index;
		final long allPieces = Bitboard.allPieces(bitboard);
		final long myPieces = Bitboard.allMyPieces(bitboard, color);
		
		return MagicBitboard.getRookMoves(index, allPieces) & ~myPieces;
	}
	
	public static long[][][] attack(final long[][][] chessboard, final long[][] bitboard, final byte color) {
		final long rooks = bitboard[Piece.ROOK][color];
		final int[] squares = Bitboard.indexBitOn(rooks);
		final long allPieces = Bitboard.allPieces(bitboard);
		final long myPieces = Bitboard.allMyPieces(bitboard, color);
		
		for(int i = 0; i < squares.length; i++) {
			chessboard[squares[i]][color][0] = MagicBitboard.getRookMoves(squares[i], allPieces) & ~myPieces;
		}
		
		return chessboard;
	}
	
	@Override
	public byte getType() {
		return Piece.ROOK;
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
