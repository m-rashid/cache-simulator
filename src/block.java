/**
 *
 * This class represents a block in main memory or cache
 * 
 */
public class block {
    
    private String tag;
    private String index;
    private int valid = 0;         //Valid bit. Changed to 1 when a block is created
    private int[] data = null; //data contained in the block
    
    public block(String index, String tag, int[] data){
        this.tag = tag;
        this.index = index;
        valid = 1;
        this.data = data;
    }
    
    
    public String getTag(){
        return this.tag;
    }
    
    public String getIndex(){
        return this.index;
    }
}
