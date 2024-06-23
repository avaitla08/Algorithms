import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    // perform independent trials on an n-by-n grid
    private Percolation p;
    private int n;
    private int trials;
    private double sum;
    private double[] fos;


    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Illegal parameter value.");
        }

        this.n = n;
        this.trials = trials;
        int row;
        int col;

        sum = 0.0;
        fos = new double[trials];

        for (int i = 0; i < trials; i++) {
            p = new Percolation(n);
            while (!p.percolates()) {
                row = StdRandom.uniform(n) + 1;
                col = StdRandom.uniform(n) + 1;

                if (!p.isOpen(row, col)) {
                    p.open(row, col);

                }

            }
            sum = p.numberOfOpenSites();
            fos[i] = sum / (n * n);
            //p.displayGrid();
            //System.out.println();


        }

    }

    // sample mean of percolation threshold
    public double mean() {

        return StdStats.mean(fos);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {

        return StdStats.stddev(fos);

    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return (StdStats.mean(fos)) - ((1.96 * (StdStats.stddev(fos))) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (StdStats.mean(fos)) + ((1.96 * (StdStats.stddev(fos))) / Math.sqrt(trials));
    }

    public static void main(String[] args) {

        //System.out.println("Enter Grid Size and amount of Trials:");
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        PercolationStats s = new PercolationStats(x, y);

        System.out.println("mean = " + s.mean());
        System.out.println("stddev = " + s.stddev());
        System.out.println("95% confidence interval = [" + s.confidenceLo() + ", " + s.confidenceHi() + "] ");

    }

}
