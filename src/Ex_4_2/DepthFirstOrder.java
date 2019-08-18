package Ex_4_2;

import java.util.Stack;
import edu.princeton.cs.algs4.Queue;

public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;          //前序：在递归调用之前将顶点加入队列
    private Queue<Integer> post;         //后序：在递归调用之后将顶点加入队列
    private Stack<Integer> reversePost;  //逆后序：在递归调用之后将顶点压入栈

    public DepthFirstOrder(Digraph G){
        pre         = new Queue<Integer>();
        post        = new Queue<Integer>();
        reversePost = new Stack<Integer>();
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if(!marked[v])
                dfs(G,v);
    }
    private  void dfs(Digraph G, int v){
        pre.enqueue(v);
        marked[v] = true;
        for(int w : G.adj(v))
            if(!marked[w])
                dfs(G,w);
        post.enqueue(v);
        reversePost.push(v);
    }
    public Iterable<Integer> pre(){ return pre;}
    public Iterable<Integer> post() { return post;}
    public Iterable<Integer> reversePost() {return reversePost;}
}
