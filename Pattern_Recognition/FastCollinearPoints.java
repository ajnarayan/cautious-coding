import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    public ArrayList<LineSegment> line = new ArrayList<>();
    
    public FastCollinearPoints(Point[] points)  {   // finds all line segments containing 4 or more points
        Point[] temp = points.clone();
        Arrays.sort(temp);
        for(int i = 0 ; i<temp.length; i++)
            for (int j = i+1 ; j<temp.length; j++){
            if (temp[i] == null)
                throw new NullPointerException();
            if (temp[i].compareTo(temp[j]) == 0 )
                throw new IllegalArgumentException("Duplicates found");
        }
        for( int i =0 ; i<temp.length; i++){
            //Think of p as the origin
            
            Arrays.sort(temp, temp[i].slopeOrder());
            //Check if any 3 (or more) adjacent points in the sorted order have equal slopes
            //with respect to p.
            int p=0, q =1 , r =2;
            while(r<temp.length){
                while(Double.compare(temp[p].slopeTo(temp[q]),temp[p].slopeTo(temp[r])) == 0) {
                    r++;    
                }
                //If so, these points, together with p, are collinear.
                if( temp[p].compareTo(temp[q])<0) 
                    line.add(new LineSegment(temp[p], temp[r-1]));
                             p = r;
                             r++;  
                             }            
        }
    }
   public int numberOfSegments()  {      // the number of line segments
       
       return line.size();
   }
   public LineSegment[] segments() {               // the line segments
       return line.toArray(new LineSegment[line.size()]);
   }
   
   }