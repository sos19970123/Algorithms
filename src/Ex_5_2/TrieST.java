package Ex_5_2;

import edu.princeton.cs.algs4.Queue;

public class TrieST <Value>{
    private static int R = 256;                  //基数
    private Node root;                           //单词查找树的根节点

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }
    public Value get(String key){
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.val;
    }
    private Node get(Node x, String key, int d){//返回以x作为根节点的子单词查找树中与key相关联的值
        if (x == null) return null;
        if (d == key.length()) return x;        //当检索到的长度等于kry的长度之后，返回x
        char c = key.charAt(d);                 //找到第d个自读所对应的单词查找树
        return get(x.next[c], key, d + 1);  //查找key的下一个字母
    }
    public void put(String key, Value val)
    { root = put(root, key, val,0);}
    private Node put(Node x, String key, Value val, int d){//如果key存在于以x为根节点的子单词查找树中则更新与它关联的值
        if (x == null) return null;
        if (d == key.length()) {x.val = val; return x;}
        char c = key.charAt(d);                            //找到第d个字符所对应的子单词查找树
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    public Iterable<String> keys()
    { return keyWithPerfix("");}                           //pre为“”空，即为收集一棵单词查找树中所有的键
    public Iterable<String> keyWithPerfix(String pre){          //接受一个字符串参数pre，并返回符号表中所有以该字符串为前缀的键（前缀匹配
        Queue<String> q = new Queue<String>();
        collect(get(root, pre, 0), pre, q);                 //先利用get（）找到前缀所对应的单词查找树，再用collect（）完成任务
        return q;
    }
    private void collect(Node x, String pre, Queue<String> q){
        if (x == null) return;
        if (x.val != null) q.enqueue(pre);                    //若x的值不为空，则加入队列
        for (char c = 0; c < R; c++)
            collect(x.next[c], pre + c, q);
    }
    /**
     * 单词查找树的通配符匹配（略）
     */
    public String longestPrefixOf(String s){                 //找到给定字符串的最长键前缀
        int length = search(root, s, 0, 0);
        return s.substring(0, length);                       //Sting.substring（int beginIndex, int endIndex） 返回一个字符串，该字符串是此字符串的子字符串。
    }
    private int search(Node x, String s, int d, int length){
        if (x == null) return length;                       //当字符表中没有该字符的单词，则直接返回最长长度
        if (x.val != null)length = d;
        if (d == s.length()) return length;                 //当字符表中有该字符的单词，则在查找结束后返回长度
        char c = s.charAt(d);
        return search(x.next[c], s, d + 1, length);
    }
}
