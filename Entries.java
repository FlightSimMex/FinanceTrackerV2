
import java.util.ArrayList;


/**
 * Class Name: Entries
 * Credit: Pablo Bandera Lopez
 * Created: 03/24/2025
 * Modified: 04/04/2025
 * 
 * Description: Collection Class for Entry objects. Acts as runtime memory for the entries. Automatically updates file with changes.
 * 
 * Attributes:
 * - entries: ArrayList<Entry>
 * - fm: FileManager
 * 
 * Methods:
 * + <<constructor>>Entry(int entryNumber):void
 * + loadFile(): void
 * + addEntry(Entry): void
 * + addEntryNoUpdate(Entry): void
 * + removeEntry(int): void
 * + clearList(): void
 * + updateEntry(int, Entry): void
 * + getEntryByNmEntry(int): Entry
 * + getEntryNumbers(): String []
 * 
 */

public class Entries {

    /* Attributes */
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Entry> entries;
    private static FileManager fm;
    

    /*Constructor */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Entries()
    {
        this.entries = new ArrayList<>();
        fm = new FileManager();

    }

    /*Methods*/

    //Returns the next entry number
    public int getNextEntryNumber()
    {   
        if(!entries.isEmpty()){
            return 1+entries.get(entries.size()-1).getEntryNumber();
        }else{
            return 1;
        }
        
    }

    //Adds an entry to the entries array and updates the file
    public void addEntry(Entry e)
    {
        if(e != null)
        {
            entries.add(e);
        }
        fm.updateFile(this);
        
    }

    public void addEntryNoUpdate(Entry e)
    {
        if(e != null)
        {
            entries.add(e);
        }
        
    }

    //Removes an entry from the entries array and updates the file
    public void removeEntry(int entryNumber)
    {
        for(int i = 0; i < entries.size(); i++)
        {
            if(entries.get(i).getEntryNumber() == entryNumber)
            {
                entries.remove(i);
            }
        }
        fm.updateFile(this);
    }

    //Removes an entry from the entries array and updates the file
    public void clearList()
    {
        this.entries.clear();
    }
    

    //Updates an entry in the entries array and updates the file
    public void updateEntry(int entryNumber, Entry e)
    {
        for(int i = 0; i < entries.size(); i++)
        {
            if(entries.get(i).getEntryNumber() == entryNumber)
            {
                entries.set(i, e);
            }
        }
        fm.updateFile(this);
    }

    //Finds and returns an entry by its entry number
    public Entry getEntryByNmEntry(int entryNumber){
        for(Entry e : entries)
        {
            if(e.getEntryNumber() == entryNumber)
            {
                return e;
            }
        }
        return null;

    }

    public ArrayList<Entry> getEntries()
    {
        return this.entries;
    }

    public int getNumberOfEntries()
    {
        return entries.size();
    }

    public String [] getEntryNumbers()
    {
       
        ArrayList<String> returns = new ArrayList<>();
        for(Entry e : this.entries)
        {   
            returns.add(Integer.toString(e.getEntryNumber()));
        }   
        String arr [] = new String[returns.size()];
        arr = returns.toArray(arr);
        return arr;
    }

    
    
    /*TEST MAIN */
    public static void main(String[] args) {
        Entries entries = new Entries();
        try {
        Entry e = new Entry(1);
        e.setAmount(100.0);
        e.setCategory(1);
        e.setSubcategory(1);
        e.setSubcategory2(1);
        e.setSubcategory3(1);
        e.setSubcategory4(1);
        e.setAccount(1);
        e.setComment("This is a comment1");
        entries.addEntry(e);
        
            
        } catch (InvalidEntryException e) {System.err.println(e);}

        Entry find = entries.getEntryByNmEntry(1);
        if(find != null){System.out.println("Entry Found: "+find.getEntryString());}

        // for(Entry e : entries.entries)
        // {
        //     System.out.println(e.getEntryString());
        // }

        // entries.removeEntry(1);
        // System.out.println("After removing entry 1:");
        // for(Entry e : entries.entries)
        // {
        //     System.out.println(e.getEntryString());
        // }

        //Entry e2 = new Entry(2);
        // e2.setAmount(200.0);
        // e2.setCategory(2);
        // e2.setSubcategory(2);
        // e2.setSubcategory2(2);
        // e2.setSubcategory3(2);
        // e2.setSubcategory4(2);
        // e2.setAccount(2);
        // e2.setComment("This is a comment2");
        // entries.addEntry(e2);
    }



    
}
