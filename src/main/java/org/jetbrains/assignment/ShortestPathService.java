package org.jetbrains.assignment;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ShortestPathService {

    public List<Location> getShortestPath(List<Location> locations) {
        List<Location> shortestPath = new ArrayList<>(locations);
        double shortestDistance = calculateTotalDistance(locations);

        // Generate all permutations of the locations
        List<List<Location>> permutations = generatePermutations(locations);

        // Find the permutation with the shortest total distance
        for (List<Location> permutation : permutations) {
            double distance = calculateTotalDistance(permutation);
            if (distance < shortestDistance) {
                shortestDistance = distance;
                shortestPath = permutation;
            }
            System.out.println("permutation: " + permutation + " distance: " + distance);
        }

        return shortestPath;
    }

    private List<List<Location>> generatePermutations(List<Location> locations) {
        if (locations.size() == 1) {
            return Collections.singletonList(locations);
        }

        List<List<Location>> permutations = new ArrayList<>();

        for (int i = 0; i < locations.size(); i++) {
            Location location = locations.get(i);

            List<Location> remainingLocations = new ArrayList<>(locations);
            remainingLocations.remove(i);

            for (List<Location> remainingPermutation : generatePermutations(remainingLocations)) {
                List<Location> permutation = new ArrayList<>();
                permutation.add(location);
                permutation.addAll(remainingPermutation);
                permutations.add(permutation);
            }
        }

        return permutations;
    }

    private double calculateTotalDistance(List<Location> locations) {
        double totalDistance = 0;

        for (int i = 0; i < locations.size() - 1; i++) {
            totalDistance += calculateDistance(locations.get(i), locations.get(i + 1));
        }

        return totalDistance;
    }

    private double calculateDistance(Location location1, Location location2) {
        return Math.sqrt(Math.pow(location2.getX() - location1.getX(), 2) + Math.pow(location2.getY() - location1.getY(), 2));
    }

}
