package chess.code.bitboard;

import static chess.code.bitboard.Diagonal.A1_H8;
import static chess.code.bitboard.Diagonal.A2_B1;
import static chess.code.bitboard.Diagonal.A2_G8;
import static chess.code.bitboard.Diagonal.A3_C1;
import static chess.code.bitboard.Diagonal.A3_F8;
import static chess.code.bitboard.Diagonal.A4_D1;
import static chess.code.bitboard.Diagonal.A4_E8;
import static chess.code.bitboard.Diagonal.A5_D8;
import static chess.code.bitboard.Diagonal.A5_E1;
import static chess.code.bitboard.Diagonal.A6_C8;
import static chess.code.bitboard.Diagonal.A6_F1;
import static chess.code.bitboard.Diagonal.A7_B8;
import static chess.code.bitboard.Diagonal.A7_G1;
import static chess.code.bitboard.Diagonal.A8_H1;
import static chess.code.bitboard.Diagonal.B1_H7;
import static chess.code.bitboard.Diagonal.B8_H2;
import static chess.code.bitboard.Diagonal.C1_H6;
import static chess.code.bitboard.Diagonal.C8_H3;
import static chess.code.bitboard.Diagonal.D1_H5;
import static chess.code.bitboard.Diagonal.D8_H4;
import static chess.code.bitboard.Diagonal.E1_H4;
import static chess.code.bitboard.Diagonal.E8_H5;
import static chess.code.bitboard.Diagonal.F1_H3;
import static chess.code.bitboard.Diagonal.F8_H6;
import static chess.code.bitboard.Diagonal.G1_H2;
import static chess.code.bitboard.Diagonal.G8_H7;
import static chess.code.bitboard.File.A;
import static chess.code.bitboard.File.B;
import static chess.code.bitboard.File.C;
import static chess.code.bitboard.File.D;
import static chess.code.bitboard.File.E;
import static chess.code.bitboard.File.F;
import static chess.code.bitboard.File.G;
import static chess.code.bitboard.File.H;
import static chess.code.bitboard.Rank.R1;
import static chess.code.bitboard.Rank.R2;
import static chess.code.bitboard.Rank.R3;
import static chess.code.bitboard.Rank.R4;
import static chess.code.bitboard.Rank.R5;
import static chess.code.bitboard.Rank.R6;
import static chess.code.bitboard.Rank.R7;
import static chess.code.bitboard.Rank.R8;

public enum Square {
	A8(63, A, R8, "a8", A8_H1), 
	B8(62, B, R8, "b8", A7_B8, B8_H2), 
	C8(61, C, R8, "c8", A6_C8, C8_H3), 
	D8(60, D, R8, "d8", A5_D8, D8_H4), 
	E8(59, E, R8, "e8", A4_E8, E8_H5), 
	F8(58, F, R8, "f8", A3_F8, F8_H6), 
	G8(57, G, R8, "g8", A2_G8, G8_H7), 
	H8(56, H, R8, "h8", A1_H8),   
	
	A7(55, A, R7, "a7", A7_B8, A7_G1), 
	B7(54, B, R7, "b7", A6_C8, A8_H1), 
	C7(53, C, R7, "c7", A5_D8, B8_H2), 
	D7(52, D, R7, "d7", A4_E8, C8_H3), 
	E7(51, E, R7, "e7", A3_F8, D8_H4), 
	F7(50, F, R7, "f7", A2_G8, E8_H5), 
	G7(49, G, R7, "g7", A1_H8, F8_H6), 
	H7(48, H, R7, "h7", B1_H7, G8_H7), 
	
	A6(47, A, R6, "a6", A6_C8, A6_F1), 
	B6(46, B, R6, "b6", A5_D8, A7_G1), 
	C6(45, C, R6, "c6", A4_E8, A8_H1),
	D6(44, D, R6, "d6", A3_F8, B8_H2), 
	E6(43, E, R6, "e6", A2_G8, C8_H3), 
	F6(42, F, R6, "f6", A1_H8, D8_H4), 
	G6(41, G, R6, "g6", B1_H7, E8_H5), 
	H6(40, H, R6, "h6", C1_H6, F8_H6), 
	
	A5(39, A, R5, "a5", A5_D8, A5_E1), 
	B5(38, B, R5, "b5", A4_E8, A6_F1),
	C5(37, C, R5, "c5", A3_F8, A7_G1), 
	D5(36, D, R5, "d5", A2_G8, A8_H1), 
	E5(35, E, R5, "e5", A1_H8, B8_H2),
	F5(34, F, R5, "f5", B1_H7, C8_H3), 
	G5(33, G, R5, "g5", C1_H6, D8_H4), 
	H5(32, H, R5, "h5", D1_H5, E8_H5),
	
	A4(31, A, R4, "a4", A4_E8, A4_D1), 
	B4(30, B, R4, "b4", A3_F8, A5_E1), 
	C4(29, C, R4, "c4", A2_G8, A6_F1),
	D4(28, D, R4, "d4", A1_H8, A7_G1),
	E4(27, E, R4, "e4", B1_H7, A8_H1), 
	F4(26, F, R4, "f4", C1_H6, B8_H2),
	G4(25, G, R4, "g4", D1_H5, C8_H3), 
	H4(24, H, R4, "h4", E1_H4, D8_H4),
	
