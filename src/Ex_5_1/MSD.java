package Ex_5_1;

import edu.princeton.cs.algs4.Insertion;

public class MSD {
    private static int R = 256;
    private static final int M = 15;
    private static String[] aux;
    private static int charAt(String s , int d){               //私有建立一个charAt方法，将超过长度的字符返回-1
     if (d < s.length())
        return s.charAt(d);
    else
    return -1;
    }
    public static void sort(String[] a){
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N - 1, 0);
    }
    private static void sort(String[] a, int lo, int hi, int d){//以第d个字符为键将a[lo]至a[hi]排序
        if (hi <= lo + M)
        { Insertion.sort(a, lo, hi); return;}
        int[] count = new int[R + 2];                            //由于超过长度的字符返回-1，所以为了得到非负的int值，所以要+2
        for (int i = lo; i <= hi; i++)                           //计算频率
            count[charAt(a[i], d) + 2]++;

        for (int r = 0; r < R + 1; r++)                         //将频率转换为索引
            count[r + 1] += count[r];

        for (int i = lo; i <= hi; i++)                          //数据分类
            aux[count[charAt(a[i], d) + 1]++] = a[i];

        for (int i  = lo; i <= hi; i++)                        //回写
            a[i] = aux[i - lo];

        for (int r = 0; r < R ; r++)                           //递归
            sort(a,lo + count[r], lo + count[r + 1] - 1, d + 1);
    }
}
