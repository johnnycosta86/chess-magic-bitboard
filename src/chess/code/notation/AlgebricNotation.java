package chess.code.notation;

import static chess.code.bitboard.Bitboard.LONG_CASTLING;
import static chess.code.bitboard.Bitboard.SHORT_CASTLING;
import static chess.code.bitboard.Bitboard.castling;
import static chess.code.bitboard.Bitboard.enPassantRank;
import static chess.code.bitboard.Bitboard.indexBit;
import static chess.code.bitboard.Bitboard.notation;
import static chess.code.bitboard.Bitboard.promotion;
import static chess.code.bitboard.Bitboard.squaresBoard;
import static chess.code.piece.Piece.PAWN;
import static chess.code.piece.Piece.SPECIAL;

import java.util.ArrayList;
import java.util.List;

import chess.code.bitboard.Bitboard;
import chess.code.bitboard.Rank;
import chess.code.bitboard.Square;
import chess.code.piece.Pawn;
import chess.code.piece.Piece;

public class AlgebricNotation {
	
	public static List<String> notation(final long[][] bitboard, final List<Long> moves, final byte color, final long[][][] moveAttack) {
		final List<String> movesInAlgebraicNotation = new ArrayList<>();
		
		for(final Long move : moves) {
			movesInAlgebraicNotation.add(notation(bitboard, move, color, moveAttack));
		}
		
		return movesInAlgebraicNotation;
	}
	
	public static String notation(final long[][] bitboard, final long move, final byte color, final long[][][] moveAttack) {
		final long enemyPieces = Bitboard.allEnemyPieces(bitboard, color);
		final long myPieces = Bitboard.allMyPieces(bitboard, color);
		final byte[] squares = Bitboard.bitOn(move);
		final byte from = Bitboard.indexBit(move & myPieces);
		final byte to = (squares[0] == from ? squares[1] : squares[0]);
		final byte pieceFrom = Bitboard.pieceType(bitboard, from, color);
		
		if ((move & castling[color][SHORT_CASTLING]) == castling[color][SHORT_CASTLING]) {
			return "0-0";
		} 
		else if ((move & castling[color][LONG_CASTLING]) == castling[color][LONG_CASTLING]) {
			return "0-0-0";
		} 
		else if ((move & bitboard[PAWN][color]) != 0) {
			
			final long enPassant = Pawn.enPassant(bitboard[SPECIAL][color], move & myPieces, color);
			final long enPassantPossible = bitboard[SPECIAL][color] & enPassantRank[color];
			final long enPassantTo = enPassant & Bitboard.board[to];
			
			if ((move & promotion[color]) != 0) {
				// DEFAULT(41 = 1 = QUEEN), (42 = 2 = ROOK), (43 = 3 = BISHOP), (44 = 4 = KNIGHT),  
				final byte newPiece = (byte)(Bitboard.indexBit(bitboard[SPECIAL][color] & Rank.R6.value) % 8); 
				
				if ((move & enemyPieces) != 0) {
					return notation[from].substring(0, 1) + "x" + notation[to] + "=" + Piece.of[newPiece][color];
				}
				
				return notation[to] + "=" + Piece.of[newPiece][color];
			} 
			else if (enPassantPossible > 0 && enPassant > 0 && enPassantTo > 0) {
				return notation[from].substring(0, 1) + "x" + notation[to] + " e.p.";
			}
			else if ((move & enemyPieces) != 0) {
				return notation[from].substring(0, 1) + "x" + notation[to];
			}
			else {
				return notation[to];
			}
		}
		else {
			final long captureEnemyIndex = move & enemyPieces;
			
			if (captureEnemyIndex != 0) {
				
				// minhas pecas que atacam o inimigo
				final long attack = moveAttack[color][Bitboard.ATTACK][indexBit(captureEnemyIndex)];
				
				// Se existem mais de um tipo de peca minha atacando a peca inimiga
				if(Long.bitCount(attack & bitboard[pieceFrom][color]) > 1) {
					return Piece.of[pieceFrom][color] + notation[from] + "x" + notation[to];
				}
				
				return Piece.of[pieceFrom][color] + "x" + notation[to];
			}
			else {
				// minhas pecas que atacam a casa de destino
				final long attack = moveAttack[color][Bitboard.ATTACK][to];
				
				final long manyTypePieceAttacking = attack & bitboard[pieceFrom][color];
				
				// Se existem mais de uma peca minha atacando a casa destino
				if(Long.bitCount(manyTypePieceAttacking) > 1) {
					
//					se tem duas ou mais pecas:
//						-	em colunas diferentes(da linha da peca inicial) e nao tem a mesma peca em outras linhas(da coluna da peca inicial)  
//								- insere a coluna depois da peca - NdF3 - (Nd2 e Ng1 para Nf3)
//								
//						-	em colunas iguais mas em linhas diferentes..                        
//								- insere a linha depois da peca - N5f3 - (Ng1 e Ng5 para Nf3)
//								
//						-	em colunas na mesma coluna e linha 
					
					final Square fromSquare = squaresBoard[from];
					
					final long fromFile = fromSquare.getFile().value;
					final long fromRank = fromSquare.getRank().value;
					
					// em colunas diferentes(da linha da peca inicial) e nao tem a mesma peca em outras linhas(da coluna da peca inicial)  
					if(Long.bitCount(manyTypePieceAttacking & fromRank) > 0 && Long.bitCount(manyTypePieceAttacking & fromFile) == 1) {
						return Piece.of[pieceFrom][color] + notation[from].substring(0, 1) + notation[to];
					}
					else if(Long.bitCount(manyTypePieceAttacking & fromFile) >= 2 && Long.bitCount(manyTypePieceAttacking) > 2) {
						return Piece.of[pieceFrom][color] + notation[from] + notation[to];
					}
					else {
						return Piece.of[pieceFrom][color] + notation[from].substring(1) + notation[to];
					}

					
				}
				
				return Piece.of[pieceFrom][color] + notation[to];
			}
		}		
	}
	
	
}
