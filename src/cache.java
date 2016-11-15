/**
 *
 * This class represents a cache as a whole
 * Each cache has its own set of blocks determined by the tag and index bits
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class cache {
    
    int blocksNo;                   //number of blocks in cache i.e cache size
    int blockSize;                  //size of blocks in cache: bytes/block
    block[] cacheBlocks = null;     //blocks in cache
    ArrayList addressBlocks = null; //blocks in main memory
    int hits;                       //counter for incrementing number of hits
    int misses;                     //counter for incrementing number of misses
    int[] data;                     //the data to be stored in a block
    int counter = 0;                //counter for incrementing number of addresses
    
    public cache(int number, int size){
        //initializations
        this.blocksNo = number;
        this.blockSize = size;
        this.cacheBlocks = new block[blocksNo];
        this.addressBlocks = new ArrayList();
        this.hits = 0;
        this.misses = 0;
        this.data = new int[size];
    }
    
    //method to read addresses from the file into blocks in main memory
    public void read(String filename) throws FileNotFoundException{
        
        Scanner sc = new Scanner(new FileInputStream(filename));
        String tag, index;
        
        
        while(sc.hasNextLine()){
            
            int i = Integer.parseInt(sc.nextLine(), 16);
            
            //address = Tag bits | Index bits | Offset bits
            String binary = String.format("%16s", Integer.toBinaryString(i)).replace(' ', '0');          
            tag = binary.substring(0, getTagBits());
            index = binary.substring(getTagBits(), getIndexBits()+getTagBits());
            block b = new block (index, tag, data);
            addressBlocks.add(b);
            counter++;
        }
    }
    
    public int getAddrIndex(int i){
        
        return Integer.parseInt(""+((block)addressBlocks.get(i)).getIndex(),2);
    }
    
    public String getAddrTag(int i){
        return ""+((block)addressBlocks.get(i)).getTag();
    }
    
    public void incrHits(){
        this.hits++;
    }
    
    public void incrMisses(){
        this.misses++;
    }
    
    public int getBlockSize(){
      return this.blockSize;
    }
    
    public int getBlockNo(){
        return this.blocksNo;
    }
    
    //get the index bits by finding log_2 of the number of blocks
    public int getIndexBits(){
        
        return (int)(Math.log10(blocksNo)/Math.log10(2));
    }
    
    //get the offset bits by finding log_2 of the block size
    public int getOffsetBits(){
        return (int)(Math.log10(blockSize)/Math.log10(2));
    }
    
    //tag bits are the ones that remain
     public int getTagBits(){
        return 16-(getOffsetBits()+getIndexBits());    
    }
    
    public int getHits(){
        return this.hits;
    }
    
    public int getMisses(){
        return this.misses;
    }
   
    
    
}
