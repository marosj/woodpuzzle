package com.marosj.woodpuzzle;

import com.marosj.woodpuzzle.backtracking.FreeSpace;
import com.marosj.woodpuzzle.backtracking.Piece;
import com.marosj.woodpuzzle.backtracking.Position;
import com.marosj.woodpuzzle.backtracking.ProblemData;
import com.marosj.woodpuzzle.backtracking.lists.FreeSpaceImpl;
import com.marosj.woodpuzzle.backtracking.lists.PieceGenerator;

import java.util.*;

/**
 * @author marosj
 */
public class Cube {

    private final FreeSpace freeSpace;
    private final List<Piece> pieces;

    public Cube() {
        Position[] freeSpacePositions = generateFreeSpace();
        freeSpace = getFreeSpace(freeSpacePositions);

        FreeSpace freeSpaceForPieces = new FreeSpaceImpl(freeSpacePositions);
        pieces = new ArrayList<>();

        pieces.add(getPieceZ(freeSpaceForPieces));
        pieces.add(getPieceK(freeSpaceForPieces));
        pieces.add(getPieceZL(freeSpaceForPieces));
        pieces.add(getPieceT(freeSpaceForPieces));
        pieces.add(getPieceU(freeSpaceForPieces));
        pieces.add(getPieceW(freeSpaceForPieces));
        pieces.add(getPieceP(freeSpaceForPieces));
        pieces.add(getPieceL(freeSpaceForPieces));
        pieces.add(getPieceS(freeSpaceForPieces));
        pieces.add(getPieceI(freeSpaceForPieces));
    }

    public ProblemData toProblemData() {
        return new ProblemData(freeSpace, pieces);
    }

    private Piece getPieceL(FreeSpace freeSpaceForPieces) {
        return new PieceGenerator("L")
                .add(0, 0, 0).add(0, 0, 1).add(0, 0, 2).add(0, 1, 2)
                .generate(freeSpaceForPieces);
    }

    private Piece getPieceZ(FreeSpace freeSpaceForPieces) {
        return new PieceGenerator("Z")
                .add(0, 0, 0).add(0, 0, 1)
                .add(0, 1, 1)
                .add(0, 2, 1).add(0, 2, 2)
                .add(1, 2, 2)
                .generate(freeSpaceForPieces);
    }

    private Piece getPieceK(FreeSpace freeSpaceForPieces) {
        return new PieceGenerator("K")
                .add(0, 0, 0).add(0, 0, 1).add(0, 0, 2).add(0, 0, 3)
                .add(0, -1, 2)
                .add(1, 0, 3)
                .generate(freeSpaceForPieces);
    }

    private Piece getPieceZL(FreeSpace freeSpaceForPieces) {
        return new PieceGenerator("ZL")
                .add(0, 0, 0).add(0, 0, 1).add(0, 0, 2)
                .add(0, -1, 2)
                .add(1, -1, 2).add(1, -1, 3)
                .generate(freeSpaceForPieces);
    }

    private Piece getPieceT(FreeSpace freeSpaceForPieces) {
        return new PieceGenerator("T")
                .add(0, 0, 0)
                .add(0, 1, 0).add(0, 1, 1).add(0, 1, 2)
                .add(0, 2, 1)
                .add(0, 3, 1)
                .generate(freeSpaceForPieces);
    }

    private Piece getPieceU(FreeSpace freeSpaceForPieces) {
        return new PieceGenerator("U")
                .add(0, 0, 0).add(0, 0, 1).add(0, 0, 2)
                .add(0, 1, 0)
                .add(1, 1, 0).add(1, 0, 2)
                .generate(freeSpaceForPieces);
    }

    private Piece getPieceW(FreeSpace freeSpaceForPieces) {
        return new PieceGenerator("W")
                .add(0, 0, 0).add(0, 0, 1)
                .add(0, 1, 1).add(0, 1, 2)
                .add(1, 0, 0).add(1, 1, 2)
                .generate(freeSpaceForPieces);
    }

    private Piece getPieceP(FreeSpace freeSpaceForPieces) {
        return new PieceGenerator("P")
                .add(0, 0, 0).add(0, 0, 1).add(0, 0, 2)
                .add(0, 1, 0).add(0, 1, 1)
                .add(1, 1, 1)
                .generate(freeSpaceForPieces);
    }

    private Piece getPieceS(FreeSpace freeSpaceForPieces) {
        return new PieceGenerator("S")
                .add(0, 0, 0).add(0, 0, 1).add(0, 1, 0)
                .add(1, 1, 0).add(1, 1, 1).add(1, 0, 1)
                .generate(freeSpaceForPieces);
    }

    private Piece getPieceI(FreeSpace freeSpaceForPieces) {
        return new PieceGenerator("I")
                .add(0, 0, 0).add(0, 0, 1).add(0, 1, 1)
                .add(1, 0, 0)
                .generate(freeSpaceForPieces);
    }

    private FreeSpace getFreeSpace(Position[] freeSpacePositions) {
        Set<Position> freeSpace = new HashSet<>(Arrays.asList(freeSpacePositions));
        freeSpace.remove(new Position(0, 0, 0));
        freeSpace.remove(new Position(0, 0, 3));
        freeSpace.remove(new Position(0, 3, 0));
        freeSpace.remove(new Position(0, 3, 3));
        freeSpace.remove(new Position(3, 0, 0));
        freeSpace.remove(new Position(3, 0, 3));
        freeSpace.remove(new Position(3, 3, 0));
        freeSpace.remove(new Position(3, 3, 3));
        return new FreeSpaceImpl(freeSpace.toArray(new Position[4 * 4 * 4 - 8]));
    }

    private Position[] generateFreeSpace() {
        Position[] result = new Position[4 * 4 * 4];
        for (int level = 0; level < 4; level++) {
            for (int row = 0; row < 4; row++) {
                for (int column = 0; column < 4; column++) {
                    result[level * 4 * 4 + row * 4 + column] = new Position(level, row, column);
                }
            }
        }
        return result;
    }
}
