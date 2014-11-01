/*
 * Copyright (C) 2014 marosj
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.marosj.woodpuzzle.backtracking.lists;

import com.marosj.woodpuzzle.backtracking.PiecePosition;
import com.marosj.woodpuzzle.backtracking.Position;
import java.util.Arrays;
import java.util.Comparator;
import javax.annotation.concurrent.Immutable;

/**
 *
 * @author marosj
 */
@Immutable
public class PiecePositionListImpl implements PiecePosition {

    private static final PositionComparator comparator = new PositionComparator();

    private final Position[] positions;

    public PiecePositionListImpl(Position[] positions) {
        if (positions == null || positions.length == 0) {
            throw new IllegalArgumentException("No positions to create PiecePosition.");
        }
        Arrays.sort(positions, comparator);
        this.positions = Arrays.copyOf(positions, positions.length);
    }

    @Override
    public Position[] positions() {
        return Arrays.copyOf(positions, positions.length);
    }

    @Override
    public boolean intersect(PiecePosition another) {
        if (another instanceof PiecePositionListImpl) {
            PiecePositionListImpl anotherCast = (PiecePositionListImpl) another;
            if (this.positions.length <= anotherCast.positions.length) {
                for (Position position : positions) {
                    int foundIdx = Arrays.binarySearch(anotherCast.positions, position, comparator);
                    if (foundIdx >= 0) {
                        return true;
                    }
                }
                return false;
            } else {
                return another.intersect(this);
            }
        } else {
            return false;
        }
    }

    private static class PositionComparator implements Comparator<Position> {

        @Override
        public int compare(Position o1, Position o2) {
            if (o1.equals(o2)) {
                return 0;
            } else if (o1.getLevel() < o2.getLevel()) {
                return -1;
            } else if (o1.getLevel() > o2.getLevel()) {
                return 1;
            } else if (o1.getRow() < o2.getRow()) {
                return -1;
            } else if (o1.getRow() > o2.getRow()) {
                return 1;
            } else {
                return o1.getColumn() - o2.getColumn();
            }
        }

    }
}