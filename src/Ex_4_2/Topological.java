package Ex_4_2;

public class Topological {
    private Iterable<Integer> order;                        //顶点的拓扑排序
    public Topological(Digraph G){
        DirectedCycle cyclefinder = new DirectedCycle(G);
        if(!cyclefinder.hasCycle()){                       //检查是否为无环有向图，有环图则不可排序，每次进行拓扑排序都需要进行一次环检查
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();                    //有向无环图的拓扑顺序即为所有顶点的逆后序排列
        }
    }
    public Iterable<Integer> order(){ return order;}
    public boolean isDAG() { return order != null;}



    public static void main(String[]  args){             //利用修改过的symbolDigraph进行字符的排序
        String filename = args[0];
        String separator = args[1];

    }
}
