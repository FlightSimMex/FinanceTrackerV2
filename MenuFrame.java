/**
 * Class Name: MenuFrame
 * Credit: Pablo Bandera Lopez
 * Created: 03/05/2025
 * Modified:
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
    private int selection = 0;
    final private int LEFT = 67, CENTER = 390, RIGHT = 50+333+333;

    final private Font BUTTON_FONT = new Font("Times New Roman", 0, 20);

    private JPanel topPanel, centerPanel, bottomPanel;
    private JButton newEntryButton, editEntryButton, deleteEntryButton, viewCurrntMonthButton, viewPastMonthButton, newMonthButton, clearMonthButton;

    //Constructor
    public MenuFrame(String title, LayoutManager layout)
    {
        super(title, layout);
        createPanels();
        createButtons();

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
        } catch (LayoutMismatchException e) {System.out.println(e);}
        

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
        button.setBounds(x, 80,200,75);
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
    public void OnNewEntry()
    {
        System.out.println("New Entry");
    }

    public void OnEditEntry()
    {
        System.out.println("Edit Entry");
    }

    public void OnDeleteEntry()
    {
        System.out.println("Delete Entry");
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
        System.out.println("New Month");
    }

    public void OnClearMonth()
    {
        System.out.println("Clear Month");
    }
}

