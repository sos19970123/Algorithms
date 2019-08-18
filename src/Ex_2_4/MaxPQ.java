package Ex_2_4;

import java.awt.image.ColorModel;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;                              //基于堆的完全二叉树
    private int N = 0;                             //存储在1 - N中，pq[0]不使用

    public MaxPQ(int maxN){
        pq = (Key[]) new Comparable[maxN + 1];    //存储在1 - n中
    }

    public boolean isEmputy(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void insert(Key v){                     //插入元素
        pq[++N]  = v;                              //在N+1插入一个新的元素
        swim(N);                                   //然后让元素上浮
    }
    public Key delMax(){                          //删除最大值
        Key max = pq[1];                          //二叉树的根节点得到最大值为pq[1]
        exch(1,N--);                           //与最后一个结点交换，并讲数组容量N -1
        pq[N + 1] = null;                         //删除结点  （N + 1）,回收空间
        sink(1);                              //恢复堆的有序
        return max;
    }

    private  boolean less(int i,int j){
        return pq[i].compareTo(pq[j]) < 0;
    }
    private  void exch(int i, int j){
        Key t = pq[i];pq[i] = pq[j];pq[j] = t;
    }
    private void swim(int k){
        while (k>1 && less(k/2,k)){
            exch(k/2,k);
            k = k/2;
        }
    }
    private void sink(int k){
        while (2 * k <= N){
            int j = 2 * k;
            if (j<N && less(j,j + 1)) j++;
            if (!less(k,j)) break;
            exch(k,j);
            k = j;
        }
    }
}
