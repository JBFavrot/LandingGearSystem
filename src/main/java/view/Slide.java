package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;

public class Slide extends JSlider {
	
	  public Slide(){
	    
	    this.setOrientation(SwingConstants.VERTICAL);
	    //this.setSize(20, 50);
	    this.setPaintTicks(true);
	    this.setPaintLabels(true);
	    /*slide.addChangeListener(new ChangeListener(){
	      public void stateChanged(ChangeEvent event){
	        label.setText("Valeur actuelle : " + ((JSlider)event.getSource()).getValue());
	      }
	    });*/      
	  }
}
