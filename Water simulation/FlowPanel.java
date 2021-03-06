import java.awt.event.MouseAdapter;//me
import java.awt.event.MouseEvent;//me
import java.awt.Color; //me for water
import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.Arrays; 

public class FlowPanel extends JPanel implements Runnable {
	Terrain land;
	Boolean boo = true;
	int x =0;
	int y =0;
	
	Water[][] w;//water grid to be traversed
	int[] index; //permuted 2d location
		
	FlowPanel(Terrain terrain, int x, int y) {
		land=terrain;
		this.x=x;
		this.y=y;
		this.w = land.getWaterArr();
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
		index = new int[2];
		while(boo==true){
		for(int i=0; i<land.getSize();i++){
			land.getPermute(i,index);
			if (w[index[0]][index[1]].getDepth()>0){
				if(w[index[0]][index[1]].getSurface()<=w[index[0]-1][index[1]].getSurface() || w[index[0]][index[1]].getSurface()<=w[index[0]+1][index[1]].getSurface() ||w[index[0]][index[1]].getSurface()<=w[index[0]][index[1]-1].getSurface() ||w[index[0]][index[1]].getSurface()<=w[index[0]][index[1]+1].getSurface() ||w[index[0]][index[1]].getSurface()<=w[index[0]-1][index[1]-1].getSurface() ||w[index[0]][index[1]].getSurface()<=w[index[0]-1][index[1]+1].getSurface() ||w[index[0]][index[1]].getSurface()<=w[index[0]+1][index[1]-1].getSurface() ||w[index[0]][index[1]].getSurface()<=w[index[0]+1][index[1]+1].getSurface()){
					float neighbours[] = new float[8];
					neighbours[0]=w[index[0]-1][index[1]].getSurface(); neighbours[1]=w[index[0]+1][index[1]].getSurface();neighbours[2]=w[index[0]][index[1]-1].getSurface();neighbours[3]=w[index[0]][index[1]+1].getSurface();neighbours[4]=w[index[0]-1][index[1]-1].getSurface();neighbours[5]=w[index[0]-1][index[1]+1].getSurface();neighbours[6]=w[index[0]+1][index[1]-1].getSurface();neighbours[7]=w[index[0]+1][index[1]+1].getSurface();
					Arrays.sort(neighbours);
				        
				        if(neighbours[0]==w[index[0]-1][index[1]].getSurface()){
				        	w[index[0]][index[1]].transferWater(w[index[0]-1][index[1]]);
				        	
					}
					else if(neighbours[0]==w[index[0]+1][index[1]].getSurface()){
				        	w[index[0]][index[1]].transferWater(w[index[0]+1][index[1]]);
					}
					else if(neighbours[0]==w[index[0]][index[1]-1].getSurface()){
				        	w[index[0]][index[1]].transferWater(w[index[0]][index[1]-1]);
					}
					else if(neighbours[0]==w[index[0]][index[1]+1].getSurface()){
				        	w[index[0]][index[1]].transferWater(w[index[0]][index[1]+1]);
					}
					else if(neighbours[0]==w[index[0]-1][index[1]-1].getSurface()){
				        	w[index[0]][index[1]].transferWater(w[index[0]-1][index[1]-1]);
					}
					else if(neighbours[0]==w[index[0]-1][index[1]+1].getSurface()){
				        	w[index[0]][index[1]].transferWater(w[index[0]-1][index[1]+1]);
					}
					else if(neighbours[0]==w[index[0]+1][index[1]-1].getSurface()){
				        	w[index[0]][index[1]].transferWater(w[index[0]+1][index[1]-1]);
					}
					else if(neighbours[0]==w[index[0]-1][index[1]+1].getSurface()){
				        	w[index[0]][index[1]].transferWater(w[index[0]-1][index[1]+1]);
					}
					
				}}
		}
		
		for(int l =0; l<land.getSize();l++){
			land.getPermute(l,index);
			if(w[index[0]][index[1]].getDepth()>0){
				Color col = new Color(64,224,208);
				(land.getImage()).setRGB(index[0],index[1],col.getRGB());
				repaint();
			}}}
			
	    repaint();
	}
}