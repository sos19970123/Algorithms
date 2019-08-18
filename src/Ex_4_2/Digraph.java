package Ex_4_2;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Digraph {                     //建立有向图
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Digraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Integer>();
    }
    public int V() {return V;}
    public int E() {return E;}

    public void addEdge (int v, int w){     //在有向图里添加一个边v → w
        adj[v].add(w);
        E++;
    }
    public Iterable<Integer> adj(int v){   //由v指出的边所链接的所有顶点
        return adj[v];
    }
    public Digraph reverse(){                //该图的反向图
        Digraph R  = new Digraph(V);       //建立有向图的一个副本
        for (int v = 0; v < V; v++)
            for(int w : adj(v))
                R.addEdge(w, v);          //将方向反转，加入副本图R
        return R;
    }
}
