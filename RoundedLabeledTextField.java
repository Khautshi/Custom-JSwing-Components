package Components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RoundedLabeledTextField extends JTextField{

    public Color getAreaColor() {
        return areaColor;
    }

    public void setAreaColor(Color areaColor) {
        this.areaColor = areaColor;
        repaint();
    }

    public int getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
        repaint();
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }
    
    public String getLabel(){
        return label;
    }
    
    public void setLabel(String label){
        this.label = label;
        repaint();
    }
    
    private int borderSize = 1;
    private Color areaColor = new Color(2,15,20);
    private Color borderColor = new Color(103,113,137);
    private boolean focus = false;
    private String label = "Label";
    
    Font labelFont, bodyFont;
    String fontPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "Fonts" + File.separator;
    
    public RoundedLabeledTextField(){
        setOpaque(false);
        setBackground(new Color (0,0,0,0));
        setForeground(new Color(226,226,226));
        loadFont();
        setFont(bodyFont);
        setText("");
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent me){
                setBorderSize(2);
            }
            @Override
            public void mouseExited(MouseEvent me){
                if(!focus){
                    setBorderSize(1);
                }
            }
        });
        addFocusListener(new FocusAdapter(){
           @Override
           public void focusGained(FocusEvent me){
               focus = true;
               setBorderSize(2);
           }
           @Override
           public void focusLost(FocusEvent me){
               focus = false;
               setBorderSize(1);
           }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g){
        int width = getWidth() - 4;
        int height = getHeight() - 14;
        int roundness = height;
        EmptyBorder border = new EmptyBorder(12, getHeight()/2,0 ,getHeight()/2);
        setBorder(border);
        Graphics2D g1 = (Graphics2D) g.create();
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g1.setColor(areaColor);
        g1.fillRoundRect(2, 12, width, height, roundness, roundness);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(borderSize > 0){
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(borderSize));
            g2.drawRoundRect(2, 12, width, height, roundness, roundness);
        } else {
            g1.drawRoundRect(2, 12, width, height, roundness, roundness);
        }
        g1.dispose();
        Graphics2D g3 = (Graphics2D) g;
        g3.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g3.setFont(labelFont);
        g3.drawString(label, 8, 9);
        super.paintComponent(g);  
    }
    
    private void loadFont(){
        try{
            labelFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath + "Roboto-Regular.ttf")).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontPath + "Roboto-Regular.ttf")));
        }catch(IOException | FontFormatException e){
            
        }
        try{
            bodyFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath + "Roboto-Regular.ttf")).deriveFont(14f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontPath + "Roboto-Regular.ttf")));
        }catch(IOException | FontFormatException e){
            
        }
    }
    
    
}
