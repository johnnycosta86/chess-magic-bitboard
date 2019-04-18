package chess.code.magic.bitboard;

import chess.code.bitboard.Bitboard;
import chess.code.bitboard.Square;

public enum MagicBitboardBase {

	H1(0, 0xa180022080400230L, 0x2910054208004104L, 52,58),
	G1(1, 0x40100040022000L, 0x2100630a7020180L, 53,59),
	F1(2, 0x80088020001002L, 0x5822022042000000L, 53,59),
	E1(3, 0x80080280841000L, 0x2ca804a100200020L, 53,59),
	D1(4, 0x4200042010460008L, 0x204042200000900L, 53,59),
	C1(5, 0x4800a0003040080L, 0x2002121024000002L, 53,59),
	B1(6, 0x400110082041008L, 0x80404104202000e8L, 53,59),
	A1(7, 0x8000a041000880L, 0x812a020205010840L, 52,58),
	H2(8, 0x10138001a080c010L, 0x8005181184080048L, 53,59),
	G2(9, 0x804008200480L, 0x1001c20208010101L, 54,59),
	F2(10, 0x10011012000c0L, 0x1001080204002100L, 54,59),
	E2(11, 0x22004128102200L, 0x1810080489021800L, 54,59),
	D2(12, 0x200081201200cL, 0x62040420010a00L, 54,59),
	C2(13, 0x202a001048460004L, 0x5028043004300020L, 54,59),
	B2(14, 0x81000100420004L, 0xc0080a4402605002L, 54,59),
	A2(15, 0x4000800380004500L, 0x8a00a0104220200L, 53,59),
	H3(16, 0x208002904001L, 0x940000410821212L, 53,59),
	G3(17, 0x90004040026008L, 0x1808024a280210L, 54,59),
	F3(18, 0x208808010002001L, 0x40c0422080a0598L, 54,57),
	E3(19, 0x2002020020704940L, 0x4228020082004050L, 54,57),
	D3(20, 0x8048010008110005L, 0x200800400e00100L, 54,57),
	C3(21, 0x6820808004002200L, 0x20b001230021040L, 54,57),
	B3(22, 0xa80040008023011L, 0x90a0201900c00L, 54,59),
	A3(23, 0xb1460000811044L, 0x4940120a0a0108L, 53,59),
	H4(24, 0x4204400080008ea0L, 0x20208050a42180L, 53,59),
	G4(25, 0xb002400180200184L, 0x1004804b280200L, 54,59),
	F4(26, 0x2020200080100380L, 0x2048020024040010L, 54,57),
	E4(27, 0x10080080100080L, 0x102c04004010200L, 54,55),
	D4(28, 0x2204080080800400L, 0x20408204c002010L, 54,55),
	C4(29, 0xa40080360080L, 0x2411100020080c1L, 54,57),
	B4(30, 0x2040604002810b1L, 0x102a008084042100L, 54,59),
	A4(31, 0x8c218600004104L, 0x941030000a09846L, 53,59),
	H5(32, 0x8180004000402000L, 0x244100800400200L, 53,59),
	G5(33, 0x488c402000401001L, 0x4000901010080696L, 54,59),
	F5(34, 0x4018a00080801004L, 0x280404180020L, 54,57),
	E5(35, 0x1230002105001008L, 0x800042008240100L, 54,55),
	D5(36, 0x8904800800800400L, 0x220008400088020L, 54,55),
	C5(37, 0x42000c42003810L, 0x4020182000904c9L, 54,57),
	B5(38, 0x8408110400b012L, 0x23010400020600L, 54,59),
	A5(39, 0x18086182000401L, 0x41040020110302L, 53,59),
	H6(40, 0x2240088020c28000L, 0x412101004020818L, 53,59),
	G6(41, 0x1001201040c004L, 0x8022080a09404208L, 54,59),
	F6(42, 0xa02008010420020L, 0x1401210240484800L, 54,57),
	E6(43, 0x10003009010060L, 0x22244208010080L, 54,57),
	D6(44, 0x4008008008014L, 0x1105040104000210L, 54,57),
	C6(45, 0x80020004008080L, 0x2040088800c40081L, 54,57),
	B6(46, 0x282020001008080L, 0x8184810252000400L, 54,59),
	A6(47, 0x50000181204a0004L, 0x4004610041002200L, 53,59),
	H7(48, 0x102042111804200L, 0x40201a444400810L, 53,59),
	G7(49, 0x40002010004001c0L, 0x4611010802020008L, 54,59),
	F7(50, 0x19220045508200L, 0x80000b0401040402L, 54,59),
	E7(51, 0x20030010060a900L, 0x20004821880a00L, 54,59),
	D7(52, 0x8018028040080L, 0x8200002022440100L, 54,59),
	C7(53, 0x88240002008080L, 0x9431801010068L, 54,59),
	B7(54, 0x10301802830400L, 0x1040c20806108040L, 54,59),
	A7(55, 0x332a4081140200L, 0x804901403022a40L, 53,59),
	H8(56, 0x8080010a601241L, 0x2400202602104000L, 52,58),
	G8(57, 0x1008010400021L, 0x208520209440204L, 53,59),
	F8(58, 0x4082001007241L, 0x40c000022013020L, 53,59),
	E8(59, 0x211009001200509L, 0x2000104000420600L, 53,59),
	D8(60, 0x8015001002441801L, 0x400000260142410L, 53,59),
	C8(61, 0x801000804000603L, 0x800633408100500L, 53,59),
	B8(62, 0xc0900220024a401L, 0x2404080a1410L, 53,59),
	A8(63, 0x1000200608243L, 0x138200122002900L, 52,58);
	
