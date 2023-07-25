package proyect;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Model_menu {

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public menuType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(menuType type) {
        this.type = type;
    }

    public Model_menu(String icon, String name, menuType type) {
        this.icon = icon;
        this.name = name;
        this.type = type;
    }

    public Model_menu() {
    }

    private String icon;
    private String name;
    private menuType type;

    public Icon toIcon() {
        return new ImageIcon(getClass().getResource("/img/" + icon + ".png"));
    }

    public Icon toIconSelected() {
        return new ImageIcon(getClass().getResource("/img/" + icon + "_selected.png"));
    }
    
    public static enum menuType {
        MENU, EMPTY
    }
}
