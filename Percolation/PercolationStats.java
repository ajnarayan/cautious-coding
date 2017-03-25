
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
//   The constructor should throw a java.lang.IllegalArgumentException if either n ≤ 0 or trials ≤ 0.
        if (n <= 0 || trials <= 0) throw new java.lang.IllegalArgumentException("No. of experiemts/gridsize should be greater than 0");
        
       double[] thresholdValues = new double[trials];
        int i=0;
        while(i<trials-1){
         //Initialize all sites to be blocked.   
        Percolation p = new Percolation(n);
        //Repeat the following until the system percolates:
        int openSites = 0;
        while(!p.percolates()){
            //Choose a site uniformly at random among all blocked sites.
            int row = 1 + StdRandom.uniform(n);
            int col = 1 + StdRandom.uniform(n);
           //Open the site
            p.open(row,col);
            openSites++;
        }
        i++;
       thresholdValues[i] = openSites / (n*n);
        }//end of trial
 
        //Calculations: 
        //By repeating this computation experiment T times and averaging the results, we obtain a more accurate estimate of the percolation threshold
        mean = StdStats.mean(thresholdValues);
        //the sample standard deviation s; measures the sharpness of the threshold
        stddev = StdStats.stddev(thresholdValues);
        minConfidence = mean - ((1.96 * stddev)/Math.sqrt(trials) );
        maxConfidence = mean + ((1.96 * stddev)/Math.sqrt(trials) );
    }
 
    public double mean() {  // sample mean of percolation threshold
     return mean;   
    }
    
    public double stddev()  {   // sample standard deviation of percolation threshold
        return stddev;   
    }
    
    public double confidenceLo()    {  // low  endpoint of 95% confidence interval
        return minConfidence;
    }
    
    public double confidenceHi()   {   // high endpoint of 95% confidence interval
        return maxConfidence;
    } 
    
    public static void main(String[] args)   { // test client (described below)
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n,trials);
        
        System.out.println("mean = "+ ps.mean());
        System.out.println("stddev = "+ ps.stddev());
        System.out.println("95% confidence interval = ["+ps.confidenceLo()+","+ps.confidenceHi()+"]");
   }
 
}