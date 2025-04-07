
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

    //Finds and returns entry by its position
    public Entry getEntryByPosition(int index)
    {
        return entries.get(index);
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
    
}
