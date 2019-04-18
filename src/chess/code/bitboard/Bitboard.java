package chess.code.bitboard;

import static chess.code.bitboard.Rank.R1;
import static chess.code.bitboard.Rank.R2;
import static chess.code.bitboard.Rank.R4;
import static chess.code.bitboard.Rank.R5;
import static chess.code.bitboard.Rank.R7;
import static chess.code.bitboard.Rank.R8;
import static chess.code.bitboard.Square.A1;
import static chess.code.bitboard.Square.A8;
import static chess.code.bitboard.Square.C1;
import static chess.code.bitboard.Square.C8;
import static chess.code.bitboard.Square.D1;
import static chess.code.bitboard.Square.D8;
import static chess.code.bitboard.Square.E1;
import static chess.code.bitboard.Square.E8;
import static chess.code.bitboard.Square.F1;
import static chess.code.bitboard.Square.F8;
import static chess.code.bitboard.Square.G1;
import static chess.code.bitboard.Square.G8;
import static chess.code.bitboard.Square.H1;
import static chess.code.bitboard.Square.H8;
import static chess.code.piece.Piece.BISHOP;
import static chess.code.piece.Piece.EMTPY;
import static chess.code.piece.Piece.KING;
import static chess.code.piece.Piece.KNIGHT;
import static chess.code.piece.Piece.PAWN;
import static chess.code.piece.Piece.QUEEN;
import static chess.code.piece.Piece.ROOK;
import static chess.code.piece.Piece.SPECIAL;
import static chess.code.util.Color.BLACK;
import static chess.code.util.Color.WHITE;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

import chess.code.Move;
import chess.code.magic.bitboard.MagicBitboard;
import chess.code.notation.AlgebricNotation;
import chess.code.piece.Bishop;
import chess.code.piece.King;
import chess.code.piece.Knight;
import chess.code.piece.Pawn;
import chess.code.piece.Piece;
import chess.code.piece.Queen;
import chess.code.piece.Rook;
import chess.code.util.Color;
import chess.code.util.SixFunction;
import chess.code.util.TriFunction;

public class Bitboard {
	
	private static final long[] WHITE_PIECES = {0x8L, 0x10L, 0x81L, 0x24L, 0x42L, 0xFF00L, 0x20000000199L};
	private static final long[] BLACK_PIECES = {0x800000000000000L, 0x1000000000000000L, 0x8100000000000000L, 0x2400000000000000L, 0x4200000000000000L, 0xFF000000000000L, 0x9900020000000000L};
	
	public static final long[] board = Square.board();
	
	public static final Square[] squaresBoard = Square.squares();
	
	public static final String[] notation = Square.notation();
	
	public static final long[] enPassantRank;
	public static final long[] pawnInitialRank;
	
	public static final byte ATTACK = 0;
	public static final byte MOVE = 1;
	
	public static final byte SHORT_CASTLING = 0;
	public static final byte LONG_CASTLING = 1;
	
	public static final byte FROM = 0;
	public static final byte TO = 1;
	
	
	public static final Map<Byte, TriFunction<Long, Long, Long, Byte, Long>> attackPiece = new HashMap<>();		
	
	public static final Map<Byte, SixFunction<Long, Long, Long, Long, Integer, Byte, Long>> movePiece = new HashMap<>();		
	
	public static final Map<Byte, BinaryOperator<Long>> shift = new HashMap<>();
	
	public static final long[][] castling;	
	public static final long[][] castlingRight;	
	public static final byte[][][] rook;
	public static final long[] promotion;
	public static final byte[] king;

