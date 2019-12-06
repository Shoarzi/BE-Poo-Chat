package swing;
import javax.swing.*;
import java.awt.*;


public class Panneau extends JPanel {
	 public void paintComponent(Graphics g){
		    g.drawString("Contenu", 10, 20);
		    g.setColor(Color.BLACK);
		  }    
}