	A3(23, A, R3, "a3", A3_F8, A3_C1), 
	B3(22, B, R3, "b3", A2_G8, A4_D1), 
	C3(21, C, R3, "c3", A1_H8, A5_E1),
	D3(20, D, R3, "d3", B1_H7, A6_F1), 
	E3(19, E, R3, "e3", C1_H6, A7_G1), 
	F3(18, F, R3, "f3", D1_H5, A8_H1), 
	G3(17, G, R3, "g3", E1_H4, B8_H2), 
	H3(16, H, R3, "h3", F1_H3, C8_H3),
	
	A2(15, A, R2, "a2", A2_G8, A2_B1),
	B2(14, B, R2, "b2", A1_H8, A3_C1), 
	C2(13, C, R2, "c2", B1_H7, A4_D1), 
	D2(12, D, R2, "d2", C1_H6, A5_E1), 
	E2(11, E, R2, "e2", D1_H5, A6_F1), 
	F2(10, F, R2, "f2", E1_H4, A7_G1), 
	G2(9,  G, R2, "g2", F1_H3, A8_H1), 
	H2(8,  H, R2, "h2", G1_H2, B8_H2),
	
	A1(7, A, R1, "a1", A1_H8), 
	B1(6, B, R1, "b1", B1_H7, A2_B1),
	C1(5, C, R1, "c1", C1_H6, A3_C1), 
	D1(4, D, R1, "d1", D1_H5, A4_D1), 
	E1(3, E, R1, "e1", E1_H4, A5_E1),
	F1(2, F, R1, "f1", F1_H3, A6_F1), 
	G1(1, G, R1, "g1", G1_H2, A7_G1), 
	H1(0, H, R1, "h1", A8_H1);
	
	public final byte id; 
	public final int index;
	private File file;
	private Rank rank;
	public final long value;
	public final String name;
	private Diagonal[] diagonal = new Diagonal[2];
	
	public static long[][] distance = new long[64][64];
	
	private Square(final int index, final File file, final Rank rank, final String name, final Diagonal... diagonal) {
		this.index = index;
		this.file = file;
		this.rank = rank;
		this.value = 1L << index;
		this.diagonal = diagonal;
		this.id = (byte)index;
		this.name= name;
	}
	
	public static long[] fileRank() {
		final long[] masks = new  long[64];
		 
		for(Square square : values()) {
			masks[square.index] = (square.getFile().getMagic() | square.getRank().getMagic()) & ~(1L<<square.index);
		}
		
		return masks;
	}
	
	public static long[] diagonals() {
		final long[] masks = new  long[64];
		 
		for(Square square : values()) {
			
			if(square.diagonal.length > 1) {
				masks[square.index] = (square.getDiagonal()[0].getMagic() | square.getDiagonal()[1].getMagic()) & ~(1L<<square.index);
			}
			else {
				masks[square.index] = square.getDiagonal()[0].getMagic() & ~(1L<<square.index);
			}
			
		}
		
		return masks;
	}
	
	public static long[] board() {
		final long[] masks = new long[64];
		 
		for(Square square : values()) {
			masks[square.index] = square.value;
		}
		
		return masks;
	}
	
	public static Square[] squares() {
		final Square[] result = new Square[64];
		 
		for(Square square : values()) {
			result[square.index] = square;
		}
		
		return result;
	}
	
	public static String[] notation() {
		final String[] algebricNotation = new String[64];
		 
		for(Square square : values()) {
			algebricNotation[square.index] = square.name;
		}
		
		return algebricNotation;
	}
	
	public static String name(final int index) {
		for(Square square : values()) {
			if(square.index == index) {
				return square.toString();
			}
		}
		
		return null;
	}
	
	public static Square get(final long value) {
		for(Square square : values()) {
			if(square.value == value) {
				return square;
			}
		}
		
		return null;
	}
	
	public static Square get(final int index) {
		for(Square square : values()) {
			if(square.index == index) {
				return square;
			}
		}
		
		return null;
	}
	
	public static long[][] distance() {
		final long[] squares = board();
		final long[][] distance = new long[64][64];
		
		int i;

		// fill from->to where to > from
		for (int from = 0; from < 64; from++) {
			for (int to = from + 1; to < 64; to++) {
				// horizontal
				if (from / 8 == to / 8) {
					i = to - 1;
					while (i > from) {
						distance[from][to] |= squares[i];
						i--;
					}
				}

				// vertical
				if (from % 8 == to % 8) {
					i = to - 8;
					while (i > from) {
						distance[from][to] |= squares[i];
						i -= 8;
					}
				}
			}
		}

		// fill from->to where to < from
		for (int from = 0; from < 64; from++) {
			for (int to = 0; to < from; to++) {
				distance[from][to] = distance[to][from];
			}
		}
	

		i = 0;

		// fill from->to where to > from
		for (int from = 0; from < 64; from++) {
			for (int to = from + 1; to < 64; to++) {
				// diagonal \
				if ((to - from) % 9 == 0 && to % 8 > from % 8) {
					i = to - 9;
					while (i > from) {
						distance[from][to] |= squares[i];
						i -= 9;
					}
				}

				// diagonal /
				if ((to - from) % 7 == 0 && to % 8 < from % 8) {
					i = to - 7;
					while (i > from) {
						distance[from][to] |= squares[i];
						i -= 7;
					}
				}
			}
		}

		// fill from->to where to < from
		for (int from = 0; from < 64; from++) {
			for (int to = 0; to < from; to++) {
				distance[from][to] = distance[to][from];
			}
		}
		
		return distance;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	public File getFile() {
		return file;
	}

	public Rank getRank() {
		return rank;
	}
	
	public Diagonal[] getDiagonal() {
		return diagonal;
	}	
}
