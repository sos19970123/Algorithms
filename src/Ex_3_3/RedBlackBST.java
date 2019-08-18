package Ex_3_3;

public class RedBlackBST<Key extends Comparable<Key>, Value>{
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node{
        private Key key;
        private Value val;
        private Node left, right;
        private int N;
        private boolean color;

        Node(Key key, Value val, int N, boolean color){
            this.key   = key;
            this.val   = val;
            this.N     = N;
            this.color = color;
        }
    }
    private boolean isRed(Node h){
        if (h == null) return false;
        return h.color = RED;
    }
    public int size(){
        return size(root);
    }
    private int size(Node x){
        if(x == null) return 0;
        else          return x.N;
    }
    private Node rotateLeft(Node h){                                //左旋转
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }
    private Node rotateRight(Node h){                                //右旋转
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }
    private void flipColors(Node h){                            //插入方法时使用      转换链接的颜色（左右链接均为红时
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
    private void deflipColors(Node h){                         //删除方法时使用         当h = RED && h.left.left = BLACK时，转换颜色
        h.color = BLACK;
        h.left.color = RED;
        h.right.color = RED;
    }

    public void put(Key key, Value val){                     //插入操作
        root = put(root, key, val);
        root.color = BLACK;
    }
    private Node put(Node h, Key key, Value val){
        if (h == null) return new Node(key,val,1,RED);    //树为空时，插入，与父结点以红相连
        int cmp = key.compareTo(h.key);
        if(cmp < 0) h.left = put(h.left,key,val);             //递归寻找目标值
        else if(cmp > 0) h.right = put(h.right,key,val);
        else h.val = val;

        if(isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);         //左链接为红时，左旋转
        if(isRed(h.right) && isRed(h.left.left)) h = rotateRight(h);   //两个红结点相连时，右旋转
        if(isRed(h.left) && isRed(h.right)) flipColors(h);              //左右链接均为红时

        h.N = size(h.left) + size(h.right) + 1;                         //更改计数器
        return h;
    }

    private Node moveRedLeft(Node h){                                  //
        deflipColors(h);
        if(isRed((h.right.left))){
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
        }
        return h;
    }
    private boolean isEmpty(){
        if (root.N == 1) return true;
        else return false;
    }
    public void deleteMin(){
        if(!isRed(root.left) && !isRed(root.right))root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }
    private Node deleteMin(Node h){
        if (h.left == null) return null;
        if(!isRed(h.left) && !isRed(h.left.left)) h = moveRedLeft(h);
        h.left = deleteMin(h.left);
        return balance(h);
    }
    private Node balance(Node h){
        if(isRed(h.right)) h = rotateLeft(h);
        if(isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);         //左链接为红时，左旋转
        if(isRed(h.right) && isRed(h.left.left)) h = rotateRight(h);    //两个红结点相连时，右旋转
        if(isRed(h.left) && isRed(h.right)) flipColors(h);              //左右链接均为红时

        h.N = size(h.left) + size(h.right) + 1;                         //更改计数器
        return h;
    }

}
