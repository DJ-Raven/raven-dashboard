package raven.tabbed;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author RAVEN
 */
public class TabbedItem extends JToggleButton {

    protected final TabbedForm component;

    public TabbedItem(String name, TabbedForm component) {
        this.component = component;
        init(name);
    }

    private void init(String name) {
        setLayout(new MigLayout("", "[]10[]"));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "background:null;"
                + "arc:10;"
                + "margin:2,8,2,5");
        JButton cmd = new JButton(new FlatSVGIcon("raven/svg/close.svg", 0.8f));
        cmd.addActionListener((ae) -> {
            WindowsTabbed.getInstance().removeTab(this);
        });
        cmd.putClientProperty(FlatClientProperties.STYLE, ""
                + "margin:3,3,3,3;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "background:null;"
                + "arc:999;");
        add(new JLabel(name));
        add(cmd, BorderLayout.EAST);
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        if (!isSelected() && getParent().getComponentZOrder(this) != getParent().getComponentCount() - 1) {
            Graphics2D g2 = (Graphics2D) grphcs.create();
            FlatUIUtils.setRenderingHints(g2);
            g2.setColor(UIManager.getColor("Component.borderColor"));
            float m = UIScale.scale(5);
            float s = UIScale.scale(1);
            g2.fill(new Rectangle2D.Double(getWidth() - s, m, s, getHeight() - m * 2));
            g2.dispose();
        }
    }
}