	static {
		enPassantRank = new long[2];
		enPassantRank[WHITE] = R5.value;
		enPassantRank[BLACK] = R4.value;
		
		pawnInitialRank = new long[2];
		pawnInitialRank[WHITE] = R2.value;
		pawnInitialRank[BLACK] = R7.value;
		
		promotion = new long[2];
		promotion[WHITE] = R8.value;
		promotion[BLACK] = R1.value;
		
		king = new byte[2];
		king[WHITE] = E1.id;
		king[BLACK] = E8.id;
		
		castling = new long[2][2];
		castling[WHITE][SHORT_CASTLING] = E1.value | G1.value;
		castling[WHITE][LONG_CASTLING] = E1.value | C1.value;
		castling[BLACK][SHORT_CASTLING] = E8.value | G8.value;
		castling[BLACK][LONG_CASTLING] = E8.value | C8.value;;

		castlingRight = new long[2][2];
		castlingRight[WHITE][SHORT_CASTLING] = E1.value | H1.value;
		castlingRight[WHITE][LONG_CASTLING] = E1.value | A1.value;
		castlingRight[BLACK][SHORT_CASTLING] = E8.value | H8.value;
		castlingRight[BLACK][LONG_CASTLING] = E8.value | A8.value;;
		
		
		rook = new byte[2][2][2];
		rook[KING][WHITE][FROM] = H1.id;
		rook[KING][WHITE][TO] = F1.id;
		rook[KING][BLACK][FROM] = H8.id;
		rook[KING][BLACK][TO] = F8.id;
		rook[QUEEN][WHITE][FROM] = A1.id;
		rook[QUEEN][WHITE][TO] = D1.id;
		rook[QUEEN][BLACK][FROM] = A8.id;
		rook[QUEEN][BLACK][TO] = D8.id;

		
		shift.put(WHITE, (u, v) -> u << v);
		shift.put(BLACK, (u, v) -> u >>> v);
		
		attackPiece.put(KING,   King::attack);
		attackPiece.put(QUEEN,  Queen::attack);
		attackPiece.put(ROOK,   Rook::attack);
		attackPiece.put(BISHOP, Bishop::attack);
		attackPiece.put(KNIGHT, Knight::attack);
		attackPiece.put(PAWN,   Pawn::attack);
		
		movePiece.put(KING,   King::move);
		movePiece.put(QUEEN,  Queen::move);
		movePiece.put(ROOK,   Rook::move);
		movePiece.put(BISHOP, Bishop::move);
		movePiece.put(KNIGHT, Knight::move);
		movePiece.put(PAWN,   Pawn::move);

		
	}
	
	public static final long[][] columns = {{File.A.value, File.H.value}, {File.H.value, File.A.value}};
	
	public static final long[][] distance = Square.distance();
	
	public static long[][] generateBitboard() {
		final long[][] bitboard = new long[7][2];
		
		for(int i = 0; i < bitboard.length; i++) {
			bitboard[i][WHITE] = WHITE_PIECES[i];
			bitboard[i][BLACK] = BLACK_PIECES[i];
		}
		
		return bitboard;
	}
	
	public static long[][] create(final byte color, final Piece... pieces) {
		final long[][] bitboard = new long[7][2];
		
		for(int i = 0; i < bitboard.length; i++) {
			bitboard[i][WHITE] = 0;
			bitboard[i][BLACK] = 0;
		}
		
		for(Piece piece : pieces) {
			bitboard[piece.getType()][piece.getColor()] |= piece.bit(); 
		}
		
		Color.colorMoving(bitboard, color);
		
		return bitboard;
	}
	
	public static long[][] add(Piece... pieces) {
		final long[][] bitboard = generateBitboard();
		
		for(Piece piece : pieces) {
			bitboard[piece.getType()][piece.getColor()] |= piece.bit(); 
		}
		
		return bitboard;
	}
	
	public static long[][] add(final long[][] bitboard, Piece... pieces) {
		for(Piece piece : pieces) {
			bitboard[piece.getType()][piece.getColor()] |= piece.bit(); 
		}
		
		return bitboard;
	}
	
	public static long[][] remove(final long[][] bitboard, Piece... pieces) {
		for(Piece piece : pieces) {
			bitboard[piece.getType()][piece.getColor()] = clearBit(piece.getSquare(), bitboard[piece.getType()][piece.getColor()]);
			
			if(piece instanceof Rook || piece instanceof King) {
				bitboard[MOVE][piece.getColor()] = clearBit(piece.getSquare().index, bitboard[MOVE][piece.getColor()]);
			}
		}
		
		return bitboard;
	}
	
	public static long[][] remove(Piece... pieces) {
		final long[][] bitboard = generateBitboard();
		
		for(Piece piece : pieces) {
			bitboard[piece.getType()][piece.getColor()] = clearBit(piece.getSquare(), bitboard[piece.getType()][piece.getColor()]);
			
			if(piece instanceof Rook) {
				bitboard[MOVE][piece.getColor()] = clearBit(piece.getSquare().index, bitboard[MOVE][piece.getColor()]);
			}
		}
		
		return bitboard;
	}
	
