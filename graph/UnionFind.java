package graph;

import java.util.Arrays;

public class UnionFind {
    private int n;
    // 父亲节点
    private int[] father;
    // 连通分量大小
    private int[] size;
    // 联通分量数
    private int cnt;

    public UnionFind(int n) {
        this.n = n;
        this.cnt = n;
        this.father = new int[n];
        this.size = new int[n];
        Arrays.fill(size, 1);
        // 每个点的初始父亲为自己
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }

    public int find(int x) {
        if (father[x] == x) {
            return father[x];
        }
        // 压缩路径
        father[x] = find(father[x]);
        return father[x];
    }

    public boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return false;
        }
        // 选择节点多的作为父亲节点
        if (size[x] < size[y]) {
            int t = x;
            x = y;
            y = x;
        }
        father[y] = x;
        size[x] += size[y];
        cnt--;
        return true;
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
