/**
 * Class Name: MenuFrame
 * Credit: Pablo Bandera Lopez
 * Created: 03/05/2025
 * Modified: 04/01/2025
 * 
 * Description: Class Extends App Frame, creates main menu for user selection
 * 
 * Attributes:
 * - selection: int
 * - LEFT: int
 * - CENTER: int
 * - RIGHT: int
 * - BUTTON_FONT: Font
 * - topPanel:  JPanel
 * - centerPanel: JPanel
 * - bottomPanel: JPanel
 * - newEntryButton: JButton
 * - editEntryButton: JButton
 * - deleteEntryButton: JButton
 * - viewCurrentMonthButton: JButton
 * - viewPastMonthButton: JButton
 * - newMonthButton: JButton
 * - clearMonthButton: JButton
 * 
 * Methods:
 * + <<constructor>>MenuFrame(String, LayoutManager)
 * - createPanels(): void
 * - createButtons(): void
 * - bevelBorderPanel(): JPanel
 * - standardButton(String, int): JButton
 * + actionPerformed(ActionEvent): void
 * + processChoice(): void
 * + OnNewEntry(): void
 * + OnEditEntry(): void
 * + OnDeleteEntry(): void
 * + OnViewCurrentMonth(): void
 * + OnViewPastMonth(): void
 * + OnNewMonth(): void
 * + OnClearMonth(): void
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuFrame extends AppFrame implements ActionListener
{
    //Atributes
    private int selection = 0;//67
    final private int LEFT = (this.getWindowWidth()/3/2)-125, CENTER = ((this.getWindowWidth()/3/2)-125)+(this.getWindowWidth()/3), RIGHT = ((this.getWindowWidth()/3/2)-125)+(2*(this.getWindowWidth()/3));
    final private int VERTICAL_CENTER = (this.getWindowHeight()-150)/12;
    final private Font BUTTON_FONT = new Font("Calibri", 0, 20);

    private JPanel topPanel, centerPanel, bottomPanel;
    private JButton newEntryButton, editEntryButton, deleteEntryButton, viewCurrntMonthButton, viewPastMonthButton, newMonthButton, clearMonthButton;

    @SuppressWarnings("FieldMayBeFinal")
    private Entries entries;

    //Constructor
    public MenuFrame(String title, LayoutManager layout, Entries entries)
    {
        super(title, layout);
        this.entries = entries;
        createPanels();
        createButtons();
        setVisible(true);

    }

    //Instanciates JPanels and adds them the MainContainer
    private void createPanels()
    {
        this.topPanel = bevelBorderPanel();
        this.centerPanel = bevelBorderPanel();
        this.bottomPanel = bevelBorderPanel();

        try {
            addToMainContainer(this.topPanel, 0);
            addToMainContainer(this.centerPanel, 1);
            addToMainContainer(this.bottomPanel, 2);  
        } catch (LayoutMismatchException e) {}
        

    }

    //Instanciates JButtons and adds them to Corresponding panels
    private void createButtons()
    {
        this.newEntryButton = standardButton("New Entry", this.LEFT);
        this.editEntryButton = standardButton("Edit Entry", this.CENTER);
        this.deleteEntryButton = standardButton("Delete Entry", this.RIGHT);
        this.viewCurrntMonthButton = standardButton("View Current", this.LEFT);
        this.viewPastMonthButton = standardButton("View Past", this.CENTER);
        this.newMonthButton = standardButton("New Month", this.LEFT);
        this.clearMonthButton = standardButton("Clear Month", this.CENTER);

        topPanel.add(this.newEntryButton);
        topPanel.add(this.editEntryButton);
        topPanel.add(this.deleteEntryButton);
        centerPanel.add(this.viewCurrntMonthButton);
        centerPanel.add(this.viewPastMonthButton);
        bottomPanel.add(this.newMonthButton);
        bottomPanel.add(this.clearMonthButton);
    }

    //Creates a panel with a Border type Bevel
    private JPanel bevelBorderPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createBevelBorder(0));
        return panel;
    }

    //Creates a buttonwith the standard style of this app
    private JButton standardButton(String s, int x)
    {   

        JButton button = new JButton(s);
        button.setVerticalTextPosition(JLabel.CENTER);
        button.setHorizontalTextPosition(JLabel.CENTER);
        button.setBounds(x, VERTICAL_CENTER,250,100);
        button.setFont(this.BUTTON_FONT);
        button.setFocusable(false);
        button.setBackground(Color.WHITE);
        button.setVisible(true);
        button.addActionListener(this);
        return button;
    }

    //Implementation of ActionPerformed for Buttons, sets selection attribute anc calls processChoice() every timne a selection is made
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==this.newEntryButton){this.selection = 1;}
        if(e.getSource()==this.editEntryButton){this.selection = 2;}
        if(e.getSource()==this.deleteEntryButton){this.selection = 3;}
        if(e.getSource()==this.viewCurrntMonthButton){this.selection = 4;}
        if(e.getSource()==this.viewPastMonthButton){this.selection = 5;}
        if(e.getSource()==this.newMonthButton){this.selection = 6;}
        if(e.getSource()==this.clearMonthButton){this.selection = 7;}
        processChoice();
    }
    
    //Performs the desired action based on the given input
    public void processChoice()
    {
        switch (this.selection)
        {
            case 1 -> OnNewEntry();
            case 2 -> OnEditEntry();
            case 3 -> OnDeleteEntry();
            case 4 -> OnViewCurrentMonth();
            case 5 -> OnViewPastMonth();
            case 6 -> OnNewMonth();
            case 7 -> OnClearMonth();
        }
    }

    //Event Hadelers 
    @SuppressWarnings("unused")
    public void OnNewEntry()
    {
        FileManager fm = new FileManager();
        if(!fm.filePathExists(fm.getCurrentMonth(), fm.getCurrentYear())){JOptionPane.showMessageDialog(null, "Month file does not exist.","Error!", JOptionPane.ERROR_MESSAGE); return;}
        AppFrame ef = new EntryFrame("Entry Number: ", new GridLayout(2,5), null, this.entries);//Creates new entry frame with null entry
    }

    @SuppressWarnings("unused")
    public void OnEditEntry()
    {   
        FileManager fm = new FileManager();
        if(!fm.filePathExists(fm.getCurrentMonth(), fm.getCurrentYear())){JOptionPane.showMessageDialog(null, "Month file does not exist.","Error!", JOptionPane.ERROR_MESSAGE); return;}
        String entrNum = JOptionPane.showInputDialog("Entry Number: ");//Get input from user using pop out.
        try{int num = Integer.parseInt(entrNum);
            Entry en = this.entries.getEntryByNmEntry(num);//Fetch entry by number from Entries
            if(en != null){AppFrame ef = new EntryFrame("Entry Number: ", new GridLayout(2,5), en, this.entries);}//Create edit frame if found
            else {JOptionPane.showMessageDialog(null, "Entry Number "+num+"\nNot Found!", "Error", JOptionPane.ERROR_MESSAGE);}
        }catch(NumberFormatException e){JOptionPane.showMessageDialog(null, "Invalid Integer!", "Error!", JOptionPane.ERROR_MESSAGE);}
    }

    public void OnDeleteEntry()
    {
        String entryNum = JOptionPane.showInputDialog("Entry Number: ");//Get input from user using pop-out
        try{int num = Integer.parseInt(entryNum);
            if(this.entries.getEntryByNmEntry(num) != null){this.entries.removeEntry(num);}
            else {JOptionPane.showMessageDialog(null, "Entry Number "+num+"\nNot Found!", "Error", JOptionPane.ERROR_MESSAGE);}
        }catch(NumberFormatException e){JOptionPane.showMessageDialog(null, "Invalid Integer!", "Error!", JOptionPane.ERROR_MESSAGE);}

    }

    public void OnViewCurrentMonth()
    {
        System.out.println("View Current Month");
    }

    public void OnViewPastMonth()
    {
        System.out.println("View Past Month");
    }

    public void OnNewMonth()
    {
        FileManager fm  = new FileManager();
        String month = fm.getCurrentMonth();
        String year = fm.getCurrentYear();
        if(!(fm.filePathExists(month,year)))
        {
            if(!(fm.directoryExists(year))){fm.makeDir(fm.getDirectoryPath(year));}
            fm.makeFile(fm.getFilePath(month,year));
        }else
        { 
            int sel = JOptionPane.showConfirmDialog(null, "Confirming will wipe current File.", "Caution: File Exists!", JOptionPane.WARNING_MESSAGE);
            if(sel == 0){
                fm.rmvFile(fm.getFilePath(month, year)); 
                fm.makeFile(fm.getFilePath(month,year));
                this.entries.clearList();
            }
        }
    }

    public void OnClearMonth()
    {
        FileManager fm = new FileManager();
        int sel = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the current file?", "Caution: Irreversible Action", JOptionPane.WARNING_MESSAGE);
        if(sel == 0){fm.rmvFile(fm.getFilePath(fm.getCurrentMonth(),fm.getCurrentYear())); this.entries.clearList();}
    }
}

