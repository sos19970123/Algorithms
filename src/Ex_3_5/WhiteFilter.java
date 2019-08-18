package Ex_3_5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashSet;

public class WhiteFilter {                     //白名单过滤器
    public static void  main(String[] args){
       HashSet<String> set ;
       set = new HashSet<String>();
       In in = new In(args[0]);                //创建一个新的数组
       while (! in.isEmpty())
           set.add(in.readString());           //将in数组复制到set
       while (!StdIn.isEmpty()){
           String word = StdIn.readString();
           if(set.contains(word))
               StdOut.print(word + " ");
       }
    }
}
