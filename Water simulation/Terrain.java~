import java.util.Locale;
import java.io.File;
import java.awt.image.*;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Terrain {

	float [][] height; // regular grid of height values
	Water [][] water;// water on terrain
	int dimx, dimy; // data dimensions
	BufferedImage img; // greyscale image for displaying the terrain top-down

	ArrayList<Integer> bounds;      //linear bounds of grid
	ArrayList<Integer> permute;	// permuted list of integers of internal grid excluding bounds
	int p =0; //Responsible for keeping each grid point's linear position
	
	int permutedLength =0;
	
	// overall number of elements in the height grid
	int dim(){
		return dimx*dimy;
	}
	
	// get x-dimensions (number of columns)
	int getDimX(){
		return dimx;
	}
	
	// get y-dimensions (number of rows)
	int getDimY(){
		return dimy;
	}
	
	// get greyscale image
	public BufferedImage getImage() {
		  return img;
	}
	
	// convert linear position into 2D location in grid
	void locate(int pos, int [] ind)
	{
		ind[0] = (int) pos / dimy; // x
		ind[1] = pos % dimy; // y	
	}
	
	// convert height values to greyscale colour and populate an image
	void deriveImage()
	{
		img = new BufferedImage(dimy, dimx, BufferedImage.TYPE_INT_ARGB);
		float maxh = -10000.0f, minh = 10000.0f;
		
		// determine range of heights
		for(int x=0; x < dimx; x++)
			for(int y=0; y < dimy; y++) {
				float h = height[x][y];
				if(h > maxh)
					maxh = h;
				if(h < minh)
					minh = h;
			}
		
		for(int x=0; x < dimx; x++)
			for(int y=0; y < dimy; y++) {
				 // find normalized height value in range
				 float val = (height[x][y] - minh) / (maxh - minh);
				 Color col = new Color(val, val, val, 1.0f);
				 img.setRGB(x, y, col.getRGB());
			}
	}
	
	// generate a permuted list of linear index positions to allow a random
	// traversal over the terrain
	void genPermute() {
		permute = new ArrayList<Integer>();
		bounds = new ArrayList<Integer>();
		permute = new ArrayList<Integer>();
			
		for(p=0; p<height[0].length; p++){
			bounds.add(p);
		}
		
		for(int r = 0;r < height.length-2; r++){
			p++;
			bounds.add(p);
			for(int col =1;col< height[0].length; col++){
				if(col==height[0].length-1){
					p++;
					bounds.add(p);
				}else{
					p++;
					permute.add(p);
				}}}
				
		for(int j=0; j<height[0].length;j++){
			p++;
			bounds.add(p);
		}
		
		java.util.Collections.shuffle(permute);
	}
	
	// find permuted 2D location from a linear index in the
	// range [0, dimx*dimy)
	void getPermute(int i, int [] loc) {

		locate(permute.get(i), loc);
	}
	
	// read in terrain from file
	void readData(String fileName){ 
		try{ 
			Scanner sc = new Scanner(new File(fileName));
			sc.useLocale(Locale.US);
			// read grid dimensions
			// x and y correpond to columns and rows, respectively.
			// Using image coordinate system where top left is (0, 0).
			dimy = sc.nextInt(); 
			dimx = sc.nextInt();
			
			// populate height grid plus creates water objects at every grid point
			height = new float[dimx][dimy];
			water= new Water[dimx][dimy]; //me
			float f=0.00f;
			int idNo = 0;
			for(int y = 0; y < dimy; y++){
				for(int x = 0; x < dimx; x++){	
					height[x][y] = sc.nextFloat();
					f = height[x][y];
					water[x][y]= new Water(0,f);
					water[x][y].incrId(idNo);
					idNo++;
				}
				}
			
			sc.close(); 
			
			// create randomly permuted list of indices for traversal 
			genPermute();
			
			//set Permuted length for traversal
			permutedLength = permute.size();  
			
			// generate greyscale heightfield image
			deriveImage();
		} 
		catch (IOException e){ 
			System.out.println("Unable to open input file "+fileName);
			e.printStackTrace();
		}
		catch (java.util.InputMismatchException e){ 
			System.out.println("Malformed input file "+fileName);
			e.printStackTrace();
		}
	}
	
	public Water getWater(int x, int y){
		return water[x][y];
	}
	
	public Water[][] getWaterArr(){
		return water;
	}
	
	public int getSize(){
		return permutedLength;
	}
	
	
	
}