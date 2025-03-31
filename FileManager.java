/**
 * Class Name: EntryFrame
 * Credit: Pablo Bandera Lopez
 * Created: 03/05/2025
 * Modified: 03/24/2025
 * 
 * Description: Class Extends App Frame, creates main menu for user selection
 * 
 * Attributes:
 * 
 * Methods:
 * + <<constructor>>MenuFrame(String, LayoutManager)
 * 
 * 
 */
public class FileManager {
    
    

    public FileManager()//Constructor
    {   
        
    }



    public void updateFile(Entries entries)//Updates the file with the current entries
    {
        for(Entry e : entries.getEntries())
        {
            System.out.println(e.toString());
        }
    }

    public Entry readEntry()//Loads the file into the entries
    {
        return null;
    }

    public int getNumberOfEntries()//Returns the number of entries in the file
    {
        return 0;
    }

   
}
