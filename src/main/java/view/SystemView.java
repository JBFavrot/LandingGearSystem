package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;

public class SystemView extends JFrame{
	
	public SystemView() throws IOException {
		
	    this.setTitle("Landing Gear Control Pannel");
	    this.setSize(370, 700);
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);	    
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	            
	      
	    //*****************************HANDLER*******************************
	    
	    JLabel title_handler = new JLabel("Gears Handler");
	    title_handler.setBounds(10, 10, 100, 20);
	    
	    JSlider handler = new JSlider(JSlider.VERTICAL, 0, 40, 0);
        handler.setPaintTicks(true);
 
        Hashtable<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
        labels.put(0, new JLabel("DOWN"));
        labels.put(40, new JLabel("UP"));
        handler.setLabelTable(labels);
 
        handler.setPaintLabels(true);
        handler.setBounds(20, 50, 100, 150);
        
	    
        //****************************LED*************************************
        
	    String path_green = "./images/green.jpg";
        File file_green = new File(path_green);
        BufferedImage image_green;
	
		image_green = ImageIO.read(file_green);
		
	    JLabel lights = new JLabel();
	    lights.setBounds(200, 50, 75, 160);
	    lights.setIcon(new ImageIcon(image_green));
	    
	    this.add(title_handler);
	    this.add(handler);         
	    //this.add(up);
	    //this.add(down);
	    this.add(lights);
	    
	    
	    //******************************GEARS******************************
	    String path = "./images/gear_pic.PNG";
        File file = new File(path);
        BufferedImage image;
	
		image = ImageIO.read(file);
	    
	    JLabel gear1 = new JLabel("Gear 1");
	    gear1.setBounds(10, 280, 100, 10);
	    JLabel pic_gear1 = new JLabel();
	    pic_gear1.setBounds(10, 300, 100, 100);
	    pic_gear1.setIcon(new ImageIcon(image));
	    JLabel state_gear1 = new JLabel("State: UP");
	    state_gear1.setBounds(10, 420, 100, 10);
	    
	    JLabel gear2 = new JLabel("Gear 2");
	    gear2.setBounds(120, 280, 100, 10);
	    JLabel pic_gear2 = new JLabel();
	    pic_gear2.setBounds(120, 300, 100, 100);
	    pic_gear2.setIcon(new ImageIcon(image));
	    JLabel state_gear2 = new JLabel("State: UP");
	    state_gear2.setBounds(120, 420, 100, 10);
	    
	    JLabel gear3 = new JLabel("Gear 3");
	    gear3.setBounds(230, 280, 100, 10);
	    JLabel pic_gear3 = new JLabel();
	    pic_gear3.setBounds(230, 300, 100, 100);
	    pic_gear3.setIcon(new ImageIcon(image));
	    JLabel state_gear3 = new JLabel("State: UP");
	    state_gear3.setBounds(230, 420, 100, 10);
	    
	    this.add(gear1);
	    this.add(gear2);
	    this.add(gear3);
	
	    this.add(pic_gear1);
	    this.add(pic_gear2);
	    this.add(pic_gear3);
	    
	    this.add(state_gear1);
	    this.add(state_gear2);
	    this.add(state_gear3);
	    
	    
	    //************************DOORS******************************
	    
	    String path_door = "./images/door_pic.PNG";
        File file_door = new File(path_door);
        BufferedImage image_door;
	
		image_door = ImageIO.read(file_door);
	    
	    JLabel door1 = new JLabel("Door 1");
	    door1.setBounds(10, 470, 100, 20);
	    JLabel pic_door1 = new JLabel();
	    pic_door1.setBounds(10, 500, 106, 101);
	    pic_door1.setIcon(new ImageIcon(image_door));
	    JLabel state_door1 = new JLabel("State: CLOSED");
	    state_door1.setBounds(10, 620, 100, 20);
	    
	    JLabel door2 = new JLabel("Door 2");
	    door2.setBounds(120, 470, 100, 20);
	    JLabel pic_door2 = new JLabel();
	    pic_door2.setBounds(120, 500, 106, 101);
	    pic_door2.setIcon(new ImageIcon(image_door));
	    JLabel state_door2 = new JLabel("State: CLOSED");
	    state_door2.setBounds(120, 620, 100, 20);
	    
	    JLabel door3 = new JLabel("Door 3");
	    door3.setBounds(230, 470, 100, 20);
	    JLabel pic_door3 = new JLabel();
	    pic_door3.setBounds(230, 500, 106, 101);
	    pic_door3.setIcon(new ImageIcon(image_door));
	    JLabel state_door3 = new JLabel("State: CLOSED");
	    state_door3.setBounds(230, 620, 100, 20);
	    
	    
	    this.add(door1);
	    this.add(door2);
	    this.add(door3);
	    
	    this.add(pic_door1);
	    this.add(pic_door2);
	    this.add(pic_door3);
	    
	    this.add(state_door1);
	    this.add(state_door2);
	    this.add(state_door3);
	                 
	    this.setVisible(true);
		
	}
	

}
