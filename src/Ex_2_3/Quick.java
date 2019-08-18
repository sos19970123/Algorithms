package Ex_2_3;

import edu.princeton.cs.algs4.StdRandom;

public class Quick {                                          //快速排序
    public static  void sort(Comparable[] a){
        StdRandom.shuffle(a);
        sort(a,0,a.length);
    }
    public static void sort(Comparable[] a,int lo,int hi){
        if (lo >= hi) return;
        int j = partition(a,lo,hi);                           //寻找切分的位置
        sort(a,lo,j -1 );                                 //利用递归，多次切分数组
        sort(a,j + 1,hi);
    }
    public static int partition(Comparable[] a,int lo,int hi){
        int i =  lo + 1 , j = hi;                             //取a[lo]为切分从{lo + 1，hi}左右两端开始扫描
        Comparable v = a[lo];                                 //取随机排序后的第一个数组作为切分
        while (true){
            while (less(a[++i],v)) if (i == hi)break;        //从左侧开始扫描，直到找到a[i]>v的数        当i=hi时跳出，避免越界
            while (less(v,a[--j])) if (j == lo)break;        //从右侧开始扫描，直到找到a[j]<v的数
            if (i >= j)break;                                //跳出扫描
            exch(a,i,j);                                     //交换a[i] a[j]
        }
        exch(a,lo,j);                                        //将切分的数插入数组
        return j;
    }
    public static boolean less(Comparable v,Comparable w){
        return v.compareTo(w) < 0;
    }
    public static void exch(Comparable[] a,int i,int j){
        Comparable t = a[i];a[i] = a[j];a[j] = t;
    }
}
