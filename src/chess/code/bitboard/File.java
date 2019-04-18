package chess.code.bitboard;

public enum File {
	H, G, F, E, D, C, B, A; 
	
	public long value;
	private long magic;
	
	private File() {
		this.value = 0x101010101010101L << ordinal();
		this.magic = 0x1010101010100L << ordinal();
	}
	
	public long getMagic() {
		return magic;
	}
}
