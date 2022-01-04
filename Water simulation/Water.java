import java.util.concurrent.atomic.AtomicInteger;

public class Water{

    private AtomicInteger id;
    private int depth = 0;
    private float height = 0.00f;
    private float surface;// = (depth/100) + height;

    public Water(int d, float h){
        this.depth = d; this.height = h; 
        id = new AtomicInteger();
        surface = (this.depth/100) + this.height;
            
    }
    
    public void incrId(int i){
        id.getAndAdd(i);
    }
    
    public int getId(){
        return id.get();
    }
    
    public synchronized int getDepth(){
        return depth;
    }
    
    public synchronized float getSurface(){
        return surface;
    }
     
    public synchronized void incrDepth(){
        depth++;
    }
    
    public synchronized void decrDepth(){
        depth--;
    }
    
    public String toString(){
        return String.valueOf(depth)+ " "+String.valueOf(surface);
    }
        
    public void transferWater( Water w){
        if ((this.id).get() <w.getId())
            synchronized(this){
            synchronized(w){
                this.decrDepth();
                w.incrDepth();
            }}
        else{
            synchronized(w){
            synchronized(this){
                this.decrDepth();
                w.decrDepth();
            }}}
    }
        

}

