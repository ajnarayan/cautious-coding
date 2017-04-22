import java.util.TreeSet;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Point2D;

public class KdTree{
    private static final boolean VERTICAL = true;
    private static final boolean HORIZONTAL = false;
    private Node root;
    private int size;  
    private RectHV r; 
    
    public KdTree(){
       root = null;
        r = new RectHV(0,0,1,1);      // construct the rectangle [xmin, xmax] x [ymin, ymax]           
    }
    
    public int size(){
     return size;   
    }
    
    public boolean isEmpty(){
     return size() == 0;   
    }
    
    public boolean contains(Point2D p) {
        if (p == null) 
            throw new java.lang.NullPointerException();
        return contains(p, x, VERTICAL);
    }
    
    public boolean contains(Point2D p, Node x, boolean horver){
        
     if (x == null)
         return false;
     
     if(x.point.equals(p))
           return true;
     double diff;
     if(horver == VERTICAL)
         diff = p.x() - x.point.x();
       else
         diff = p.y() - x.point.y();
     if(diff< 0) 
         return compare(p,x.left,!horver);
     else
         return compare(p,x.right,!horver);

    }
    
    public void draw() {
        draw(root, VERTICAL);
    }
    
    public void draw(Node x, boolean horver){
        /*A 2d-tree divides the unit square in a simple way: 
         * all the points to the left of the root go in the left subtree; all those to the
         * right go in the right subtree; and so forth, recursively. Your draw() method should 
         * draw all of the points to standard draw in black and the subdivisions in red (for vertical splits) 
         * and blue (for horizontal splits). This method need not be efficient—it is primarily for debugging.
         */
        if (x == null) {
            throw new java.lang.NullPointerException();
        }
         StdDraw.setPenColor(StdDraw.BLACK);
        x.point.draw();
        if (horver == VERTICAL) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(x.point.x(), x.rectangle.ymin(), x.point.x(), x.rectangle.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(x.rectangle.xmin(), x.point.y(), x.rectangle.xmax(), x.point.y());
        }
        if (x.left != null) 
            draw(x.left, !horver);
        
        if (x.right != null) 
            draw(x.right, !horver);

    }
    
   public void insert(Point2D p) {             // add the point to the set (if it is not already in the set)
       
       /*Search and insert. The algorithms for search and insert are similar to those for BSTs, 
        * but at the root we use the x-coordinate (if the point to be inserted has a smaller x-coordinate than 
        * the point at the root, go left; otherwise go right); then at the next level, we use the y-coordinate 
        * (if the point to be inserted has a smaller y-coordinate than the point in the node, go left; otherwise go right); 
        * then at the next level the x-coordinate, and so forth.
        */
       if(p == null)
            throw new java.lang.NullPointerException();
        
        root = insert(root,p, r, VERTICAL);
        
    }
   
   private Node insert(Node x, Point2D p, RectHV r, boolean horver){
       
       if(root == null){
          size++;
       return new Node( p, r, size);
       }
       
       if(x.point.equals(p))
           return x;
       double diff; 
       RectHV next;  
       if(horver = VERTICAL) {
           diff = p.x() - x.point.x();
           if(diff<0){
               next = new RectHV(x.rectangle.xmin(), x.rectangle.ymin(), x.point.x(), x.rectangle.ymax());
               x.left = insert(x.left, p, next, !horver) ;   
           }else{
               next = new RectHV(x.point.x(), x.rectangle.ymin(), x.rectangle.xmax(), x.rectangle.ymax());
               x.right = insert(x.right, p, next, !horver) ; 
               
           }
       }//eof vertical 
       else{
        //if horizontal    
           diff = p.y() - x.point.y();
           if(diff<0){
               next = new RectHV(x.rectangle.xmin(), x.rectangle.ymin(), x.rectangle.xmax(), x.point.y());
               x.left = insert(x.left, p, next, !horver) ;   
           }else{
               next = new RectHV(x.rectangle.xmin(), x.point.y(), x.rectangle.xmax(), x.rectangle.ymax());
               x.right = insert(x.right, p, next, !horver) ; 
           }   
       }
       return x;
   }//eof insert
   
   
    private static class Node{
        private Node left;
        private Node right;
        private int count;
        private RectHV rectangle;
        private Point2D point;
        
        public Node(Point2D point, RectHV rectangle, int count){
            this.point = point;
            this.rectangle = rectangle;
            this.count = count; 
        }
    }//eof Node
    
    
    
    
}
