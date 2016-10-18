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
package com.marosj.woodpuzzle;

import com.marosj.woodpuzzle.backtracking.Piece;
import com.marosj.woodpuzzle.backtracking.PiecePosition;
import com.marosj.woodpuzzle.backtracking.SolutionData;
import com.marosj.woodpuzzle.backtracking.Solver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author marosj
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    
    public static void main(String[] args) {
        LOGGER.debug("Started");
        Cube cube = new Cube();
        LOGGER.debug("Cube problem generated.");
        SolutionData solutionData =new Solver().solve(cube.toProblemData());
        int i = 0;
        for (Piece piece : solutionData.pieces()) {
            PiecePosition position = solutionData.positions().get(i);
            LOGGER.info("Piece: {} has position: {}", piece.name(), position);
            i++;
        }
        LOGGER.debug("Ended");
    }
}
