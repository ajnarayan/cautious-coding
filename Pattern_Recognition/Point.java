
/*
 * The compareTo() method should compare points by their y-coordinates, breaking ties by their x-coordinates. 
 * Formally, the invoking point (x0, y0) is less than the argument point (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
 * 
 * The slopeTo() method should return the slope between the invoking point (x0, y0) and the argument point (x1, y1), 
 * which is given by the formula (y1 − y0) / (x1 − x0). Treat the slope of a horizontal line segment as positive zero; 
 * treat the slope of a vertical line segment as positive infinity; 
 * treat the slope of a degenerate line segment (between a point and itself) as negative infinity.
 * 
 * The slopeOrder() method should return a comparator that compares its two argument points by the slopes
 * they make with the invoking point (x0, y0). Formally, the point (x1, y1) is less than the point (x2, y2) if 
 * and only if the slope (y1 − y0) / (x1 − x0) is less than the slope (y2 − y0) / (x2 − x0). Treat horizontal, vertical, 
 * and degenerate line segments as in the slopeTo() method.
 */
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point
    
    public Point(int x, int y) {                          // constructs the point (x, y)
        this.x = x;
        this.y = y;
    }
    public   void draw()   {                            // draws this point
        StdDraw.point(x, y);
    }
    
    public   void drawTo(Point that)   {                // draws the line segment from this point to that point
        StdDraw.line(this.x, this.y, that.x, that.y);    
    }
    public String toString() {                          // string representation
        return "(" + x + ", " + y + ")";   
        
    }
    public int compareTo(Point that) {    // compare two points by y-coordinates, breaking ties by x-coordinates
        if (that == null) {
            throw new NullPointerException();
        }
        
        if (this.y < that.y || (that.y == this.y && this.x<that.x))
            return -1;
        
            return 0; 
    }
    public double slopeTo(Point that)  {     // the slope between this point and that point
        if (that.x == this.x && that.y == this.y)
            return Double.NEGATIVE_INFINITY;
        if (that.y-this.y == 0.0)
            return 0.0; 
        if (that.x-this.x == 0.0)
            return  Double.POSITIVE_INFINITY;
        
        return (that.y-this.y)/(double)(that.x-this.x);
    }
   
    //https://stackoverflow.com/questions/22499999/public-final-comparatorpoint-slope-order
    public Comparator<Point> slopeOrder()   {           // compare two points by slopes they make with this point
       return new Comparator<Point>() {
    @Override
        public int compare(Point a, Point b){
            double slopea = slopeTo(a);
            double slopeb = slopeTo(b);
            return Double.compare(slopea, slopeb); 
        }
       };
    }
             
        public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}