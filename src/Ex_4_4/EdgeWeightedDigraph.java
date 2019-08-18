package Ex_4_4;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class  EdgeWeightedDigraph {                 //加权有向图的数据类型
    private  int V;
    private  int E;
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V ;v++ )
            adj[v] = new Bag<DirectedEdge>();
    }
    public EdgeWeightedDigraph(In in){

    }
    public int V(){ return V;}
    public int E(){ return E;}
    public void addEdge(DirectedEdge e){
        int v = e.from(), w = e.to();
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }
    public Iterable<DirectedEdge> adj(int v){
        return adj[v];
    }
    public Iterable<DirectedEdge> edges() {
     Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
     for (int v = 0; v < V; v++)
        for (DirectedEdge e : adj[v])
            bag.add(e);
        return bag;
    }
}
