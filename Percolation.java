public class Percolation {
    private int[][] grid;
    private int openSites;
    private int gridSize;
    private int gridLength;
    private double percolationThreshold = 0.593;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Percolation size must be a positive integer");
        }
        openSites = 0;
        gridLength = n;
        gridSize = n * n;
        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Let 0 mean that a site is blocked
                grid[i][j] = 0;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if ((row < 0 || row > gridLength) || (col < 0 || col > gridLength)) {
            throw new IllegalArgumentException("Index out of Range");
        }

        grid[row - 1][col - 1] = 1;
        openSites += 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if ((row < 0 || row > gridLength) || (col < 0 || col > gridLength)) {
            throw new IllegalArgumentException("Index out of Range");
        }

        if (grid[row - 1][col - 1] == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if ((row < 0 || row > gridLength) || (col < 0 || col > gridLength)) {
            throw new IllegalArgumentException("Index out of Range");
        }

        if (grid[row - 1][col - 1] == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        if (((double) openSites / (double) gridSize) > percolationThreshold) {
            return true;
        }
        else {
            return false;
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int iterations = 0;
        int maxcount = n * n;

        Percolation perc = new Percolation(n);
        while (!perc.percolates() || iterations > maxcount) {
            int randomCol = (int) (Math.random() * (n));
            int randomRow = (int) (Math.random() * (n));

            if (!perc.isOpen(randomRow, randomCol)) {
                perc.open(randomRow, randomCol);
            }
            iterations += 1;
        }

    }
}
