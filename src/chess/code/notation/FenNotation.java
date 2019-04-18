package chess.code.notation;

import static chess.code.bitboard.Bitboard.LONG_CASTLING;
import static chess.code.bitboard.Bitboard.SHORT_CASTLING;
import static chess.code.bitboard.Bitboard.shift;
import static chess.code.piece.Piece.BISHOP;
import static chess.code.piece.Piece.KING;
import static chess.code.piece.Piece.KNIGHT;
import static chess.code.piece.Piece.PAWN;
import static chess.code.piece.Piece.QUEEN;
import static chess.code.piece.Piece.ROOK;
import static chess.code.piece.Piece.SPECIAL;
import static chess.code.util.Color.BLACK;
import static chess.code.util.Color.WHITE;

import java.math.BigInteger;
import java.util.Optional;
import java.util.stream.Stream;

import chess.code.bitboard.Bitboard;
import chess.code.bitboard.Square;
import chess.code.piece.Piece;
import chess.code.util.Color;

// rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
public class FenNotation {
	
	// Gera um tabuleiro a partir de uma Notacao Fen
	public static long[][] notation(final String fenNotationString) {
		final String[] array = fenNotationString.split(" ");
		final byte color = Color.get(array[1]);
		
		long[][] bitboard = new long[7][2];
		
		for(int i = 0; i < bitboard.length; i++) {
			bitboard[i][WHITE] = 0;
			bitboard[i][BLACK] = 0;
		}
	
		bitboard = Color.colorMoving(bitboard, color);
		bitboard = pieces(bitboard, array[0]);
		bitboard = castling(bitboard, array[2]);
		bitboard = enPassant(bitboard, array[3], color);
		bitboard = halfmove(bitboard, array[4]);
		bitboard = fullmove(bitboard, array[5]);
		bitboard = promotion(bitboard);
		
		return bitboard;
	}

	// DEFAULT(41 = 1 = QUEEN), (42 = 2 = ROOK), (43 = 3 = BISHOP), (44 = 4 = KNIGHT),  
	private static long[][] promotion(final long[][] bitboard) {
		bitboard[SPECIAL][Color.WHITE] = Bitboard.setBit(Square.G6.id, bitboard[SPECIAL][Color.WHITE]);
		bitboard[SPECIAL][Color.BLACK] = Bitboard.setBit(Square.G6.id, bitboard[SPECIAL][Color.BLACK]);
		
		return bitboard;
	}
	
	private static long[][] halfmove(final long[][] bitboard, final String halfmoveString) {
		final long halfmove = Long.valueOf(halfmoveString) << 8;
		bitboard[SPECIAL][Color.BLACK] |= halfmove;
		
		return bitboard;
	}
	
	private static long[][] fullmove(final long[][] bitboard, final String fullmoveString) {
		final long fullmove = Long.valueOf(fullmoveString) << 8;
		bitboard[SPECIAL][Color.WHITE] |= fullmove;
		
		return bitboard;
	}
	
	private static long[][] enPassant(final long[][] bitboard, final String enPassantSquare, final byte color) {
		
		if(enPassantSquare.isEmpty() || enPassantSquare.equals("-")) {
			return bitboard;
		}
		
		final Optional<Square> enPassant = Stream.of(Bitboard.squaresBoard)
				.filter(square -> square.name.equals(enPassantSquare)).findFirst();
		
		if(enPassant.isPresent()) {
			final long index = enPassant.get().value;
			
			bitboard[SPECIAL][color] |= shift.get(color).apply(index, 8L);
		}
		
		return bitboard;
	}
	
	private static long[][] pieces(final long[][] bitboard, final String pieces) {
		
		int positionCount = 63;
		for (int i = 0; i < pieces.length(); i++) {

			final char character = pieces.charAt(i);
			switch (character) {
				case '/':
					continue;
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
					positionCount -= Integer.parseInt("" + character);
					break;
				case 'P':
					bitboard[PAWN][WHITE] |= Bitboard.board[positionCount--];
					break;
				case 'N':
					bitboard[KNIGHT][WHITE] |= Bitboard.board[positionCount--];
					break;
				case 'B':
					bitboard[BISHOP][WHITE] |= Bitboard.board[positionCount--];
					break;
				case 'R':
					bitboard[ROOK][WHITE] |= Bitboard.board[positionCount--];
					break;
				case 'Q':
					bitboard[QUEEN][WHITE] |= Bitboard.board[positionCount--];
					break;
				case 'K':
					bitboard[KING][WHITE] |= Bitboard.board[positionCount--];
					break;
				case 'p':
					bitboard[PAWN][BLACK] |= Bitboard.board[positionCount--];
					break;
				case 'n':
					bitboard[KNIGHT][BLACK] |= Bitboard.board[positionCount--];
					break;
				case 'b':
					bitboard[BISHOP][BLACK] |= Bitboard.board[positionCount--];
					break;
				case 'r':
					bitboard[ROOK][BLACK] |= Bitboard.board[positionCount--];
					break;
				case 'q':
					bitboard[QUEEN][BLACK] |= Bitboard.board[positionCount--];
					break;
				case 'k':
					bitboard[KING][BLACK] |= Bitboard.board[positionCount--];
					break;
			}			
		}
		
		return bitboard;
	}
	