	public static long allPieces(long[][] bitboard) {
        long result = 0;
        
        for (int i = KING; i <= PAWN; i++) {
            result |= bitboard[i][WHITE] | bitboard[i][BLACK];
        }
        
        return result;
    }
	
	public static long allMyPieces(final long[][] bitboard, final int color) {
        long result = 0;
        
        for (int i = KING; i <= PAWN; i++) {
            result |= bitboard[i][color];
        }
        
        return result;
    }
	
	public static long allEnemyPieces(final long[][] bitboard, final byte color) {
        long result = 0;
        
        int index = enemy(color);
        
        for (int i = KING; i <= PAWN; i++) {
            result |= bitboard[i][index];
        }
        
        return result;
    }
	
	public static byte pieceType(long[][] bitboard, byte index, final byte color) {
        for (byte piece = KING; piece <= PAWN; piece++) {
            if(onBit(index, bitboard[piece][color])) {
                return piece;
            }
        }
        return EMTPY;
    }
	
	public static int[] indexBitOn(long value) {
		int[] setBits = new int[Long.bitCount(value)];

		int counter = 0;
		while (value != 0) {
			setBits[counter++] = Long.numberOfTrailingZeros(value);
			value &= value - 1;
		}

		return setBits;
	}
	
	public static byte[] bitOn(long value) {
		byte[] setBits = new byte[Long.bitCount(value)];

		int counter = 0;
		while (value != 0) {
			setBits[counter++] = (byte)Long.numberOfTrailingZeros(value);
			value &= value - 1;
		}

		return setBits;
	}
	
	public static byte indexBit(long value) {
		byte bit = 0;

		while (value != 0) {
			return (byte)Long.numberOfTrailingZeros(value);
		}
		
		return bit;
	}
	
	public static long[] pieces(long value) {
		long[] bits = new long[Long.bitCount(value)];
		
		int counter = 0;
		while(value != 0) {
			bits[counter++] = Long.highestOneBit(value);
			
			int index = 63 - Long.numberOfLeadingZeros(value);
			
			value = clearBit(index, value);
		}
		
		return bits;
	}
	
	public static long setBit(final byte board, final long table) {
		final BitSet set = BitSet.valueOf(new long[] { table });
		set.set(board);
		
		return set.toLongArray()[0];
	}
	
	public static long clearBit(final Square board, final long table) {
		return table & ~(1L<<board.index);
	}
	
	public static long clearBit(final int index, final long table) {
		return table & ~(1L<<index);
	}
	
	public static boolean onBit(final byte board, final long table) {
		final BitSet set = BitSet.valueOf(new long[] { table });
		
		return set.get(board);
	}
	
	public static Move generateMoves(final long[][] bitboard, final byte color) { 
		final long[][][] moveAttack = generateMoveAttack(bitboard);
		
		final List<Long> moves = generateMoves(bitboard, color, moveAttack);
		final List<String> notation = AlgebricNotation.notation(bitboard, moves, color, moveAttack);
		
		return Move.of(color).moves(moves).notation(notation);
	}
	
