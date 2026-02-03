package org.Leetcode.dfs;

public class Island {

    /**
     *     Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
     *
     *     An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
     *     You may assume all four edges of the grid are all surrounded by water.
     * }
     */
    public int numIslands(char[][] grid){

        /**
         * 1. find a land
         * 2. flood the land next to it (use DFS)
         * 3. everytime find a land + 1 = # of islands
         */
        int x = grid.length;
        int y = grid[0].length;
        int res = 0;
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                if(grid[i][j] == '1'){
                    dfs(grid,i,j);
                    res++;
                }
            }
        }

        return res;
    }

    /**
     * flood grid[x,y] and the cell surrounded
     */
    void dfs(char[][] grid, int x, int y){
        //base case, out of bound
        if(x >= grid.length || x < 0 || y >= grid[0].length || y < 0) return;
        if(grid[x][y] == '0') return; //already water

        grid[x][y] = '0'; // flood cell
        dfs(grid,x-1,y) ;// left
        dfs(grid,x+1,y) ;// right
        dfs(grid,x,y-1) ;// down
        dfs(grid,x,y+1) ;// up

    }

    /**
     *
     The area of an island is the number of cells with a value 1 in the island.

     Return the maximum area of an island in grid. If there is no island, return 0.
     */
    public int maxAreaOfIsland(int[][] grid){
        /**
         * everytime flood a island, return the area
         */

        int res = -1;
        int x =grid.length;
        int y = grid[0].length;
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                res = Math.max(res,dfs(grid,i,j));
            }
        }

        return res;
    }

    // flood the grid[x][y] and return the area
    // area = the area up + down + left + right + itself(1)
    int dfs(int[][] grid, int x, int y){
        //base case, out of bound
        if(x >= grid.length || x < 0 || y >= grid[0].length || y < 0) return 0;
        if(grid[x][y] == 0) return 0; //already water

        grid[x][y] = 0; // flood cell
        return
        dfs(grid,x-1,y) +// left
        dfs(grid,x+1,y) +// right
        dfs(grid,x,y-1) +// down
        dfs(grid,x,y+1) + 1;// up
    }


    //check # of islands in grid2 is a subisland in grid1
    public int countSubIslands(int[][] grid1, int[][] grid2){
        /**
         * how to make sure island in grid2 can be made up using grid1
         * think about what island can't
         * cell in grid2 == 1 where grid1 == 0; => flood those in grid2
         * the cell rest can be make by grid 1
         */

        int res = 0;
        int x =grid2.length;
        int y = grid2[0].length;
        //logic of flood grid2 where grid1 is water
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                if(grid1[i][j] == 0 && grid2[i][j] == 1) dfsSub(grid2,i,j);
            }
        }

        //now calculate # of island in grid2
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                if( grid2[i][j] == 1) {
                    res++;
                    dfsSub(grid2,i,j);
                }
            }
        }

        return res;
    }

    void dfsSub(int[][] grid, int x, int y){
        //base case, out of bound
        if(x >= grid.length || x < 0 || y >= grid[0].length || y < 0) return;
        if(grid[x][y] == 0) return; //already water

        grid[x][y] = 0; // flood cell

        dfsSub(grid,x-1,y);// left
        dfsSub(grid,x+1,y);// right
        dfsSub(grid,x,y-1);// down
        dfsSub(grid,x,y+1) ;// up
    }


}