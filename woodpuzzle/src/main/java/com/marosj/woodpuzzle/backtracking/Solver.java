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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Runs backtracking solving algorithm.
 * 
 * @author marosj
 */
public class Solver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Solver.class);

    public SolutionData solve(ProblemData problem) {
        SolutionData emptySolution = new SolutionDataImpl();
        ListIterator<Piece> pieceIt = new ArrayList<>(problem.availablePieces()).listIterator();
        return solve(pieceIt, problem.freeSpace(), emptySolution);
    }

    private SolutionData solve(ListIterator<Piece> availablePieces, FreeSpace space, SolutionData partialSolution) {
        if (!availablePieces.hasNext()) {
            LOGGER.info("No more pieces to add - solution found: {}", partialSolution);
            return partialSolution;
        } else {
            Piece piece = availablePieces.next();
            LOGGER.debug("Checking piece {}", piece);
            for (PiecePosition position : piece.availablePositions()) {
                if (space.canAdd(position)) {
                    LOGGER.debug("Adding position {}", position);
                    SolutionData newSolution = solve(availablePieces, space.add(position), partialSolution.addPiece(piece, position));
                    if (newSolution != null) {
                        return newSolution;
                    }
                }
            }
            LOGGER.debug("Backtracking from piece {}", piece);
            availablePieces.previous(); //backtrack
            return null;
        }
    }
}
