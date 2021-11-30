/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafik;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;

/**
 *
 * @author le
 */
public class Zeichenflaeche extends JComponent // JPanel
{
  private Rectangle2D.Double rect;
  private BasicStroke pinsel;
  private static final float DICKE = 2f;
  private long Zeiger_Breite = 10;
  
  public Zeichenflaeche()
  {
    rect = new Rectangle2D.Double();
    pinsel = new BasicStroke(DICKE);
  }
  
  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g; // neue Lib benutzen!!!!!!!
    
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
    
    int breite = this.getWidth() - 1;
    int hoehe = this.getHeight() - 1;
    
    float x = breite/2f - Zeiger_Breite/2; 
    float y = hoehe/2f;
    
    float maxRadius = -2 + Math.min(breite, hoehe)/2;

    rect.setFrame(x, y, Zeiger_Breite, maxRadius);
    
    g2.setStroke(pinsel);
    g2.setPaint(Color.RED);
    
    g2.draw(rect);
    //g2.fill(ellipse);
  }
  
}
