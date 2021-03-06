import javax.swing.*;

import java.awt.Color;
import java.awt.event.MouseAdapter;//me
import java.awt.event.MouseEvent;//me

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

public class Flow {
	static long startTime = 0;
	static int frameX;
	static int frameY;
	static FlowPanel fp;

	// start timer
	private static void tick(){
		startTime = System.currentTimeMillis();
	}
	
	// stop timer, return time elapsed in seconds
	private static float tock(){
		return (System.currentTimeMillis() - startTime) / 1000.0f; 
	}
	
	public static void setupGUI(int frameX,int frameY,Terrain landdata) {
		
		Dimension fsize = new Dimension(800, 800);
    	JFrame frame = new JFrame("Waterflow"); 
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.getContentPane().setLayout(new BorderLayout());
    	
      	JPanel g = new JPanel();
        g.setLayout(new BoxLayout(g, BoxLayout.PAGE_AXIS)); 
   
		fp = new FlowPanel(landdata,0,0);
		fp.setPreferredSize(new Dimension(frameX,frameY));
		g.add(fp);
	    
		// to do: add a MouseListener, buttons and ActionListeners on those buttons
	   	fp.addMouseListener(new MouseAdapter() {
	   		@Override
	   		public void mousePressed(MouseEvent e) {
           	 		fp.makeSquare(e.getX(),e.getY());
           	 		(landdata.getWater(e.getX(), e.getY())).incrDepth();//increments water on Terrain           	 		
           	 	fp.repaint();
           		}
		});
	   	
		//////
		JButton resetB = new JButton("Reset");;
		JButton pauseB = new JButton("Pause");
		JButton playB = new JButton("Play");
		
		JPanel b = new JPanel();
	    b.setLayout(new BoxLayout(b, BoxLayout.LINE_AXIS));
		JButton endB = new JButton("End");;
		// add the listener to the jbutton to handle the "pressed" event
		endB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// to do ask threads to stop
				frame.dispose();
			}
		});
		
		b.add(resetB); b.add(pauseB); b.add(playB); b.add(endB);
		g.add(b);
    	
		frame.setSize(frameX, frameY+50);	// a little extra space at the bottom for buttons
      	frame.setLocationRelativeTo(null);  // center window on screen
      	frame.add(g); //add contents to window
        frame.setContentPane(g);
        frame.setVisible(true);
        Thread fpt = new Thread(fp);
        fpt.start();
	}
	
		
	public static void main(String[] args) {
		Terrain landdata = new Terrain();
		
		// check that number of command line arguments is correct
		if(args.length != 1)
		{
			System.out.println("Incorrect number of command line arguments. Should have form: java -jar flow.java intputfilename");
			System.exit(0);
		}
				
		// landscape information from file supplied as argument
		// 
		landdata.readData(args[0]);
		
		frameX = landdata.getDimX();
		frameY = landdata.getDimY();
		SwingUtilities.invokeLater(()->setupGUI(frameX, frameY, landdata));
		
		// to do: initialise and start simulation
		//Color col = new Color(64,224,208);
		//(landdata.getImage()).setRGB(40,40,col.getRGB());
		Thread thr = new Thread(fp);
		//Thread thr2=new Thread(fp);
		//Thread thr3=new Thread(fp);
		//Thread thr4 = new Thread(fp);
		thr.start();
		//thr2.start();
		//thr3.start();
		//thr4.start();
		
			
	}
}
