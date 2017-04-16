import java.util.Stack;
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
            int x0 = (board[i][j]-1) / dimension();
            int y0 = (board[i][j]-1) % dimension();
            man += Math.abs(i-x0) + Math.abs(j-y0);
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
 
            int temp = board[i][j];
            board[i][j] = board[k][l];
            board[k][l] = temp;
            return true; 
    }
    
    public boolean equals(Object y) {       // does this board equal y?
        //from http://algs4.cs.princeton.edu/12oop/Date.java.html
        if(this == y)
            return true;
        if (this == null)
            return false;
       
        Board that = (Board) y;
        if (this.size != that.size)
            return false;
        
        return this.toString().equals(that.toString());   
    }  
    
    public Iterable<Board> neighbors(){     // all neighboring boards
        // To reduce unnecessary exploration of useless search nodes, when considering the
        //neighbors of a search node, don't enqueue a neighbor if its board is the same as the board of the previous search node.   
        Stack<Board> boardStack = new Stack<Board>();
        int k =0, l=0;
        boolean flag = false;
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board.length; j++){
            if(board[i][j] ==0) {
                k = i;
                l = j;
                break;
            }
            
        }
        }
        Board b1 = new Board(board);
        if(k-1 >=0){
        boolean neighbor = b1.swap(k, l, k -1, l);
        if(neighbor)
            boardStack.push(b1);
        }
        
        b1 = new Board(board);
        if (l-1 >=0){
        boolean neighbor = b1.swap(k, l, k, l-1);
        if(neighbor)
            boardStack.push(b1);
        }
        
        b1 = new Board(board);
        if(k+1< board.length){
        boolean neighbor = b1.swap(k, l, k+1, l);
        if(neighbor)
            boardStack.push(b1);
        }
        
        b1 = new Board(board);
        if(l+1<board.length){
        boolean neighbor = b1.swap(k, l, k, l+1);
        if(neighbor)
            boardStack.push(b1);
        }
        
        return boardStack;
        
    }
    
    public String toString()  {             // string representation of this board (in the output format specified below)
        StringBuilder s = new StringBuilder();
        s.append(size + "\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();  
    }
    
    public static void main(String[] args){ // unit tests (not graded)
        
    }
}