	public static List<Long> generateMoves(final long[][] bitboard, final byte color, final long[][][] moveAttack) {
		final List<Long> moves = new ArrayList<>();
		final byte kingIndex = indexBit(bitboard[KING][color]);
		
		final byte enemyColor = enemy(color);
		
		// retorna todas as casas que atacam o rei
		final long allEnemyPositionAttackMyKing = moveAttack[enemyColor][ATTACK][kingIndex];
				
		// numero de pecas inimigas que atacam meu rei
		final int numberOfAttackers = Long.bitCount(allEnemyPositionAttackMyKing); 
		
		if(numberOfAttackers == 2) {
			// check duplo. Somente movimentando o rei.
			final long validMoves = moveAttack[color][MOVE][kingIndex]; 
			
			if(validMoves == 0) {
				return moves; // checkmate 
			}
			
			moves.addAll(Move.createMoves(bitboard[KING][color], pieces(validMoves)));
			
			return moves;
		} 
		else if(numberOfAttackers == 1) {
			//*************************************Rei Move*********************************
			final long validMovesKing = moveAttack[color][MOVE][kingIndex]; 
						
			if(validMovesKing > 0) {
				moves.addAll(Move.createMoves(bitboard[KING][color], pieces(validMovesKing)));
			}
			//*************************************Capturas*********************************
			final byte enemySquare = indexBit(allEnemyPositionAttackMyKing); 
			
			// retorna todas as casas que atacam a casa do inimigo 
			final long validMoves = moveAttack[color][ATTACK][enemySquare]; 
			
			if(validMoves > 0) {
				moves.addAll(Move.createMoves(allEnemyPositionAttackMyKing, pieces(validMoves)));
			}
			//*************************************Coloca Pecas no meio*********************************
			// Se for cavalo, nao em como colocar pecas na frente
			if(onBit(enemySquare, bitboard[KNIGHT][enemyColor])) {
				if(moves.isEmpty()) {
					return moves; // checkmate
				}
			} else {
				final long squaresBetween = Bitboard.distance[kingIndex][enemySquare];
				
				final byte[] squareBetweenIndex = bitOn(squaresBetween);
				
				for(byte index = 0; index < squareBetweenIndex.length; index++) {
					final long interpose = findSquareInterpose(moveAttack, squareBetweenIndex[index], color);
					
					if(interpose > 0) {
						moves.addAll(Move.createMoves(board[squareBetweenIndex[index]], pieces(interpose)));
					}
				}
			}
			return moves;
		}
		
		// retira movimentos que tornam o rei em check.
		// Procura pecas minhas presas atacadas por torres, bispos e rainha inimiga e nao permite movimentos deles.
		final long allPieces = Bitboard.allPieces(bitboard);
		final long myPieces = Bitboard.allMyPieces(bitboard, color);
		final long rookBishopQueenEnemy = bitboard[BISHOP][enemyColor] | bitboard[ROOK][enemyColor] | bitboard[QUEEN][enemyColor];
		
		// coloca um rainha no lugar do rei e acha minhas pecas que nao podem se mover
		final int[] pinnedPieces = indexBitOn(MagicBitboard.getQueenMoves(kingIndex, allPieces) & myPieces);

		for(int i = 0; i < pinnedPieces.length; i++) {
			// pecas inimigas prendendo minhas pecas de movimentarem
			final int[] indexOfEnemyAttackerPinned = indexBitOn(moveAttack[enemyColor][ATTACK][pinnedPieces[i]]);
			final long pinnedPiece = board[pinnedPieces[i]];
			
			for(int j = 0; j < indexOfEnemyAttackerPinned.length; j++) {
				
				final long squaresBetweenKingAndEnemy = Bitboard.distance[kingIndex][indexOfEnemyAttackerPinned[j]];
				
				if((squaresBetweenKingAndEnemy & pinnedPiece) != 0) {
					
					final long enemySquare = board[indexOfEnemyAttackerPinned[j]];
					
					// rainha bispo ou torre atacando minha peca presa
					if((enemySquare & rookBishopQueenEnemy ) != 0) {
						
						// valida se a peca presa nao ataca o atacante
						if((moveAttack[color][MOVE][pinnedPieces[i]] & enemySquare) == 0) {
							
							// nao pode movimentar a peca pinnedPieces[i]
							// remove de move o movimento dessa peca
							moveAttack[color][MOVE][pinnedPieces[i]] = 0;
						}
						else {
							// retira movimentos da peca presa que tornam o rei em check
							moveAttack[color][MOVE][pinnedPieces[i]] = moveAttack[color][MOVE][pinnedPieces[i]] 
									& (squaresBetweenKingAndEnemy | enemySquare);;
						}
					}
					
				}
				
				
			}
		}
		
		for(byte i = 0; i < moveAttack[color][MOVE].length; i++) {
			final long validMoves = moveAttack[color][MOVE][i];
			
			if((board[i] & myPieces) != 0 && validMoves != 0) {
				moves.addAll(Move.createMoves(board[i], pieces(validMoves)));
			}
		}
		
		return moves;
	}
	
	
	
	public static long findSquareInterpose(final long[][][] moveAttack, final byte index, final byte color) {
		long move = 0;
		
		for(byte square = 0; square < moveAttack[color][MOVE].length; square++) {
			if(onBit(index, moveAttack[color][MOVE][square])) {
				move = setBit(square, move);
			}
		}
		
		return move;
	}
	
