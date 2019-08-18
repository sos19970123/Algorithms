package EX_2_2;

public class Merge {                                              //自顶向下的归并排序
    private static Comparable[] aux;
    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a,0,a.length-1);
    }
    public  static void sort(Comparable[]a,int lo,int hi){
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo)/2;                                   //将数组分为左右两边
        sort(a,lo,mid);                                               //左半边排序 。利用递归将左半边先分割为小的部分，进行排序，再将部分扩大后排序
        sort(a,mid+1,hi);                                         //右半边排序
        merge(a,lo,mid,hi);                                           //归并
    }
    public  static  void merge(Comparable[] a,int lo,int mid,int hi){
        int i = lo;
        int j = mid+1;
        for (int k = lo;k <= hi;k++){
            aux[k] = a[k];                                           //将a数组复制到aux
        }
        for(int k = lo;k <= hi;k++){
            if      (i > mid)             a[k] = aux[j++];          //左半边用尽后
            else if (j > hi)              a[k] = aux[i++];          //右半边用尽后
            else if (less(aux [j],aux[i]))a[k] = aux[j++];          //右半边当前元素小于左半边当前元素，取右半边元素
            else                          a[k] = aux[i++];          //右半边当前元素大于左半边当前元素，取左半边元素
        }
    }
    public static boolean less(Comparable v,Comparable w){
        return v.compareTo(w) < 0;
    }
}
