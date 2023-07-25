package raven.tabbed;

import java.awt.Color;
import javax.swing.JTabbedPane;

/**
 *
 * @author RAVEN
 */
public class TabbedPaneCustom extends JTabbedPane {

    public Color getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
        repaint();
    }

    public Color getUnselectedColor() {
        return unselectedColor;
    }

    public void setUnselectedColor(Color unselectedColor) {
        this.unselectedColor = unselectedColor;
        repaint();
    }

    
    
    public Color getSelectedTextColor() {
        return selectedTextColor;
    }

    public void setSelectedTextColor(Color selectedTextColor) {
        this.selectedTextColor = selectedTextColor;
        repaint();
    }

    public Color getUnselectedTextColor() {
        return unselectedTextColor;
    }

    public void setUnselectedTextColor(Color unselectedTextColor) {
        this.unselectedTextColor = unselectedTextColor;
        repaint();
    }
    
    private Color selectedColor = new Color(248, 91, 50);
    private Color unselectedColor = new Color(230, 230, 230);
    
    
    private Color selectedTextColor = new Color(230, 230, 230);
    private Color unselectedTextColor = new Color(248, 91, 50);

    public TabbedPaneCustom() {
        setBackground(new Color(250, 250, 250));
        setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        setUI(new TabbedPaneCustomUI(this));
    }
}
