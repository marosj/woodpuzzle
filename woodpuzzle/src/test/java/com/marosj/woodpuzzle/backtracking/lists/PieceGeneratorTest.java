package com.marosj.woodpuzzle.backtracking.lists;

import com.marosj.woodpuzzle.backtracking.FreeSpace;
import com.marosj.woodpuzzle.backtracking.Piece;
import com.marosj.woodpuzzle.backtracking.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author marosj
 */
public class PieceGeneratorTest {

    @Test
    public void test1() {
        FreeSpace space = new FreeSpaceImpl(new Position[]{
                new Position(0, 0, 0),
                new Position(0, 0, 1),
                new Position(0, 1, 0),
                new Position(0, 2, 0),
                new Position(1, 0, 0)
        });

        PieceGenerator gen = new PieceGenerator("one");
        gen.add(0, 0, 0); //simplest one position

        Piece piece = gen.generate(space);

        Piece expected = new PieceBuilder("one")
                .add(0, 0, 0).addPosition()
                .add(0, 0, 1).addPosition()
                .add(0, 1, 0).addPosition()
                .add(0, 2, 0).addPosition()
                .add(1, 0, 0).addPosition()
                .create();

        assertEquals(expected, piece);
    }

    @Test
    public void test2() {
        FreeSpace space = new FreeSpaceImpl(new Position[]{
                new Position(0, 0, 0),
                new Position(0, 0, 1),
                new Position(0, 1, 0),
                new Position(0, 2, 0),
                new Position(1, 0, 0),
                new Position(1, 0, 1)
        });

        PieceGenerator gen = new PieceGenerator("one");
        gen.add(0, 0, 0).add(0, 0, 1);

        Piece piece = gen.generate(space);

        Piece expected = new PieceBuilder("one")
                .add(0, 0, 0).add(0, 0, 1).addPosition()
                .add(1, 0, 0).add(1, 0, 1).addPosition()
                .add(0, 0, 0).add(1, 0, 0).addPosition()
                .add(0, 0, 1).add(1, 0, 1).addPosition()
                .add(0, 0, 0).add(0, 1, 0).addPosition()
                .add(0, 1, 0).add(0, 2, 0).addPosition()
                .create();

        assertEquals(expected, piece);
    }

    @Test
    public void test3() {
        FreeSpace space = new FreeSpaceImpl(new Position[]{
                new Position(0, 0, 0),
                new Position(0, 0, 1),
                new Position(0, 1, 0),
                new Position(0, 2, 0),
                new Position(1, 0, 0),
                new Position(1, 0, 1)
        });

        PieceGenerator gen = new PieceGenerator("one");
        gen.add(0, 0, 0).add(0, 0, 1).add(0, 0, 2).add(0, 1, 2);

        Piece piece = gen.generate(space);

        Piece expected = new PieceBuilder("one")
                .add(0, 0, 0).add(0, 1, 0).add(0, 2, 0).add(1, 0, 0).addPosition()
                .add(0, 0, 0).add(0, 1, 0).add(0, 2, 0).add(0, 0, 1).addPosition()
                .create();

        assertEquals(expected, piece);
    }

}