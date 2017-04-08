import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    
    public ArrayList<LineSegment> line = new ArrayList<>();
    
    public BruteCollinearPoints(Point[] points) {   // finds all line segments containing 4 points
        Point[] temp = points.clone();
        
        for(int i = 0 ; i<temp.length; i++)
            for (int j = i+1 ; j<temp.length; j++){
            if (temp[i] == null)
                throw new NullPointerException();
            if (temp[i].compareTo(temp[j]) == 0 )
                throw new IllegalArgumentException("Duplicates found");
        }
        
        Arrays.sort(temp);
        //The method segments() should include each line segment containing 4 points exactly once. 
        //If 4 points appear on a line segment in the order p→q→r→s, then you should include either the line segment p→s or s→p (but not both)
        //and you should not include subsegments such as p→r or q→r. For simplicity, we will not supply any input to BruteCollinearPoints that has 5 
        //or more collinear points.
        for(int p = 0; p< temp.length-3; p++)
            for(int q=p+1 ; q<temp.length-2; q++)
             for(int r = q+1; r<temp.length-1; r++)
               for(int  s = r+1 ; s< temp.length; s++){
                    if( temp[p].slopeTo(temp[q]) == temp[p].slopeTo(temp[r]) && temp[p].slopeTo(temp[q]) == temp[p].slopeTo(temp[s]))
                           //either p->s or s->p
                           line.add(new LineSegment(temp[p], temp[s]));
        }   
    }
    
    
    
    public int numberOfSegments()  {      // the number of line segments
        return line.size();   
        
    }
    public LineSegment[] segments()    {            // the line segments
        return line.toArray(new LineSegment[line.size()]);    
        
    }
    
}