package Ex_3_5;

import edu.princeton.cs.algs4.*;

public class LookupIndex {
      public static void main(String[] args){
          In in = new In(args[0]);                                        //引索数据库
          String sp = args[1];                                            //分隔符
          ST<String, Queue<String>> st = new ST<String, Queue<String>>();
          ST<String, Queue<String>> ts = new ST<String, Queue<String>>();
          while (in.hasNextLine()){
              String[] a = in.readLine().split(sp);
              String key = a[0];
              for(int i = 1; i < a.length; i++){
                  String val = a[i];
                  if (!st.contains(key)) st.put(key, new Queue<String>());
                  if (!st.contains(val)) st.put(val, new Queue<String>());
                  st.get(key).enqueue(val);
                  st.get(val).enqueue(key);
              }
          }
          while (!StdIn.isEmpty()){
              String query = StdIn.readLine();
              if (st.contains(query))
                  for (String s :st.get(query))
                      StdOut.println(" " + s);
              if (ts.contains(query))
                  for (String s : ts.get(query))
                      StdOut.println(" " +  s);
          }
      }
}
