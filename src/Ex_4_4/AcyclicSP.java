package Ex_4_4;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

public class AcyclicSP {//无环加权有向图的最短路径（将所有的边的权重取负，初始化distTo[] = Double.NEGATIVE_INFINITY 初始化为负无穷既可以得到最长路径
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public AcyclicSP(EdgeWeightedDigraph G , int s){
        edgeTo  = new DirectedEdge[G.V()];
        distTo = new double[G.V()];

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        Topological top = new Topological(G);     //建立无环加权有向图的拓扑顺序
        for (int v : top.order())                 //在拓扑顺序中放松
            relax(G,v);
    }

    private void relax(EdgeWeightedDigraph G, int v){
        for (DirectedEdge e : G.adj(v)){
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;                                    //将e新路径存入最短路径树
                if (pq.contains(w)) pq.changeKey(w, distTo[w]);   //如果w存在与优先队列中，则将w的权重改变
                else                pq.insert(w, distTo[w]);      //若不存在，则插入
            }
        }
    }
    public double diskTo(int v){ return distTo[v];}
    public boolean hasPathTo(int v) { return distTo[v] < Double.POSITIVE_INFINITY;}
    public Iterable<DirectedEdge> pathTo(int v){
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }
}
