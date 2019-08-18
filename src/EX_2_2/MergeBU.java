package EX_2_2;

public class MergeBU {                                                  //自顶向下通过递归分割数组，自顶向上通过创造一个数组长度进行手动分隔
    private static  Comparable[] aux;
    public static void  sort(Comparable[] a){
        int N = a.length;
        aux = new Comparable[N];
        for(int sz = 1;sz < N;sz += sz){                               //每次数组长度增加一倍
            for(int lo = 0;lo < N-sz;lo += sz + sz){                   //将整个数组以归并后的数组长度进行分隔
                merge(a,lo,lo + sz -1,Math.min(lo+sz+sz+1,N-1));
;            }
        }
    }
    public  static  void merge(Comparable[] a,int lo,int mid,int hi){
        int i = lo;
        int j = mid+1;
        for (int k = lo;k <= hi;k++){
            aux[k] = a[k];
        }
        for(int k = lo;k <= hi;k++){
            if    (i > mid)               a[k] = aux[j++];
            else if(j > hi)               a[k] = aux[i++];
            else if (less(aux [j],aux[i]))a[k] = aux[j++];
            else                          a[k] = aux[i++];
        }
    }
    public static boolean less(Comparable v,Comparable w){
        return v.compareTo(w) < 0;
    }
}
