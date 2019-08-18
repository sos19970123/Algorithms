package Ex_2_1;

public class Shell {                                  //以希尔序列倒叙将数组分割后进行局部排序（如13，4，1），局部排序结果就是整体排序
    public static void sort(Comparable[] a){
        int N  = a.length;
        int h = 1;
        int t;
        while (h < N/3) {
            h = 3 * h + 1;//希尔数列：1，4，13……
        }
        while (h >= 1){
            for (int i = h;i < N ;i++){
                for (int j = i;j >=h && less(a[j],a[j-h]);j -= h){
                    exch(a,j,j-h);
                }
            }
        h = h/3;
        }
    }

    public static boolean less(Comparable v,Comparable w){
        return v.compareTo(w) < 0;
    }
    public static void exch(Comparable[] a,int i,int j){
        Comparable t = a[i];a[i] = a[j];a[j] = t;
    }

}
