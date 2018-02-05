package Model;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Rodney Cruden-Powell
 *
 * This class represents the grid of Cells
 */
public class Grid {

    public Cell[][] grid;
    public int size;

    /**
     * Creates a grid of cells
     * @param difficulty - how difficult the game is, 1 = easy, 2 = normal, 3 = hard
     *                   used to determine how many mines are created
     */
    public Grid(int difficulty) {
        assert difficulty == 1 || difficulty == 2 || difficulty == 3 : "not a valid difficulty, must be 1, 2 or 3";

        updateSize(difficulty);
        this.grid = new Cell[size][size];

        // fill out the grid based on size and difficulty
        for (int row = 0; row < size-1; row++) {
            for (int col = 0; col < size-1; col++) {
                grid[col][row] = isBomb(difficulty) ? new Bomb(row, col) : new Cell(row, col);
            }
        }
    }

    private boolean isBomb(int difficulty) {
        //TODO: Test and update probabilities
        if (difficulty == 1) return new Random().nextInt(10)==0;
        if (difficulty == 2) return new Random().nextInt(10)==0;
        if (difficulty == 3) return new Random().nextInt(10)==0;
        return false;
    }

    private void updateSize(int difficulty) {
        //TODO: Temporary
        if (difficulty == 1) this.size = 10;
        if (difficulty == 2) this.size = 30;
        if (difficulty == 3) this.size = 50;
    }

    public class Cell {
        private int row;
        private int col;
        private ArrayList<Cell> neighbours = new ArrayList<>();
        private boolean isRevealed;
        private int numberOfBombNeighbours;

        /**
         * Default constructor for NullCell
         */
        public Cell() {}

        /**
         * Constructs a new cell in the game grid
         * @param row
         * @param col
         */
        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
            updateNeighbours();
            this.numberOfBombNeighbours = numberOfBombNeighbours();
            this.isRevealed = false;
        }

        /**
         * Reveals this cell (after being clicked on), allowing it's contents to be viewed in the GUI
         */
        public void reveal() {
            this.isRevealed = true;
        }

        @Override
        public String toString() { return this instanceof Bomb ? "Bomb at " + row + ", " + col : "Empty cell at " + row + ", " + col; }

        private int numberOfBombNeighbours() {
            int n = 0;
            for (Cell cell : neighbours) if (cell instanceof Bomb) n++;
            return n;
        }

        private void updateNeighbours() {
            // put NullCells in the out of bounds areas

            neighbours.add(grid[col-1][row]);
            neighbours.add(grid[col+1][row]);
            neighbours.add(grid[col][row-1]);
            neighbours.add(grid[col][row+1]);
            neighbours.add(grid[col-1][row+1]);
            neighbours.add(grid[col+1][row-1]);
            neighbours.add(grid[col+1][row+1]);
            neighbours.add(grid[col-1][row-1]);
        }
    }

    /**
     * Bomb class
     */
    private class Bomb extends Cell {
        public Bomb(int row, int col) { super(row, col); }
    }

    private class NullCell extends Cell {
        @Override
        public void reveal() { throw new UnsupportedOperationException("Cannot reveal NullCell"); }

        @Override
        public String toString() { return "Null Cell"; }
    }
}
