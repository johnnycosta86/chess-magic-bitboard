package chess.code.bitboard;

import static chess.code.bitboard.Rank.*;

public enum Diagonal {
	G1_H2(0x102L),
	F1_H3(0x10204L),
	E1_H4(0x1020408L),
	D1_H5(0x102040810L),
	C1_H6(0x10204081020L),
	B1_H7(0x1020408102040L),
	A1_H8(0x102040810204080L),
	A2_G8(0x204081020408000L),
	A3_F8(0x408102040800000L),
	A4_E8(0x810204080000000L),
	A5_D8(0x1020408000000000L),
	A6_C8(0x2040800000000000L),
	A7_B8(0x4080000000000000L),
	A2_B1(0x8040L),
	A3_C1(0x804020L),
	A4_D1(0x80402010L),
	A5_E1(0x8040201008L),
	A6_F1(0x804020100804L),
	A7_G1(0x80402010080402L),
	A8_H1(0x8040201008040201L),
	B8_H2(0x4020100804020100L),
	C8_H3(0x2010080402010000L),
	D8_H4(0x1008040201000000L),
	E8_H5(0x804020100000000L),
	F8_H6(0x402010000000000L),
	G8_H7(0x201000000000000L);
	
	private long value;
	private long magic;
	
	private Diagonal(final long value) {
		this.value = value;
		this.magic = value & ~R1.value & ~R8.value & ~File.A.value & ~File.H.value;
	}
	
	public long getValue() {
		return value;
	}
	
	public long getMagic() {
		return magic;
	}
}
