
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
/*
 * Output: 
 * mean                    = 0.5929934999999997
 * stddev                  = 0.00876990421552567
 * 95% confidence interval = [0.5912745987737567, 0.5947124012262428]
 */

public class PercolationStats{
    private double mean; 
    private double stddev;
    private double minConfidence;
    private double maxConfidence;
    
    public PercolationStats(int n, int trials) {   // perform trials independent experiments on an n-by-n grid
   
        
    }
    public double mean() {                         // sample mean of percolation threshold
     return mean;   
    }
    public double stddev()  {
     return stddev;   
    }// sample standard deviation of percolation threshold
    public double confidenceLo()    {
        // low  endpoint of 95% confidence interval
        return minConfidence;
    }
    public double confidenceHi()   {               // high endpoint of 95% confidence interval
        return maxConfidence;
    } 
       public static void main(String[] args)   {     // test client (described below)
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        
   }
    
    
}