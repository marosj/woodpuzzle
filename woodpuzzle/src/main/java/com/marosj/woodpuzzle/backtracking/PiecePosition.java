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
package com.marosj.woodpuzzle.backtracking;

/**
 *
 * @author marosj
 */
public interface PiecePosition {

    Position[] positions();
    
    /**
     * @return true if at least one position is in both PiecePositions
     */
    boolean intersect(PiecePosition another);
    
    boolean intersect(Position pos);

    PiecePosition shiftLevel(byte levelShift);

    PiecePosition shiftRow(byte rowShift);

    PiecePosition shiftColumn(byte columnShift);
}
