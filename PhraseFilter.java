
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
    private String where;
    private String phrase;
    
    public PhraseFilter(String wherePF, String phrasePF) {
        where = wherePF;
        phrase = phrasePF;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        String entry = qe.getInfo();
        boolean found=false;
        if (where.equals("start")) {
            found = entry.startsWith(phrase);
        }
        else if (where.equals("end")) {
            found = entry.endsWith(phrase);
        }
        else if (where.equals("any")) {
            int check = entry.indexOf(phrase);
            if (check != -1) {
                found = true;
            }
            else {
                found = false;
            }
        }
        else {
            System.out.println("Error in function finding_where: key ''where'' not found.");
        }
        return found;
    }
    
    public String getName() {
        return "Phrase";
    }
}
