import java.awt.event.MouseAdapter;//me
import java.awt.event.MouseEvent;//me
import java.awt.Color; //me for water
import java.awt.Graphics;
import javax.swing.JPanel;

public class FlowPanel extends JPanel implements Runnable {
	Terrain land;
	Boolean boo = true; //if true; mouse draws square(incomplete)
	int x =0;
	int y =0;
	
	Water[][] waterGrid = new Water[land.getDimX()][land.getDimY()];
		
	FlowPanel(Terrain terrain, int x, int y) {
		land=terrain;
		this.x=x;
		this.y=y;
		waterGrid = land.getWaterArr();
	}
		
	// responsible for painting the terrain and water
	// as images
	@Override
    protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		  
		super.paintComponent(g);
		
		// draw the landscape in greyscale as an image
		if (land.getImage() != null){
			g.drawImage(land.getImage(), 0, 0, null);
		
		}
		
		// for changing Terrain block to blue if mouse is pressed
		if (boo==true){
			if(x==0 || y==0)
			{}
			else{
				g.setColor(Color.BLUE);
				g.fillRect(x,y, 10,10);
			}
		}
	}
	
	public void makeSquare(int x, int y){ //for block of water
		this.x=x;
		this.y=y;
		boo = true;
	}
	
	public void run() {
		// display loop here
		// to do: this should be controlled by the GUI
		// to allow stopping and starting
		int[] index;
		for(int i=0; i<land.getPermuteLength(); i++){
			land.getPermute(i,index);
			if(waterGrid[index[0]][index[1]]<waterGrid[index[0]-1][index[1]] || waterGrid[index[0]][index[1]]<waterGrid[index[0]+1][index[1]] ||waterGrid[index[0]][index[1]]<waterGrid[index[0]][index[1]-1] ||waterGrid[index[0]][index[1]]<waterGrid[index[0]][index[1]+1] ||waterGrid[index[0]][index[1]]<waterGrid[index[0]-1][index[1]-1] ||waterGrid[index[0]][index[1]]<waterGrid[index[0]-1][index[1]+1] ||waterGrid[index[0]][index[1]]<waterGrid[index[0]+1][index[1]-1] ||waterGrid[index[0]][index[1]]<waterGrid[index[0]+1][index[1]+1]) {
			}
			
		}
		
		
	    repaint();
	}
}