package chess.code;

import chess.code.bitboard.Bitboard;
import chess.code.bitboard.Square;
import chess.code.notation.FenNotation;

public class ChessBoard {

	// Gerar algoritmo recebendo uma Lista de long moves e gerar uma lista de bitboards long[][]
	// Gerar algoritmo recebendo uma jogada e validar com long retornado pela lista de moves
	// Gerar algoritmo para na promocao se selecionar Queen, Rook, Bishop, Knight
	
	// implementar empate(Empate por afogamento onde rei nao em check e zero movimentos possiveis)
	//					 (Empate por material insuficiente, KK, KBK, KKK, KKKK)
	//					 (Empate por check perpetuo) 
	//					 (Empate por 50 lances em sequencia sem captura de pecas e sem movimento de pecas)
	//					 (Empate por posicao ocorrer tres vezes durante uma partida)
	// implementar GUI usando PNG
	
	public static final long[][] distance = Square.distance();
	
	public static void main(String[] args) {
		final ChessBoard chessBoard = ChessBoard.newGame();
		chessBoard.start();	
	}
	
	public static ChessBoard newGame() {
		return new ChessBoard();
	}
	
	private void start() {
		System.out.println("Inicio de Jogo\n");
		
		final String fenString = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

		System.out.println(fenString);
		
		long[][] bitboard = FenNotation.notation(fenString);
		
		Bitboard.show(bitboard);
		
		final String fenStringEqual = FenNotation.notation(bitboard);
		
		System.out.println(fenStringEqual);
		
		if(fenString.equals(fenStringEqual)) {
			System.out.println("Fen Iguais");
		} else {
			System.out.println("Fen diferentes");
		}
		
//		Random random = new Random();		
//		Move movePieces = Bitboard.generateMoves(bitboard, color);
//		
//		Bitboard.show(bitboard);
//		String fenNotation = FenNotation.notation(bitboard);
//		System.out.println(fenNotation);
//		
//		int a = 0;
//		
//		int[] array = {10, 11, 0, 1, 1, 1, 1, 1, 1, 1};
//		
//		do {
//
//			List<String> notation = movePieces.getNotation();
//			List<Long> moves = movePieces.getMoves();
//			
//			int moveNumber = random.nextInt(moves.size()-1);
//			
//			final long move = moves.get(moveNumber);
//			
//			System.out.println("Movimento das " + Color.name(color) + " escolhido " + notation.get(moveNumber));
//			
//			bitboard = Move.movePiece(bitboard, move, color);
//			
//			fenNotation = FenNotation.notation(bitboard);
//			System.out.println(fenNotation);
//			
//			color = Bitboard.enemy(color);
//			
//			movePieces = Bitboard.generateMoves(bitboard, color);
//			
//			a += 1;
//			
//		}while(a < 20);

	}
}