	private static long[][] castling(final long[][] bitboard, final String castlingRights) {
		if(castlingRights.contains("K")) {
			bitboard[SPECIAL][Color.WHITE] |= Bitboard.castlingRight[Color.WHITE][SHORT_CASTLING];
		}
		
		if(castlingRights.contains("Q")) {
			bitboard[SPECIAL][Color.WHITE] |= Bitboard.castlingRight[Color.WHITE][LONG_CASTLING];
		}
		
		if(castlingRights.contains("k")) {
			bitboard[SPECIAL][Color.BLACK] |= Bitboard.castlingRight[Color.BLACK][SHORT_CASTLING];
		}
		
		if(castlingRights.contains("q")) {
			bitboard[SPECIAL][Color.BLACK] |= Bitboard.castlingRight[Color.BLACK][LONG_CASTLING];
		}
		
		return bitboard;
	}
	
	// Gera uma Notacao Fen a partir de um tabuleiro
	public static String notation(final long[][] bitboard) {
		
		StringBuilder view = 
				new StringBuilder(String.format("%064d", new BigInteger(Long.toBinaryString(0))));
		
		for(byte color = WHITE; color <= BLACK; color++) {
	        for (int piece = KING; piece <= PAWN; piece++) {
		        final int[] arrayPieces = Bitboard.indexBitOn(bitboard[piece][color]);
	        	
		        final String typePiece = Piece.of[piece][color];
		        
		        for(int j = 0; j < arrayPieces.length; j++) {
		        	view.replace(arrayPieces[j], arrayPieces[j]+1, typePiece);
		        }
	        }
		}
		
		view = view.reverse();

		byte numberZeros = 0;
		String result = "";
		int index = 0;
		
		for(char c : view.toString().toCharArray()) {
			if(index % 8 == 0 && index > 1) {
				if(numberZeros > 0) {
					result += numberZeros;
				}
				result += "/";
				numberZeros = 0;
			}
			
			if(c == '0') {
				numberZeros += 1;
			}
			else {
				if(numberZeros > 0) {
					result += numberZeros;
					result += c;
					numberZeros = 0;
				} else {
					result += c;
				}
			}
			index++;
		}
		
		if(numberZeros > 0) {
			result += numberZeros;
		}
		
		result += " ";
		result += Color.colorMoving(bitboard);
		result += " ";
		result += castling(bitboard);		
		result += " ";
		result += enPassant(bitboard);
		result += " ";
		result += Bitboard.getHalfMoveSinceCapturePawnAdvance(bitboard);
		result += " ";
		result += Bitboard.getNumberMove(bitboard);
		return result;
	}
	
	private static String castling(final long[][] bitboard) {
		final String whiteCastling = castlingRight(bitboard, Color.WHITE);
		final String blackCastling = castlingRight(bitboard, Color.BLACK);
	
		if(whiteCastling.isEmpty() && blackCastling.isEmpty()) {
			return "-";
		}
		
		return whiteCastling + blackCastling;
	}
	
	private static String castlingRight(final long[][] bitboard, final byte color) {
		String result = "";
		
		if((bitboard[Piece.SPECIAL][color] & Bitboard.castlingRight[color][Bitboard.SHORT_CASTLING]) == Bitboard.castlingRight[color][Bitboard.SHORT_CASTLING]) {
			result += Piece.of[Piece.KING][color];
		}
		
		if((bitboard[Piece.SPECIAL][color] & Bitboard.castlingRight[color][Bitboard.LONG_CASTLING]) == Bitboard.castlingRight[color][Bitboard.LONG_CASTLING]) {
			result += Piece.of[Piece.QUEEN][color];
		}
		
		return result;
	}
	
	private static String enPassant(final long[][] bitboard) { 
		String result = "";
		
		result += enPassantRight(bitboard, Color.WHITE);
		result += enPassantRight(bitboard, Color.BLACK);
		
		if(result.isEmpty()) {
			return "-";
		}
		
		return result;
		
	}
	
	private static String enPassantRight(final long[][] bitboard, final byte color) {
		final long enPassant = bitboard[Piece.SPECIAL][color] & Bitboard.enPassantRank[Bitboard.enemy(color)];
		
		if(enPassant > 0) {
			final long enPassantSquare = (shift.get(Bitboard.enemy(color)).apply(enPassant, 8L));
			
			return Bitboard.squaresBoard[Bitboard.indexBit(enPassantSquare)].name;
		}
		
		return "";
	}
	
	
}
