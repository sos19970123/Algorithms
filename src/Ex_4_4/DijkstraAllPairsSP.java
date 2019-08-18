package Ex_4_4;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.DirectedEdge;

public class DijkstraAllPairsSP {   //求任意顶点对之间的最短路径
    private DijkstraSP[] all;

    DijkstraAllPairsSP(EdgeWeightedDigraph G){
        all = new DijkstraSP[G.V()];
        for (int v = 0; v < G.V(); v++)
            all[v] = new DijkstraSP(G, v);
    }
    Iterable<DirectedEdge> path(int s, int t)
    {return all[s].pathTo(t);}

    double dist(int s, int t)
    {return all[s].diskTo(t);}
}
