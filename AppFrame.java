/**
 * Class Name: AppFrame
 * Credit: Pablo Bandera Lopez
 * Created: 03/05/2025
 * Modified:
 * 
 * Description: Abstract Class, BluePrint for every App window
 * 
 * Attributes:
 * - title: String
 * - windowTitle: String
 * - titleFont: Font
 * - centerLayout: Layout
 * - windowWidth: int
 * - windowHeight: int
 * - mainFrame: JFrame
 * - panelTitle: JPanel
 * - panelMainContainer: JPanel
 * - labelTitle: JLabel
 * 
 * Methods:
 * + <<constructor>>AppFrame(String, LayoutManager):void
 * - createMainFrame(): void
 * - createTitlePanel(): void
 * - createMainContainer(): void
 * - createFrameTitle(): void
 * - loadMainFrame(): void
 * + addToMainContainer(JComponent): void throws LayoutMismatchException
 * + addToMainContainer(JComponent, int): void throws LayoutMismatchException
 * + dispose(): void
 * + setVisible(): void
 * + setLayout(): void
 * + getWindowWidth(): int
 * + getWindowHeight(): int
 */

import java.awt.*;
import javax.swing.*;

public abstract class AppFrame extends JFrame
{
    //Attributes
    private String title;
    final private String windowTitle = "Finance Tracker V1.2";
    final private Font titleFont = new Font("Times New Roman",0, 28);
    final private LayoutManager centerLayout;

    final private int WINDOW_WIDTH = 1000, WINDOW_HEIGHT = 800;

    private JFrame mainFrame;
    private JPanel panelTitle, panelMainContainer;
    private JLabel labelTitle;

    //Constructor
    public AppFrame(String title, LayoutManager layout)
    {
        if(title == null || title.equals("")){ this.title = "Invalid Title";} //Check for vlaid title
        this.title = title;
        this.centerLayout = layout;

        createMainFrame();
        createTitlePanel();
        createMainContainer();
        createFrameTitle();
        loadMainFrame();

    }   

    /*Private Methods*/

    //Creates main window
    private void createMainFrame()
    {
        this.mainFrame = new JFrame(this.windowTitle);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(new Dimension(this.WINDOW_WIDTH,this.WINDOW_HEIGHT));
        this.mainFrame.setResizable(false);
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setVisible(true);
    }

    //Creates Panel for window Title
    private void createTitlePanel()
    {
        this.panelTitle = new JPanel();
        this.panelTitle.setVisible(true);
        this.panelTitle.setPreferredSize(new Dimension(100,100)); 
        this.panelTitle.setLayout(new BorderLayout(0,2));
        this.panelTitle.setBorder(BorderFactory.createEtchedBorder(1));
    }

    //Creates Panel for window Body
    private void createMainContainer()
    {
        this.panelMainContainer = new JPanel();
        this.panelMainContainer.setBackground(Color.lightGray);
        this.panelMainContainer.setVisible(true);
        this.setLayout(this.centerLayout);
    }

    //Creates label and sets text for window title, adds to panel
    private void createFrameTitle()
    {
        this.labelTitle = new JLabel(this.title);
        this.labelTitle.setFont(this.titleFont);
        this.labelTitle.setVerticalTextPosition(JLabel.CENTER);
        this.labelTitle.setHorizontalAlignment(JLabel.CENTER);
        this.labelTitle.setVisible(true);
        this.labelTitle.setBorder(BorderFactory.createBevelBorder(0));
        this.panelTitle.add(this.labelTitle);
    }
    
    //Adds title panel and main container panel to the window
    private void loadMainFrame()
    {
        this.mainFrame.add(this.panelTitle, BorderLayout.NORTH);
        this.mainFrame.add(this.panelMainContainer, BorderLayout.CENTER);
    }

    /*Public Methods */

    //Adds JComponent to MainContainer if the layout fo maincontainer is null. Throws exception if layout is not set to null.
    public void addToMainContainer(JComponent component) throws LayoutMismatchException
    {   
        if(this.panelMainContainer.getLayout() instanceof GridLayout){throw new LayoutMismatchException("Main Container Layout is: GridLayout, requires index to be added to");}//Incorrect method for container layout
        this.panelMainContainer.add(component);
    }

    //Adds JComponent to MainContainer if the layout fo maincontainer is GridLayout. Throws exception if and index is not passed.
    public void addToMainContainer(JComponent component, int index) throws LayoutMismatchException
    {
        if(this.panelMainContainer.getLayout() == null){throw new LayoutMismatchException("Main Container Layout is: NULL, excess of parameters");}//Incorrect method for container layout
        if(this.panelMainContainer.getLayout() instanceof GridLayout){this.panelMainContainer.add(component, index);}

    }

    //Override Dispose method for mainFrame
    @Override
    public void dispose() {this.mainFrame.dispose();}

    //Override setVisible methhod for mainFrame
    @Override
    public void setVisible(boolean v) {mainFrame.setVisible(v);}

    //Override setLayout method so layout is applied to mainContainer panel
    @Override
    public void setLayout(LayoutManager mgr)
    {
        if(this.panelMainContainer != null){this.panelMainContainer.setLayout(mgr);}
    }

    /* Getters */
    public int getWindowWidth() {return this.WINDOW_WIDTH;}
    public int getWindowHeight() {return this.WINDOW_HEIGHT;}

}