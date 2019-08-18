package Ex_4_3;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class LazyPrimMST {                        //Prim算法的延迟实现（
    private boolean[] marked;                     //最小生成树的顶点
    private Queue<Edge> mst;                      //最小生成树的边
    private MinPQ<Edge> pq;                       //横切边（包括失效的边

    public  LazyPrimMST(EdgeWeightedGraph G){
        pq = new MinPQ<Edge>();
        marked = new boolean[G.V()];
        mst = new Queue<Edge>();

        visit(G, 0);                           //假设G是连通的
        while (!pq.isEmpty()){
            Edge e = pq.delMin();                 //利用优先排序，在pq中得到权重最小的边，并将权重最小的边删除
            int v = e.either(), w = e.other(v);   //得到最小边的两个顶点
            if (marked[v] && marked[w]) continue;//跳过失效的边（边的两个顶点都已经在最小生成树中（continue跳出本次的while循环
            mst.enqueue(e);                      //将边添加到树中
            if (!marked[v]) visit(G, v);         //将边的顶点（v或w）添加到树中
            if (!marked[w]) visit(G, w);
        }
    }
    private void visit(EdgeWeightedGraph G , int v){//标记顶点v并将所有链接v和未被标记的顶点
        marked[v] = true;                           //将已经加入树的顶点设为true
        for (Edge e : G.adj(v))                     //将现有最小生成树的顶点的边加入优先队列pq进行排序
            if (!marked[e.other(v)])
                pq.insert(e);
    }
    public Iterable<Edge> edges() { return mst;}
}
