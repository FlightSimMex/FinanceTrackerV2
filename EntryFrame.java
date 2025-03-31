/**
 * Class Name: EntryFrame
 * Credit: Pablo Bandera Lopez
 * Created: 03/05/2025
 * Modified: 03/24/2025
 * 
 * Description: Class Extends App Frame, creates instance of Entry Frame. This type of frame acts as the UI for the user to select the data for the entry. 
 * It can be pre-loaded with an entry for edditing previous entries or start from an empty entry. When the submit button is clicked if the entry is valid, it will be added to entries.
 * 
 * Attributes:
 * - entryNumber: int
 * - DEFAULT_FONT: Font
 * - TYPES: Stirng[]
 * - CATEGORIES_INCOME: String[]
 * - CATEGORIES_EXPENSE: String[]
 * - ACCOUNTS: String[]
 * - SUBCAT1_EXPENSE_LIVING: String[]
 * - SUBCAT1_EXPENSE_PURCHASE: String[]
 * - SUBCAT1_EXPENSE_FLIGHT: String[]
 * - SUBCAT1_EXPENSE_SCHOOL: String[]
 * - SUBCAT2_LIVING_APPARTMENT: String[]
 * - SUBCAT2_LIVING_FOOD: String[]
 * - SUBCAT2_LIVING_TRANSPORTATION: String[]
 * - SUBCAT2_SCHOOL_FLIGHTTRAINING: String[]
 * - SUBCAT3_APPARTMENT_RENT: String[]
 * - SUBCAT3_APPARTMENT_EXPENSE: String[]
 * - SUBCAT3_FOOD_GROCERIES: String[]
 * - SUBCAT3_FOOD_FOODOUT: String[]
 * - SUBCAT3_FOOD_SNACKS: String[]
 * - SUBCAT4_RENT_UTILITIES: String[]
 * - SUBCAT4_FOODOUT_EATOUT: String[]
 * - hasSubCat: boolean
 * - hasSubCat1: boolean
 * - hasSubCat2: boolean
 * - hasSubCat3: boolean
 * - hasSubCat4: boolean
 * - panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8, panel9, panel10: JPanel
 * - label1, label2, label3, label4, label5, label6, label7, label8: JLabel
 * - textFieldAmount: JTextField
 * - textAreaComment: JTextArea
 * - comboBoxCategory, comboBoxSubCat1, comboBoxSubCat2, comboBoxSubCat3, comboBoxSubCat4, comboBoxType, comboBoxAccount: JComboBox<String>
 * - submitButton, cancelButton: JButton
 * - entry: Entry
 * - entries: Entries
 * 
 * Methods:
 * + <<constructor>>EntryFrame(String, LayoutManager, Entry, Entries)
 * - loadEntry(): void
 * - resetCategory(): void
 * - resetSubCats(): void
 * - resetSubCats2On(): void
 * - resetSubCats3On(): void
 * - resetSubCats4ON(): void
 * - setCategories(): void
 * - setSubCat1(): void
 * - setSubCat2(): void
 * - setSubCat3(): void
 * - setSubCat4(): void
 * - initFlags(): void
 * - resetFlags(int): void
 * - initPanels(): void
 * - initTextField(): void
 * - initTextArea(): void
 * - initComboBoxes(): void
 * - createPanels(): void
 * - createLabels(): void
 * - createButtons():void
 * - createComboBoxs(): void
 * - createPanel(): JPanel
 * - creatLabel(String): JLabel
 * - creaTextField(): JTextField
 * - creatTextArea(): JTextArea
 * - createButton(String, int): JButton
 * - createComboBox(String[]): JComboBox<String>
 * - amountModified(): void
 * - commentModified(): void
 * - submitClicked(): void
 * - cancelClicked(): void
 * - typeSelected(): void
 * - categorySelected(): void
 * - subCat1Selected(): void
 * - subCat2Selected(): void
 * - subCat3Selected(): void
 * - subCat4Selected(): void
 * - accountSelected(): void
 */
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class EntryFrame extends AppFrame 
{

    /*Attributes*/
    private int entryNumber;
    final private Font DEFAULT_FONT = new Font("Calibri", 0, 20);
    final private String[] TYPES = {"None","Income", "Expense"};
    final private String[] CATEGORIES_INCOME = {"None","Mom", "Pay Stub", "Other"};
    final private String[] CATEGORIES_EXPENSE = {"None", "Living", "Purchase","Flight", "School"};
    final private String[] ACCOUNTS = {"None", "Personal", "Flight", "Mother"};
    final private String[] SUBCAT1_EXPENSE_LIVING = {"None", "Appartment", "Food", "Transportation"};
    final private String[] SUBCAT1_EXPENSE_PURCHASE = {"None", "Personal", "Travel", "Paperwork"};
    final private String[] SUBCAT1_EXPENSE_FLIGHT = {"None", "Medical", "Currency", "Fun"};
    final private String[] SUBCAT1_EXPENSE_SCHOOL = {"None", "Tuition", "Flight Training", "Books"};
    final private String[] SUBCAT2_LIVING_APPARTMENT = {"None", "Rent", "Expense"};
    final private String[] SUBCAT2_LIVING_FOOD = {"None", "Groceries", "Food Out", "Snacks"};
    final private String[] SUBCAT2_LIVING_TRANSPORTATION = {"None", "Insurance", "Gas", "Tolls", "Service", "Cleaning", "Other"};
    final private String[] SUBCAT2_SCHOOL_FLIGHTTRAINING = {"None", "Training", "Exam", "Paperwork"};
    final private String[] SUBCAT3_APPARTMENT_RENT = {"None", "Base Rent", "Utilities"};
    final private String[] SUBCAT3_APPARTMENT_EXPENSE = {"None", "Cleaning Supplies", "Furniture", "Kitchen"};
    final private String[] SUBCAT3_FOOD_GROCERIES = {"None", "Supermarket", "Bakery", "Convenience"};
    final private String[] SUBCAT3_FOOD_FOODOUT = {"None", "Eat Out", "Take Out", "Delivery"};
    final private String[] SUBCAT3_FOOD_SNACKS = {"None", "At School", "Coffee", "Ice Cream", "Other"};
    final private String[] SUBCAT4_RENT_UTILITIES = {"None", "Power", "Wifi", "Cell", "Water", "Gas", "Subscriptions"};
    final private String[] SUBCAT4_FOODOUT_EATOUT = {"None", "At School", "Restautant"};
    
    

    //OPTIONS

    //Flags
    private boolean hasSubCat = false;
    private boolean hasSubCat1 = false;
    private boolean hasSubCat2 = false;
    private boolean hasSubCat3 = false;
    private boolean hasSubCat4 = false;
    private boolean isValid = false;
    private boolean loading = false;

    //JComponents
    private JPanel panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8, panel9, panel10;
    private JLabel label1, label2, label3, label4, label5, label6, label7, label8;
    private JTextField textFieldAmount;
    private JTextArea textAreaComment;
    private JComboBox<String> comboBoxCategory, comboBoxSubCat1, comboBoxSubCat2, comboBoxSubCat3, comboBoxSubCat4, comboBoxType, comboBoxAccount;
    private JButton submitButton, cancelButton;

    //Objects
    private Entry entry = null;
    private Entries entires = null;
    


    /*Constructor*/
    public EntryFrame(String title, LayoutManager layout, Entry e, Entries entries)
    {
        super(title, layout);
        this.entryNumber = entries.getNextEntryNumber();//Assigns new entry number
        this.entires = entries;
        if(e != null){this.entry = e;this.entryNumber = e.getEntryNumber();this.loading = true;} else {try {this.entry = new Entry(this.entryNumber);} catch (InvalidEntryException ex) {}}//If entry is null, create a new entry else load the entry
        this.changeTitle(title+this.entryNumber);//Adds entry number to title
        
        initFlags();

        createPanels();
        createLabels();
        createButtons();
        createComboBoxs();

        initPanels();
        initTextField();
        initTextArea();
        initComboBoxes();

        if(e != null){loadEntry();}
    }

    /*Private Methods*/

    private void loadEntry()
    {
        this.textFieldAmount.setText(Double.toString(entry.getAmount()));
        this.comboBoxType.setSelectedIndex(entry.getType());
        this.comboBoxCategory.setSelectedIndex(entry.getCategory());
        if(entry.getSubcategory()>0){this.hasSubCat1 = true; this.comboBoxSubCat1.setSelectedIndex(entry.getSubcategory());}
        if(entry.getSubcategory2()>0){this.hasSubCat2 = true; this.comboBoxSubCat2.setSelectedIndex(entry.getSubcategory2());}
        if(entry.getSubcategory3()>0){this.hasSubCat3 = true; this.comboBoxSubCat3.setSelectedIndex(entry.getSubcategory3());}
        if(entry.getSubcategory4()>0){this.hasSubCat4 = true; this.comboBoxSubCat4.setSelectedIndex(entry.getSubcategory4());}
        this.comboBoxAccount.setSelectedIndex(entry.getAccount());
        this.textAreaComment.setText(entry.getComment());
        isValid = true;
        loading = false;
    }

    private void resetCategory()
    {
        this.comboBoxCategory.setSelectedIndex(0);
        this.comboBoxSubCat1.setSelectedIndex(0);
        this.comboBoxSubCat2.setSelectedIndex(0);
        this.comboBoxSubCat3.setSelectedIndex(0);
        this.comboBoxSubCat4.setSelectedIndex(0);


        this.panel3.setVisible(false);
        this.panel4.setVisible(false);
        this.panel5.setVisible(false);
        this.panel6.setVisible(false);
        this.panel7.setVisible(false);

        resetFlags(this.entry.getType());

    }

    private void resetSubCats()
    {
        
        this.comboBoxSubCat1.setSelectedIndex(0);
        this.comboBoxSubCat2.setSelectedIndex(0);
        this.comboBoxSubCat3.setSelectedIndex(0);
        this.comboBoxSubCat4.setSelectedIndex(0);
        
        

        this.panel4.setVisible(false);
        this.panel5.setVisible(false);
        this.panel6.setVisible(false);
        this.panel7.setVisible(false);

        resetFlags(this.entry.getType());
        this.hasSubCat1 = true;

    }

    private void resetSubCats2On()
    {
        this.comboBoxSubCat2.setSelectedIndex(0);
        this.comboBoxSubCat3.setSelectedIndex(0);
        this.comboBoxSubCat4.setSelectedIndex(0);



        this.panel5.setVisible(false);
        this.panel6.setVisible(false);
        this.panel7.setVisible(false);

        resetFlags(this.entry.getType());
        this.hasSubCat1 = true;
        

    }

    private void resetSubCats3On()
    {
        
        this.comboBoxSubCat3.setSelectedIndex(0);
        this.comboBoxSubCat4.setSelectedIndex(0);


        this.panel6.setVisible(false);
        this.panel7.setVisible(false);

        resetFlags(this.entry.getType());
        this.hasSubCat1 = true;
        this.hasSubCat2 = true;
        

    }

    private void resetSubCats4On()
    {
        this.comboBoxSubCat4.setSelectedIndex(0);

        this.panel7.setVisible(false);



        resetFlags(this.entry.getType());
        this.hasSubCat1 = true;
        this.hasSubCat2 = true;
        this.hasSubCat3 = true;
        

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void setCategories()
    {
        switch (this.entry.getType())
        {
            case 1 -> this.comboBoxCategory.setModel(new DefaultComboBoxModel(this.CATEGORIES_INCOME));
            case 2 -> this.comboBoxCategory.setModel(new DefaultComboBoxModel(this.CATEGORIES_EXPENSE));
        }
        this.panel3.setVisible(true);
        this.panel3.setBorder(new BevelBorder(0));
        this.isValid = false;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void setSubCat1()
    {
        if(this.hasSubCat)
        {
            
            switch (this.entry.getCategory())
            {
                case 1 -> {this.comboBoxSubCat1.setModel(new DefaultComboBoxModel(this.SUBCAT1_EXPENSE_LIVING)); this.hasSubCat2 = true;}
                case 2 -> this.comboBoxSubCat1.setModel(new DefaultComboBoxModel(this.SUBCAT1_EXPENSE_PURCHASE));
                case 3 -> this.comboBoxSubCat1.setModel(new DefaultComboBoxModel(this.SUBCAT1_EXPENSE_FLIGHT));
                case 4 -> {this.comboBoxSubCat1.setModel(new DefaultComboBoxModel(this.SUBCAT1_EXPENSE_SCHOOL)); this.hasSubCat2 = true;}
            }
            this.panel4.setVisible(true);
            this.panel4.setBorder(new BevelBorder(0));
            this.isValid = false;
        }else{
            this.isValid = true;
        }
        
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void setSubCat2()
    {
        if(this.entry.getCategory() == 1 || (this.entry.getCategory() == 4 && this.entry.getSubcategory() ==2))
        {   
            if(this.entry.getCategory() == 1)
            {
                switch (this.entry.getSubcategory())
                {
                    case 1 -> {this.comboBoxSubCat2.setModel(new DefaultComboBoxModel(this.SUBCAT2_LIVING_APPARTMENT)); this.hasSubCat3 = true;}
                    case 2 -> {this.comboBoxSubCat2.setModel(new DefaultComboBoxModel(this.SUBCAT2_LIVING_FOOD)); this.hasSubCat3 = true;}
                    case 3 -> this.comboBoxSubCat2.setModel(new DefaultComboBoxModel(this.SUBCAT2_LIVING_TRANSPORTATION)); 
                }
            }else if(this.entry.getCategory() == 4 && this.entry.getSubcategory() ==2){this.comboBoxSubCat2.setModel(new DefaultComboBoxModel(this.SUBCAT2_SCHOOL_FLIGHTTRAINING));}

            this.panel5.setVisible(true);
            this.panel5.setBorder(new BevelBorder(0));
            this.isValid = false;
        }else{
            this.isValid = true;
        }
        
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void setSubCat3()
    {
        if((this.entry.getCategory() == 1 && this.entry.getSubcategory() == 1 && this.entry.getSubcategory2() > 0) || (this.entry.getCategory() == 1 && this.entry.getSubcategory() == 2 && this.entry.getSubcategory2() > 0))
        {   
            if(this.entry.getSubcategory() == 1)
            {
                switch (this.entry.getSubcategory2())
                {
                    case 1 -> {this.comboBoxSubCat3.setModel(new DefaultComboBoxModel(this.SUBCAT3_APPARTMENT_RENT)); this.hasSubCat4 = true;}
                    case 2 -> this.comboBoxSubCat3.setModel(new DefaultComboBoxModel(this.SUBCAT3_APPARTMENT_EXPENSE));  
                }
            }else if(this.entry.getSubcategory() == 2)
            {
                switch(this.entry.getSubcategory2())
                {
                    case 1 -> this.comboBoxSubCat3.setModel(new DefaultComboBoxModel(this.SUBCAT3_FOOD_GROCERIES));
                    case 2 -> {this.comboBoxSubCat3.setModel(new DefaultComboBoxModel(this.SUBCAT3_FOOD_FOODOUT)); this.hasSubCat4 = true;}
                    case 3 -> this.comboBoxSubCat3.setModel(new DefaultComboBoxModel(this.SUBCAT3_FOOD_SNACKS));
                }
            }

            this.panel6.setVisible(true);
            this.panel6.setBorder(new BevelBorder(0));
            this.isValid = false;
        }else{
            this.isValid = true;
        }
        
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void setSubCat4()
    {
        if((this.entry.getCategory() == 1 && this.entry.getSubcategory() == 1 && this.entry.getSubcategory2() == 1 && this.entry.getSubcategory3() == 2) || (this.entry.getCategory() == 1 && this.entry.getSubcategory() == 2 && this.entry.getSubcategory2() == 2 && this.entry.getSubcategory3() == 1) )
        {
            if(this.comboBoxSubCat3.getSelectedItem().equals("Utilities")){this.comboBoxSubCat4.setModel(new DefaultComboBoxModel(this.SUBCAT4_RENT_UTILITIES));}
            else if(this.comboBoxSubCat3.getSelectedItem().equals("Eat Out")){this.comboBoxSubCat4.setModel(new DefaultComboBoxModel(this.SUBCAT4_FOODOUT_EATOUT));}
            this.panel7.setVisible(true);
            this.panel7.setBorder(new BevelBorder(0));
            this.isValid = false;
        }else{
            this.isValid = true;
        }
        
    }

    /*Init Methods*/
    private void initFlags(){
        if(this.entry.getType()>1){this.hasSubCat = true;}//If not Income set true
        if(this.entry.getSubcategory()>0){this.hasSubCat1 = true;}
        if(this.entry.getSubcategory2()>0){this.hasSubCat2 = true;}
        if(this.entry.getSubcategory3()>0){this.hasSubCat3 = true;}
        if(this.entry.getSubcategory4()>0){this.hasSubCat4 = true;}
    }

    private void resetFlags(int type)
    {
        this.hasSubCat = type > 1;
        this.hasSubCat1 = type > 1;
        this.hasSubCat2 = false;
        this.hasSubCat3 = false;
        this.hasSubCat4 = false;
    }

    private void initPanels()
    {
        panel1.setVisible(true);
        panel2.setVisible(true);
        panel8.setVisible(true);
        panel9.setVisible(true);
        panel10.setVisible(true);

        panel1.setBorder(new BevelBorder(0));
        panel2.setBorder(new BevelBorder(0));
        panel8.setBorder(new BevelBorder(0));
        panel9.setBorder(new BevelBorder(0));
        panel10.setBorder(new BevelBorder(0));

        if(this.entry.getAmount() != 0.0)
        {
            panel3.setVisible(true);
            panel3.setBorder(new BevelBorder(0));
            if(this.hasSubCat1){panel4.setVisible(true);panel4.setBorder(new BevelBorder(0));}
            if(this.hasSubCat2){panel5.setVisible(true);panel5.setBorder(new BevelBorder(0));}
            if(this.hasSubCat3){panel6.setVisible(true);panel6.setBorder(new BevelBorder(0));}
            if(this.hasSubCat4){panel7.setVisible(true);panel7.setBorder(new BevelBorder(0));}
            
            
        }
    }

    private void initTextField()
    {
        this.textFieldAmount = creaTextField();
        this.panel1.add(this.textFieldAmount);
        this.textFieldAmount.setText(String.valueOf(this.entry.getAmount()));
        this.textFieldAmount.addActionListener(l -> amountModified());
    }

    private void initTextArea()
    {
        this.textAreaComment = creatTextArea();
        this.panel9.add(this.textAreaComment);
        if(!this.entry.getComment().isEmpty()){this.textAreaComment.setText(this.entry.getComment());}
        else
        {
            this.textAreaComment.setText("Enter Comment Here");
            this.textAreaComment.setForeground(new Color(220,220,220));
        }
        this.textAreaComment.addFocusListener(new FocusAdapter() {@Override public void focusGained(FocusEvent e){commentModified();}});
        
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void initComboBoxes()
    {
       //IF ENTRY IS NULL
        if(entry.getAmount() == 0.0)
        {
            this.comboBoxType.setModel(new DefaultComboBoxModel(this.TYPES));
            this.comboBoxAccount.setModel(new DefaultComboBoxModel(this.ACCOUNTS));
        }
         //IF ENTRY IS NOT NULL LOAD
        else
        {
            this.comboBoxType.setModel(new DefaultComboBoxModel(this.TYPES));
            this.comboBoxType.setSelectedIndex(this.entry.getType());
            this.comboBoxAccount.setModel(new DefaultComboBoxModel(this.ACCOUNTS));
            this.comboBoxAccount.setSelectedIndex(this.entry.getAccount());
        }
        
    }

    /*Create Methods*/

    private void createPanels()
    {
        this.panel1 = createPanel();
        this.panel2 = createPanel();
        this.panel3 = createPanel();
        this.panel4 = createPanel();
        this.panel5 = createPanel();
        this.panel6 = createPanel();
        this.panel7 = createPanel();
        this.panel8 = createPanel();
        this.panel9 = createPanel();
        this.panel10 = createPanel();

        try {
            addToMainContainer(this.panel1, 0);
            addToMainContainer(this.panel2, 1);
            addToMainContainer(this.panel3, 2);
            addToMainContainer(this.panel4, 3);
            addToMainContainer(this.panel5, 4);
            addToMainContainer(this.panel6, 5);
            addToMainContainer(this.panel7, 6);
            addToMainContainer(this.panel8, 7);
            addToMainContainer(this.panel9, 8);
            addToMainContainer(this.panel10, 9);
        } catch (LayoutMismatchException e) {}


    }

    private void createLabels()
    {
        this.label1 = creatLabel("Amount:");
        this.label2 = creatLabel("Type:");
        this.label3 = creatLabel("Category:");
        this.label4 = creatLabel("Sub 1: ");
        this.label5 = creatLabel("Sub 2:" );
        this.label6 = creatLabel("Sub 3:");
        this.label7 = creatLabel("Sub 4:");
        this.label8 = creatLabel("Account:");

        this.panel1.add(this.label1);
        this.panel2.add(this.label2);
        this.panel3.add(this.label3);
        this.panel4.add(this.label4);
        this.panel5.add(this.label5);
        this.panel6.add(this.label6);
        this.panel7.add(this.label7);
        this.panel8.add(this.label8);
    }

    private void createButtons()
    {
        this.submitButton = createButton("Submit", 75);
        this.submitButton.addActionListener(l -> submitClicked());
        this.cancelButton = createButton("Cancel", 175);
        this.cancelButton.addActionListener(l -> cancelClicked());

        this.panel10.add(this.submitButton);
        this.panel10.add(this.cancelButton);
    }

    private void createComboBoxs()
    {
        String[] nullArr = {""};
        this.comboBoxType = createComboBox(nullArr);
        this.comboBoxCategory = createComboBox(nullArr);
        this.comboBoxSubCat1 = createComboBox(nullArr);
        this.comboBoxSubCat2 = createComboBox(nullArr);
        this.comboBoxSubCat3 = createComboBox(nullArr);
        this.comboBoxSubCat4 = createComboBox(nullArr);
        this.comboBoxAccount = createComboBox(nullArr);

        this.comboBoxType.addActionListener(l -> typeSelected());
        this.comboBoxCategory.addActionListener(l -> categorySelected());
        this.comboBoxSubCat1.addActionListener(l -> subCat1Selected());
        this.comboBoxSubCat2.addActionListener(l -> subCat2Selected());
        this.comboBoxSubCat3.addActionListener(l -> subCat3Selected());
        this.comboBoxSubCat4.addActionListener(l -> subCat4Selected());
        this.comboBoxAccount.addActionListener(l -> accountSelected());
        
        this.panel2.add(this.comboBoxType);
        this.panel3.add(this.comboBoxCategory);
        this.panel4.add(this.comboBoxSubCat1);
        this.panel5.add(this.comboBoxSubCat2);
        this.panel6.add(this.comboBoxSubCat3);
        this.panel7.add(this.comboBoxSubCat4);
        this.panel8.add(this.comboBoxAccount);
    }


    private JPanel createPanel()
    {   
        
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 325));
        panel.setLayout(null);
        panel.setVisible(false);
        panel.setBackground(new Color(238,238,238));
        return panel;
    }

    private JLabel creatLabel(String s)
    {
        JLabel label = new JLabel();
        label.setFont(this.DEFAULT_FONT);
        label.setText(s);
        label.setBounds(50, 50, 100, 50);
        label.setForeground(Color.BLACK);
        label.setVisible(rootPaneCheckingEnabled);
        return label;
    }

    private JTextField creaTextField()
    {
        JTextField field = new JTextField("0.0");
        field.setFont(this.DEFAULT_FONT);
        field.setBounds(50, 100, 100, 50);
        field.setVisible(rootPaneCheckingEnabled);
        return field;
    }

    private JTextArea creatTextArea()
    {
        JTextArea area = new JTextArea();
        area.setFont(this.DEFAULT_FONT);
        area.setBounds(10, 10, 175, 285);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createLineBorder(Color.white, 10));
        area.setVisible(rootPaneCheckingEnabled);
        return area;
    }

    private JButton createButton(String s, int y)
    {
        JButton button = new JButton(s);
        button.setVerticalTextPosition(JLabel.CENTER);
        button.setHorizontalTextPosition(JLabel.CENTER);
        button.setBounds(25, y,150,65);
        button.setFont(this.DEFAULT_FONT);
        button.setFocusable(false);
        button.setBackground(Color.WHITE);
        button.setVisible(rootPaneCheckingEnabled);
        return button;
    }

    private JComboBox<String> createComboBox(String[] s)
    {
        JComboBox<String> comboBox = new JComboBox<>(s);
        comboBox.setFont(this.DEFAULT_FONT);
        comboBox.setBounds(50, 100, 100, 50);
        comboBox.setBackground(Color.WHITE);
        comboBox.setVisible(rootPaneCheckingEnabled);
        return comboBox;
    }

    /*Event Handlers*/
    private void amountModified()
    {
        try{entry.setAmount(Double.parseDouble(this.textFieldAmount.getText()));}catch(InvalidEntryException e){this.textFieldAmount.setText("0.0");}
    }

    private void commentModified()
    {
        this.textAreaComment.setForeground(Color.BLACK);
        entry.setComment(this.textAreaComment.getText());
    }

    private void submitClicked()
    {
        amountModified();
        commentModified();
        try{
            this.entry.setAmount(this.entry.getAmount());
            this.entry.setType(this.entry.getType());//Checks if the current value is a valid value
            this.entry.setCategory(this.entry.getCategory());
            this.entry.setAccount(this.entry.getAccount());
        }catch (InvalidEntryException ex){JOptionPane.showMessageDialog(null, ex, "Error:", JOptionPane.ERROR_MESSAGE);return;}

        if(this.entires.getEntryByNmEntry(this.entryNumber) != null && (this.isValid))
        {
            entires.updateEntry(this.entryNumber, this.entry);
        }else if(this.isValid){
            this.entires.addEntry(this.entry);
        }else{
            JOptionPane.showMessageDialog(null, "Invalid Entry: Check Selections", "Error:", JOptionPane.ERROR_MESSAGE);return;
        }
        
        this.dispose();
    }

    private void cancelClicked()
    {
        this.dispose();
    }

    private void typeSelected()
    {   
        resetCategory();
        if(!this.loading)
        {
            this.entry.resetType();
            this.entry.resetCategory();
            this.entry.resetSub1();
            this.entry.restSub2();
            this.entry.restSub3();
            this.entry.restSub4();
        }
        try
        {
            entry.setType(this.comboBoxType.getSelectedIndex());
            setCategories();
            
        }catch(InvalidEntryException e){}
        
    }

    private void categorySelected()
    {
        resetSubCats();
        if(!this.loading)
        {
            this.entry.resetCategory();
            this.entry.resetSub1();
            this.entry.restSub2();
            this.entry.restSub3();
            this.entry.restSub4();
        }
        try
        {
            entry.setCategory(this.comboBoxCategory.getSelectedIndex());
            setSubCat1();
            
        }catch(InvalidEntryException e){}
    }

    private void subCat1Selected()
    {
        resetSubCats2On();
        if(!this.loading)
        {
            this.entry.resetSub1();
            this.entry.restSub2();
            this.entry.restSub3();
            this.entry.restSub4();
        }
        try
        {
            entry.setSubcategory(this.comboBoxSubCat1.getSelectedIndex());
            setSubCat2();
        }catch (InvalidEntryException e){}
        
    }

    private void subCat2Selected()
    {
        resetSubCats3On();
        if(!this.loading)
        {
            this.entry.restSub2();
            this.entry.restSub3();
            this.entry.restSub4();
        }
        try
        {
            entry.setSubcategory2(this.comboBoxSubCat2.getSelectedIndex());
            setSubCat3();
        }catch (InvalidEntryException e){}
    }

    private void subCat3Selected()
    {
        resetSubCats4On();
        if(!this.loading)
        {
            this.entry.restSub3();
            this.entry.restSub4();
        }
        try
        {
            entry.setSubcategory3(this.comboBoxSubCat3.getSelectedIndex());
            setSubCat4();
        }catch(InvalidEntryException e){}
    }

    private void subCat4Selected()
    {
        try
        {
            entry.setSubcategory4(this.comboBoxSubCat4.getSelectedIndex());
        }catch(InvalidEntryException e){}
        
    }

    private void accountSelected()
    {
        try{entry.setAccount(this.comboBoxAccount.getSelectedIndex());}catch(InvalidEntryException e){this.isValid =false;}
    }

   

}
