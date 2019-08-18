package Ex_4_1;

public class CC {                               //查找连通分量
    private boolean[] marked;
    private int[] id;                           //以顶点为索引的数组，将同一连通分量的顶点和连通标识符关联起来
    private int count;                          //连通分量数

    public CC(Graph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for(int s = 0; s < G.V(); s++)
            if(!marked[s]){
                dfs(G,s);
                count++;                     //当dfs在一个连通分量内递归结束后，连通分量数加一
            }
    }
    private void dfs(Graph G, int v){
        marked[v] = true;
        id[v] = count;                      //在一个连通分量内递归时，将顶点id[v]赋值为当前连通分量的序号count
        for(int w : G.adj(v))
            if(!marked[v])
                dfs(G, w);
    }
    public boolean connected(int                                                                          v, int w){
        return id[v] == id[w];             //当顶点的id的值相等时，即为同一个count，可判断它们在同一个连通分量内
    }
    public int id(int v){
        return id[v];
    }
    public int count(){
        return count;
    }
}
