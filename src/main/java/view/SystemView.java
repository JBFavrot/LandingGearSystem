package view;

import controler.SystemController;
import model.DoorModel;
import model.GearModel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SystemView extends JFrame implements ChangeListener{

    SystemController systemController = SystemController.getInstance();

	private static JLabel lights, state_gear1, state_gear2, state_gear3, state_door1, state_door2, state_door3, pic_gear1, pic_gear2, pic_gear3, pic_door1, pic_door2, pic_door3;

	public SystemView() throws IOException {
		
	    this.setTitle("Landing Gear Control Pannel");
	    this.setSize(370, 700);
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);	    
	    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	    //*****************************HANDLER*******************************
	    
	    JLabel title_handler = new JLabel("Gears Handler");
	    title_handler.setBounds(10, 10, 100, 20);
	    
	    JSlider handler = new JSlider(JSlider.VERTICAL, 0, 40, 40);
        handler.setPaintTicks(true);

		handler.addChangeListener(this);



        Hashtable<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
        labels.put(0, new JLabel("DOWN"));
        labels.put(40, new JLabel("UP"));
        handler.setLabelTable(labels);
 
        handler.setPaintLabels(true);
        handler.setBounds(20, 50, 100, 150);
        
	    
        //****************************LED*************************************
        
	    String path_vide = "./images/feu_vide.jpg";
        File file_vide = new File(path_vide);
        BufferedImage image_vide;
	
		image_vide = ImageIO.read(file_vide);
		
	    lights = new JLabel();
	    lights.setBounds(200, 50, 75, 160);
	    lights.setIcon(new ImageIcon(image_vide));
	    
	    this.add(title_handler);
	    this.add(handler);         
	    //this.add(up);
	    //this.add(down);
	    this.add(lights);
	    
	    
	    //******************************GEARS******************************
	    String path_gear = "./images/gear_retracted.jpg";
        File file_gear = new File(path_gear);
        BufferedImage image_gear;
	
		image_gear = ImageIO.read(file_gear);
	    
	    JLabel gear1 = new JLabel("Gear 1");
	    gear1.setBounds(10, 280, 100, 10);
	    pic_gear1 = new JLabel();
	    pic_gear1.setBounds(10, 300, 100, 100);
	    pic_gear1.setIcon(new ImageIcon(image_gear));
	    state_gear1 = new JLabel("State: UP");
	    state_gear1.setBounds(10, 420, 100, 10);
	    
	    JLabel gear2 = new JLabel("Gear 2");
	    gear2.setBounds(120, 280, 100, 10);
	    pic_gear2 = new JLabel();
	    pic_gear2.setBounds(120, 300, 100, 100);
	    pic_gear2.setIcon(new ImageIcon(image_gear));
	    state_gear2 = new JLabel("State: UP");
	    state_gear2.setBounds(120, 420, 100, 10);
	    
	    JLabel gear3 = new JLabel("Gear 3");
	    gear3.setBounds(230, 280, 100, 10);
	    pic_gear3 = new JLabel();
	    pic_gear3.setBounds(230, 300, 100, 100);
	    pic_gear3.setIcon(new ImageIcon(image_gear));
	    state_gear3 = new JLabel("State: UP");
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
	    
	    String path_door = "./images/door_closed.jpg";
        File file_door = new File(path_door);
        BufferedImage image_door;
	
		image_door = ImageIO.read(file_door);
	    
	    JLabel door1 = new JLabel("Door 1");
	    door1.setBounds(10, 470, 100, 20);
	    pic_door1 = new JLabel();
	    pic_door1.setBounds(10, 500, 106, 101);
	    pic_door1.setIcon(new ImageIcon(image_door));
	    state_door1 = new JLabel("State: CLOSED");
	    state_door1.setBounds(10, 620, 100, 20);
	    
	    JLabel door2 = new JLabel("Door 2");
	    door2.setBounds(120, 470, 100, 20);
	    pic_door2 = new JLabel();
	    pic_door2.setBounds(120, 500, 106, 101);
	    pic_door2.setIcon(new ImageIcon(image_door));
	    state_door2 = new JLabel("State: CLOSED");
	    state_door2.setBounds(120, 620, 100, 20);
	    
	    JLabel door3 = new JLabel("Door 3");
	    door3.setBounds(230, 470, 100, 20);
	    pic_door3 = new JLabel();
	    pic_door3.setBounds(230, 500, 106, 101);
	    pic_door3.setIcon(new ImageIcon(image_door));
	    state_door3 = new JLabel("State: CLOSED");
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


    /**
     * Invoked when the target of the listener has changed its state.
     *
     * @param e a ChangeEvent object
     */
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting())
        {
            int action = (int) source.getValue();
            if (action == 0 || action == 40) {
                systemController.changeSystemState();
			}
		}
    }

    public static void refreshDoor(DoorModel.states state) throws IOException
    {
        //TODO: Implement function
		String path = "";

		if(state == DoorModel.states.movingUp || state == DoorModel.states.movingDown )
		{
			path = "./images/door_moving.jpg";
		}
		else if(state == DoorModel.states.opened)
		{
			path = "./images/door_open.PNG";
		}
		else if(state == DoorModel.states.closed)
		{
			path = "./images/door_closed.jpg";
		}

		File file = new File(path);
		BufferedImage icon = ImageIO.read(file);

		pic_door1.setIcon(new ImageIcon(icon));
		pic_door2.setIcon(new ImageIcon(icon));
		pic_door3.setIcon(new ImageIcon(icon));

		state_door1.setText("State: "+state.toString());
		state_door2.setText("State: "+state.toString());
		state_door3.setText("State: "+state.toString());

    }

	public static void refreshGear(GearModel.states state) throws IOException {
		//TODO: Implement function
		String path = "";

		if(state == GearModel.states.movingUp || state == GearModel.states.movingDown )
		{
			path = "./images/gear_moving.jpg";
			lights.setIcon(new ImageIcon(ImageIO.read(new File("./images/orange.jpg"))));
		}
		else if(state == GearModel.states.down)
		{
			path = "./images/gear_down.PNG";
			lights.setIcon(new ImageIcon(ImageIO.read(new File("./images/green.jpg"))));
		}
		else if(state == GearModel.states.up)
		{
			path = "./images/gear_retracted.jpg";
			lights.setIcon(new ImageIcon(ImageIO.read(new File("./images/feu_vide.jpg"))));
		}

		File file = new File(path);
		BufferedImage icon = ImageIO.read(file);

		pic_gear1.setIcon(new ImageIcon(icon));
		pic_gear2.setIcon(new ImageIcon(icon));
		pic_gear3.setIcon(new ImageIcon(icon));

		state_gear1.setText("State: "+state.toString());
		state_gear2.setText("State: "+state.toString());
		state_gear3.setText("State: "+state.toString());

	}
}
