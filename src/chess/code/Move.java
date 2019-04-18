package chess.code;

import static chess.code.bitboard.Bitboard.FROM;
import static chess.code.bitboard.Bitboard.LONG_CASTLING;
import static chess.code.bitboard.Bitboard.SHORT_CASTLING;
import static chess.code.bitboard.Bitboard.TO;
import static chess.code.bitboard.Bitboard.castling;
import static chess.code.bitboard.Bitboard.enPassantRank;
import static chess.code.bitboard.Bitboard.king;
import static chess.code.bitboard.Bitboard.pawnInitialRank;
import static chess.code.bitboard.Bitboard.promotion;
import static chess.code.bitboard.Bitboard.rook;
import static chess.code.piece.Piece.KING;
import static chess.code.piece.Piece.PAWN;
import static chess.code.piece.Piece.QUEEN;
import static chess.code.piece.Piece.ROOK;
import static chess.code.piece.Piece.SPECIAL;

import java.util.ArrayList;
import java.util.List;

import chess.code.bitboard.Bitboard;
import chess.code.bitboard.Rank;
import chess.code.bitboard.Square;
import chess.code.piece.Pawn;
import chess.code.piece.Piece;
import chess.code.util.Color;

public class Move {
	private byte color;
	private List<Long> moves;
	private List<String> notation;
	
	public static Move of(final byte color) {
	    final Move move = new Move();                             
	    move.color = color;
		return move;
	}
	
	public Move moves(final List<Long> moves) {
		this.moves = moves;
		return this;
	}
	
	public Move notation(final List<String> notation) {
		this.notation = notation;
		return this;
	}
	
	public byte getColor() {
		return color;
	}

	public List<Long> getMoves() {
		return moves;
	}
	
	public List<String> getNotation() {
		return notation;
	}
	
