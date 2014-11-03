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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 *
 * @author marosj
 */
@Immutable
public class ProblemData {
    
    private final FreeSpace space;
    private final List<Piece> pieces;

    public ProblemData(FreeSpace space, List<Piece> pieces) {
        this.space = space;
        this.pieces = pieces;
    }
    
    public FreeSpace freeSpace() {
        return space;
    }
    
    public Collection<Piece> availablePieces() {
        return Collections.unmodifiableList(pieces);
    }
}
