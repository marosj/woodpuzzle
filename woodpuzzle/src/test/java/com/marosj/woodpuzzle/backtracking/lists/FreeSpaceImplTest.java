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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author marosj
 */
public class FreeSpaceImplTest {

    @Test
    public void test1() {
        FreeSpaceImpl space = new FreeSpaceImpl(new Position[]{
            new Position((byte) 0, (byte) 0, (byte) 0),
            new Position((byte) 0, (byte) 0, (byte) 1),
            new Position((byte) 0, (byte) 0, (byte) 2)
        });

        PiecePosition position = new PiecePositionListImpl(new Position[]{
            new Position((byte) 0, (byte) 0, (byte) 0),
            new Position((byte) 0, (byte) 0, (byte) 1)
        });

        assertTrue(space.canAdd(position));
    }

    @Test
    public void test2() {
        FreeSpaceImpl space = new FreeSpaceImpl(new Position[]{
            new Position((byte) 0, (byte) 0, (byte) 0),
            new Position((byte) 0, (byte) 0, (byte) 1),
            new Position((byte) 0, (byte) 0, (byte) 2)
        });

        PiecePosition position = new PiecePositionListImpl(new Position[]{
            new Position((byte) 0, (byte) 0, (byte) 0),
            new Position((byte) 0, (byte) 0, (byte) 3)
        });

        assertFalse(space.canAdd(position));
    }

    @Test
    public void testEmptySpace() {
        FreeSpaceImpl space = new FreeSpaceImpl(new Position[]{});

        PiecePosition position = new PiecePositionListImpl(new Position[]{
            new Position((byte) 0, (byte) 0, (byte) 0),
            new Position((byte) 0, (byte) 0, (byte) 1)
        });

        assertFalse(space.canAdd(position));
    }

    @Test
    public void testOutOfSpace() {
        FreeSpaceImpl space = new FreeSpaceImpl(new Position[]{
            new Position((byte) 0, (byte) 0, (byte) 0),
            new Position((byte) 0, (byte) 0, (byte) 1),
            new Position((byte) 0, (byte) 0, (byte) 2)
        });

        PiecePosition position = new PiecePositionListImpl(new Position[]{
            new Position((byte) 1, (byte) 0, (byte) 0),
            new Position((byte) 1, (byte) 0, (byte) 1)
        });

        assertFalse(space.canAdd(position));
    }

    @Test
    public void testAdd1() {
        FreeSpaceImpl space = new FreeSpaceImpl(new Position[]{
            new Position((byte) 0, (byte) 0, (byte) 0),
            new Position((byte) 0, (byte) 0, (byte) 1),
            new Position((byte) 0, (byte) 0, (byte) 2)
        });

        PiecePosition position = new PiecePositionListImpl(new Position[]{
            new Position((byte) 0, (byte) 0, (byte) 0),
            new Position((byte) 0, (byte) 0, (byte) 2)
        });

        assertTrue(space.canAdd(position));
        FreeSpace newSpace = space.add(position);
        assertFalse(newSpace.canAdd(position));
    }

    @Test
    public void testAdd2() {
        FreeSpaceImpl space = new FreeSpaceImpl(new Position[]{
            new Position((byte) 0, (byte) 0, (byte) 0),
            new Position((byte) 0, (byte) 0, (byte) 1),
            new Position((byte) 0, (byte) 0, (byte) 2)
        });

        PiecePosition position = new PiecePositionListImpl(new Position[]{
            new Position((byte) 0, (byte) 0, (byte) 0),
            new Position((byte) 0, (byte) 0, (byte) 4) //out of space
        });

        PiecePosition smallerPosition = new PiecePositionListImpl(new Position[]{
            new Position((byte) 0, (byte) 0, (byte) 0)
        });

        assertTrue(space.canAdd(smallerPosition));
        assertFalse(space.canAdd(position));

        FreeSpace newSpace = space.add(position);
        assertFalse(newSpace.canAdd(smallerPosition));
        assertFalse(newSpace.canAdd(position));
    }

    @Test
    public void testAddEmpty() {
        FreeSpaceImpl space = new FreeSpaceImpl(new Position[]{
            new Position((byte) 0, (byte) 0, (byte) 0),
            new Position((byte) 0, (byte) 0, (byte) 1),
            new Position((byte) 0, (byte) 0, (byte) 2)
        });

        PiecePosition position = new PiecePositionListImpl(new Position[]{});

        assertEquals(3, space.positions().length);
        assertTrue(space.canAdd(position));

        FreeSpace newSpace = space.add(position);

        assertEquals(3, newSpace.positions().length);
        assertTrue(newSpace.canAdd(position));
    }

    @Test
    public void testAddIntoEmpty() {
        FreeSpaceImpl space = new FreeSpaceImpl(new Position[]{});

        PiecePosition position = new PiecePositionListImpl(new Position[]{
            new Position((byte) 0, (byte) 0, (byte) 0),
            new Position((byte) 0, (byte) 0, (byte) 2)
        });

        assertFalse(space.canAdd(position));

        FreeSpace newSpace = space.add(position);
        assertFalse(newSpace.canAdd(position));
        assertEquals(0, newSpace.positions().length);
    }
}
