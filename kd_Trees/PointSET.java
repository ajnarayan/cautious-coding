import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.TreeSet;
import edu.princeton.cs.algs4.Queue;
/*
 * https://docs.oracle.com/javase/7/docs/api/java/util/TreeSet.html
 */

public class PointSET {
    
    private TreeSet<Point2D> set;
    
    
    public PointSET() {                              // construct an empty set of points 
        set = new TreeSet<Point2D>();    
    }
    
    
    public boolean isEmpty() {                     // is the set empty? 
     return set.size() == 0;    
    }
    
    
    public int size()  {                       // number of points in the set 
     return set.size();   
        
    }
        public void insert(Point2D p) {             // add the point to the set (if it is not already in the set)
        if(p == null)
            throw new java.lang.NullPointerException();
        
        set.add(p);
    }
    
        
    public boolean contains(Point2D p) {           // does the set contain point p? 
        if(p == null)
            throw new java.lang.NullPointerException();   
        
        return set.contains((Object) p);
    }
    
    public  void draw(){                         // draw all points to standard draw 
        for(Point2D p: set)
            p.draw();
    }
        
        
        public Iterable<Point2D> range(RectHV rect)  {           // all points that are inside the rectangle 
        Queue<Point2D> q = new Queue<Point2D>();
            if(rect == null)
            throw new java.lang.NullPointerException();   
            for(Point2D p : set)
                if(rect.contains(p))
                  q.enqueue(p);
        
        return q;
    }
        
        
        public Point2D nearest(Point2D p) {            // a nearest neighbor in the set to point p; null if the set is empty 
       
            if(p == null)
            throw new java.lang.NullPointerException();
        Point2D point = null;
            for(Point2D p2 : set)
                if(point == null) 
                   point = p;
               
                
        return point;
    }
    
        public static void main(String[] args)   {               // unit testing of the methods (optional) 
        }
}