	public static long attackByColor(final long[][][] moveAttack, final byte color) {
		long table = 0;
		
		for(byte i = 0; i < moveAttack[color][ATTACK].length; i++) {
			if(moveAttack[color][ATTACK][i] != 0) {
				table = setBit(i, table);
			}
		}
		
		return table;
	}
	
	public static long[][][] generateMoveAttack(final long[][] bitboard) {
		long[][][] moveAttack = new long[2][2][64];
		
		final long allPieces = Bitboard.allPieces(bitboard);

		for(byte color = WHITE; color <= BLACK; color++) {
			for(int typeMove = 0; typeMove < 2; typeMove++) {
				Arrays.fill(moveAttack[color][typeMove], 0);
			}
		}
		
		for(byte color = WHITE; color <= BLACK; color++) {
			for(byte piece = KING; piece <= PAWN; piece++) {
				moveAttack = generateAttackSquare(moveAttack, bitboard[piece][color], allPieces, color, piece, bitboard[MOVE][color]);
			}			
		}
		
		for(byte color = WHITE; color <= BLACK; color++) {
			final long myPieces = Bitboard.allMyPieces(bitboard, color);
			 
			// casas atacadas pelo inimigo
			final long allSquaresAttackedByEnemy = attackByColor(moveAttack, enemy(color));
			
			for(byte piece = KING; piece <= PAWN; piece++) {
				moveAttack = generateMoveSquare(moveAttack, allPieces, myPieces, bitboard[SPECIAL][color], allSquaresAttackedByEnemy, color, piece, bitboard[piece][color]);
			}			
		}
		
		return moveAttack;
	}
	
	private static long[][][] generateMoveSquare(final long[][][] moveAttack, final long allPieces, final long myPieces, final long moveSpecial,
			long allSquaresAttackedByEnemy, byte color, final byte piece, final long pieces) {
		
		final int[] piecesIndex = indexBitOn(pieces);
		
		for(int i = 0; i < piecesIndex.length; i++) {	
			final long moves = movePiece.get(piece).apply(allPieces, myPieces, moveSpecial, allSquaresAttackedByEnemy, piecesIndex[i], color);  

			moveAttack[color][MOVE][piecesIndex[i]] = moves;					
		}
		
		return moveAttack;
	}

	private static long[][][] generateAttackSquare(final long[][][] moveAttack, final long pieces, final long allPieces, final byte color, final byte piece, final long moveSpecial) {
		final long[] piecesIndex = pieces(pieces);
		
		for(int i = 0; i < piecesIndex.length; i++) {	
			final long attack = attackPiece.get(piece).apply(piecesIndex[i], allPieces, moveSpecial, color);  

			final int[] index = indexBitOn(attack);

			for(int j = 0; j < index.length; j++) {					
				moveAttack[color][ATTACK][index[j]] |= piecesIndex[i];					
			}	
		}
		
		return moveAttack;
	} 
	
	public static byte enemy(final byte color) {
		return (byte)Math.abs(color-1);
	}
		
	public static long getHalfMoveSinceCapturePawnAdvance(final long[][] bitboard) {
		// Le a ultima posicao e pega a proxima
		final long result = ((bitboard[SPECIAL][Color.BLACK] & Rank.R2.value) | 
				(bitboard[SPECIAL][Color.BLACK] & Rank.R3.value)) >> 8;
			
		return result;
	}
	
	public static long[][] setHalfMoveZero(final long[][] bitboard) {
				
		// Le a ultima posicao e pega a proxima
		bitboard[SPECIAL][Color.BLACK] = (bitboard[SPECIAL][Color.BLACK] & Rank.R8.value) | 
										 (bitboard[SPECIAL][Color.BLACK] & Rank.R7.value) |
										 (bitboard[SPECIAL][Color.BLACK] & Rank.R6.value) |
										 (bitboard[SPECIAL][Color.BLACK] & Rank.R5.value) |
										 (bitboard[SPECIAL][Color.BLACK] & Rank.R4.value) |
										 (bitboard[SPECIAL][Color.BLACK] & Rank.R3.value & 0) |
										 (bitboard[SPECIAL][Color.BLACK] & Rank.R2.value & 0) |
										 (bitboard[SPECIAL][Color.BLACK] & Rank.R1.value);
					
		return bitboard;
	}
	
