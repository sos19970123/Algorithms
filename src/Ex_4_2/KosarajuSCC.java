package Ex_4_2;

public class KosarajuSCC {
    private boolean[] marked;    //已访问过的顶点
    private int[] id;            //强连通分量的标识符
    private int count;           //强连通分量的数量

    public KosarajuSCC(Digraph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());//建立一个原图G的反向图Gr
        for(int s : order.reversePost())                         //对方向图Gr进行逆后序排序
            if(!marked[s]){
                dfs(G,s);
                count++;
            }
    }
    private void dfs(Digraph G, int v){
        marked[v] = true;
        id[v] = count;
        for(int w : G.adj(v))
            if(!marked[v])
                dfs(G, w);
    }
    public boolean stronglyConnected(int v, int w){
        return id[v] == id[w];
    }
    public int id(int v){
        return id[v];
    }
    public int count(){
        return count;
    }
}
