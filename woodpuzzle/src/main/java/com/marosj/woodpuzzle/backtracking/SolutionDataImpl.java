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
package com.marosj.woodpuzzle.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 *
 * @author marosj
 */
@Immutable
class SolutionDataImpl implements SolutionData{

    private final List<Piece> pieces = new ArrayList<>();
    private final List<PiecePosition> positions = new ArrayList<>();

    public SolutionDataImpl() {
    }
    
    private SolutionDataImpl(List<Piece> pieces, List<PiecePosition> positions) {
        this.pieces.addAll(pieces);
        this.positions.addAll(positions);
    }
    
    @Override
    public SolutionData addPiece(Piece piece, PiecePosition piecePos) {
        List<PiecePosition> newPositions = new ArrayList<>(positions);
        newPositions.add(piecePos);
        List<Piece> newPieces = new ArrayList<>(this.pieces);
        newPieces.add(piece);
        return new SolutionDataImpl(newPieces, newPositions);
    }

    @Override
    public List<Piece> pieces() {
        return Collections.unmodifiableList(pieces);
    }

    @Override
    public List<PiecePosition> positions() {
        return Collections.unmodifiableList(positions);
    }

    @Override
    public String toString() {
        return "SolutionDataImpl{" + "pieces=" + pieces + ", positions=" + positions + '}';
    }

}
