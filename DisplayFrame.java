/**
 * Class Name: DisplayFrame
 * Credit: Pablo Bandera Lopez
 * Created: 03/05/2025
 * Modified: 03/24/2025
 * 
 * Description: Class Extends App Frame, creates main menu for user selection
 * 
 * Attributes:
 * - BUTTON_FONT: Font
 * - COLUMN_HEADERS_TOTALS: String
 * - COLUMN_HEADERS: String
 * - data: Object [][]
 * - totals: Object [][]
 * - entries: Entries
 * - d: Decoder
 * - table: JTable
 * - table2: JTable
 * - close: JButton
 * - viewTotals: JButton
 * - viewEntries: JButton
 * - sPane: JScrollPane
 * - sPane2: JScrollPane
 * 
 * Methods:
 * + <<constructor>>DisplayFrame(String, FileManager, Entries, String, String)
 * + loadCurrent(String, Entries): void
 * + loadOther(String,FileManager, Entries, String): void
 * - loadTableData(): void
 * - loadTotalData(): void
 * - createTable(Object [][], String []): JTable
 * - createTotals(): void
 * - createSPane(Component): void
 * - loadFrame(): void
 * - standardButton(String, int, int): void
 * - onTotals(): void
 * - onEntries(): void
 * - onClose(): void
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

public class DisplayFrame extends AppFrame
{
    //Attributes
    final private Font BUTTON_FONT = new Font("Calibri", 0, 20);
    final private String [] COLUMN_HEADERS_TOTALS = {"ACCOUNT: ","INCOME: ", "SPENT: ", "REMAINING: "};
    final private String [] COLUMN_HEADERS = {"Entry Number: ", "Amount: ", "Information: ", "Charged To: ", "Comment: "};
    private Object [][] data;
    private Object [][] totals;

    //Object Attributes
    private Entries entries;
    private Decoder d;

    //JComponent Attributes
    @SuppressWarnings("FieldMayBeFinal")
    private JTable table, table2;
    @SuppressWarnings("FieldMayBeFinal")
    private JButton close, viewTotals, viewEntries;
    @SuppressWarnings("FieldMayBeFinal")
    private JScrollPane sPane, sPane2;
    
    

    /* Constructor */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public DisplayFrame(String title, FileManager fm, Entries ents, String month, String year)
    {
        super(title, null);
        if(month.equalsIgnoreCase(fm.getCurrentMonth())){loadCurrent(month, ents);}
        else { loadOther(month, fm, ents, year);}//load month data
        
        loadTableData();
        table = createTable(data, COLUMN_HEADERS);
        sPane = createSPane(table);
        close = standardButton("Close", 750, 500);
        close.addActionListener( l -> onClose());

        viewTotals = standardButton("Totals", 100, 500);
        viewTotals.addActionListener(l -> onTotals());

        viewEntries = standardButton("Entries", 300, 500);
        viewEntries.setVisible(false);
        viewEntries.addActionListener(l -> onEntries());

        loadTotalData();
        table2 = createTotals();
        sPane2 = createSPane(table2);
        sPane2.setVisible(false);
        table2.setVisible(false);

        loadFrame();
    }

    /* Public Methods */

    public void loadCurrent(String month, Entries ents)
    {
        this.changeTitle(month);
        this.entries = ents;
    }

    public void loadOther(String month,FileManager fm, Entries ents, String year)
    {
        this.changeTitle(month);
        String filePath = fm.getFilePath(month,year);
        this.entries = fm.readEntries(filePath);
    }

    /* Private Methods */

    private void loadTableData()
    {
        int rows = this.entries.getNumberOfEntries();
        data = new Object[rows][5];
        for(int i = 0; i < rows; i ++)
        {
            Entry e = this.entries.getEntryByPosition(i);
            if(e != null)
            {
                d = new Decoder(e.getEntryString());
                data[i][0] = e.getEntryNumber();
                data[i][1] = "$"+e.getAmount();
                data[i][2] = d.getDecoded();
                data[i][3] = d.decodeAccount();
                data[i][4] = e.getComment();
            }
        }
    }

    @SuppressWarnings("unused")
    private void loadTotalData()
    {
        int personalIncome = 0;
        int personalExpense = 0;
        int motherIncome = 0;
        int motherExpense = 0;
        int flightIncome = 0;
        int flightExpense = 0;
        int personalDifference;
        int motherDifference;
        int flightDifference;

        totals = new Object[3][4];

        for(int i = 0 ; i < this.entries.getNumberOfEntries(); i ++)
        {
            Entry e = this.entries.getEntryByPosition(i);
            if(e.getAccount() == 1 && e.getType() == 1){personalIncome += e.getAmount();}
            if(e.getAccount() == 1 && e.getType() == 2){personalExpense += e.getAmount();}
            if(e.getAccount() == 2 && e.getType() == 1){flightIncome += e.getAmount();}
            if(e.getAccount() == 2 && e.getType() == 2){flightExpense += e.getAmount();}
            if(e.getAccount() == 3 && e.getType() == 1){motherIncome += e.getAmount();}
            if(e.getAccount() == 3 && e.getType() == 2){motherExpense += e.getAmount();}
            
        }

        personalDifference = personalIncome-personalExpense;
        motherDifference = motherIncome-motherExpense;
        flightDifference = flightIncome-flightExpense;

        totals[0][0] = "Personal"; totals[1][0] = "Flight"; totals[2][0] = "Mother";
        totals[0][1] = personalIncome; totals[0][2] = personalExpense; totals[0][3] = personalDifference;
        totals[1][1] = flightIncome; totals[1][2] = flightExpense; totals[1][3] = flightDifference;
        totals[2][1] = motherIncome; totals[2][2] = motherExpense; totals[2][3] = motherDifference;
        

    }

    private JTable createTable(Object [][] data, String [] headers)
    {
        JTable table1 = new JTable(data, headers); 
        TableColumnModel colMod = table1.getColumnModel();
        if(headers.length>3)
        {
            colMod.getColumn(0).setPreferredWidth(15);
            colMod.getColumn(1).setPreferredWidth(10);
            colMod.getColumn(2).setPreferredWidth(200);
            colMod.getColumn(3).setPreferredWidth(15);
            colMod.getColumn(4).setPreferredWidth(40);
        }
        
        return table1;
    }

    private JTable createTotals()
    {
        JTable table12 = new JTable(totals, COLUMN_HEADERS_TOTALS);
        table12.setVisible(false);
        return table12;
    }

    private JScrollPane createSPane(Component view)
    {
        JScrollPane sp = new JScrollPane(view);
        double TABLE_WIDTH = (this.getWindowWidth()*0.8);
        double TABLE_X = (this.getWindowWidth()/2)-(TABLE_WIDTH/2);
        double TABLE_HEIGHT = (this.getWindowHeight()-150)*0.65;
        sp.setBounds((int)TABLE_X, 50, (int)TABLE_WIDTH, (int)TABLE_HEIGHT);
        return sp;
    }

    private void loadFrame()
    {
        try{
            if(sPane != null){addToMainContainer(sPane);}
            if(sPane2 != null){addToMainContainer(sPane2);}
            if(close != null){addToMainContainer(close);}
            if(viewEntries != null){addToMainContainer(viewEntries);}
            if(viewTotals != null){addToMainContainer(viewTotals);}
            
        }catch (LayoutMismatchException e){}
    }

    private JButton standardButton(String s, int x, int y)
    {   

        JButton button = new JButton(s);
        button.setVerticalTextPosition(JLabel.CENTER);
        button.setHorizontalTextPosition(JLabel.CENTER);
        button.setBounds(x, y,150,50);
        button.setFont(this.BUTTON_FONT);
        button.setFocusable(false);
        button.setBackground(Color.WHITE);
        button.setVisible(true);
        return button;
    }

    private void onTotals()
    {
        this.viewEntries.setVisible(true);
        this.viewTotals.setVisible(false);
        table2.setVisible(true);
        sPane2.setVisible(true);
        table.setVisible(false);
        sPane.setVisible(false);
        

      

    }

    private void onEntries()
    {
        this.viewTotals.setVisible(true);
        this.viewEntries.setVisible(false);
        table2.setVisible(false);
        sPane2.setVisible(false);
        table.setVisible(true);
        sPane.setVisible(true);
        
    }

    private void onClose()
    {
        this.dispose();
    }


}
