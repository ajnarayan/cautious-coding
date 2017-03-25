import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.Arrays;

public class Percolation{
    // create n-by-n grid, with all sites blocked
    public WeightedQuickUnionUF wUnionFind;
    private final boolean[][] grid;
    private int sizeOfGrid;
    
    
    
    public Percolation(int n){
    wUnionFind = new WeightedQuickUnionUF(n*n);  
    //grid is blocked initially with all values as False.
    grid = new boolean[n][n];
    sizeOfGrid = n;
    }       

    public void open(int row, int col) { 
        // open site (row, col) if it is not open already
        if (!grid[row-1][col-1]){
            grid[row-1][col-1] = true;
        }
        //need to link it to neighbors too!! 
        
    }
    public boolean isOpen(int row, int col) { // is site (row, col) open?
        return grid[row-1][col-1];
    }
    
    private int xyTo1D(int row, int col){
     //map from a 2-dimensional (row, column) pair to a 1-dimensional union find object index   
        return (row-1) * sizeOfGrid + col;   
    }
    public boolean isFull(int row, int col){  // is site (row, col) full?
   // A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites.
       //Check if it is connected from top (0,0) to current index in 1-D converted format
        if(isOpen(row,col)){
            int convertedIndex = xyTo1D(row,col);
            return wUnionFind.connected(0,convertedIndex) ;
        }
        return false;
    }
    public int numberOfOpenSites() {      // number of open sites
        int count = 0;
        for(int i=0; i<sizeOfGrid; i++){
            for(int j=0; j<sizeOfGrid;j++){
                if(isOpen(i,j)){
                    count ++;
                }
            }
        }
        return count;
    }
       
    public boolean percolates()      {        // does the system percolate?
        //if it is connected from top to bottom, it percolates:
       return wUnionFind.connected(0,sizeOfGrid*sizeOfGrid+1);
    }
    public static void main(String[] args) {  // test client (optional)
        Percolation p = new Percolation(2);
        System.out.println(Arrays.deepToString(p.grid));
        p.open(1,1);
        p.open(2,1);
        System.out.println(Arrays.deepToString(p.grid));
        System.out.println(p.isOpen(1,1));
        System.out.println(p.isFull(2,1));
    }
    }


