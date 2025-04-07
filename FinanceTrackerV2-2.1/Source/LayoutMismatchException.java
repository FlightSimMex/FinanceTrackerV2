/**
 * Class Name: LayoutMismatchException
 * Credit: Pablo Bandera Lopez
 * Created: 03/05/2025
 * Modified: 
 * 
 * Description: Exception class for layout mismatches. This exception is thrown when a JComponent is being added to a JPanel and the position of the JComponent is not within the bounds of the JPanel's layout.
 * 
 * Attributes:
 * 
 * Methods:
 * + <<constructor>>LayoutMismatchException(String)
 * 
 * 
 */
public class LayoutMismatchException extends Exception {
    public LayoutMismatchException(String msg){
        super(msg);
    }
    
}
