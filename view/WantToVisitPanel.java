package view;
import java.awt.*;

import javax.swing.*;

import model.Restaurant;
import service.RestaurantManager;

public class WantToVisitPanel extends JPanel{
    private JButton searchBtn;
    private JTextField searchField;
    private JButton addBtn;
    private JButton editBtn;
    private JButton moveBtn;
    private JButton deleteBtn;
    private JButton refreshBtn;

    private JList<Restaurant> restaurantList;
    private DefaultListModel<Restaurant> listModel;

    private RestaurantManager manager;
    public WantToVisitPanel(RestaurantManager manager){
        this.manager = manager;

        setLayout(new BorderLayout());
        //list of restaurants here scrollable (use restuaurant cell renderer)
        listModel = new DefaultListModel<>();
        for (Restaurant r : manager.getVisited()) {
            listModel.addElement(r);
        }
        restaurantList = new JList<>(listModel);
        restaurantList.setCellRenderer(new RestaurantCellRenderer(true));

        JScrollPane scrollPane = new JScrollPane(restaurantList);
        add(scrollPane, BorderLayout.CENTER);

        refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> refreshList());

    
        //add search functionality on top
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
            
            new AddNewGUI(manager, true);
            
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
        moveBtn = new JButton ("Move to Visited");
        moveBtn.addActionListener(e -> {
            Restaurant selected = restaurantList.getSelectedValue();

            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Select a restaurant first.");
                return;
            }

            new EditGUI(selected, manager, true);
            listModel.setElementAt(selected, restaurantList.getSelectedIndex());
           
        });

        deleteBtn = new JButton("Delete Restaurant");
        deleteBtn.addActionListener(e -> {
            Restaurant selected = restaurantList.getSelectedValue();

            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Select a restaurant first.");
                return;
            }
            manager.deleteFromWantToVisit(selected);
            listModel.removeElement(selected);
            refreshList();
        });

        bottomPanel.add(addBtn);
        bottomPanel.add(editBtn);
        bottomPanel.add(moveBtn);
        bottomPanel.add(deleteBtn);
        add(bottomPanel, BorderLayout.SOUTH);
        revalidate();
        repaint();
        
    }
    private void refreshList() {
        listModel.clear();
        this.searchField.setText("");

        for (Restaurant r : manager.getWantToVisit()) {
            listModel.addElement(r);
        }
    }
    
}
