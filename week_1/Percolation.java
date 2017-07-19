import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
    private boolean[][] grid;
    private int openSites;
    private final WeightedQuickUnionUF connections;
    
    public Percolation(int n) {
        
        if (n <= 0) throw new IllegalArgumentException();
        
        grid = new boolean[n][];
        
        for (int i = 0; i < grid.length; i++) {
            grid[i] = new boolean[n];
        }
        
        /* We initialise the union find datastructure with 1 more node
         * so that we can connect the first one with the first row. 
         * If there's a connection between the new node and any of the last row,
         * then the grid percolates.
         */
        connections = new WeightedQuickUnionUF((n*n)+1);
        for (int i = 1; i <= n; i++) {
            connections.union(0, i);
        }
    }
    
    public void open(int row, int col) {
        
        // Check if the arguments are in the grid
        this.isInsideBounds(row);
        this.isInsideBounds(col);
        
        // To open a site, set it to 1
        if (this.isOpen(row, col)) {
            return;
        }
        
        this.grid[row-1][col-1] = true;
        this.openSites++;
        
        this.connectTop(row, col);
        this.connectBottom(row, col);
        this.connectLeft(row, col);
        this.connectRight(row, col);
    
    }
    public boolean isOpen(int row, int col) {
        // Check if the arguments are in the grid
        this.isInsideBounds(row);
        this.isInsideBounds(col);
        
        boolean isOpen = this.grid[row-1][col-1];
        
        return isOpen;
    }
    public boolean isFull(int row, int col) {
    
        // Check if the arguments are in the grid
        this.isInsideBounds(row);
        this.isInsideBounds(col);
        
        boolean isFull = this.connections.connected(0, xyToIdx(row, col));
        isFull = isFull && this.isOpen(row, col);
        
        return isFull;
    
    }
    public int numberOfOpenSites() { return this.openSites; }
    public boolean percolates() {
    
        int n = this.grid.length;
        
        for (int i = n*(n -1)+1; i <= n*n; i++) {
            if (this.connections.connected(0, i)) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean isInsideBounds(int i) {
    
        if (i-1 >= 0 && i <= this.grid.length) {
            return true;
        }
        else { 
            throw new IllegalArgumentException();
        }
        
    }

    private int xyToIdx(int row, int col) {
    
        int n = this.grid.length;
        int idx = 0;
        
        for (int i = 1; i < row; i++) {
            idx += n;
        }
        
        idx = idx + col;
        
        return idx;
        
    
    }
    
    private void connectTop(int row, int col) {
        if (row-1 >= 1) {
            
            if (!this.isOpen(row-1, col)) {
                return;
            }
            
            this.connections.union(xyToIdx(row-1, col),  xyToIdx(row, col));
        }
    }
    private void connectBottom(int row, int col) {

     
        if (row+1 <= this.grid.length) {
            
            if (!this.isOpen(row+1, col)) {
                return;
            }
            
            this.connections.union(xyToIdx(row+1, col), xyToIdx(row, col));
        }
    }
    
    private void connectLeft(int row, int col) {

        if (col-1 >= 1) {
            
            if (!this.isOpen(row, col-1)) {
                return;
            }
                  
            this.connections.union(xyToIdx(row, col-1), xyToIdx(row, col));
        }
    }
    
    private void connectRight(int row, int col) {
 
        if (col+1 <= this.grid.length) {
        
            if (!this.isOpen(row, col+1)) {
                return;
            }
            
            this.connections.union(xyToIdx(row, col+1), xyToIdx(row, col));
        }
    }
    
}