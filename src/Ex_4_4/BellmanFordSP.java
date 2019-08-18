package Ex_4_4;

import edu.princeton.cs.algs4.EdgeWeightedDirectedCycle;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BellmanFordSP {
    private double distTo[];                          //从起点到某个顶点的路径长度
    private DirectedEdge[] edgeTo;                    //从起点到某个顶点的最后一条边
    private boolean[] onQ;                            //该顶点是否在队列中
    private Queue<Integer> queue;                     //正在被放松的点
    private int cost;                                 //relax（）被调用的次数
    private Iterable<DirectedEdge> cycle;             //edgeTo[]中是否有负权重环

    public BellmanFordSP(EdgeWeightedDigraph G, int s){
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQ = new boolean[G.V()];
        queue = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;                                   //设起点为s
        queue.enqueue(s);
        onQ[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycyle()){  //若检查发现没有负权重环时，开始放松
            int v = queue.dequeue();
            onQ[v] = false;
            relax(G, v);
        }
    }
    private void relax(EdgeWeightedDigraph G, int v){
        for (DirectedEdge e : G.adj(v)){
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQ[w]){                             //查看被放松的点是否在队列onQ中，以防止将顶点重复插入队列
                    queue.enqueue(w);                     //将w重新插入队列中
                    onQ[w] = true;                        //将onQ重新定义为true
                }
            }
            if (cost ++ % G.V() == 0)                      //在调用过一定次数relax（）后，进行检查，是否有负权重环
                findNegativeCycle();
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
    private void findNegativeCycle(){                   //检测负权重环
        int V = edgeTo.length;
        EdgeWeightedDigraph spt;
        spt = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V ; v++)
            spt.addEdge(edgeTo[v]);
        EdgeWeightedDirectedCycleFinder cf;
        cf = new EdgeWeightedDigraphFinder();
        cycle = cf.cycle();
    }
    public boolean hasNegativeCycyle()
    {return cycle != null;}
    public Iterable<DirectedEdge> negativeCycle()
    {return cycyle;}
}
