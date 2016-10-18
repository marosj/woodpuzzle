package com.marosj.woodpuzzle.backtracking.lists;

import com.marosj.woodpuzzle.backtracking.FreeSpace;
import com.marosj.woodpuzzle.backtracking.Piece;
import com.marosj.woodpuzzle.backtracking.PiecePosition;
import com.marosj.woodpuzzle.backtracking.Position;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author marosj
 */
public class PieceGenerator {

    private final String name;
    private final List<Position> positions = new ArrayList<>();

    public PieceGenerator(String name) {
        this.name = name;
    }

    public PieceGenerator add(int level, int row, int column) {
        positions.add(new Position(level, row, column));
        return this;
    }

    public Piece generate(FreeSpace space) {
        PiecePosition positionDefinition = new PiecePositionListImpl(positions.toArray(new Position[positions.size()]));
        List<PiecePosition> rotated = createRotations(positionDefinition);
        Set<PiecePosition> addedPositions = new LinkedHashSet<>();
        for (PiecePosition position : rotated) {
            PiecePosition levelShifted = position;
            while (space.intersect(levelShifted)) {
                PiecePosition rowShifted = levelShifted;
                do {
                    PiecePosition columnShifted = rowShifted;
                    do {
                        if (space.canAdd(columnShifted)) {
                            addedPositions.add(columnShifted);
                        }
                        columnShifted = columnShifted.shiftColumn((byte) 1);
                    } while (space.intersect(columnShifted));

                    rowShifted = rowShifted.shiftRow((byte) 1);
                } while (space.intersect(rowShifted));

                levelShifted = levelShifted.shiftLevel((byte) 1);
            }
        }
        return new PieceImpl(name, new ArrayList<>(addedPositions));
    }

    private List<PiecePosition> createRotations(PiecePosition positionDefinition) {
        Set<PiecePosition> result = new LinkedHashSet<>();
        result.add(positionDefinition);

        PiecePositionListImpl rowRotated = rowAxisRotate(positionDefinition);
        for (int i = 0; i < 3; i++) {
            result.add(rowRotated);

            PiecePositionListImpl columnRotated = columnAxisRotate(rowRotated);
            for (int j = 0; j < 3; j++) {
                result.add(columnRotated);

                PiecePositionListImpl levelRotated = levelAxisRotate(columnRotated);
                for (int k = 0; k < 3; k++) {
                    result.add(levelRotated);
                    levelRotated = levelAxisRotate(levelRotated);
                }
                columnRotated = columnAxisRotate(columnRotated);
            }

            rowRotated = rowAxisRotate(rowRotated);
        }

        return new ArrayList<>(result);
    }

    private PiecePositionListImpl rowAxisRotate(PiecePosition oldPos) {
        Position[] newPos = new Position[oldPos.positions().length];
        int i = 0;
        for (Position position : oldPos.positions()) {
            newPos[i] = new Position(position.getColumn(), position.getRow(), 0 - position.getLevel());
            i++;
        }
        return new PiecePositionListImpl(newPos);
    }

    private PiecePositionListImpl columnAxisRotate(PiecePosition oldPos) {
        Position[] newPos = new Position[oldPos.positions().length];
        int i = 0;
        for (Position position : oldPos.positions()) {
            newPos[i] = new Position(position.getRow(), 0 - position.getLevel(), position.getColumn());
            i++;
        }
        return new PiecePositionListImpl(newPos);
    }

    private PiecePositionListImpl levelAxisRotate(PiecePosition oldPos) {
        Position[] newPos = new Position[oldPos.positions().length];
        int i = 0;
        for (Position position : oldPos.positions()) {
            newPos[i] = new Position(position.getLevel(), position.getColumn(), 0 - position.getRow());
            i++;
        }
        return new PiecePositionListImpl(newPos);
    }

}
