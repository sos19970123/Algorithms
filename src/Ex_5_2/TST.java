package Ex_5_2;

public class TST<Value> {                                    //三向单词查找树
    private Node root;                                       //树的根节点
    private class Node{
        char c;                                              //字符
        Node left, mid, right;                               //左中右三向单词查找树
        Value val;                                           //与字符串相关联的值
    }
    public Value get(String key){
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.val;
    }
    private Node get(Node x, String key, int d){
        if (x == null) return null;
        char c = key.charAt(d);
        if      (c < x.c) return get(x.left, key, d);      //若比当前字符小，则取左边的字符
        else if (c > x.c) return get(x.right, key, d);     //若比当前字符大，则取右边的字符
        else if (d < key.length() - 1)                     //以上均否，则判断是否超出key的长度，若无，则取中间的字符
                          return get(x.mid, key, d + 1);
        else              return x;                        //无对应的字符，或已经达到目标字符串的长度，则返回
    }
    public void put(String key, Value val)
    { root = put(root, key, val, 0);}
    private Node put(Node x, String key, Value val, int d){
        char c = key.charAt(d);
        if (x == null) {x = new Node(); x.c = c;}
        if (c < x.c) x.left = put(x.left, key, val, d);
        else if (c > x.c) x.right = put(x.right, key, val, d);
        else if (d < key.length() - 1)
            x.mid = put(x.mid, key, val, d + 1);
        else x.val = val;
        return x;
    }
}
