package Ex_4_3;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;


public class EdgeWeightedGraph { //加权无向图
    private  int V;         //顶点总数
    private  int E;              //边的总数
    private Bag<Edge>[] adj;     //邻接表

    public EdgeWeightedGraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V ;v++ )
            adj[v] = new Bag<Edge>();//每一个顶点连接的顶点都放在一个背包里
    }
    public EdgeWeightedGraph(In in){

    }
    public int V(){ return V;}
    public int E(){ return E;}
    public void addEdge(Edge e){
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }
    public Iterable<Edge> adj(int v){
        return adj[v];
    }
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < V; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
                // add only one copy of each self loop (self loops will be consecutive)
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }
}
