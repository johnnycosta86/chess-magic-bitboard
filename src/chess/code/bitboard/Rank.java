package chess.code.bitboard;

public enum Rank {
	R1,	R2,	R3,	R4,	R5,	R6,	R7,	R8;
	
	public long value;
	private long magic;
	
	private Rank() {
		this.value = 0xffL << (8*ordinal());	
		this.magic = 0x7eL << (8*ordinal());	
	}
	
	public long getMagic() {
		return magic;
	}
}
