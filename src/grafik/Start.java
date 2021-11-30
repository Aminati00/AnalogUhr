/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafik;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.OverlayLayout;
import javax.swing.WindowConstants;

/**
 *
 * @author le
 */
public class Start 
{
  public Start()
  {
    JFrame fenster = new JFrame();
    fenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    Container cont = fenster.getContentPane();
    cont.setLayout(new OverlayLayout(cont));
    
    for (int i = 0; i <= 2; i++)
    {
       Zeiger zeiger = new Zeiger(i);
       zeiger.setOpaque(false); // undurchsichtig -> false => ist also durchsichtig
       cont.add(zeiger);
       zeiger.start(); 
    }

    fenster.setSize(800, 600);
    fenster.setVisible(true);
    
  }

  public static void main(String[] args) 
  {
    new Start();
  }

}
