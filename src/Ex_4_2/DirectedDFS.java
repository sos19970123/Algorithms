package Ex_4_2;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;

public class DirectedDFS {                                    //有向图的可达性
    private boolean[] marked;

    public  DirectedDFS(Digraph G, int s){                   //在G中找到从s可达的所有顶点
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public DirectedDFS(Digraph G, Iterable<Integer> sources){//在G中找到从sources中的所有顶点可达的所有
        marked = new boolean[G.V()];
        for (int s : sources)
            if (!marked[s])
                dfs(G,s);
    }
    private  void dfs(Digraph G, int v){
        marked[v] = true;
        for(int w : G.adj(v))
            if (!marked[v])
                dfs(G,w);

    }
    public boolean marked(int v){
        return marked[v];
    }
    public static void main(String[] args){
        Digraph G = new Digraph(new In(args[0]));

        Bag<Integer> sources =  new Bag<Integer>();
        for (int i = 1; i < args.length; i++)
            sources.add(Integer.parseInt(args[i]));         //parseInt：将字符串化为带符号的十进制整数
        DirectedDFS reachbale = new DirectedDFS(G, sources);

        for (int v = 0; v < G.V(); v++){
            if (reachbale.marked(v))
                StdOut.print(v +  " ");
        StdOut.println();
        }
    }
}
