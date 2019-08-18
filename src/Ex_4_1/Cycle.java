package Ex_4_1;

public class Cycle {                               //判断G是否是无环图
    private boolean[] marked;
    private boolean hasCycle;
    public Cycle(Graph G){
        marked = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++)
            if(!marked[s])
                dfs(G,s,s);
    }
    private void dfs(Graph G, int v, int u){
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G,w,v);
            else if (w != u)                  ///若w被标记过，那么若无环，w必然和父节点相同，否则就是有环
                hasCycle = true;
    }
    public boolean hasCycle(){
        return hasCycle;
    }
}
