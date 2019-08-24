package com.mylearning.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maze {
    public static List<Coordinate> searchMaze(List<List<Color>> maze, Coordinate s, Coordinate e) {
        List<Coordinate> path = new ArrayList<>();
        searchMazeHelper(maze, s, e, path);
        return path;
    }

    // Performs DFS to find a feasible path.
    private static boolean searchMazeHelper(List<List<Color>> maze, Coordinate cur, Coordinate e, List<Coordinate> path) {
        // Checks cur is within maze and is a white pixel.
        if (isValid(maze, cur)) {
            return false;
        }

        //Choose
        path.add(cur);
        maze.get(cur.x).set(cur.y, Color.BLACK); //mark it black, similar to visited
        if (cur.equals(e)) {
            return true;
        }

        //Explore
        for (Coordinate nextMove : Arrays.asList(new Coordinate(cur.x, cur.y + 1),
                new Coordinate(cur.x, cur.y - 1),
                new Coordinate(cur.x + 1, cur.y),
                new Coordinate(cur.x - 1, cur.y))) {
            if (searchMazeHelper(maze, nextMove, e, path)) {
                return true;
            }
        }
        //Unchoose
        // Cannot find a path, remove the entry added in path.add(cur).
        path.remove(path.size() - 1);
        return false;
    }

    private static boolean isValid(List<List<Color>> maze, Coordinate cur) {
        return cur.x < 0 || cur.x >= maze.size() || cur.y < 0 || cur.y >= maze.get(cur.x).size() || maze.get(cur.x).get(cur.y) != Color.WHITE;
    }

    public static class Coordinate {
        public int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Coordinate that = (Coordinate)o;
            if (x != that.x || y != that.y) {
                return false;
            }
            return true;
        }

        @Override public String toString() {
            return "Coordinate{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public enum Color { WHITE, BLACK }

    public static void main(String[] args) {
        List<List<Integer>> maze = new ArrayList<>();
        maze.add(Arrays.asList(0, 0, 1, 0));
        maze.add(Arrays.asList(0, 0, 0, 0));
        maze.add(Arrays.asList(0, 0, 0, 0));
        maze.add(Arrays.asList(0, 0, 1, 0));
        maze.add(Arrays.asList(1, 0, 0, 1));
        maze.add(Arrays.asList(0, 0, 0, 1));

        List<List<Color>> colored = new ArrayList<>();
        for (List<Integer> col : maze) {
            List<Color> tmp = new ArrayList<>();
            for (Integer i : col) {
                tmp.add(i == 0 ? Color.WHITE : Color.BLACK);
            }
            colored.add(tmp);
        }

        List<Coordinate> coordinates = searchMaze(colored, new Coordinate(2,1), new Coordinate(2,0)); //true
        coordinates.forEach(System.out::println);
    }
}
