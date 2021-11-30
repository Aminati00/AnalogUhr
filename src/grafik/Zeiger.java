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
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.swing.JComponent;
import java.util.logging.Logger;

/**
 *
 * @author le
 */
public class Zeiger extends JComponent implements Runnable // JPanel
{
  private static Logger lg = Logger.getLogger("grafik");
  private Rectangle2D.Double rect;
  private BasicStroke pinsel;
  private long zeiger_Breite;
  private long WARTEZEIT = 5000;
  private float maxRadius;
  private ExecutorService eService;
  private Future task;
  private static final float DICKE = 2f;
  private AffineTransform at;
  private float x;
  private float y;
  private int Zeigerart;
  private float y_Zeiger;
  private float x_Zeiger;
  private float zeiger_Laenge;
  
  public Zeiger(int i)
  {
    this.at = new AffineTransform();
    pinsel = new BasicStroke(DICKE);
    maxRadius = 200;
    eService = Executors.newSingleThreadExecutor();
    task = null;
    this.rect = new Rectangle2D.Double(20, 20, 100, zeiger_Breite);
    this.Zeigerart = i;
    y_Zeiger = 0;
    x_Zeiger = 0;
    zeiger_Laenge = 0;
    zeiger_Breite = 10;
    
      switch (Zeigerart) {
          case 0:
              zeiger_Breite = zeiger_Breite - 6;
              WARTEZEIT = 1000; //geht nur bis 90
              break;
          case 1:
              zeiger_Breite = zeiger_Breite - 2;
              WARTEZEIT = 61 * 1000;
              break;
          case 2:
              WARTEZEIT = 61 * 61 * 1000;
              break;
          default:
              break;
      }
  }
  
  public void start()
  {
    if (task == null)
    {
      task = eService.submit(this);
    }
  }
  
  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g; // neue Lib benutzen!!!!!!!
    g2.setTransform(this.at);
    
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
    
    int breite = this.getWidth() - 1;
    int hoehe = this.getHeight() - 1;
    
    synchronized(this)
    {
      maxRadius = -2 + Math.min(breite, hoehe)/2;
    }
    
    switch (Zeigerart) {
        case 0:
            zeiger_Laenge = maxRadius;
            break;
        case 1:
            zeiger_Laenge = maxRadius - 30f;
            break;
        case 2:
            zeiger_Laenge = maxRadius - 70f;
            break;
    }
    
    x = breite/2f;
    y = hoehe/2f;
    
    x_Zeiger = x - zeiger_Breite/2;
    y_Zeiger = y - zeiger_Laenge;
    
      switch (Zeigerart) {
          case 0:
              rect.setFrame(x_Zeiger, y_Zeiger, zeiger_Breite, zeiger_Laenge);
              break;
          case 1:
              rect.setFrame(x_Zeiger, y_Zeiger, zeiger_Breite, zeiger_Laenge);
              break;
          case 2:
              rect.setFrame(x_Zeiger, y_Zeiger, zeiger_Breite, zeiger_Laenge);
              break;
          default:
              break;
      }
    
    g2.setStroke(pinsel);
    g2.setPaint(Color.WHITE);
    g2.fill(rect);
    g2.setPaint(Color.RED);
    g2.draw(rect);
  }
  
  public void Ausgabe()
  {
      
  }

  @Override
  public void run()
  {

    
    while (true)
    { 

      try
      {
        Thread.sleep(WARTEZEIT);
      }
      catch (Exception ex)
      {
        lg.warning(ex.toString());
      }
      
      synchronized(this)
      {
        this.at.rotate(Math.toRadians(6), x, y);
      }
      
      this.repaint();  // Neuzeichnen -> paintComponent
      
    }
  }
  
}
