package org.jetbrains.assignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RobotControllerTest {

    private final RobotController robotController = new RobotController();;

    @Test
    public void testGetLocations() {
        List<Movement> movements = Arrays.asList(
                new Movement(Direction.EAST, 1),
                new Movement(Direction.NORTH, 3),
                new Movement(Direction.EAST, 3),
                new Movement(Direction.SOUTH, 5),
                new Movement(Direction.WEST, 2)
        );

        List<Location> expectedLocations = Arrays.asList(
                new Location(0, 0),
                new Location(1, 0),
                new Location(1, 3),
                new Location(4, 3),
                new Location(4, -2),
                new Location(2, -2)
        );

        List<Location> actualLocations = robotController.getLocations(movements);

        assertEquals(expectedLocations, actualLocations);
    }

    @Test
    public void testGetMoves() {
        List<Location> locations = Arrays.asList(
                new Location(0, 0),
                new Location(1, 0),
                new Location(1, 3),
                new Location(0, 3),
                new Location(0, 0)
        );

        List<Movement> expectedMovements = Arrays.asList(
                new Movement(Direction.EAST, 1),
                new Movement(Direction.NORTH, 3),
                new Movement(Direction.WEST, 1),
                new Movement(Direction.SOUTH, 3)
        );

        List<Movement> actualMovements = robotController.getMoves(locations);

        assertEquals(expectedMovements, actualMovements);
    }

    @Test
    public void testGetLocationsWithEmptyMovements() {
        List<Movement> movements = Collections.emptyList();

        List<Location> expectedLocations = Collections.singletonList(new Location(0, 0));

        List<Location> actualLocations = robotController.getLocations(movements);

        assertEquals(expectedLocations, actualLocations);
    }

    @Test
    public void testGetMovesWithEmptyLocations() {
        List<Location> locations = Collections.emptyList();

        List<Movement> expectedMovements = Collections.emptyList();

        List<Movement> actualMovements = robotController.getMoves(locations);

        assertEquals(expectedMovements, actualMovements);
    }

    @Test
    public void testGetLocationsWithInvalidDirections() {
        List<Movement> movements = Arrays.asList(
                new Movement(null, 1),
                new Movement(Direction.NORTH, 3),
                new Movement(Direction.EAST, 3),
                new Movement(Direction.SOUTH, 5),
                new Movement(Direction.WEST, 2)
        );

        assertThrows(IllegalArgumentException.class, () -> robotController.getLocations(movements));
    }

    @Test
    public void testGetMovesWithInvalidLocations() {
        List<Location> locations = Arrays.asList(
                new Location(0, 0),
                new Location(1, 0),
                new Location(1, 3),
                new Location(0, 3),
                new Location(-1, 0)
        );

        assertThrows(IllegalArgumentException.class, () -> robotController.getMoves(locations));
    }

    @Test
    public void testGetMovesWithLocationShiftsRequiringMultipleMovements() {
        List<Location> locations = Arrays.asList(
                new Location(0, 0),
                new Location(3, 0),
                new Location(3, 3),
                new Location(0, 3),
                new Location(0, 0)
        );

        List<Movement> expectedMovements = Arrays.asList(
                new Movement(Direction.EAST, 3),
                new Movement(Direction.NORTH, 3),
                new Movement(Direction.WEST, 3),
                new Movement(Direction.SOUTH, 3)
        );

        List<Movement> actualMovements = robotController.getMoves(locations);

        assertEquals(expectedMovements, actualMovements);
    }
}