package Ex_3_4;

import javafx.beans.property.ReadOnlyDoubleProperty;

public class LinearProbingHashST<Key, Value>{
    private int N;                        //键值对的总数
    private int M = 16;                  //线性探测表大小
    private Key[]  keys;
    private Value[] vals;
    public LinearProbingHashST(int cap){
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }
    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;                      //哈希值算法
    }

    private void resize(int cap){
        LinearProbingHashST<Key, Value> t;                            //创建一个长度为cap新的散列表t备份
        t = new LinearProbingHashST<Key, Value>(cap);
        for(int i = 0; i < M ; i++)                                   //将keys不为null时，将键值对插入散列表t
        {
            if(keys[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }
        keys = t.keys;                                                 //将备份的散列表t赋值回散列表
        vals = t.vals;
        M = t.M;
    }

    public void put(Key key, Value val){
        if (N >= M/2) {
            resize(2 * M);                              //将M加倍,保证散列表的使用率不会超过1/2
        }
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) %  M){         //从哈希值键开始 ，当值不为空时，循环，每次循环都取到下一个哈希值的位置
            if(keys[i].equals(key)) {                                   //若命中目标值，将目标值的val改变,并从方法中返回
                vals[i] = val;
                return;
            }
        }                                                              //若无命中，则再i处拆入该值，并将散列表的键值对加一
        keys[i] = key;                                                 //更新键值对的散列值
        vals[i] = val;
        N++;                                                           //键值对的总数+1
    }

    public Value get(Key key){
        for (int i = hash(key); keys[i] != null; i = (i + 1) %  M){ //检查散列表的下一个位置
            if ((keys[i].equals(key))){                            //命中目标，则将目标的值返回
                return vals[i];
            }
        }
        return null;
    }

    public void delete(Key key ){
        if(!contains(key)) {
            return;
        }
        int i = hash(key);
        while(!key.equals(keys[i]))                                 //寻找待删除的目标键值对
        {
            i = (i + 1) % M;
        }
        keys[i] = null;                                             //找到键值对后，将目标的键值对变为null
        vals[i] = null;
        i = (i + 1)  % M;                                           //从删除的键值对下一个开始
        while (keys[i] !=  null){                                   //若此处不为空
            Key keyToRedo = keys[i];                                //将此键值对备份
            Value valToRedo = vals[i];
            keys[i] = null;                                        //删除
            vals[i] = null;
            N--;                                                   //键值对的总数-1
            put(keyToRedo, valToRedo);                             //将备份的键值对重新插入
            i = (i + 1) % M;                                       //寻找下一个位置
        }
        N--;                                                     //删除数据和重新排序完成后，将键值对总数-1
        if(N > 0 && N == M / 8) {
            resize(M/2);               //调整数组大小，使用率在8/1和2/1之间
        }
    }
}
