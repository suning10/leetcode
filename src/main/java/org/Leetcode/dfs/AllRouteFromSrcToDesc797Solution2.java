package org.Leetcode.dfs;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * All Paths From Source to Target
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1,
 * find all possible paths from node 0 to node n - 1 and return them in any order.
 *
 * The graph is given as follows: graph[i] is a list of all nodes you can
 * visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
 */
public class AllRouteFromSrcToDesc797Solution2 {

    /**
     *DAG, directed, acylic(no circle)
     * onPath(to avoid duplicate path)
     * visited:(to avoid traverse same node twice)
     * here, no circle (DAG) no need to use visited and onPath
     */
    List<Integer> res = new LinkedList<>();
    // 记录当前路径组成的数字的值
    int track = 0;
    // 记录当前数字的位数
    int digit = 0;

    public int[] numsSameConsecDiff(int n, int k) {
        backtrack(n, k);
        // Java 需要把 List<Integer> 转成 int[]
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }

    // 回溯算法核心函数
    void backtrack(int n, int k) {
        // base case，到达叶子节点
        if (digit == n) {
            // 找到一个合法的 n 位数
            res.add(track);
            return;
        }

        // 回溯算法标准框架
        for (int i = 0; i <= 9; i++) {
            // 本题的剪枝逻辑 1，第一个数字不能是 0
            if (digit == 0 && i == 0) continue;
            // 本题的剪枝逻辑 2，相邻两个数字的差的绝对值必须等于 k
            if (digit > 0 && Math.abs(i - track % 10) != k) continue;

            // 做选择，在 track 尾部追加数字 i
            digit++;
            track = 10 * track + i;
            // 进入下一层回溯树
            backtrack(n, k);
            // 取消选择，删除 track 尾部数字
            track = track / 10;
            digit--;
        }
    }
}
