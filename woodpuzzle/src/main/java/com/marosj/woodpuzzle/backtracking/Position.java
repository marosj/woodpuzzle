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

import javax.annotation.concurrent.Immutable;

/**
 * x,y,z coordinates of position in cube (range 0..3)
 *
 * @author marosj
 */
@Immutable
public class Position {

    private final byte level;
    private final byte row;
    private final byte column;

    public Position(byte level, byte row, byte column) {
        this.level = level;
        this.row = row;
        this.column = column;
    }
    
    public byte getLevel() {
        return level;
    }

    public byte getRow() {
        return row;
    }

    public byte getColumn() {
        return column;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.level;
        hash = 41 * hash + this.row;
        hash = 41 * hash + this.column;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.level != other.level) {
            return false;
        }
        if (this.row != other.row) {
            return false;
        }
        if (this.column != other.column) {
            return false;
        }
        return true;
    }

    
}
