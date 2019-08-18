package Ex_4_4;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

public class DijkstraSP {
    private DirectedEdge[] edgeTo;                   //保存树的队列
    private double[] distTo;
    private IndexMinPQ<Double> pq;                   //优先队列pq，用于保存需要被放松的点和确定下一个被放松的顶点

    public DijkstraSP(EdgeWeightedDigraph G, int s){
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<Double>(G.V());
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;   //先将每个点的权重设为无穷大
        distTo[s] = 0.0;

        pq.insert(s, 0.0);                   //给定一个起点s
        while (!pq.isEmpty())
            relax(G, pq.delMin());                //将pq中的最小值删除，知道pq为空时结束循环
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
