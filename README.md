==================================================================================================================
                                           CACHE SIMULATOR
==================================================================================================================

Getting Started
---------------

Instructions for running the appliation
1. Navigate to the project folder via the terminal
2. Run 'make all'
3. Navigate to \bin
4. Enter the following command line arguments to run the simulation:  java cacheSimulator <address_file_name>
	 <address_file_name> can be chosen from one of the following pre-loaded files:
 	 assignment.addr, fourier.addr, heapsort.addr, matmul5x5_loop, small_L2_set.addr, small_set.addr
	 You can place your own address file into the bin/files
5. Follow the on-screen instructions: Enter 1 to run a L1 siumulation, or 2 to run a L2 simulation
6. Vary the parameters as indicated.
	 For a L1 cache, you must enter two paramers: <Number of blocks> <Block size>
	 For a L2 cache, you must enter four paramers: <L1 no. of blocks> <L1 block size> <L2 no. of blocks> <L2block size>

Tests
-----

The predefined set of results using the small_set.addr and small_L2_set.addr files were compared with this
simulator to check its accuracy. The outputs are shown below.

|----------L1cache small_set.addr 16blocks 32bytes/block-----------|
L1 hits: 5
L1 misses: 4
Cycles: 4050
CPI: 451
|---------------------------------------------------------------------|


|----------L1cache small_L2_set.addr 16blocks 16bytes/block-----------|
|----------L2cache small_L2_set.addr 64blocks 64bytes/block-----------|
L1 hits: 2
L1 misses: 6
L2 hits: 3
L2 misses: 3
Cycles: 3320
CPI: 416
|---------------------------------------------------------------------|

The results produced by running the two address files with configurations shown in the output above
are consistent with the ones given in the assignment question.
Hence, it can be stated that the simulator works correctly.
