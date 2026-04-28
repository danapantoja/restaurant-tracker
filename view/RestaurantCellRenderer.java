package view;

import java.awt.*;

import javax.swing.*;
import model.Restaurant;

import model.Restaurant;

//display for each restaurant in the scrollable list
public class RestaurantCellRenderer extends JPanel implements ListCellRenderer<Restaurant>{

    private JLabel nameLbl;
    private JLabel infoLbl;
    private JLabel reviewLbl;
    public RestaurantCellRenderer(){
        setLayout(new GridLayout(2, 1));
        setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        nameLbl = new JLabel();
        infoLbl = new JLabel();

        add(nameLbl);
        add(infoLbl);
        

    }
    @Override
    public Component getListCellRendererComponent(JList<? extends Restaurant> list, Restaurant value, int index,
            boolean isSelected, boolean cellHasFocus) {
            nameLbl.setText(value.getName());
            infoLbl.setText(value.getLocation() + " | " + value.getCuisine());
            
            //TODO: add label for rating and review

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                nameLbl.setForeground(list.getSelectionForeground());
                infoLbl.setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                nameLbl.setForeground(list.getForeground());
                infoLbl.setForeground(list.getForeground());
            }
            setOpaque(true);
            return this;

    }
    
}
