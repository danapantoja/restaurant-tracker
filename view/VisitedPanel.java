package view;
import javax.swing.*;
import java.awt.*;
import model.Restaurant;
import service.RestaurantManager;


public class VisitedPanel extends JPanel{
    private JButton addBtn;
    private JButton editBtn;
    private JButton deleteBtn;
    private JButton searchBtn;
    private JTextField searchField;
    private JList<Restaurant> restaurantList;
    private DefaultListModel<Restaurant> listModel;
    private JButton refreshBtn;

    private RestaurantManager manager;

    public VisitedPanel(RestaurantManager manager){
        this.manager = manager;
        setLayout(new BorderLayout());


        //list of restaurants here scrollable (use restuaurant cell renderer)
        listModel = new DefaultListModel<>();
        for (Restaurant r : manager.getVisited()) {
            listModel.addElement(r);
        }
        restaurantList = new JList<>(listModel);
        restaurantList.setCellRenderer(new RestaurantCellRenderer(false));

        JScrollPane scrollPane = new JScrollPane(restaurantList);
        add(scrollPane, BorderLayout.CENTER);

        refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> refreshList());

    
        //add search functionality 
        searchField = new JTextField(20 );
        searchBtn = new JButton("Search by Name");
        searchField.addActionListener(e -> searchBtn.doClick());
        searchBtn.addActionListener(e ->{
            String searchTerm = searchField.getText().toLowerCase();
            listModel.clear();
            for (Restaurant r : this.manager.getVisited()) {
                if (searchTerm.isEmpty() || r.getName().toLowerCase().contains(searchTerm)) { //filters restaurants
                    listModel.addElement(r);
                }
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);
        topPanel.add(searchBtn);
        topPanel.add(refreshBtn);

        add(topPanel, BorderLayout.NORTH);


        //---bottom button panel---
        //add button to add new restaurant gui
        //add edit restuarant functionality when a user clicks on a restaurant and presses edit
        //add delete restaurant button functionality when user clicks on a restaurant and presses delete
        JPanel bottomPanel = new JPanel();
        addBtn = new JButton("Add New Restaurant");
        addBtn.addActionListener(e->{
            new AddNewGUI(manager, false);
            
        });
        editBtn = new JButton ("Edit Restaurant");
        editBtn.addActionListener(e -> {
            Restaurant selected = restaurantList.getSelectedValue();

            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Select a restaurant first.");
                return;
            }

            new EditGUI(selected, manager, false);
            listModel.setElementAt(selected, restaurantList.getSelectedIndex());
            
        });

        deleteBtn = new JButton("Delete Restaurant");
        deleteBtn.addActionListener(e -> {
            Restaurant selected = restaurantList.getSelectedValue();

            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Select a restaurant first.");
                return;
            }
            manager.deleteFromVisited(selected);
            listModel.removeElement(selected);
            refreshList();
        });

        bottomPanel.add(addBtn);
        bottomPanel.add(editBtn);
        bottomPanel.add(deleteBtn);
        add(bottomPanel, BorderLayout.SOUTH);
        revalidate();
        repaint();


    }
    private void refreshList() {
        listModel.clear();
        this.searchField.setText("");

        for (Restaurant r : manager.getVisited()) {
            listModel.addElement(r);
        }
    }


    
}