	private int index;
	private long rookMask;
	private long bishopMask;
	private int rookShift;
	private int bishopShift;
	
	private static final long[] magic = new long[6326];
	
	private static int indexMagic = 0;
	
	private static final long[] rookMovementMasks = Square.fileRank();
	private static final long[] bishopMovementMasks = Square.diagonals();
	private static final long[] board = Bitboard.board;

	private MagicBitboardBase(final int index, final long rookMask, final long bishopMask, final int rookShift, final int bishopShift) {
		this.index = index;
		this.rookMask = rookMask;
		this.bishopMask = bishopMask;
		this.rookShift = rookShift;
		this.bishopMask = bishopMask;
		this.bishopShift = bishopShift;
	}
	
	public static long[] rookMagic() {
		
		final long[] array = new long[64]; 
		
		for(MagicBitboardBase magic : MagicBitboardBase.values()) {
			array[magic.getIndex()] = magic.getRookMask();
		}
		
		return array;
	}
	
	public static int[] rookShift() {
		
		final int[] array = new int[64]; 
		
		for(MagicBitboardBase magic : MagicBitboardBase.values()) {
			array[magic.getIndex()] = magic.getRookShift();
		}
		
		return array;
	}
	
	public static long[] bishopMagic() {
		
		final long[] array = new long[64]; 
		
		for(MagicBitboardBase magic : MagicBitboardBase.values()) {
			array[magic.getIndex()] = magic.getBishopMask();
		}
		
		return array;
	}
	
	public static int[] bishopShift() {
		
		final int[] array = new int[64]; 
		
		for(MagicBitboardBase magic : MagicBitboardBase.values()) {
			array[magic.getIndex()] = magic.getBishopShift();
		}
		
		return array;
	}
	
	private static long[][] generateRookOccupancy() {
		final long[][] rookOccupancyVariations = new long[64][];
		
		// calculate all variations
		for (int bitIndex = 0; bitIndex < 64; bitIndex++) {
			int[] setBitsInMask = Bitboard.indexBitOn(rookMovementMasks[bitIndex]);
			int variationCount = (int) board[Long.bitCount(rookMovementMasks[bitIndex])];
			rookOccupancyVariations[bitIndex] = new long[variationCount];

			for (int variationIndex = 0; variationIndex < variationCount; variationIndex++) {
				int currentVariationIndex = variationIndex;
				while (currentVariationIndex != 0) {
					rookOccupancyVariations[bitIndex][variationIndex] |= board[setBitsInMask[Long.numberOfTrailingZeros(currentVariationIndex)]];
					currentVariationIndex &= currentVariationIndex - 1;
				}
			}
		}
		
		return rookOccupancyVariations;
		
	}
	
	private static long[][] generateBishopOccupancy() {
		final long[][] bishopOccupancyVariations = new long[64][];
		
		// calculate all variations
		for (int bitIndex = 0; bitIndex < 64; bitIndex++) {
			int[] setBitsInMask = Bitboard.indexBitOn(bishopMovementMasks[bitIndex]);
			int variationCount = (int) board[Long.bitCount(bishopMovementMasks[bitIndex])];
			bishopOccupancyVariations[bitIndex] = new long[variationCount];

			for (int variationIndex = 0; variationIndex < variationCount; variationIndex++) {
				int currentVariationIndex = variationIndex;
				while (currentVariationIndex != 0) {
					bishopOccupancyVariations[bitIndex][variationIndex] |= board[setBitsInMask[Long.numberOfTrailingZeros(currentVariationIndex)]];
					currentVariationIndex &= currentVariationIndex - 1;
				}
			}
		}
		
		return bishopOccupancyVariations;
	}
	
