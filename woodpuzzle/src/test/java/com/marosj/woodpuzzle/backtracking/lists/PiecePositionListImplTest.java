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
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author marosj
 */
public class PiecePositionListImplTest {

    private final Position p1 = new Position((byte) 0, (byte) 0, (byte) 0);
    private final Position p2 = new Position((byte) 0, (byte) 0, (byte) 1);
    private final Position p3 = new Position((byte) 0, (byte) 0, (byte) -1);
    
    private final Position p4 = new Position((byte) -1, (byte) 1, (byte) 0);
    private final Position p5 = new Position((byte) -1, (byte) 0, (byte) 0);
    
    private final Position p6 = new Position((byte) 1, (byte) -1, (byte) 0);
    private final Position p7 = new Position((byte) 1, (byte) 0, (byte) 1);

    @Test
    public void testPositions() {
        PiecePositionListImpl instance = new PiecePositionListImpl(new Position[] {p1, p2, p3, p4, p5, p6, p7});
        Position[] expResult = {p5, p4, p3, p1, p2, p6, p7};
        Position[] result = instance.positions();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testIntersect() {
        PiecePosition another = new PiecePositionListImpl(new Position[] {p6});
        PiecePositionListImpl instance = new PiecePositionListImpl(new Position[] {p1, p2, p3, p4, p5, p6, p7});
        assertTrue(instance.intersect(instance));
        assertTrue(instance.intersect(another));
        assertTrue(another.intersect(instance));
    }

    @Test
    public void testIntersect2() {
        PiecePositionListImpl instance = new PiecePositionListImpl(new Position[] {p1, p2, p3, p4, p5, p6, p7});
        for (Position position : instance.positions()) {
            PiecePositionListImpl another = new PiecePositionListImpl(new Position[] {position});
            assertTrue(another.intersect(instance));
            assertTrue(instance.intersect(another));
        }
    }

    @Test
    public void testIntersect3() {
        PiecePosition another = new PiecePositionListImpl(new Position[] {new Position((byte) 2, (byte)0, (byte)0)});
        PiecePositionListImpl instance = new PiecePositionListImpl(new Position[] {p1, p2, p3, p4, p5, p6, p7});
        assertFalse(instance.intersect(another));
        assertFalse(another.intersect(instance));
    }
    
}
