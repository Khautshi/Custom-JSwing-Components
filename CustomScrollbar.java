
package Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class CustomScrollbar extends JScrollBar{

    public int getThumbSize() {
        return thumbSize;
    }

    public void setThumbSize(int thumbSize) {
        this.thumbSize = thumbSize;
        repaint();
    }
    
    public Color getThumbColor(){
        return thumbColor;
    }
    
    public void setThumbColor(Color thumbColor){
        this.thumbColor = thumbColor;
        repaint();
    }
    
    private int thumbSize = 70;
    private Color thumbColor = new Color(103, 110, 137, 70);
    private Color BGColor = thumbColor;
    private boolean clicked = false;

    public CustomScrollbar() {
        setUI(new CustomSBUI());
        setPreferredSize(new Dimension(8,8));
        setForeground(Color.blue);
        setBackground(new Color(0,0,0,0));
        setOpaque(false);
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent me){
                BGColor = new Color(thumbColor.getRed(), thumbColor.getGreen(), thumbColor.getBlue(), 255);
            }
            @Override
            public void mouseExited(MouseEvent me){
                if(!clicked){
                BGColor = new Color(thumbColor.getRed(), thumbColor.getGreen(), thumbColor.getBlue(), 70);
                }
            }
            @Override
            public void mousePressed(MouseEvent me){
                BGColor = new Color(thumbColor.getRed(), thumbColor.getGreen(), thumbColor.getBlue(), 255);
                clicked = true;
            }
            @Override
            public void mouseReleased(MouseEvent me){
                BGColor = new Color(thumbColor.getRed(), thumbColor.getGreen(), thumbColor.getBlue(), 70);
                clicked = false;
            }
        });
    }
 
    public class CustomSBUI extends BasicScrollBarUI {
    
    @Override
    protected Dimension getMaximumThumbSize() {
        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            return new Dimension(0, getThumbSize());
        } else {
            return new Dimension(getThumbSize(),0);
        }
    }

    @Override
    protected Dimension getMinimumThumbSize() {
        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            return new Dimension(0, getThumbSize());
        } else {
            return new Dimension(getThumbSize(),0);
        }
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new ScrollBarrButton();
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new ScrollBarrButton();
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        Graphics2D g1 = (Graphics2D) g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int orientation = scrollbar.getOrientation();
        int size;
        int x;
        int y;
        int width;
        int height;
        if (orientation == JScrollBar.VERTICAL) {
            size = trackBounds.width / 2;
            x = trackBounds.x + ((trackBounds.width - size) / 2);
            y = trackBounds.y;
            width = size;
            height = trackBounds.height;
        } else {
            size = trackBounds.height / 2;
            y = trackBounds.y + ((trackBounds.height - size) / 2);
            x = 0;
            height = size;
            width = trackBounds.width;
        }
        g1.setColor(new Color(0,0,0,0));
        g1.fillRect(x, y, width, height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        Graphics2D g1 = (Graphics2D) g.create();
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x = thumbBounds.x;
        int y = thumbBounds.y;
        int width = thumbBounds.width;
        int height = thumbBounds.height;
        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            y += 8;
            height -= 16;
        } else {
            x += 8;
            width -= 16;
        }
        g1.setColor(BGColor);
        g1.fillRoundRect(x, y, width, height, 10, 10);
    }

    private class ScrollBarrButton extends JButton{

        public ScrollBarrButton() {
            setBorder(BorderFactory.createEmptyBorder());
        }

        @Override
        protected void paintComponent(Graphics g) {
            
        }
        
        
    }
    
}

    
}