	public static short[][] generateRookMoveDatabase() {
		
		final long[][] rookOccupancyVariations = generateRookOccupancy();
		final long[] rookMagicNumbers = rookMagic();
		
		final short[][] rookMagicMoves = new short[64][];
		
		int j = 0;
		for (int bitIndex = 0; bitIndex < 64; bitIndex++) {
			rookMagicMoves[bitIndex] = new short[rookOccupancyVariations[bitIndex].length];
			for (int variationIndex = 0; variationIndex < rookOccupancyVariations[bitIndex].length; variationIndex++) {
				long validMoves = 0;
				int magicIndex = (int) ((rookOccupancyVariations[bitIndex][variationIndex] * rookMagicNumbers[bitIndex]) >>> (64
						- Long.bitCount(rookMovementMasks[bitIndex])));

				for (j = bitIndex + 8; j < 64; j += 8) {
					validMoves |= board[j];
					if ((rookOccupancyVariations[bitIndex][variationIndex] & board[j]) != 0) {
						break;
					}
				}
				for (j = bitIndex - 8; j >= 0; j -= 8) {
					validMoves |= board[j];
					if ((rookOccupancyVariations[bitIndex][variationIndex] & board[j]) != 0) {
						break;
					}
				}
				for (j = bitIndex + 1; j % 8 != 0; j++) {
					validMoves |= board[j];
					if ((rookOccupancyVariations[bitIndex][variationIndex] & board[j]) != 0) {
						break;
					}
				}
				for (j = bitIndex - 1; j % 8 != 7 && j >= 0; j--) {
					validMoves |= board[j];
					if ((rookOccupancyVariations[bitIndex][variationIndex] & board[j]) != 0) {
						break;
					}
				}
				
				saveMagicNumber(validMoves, rookMagicMoves, bitIndex, magicIndex);
			}
		}
		
		return rookMagicMoves;
	}
	
	public static short[][] generateBishopMoveDatabase() {
		
		final long[][] bishopOccupancyVariations = generateBishopOccupancy();
		final long[] bishopMagicNumbers = bishopMagic();
		
		final short[][] bishopMagicMoves = new short[64][];
		
		int j = 0;
		for (int bitIndex = 0; bitIndex < 64; bitIndex++) {
			bishopMagicMoves[bitIndex] = new short[bishopOccupancyVariations[bitIndex].length];
			for (int variationIndex = 0; variationIndex < bishopOccupancyVariations[bitIndex].length; variationIndex++) {
				long validMoves = 0;
				int magicIndex = (int) ((bishopOccupancyVariations[bitIndex][variationIndex] * bishopMagicNumbers[bitIndex]) >>> (64
						- Long.bitCount(bishopMovementMasks[bitIndex])));

				// up-right
				for (j = bitIndex + 7; j % 8 != 7 && j < 64; j += 7) {
					validMoves |= board[j];
					if ((bishopOccupancyVariations[bitIndex][variationIndex] & board[j]) != 0) {
						break;
					}
				}
				// up-left
				for (j = bitIndex + 9; j % 8 != 0 && j < 64; j += 9) {
					validMoves |= board[j];
					if ((bishopOccupancyVariations[bitIndex][variationIndex] & board[j]) != 0) {
						break;
					}
				}
				// down-right
				for (j = bitIndex - 9; j % 8 != 7 && j >= 0; j -= 9) {
					validMoves |= board[j];
					if ((bishopOccupancyVariations[bitIndex][variationIndex] & board[j]) != 0) {
						break;
					}
				}
				// down-left
				for (j = bitIndex - 7; j % 8 != 0 && j >= 0; j -= 7) {
					validMoves |= board[j];
					if ((bishopOccupancyVariations[bitIndex][variationIndex] & board[j]) != 0) {
						break;
					}
				}
				
				saveMagicNumber(validMoves, bishopMagicMoves, bitIndex, magicIndex);
			}
		}
		
		return bishopMagicMoves;
	}
	
	private static void saveMagicNumber(final long validMoves, final short[][] array, int bitIndex, int magicIndex) {
		int index = -1;
		
		for(int a = 0; a < magic.length; a++) {
			if(magic[a] == validMoves) {
				index = a;
				break;
			}
		}
		
		if(index != -1) {
			array[bitIndex][magicIndex] = (short) index;
		}
		else {
			magic[indexMagic] = validMoves;
			
			array[bitIndex][magicIndex] = (short) indexMagic;
			
			indexMagic = indexMagic + 1;
		}
	}

	public int getIndex() {
		return index;
	}

	public long getRookMask() {
		return rookMask;
	}

	public long getBishopMask() {
		return bishopMask;
	}

	public int getRookShift() {
		return rookShift;
	}

	public int getBishopShift() {
		return bishopShift;
	}
	
	public static long[] getMagic() {
		return magic;
	}

	public static long[] getRookmovementmasks() {
		return rookMovementMasks;
	}

	public static long[] getBishopmovementmasks() {
		return bishopMovementMasks;
	}
}