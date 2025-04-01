
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Class Name: FileManager
 * Credit: Pablo Bandera Lopez
 * Created: 03/31/2025
 * Modified: 
 * 
 * Description: Class deals with File I/O. Has three sets of methds:
 * FILE PATH & DIRECTORY: Used to construct strings pointing to the files needed to read and write and create and delete files. Uses Calendar to determine the current Month and Year.
 * PRINTING METHODS: Use PrintWriter to print out to a file based on the path given.
 * READING METHODS: Use Scanner to read information from the input document.
 * 
 * Attributes:
 * - month: String
 * - year: String
 * - C: Calendar
 * 
 * Methods:
 * + <<constructor>>FileManager()
 * + getCurrentMonth(): String
 * + getCurrentYear(): String
 * + getFilePath(String, String): String
 * + getDirectoryPath(String): String
 * + filePathExists(String, String): boolean
 * + directoryExists(String): boolean
 * + makeDir(String): void
 * + makeFile(String): void
 * + rmvFile(String): void
 * + updateFile(Entries): void
 * + readEntries(String): Entries
 * + entryStringToEntry(String): Entry
 * + getNumberOfEntries(String): int
 * 
 */
public class FileManager {
    
    /*Attributes*/
    private String month, year;
    final private static Calendar C = Calendar.getInstance();


    @SuppressWarnings("OverridableMethodCallInConstructor")
    public FileManager()//Constructor
    {   
        
        String m = this.getCurrentMonth();
        String y = this.getCurrentYear();
        if(m != null && !m.isEmpty()){this.month = m;}
        if(y != null && !y.isEmpty()) {this.year  = y;}
        
    }


    /* FILE PATH & DIRECTORY METHODS */
    public String getCurrentMonth()
    {
        int monthInt = C.get(Calendar.MONTH);
        DateFormatSymbols symbols = new DateFormatSymbols();
        String [] months = symbols.getMonths();
        return months[monthInt];
    }

    public String getCurrentYear()
    {
        int yearInt = C.get(Calendar.YEAR);
        return String.valueOf(yearInt);
    }

    public String getFilePath(String m, String y)
    {
        String path = "./Files/"+y+"/"+m+".txt";
        return path;
    }

    public String getDirectoryPath(String y)
    {
        String path = "./Files/"+y;
        return path;
    }
    
    public boolean filePathExists(String m, String y) {return Files.exists(Paths.get(getFilePath(m, y)));}
    public boolean directoryExists(String y) {return Files.exists(Paths.get(getDirectoryPath(y)));}

    public void makeDir(String path)
    {
        File f = new File(path);
        f.mkdirs();
    }

    public void makeFile(String path)
    {
        File f = new File(path);
        try{f.createNewFile();}
        catch(IOException e){JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);}
    }

    public void rmvFile(String path)
    {
        File f = new File(path);
        try{f.delete();}catch(Exception e){}
    }



    /* PRINTING METHODS */
    public void updateFile(Entries entries)//Updates the file with the current entries
    {
    
        try (PrintWriter pw = new PrintWriter(new File(getFilePath(month, year)))) {
            for(Entry e : entries.getEntries())
            {
                pw.println(e.toString());
            }
        } catch (FileNotFoundException e) 
        {
            JOptionPane.showMessageDialog(null, e.toString(), "Error: File Not Found", JOptionPane.ERROR_MESSAGE);
        }
        
    }


    /* READING METHODS */
    public Entries readEntries(String path)//Loads the file into the entries
    {
        try (Scanner s = new Scanner(new File(path));){
            String entryString;
            Entry e;
            Entries entries = new Entries();
            
            while(s.hasNextLine())
            {
                entryString = s.nextLine();
                try{
                    e = entryStringToEntry(entryString);
                    entries.addEntryNoUpdate(e);
                    
                }catch(FileFormatException ex){}
            }
            s.close();
            return entries;
        } catch (FileNotFoundException ex) 
        {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error: File Not Found", JOptionPane.ERROR_MESSAGE);
        }
        return null;
        
    }

    public Entry entryStringToEntry(String s) throws FileFormatException
    {

        String [] tokens = s.split(",");
        if(tokens.length < 10){throw new FileFormatException("Error Reading File");}
        try
        {
            Entry e  = new Entry(Integer.parseInt(tokens[0]));
            e.setAmount(Double.parseDouble(tokens[1]));
            e.setType(Integer.parseInt(tokens[2]));
            e.setCategory(Integer.parseInt(tokens[3]));
            int i = Integer.parseInt(tokens[4]);
            if(i > 0){e.setSubcategory(i);}
            i = Integer.parseInt(tokens[5]);
            if(i > 0){e.setSubcategory2(i);}
            i = Integer.parseInt(tokens[6]);
            if(i > 0){e.setSubcategory3(i);}
            i = Integer.parseInt(tokens[7]);
            if(i > 0){e.setSubcategory4(i);}
            e.setAccount(Integer.parseInt(tokens[8]));
            e.setComment(tokens[9]);
            return e;

        }catch(InvalidEntryException e){}
        return null;
    }

    public int getNumberOfEntries(String path)//Returns the number of entries in the file
    {  
        try (Scanner s = new Scanner(new File(path));){
            int counter = 0;
            while(s.hasNextLine()){
                counter ++;
                s.nextLine();
            }
            s.close();
            return counter;
        }catch (FileNotFoundException ex){}
        return 0;
    }

    /* TEST MAIN */
    public static void main(String[] args)
    {
        FileManager fm  = new FileManager(); 
        Decoder d;

        System.out.println("Num of Entries: "+ fm.getNumberOfEntries(fm.getFilePath(fm.month, fm.year)));
        System.out.println();

        Entries entries = fm.readEntries(fm.getFilePath(fm.getCurrentMonth(), fm.getCurrentYear()));
        ArrayList<Entry> ent = entries.getEntries();

        for(int i = 0; i < ent.size(); i ++)
        {
            System.out.println("RAW ENTRY: "+ent.get(i).getEntryString());
            d = new Decoder(ent.get(i).getEntryString());
            System.out.println("Decoded Data: "+d.getDecoded());
            System.out.println("Charged to: "+d.decodeAccount()+" Account.");
            System.out.println();
        }
        
        //MAKE NEW FILE
        // if(!fm.filePathExists(fm.month, fm.year)){
        //     if(!fm.directoryExists(fm.year))
        //     {
        //         fm.makeDir(fm.getDirectoryPath(fm.year));
        //     }
        //     fm.makeFile(fm.getFilePath(fm.month, fm.year));
        // }
        //DELETE CURRENT FILE
        //fm.rmvFile(fm.getFilePath(fm.month, fm.year));

        
        

    }

   
}
