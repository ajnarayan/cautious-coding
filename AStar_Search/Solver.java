import edu.princeton.cs.algs4.MinPQ;
import java.util.Stack;
import java.util.Comparator;

/*
 * The success of this approach hinges on the choice of priority function for a search node. 
 * We consider two priority functions: Hamming priority function & Manhattan priority function.
 * 
 * To solve the puzzle from a given search node on the priority queue, 
 * the total number of moves we need to make (including those already made) is at least its priority,
 * using either the Hamming or Manhattan priority function. (For Hamming priority, 
 * this is true because each block that is out of place must move at least once to reach its goal position. 
 * For Manhattan priority, this is true because each block must move its Manhattan distance from its goal position.
 * Note that we do not count the blank square when computing the Hamming or Manhattan priorities.) Consequently, when the goal board is dequeued,
 * we have discovered not only a sequence of moves from the initial board to the goal board, but one that makes the fewest number of moves. 
 */



public class Solver {
    private MinPQ<SearchNode> priorityQue; 
    private MinPQ<SearchNode> priorityQueTwin;
    private boolean solved = true;
    private Stack<Board> stackBoard;
    
    
    //Solver Constructor
    public Solver(Board initial)  {         // find a solution to the initial board (using the A* algorithm)
        if (initial == null)
            throw new NullPointerException();
        solved = true;
        priorityQue = new MinPQ<SearchNode>();
        priorityQueTwin = new MinPQ<SearchNode>();
         stackBoard = new Stack<Board>();
        Board board = initial;
        if(board.isGoal()){
            solved = true;
            this.stackBoard.push(board);
            return;
        }
        Board twinBoard = initial.twin();
        if(twinBoard.isGoal()){
            solved = false;
            return;
        }
        //First, insert the initial search node (the initial board, 0 moves, and a null previous search node) into a priority queue.
        SearchNode node = new SearchNode(board,0,null); 
        priorityQue.insert(node);
        SearchNode twinNode = new SearchNode(twinBoard,0,null);
        priorityQueTwin.insert(twinNode);
        boolean solve = false;
        while(!solve){
            node = priorityQue.delMin();
            board = node.board;
            
            
            twinNode = priorityQueTwin.delMin();
            twinBoard = twinNode.board;
            
            if(board.isGoal()){
                solved = true;
                solve = true;
                this.stackBoard.push(board);
                while(node.prevSearchNode != null){
                    node = node.prevSearchNode;   
                    stackBoard.push(node.board);
                }
                //solved
                return;     
            }
            
            //same as above for twin
            if(twinBoard.isGoal()){
                solved = false;
                solve = true;
                //cannot be solved
                return;     
            }
            node.numOfMoves++;
            twinNode.numOfMoves++;
       
        
        //insert onto the priority queue all neighboring search nodes (those that can be reached in one move from the dequeued search node).
        //Repeat this procedure until the search node dequeued corresponds to a goal board.
        Iterable<Board> neighbor = board.neighbors();
        //for node:
        for(Board b : neighbor){
            if(node.prevSearchNode == null && !b.equals(node.prevSearchNode.board)){
            
                SearchNode n = new SearchNode(b, node.numOfMoves, node);
             priorityQue.insert(n);
            }
            
        }
        
        //Same for twin
        Iterable<Board> tneighbor = twinBoard.neighbors();
        //for node:
        for(Board b : tneighbor){
            if(twinNode.prevSearchNode == null && !b.equals(twinNode.prevSearchNode.board)){
           
                SearchNode n = new SearchNode(b, twinNode.numOfMoves, twinNode);
             priorityQueTwin.insert(n);
            }
            
        }
  
    
         }//end of while
    }
    
    
    public boolean isSolvable() {           // is the initial board solvable?
        return solved;   
        
    }
    public int moves()  {
        // min number of moves to solve initial board; -1 if unsolvable
        if(!solved)
            return -1;
        else
            return stackBoard.size()-1;
    }
    
    
    
    public Iterable<Board> solution()  {    // sequence of boards in a shortest solution; null if unsolvable
        
     if(!solved)
         return null;
     else
         return stackBoard;

    }
    
    private class SearchNode implements Comparator<SearchNode>{
        //Search node of the game to be a board, 
        //the number of moves made to reach the board, and the previous search node.
        private  Board board; 
        private  int numOfMoves;
        private SearchNode prevSearchNode;
        private int priority = -1;
        
        public SearchNode(Board board, int numOfMoves, SearchNode searchNode){
            this.board = board;
            this.numOfMoves = numOfMoves;
            this.prevSearchNode = searchNode;
        }
  
        //create comparator of hamming vs manhattan    
        @Override 
        public int compare(SearchNode a, SearchNode b){
            return a.board.manhattan() + a.numOfMoves - b.board.manhattan() - b.numOfMoves;
        }
    }//end of SearchNode Class
    
    
    
    
    public static void main(String[] args) {// solve a slider puzzle (given below)
        
    }
}