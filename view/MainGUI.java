package view;
import javax.swing.*;


public class MainGUI extends JFrame{

    private JTabbedPane tabbedPane;
    private VisitedPanel visitedPanel;
    private WantToVisitPanel wantToVisitPanel;


    public MainGUI() {
        //title
        setTitle("Restaurant Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        //create new panes from custom classes
        tabbedPane = new JTabbedPane();
        visitedPanel = new VisitedPanel();
        wantToVisitPanel = new WantToVisitPanel();

        //add to main gui
        tabbedPane.addTab("Visited", visitedPanel);
        tabbedPane.addTab("Want to Visit", wantToVisitPanel);
        add(tabbedPane);


        setVisible(true);



    }
    
}
