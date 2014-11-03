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

import com.marosj.woodpuzzle.backtracking.FreeSpace;
import com.marosj.woodpuzzle.backtracking.PiecePosition;
import com.marosj.woodpuzzle.backtracking.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 *
 * @author marosj
 */
@Immutable
public class FreeSpaceImpl extends PiecePositionListImpl implements FreeSpace {

    public FreeSpaceImpl(Position[] positions) {
        super(positions);
    }

    @Override
    public boolean canAdd(PiecePosition another) {
        for (Position position : another.positions()) {
            if (!this.intersect(position)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public FreeSpace add(PiecePosition another) {
        List<Position> newPos = new ArrayList<>(Arrays.asList(positions()));
        newPos.removeAll(Arrays.asList(another.positions()));
        return new FreeSpaceImpl(newPos.toArray(new Position[newPos.size()]));
    }

}
