import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {
    
    private final int trials;
    private final double[] runs;
    private final double mean;
    private final double stddev;
     
    public PercolationStats(int n, int trials) {
     
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
         
        this.trials = trials;
         

        runs = new double[trials];
      
        for (int i = 0; i < trials; i++) {
              
            Percolation perc = new Percolation(n);
         
            while (!perc.percolates()) {
                perc.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
            }

            runs[i] = perc.numberOfOpenSites()*1.0/(n*n);
        }
        
        this.mean = StdStats.mean(this.runs);
        this.stddev = StdStats.stddev(this.runs);
    }
    
    public double mean() {
        return this.mean;
    }
     
    public double stddev() { 
         
        if (this.trials == 1) {
            return Double.NaN;
        }
         
        return this.stddev;
         
    }
     
    public double confidenceLo() { 
     
        double low = this.mean() - (1.96*this.stddev() / Math.sqrt(this.trials));
        return low;
    }
     
    public double confidenceHi() { 
              
        double hi = this.mean() + (1.96*this.stddev() / Math.sqrt(this.trials));
        return hi;
    }
     

    public static void main(String[] args) {
     
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
         
        PercolationStats percStat = new PercolationStats(n, trials);
        
        System.out.println("mean                       " + percStat.mean());
        System.out.println("stddev                     " + percStat.stddev());
        System.out.println("95% confidence interval = [" + percStat.confidenceLo() +", "
                       + percStat.confidenceHi() + "]");
    }
}