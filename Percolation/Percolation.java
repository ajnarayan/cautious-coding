import edu.princeton.cs.algs4.WeightedQuickUnionUF;
//import java.util.Arrays;
//import java.util.Scanner;

public class Percolation{
    // create n-by-n grid, with all sites blocked
    private WeightedQuickUnionUF wUnionFind;
    private final boolean[][] grid;
    private int sizeOfGrid;
    private int noOfOpen;
    
   
    public Percolation(int n){
       if (n <= 0) {
            throw new IllegalArgumentException("n not >0");
        } 
     sizeOfGrid = n;
    wUnionFind = new WeightedQuickUnionUF(sizeOfGrid*sizeOfGrid+2);  
    //grid is blocked initially with all values as False.
    grid = new boolean[sizeOfGrid][sizeOfGrid];
    noOfOpen=0;
    }       

    public void open(int row, int col) { 
        // open site (row, col) if it is not open already
        if (!isOpen(row,col)){    
            noOfOpen++;
        //need to link it to neighbors too!! 
        if (row == 1 ) //if it is the second row
            wUnionFind.union(xyTo1D(row,col), 0);
        if (row == sizeOfGrid ) //if it is the last but one
            wUnionFind.union(xyTo1D(row,col), sizeOfGrid * sizeOfGrid + 1);
        //neighbors need to be connected 
        if ( col > 1 && isOpen(row, col-1)) 
            wUnionFind.union(xyTo1D(row,col), xyTo1D(row,col-1));
        if ( row > 1 && isOpen(row-1, col)) 
            wUnionFind.union(xyTo1D(row,col), xyTo1D(row-1,col));
        if ( col < sizeOfGrid && isOpen(row, col+1)) 
            wUnionFind.union(xyTo1D(row,col), xyTo1D(row,col+1));
        if ( row < sizeOfGrid && isOpen(row+1, col)) 
            wUnionFind.union(xyTo1D(row,col), xyTo1D(row+1,col));
        
        grid[row-1][col-1] = true;
    }
    }
    public boolean isOpen(int row, int col) { // is site (row, col) open?
        return grid[row-1][col-1];
    }
    
    private int xyTo1D(int row, int col){
     //map from a 2-dimensional (row, column) pair to a 1-dimensional union find object index   
        return  sizeOfGrid * (row-1) + col;   
    }
    public boolean isFull(int row, int col){  // is site (row, col) full?
   // A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites.
       //Check if it is connected from top (0,0) to current index in 1-D converted format
        if(isOpen(row,col)){
            int convertedIndex = xyTo1D(row,col);
            return wUnionFind.connected(0,convertedIndex) ;
        }
        else{
        return false;
        
        }  
    }
    public int numberOfOpenSites() {      // number of open sites
//        int count = 0;
//        for(int i=0; i<sizeOfGrid; i++){
//            for(int j=0; j<sizeOfGrid;j++){
//                if(isOpen(i,j)){
//                    count ++;
//                }
//            }
//        }
//        return count;
        return noOfOpen;
    }
       
    public boolean percolates()      {        // does the system percolate?
        //if it is connected from top to bottom, it percolates:
       return wUnionFind.connected(0, sizeOfGrid*sizeOfGrid+1 );
    }
    
   
   public static void main(String[] args) {  // test client (optional)
      //Percolation p = new Percolation(6);
    //  System.out.println(Arrays.deepToString(p.grid));      
//   for ( int k =0 ; k<18 ; k++)
//   { 
//      Scanner in = new Scanner(System.in);
//      int i = in.nextInt();
//      int j = in.nextInt();
//      p.open(i,j);
//      System.out.println(p.isOpen(i,j));
//      System.out.println(p.percolates());
//      p.open(i,j);
//      System.out.println(p.numberOfOpenSites());
//      System.out.println(p.isFull(i,j));
//   }
  
      System.out.println("------------------------------ ");
    //  System.out.println(Arrays.deepToString(p.grid));
      //System.out.println(p.isOpen(1,6));
   }
    }


