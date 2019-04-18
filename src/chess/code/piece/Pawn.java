package chess.code.piece;

import static chess.code.bitboard.Bitboard.columns;
import static chess.code.bitboard.Bitboard.enemy;
import static chess.code.bitboard.Bitboard.shift;

import chess.code.bitboard.Bitboard;
import chess.code.bitboard.File;
import chess.code.bitboard.Square;

public class Pawn implements Piece {
	
	private Square square;
	
	private byte color;
	
	public static Pawn at(final Square square) {
		final Pawn pawn = new Pawn();
		pawn.square = square;
		
		return pawn;
	}
	
	public Pawn of(final byte color) {
		this.color = color;
		return this;
	}
	
	public static long attack(final long square, final long allPieces, long moveSpecial, final byte color) {		// this
		return (shift.get(color).apply(square, 7L) & ~columns[color][0]) | (shift.get(color).apply(square, 9L) & ~columns[color][1]) | enPassant(moveSpecial, square, color);
	}
	
	public static long attack(final long square, final byte color) {
		return (shift.get(color).apply(square, 7L) & ~columns[color][0]) | (shift.get(color).apply(square, 9L) & ~columns[color][1]);
	} 

	public static long move(final long allPieces, final long myPieces, long moveSpecial, final long squaresAttackedByEnemy, final int index, byte color) {
		final long enemyPieces = allPieces & ~myPieces;
		final long emptySquares = ~allPieces;
		final long position = Bitboard.board[index];
		final long squaresAttacked = attack(position, color) & enemyPieces;
		final long enPassant = enPassant(moveSpecial, position, color);
		final long firstLine = shift.get(color).apply(position, 8L) & emptySquares;
		
		if((position & Bitboard.pawnInitialRank[color]) > 0) {
			final long secondLine = shift.get(color).apply(position, 16L) & emptySquares;
			
			if(firstLine > 0 && secondLine > 0) {
				return firstLine | secondLine | squaresAttacked;
			}
			
			return firstLine | squaresAttacked;
		}
		
		return firstLine | squaresAttacked | enPassant;
	}
	
	public static long enPassant(final long specialMoves, final long position, final byte color) {
		
		final long enPassant = specialMoves & Bitboard.enPassantRank[color];
		
		if(enPassant > 0) {
			final long enPassantSidesSquares = (shift.get(color).apply(enPassant, 1L) & ~File.H.value) | 
									   (shift.get(enemy(color)).apply(enPassant, 1L) & ~File.A.value);
			
			if((enPassantSidesSquares & position) > 0) { 
				return shift.get(color).apply((enPassant), 8L);
			}
		}
		
		return 0L;
	}
	
	@Override
	public Square getSquare() {
		return square;
	}
	
	@Override
	public byte getType() {
		return Piece.PAWN;
	}

	@Override
	public byte getColor() {
		return color;
	}
	
	public long bit() {
		return square.value;
	}
}
