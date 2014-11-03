// <editor-fold defaultstate="collapsed" desc="License">
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
// </editor-fold>
package com.marosj.woodpuzzle.backtracking.lists;

import com.marosj.woodpuzzle.backtracking.Piece;
import com.marosj.woodpuzzle.backtracking.PiecePosition;
import com.marosj.woodpuzzle.backtracking.Position;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marosj
 */
public class PieceBuilder {

    private final String name;
    private final List<PiecePosition> piecePositions = new ArrayList<>();
    private final List<Position> positions = new ArrayList<>();

    public PieceBuilder(String name) {
        this.name = name;
    }
    
    public PieceBuilder add(int level, int row, int column) {
        positions.add(new Position(level, row, column));
        return this;
    }
    
    public PieceBuilder addPosition() {
        piecePositions.add(new PiecePositionListImpl(positions.toArray(new Position[0])));
        positions.clear();
        return this;
    }
    
    public Piece create() {
        return new PieceImpl(name, piecePositions);
    }
}
