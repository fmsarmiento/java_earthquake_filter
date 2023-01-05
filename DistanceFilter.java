
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private Location loc;
    private double distMax;
    
    public DistanceFilter(Location loc2, double dist) {
        loc = loc2;
        distMax = dist;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return (qe.getLocation().distanceTo(loc) < distMax);
    }
    
    public String getName() {
        return "Distance";
    }
}
