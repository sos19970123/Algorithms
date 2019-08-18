package Ex_4_1;

public class DepthFirstSearch {               //深度优先搜索（利用递归来遍历顶点（路口）
    private boolean[] marked;                 //标记已达到的路口
    private int count;                        //路径的数量

    public DepthFirstSearch(Graph G, int s){
        marked = new boolean[G.V()];         //标记的数组大小为图的顶点数（顶点即为路口
        dfs(G,s);
    }
    private  void dfs(Graph G, int v){
        marked[v] = true;                   //将标记设为true
        count++;                            //路径数增加
        for(int w : G.adj(v))               //遍历路口的所有出口
            if(!marked[w])  dfs(G,w);       //当marked为假（为标记的路口），则从w路口开始遍历
    }

    public boolean marked(int w){
        return marked[w];
    }

    public int count() {
        return count;
    }
}
