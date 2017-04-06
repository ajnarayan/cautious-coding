
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





public class Point implements Comparable<Point> {
    public Point(int x, int y)                         // constructs the point (x, y)
        
        public   void draw()                               // draws this point
        public   void drawTo(Point that)                   // draws the line segment from this point to that point
        public String toString()                           // string representation
        
        public int compareTo(Point that)     // compare two points by y-coordinates, breaking ties by x-coordinates
        public double slopeTo(Point that)       // the slope between this point and that point
        public Comparator<Point> slopeOrder()              // compare two points by slopes they make with this point
}