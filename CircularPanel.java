package Components;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;


public class CircularPanel extends JComponent{
    
    private int diameter;
    
    public CircularPanel(){
        setOpaque(false);
    }
    
    @Override
    protected void paintComponent(Graphics grphcs){
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        int width = getWidth();
        int height = getHeight();
        diameter = Math.min(width, height) - 2;        
        //g2.drawOval(0, 0, diameter, diameter);
        g2.fillOval(1, 1, diameter, diameter);
        g2.dispose();
        super.paintComponent(grphcs);
    }
}
