package Tests;
import Model.Grid;
import org.junit.*;
import static org.junit.Assert.*;

public class ModelTests {

    /**
     * Passes if creating a grid with an invalid difficulty throws an AssertionError
     */
    @Test
    public void testInvalidDifficultyThrowsAssertion() {
        try {
            new Grid(5);
            fail();
        } catch (Error e) {
            assert(e instanceof AssertionError);
        }
    }

    @Test
    public void testGridIsPopulated() {
        Grid grid = new Grid(1);

        for (int i = 0; i < grid.size; i++) {
            for (int j = 0; j < grid.size; j++) {
                assert (grid.grid[j][i] != null);
            }
        }
    }

    @Test
    public void testCountsBombNeighbours() {
        Grid grid = new Grid(2);

        for (Grid.Cell[] c : grid.grid) {
            for (Grid.Cell cell : c) {
                System.out.println(cell.toString());
            }
        }
    }
}
