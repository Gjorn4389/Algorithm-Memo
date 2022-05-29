package graph.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 最短路算法
 * <p/>
 * 朴素实现: dijkstra1
 * <p/>
 * 优先队列: dijkstra2
 */
public class Dijkstra {
    /**
     * @param n     图中有n个点, 节点 (1 - n)
     * @param edges 无向边的权值, graph[e[0]][e[1]] = e[2]
     * @param start 起点
     * @return 从起点到每个点的最小距离
     */
    public int[] dijkstra1(int n, List<int[]> edges, int start) {
        // 通过边构建图
        int[][] graph = new int[n + 1][n + 1];
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = edge[2];
            graph[edge[1]][edge[0]] = edge[2];
        }
        // 初始化 所有节点没有访问 end
        boolean[] visit = new boolean[n + 1];
        // 初始化，每个点到终点的最小距离
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // 遍历最小边
        for (int i = 1; i <= n; i++) {
            // 找到 未访问节点中 离 start 最近的节点
            int k = 1;
            int min = Integer.MAX_VALUE;
            for (int j = 1; j <= n; j++) {
                if (!visit[j] && dist[j] < min) {
                    k = j;
                    min = dist[j];
                }
            }
            // 访问 节点k
            visit[k] = true;
            for (int j = 1; j <= n; j++) {
                // 没有访问过 节点j && 节点j和节点k连通 && 走这条路 离start 比较近
                if (!visit[j] && graph[j][k] > 0 && dist[j] > graph[j][k] + dist[k]) {
                    dist[j] = graph[j][k] + dist[k];
                }
            }
        }
        return dist;
    }

    public int[] dijkstra2(int n, List<int[]> edges, int start) {
        // 通过边构建图
        int[][] graph = new int[n + 1][n + 1];
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = edge[2];
            graph[edge[1]][edge[0]] = edge[2];
        }
        // 初始化 所有节点没有访问 end
        boolean[] visit = new boolean[n + 1];
        // 初始化，每个点到终点的最小距离
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // item := [start, idx, dist[idx]]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        // 找到离start最近的点
        pq.add(new int[]{start, start, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int idx = cur[1];
            if (visit[idx]) {
                continue;
            }
            // 访问到 节点idx
            visit[idx] = true;
            for (int i = 1; i <= n; i++) {
                // idx 到 i节点 可达 && 这条路径 更近
                if (graph[idx][i] > 0 && dist[i] > dist[idx] + graph[idx][i]) {
                    dist[i] = dist[idx] + graph[idx][i];
                    pq.add(new int[]{start, i, dist[i]});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int start = 1, n = 4;
        List<int[]> edges = new ArrayList<>();
        edges.add(new int[]{1, 2, 2});
        edges.add(new int[]{2, 3, 2});
        edges.add(new int[]{2, 4, 1});
        edges.add(new int[]{1, 3, 5});
        edges.add(new int[]{3, 4, 3});
        edges.add(new int[]{1, 4, 4});
        Dijkstra dijkstra = new Dijkstra();
        int[] ans = dijkstra.dijkstra1(n, edges, start);
        for (int i = 1; i <= n; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();
    }
}
