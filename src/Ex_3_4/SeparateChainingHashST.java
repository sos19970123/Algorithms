package Ex_3_4;

import edu.princeton.cs.algs4.SequentialSearchST;

public class SeparateChainingHashST<Key,Value> {
    private int N;                                       //j键值对总数
    private int M;                                       //散列表大小
    private SequentialSearchST<Key,Value>[] st;          //存放链表的数组

    public SeparateChainingHashST(){
        this(997);                                                       //M为散列值
    }
    public SeparateChainingHashST(int M){
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];   //创建存放链表的数组
        for(int i = 0; i < M ;i++){                                          //将链表的数据存入数组
            st[i] = new SequentialSearchST();
        }
    }

    private int hash (Key key){
        return (key.hashCode() & 0x7fffffff) % M ;                            /**将默认的哈希值和除留余数法结合起来产生一个0 至 M-1的整数
                                                                                &为按位与，即二进位相与
                                                                                可以将符号为屏蔽，将32位整数化为31位非负整数
                                                                             **/

    }

    public Value get(Key  key){
        return (Value) st[hash(key)].get(key);                            //得到st数组中键值为所求key的哈希值的值    进行类型的转换，因为java不允许泛型的数组
    }
     public void put(Key key, Value val){
        st[hash(key)].put(key,val);
     }

}
