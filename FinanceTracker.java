import java.awt.GridLayout;

/**
 * Class Name: FinanceTracker
 * Credit: Pablo Bandera Lopez
 * Created: 03/05/2025
 * Modified:
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

    public void runApp() throws LayoutMismatchException
    {   
        //App Init
        AppFrame mm = new MenuFrame("Main Menu", new GridLayout(3,0));
    

    }
}