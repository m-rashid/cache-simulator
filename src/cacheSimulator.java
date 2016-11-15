/**
 *
 * The class containing a main method that handles the simulation
 * 
 */
import java.io.FileNotFoundException;
import java.util.Scanner;

public class cacheSimulator {

    public static void main(String[] args) throws FileNotFoundException{
        
        Scanner sc = new Scanner(System.in);
        String[] conf = null;
        cache L1 = null;
        cache L2 = null;
        int indexL1, indexL2, L1Blocks, L2Blocks, L1BlockSize, L2BlockSize;
        System.out.println("Select the simulator cache level:\n1. L1\n2. L2\nEnter 0 to Quit");
        int cacheLevel = sc.nextInt();
        
        while(cacheLevel!=0){
            
            //L1 cache
            if(cacheLevel==1){ 
                System.out.println("Enter the L1 configurations in the format: <Number of blocks> <Block size>");
                L1Blocks = Integer.parseInt(sc.next());
                L1BlockSize = Integer.parseInt(sc.next());
                L1 = new cache(L1Blocks, L1BlockSize); //create the L1 cache
                L1.read("files/"+args[0]);             //read the addresses from the file into main memory
                for(int i = 0; i<L1.addressBlocks.size(); i++){

                    indexL1 = L1.getAddrIndex(i);
                       
                    //if the tag of the block in cache is the same as the tag of the block in the same index in main memory
                    if(L1.cacheBlocks[indexL1]!=null && L1.cacheBlocks[indexL1].getTag().equals(L1.getAddrTag(i))){

                        L1.incrHits();
                        continue;
                    }
                    
                    //if block is not found in cache, create a new one at that index
                    else{
                        L1.cacheBlocks[indexL1] = (block)L1.addressBlocks.get(i);
                        L1.incrMisses();
                    }

                }
                
                //|------------------------RESULTS-------------------------------|
                int cycles = (L1.getHits()*10) + (L1.getMisses()*1000);
                System.out.println("|----------L1cache "+args[0]+" "+L1.blocksNo+"blocks"+" "+L1.blockSize+"bytes/block-----------|");
                System.out.println("L1 hits: "+L1.getHits());
                System.out.println("L1 misses: "+L1.getMisses());
                System.out.println("Cycles: "+cycles);
                System.out.println("CPI: "+((cycles/L1.counter)+1));
                System.out.println("|---------------------------------------------------------------------|");
                System.out.println(" ");

            }


            //L2 cache
            else if(cacheLevel==2){
                System.out.println("Enter the multi-level cache configurations in the format: <L1 no. of blocks> <L1 block size> <L2 no. of blocks> <L2block size>");
                L1Blocks = Integer.parseInt(sc.next());
                L1BlockSize = Integer.parseInt(sc.next());
                L2Blocks = Integer.parseInt(sc.next());
                L2BlockSize = Integer.parseInt(sc.next());
                L1 = new cache(L1Blocks, L1BlockSize);
                L2 = new cache(L2Blocks, L2BlockSize);
                //read blocks into main memory. Here the same reference to main memory is made by both the caches
                L1.read("files/"+args[0]);  
                L2.read("files/"+args[0]);

                for(int i = 0; i<L1.addressBlocks.size(); i++){

                    indexL1 = L1.getAddrIndex(i);
                    indexL2 = L2.getAddrIndex(i);

                    //if the tag of the block in L1 cache is the same as the tag of the block in the same indes in main memory
                    if(L1.cacheBlocks[indexL1]!=null && L1.cacheBlocks[indexL1].getTag().equals(L1.getAddrTag(i))){

                        L1.incrHits();
                        continue;
                    }
                    
                    //if block is not found in L1, check in L2
                    else if(L2.cacheBlocks[indexL2]!=null && L2.cacheBlocks[indexL2].getTag().equals(L2.getAddrTag(i))){

                        L2.incrHits();
                        L1.incrMisses();
                        L1.cacheBlocks[indexL1] = (block)L1.addressBlocks.get(i);
                        continue;
                    }
                    
                    //if block is not found in both L1 and L2, create/replace the block in both the caches
                    else{
                        L1.cacheBlocks[indexL1] = (block)L1.addressBlocks.get(i);
                        L2.cacheBlocks[indexL2] = (block)L2.addressBlocks.get(i);
                        L1.incrMisses();
                        L2.incrMisses();
                    }


                }
                
                //|------------------------RESULTS-------------------------------|
                int cycles = ((L1.getHits()*10) + (L2.getHits()*100) + (L2.getMisses()*1000));
                System.out.println("|----------L1cache "+args[0]+" "+L1.blocksNo+"blocks"+" "+L1.blockSize+"bytes/block-----------|");
                System.out.println("|----------L2cache "+args[0]+" "+L2.blocksNo+"blocks"+" "+L2.blockSize+"bytes/block-----------|");
                System.out.println("L1 hits: "+L1.getHits());
                System.out.println("L1 misses: "+L1.getMisses());
                System.out.println("L2 hits: "+L2.getHits());
                System.out.println("L2 misses: "+L2.getMisses());
                System.out.println("Cycles: "+cycles);
                System.out.println("CPI: "+((cycles/L1.counter)+1));
                System.out.println("|---------------------------------------------------------------------|");
                System.out.println(" ");

                }


                else{
                    System.out.println("You have selected the wrong option");
                    System.exit(0);
                }
        System.out.println("Select the simulator cache level:\n1. L1\n2. L2\nEnter 0 to Quit");
        cacheLevel = sc.nextInt();
        }    
        
        
    }
    
}
