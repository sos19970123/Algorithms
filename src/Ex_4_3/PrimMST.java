package Ex_4_3;

import edu.princeton.cs.algs4.IndexMinPQ;

public class PrimMST {
    private Edge[] edgeTo;              //距离树最近的边（如果顶点v不在树中，但至少有一条边与树相连，那么edge[v]就是顶点v与树相连的边
    private double[] distTo;            //distTo[w] = edgeTo[w].weight（同上，即顶点v与树最近的边的权重，如果！marked[v] 等价与 distTo[v]为正无穷（distTo[v] = Double.POSITIVE_INFINITY;
    private boolean[] marked;           //如果v在树中则为true
    private IndexMinPQ<Double> pq;     //有效的横切边（索引优先队列

   public PrimMST(EdgeWeightedGraph G) {
       edgeTo = new Edge[G.V()];
       distTo = new double[G.V()];
       marked = new boolean[G.V()];
       for (int v = 0; v < G.V(); v++)
           distTo[v] = Double.POSITIVE_INFINITY;//Double类的字段（恒定持有double型的正无穷大。 它等于Double.longBitsToDouble(0x7ff0000000000000L)返回的值。
        pq = new IndexMinPQ<Double>(G.V());

        distTo[0] = 0.0;
        pq.insert(0, 0.0);             //用顶点0和权重0.0初始化pq
        while (!pq.isEmpty())
            visit(G, pq.delMin());             //将最近的顶点添加到树中
    }

    private void visit(EdgeWeightedGraph G, int v){//顶点v加入树中，更新数据
        marked[v] = true;
        for (Edge e : G.adj(v)){                    //遍历新加入顶点的边
            int w = e.other(v);
            if (marked[v]) continue;                //v-w失效
            if (e.weight() < distTo[w]){            //将v周围的边的权重与w的权重相比，若小于w，则用当前e的边代替w
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w))                 //若w已经在有效横切边内，则改变它的值，否则就加入
                    pq.changeKey(w, distTo[w]);
                else
                    pq.insert(w, distTo[w]);
            }
        }
    }
}
