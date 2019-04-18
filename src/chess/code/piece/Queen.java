package chess.code.piece;

import chess.code.bitboard.Bitboard;
import chess.code.bitboard.Square;
import chess.code.magic.bitboard.MagicBitboard;

public class Queen implements Piece {
	
	private Square square;
	
	private byte color;
	
	private byte index;
	
	public static Queen at(final long value) {
		final Queen queen = new Queen();
		queen.setIndex((byte)Bitboard.indexBitOn(value)[0]);
		
		return queen;
	}
	
	public static long attack(final long queen, final long allPieces, long moveSpecial, final byte color) {
		final int square = Bitboard.indexBitOn(queen)[0];
		
		return (MagicBitboard.getQueenMoves(square, allPieces));			
	}
	
	public static long attack(final long queen, final long allPieces) {
		final int square = Bitboard.indexBitOn(queen)[0];
		
		return (MagicBitboard.getQueenMoves(square, allPieces));			
	}
	
	public static Queen at(final Square square) {
		final Queen queen = new Queen();
		queen.square = square;
		
		return queen;
	}
	
	public Queen of(final byte color) {
		this.color = color;
		return this;
	}
	
	public static long[] attack(final long[][] bitboard, final byte color, final long enemyKing) { 
		final long[] array = new long[3];
		
		final long queens = bitboard[Piece.QUEEN][color];
		final int[] squares = Bitboard.indexBitOn(queens);
		final long allPieces = Bitboard.allPieces(bitboard);
		
		for(int i = 0; i < squares.length; i++) {
			final long result = (MagicBitboard.getQueenMoves(squares[i], allPieces));	
			
			if((result & enemyKing) > 0) {
				array[1] |= result;
				array[2] |= squares[i];
			}
			
			array[0] |= result;
		}
		
		return array;
	}
	
	public static long attack(final long[][] bitboard, final byte color) {
		final long queens = bitboard[Piece.QUEEN][color];
		final int[] squares = Bitboard.indexBitOn(queens);
		long table = 0L;
		final long allPieces = Bitboard.allPieces(bitboard);
		
		for(int i = 0; i < squares.length; i++) {
			table |= (MagicBitboard.getQueenMoves(squares[i], allPieces));	
		}
		
		return table;		
	}
	
	public static long move(final long allPieces, final long myPieces, long moveSpecial, final long squaresAttackedByEnemy, final int index, byte color) {
		return MagicBitboard.getQueenMoves(index, allPieces) & ~myPieces;
	}
	
	public static long move(long[][] bitboard, final Square square, byte color) {
		final int index = square.index;
		final long allPieces = Bitboard.allPieces(bitboard);
		final long myPieces = Bitboard.allMyPieces(bitboard, color);
		
		return MagicBitboard.getQueenMoves(index, allPieces) & ~myPieces;
	}
	
	public static long[][][] attack(final long[][][] chessboard, final long[][] bitboard, final byte color) {
		final long queen = bitboard[Piece.QUEEN][color];
		final int[] squares = Bitboard.indexBitOn(queen);
		final long allPieces = Bitboard.allPieces(bitboard);
		final long myPieces = Bitboard.allMyPieces(bitboard, color);
		
		for(int i = 0; i < squares.length; i++) {
			chessboard[squares[i]][color][0] = MagicBitboard.getQueenMoves(squares[i], allPieces) & ~myPieces;
		}
		
		return chessboard;
	}
	
	@Override
	public byte getType() {
		return Piece.QUEEN;
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

	/**
	 * @return the index
	 */
	public byte getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(byte index) {
		this.index = index;
	}
}
