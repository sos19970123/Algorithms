package Ex_3_2;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;                                    //根节点
    private class Node{
        private Key key;                                  //键
        private Value val;                                //值
        private Node left,right;                          //指向子树的链接
        private int N;                                    //以该结点为根的子树的结点总数（计数器

        public Node(Key key,Value val, int N ){
            this.key = key; this.val = val; this.N = N;
        }
    }
    public int size(){
        return size(root);                            //java：方法的重载
    }
    private int size(Node x){                           //树的节点总数
        if(x == null) return 0;
        else          return x.N;
    }

    public Value get(Key key){
        return get(root, key);
    }
    public Value get(Node x, Key key){                   //在x为根节点的子树中查找并返回key对应的值，否则返回
        if(x == null) return null;                       //否则返回null
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left,key);       //小于根节点，就从左侧的树中查找
        else if (cmp > 0) return get(x.right,key);
        else              return x.val;
    }

    public void put(Key key,Value val){                 //找到key，然后更新它的值
        root = put(root, key,val);
    }
    public Node put(Node x, Key key, Value val){       //在x为根节点的子树中查找并更新key对应的值
        if(x == null) {
            return new Node(key,val,1);   //否则以key和val的键值对的新结点插入
        }
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) {
            return x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            return x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min(){                                 //查找最小值
        return min(root).key;
    }
    private Node min(Node x){
        if(x.left == null) return x;
        return min(x.left);
    }
    public Key max(){                                 //查找最小值
        return max(root).key;
    }
    private Node max(Node x){
        if(x.right == null) return x;
        return max(x.left);
    }
    public Key floor(Key key){                      //向下取整
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }
    private Node floor(Node x, Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;                    //树中有值相等的key，返回
        if (cmp < 0)  return floor(x.left, key);   //目标值小于当前节点，向左子树移动
        Node t = floor(x.right, key);              //找到比目标值小的最大子树
        if(t != null) return t;                    //最大子树的右侧是否有目标值，若不为空，返回
        else          return x;                    //最大子树的右侧为空，该结点为小于目标值的最大结点
    }

    public Key select(int k){                      //查找在二叉查找树中按大小排名为k的节点（如果元素x排名为k，则有k个元素排在x的前面，排名以0为起点），以升序排序
        return select(root,k).key;
    }
    private Node select(Node x, int k){
        if(x == null) return null;
        int t = size(x.left);
        if     (t > k) return select(x.left,k);
        else if(t < k) return select(x.right,k-t-1); //??
        else           return x;
    }

    public int rank(Key key){                                             //排名
        return rank(key,root);
    }
    private int rank(Key key, Node x){
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if(cmp < 0)return rank(key,x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    public  void deleteMin(){
        root = deleteMin(root);
    }
    private Node deleteMin(Node x){
        if(x.left == null) return x.right;               //将最左边的结点的右链接返回
        x.left = deleteMin(x.left);                      //利用递归不断检索至最左的结点，然后将上一行返回的最左结点的x.right链接到倒二左结点的左链接（更新链接
        x.N = size(x.left) + size(x.right) + 1;          //更新计数器
        return x;                                        //从上两行的递归中不断return x，更新根节点的左子树的计数器
    }

    public void delete(Key key){
        root = delete(root, key);
    }
    private Node delete(Node x, Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left = delete(x.left,key);   //递归不断检索至要删除的k结点
        else if (cmp > 0) x.right = delete(x.right,key);
        else{
            if (x.right == null) return x.left;         //若只有一个子树，就返回该子树
            if (x.left == null) return x.right;
            Node t = x;                                 //设置一个t结点来保存被删除结点的链接
            x = min(t.right);                           //将大于被删除节点（右侧子树）的最小值作为后继结点
            x.right = deleteMin(t.right);               //将后继节点的右侧树链接到被删除了后继节点的原右侧树
            x.left = t.left;                            //将后继节点的左侧树链接
        }
        x.N = size(x.left) + size(x.left) + 1;         //通过不断跳出递归更新计数器
        return x;
    }

    public Iterable<Key> keys(){                      //范围查找
        return keys(min(),max());                     //不设范围，默认为全体
    }
    public Iterable<Key> keys(Key lo,Key hi){
        Queue<Key> queue = new LinkedList<Key>();    //创建一个队列
        keys(root,queue,lo,hi);
        return queue;
    }
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi){
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left,queue,lo,hi);                //递归确定范围
        if (cmplo <= 0 && cmphi >= 0) queue.offer(x.key);       //将范围内的结点的值放入队列
        if (cmphi > 0) keys(x.right,queue,lo,hi);
    }
}

