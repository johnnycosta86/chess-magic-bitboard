package chess.code.piece;

import java.util.HashMap;
import java.util.Map;

import chess.code.bitboard.Bitboard;
import chess.code.bitboard.Square;

public class Knight implements Piece {
	
	private static Map<Integer, Long> position = new HashMap<>();
	
	static {
		position.put(Square.A1.index, Long.valueOf(0x402000L)); 
		position.put(Square.A2.index, Long.valueOf(0x40200020L)); 
		position.put(Square.A3.index, Long.valueOf(0x4020002040L)); 
		position.put(Square.A4.index, Long.valueOf(0x402000204000L)); 
		position.put(Square.A5.index, Long.valueOf(0x40200020400000L)); 
		position.put(Square.A6.index, Long.valueOf(0x4020002040000000L));
		position.put(Square.A7.index, Long.valueOf(0x2000204000000000L)); 
		position.put(Square.A8.index, Long.valueOf(0x20400000000000L)); 
		
		position.put(Square.B1.index, Long.valueOf(0xa01000L)); 
		position.put(Square.B2.index, Long.valueOf(0xa0100010L)); 
		position.put(Square.B3.index, Long.valueOf(0xa0100010a0L)); 
		position.put(Square.B4.index, Long.valueOf(0xa0100010a000L)); 
		position.put(Square.B5.index, Long.valueOf(0xa0100010a00000L)); 
		position.put(Square.B6.index, Long.valueOf(0xa0100010a0000000L)); 
		position.put(Square.B7.index, Long.valueOf(0x100010a000000000L)); 
		position.put(Square.B8.index, Long.valueOf(0x10a00000000000L)); 
		
		position.put(Square.C1.index, Long.valueOf(0x508800L)); 
		position.put(Square.C2.index, Long.valueOf(0x50880088L)); 
		position.put(Square.C3.index, Long.valueOf(0x5088008850L)); 
		position.put(Square.C4.index, Long.valueOf(0x508800885000L)); 
		position.put(Square.C5.index, Long.valueOf(0x50880088500000L)); 
		position.put(Square.C6.index, Long.valueOf(0x5088008850000000L)); 
		position.put(Square.C7.index, Long.valueOf(0x8800885000000000L)); 
		position.put(Square.C8.index, Long.valueOf(0x88500000000000L)); 
		
		position.put(Square.D1.index, Long.valueOf(0x284400L)); 
		position.put(Square.D2.index, Long.valueOf(0x28440044L)); 
		position.put(Square.D3.index, Long.valueOf(0x2844004428L)); 
		position.put(Square.D4.index, Long.valueOf(0x284400442800L)); 
		position.put(Square.D5.index, Long.valueOf(0x28440044280000L)); 
		position.put(Square.D6.index, Long.valueOf(0x2844004428000000L)); 
		position.put(Square.D7.index, Long.valueOf(0x4400442800000000L)); 
		position.put(Square.D8.index, Long.valueOf(0x44280000000000L)); 
		
		position.put(Square.E1.index, Long.valueOf(0x142200L)); 
		position.put(Square.E2.index, Long.valueOf(0x14220022L)); 
		position.put(Square.E3.index, Long.valueOf(0x1422002214L)); 
		position.put(Square.E4.index, Long.valueOf(0x142200221400L)); 
		position.put(Square.E5.index, Long.valueOf(0x14220022140000L)); 
		position.put(Square.E6.index, Long.valueOf(0x1422002214000000L)); 
		position.put(Square.E7.index, Long.valueOf(0x2200221400000000L)); 
		position.put(Square.E8.index, Long.valueOf(0x22140000000000L)); 
		
		position.put(Square.F1.index, Long.valueOf(0xa1100L)); 
		position.put(Square.F2.index, Long.valueOf(0xa110011L)); 
		position.put(Square.F3.index, Long.valueOf(0xa1100110aL)); 
		position.put(Square.F4.index, Long.valueOf(0xa1100110a00L)); 
		position.put(Square.F5.index, Long.valueOf(0xa1100110a0000L)); 
		position.put(Square.F6.index, Long.valueOf(0xa1100110a000000L)); 
		position.put(Square.F7.index, Long.valueOf(0x1100110a00000000L)); 
		position.put(Square.F8.index, Long.valueOf(0x110a0000000000L)); 
		
		position.put(Square.G1.index, Long.valueOf(0x50800L)); 
		position.put(Square.G2.index, Long.valueOf(0x5080008L)); 
		position.put(Square.G3.index, Long.valueOf(0x508000805L)); 
		position.put(Square.G4.index, Long.valueOf(0x50800080500L)); 
		position.put(Square.G5.index, Long.valueOf(0x5080008050000L)); 
		position.put(Square.G6.index, Long.valueOf(0x508000805000000L)); 
		position.put(Square.G7.index, Long.valueOf(0x800080500000000L)); 
		position.put(Square.G8.index, Long.valueOf(0x8050000000000L)); 
		
		position.put(Square.H1.index, Long.valueOf(0x20400L)); 
		position.put(Square.H2.index, Long.valueOf(0x2040004L)); 
		position.put(Square.H3.index, Long.valueOf(0x204000402L)); 
		position.put(Square.H4.index, Long.valueOf(0x20400040200L)); 
		position.put(Square.H5.index, Long.valueOf(0x2040004020000L)); 
		position.put(Square.H6.index, Long.valueOf(0x204000402000000L)); 
		position.put(Square.H7.index, Long.valueOf(0x400040200000000L)); 
		position.put(Square.H8.index, Long.valueOf(0x4020000000000L)); 
	}
	
