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

import com.marosj.woodpuzzle.backtracking.lists.FreeSpaceImpl;
import com.marosj.woodpuzzle.backtracking.lists.PieceBuilder;
import java.util.Arrays;
import java.util.Iterator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author marosj
 */
public class SolverTest {

    @Test
    public void testOnePath() {
        FreeSpace space = new FreeSpaceImpl(new Position[]{
            new Position(0, 0, 0),
            new Position(0, 0, 1),
            new Position(0, 0, 2)
        });

        Piece pieceOne = new PieceBuilder("pieceOne").add(0, 0, 0).addPosition().create();
        Piece pieceTwo = new PieceBuilder("pieceTwo").add(0, 0, 1).addPosition().create();
        Piece pieceThree = new PieceBuilder("pieceThree").add(0, 0, 2).addPosition().create();

        ProblemData problem = new ProblemData(space, Arrays.asList(pieceOne, pieceTwo, pieceThree));

        SolutionData solution = new Solver().solve(problem);

        assertNotNull(solution);
        assertEquals(pieceOne, solution.pieces().get(0));
        assertEquals(pieceTwo, solution.pieces().get(1));
        assertEquals(pieceThree, solution.pieces().get(2));
    }

    @Test
    public void testBacktrack() {
        FreeSpace space = new FreeSpaceImpl(new Position[]{
            new Position(0, 0, 0),
            new Position(0, 0, 1),
            new Position(0, 0, 2)
        });

        Piece pieceOne = new PieceBuilder("pieceOne").add(0, 0, 0).addPosition().create();
        Piece pieceTwo = new PieceBuilder("pieceTwo").add(0, 0, 0).addPosition().add(0, 0, 1).addPosition().create();
        Piece pieceThree = new PieceBuilder("pieceThree").add(0, 0, 2).addPosition().create();

        ProblemData problem = new ProblemData(space, Arrays.asList(pieceOne, pieceTwo, pieceThree));

        SolutionData solution = new Solver().solve(problem);

        assertNotNull(solution);
        assertEquals(3, solution.pieces().size());
        assertEquals(pieceOne.availablePositions().iterator().next(), solution.positions().get(0));
        Iterator<PiecePosition> positionsOfPieceTwoIt = pieceTwo.availablePositions().iterator();
        positionsOfPieceTwoIt.next();
        assertEquals(positionsOfPieceTwoIt.next(), solution.positions().get(1));
        assertEquals(pieceThree.availablePositions().iterator().next(), solution.positions().get(2));
    }

    @Test
    public void testBacktrackDeeper() {
        FreeSpace space = new FreeSpaceImpl(new Position[]{
            new Position(0, 0, 0),
            new Position(0, 0, 1),
            new Position(0, 0, 2)
        });

        Piece pieceOne = new PieceBuilder("pieceOne").add(0, 0, 0).addPosition().add(0, 0, 1).addPosition().create();
        Piece pieceTwo = new PieceBuilder("pieceTwo").add(0, 0, 1).addPosition().add(0, 0, 2).addPosition().create();
        Piece pieceThree = new PieceBuilder("pieceThree").add(0, 0, 0).addPosition().create();

        ProblemData problem = new ProblemData(space, Arrays.asList(pieceOne, pieceTwo, pieceThree));

        SolutionData solution = new Solver().solve(problem);

        assertNotNull(solution);
        assertEquals(3, solution.pieces().size());
        
        Iterator<PiecePosition> positionsOfPieceOneIt = pieceOne.availablePositions().iterator();
        positionsOfPieceOneIt.next();
        assertEquals(positionsOfPieceOneIt.next(), solution.positions().get(0));
        
        Iterator<PiecePosition> positionsOfPieceTwoIt = pieceTwo.availablePositions().iterator();
        positionsOfPieceTwoIt.next();
        assertEquals(positionsOfPieceTwoIt.next(), solution.positions().get(1));
        
        assertEquals(pieceThree.availablePositions().iterator().next(), solution.positions().get(2));
    }

    @Test
    public void testNoSolution() {
        FreeSpace space = new FreeSpaceImpl(new Position[]{
            new Position(0, 0, 0),
            new Position(0, 0, 1),
            new Position(0, 0, 2)
        });

        Piece pieceOne = new PieceBuilder("pieceOne").add(0, 0, 0).addPosition().create();
        Piece pieceTwo = new PieceBuilder("pieceTwo").add(0, 0, 1).addPosition().create();
        Piece pieceThree = new PieceBuilder("pieceThree").add(0, 0, 1).addPosition().create();

        ProblemData problem = new ProblemData(space, Arrays.asList(pieceOne, pieceTwo, pieceThree));

        SolutionData solution = new Solver().solve(problem);

        assertNull(solution);
    }
}
