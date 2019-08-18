package Ex_4_1;

import java.util.*;

public class BreadthFirstPaths {                 //广度优先搜索在获取下一个顶点时从最早加入的顶点开始
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstPaths(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }
    private void bfs(Graph G, int s){
        Queue<Integer> queue = new LinkedList<>(); //建立一个队列
        marked[s] = true;
        queue.add(s);
        while (!queue.isEmpty()){                //当队列不为空时
            int v = queue.poll();                //v为队列的第一个数据，并在队列中删除
            for (int w : G.adj(v))               //从依附在顶点v的所有路径遍历
                if (!marked[w]){                 //若没有被标记
                    edgeTo[w] = v;               //
                    marked[w] = true;
                    queue.add(w);               //将分支路径的所有未被标记的顶点加入队列
                }
        }
    }
    public boolean hasPathTo(int v){              //是否存在s到v的路径
        return marked[v];
    }
    public Iterable<Integer> pathTo(int v){       //查找s到v的路径，如果不存在就返回null
        if(!hasPathTo(v)) return null;            //如果没有路了就返回空
        Stack<Integer> path = new Stack<Integer>();//建立一个堆（先进后出
        for(int x = v;  x != s; x = edgeTo[x])     //？？
            path.push(x);
        path.push(s);
        return path;
    }
}