	public static long[][] setHalfMoveSinceCapturePawnAdvance(final long[][] bitboard) {
		
		final long nextNumber = getHalfMoveSinceCapturePawnAdvance(bitboard) + 1;
		
		// Le a ultima posicao e pega a proxima
		bitboard[SPECIAL][Color.BLACK] = (bitboard[SPECIAL][Color.BLACK] & Rank.R8.value) | 
										 (bitboard[SPECIAL][Color.BLACK] & Rank.R7.value) |
										 (bitboard[SPECIAL][Color.BLACK] & Rank.R6.value) |
										 (bitboard[SPECIAL][Color.BLACK] & Rank.R5.value) |
										 (bitboard[SPECIAL][Color.BLACK] & Rank.R4.value) |
										 (bitboard[SPECIAL][Color.BLACK] & Rank.R3.value & 0) |
										 (bitboard[SPECIAL][Color.BLACK] & Rank.R2.value & 0) |
										 (bitboard[SPECIAL][Color.BLACK] & Rank.R1.value);
		
		// Le a ultima posicao e pega a proxima
		bitboard[SPECIAL][Color.BLACK] = bitboard[SPECIAL][Color.BLACK] | (nextNumber << 8L);
					
		return bitboard;
	}
	
	public static long getNumberMove(final long[][] bitboard) {
		// Le a ultima posicao e pega a proxima
		final long result = ((bitboard[SPECIAL][Color.WHITE] & Rank.R2.value) | 
				(bitboard[SPECIAL][Color.WHITE] & Rank.R3.value)) >> 8;
			
		return result;
	}
	
	public static long[][] setNextNumberMove(final long[][] bitboard) {
		
		final long nextNumber = getNumberMove(bitboard) + 1;
		
		// Le a ultima posicao e pega a proxima
		bitboard[SPECIAL][Color.WHITE] = (bitboard[SPECIAL][Color.WHITE] & Rank.R8.value) | 
										 (bitboard[SPECIAL][Color.WHITE] & Rank.R7.value) |
										 (bitboard[SPECIAL][Color.WHITE] & Rank.R6.value) |
										 (bitboard[SPECIAL][Color.WHITE] & Rank.R5.value) |
										 (bitboard[SPECIAL][Color.WHITE] & Rank.R4.value) |
										 (bitboard[SPECIAL][Color.WHITE] & Rank.R3.value & 0) |
										 (bitboard[SPECIAL][Color.WHITE] & Rank.R2.value & 0) |
										 (bitboard[SPECIAL][Color.WHITE] & Rank.R1.value);
		
		// Le a ultima posicao e pega a proxima
		bitboard[SPECIAL][Color.WHITE] = bitboard[SPECIAL][Color.WHITE] | (nextNumber << 8L);
					
		return bitboard;
	}
	
	public static String show(final long[][] bitboard) {
		StringBuilder view = 
				new StringBuilder(String.format("%064d", new BigInteger(Long.toBinaryString(0))));
		
		for(byte color = WHITE; color <= BLACK; color++) {
	        for (int piece = KING; piece <= PAWN; piece++) {
		        final int[] arrayPieces = indexBitOn(bitboard[piece][color]);
	        	
		        final String typePiece = Piece.of[piece][color];
		        
		        for(int j = 0; j < arrayPieces.length; j++) {
		        	view.replace(arrayPieces[j], arrayPieces[j]+1, typePiece);
		        }
	        }
		}
		
		view = view.reverse();
		
		for(int i = 1; i < 127; i++) {
			if(i % 2 != 0) {
				if(i == 15 || i == 31 || i == 47 ||
						i == 63 || i == 79 || i == 95 ||
							i == 111 || i == 127) {
					view.insert(i, '\n');
					continue;
				}
				view.insert(i, ' ');
			}
		}
		
		System.out.println(view.toString() + "\n");
		
        return view.toString() + "\n";
	}
	
	public static String show(final Long table) {	
		final StringBuilder view = 
				new StringBuilder(String.format("%064d", new BigInteger(Long.toBinaryString(table))));
		
		for(int i = 1; i < 127; i++) {
			if(i % 2 != 0) {
				if(i == 15 || i == 31 || i == 47 ||
						i == 63 || i == 79 || i == 95 ||
							i == 111 || i == 127) {
					view.insert(i, '\n');
					continue;
				}
				view.insert(i, ' ');
			}
		}
		
		System.out.println(view.toString() + "\n");
		
        return view.toString() + "\n";
	}	
}
