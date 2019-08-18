package Ex_4_2;

import java.util.Stack;

public class DirectedCycle {
    private boolean[] marked;
    private  int[] edgeTo;
    private Stack<Integer> cycle;                  //保存递归调用期间栈上的所有顶点，当找到一条边 v → w 且w在栈中时，就找到了一个环
    private boolean[] onStack;

    public DirectedCycle(Digraph G){
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v])
                dfs(G, v);
    }
    private void dfs(Digraph G, int v){
        onStack[v] = true;
        marked[v] =  true;
        for (int w : G.adj(v))
            if (this.hasCycle())
                return;
            else if (!marked[w]){
                edgeTo[w] = v;                         //保存在此顶点开始，本次递归所经过的所有顶点
                dfs(G, w);
            }
            else if (onStack[w]){                      //从调用开始设起点的onStack[v]为true，然后开始递归，直到调用到本次递归中已经经过的顶点（即在onStack为true
                cycle = new Stack<Integer>();          //建立一个cycle的栈，用于记录环的路径
                for (int x = v; x != w; x = edgeTo[x]) //将保存路径的edgeTo的点复制到cycle中
                    cycle.push(x);                     //从终点开始，倒着寻找起点，依次push进cycle
                cycle.push(w);                         // for循环到起点处终止，所以在循环结束后要把起点入栈，至此 一条完整的路径依次入栈
                cycle.push(v);
;            }
            onStack[v] = false;                        //在掉用dfs时先将onStack[v]设为true，调用结束之后设为false
    }
    public boolean hasCycle()
    { return cycle != null; }
    public Iterable<Integer> cycle()
    {return cycle;}
}
