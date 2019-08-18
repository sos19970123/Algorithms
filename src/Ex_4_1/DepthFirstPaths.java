package Ex_4_1;

import java.util.Stack;

public class DepthFirstPaths {                        //深度优先搜索获取下一个顶点的是从最晚的加入的顶点开始
    private boolean[] marked;                         //标记这个顶点上调用过dfs（）了吗
    private int[] edgeTo;                             //从起点到一个顶点的已知路径上的最后一个顶点
    private final int s;                                //起点

    public DepthFirstPaths(Graph G, int s){
        marked = new boolean[G.V()];                 //创建数组，长度为该依附该顶点的所有路径
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }
    private void dfs(Graph G, int v){
        marked[v] = true;
        for(int w : G.adj(v))
            if (!marked[w]){
                edgeTo[w] = v;                       //若有标记为false，则该点存入edgeTo数组
                dfs(G, w);
            }
    }

    public boolean hasPathTo(int v){               //判定是否还有路（是否还有路径连接到未标记的顶点
        return marked[v];
    }
    public Iterable<Integer> pathTo(int v){       //查找路径
        if(!hasPathTo(v)) return null;            //如果没有路了就返回空
        Stack<Integer> path = new Stack<Integer>();//建立一个堆（先进后出
        for(int x = v;  x != s; x = edgeTo[x])     //？？
            path.push(x);
        path.push(s);
        return path;
    }
}
