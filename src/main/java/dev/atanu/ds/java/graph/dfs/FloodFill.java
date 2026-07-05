package dev.atanu.ds.java.graph.dfs;

/**
 * https://leetcode.com/problems/flood-fill/
 *
 */
public class FloodFill {

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        dfs(image, sr, sc, image[sr][sc], color);
        return image;
    }

    public void dfs(int[][] image, int sr, int sc, int originalColor, int color) {
        if(sr < 0 || sr >= image.length
                || sc < 0 || sc >= image[0].length
                || image[sr][sc] != originalColor
                || image[sr][sc] == color) {
            return;
        }

        image[sr][sc] = color;

        dfs(image, sr + 1, sc, originalColor, color);
        dfs(image, sr, sc + 1, originalColor, color);
        dfs(image, sr - 1, sc, originalColor, color);
        dfs(image, sr, sc - 1, originalColor, color);
    }

}
