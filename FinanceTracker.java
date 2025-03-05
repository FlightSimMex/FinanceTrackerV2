import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

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
        AppFrame mm = new TestFrame("Main Menu", new GridLayout(0,3));

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.BLUE);
        panel1.setVisible(true);
        panel1.setBounds(0,0,100,100);
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.GREEN);
        panel2.setVisible(true);
        panel2.setBounds(100,100,100,100);
        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.RED);
        panel3.setVisible(true);
        panel3.setBounds(200,200,100,100);

        mm.setLayout(null);

        try {
            mm.addToMainContainer(panel1);
            mm.addToMainContainer(panel2);
            mm.addToMainContainer(panel3);
        } catch (LayoutMismatchException e) {
            System.out.println(e);
        }
        
       
        

    }
}