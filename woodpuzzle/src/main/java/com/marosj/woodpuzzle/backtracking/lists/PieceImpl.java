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

import javax.annotation.concurrent.Immutable;
import java.util.*;

/**
 *
 * @author marosj
 */
@Immutable
public class PieceImpl implements Piece {

    private final String name;
    private final List<PiecePosition> positions = new ArrayList<>();

    public PieceImpl(String name, List<PiecePosition> positions) {
        this.name = name;
        this.positions.addAll(positions);
    }
    
    @Override
    public String name() {
        return name;
    }

    @Override
    public Collection<PiecePosition> availablePositions() {
        return Collections.unmodifiableList(positions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PieceImpl)) return false;
        PieceImpl piece = (PieceImpl) o;
        return Objects.equals(name, piece.name) &&
                Objects.equals(positions, piece.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, positions);
    }

    @Override
    public String toString() {
        return "Piece{" + "name=" + name + ",availablePositions=" + positions + '}';
    }
    
    
}
