package Ex_4_3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.UF;

public class KruskalMST {
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;
    public KruskalMST(EdgeWeightedGraph G){
        mst = new Queue<Edge>();
        pq = new MinPQ<Edge>();
        for (Edge e : G.edges())
            pq.insert(e);                                //将所有边的权重加入队列pq
        UF uf = new UF(G.V());                           //Union - find算法（动态连通性

        while (!pq.isEmpty() && mst.size() < G.V()-1){
            Edge  e  = new pq.delMin();
            int v = e.either(), w = e.other(v);
            if (uf.connected(v,w))                      //两个顶点若被标记，则跳出本次循环（
                continue;
            uf.union(v,w);                              //按照所有边的权重顺序分别相连
            mst.enqueue(e);
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }
}
