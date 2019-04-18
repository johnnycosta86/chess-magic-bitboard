package chess.code.magic.bitboard;

public final class MagicBitboard {
	private static final short[][] rookMagicMoves = MagicBitboardBase.generateRookMoveDatabase();
	private static final long[] rookMovementMasks = MagicBitboardBase.getRookmovementmasks();
	private static final long[] rookMagicNumbers = MagicBitboardBase.rookMagic();
	private static final int[] rookShifts = MagicBitboardBase.rookShift();
	
	private static final short[][] bishopMagicMoves = MagicBitboardBase.generateBishopMoveDatabase();
	private static final long[] bishopMovementMasks = MagicBitboardBase.getBishopmovementmasks();
	private static final long[] bishopMagicNumbers = MagicBitboardBase.bishopMagic();
	private static final int[] bishopShifts = MagicBitboardBase.bishopShift();
	
	// magic result: 50kb
	private static final long[] magic = MagicBitboardBase.getMagic();
	
	// rook-size: 201kb
	public static long getRookMoves(final int fromIndex, final long allPieces) {
		final short index = rookMagicMoves[fromIndex][(int) ((allPieces & rookMovementMasks[fromIndex]) * rookMagicNumbers[fromIndex] >>> rookShifts[fromIndex])];
		
		return magic[index];
	}

	// bishop-size: 11kb
	public static long getBishopMoves(final int fromIndex, final long allPieces) {
		final short index = bishopMagicMoves[fromIndex][(int) ((allPieces & bishopMovementMasks[fromIndex]) * bishopMagicNumbers[fromIndex] >>> bishopShifts[fromIndex])];
		
		return magic[index];
	}

	public static long getQueenMoves(final int fromIndex, final long allPieces) {
		return getRookMoves(fromIndex, allPieces) | getBishopMoves(fromIndex, allPieces);
	}	
}
