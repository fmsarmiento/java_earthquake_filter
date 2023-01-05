import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        Location loc = new Location(35.42, 139.43); //japan
        Filter f = new DistanceFilter(loc, 10000000); 
        Filter f2 = new PhraseFilter("end","Japan");
        ArrayList<QuakeEntry> m2  = filter(list, f); 
        ArrayList<QuakeEntry> m = filter(m2, f2);
        for (QuakeEntry qe: m) { 
            System.out.println(qe);
        } 
        System.out.println("Length :"+m.size());
    }
    
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0,2.0));
        maf.addFilter(new DepthFilter(-100000,-10000.0));
        maf.addFilter(new PhraseFilter("any","a"));
        ArrayList<QuakeEntry> quakes = filter(list,maf);
        for (QuakeEntry qe: quakes) { 
            System.out.println(qe);
        } 
        System.out.println("length is "+quakes.size());
    }
    
    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        Location loc = new Location(36.1314, -95.9372); //tulsa, oklahoma
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0,3.0));
        maf.addFilter(new DistanceFilter(loc, 10000000));
        maf.addFilter(new PhraseFilter("any","Ca"));
        ArrayList<QuakeEntry> quakes = filter(list,maf);
        for (QuakeEntry qe: quakes) { 
            System.out.println(qe);
        }
        System.out.print("Length is "+quakes.size());
        System.out.print("Filters used: ");
        System.out.println(maf.getName());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