	public static long[][] movePiece(final long[][] bitboard, final long move, final byte color) {
		final long enemyPieces = Bitboard.allEnemyPieces(bitboard, color);
		final long myPieces = Bitboard.allMyPieces(bitboard, color);
		final byte enemyColor = Bitboard.enemy(color);
		final byte[] squares = Bitboard.bitOn(move);
		final byte from = Bitboard.indexBit(move & myPieces);
		final byte to = (squares[0] == from ? squares[1] : squares[0]);
		final byte pieceFrom = Bitboard.pieceType(bitboard, from, color);
		boolean halfmoves = false;
		
		if ((move & castling[color][SHORT_CASTLING]) == castling[color][SHORT_CASTLING]) {
			bitboard[KING][color] = Bitboard.setBit(to, bitboard[KING][color]);
			bitboard[ROOK][color] = Bitboard.clearBit(rook[KING][color][FROM], bitboard[ROOK][color]);
			bitboard[ROOK][color] = Bitboard.setBit(rook[KING][color][TO], bitboard[ROOK][color]);
			
			bitboard[SPECIAL][color] = Bitboard.clearBit(from, bitboard[SPECIAL][color]);
			bitboard[SPECIAL][color] = Bitboard.clearBit(rook[KING][color][FROM], bitboard[SPECIAL][color]);
		} 
		else if ((move & castling[color][LONG_CASTLING]) == castling[color][LONG_CASTLING]) {
			bitboard[KING][color] = Bitboard.setBit(to, bitboard[KING][color]);
			bitboard[ROOK][color] = Bitboard.clearBit(rook[QUEEN][color][FROM], bitboard[ROOK][color]);
			bitboard[ROOK][color] = Bitboard.setBit(rook[QUEEN][color][TO], bitboard[ROOK][color]);
			
			bitboard[SPECIAL][color] = Bitboard.clearBit(from, bitboard[SPECIAL][color]);
			bitboard[SPECIAL][color] = Bitboard.clearBit(rook[QUEEN][color][FROM], bitboard[SPECIAL][color]);
		} 
		else if ((move & bitboard[PAWN][color]) > 0) {
			halfmoves = true;
			
			if ((move & promotion[color]) > 0) {
				// DEFAULT(41 = 1 = QUEEN), (42 = 2 = ROOK), (43 = 3 = BISHOP), (44 = 4 = KNIGHT),  
				final byte newPiece = (byte)(Bitboard.indexBit(bitboard[SPECIAL][color] & Rank.R6.value) % 8); 
				
				bitboard[pieceFrom][color] = Bitboard.clearBit(to, bitboard[pieceFrom][color]);
				bitboard[newPiece][color] = Bitboard.setBit(to, bitboard[newPiece][color]);
			} 
			else if ((bitboard[SPECIAL][color] & enPassantRank[color]) > 0) {
				final long enPassant = Pawn.enPassant(bitboard[SPECIAL][color], move & myPieces, color);
				
				if(enPassant > 0) {
					bitboard[PAWN][color] = Bitboard.setBit(to, bitboard[PAWN][color]);
					final byte capture = Bitboard.indexBit(bitboard[SPECIAL][color] & Bitboard.enPassantRank[color]);
					final byte pieceCapture = Bitboard.pieceType(bitboard, capture, enemyColor);
					
					bitboard[pieceCapture][enemyColor] = Bitboard.clearBit(capture, bitboard[pieceCapture][enemyColor]);
				}
			}
			else if((move & pawnInitialRank[color]) > 0 && (Math.abs(to-from) == 16)) {
				bitboard[PAWN][color] = Bitboard.setBit(to, bitboard[PAWN][color]);
				bitboard[SPECIAL][color] = Bitboard.setBit(to, bitboard[SPECIAL][color]);
			}
			else {
				bitboard[PAWN][color] = Bitboard.setBit(to, bitboard[PAWN][color]);
			}
		}
		else {
			bitboard[pieceFrom][color] = Bitboard.setBit(to, bitboard[pieceFrom][color]);
			
			if(pieceFrom == Piece.ROOK || pieceFrom == Piece.KING) {
				if(from == rook[KING][color][FROM] || from == rook[QUEEN][color][FROM] || from == king[color]) {
					bitboard[SPECIAL][color] = Bitboard.clearBit(from, bitboard[SPECIAL][color]);
				}
			}
		}

		if ((move & enemyPieces) > 0) {
			halfmoves = true;
			
			final byte enemyPiece = Bitboard.pieceType(bitboard, to, enemyColor);
			bitboard[enemyPiece][enemyColor] = Bitboard.clearBit(to, bitboard[enemyPiece][enemyColor]);
		}

		bitboard[pieceFrom][color] = Bitboard.clearBit(from, bitboard[pieceFrom][color]);
		
		final long enPassant = bitboard[Piece.SPECIAL][enemyColor] & Bitboard.enPassantRank[color];

		if(enPassant != 0) {
			bitboard[SPECIAL][enemyColor] = Bitboard.clearBit(Bitboard.indexBit(enPassant), bitboard[SPECIAL][enemyColor]);
		}
		
		if(color == Color.WHITE) {
			// Atualiza proxima jogada das pretas
			bitboard[SPECIAL][Color.WHITE] = Bitboard.clearBit(Square.D1.id, bitboard[SPECIAL][Color.WHITE]);
		}
		else {
			// atualiza o numero do movimento no bitboard
			Bitboard.setNextNumberMove(bitboard);
			
			// Atualiza que proxima jogadas das brancas
			bitboard[SPECIAL][Color.WHITE] = Bitboard.setBit(Square.D1.id, bitboard[SPECIAL][Color.WHITE]);
		}
		
		// houve captura ou movimento de peao, atualiza tabuleiro para zero
		if(halfmoves) {
			Bitboard.setHalfMoveZero(bitboard); 
		}
		else {
			Bitboard.setHalfMoveSinceCapturePawnAdvance(bitboard); 
		}
		
		return bitboard;
	}

	public static List<Long> createMoves(final long from, final long moves[]) {
		final List<Long> listMoves = new ArrayList<>();
		
		for(int i = 0; i < moves.length; i++) {
			listMoves.add(from | moves[i]);
		}
		
		return listMoves;
	}
}
