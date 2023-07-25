package raven.cell;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author RAVEN
 */
public class TableActionCellRender1 extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSeleted, boolean bln1, int row, int column) {
        Component com = super.getTableCellRendererComponent(jtable, o, isSeleted, bln1, row, column);
        PanelPlus action = new PanelPlus();
        if (isSeleted == true) {
            action.setBackground(new Color(133, 127, 114));
        } else {
            if (row % 2 == 0) {
                action.setBackground(new Color(39, 36, 29));
            } else {
                action.setBackground(new Color(66, 61, 51));
            }
        }
        return action;
    }
}