	private Square square;
	
	private byte color;
	
	public static Knight at(final Square square) {
		final Knight knight = new Knight();
		knight.square = square;
		
		return knight;
	}
	
	public Knight of(final byte color) {
		this.color = color;
		return this;
	}
	
	public static long[] attack(final long[][] bitboard, final byte color, final long enemy) {
		final long[] array = new long[3];
		
		final long knights = bitboard[Piece.KNIGHT][color];
		final int[] squares = Bitboard.indexBitOn(knights);
		
		for(int i = 0; i < squares.length; i++) {
			final long knightAttack = position.get(squares[i]);
			
			if((knightAttack & enemy) > 0) {
				array[1] |= knightAttack;
				array[2] |= squares[i];
			}
			
			array[0] |= knightAttack;
			
		}
		
		return array;
	}
	
	public static long attack(final long knight) {		
		final int square = Bitboard.indexBitOn(knight)[0];
		
		return position.get(square);
	}
	
	public static long attack(final long knight, final long allPieces, long moveSpecial, final byte color) {		
		final int square = Bitboard.indexBitOn(knight)[0];
		
		return position.get(square);
	}

	public static long attack(final long[][] bitboard, final byte color) {
		final long knights = bitboard[Piece.KNIGHT][color];
		final int[] squares = Bitboard.indexBitOn(knights);
		long table = 0L;
		
		for(int i = 0; i < squares.length; i++) {
			table |= position.get(squares[i]);
		}
		
		return table;
	}
	
	public static long move(final long allPieces, final long myPieces, long moveSpecial, final long squaresAttackedByEnemy, final int index, byte color) {
		return position.get(index) & ~myPieces;
	}
	
	public static long move(long[][] bitboard, final Square square, byte color) {
		final long myPieces = Bitboard.allMyPieces(bitboard, color);
		
		return position.get(square.index) & ~myPieces;
	}
	
	public static long[][][] attack(final long[][][] chessboard, final long knights, final byte color) {
		final int[] squares = Bitboard.indexBitOn(knights);
		
		for(int i = 0; i < squares.length; i++) {
			chessboard[squares[i]][color][0] = position.get(squares[i]);
		}
		
		return chessboard;
	}
	
	
	@Override
	public byte getType() {
		return Piece.KNIGHT;
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
