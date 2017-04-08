import java.util.ArrayList;

public class BruteCollinearPoints {
    
    public ArrayList<LineSegment> line = new ArrayList<>();
    public BruteCollinearPoints(Point[] points) {   // finds all line segments containing 4 points
        if(point == null)
            throw new NullPointerException();
        
        for(int i = 0 ; i<points.length; i++)
            for (j = i+1 ; j<points.length; j++)
            if (points[i].compareTo(points[j]) == 0 )
            throw new IllegalArgumentException("Duplicates found");
    }
    
    
    
    
public int numberOfSegments()  {      // the number of line segments
  return line.size();   
    
}
public LineSegment[] segments()    {            // the line segments
   return line.toArray(new lineSegment[line.size()]);    

}

}