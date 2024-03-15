package org.jetbrains.assignment;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RobotController {

    @PostMapping("/locations")
    public List<Location> getLocations(@RequestBody List<Movement> movements) {
        // Validate input
        movements.stream()
                .filter(movement -> movement.getDirection() == null || movement.getSteps() < 0)
                .findAny()
                .ifPresent(movement -> {
                    throw new IllegalArgumentException("Invalid movement: " + movement);
                });

        List<Location> locations = new ArrayList<>();
        Location currentLocation = new Location(0, 0);
        locations.add(new Location(currentLocation.getX(), currentLocation.getY())); // add initial location

        for (var movement : movements) {
            switch (movement.getDirection()) {
                case NORTH -> currentLocation.setY(currentLocation.getY() + movement.getSteps());
                case EAST -> currentLocation.setX(currentLocation.getX() + movement.getSteps());
                case SOUTH -> currentLocation.setY(currentLocation.getY() - movement.getSteps());
                case WEST -> currentLocation.setX(currentLocation.getX() - movement.getSteps());
            }
            locations.add(new Location(currentLocation.getX(), currentLocation.getY())); // add new location after move
        }

        return locations;
    }

    @PostMapping("/moves")
    public List<Movement> getMoves(@RequestBody List<Location> locations) {
        // Validate input
        locations.stream()
                .filter(location -> location.getX() < 0 || location.getY() < 0)
                .findAny()
                .ifPresent(location -> {
                    throw new IllegalArgumentException("Invalid location: " + location);
                });

        List<Movement> movements = new ArrayList<>();
        Location currentLocation = new Location(0, 0);
        for (var location : locations) {
            int dx = location.getX() - currentLocation.getX();
            int dy = location.getY() - currentLocation.getY();
            if (dx != 0) {
                movements.add(new Movement(dx > 0 ? Direction.EAST : Direction.WEST, Math.abs(dx)));
            }
            if (dy != 0) {
                movements.add(new Movement(dy > 0 ? Direction.NORTH : Direction.SOUTH, Math.abs(dy)));
            }
            currentLocation = location;
        }
        return movements;
    }
}