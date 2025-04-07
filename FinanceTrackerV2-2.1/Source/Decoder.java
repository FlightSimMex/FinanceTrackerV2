/**
 * Class Name: Decoder
 * Credit: Pablo Bandera Lopez
 * Created: 03/31/2025
 * Modified: 
 * 
 * Description: Contains data and appropriet methods to translate an entry obect into its entry String.
 * 
 * Attributes:
 * - encoded: String
 * - tokens: String []
 * - decoded: String
 * 
 * Methods:
 * + <<constructor>>Decoder(String):void
 * - parseEncoded(): void
 * - determineTypr(): void
 * - determineCategory(): void
 * - determineSubCategory(): void
 * - determineSubCategory2(): void
 * - determineSubCategory3(): void
 * - determineSubCategory4(): void
 * + decodeAccount(): String
 * + getDecoded(): String
 * 
 */

public class Decoder {
    
    /* Attributes */
     
    private String encoded;
    private String [] tokens;
    private String decoded = "";

    public Decoder(String s)
    {
        if(s != null && !s.isEmpty()){this.encoded = s;}
        parseEncoded();
        determineType();
        determineCategory();
        determineSubCategory();
        determineSubCategory2();
        determineSubCategory3();
        determineSubCategory4();
    }

    private void parseEncoded()
    {
        this.tokens = this.encoded.split(",");
    }


    private void determineType()
    {
        String type = tokens[2];
        if(type.equals("1")){type = "Income";}
        else{type = "Expense";}
        decoded += type + ", ";
    }

    private void determineCategory()
    {
        String category = tokens[3];
        if(tokens[2].equals("1"))
        {
            switch(category){
                case "1" -> category = "Mother";
                case "2" -> category = "Pay Stub";
                case "3" -> category = "Other";
            }
        }else
        {
            switch(category){
                case "1" -> category = "Living";
                case "2" -> category = "Purchase";
                case "3" -> category = "Flight";
                case "4" -> category = "School";
            }
        }
        decoded += category + ", ";

    }

    private void determineSubCategory()
    {
        if(tokens[2].equals("1")){return;}
        String subcategory = tokens[4];
        switch (tokens[3]) {
            case "1" -> {
                switch(subcategory){
                    case "1" -> subcategory = "Appartment";
                    case "2" -> subcategory = "Food";
                    case "3" -> subcategory = "Transportation";
                }
            }
            case "2" -> {
                switch(subcategory){
                    case "1" -> subcategory = "Personal";
                    case "2" -> subcategory = "Travel";
                    case "3" -> subcategory = "Paperwork";
                }
            }
            case "3" -> {
                switch(subcategory){
                    case "1" -> subcategory = "Medical";
                    case "2" -> subcategory = "Currency";
                    case "3" -> subcategory = "Fun";
                }
            }
            default -> {
                switch(subcategory){
                    case "1" -> subcategory = "Tuition";
                    case "2" -> subcategory = "Flight Training";
                    case "3" -> subcategory = "Books";
                }
            }
        }
        if(Integer.parseInt(tokens[4])>0){decoded += subcategory + ", ";}
    }

    private void determineSubCategory2()
    {
        if(tokens[2].equals("1") || !(tokens[3].equals("1") || tokens[3].equals("4"))){return;}
        String subcategory2 = tokens[5];
        if(tokens[3].equals("1") && tokens[4].equals("1"))
        {
            switch(subcategory2){
                case "1" -> subcategory2 = "Rent";
                case "2" -> subcategory2 = "Expense";
            }
        }else if(tokens[3].equals("1") && tokens[4].equals("2"))
        {
            switch(subcategory2){
                case "1" -> subcategory2 = "Groceries";
                case "2" -> subcategory2 = "Food Out";
                case "3" -> subcategory2 = "Snacks";
            }
        }else if(tokens[3].equals("1") && tokens[4].equals("3"))
        {
            switch(subcategory2){
                case "1" -> subcategory2 = "Insurance";
                case "2" -> subcategory2 = "Gas";
                case "3" -> subcategory2 = "Tolls";
                case "4" -> subcategory2 = "Service";
                case "5" -> subcategory2 = "Cleaning";
                case "6" -> subcategory2 = "Other";
            }
        }else if(tokens[3].equals("4") && tokens[4].equals("2"))
        {
            switch(subcategory2){
                case "1" -> subcategory2 = "Training";
                case "2" -> subcategory2 = "Exam";
                case "3" -> subcategory2 = "Paperwprk";
            }
        }
        if(Integer.parseInt(tokens[5])>0){decoded += subcategory2 + ", ";}
    }

