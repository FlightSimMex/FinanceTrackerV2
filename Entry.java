
/**
 * Class Name: Entry
 * Credit: Pablo Bandera Lopez
 * Created: 03/24/2025
 * Modified:
 * 
 * Description: Instanciate an Entry object to store the data of a single entry.
 * 
 * Attributes:
 * - entryNumber: int
 * - dollarAmount: double
 * - type: int
 * - category: int
 * - subcategory: int
 * - subcategory2: int
 * - subcategory3: int
 * - subcategory4: int
 * - account: int
 * - comment: String
 * - entryString: String
 * - textEntryString: String
 * 
 * 
 * Methods:
 * + <<constructor>>Entry(int entryNumber):void
 * - setEntryString(): void
 * + setAmount(double amount): void
 * + setCategory(int category): void
 * + setSubcategory(int subcategory): void
 * + setSubcategory2(int subcategory2): void
 * + setSubcategory3(int subcategory3): void
 * + setSubcategory4(int subcategory4): void
 * + setAccount(int account): void
 * + setComment(String comment): void
 * + getEntryNumber(): int
 * + getAmount(): double
 * + getCategory(): int
 * + getSubcategory(): int
 * + getSubcategory2(): int
 * + getSubcategory3(): int
 * + getSubcategory4(): int
 * + getAccount(): int
 * + getComment(): String
 * + getEntryString(): String
 * + getTextEntryString(): String
 */

public class Entry {
    
    /* Attributes */
    private int entryNumber;
    private double dollarAmount;
    private int type;
    private int category;
    private int subcategory;
    private int subcategory2;
    private int subcategory3;
    private int subcategory4;
    private int account;
    private String comment;
    private String entryString;//String containing the indecies of the entry data
    private String textEntryString;//String containing the text of the entry data

    /*Constructor*/
    public Entry(int entryNumber) throws InvalidEntryException
    {   
        if(entryNumber > 0){this.entryNumber = entryNumber;}else{throw new InvalidEntryException("Invalid Entry Number");}
        this.dollarAmount = 0.0;
        this.type = -1;
        this.category = -1;
        this.subcategory = -1;
        this.subcategory2 = -1;
        this.subcategory3 = -1;
        this.subcategory4 = -1;
        this.account = -1;
        this.comment = "";
        setEntryString();

    }

    /*Setters */
    private void setEntryString(){this.entryString = this.entryNumber + "," + this.dollarAmount + "," + this.type + "," + this.category + "," + this.subcategory + "," + this.subcategory2 + "," + this.subcategory3 + "," + this.subcategory4 + "," + this.account + "," + this.comment;}//Sets the entryString, this is the value to be stored in the file
    public void setType(int type)throws InvalidEntryException
    {
        if(type > 0){this.type = type;}
        else{throw new InvalidEntryException("No Type Selected");}
        setEntryString();
    }
    public void setAmount(double amount)throws InvalidEntryException
    {
        if(amount > 0){this.dollarAmount = amount;}
        else{throw new InvalidEntryException("Invalid Amount");}
        setEntryString();
    }
    public void setCategory(int category)throws InvalidEntryException
    {
        if(category > 0){this.category = category;}
        else{throw new InvalidEntryException("No Category Selected");}
        setEntryString();
    }
    public void setSubcategory(int subcategory)throws InvalidEntryException
    {
        if(subcategory != 0){this.subcategory = subcategory;}
        else{throw new InvalidEntryException("No Subcategory Selected");}
        setEntryString();
    }
    public void setSubcategory2(int subcategory2)throws InvalidEntryException
    {
        if(subcategory2 != 0){this.subcategory2 = subcategory2;}
        else{throw new InvalidEntryException("No Subcategory2 Selected");}
        setEntryString();
    }
    public void setSubcategory3(int subcategory3)throws InvalidEntryException
    {
        if(subcategory3 != 0){this.subcategory3 = subcategory3;}
        else{throw new InvalidEntryException("No Subcategory3 Selected");}
        setEntryString();
    }
    public void setSubcategory4(int subcategory4)throws InvalidEntryException
    {
        if(subcategory4 != 0){this.subcategory4 = subcategory4;}
        else{throw new InvalidEntryException("No Subcategory4 Selected");}
        setEntryString();
    }
    public void setAccount(int account)throws InvalidEntryException
    {
        if(account > 0){this.account = account;}
        else{throw new InvalidEntryException("No Account Selected");}
        setEntryString();
    }
    public void setComment(String comment)
    {
        if(comment == null || comment.equals("")){this.comment = "";}
        else{this.comment = comment;}
        setEntryString();
    }
    
    //resetMethods
    public void resetType(){this.type = -1;}
    public void resetCategory(){this.category = -1;}
    public void resetSub1(){this.subcategory = -1;}
    public void restSub2(){this.subcategory2 = -1;}
    public void restSub3(){this.subcategory3 = -1;}
    public void restSub4(){this.subcategory4 = -1;}
    public void resetAccount(){this.account = -1;}

    /*Getters*/
    public int getEntryNumber(){return this.entryNumber;}
    public double getAmount(){return this.dollarAmount;}
    public int getType(){return this.type;}
    public int getCategory(){return this.category;}
    public int getSubcategory(){return this.subcategory;}
    public int getSubcategory2(){return this.subcategory2;}
    public int getSubcategory3(){return this.subcategory3;}
    public int getSubcategory4(){return this.subcategory4;}
    public int getAccount(){return this.account;}
    public String getComment(){return this.comment;}
    public String getEntryString(){return this.entryString;}
    public String getTextEntryString(){return this.textEntryString;}

    @Override
    public String toString()
    {
        return this.entryString;
    }
    /*TEST MAIN*/
    public static void main(String[] args) {
        try {
            Entry testEntry = new Entry(1);
            testEntry.setAmount(12.22);
            testEntry.setType(2);
            testEntry.setCategory(2);
            testEntry.setSubcategory(1);
            testEntry.setSubcategory2(2);
            testEntry.setSubcategory3(1);
            testEntry.setSubcategory4(3);
            testEntry.setAccount(2);
            testEntry.setComment("Test Comment");
            System.out.println(testEntry.getEntryString());
        } catch (InvalidEntryException e) {
            System.out.println(e);
        }
    }

}

