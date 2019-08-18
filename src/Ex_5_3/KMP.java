package Ex_5_3;

public class KMP {
    private String pat;
    private int[][] dfa;
    public KMP(String pat){                       //由模式字符串构造DFA（通过构造一个数组来记录模式字符串中的重复字符子串
        this.pat = pat;                           //模式字符串
        int M = pat.length();                     //M为模式字符串的长度
        int R = 256;
        dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;
        for (int X = 0, j = 1; j < M; j++){      //计算dfa[][]（利用j来遍历整个模式字符串
            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][X];           //复制匹配失败情况下的值
            dfa[pat.charAt(j)][j] = j + 1;       //设置匹配成功情况下的值
            X = dfa[pat.charAt(j)][X];           //更新重启状态
        }
    }
    public int search(String txt){              //在txt上模拟DFA运行（文本字符串
        int i, j, N = txt.length(), M = pat.length();
        for (i = 0, j = 0; i < N && j < M; i++)
            j = dfa[txt.charAt(i)][j];
        if (j == M) return i - M;               //找到匹配（到达模式字符串的结尾
        else        return N;                   //未找到匹配（到达文本字符串的结尾
    }

}
