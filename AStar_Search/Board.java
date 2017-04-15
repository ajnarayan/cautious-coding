public class Board {
    private final int size; 
    private final int[][] board;
    
    
    public Board(int[][] blocks)   {        // construct a board from an n-by-n array of blocks
        // (where blocks[i][j] = block in row i, column j)
        size = blocks.length;
        board = new int[size][];
        for(int i=0; i<size; i++)
            board[i] = blocks[i].clone();
    }
    
    public int dimension() {                // board dimension n
        return size; 
    }
    
    public int hamming()  {                 // number of blocks out of place
        int ham = 0; 
        for(int i=0; i<size; i++) 
            for(int j=0; j<size; j++){
            if(board[i][j] != (1 + i*dimension()+j) && board[i][j]!=0)
                ham++;   
        }
        return ham; 
    }    
    
    public int manhattan() {                // sum of Manhattan distances between blocks and goal
        int man = 0;
        for(int i=0; i<size; i++) 
            for(int j=0; j<size; j++)
            if(board[i][j]!=0 && board[i][j] != (1 + i*size+j) ){
            //int distance = Math.abs(x1-x0) + Math.abs(y1-y0);
            x0 = (board[i][j]) / dimension();
            y0 = (board[i][j]) % dimentsion();
            man += Math.abs(i-x0) + Math.abs(i+y0);
            
        }
        return man;
    }
    
    public boolean isGoal() {               // is this board the goal board?
//     boolean flag = True;
//        for(int i=0; i<size; i++) 
//            for(int j=0; j<size; j++)  
//              if(board[i][j]!=(1 + i*dimension()+j))
//                    flag = false;
//        return flag;
        return hamming() == 0;
        
    }
    
    public Board twin()  {                  // a board that is obtained by exchanging any pair of blocks
        //lead to the goal board if we modify the initial board by swapping any pair of blocks (the blank square is not a block).
        Board b = new Board(board);
        for(int i=0; i<dimension(); i++) 
            for(int j=0; j<dimension()-1; j++)
            if(board[i][j]!=0 && board[i][j+1]!=0)
        { //swap (i,j) and (i,j+1) 
            b.swap(i,j,i,j+1);
            return b;
        }
        return b;
    }
    
    private boolean swap(int i, int j , int k, int l){
        if(i>0 || i<= dimension() || j>0 || j<=dimension() ){
            int temp = board[i][j];
                board[i][j] = board[i][j+1];
                board[i][j+1] = temp;
                return true; 
        }
        else
            return false; 
    }
    
public boolean equals(Object y) {       // does this board equal y?
    //from http://algs4.cs.princeton.edu/12oop/Date.java.html
    if(this == y)
        return true;
    if (this == null)
        return false;
    if (y.getClass() != this.getClass()) return false;
    Board that = (Board) y;
    return this.toString().equals(that.toString());   
}  

private int[] findVal(int val){
    int ij = {0,0};
    boolean flag = false;
    for(int i=0; i<dimension(); i++) 
        for(int j=0; j<dimension()-1; j++){
        if(board[i][j] ==val) {
            
         ij[0] = i;
         ij[1] = j;
         flag = true;
         break;
        }
              
    }
    if(flag)
        break; 
}


public Iterable<Board> neighbors(){     // all neighboring boards
 // To reduce unnecessary exploration of useless search nodes, when considering the
    //neighbors of a search node, don't enqueue a neighbor if its board is the same as the board of the previous search node.   
    Stack<Board> boardStack = new Stack<Board>();
    int[] ij = findVal(0);
    Board b1 = new Board(board);
    boolean neighbor = b1.swap(ij[0], ij[1], ij[0] -1, ij[1]);
    if(neighbor)
        boardStack.push(b1);
    
    b1 = new Board(board);
    boolean neighbor = b1.swap(ij[0], ij[1], ij[0], ij[1]-1);
    if(neighbor)
        boardStack.push(b1);
    
    b1 = new Board(board);
    boolean neighbor = b1.swap(ij[0], ij[1], ij[0]+1, ij[1]);
    if(neighbor)
        boardStack.push(b1);
    
    b1 = new Board(board);
    boolean neighbor = b1.swap(ij[0], ij[1], ij[0], ij[1]+1);
    if(neighbor)
        boardStack.push(b1);
    
    return b1;
    
}
    public String toString()  {             // string representation of this board (in the output format specified below)
    StringBuilder s = new StringBuilder();
    s.append(n + "\n");
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            s.append(String.format("%2d ", tiles[i][j]));
        }
        s.append("\n");
    }
    return s.toString();
    
    
}
    public static void main(String[] args){ // unit tests (not graded)
        
    }
    }
