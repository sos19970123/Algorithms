package Ex_3_5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashSet;

public class DeDup {                               //过滤器
    public static void main(String[] args){
        HashSet<String> set;
        set = new HashSet<String>();
        while (!StdIn.isEmpty()){
            String key = StdIn.readString();
            if(!set.contains(key)){               //输入的key不在set的哈希表中，则将key加入，并输出
                set.add(key);
                StdOut.print(key + " ");
            }
        }
    }
}