    private void determineSubCategory3()
    {
        if(tokens[2].equals("1") || ((!tokens[3].equals("1") && (!tokens[4].equals("1") || !tokens[4].equals("2") )))){return;}
        String subcategory3 = tokens[6];
        if(tokens[3].equals("1") && tokens[4].equals("1") && tokens[5].equals("1"))
        {
            switch(subcategory3){
                case "1" -> subcategory3 = "Base Rent";
                case "2" -> subcategory3 = "Utilities";
            }
        }else if(tokens[3].equals("1") && tokens[4].equals("1") && tokens[5].equals("2")){
            switch(subcategory3){
                case "1" -> subcategory3 = "Cleaning Supplies";
                case "2" -> subcategory3 = "Furniture";
                case "3" -> subcategory3 = "Kitchen";
            }
        }else if(tokens[3].equals("1") && tokens[4].equals("2") && tokens[5].equals("1")){
            switch(subcategory3){
                case "1" -> subcategory3 = "Supermarket";
                case "2" -> subcategory3 = "Bakery";
                case "3" -> subcategory3 = "Convenience";
            }
        }else if(tokens[3].equals("1") && tokens[4].equals("2") && tokens[5].equals("2")){
            switch(subcategory3){
                case "1" -> subcategory3 = "Eat Out";
                case "2" -> subcategory3 = "Take Out";
                case "3" -> subcategory3 = "Delivery";
            }
        }else if(tokens[3].equals("1") && tokens[4].equals("2") && tokens[5].equals("3")){
            switch(subcategory3){
                case "1" -> subcategory3 = "At School";
                case "2" -> subcategory3 = "Coffee";
                case "3" -> subcategory3 = "Ice Cream";
                case "4" -> subcategory3 = "Other";
            }
        }
        if(Integer.parseInt(tokens[6])>0){decoded += subcategory3 + ", ";}
    }

    private void determineSubCategory4()
    {
        if(tokens[2].equals("1") || !((tokens[3].equals("1") && tokens[4].equals("1") && tokens[5].equals("1") && tokens[6].equals("2")) || (tokens[3].equals("1") && tokens[4].equals("2") && tokens[5].equals("2") && tokens[6].equals("1")))){return;}
        String subcategory4 = tokens[7];
        if(tokens[3].equals("1") && tokens[4].equals("1") && tokens[5].equals("1") && tokens[6].equals("2")){
            switch(subcategory4){
                case "1" -> subcategory4 = "Power";
                case "2" -> subcategory4 = "Wifi";
                case "3" -> subcategory4 = "Cell";
                case "4" -> subcategory4 = "Water";
                case "5" -> subcategory4 = "Gas";
                case "6" -> subcategory4 = "Subscriptions";
            }
        }else if(tokens[3].equals("1") && tokens[4].equals("2") && tokens[5].equals("2") && tokens[6].equals("1")){
            switch(subcategory4){
                case "1" -> subcategory4 = "At School";
                case "2" -> subcategory4 = "Restaurant";
            }
        }
        if(Integer.parseInt(tokens[7])>0){decoded += subcategory4;}
    }

    public String decodeAccount()
    {
        String account = "";
        switch(tokens[8]){
            case "1" -> account = "Personal";
            case "2" -> account = "Flight";
            case "3" -> account = "Mother";
        }

        return account;
    }

    public String getDecoded() {return decoded;}


}
