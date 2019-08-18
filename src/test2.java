public class test2 {
    public static int[][] uniquePaths(int m, int n) {
        if(m == 0 || n == 0) return null;
        int[][] dp  = new int[m][n];
        for(int i = 0; i<m; i++)
            dp[i][0] = 1;
        for(int j = 0; j < n; j++)
            dp[0][j] = 1;
        for(int i = 1; i < m; i++)
            for(int j = 1;j < n; j++)
                dp[i][j] = dp[i][j-1] + dp[i-1][j];
        return dp;
    }
    public static void main(String[] args)throws Exception{
        int[] [] dp =  uniquePaths(3,3);
        for (int i = 0; i < 3;i++){
            for(int j = 0; j < 3; j++){
                System.out.println(dp[i][j]);
            }
        }

    }
}
