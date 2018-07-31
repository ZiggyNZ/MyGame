package Utilities.SearchAlgorithms;

import Model.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class AStarSearch {
    public static List<Tile> getRoute(Tile root, Tile goal, Tile[][] tiles) {
        if(root == null || goal == null) {
            throw new Error("Invalid root or goal node detected!\n" +
                    "root: " + root + ", goal:" + goal);
        }
        resetAlgorithmFields(tiles);

        List<Tile> visited = new ArrayList<>();
        PriorityQueue<Tile> queue = new PriorityQueue<>();
        Tile current = null;

        queue.add(root);
        while(!queue.isEmpty()) {
            current = queue.poll();
            visited.add(current);
            if(current == goal) {break;}

            for(Tile neighbour : current.neighbours) {
                double cost = current.costSoFar + getDistance(current, neighbour);
                if(((!visited.contains(neighbour) || cost < neighbour.costSoFar) && neighbour.traversable()) || neighbour == goal) {
                    if(!visited.contains(neighbour) && neighbour.costSoFar > 0 && cost > neighbour.costSoFar) {continue;}
                    neighbour.costSoFar = cost;
                    neighbour.heuristic = getDistance(neighbour, goal);
                    neighbour.parent = current;
                    if(queue.contains(neighbour)) {queue.remove(neighbour);} //Make sure there are no duplicates in the queue
                    queue.add(neighbour);
                }
            }
        }

        List<Tile> route = new ArrayList<>();
        if(current == goal){
            while(current.parent != null){
                route.add(current);
                current = current.parent;
            }
            route.add(current);
            Collections.reverse(route);
        }
        return route;
    }

    private static double getDistance(Tile first, Tile second){
        return Math.hypot(first.getXPosition() - second.getXPosition(),
                first.getYPosition() - second.getYPosition());
    }

    private static void resetAlgorithmFields(Tile[][] tiles) {
        for(Tile[] line : tiles) {
            for(Tile tile : line) {
                if(tile == null) {continue;}
                tile.parent = null;
                tile.heuristic = 0;
                tile.costSoFar = 0;
            }
        }
    }
}
