package Ex_5_1;

public class LSD {                              //低位优先的字符串排序
    public static void sort(String[] a, int W){//W 为改字符串的位数，低位优先的字符串排序一般用于数组中每个字符的键长相等（W相同）
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];           //构造辅助数组

        for (int d = W - 1; d >= 0; d--){      //从字符的最后一位字符开始进行排序
            int[] count = new int[R + 1];      //计算出现频率
            for (int i = 0; i < N ;i++)
                count[a[i].charAt(d) + 1]++;   //charAt返回字符串在d的值（由于第一位0，所以所有的都要先+1

            for (int r = 0; r < R ;r++)       //将频率化为引索
                count[r + 1] += count[r];

            for (int i = 0; i < N ; i++)      //将元素分类
                aux[count[a[i].charAt(d)]++] = a[i];

            for (int i = 0; i < N ; i++)       //回写，将辅助数组写回原数组
                a[i] = aux[i];
        }
    }
}
