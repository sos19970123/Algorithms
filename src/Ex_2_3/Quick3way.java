package Ex_2_3;

import edu.princeton.cs.algs4.StdRandom;

public class Quick3way {
    public static  void sort(Comparable[] a){
        StdRandom.shuffle(a);
        sort(a,0,a.length);
    }
    public static void sort(Comparable[] a,int lo,int hi){
        if(lo >= hi) return;
        int lt = lo , i = lo + 1 , gt = hi;               //lt指针为小于v，gt指针为大于v，i表示与v相等的元素
        Comparable v = a[lo];
        while (i <= gt){
            int cmp = a[i].compareTo(v);                  //
            if (cmp > 0) exch(a, lt++,i++);               //a[i] > v ，a[lt] a[i] 交换
            else if (cmp < 0) exch(a, i, gt--);           //a[i] < v , a[gt] a[i] 交换
            else i++;
        }
        sort(a,lo,lt - 1);
        sort(a,gt + 1,hi);
    }
    public static void exch(Comparable[] a,int i,int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
