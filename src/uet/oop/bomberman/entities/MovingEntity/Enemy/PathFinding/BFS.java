package uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    pair[] path = new pair[4];
    String s = "ULRD";
    String res = "";
    pair start;
    pair end;
    int n, m, desX, dexY;
    int[][] a = new int[50][50];
    int[][] pre = new int[50][50];

    public BFS(int n, int m, int startX, int startY, int endX, int endY, int[][] a) {
        path[0] = new pair(-1, 0);
        path[1] = new pair(0, -1);
        path[2] = new pair(0, 1);
        path[3] = new pair(1, 0);
        this.n = n;
        this.m = m;
        this.a = a;
        this.desX = endX;
        this.dexY = endY;
        start = new pair(startX, startY);
        end = new pair(endX, endY);
        if (bfs(start.fi, start.se)) {
            int i = end.fi, j = end.se;
            while (!(i == start.fi && j == start.se)) {
                int k = pre[i][j];
                res += s.charAt(k);
                i = i - path[k].fi;
                j = j - path[k].se;
            }
        }
    }

    Boolean bfs(int i, int j) {
        Queue q = new LinkedList<pair>();
        q.add(new pair(i, j));
        a[i][j] = 1;
        while (q.size() != 0) {
            pair top = (pair) q.remove();
            i = top.fi;
            j = top.se;
            for (int k = 0; k < 4; k++) {
                int i1 = i + path[k].fi, j1 = j + path[k].se;
                if (i1 >= 0 && i1 < n && j1 >= 0 && j1 < m && a[i1][j1] != 1) {
                    pre[i1][j1] = k;
                    if (i1 == desX && j1 == dexY) {
                        return true;
                    }
                    a[i1][j1] = 1;
                    q.add(new pair(i1, j1));
                }
            }
        }
        return false;
    }

    public String getDirection() {
        return res;
    }
}
