import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF x;
    private int[][] siteGrid;

    private int n;
    private final int open = 0;
    private final int blocked = 1;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.n = n;
        if (n <= 0) {
            throw new IllegalArgumentException("N must be greater than 0");
        } else {
            siteGrid = new int[n][n];

            x = new WeightedQuickUnionUF((n*n));
            for (int i = 0; i < siteGrid.length; i++) {

                for (int j = 0; j < siteGrid[i].length; j++) {
                    siteGrid[i][j] = blocked;

                }
            }
        }

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row > n || col < 0 || col > n) {
            throw new IllegalArgumentException("Illegal parameter value.");
        }
        row = row - 1;
        col = col - 1;

        siteGrid[row][col] = open;

        if ((row - 1) >= 0) {
            if (siteGrid[row - 1][col] == open) {
                x.union(((row * n) + col), (((row - 1) * n) + col));
            }
        }
        if ((row + 1) < n) {
            if (siteGrid[row + 1][col] == open) {
                x.union(((row * n) + col), (((row + 1) * n) + col));
            }
        }
        if ((col - 1) >= 0) {
            if (siteGrid[row][col - 1] == open) {
                x.union(((row * n) + col), ((row * n) + (col - 1)));
            }
        }
        if ((col + 1) < n) {
            if (siteGrid[row][col + 1] == open) {
                x.union(((row * n) + col), ((row * n) + (col + 1)));
            }
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if ((row < 0 || row > n) || (col > n || col < 0)) {
            throw new IllegalArgumentException("Illegal parameter value.");
        }
        row = row - 1;
        col = col - 1;

        return siteGrid[row][col] == open;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (this.isOpen(row, col)) {
            if ((row < 0 || row > n) || (col > n || col < 0)) {
                throw new IllegalArgumentException("Illegal parameter value.");
            }
            row = row - 1;
            col = col - 1;

            for (int i = 0; i <= n - 1; i++) {
                if (x.connected(i, ((row * n) + col))) {

                    return true;

                    //     System.out.println(i + ", " +((row * n) + col) );

                }
            }
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < siteGrid.length; i++) {

            for (int j = 0; j < siteGrid[i].length; j++) {
                if (siteGrid[i][j] == 0) {
                    count++;
                }

            }
        }
        return count;
    }

//    public void displayGrid() {
//        for (int i = 0; i < n; i++) {
//            System.out.println();
//            for (int j = 0; j < n; j++) {
//                System.out.print(" [" + siteGrid[i][j] + "] ");
//            }
//        }
//    }
    // does the system percolate?

    public boolean percolates() {

        for (int i = 0; i <= n - 1; i++) {
            for (int j = ((n * n) - n); j <= ((n * n) - 1); j++) {
                // System.out.println("Inside Percolate  "+i+" "+j );
                if (x.connected(i, j)) {
                    if ((n == 1 ) && (siteGrid[i][j] == 1)) {
                      return false;
                    }
                    return true;
                }
            }
        }
        return false;

    }

    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);

 //       Percolation s = new Percolation(1);
//
//
//       s.open(1, 1);
//        //s.open(2,2);
//        System.out.println("Percolate :" + s.percolates());
//        s.displayGrid();
//        s.open(4,6);
//        s.open(2, 6);
//        s.open(3,6);
//        s.open(5,6);
//        s.open(6,6);
//        System.out.println("Percolate :" + s.percolates());
//        System.out.println("Number of Open Sites :" + s.numberOfOpenSites());
//        System.out.println("IsOpen :" + s.isOpen(1, 1));
////        System.out.println("IsOpen :" + s.isOpen(2, 6));
////        System.out.println("IsOpen :" + s.isOpen(3, 6));
////        System.out.println("IsOpen :" + s.isOpen(4, 6));
////        System.out.println("IsOpen :" + s.isOpen(5, 6));
////        System.out.println("IsOpen :" + s.isOpen(6, 6));
//        System.out.println("IsFull :" + s.isFull(1, 1));
//
//
//        System.out.println("Percolate :" + s.percolates());
//        s.displayGrid();



    }

}
