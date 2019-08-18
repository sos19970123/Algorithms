package Ex_2_4;


public class Sort {
    public void sort(Comparable[] a){    //堆排序
        int N = a.length;
        for( int k = N / 2;k >= 1;k--){     //构造堆
            sink(a,k,N);
        }
        while (N > 1){                    //堆排序
            exch(a,1,N-1);         //将根结点与最后的叶节点交换，并n-1删除堆的长度，
            sink(a,1,N);              //利用下沉恢复堆的有序性
        }

    }

    private  boolean less(Comparable[] a,int i, int j){
        return a[i].compareTo(a[j]) < 0;
    }
    private  void exch(Comparable[] a,int i,int j){
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }

    private void sink(Comparable[] a,int k, int n){
        while (2 * k <= n){
            int i = 2 * k;
            if (i < n && less(a,i,i+1)) i++;
            if(!less(a,k,i)) break;
            exch(a,k,i);
            k = i;
        }
    }
}
