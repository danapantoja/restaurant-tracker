package view;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import model.Restaurant;

//display for each restaurant in the scrollable list
public class RestaurantCellRenderer extends JPanel implements ListCellRenderer<Restaurant>{



    
    @Override
    public Component getListCellRendererComponent(JList<? extends Restaurant> list, Restaurant value, int index,
            boolean isSelected, boolean cellHasFocus) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getListCellRendererComponent'");
    }
    
}
