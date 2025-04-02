import java.awt.GridLayout;

/**
 * Class Name: FinanceTracker
 * Credit: Pablo Bandera Lopez
 * Created: 03/05/2025
 * Modified: 03/31/2025
 * 
 * Description: Contains main(), and instantiates the first app frame
 * 
 * Attributes:
 * 
 * Methods:
 * + <<constructor>>FinanceTracker()
 * + static main(String[] args):void
 * + runApp():void
 */

public class FinanceTracker
{
    //Constructor
    public FinanceTracker(){}

    public static void main(String[] args) throws LayoutMismatchException 
    {
        FinanceTracker ftApp = new FinanceTracker();
        ftApp.runApp();
    }   

    @SuppressWarnings("unused")
    public void runApp() throws LayoutMismatchException
    {   
        //App Init
        Entries entries = new Entries();
        FileManager fm = new FileManager();
        entries = loadWorkingFile(fm, entries);

        //App Start
        AppFrame mm = new MenuFrame("Main Menu", new GridLayout(3,0), entries);
    

    }

    public Entries loadWorkingFile(FileManager fm, Entries entries)
    {
        if(fm.filePathExists(fm.getCurrentMonth(), fm.getCurrentYear()))
        {
            entries = fm.readEntries(fm.getFilePath(fm.getCurrentMonth(), fm.getCurrentYear()));
        }
        
        return entries;
    }
}