import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.Random;
import java.io.*;

public class sort
{
    static long MAXV =  2000000000;
    static long MINV = -2000000000;
    static int numberOfTrials = 100;
    static int MAXINPUTSIZE  = (int) Math.pow(2,14);
    static int MININPUTSIZE  =  1;


    static ThreadMXBean bean = ManagementFactory.getThreadMXBean( );

    static String ResultsFolderPath = "/home/cody/Results/"; // pathname to results folder
    static FileWriter resultsFile;
    static PrintWriter resultsWriter;

    public static void main(String[] args) {


    // test before hand
   // test();
    checkSortCorrectness();
    // run the whole experiment at least twice, and expect to throw away the data from the earlier runs, before java has fully optimized
    //runFullExperiment("bubble-Exp1-ThrowAway.txt");
    //runFullExperiment("bubble-Exp2.txt");
   // runFullExperiment("bubble-Exp3.txt");

    /*runFullExperiment("insert-Exp1-ThrowAway.txt");
    runFullExperiment("insert-Exp2.txt");
    runFullExperiment("insert-Exp3.txt");*/

    //runFullExperiment("quicknaive-Exp1-ThrowAway.txt");
    //runFullExperiment("quicknaive-Exp2.txt");
    //runFullExperiment("quicknaive-Exp3.txt");

    /*runFullExperiment("quick-Exp1-ThrowAway.txt");
    runFullExperiment("quick-Exp2.txt");
    runFullExperiment("quick-Exp3.txt");*/

    //runFullExperiment("merge-Exp1-ThrowAway.txt");
    //runFullExperiment("merge-Exp2.txt");
    //runFullExperiment("merge-Exp3.txt");

}

    static void runFullExperiment(String resultsFileName){



        try {

            resultsFile = new FileWriter(ResultsFolderPath + resultsFileName);
            resultsWriter = new PrintWriter(resultsFile);

        } catch(Exception e) {

            System.out.println("*****!!!!!  Had a problem opening the results file "+ResultsFolderPath+resultsFileName);
            return; // not very foolproof... but we do expect to be able to create/open the file...

        }



        ThreadCpuStopWatch BatchStopwatch = new ThreadCpuStopWatch(); // for timing an entire set of trials
        ThreadCpuStopWatch TrialStopwatch = new ThreadCpuStopWatch(); // for timing an individual trial


        resultsWriter.println("#InputSize    AverageTime"); // # marks a comment in gnuplot data
        resultsWriter.flush();
        /* for each size of input we want to test: in this case starting small and doubling the size each time */
        for(int inputSize=MININPUTSIZE;inputSize<=MAXINPUTSIZE; inputSize*=2) {
            // progress message...
            System.out.println("Running test for input size "+inputSize+" ... ");
            double prevTimePerTrial = 0;
            int[] testList = createRandomListOfIntegers(inputSize);
            /* repeat for desired number of trials (for a specific size of input)... */
            long batchElapsedTime = 0;
            // generate a list of randomly spaced integers in ascending sorted order to use as test input
            // In this case we're generating one list to use for the entire set of trials (of a given input size)
            // but we will randomly generate the search key for each trial
            System.out.print("    Generating test data...");



            System.out.println("...done.");
            System.out.print("    Running trial batch...");


            /* force garbage collection before each batch of trials run so it is not included in the time */
            System.gc();




            // instead of timing each individual trial, we will time the entire set of trials (for a given input size)
            // and divide by the number of trials -- this reduces the impact of the amount of time it takes to call the
            // stopwatch methods themselves
            BatchStopwatch.start(); // comment this line if timing trials individually


            // run the tirals
            for (long trial = 0; trial < numberOfTrials; trial++)
            {

                // generate a random key to search in the range of a the min/max numbers in the list
                // long testSearchKey = (long) (0 + Math.random() * (testList[testList.length-1]));
                /* force garbage collection before each trial run so it is not included in the time */
                // System.gc();


                //TrialStopwatch.start(); // *** uncomment this line if timing trials individually
                /* run the function we're testing on the trial input */
                //long foundIndex = count(0, testList);
                //long counted = fastestthree(testList);
                // batchElapsedTime = batchElapsedTime + TrialStopwatch.elapsedTime(); // *** uncomment this line if timing trials individually

                // sort list tested one by one in full runtime
                //quick_sort(testList);
                //smart_quick_sort(testList);
                //insertion_sort(testList);
                //merge_sort(testList);
                //bubble_sort(testList);




            }

            batchElapsedTime = BatchStopwatch.elapsedTime(); // *** comment this line if timing trials individually
            double averageTimePerTrialInBatch = (double) batchElapsedTime / (double)numberOfTrials; // calculate the average time per trial in this batch

            // tying to get doubling ratio to work.
            //double doublingRatio = (double) averageTimePerTrialInBatch / (double) prevTimePerTrial;
           // prevTimePerTrial = averageTimePerTrialInBatch;


            /* print data for this size of input */
            resultsWriter.printf("%12d  %15.2f   \n",inputSize, averageTimePerTrialInBatch ); // might as well make the columns look nice
            resultsWriter.flush();
            System.out.println(" ....done.");

        }

    }



// bubble sort from previous lab
    public static void bubble_sort(int[] list)
    {
        for(int i = 0; i < list.length ; i++)
        {
            for(int j = 0; j < list.length - 1; j++)
            {
                if (list[j] > list[j+1])
                {
                    int tmp = list[j];
                    list[j] = list[j+1];
                    list[j+1] = tmp;
                }
            }
        }
    }


    //simple insertion sort
    public static void insertion_sort(int[] list)
    {
        int temp = 0;
        int j = 0;
        for(int i = 1; i < list.length; i++)
        {
            j = i;
            while( j > 0 && list[j-1] > list[j])
            {
                temp = list[j];
                list[j] = list[j-1];
                list[j-1] = temp;
                j = j -1;
            }
        }

    }

    public static void merge_sort(int[] list)
    {
        merge_sort_work(list, 0, list.length -1 );

    }
    public static void merge_sort_work(int[] list, int lo, int hi )
    {
        int mid;
        if ( lo < hi)
        {

            mid = (lo + hi) / 2;

            merge_sort_work(list, lo, mid);

            merge_sort_work(list, mid + 1, hi);

            merge(list, lo, mid, hi);
        }

    }


    //merge sourt help being made from geeksforgeeks
    public static void merge(int list[], int lo,int mid,int hi)
    {
        int length_a = mid - lo + 1;
        int length_b = hi - mid;
        int iA = 0;
        int iB = 0;
        int iM = lo;

        int low_list[] = new int [length_a];
        int hi_list[] = new int [length_b];


        for(int i = 0; i < length_a; i++)
        {
            low_list[i] = list[lo + i];
        }
        for(int j = 0; j < length_b; j++)
        {
            hi_list[j] = list[mid + 1 + j];
        }

        //System.out.println(Arrays.toString(list ) );

        while(iA < length_a && iB < length_b)
        {
            if (low_list[iA] <= hi_list[iB] )
            {
                list[iM] = low_list[iA];
                iA++;

            }
            else
            {
                list[iM] = hi_list[iB];
                iB++;
            }
            iM++;


        }
        while(iA < length_a)
        {
            list[iM] = low_list[iA];
            iA++;
            iM++;


        }
        while(iB < length_b)
        {
            list[iM] = hi_list[iB];
            iB++;
            iM++;


        }

    }


    public static void quick_sort(int[] list)
    {

        quick_sort_work(list, 0 , list.length-1 );

    }

    public static void quick_sort_work(int[] list, int lo, int hi)
    {
        int pivot;
        int pivot_index;
        int next_hi;
        // precaution if statement
        if(lo >= hi)
        {
            return ;
        }

        // getting the pivot point from the front of the arrays
        pivot = list[lo];
        pivot_index = lo;
        swap(list, pivot_index,lo);
        //System.out.println("test");
        pivot_index = lo;
        pivot = list[lo];

        next_hi = hi;

        //switching in the array
        while(pivot_index < next_hi)
        {
                if(list[pivot_index+1] <= list[pivot_index])
                {
                    swap(list,pivot_index+1 , pivot_index);
                    pivot_index++;


                }
                else
                {

                    swap(list,pivot_index+1 , next_hi);
                    next_hi--;
                     ;
                }

        }

        // using the pivot point to make more sorting
        quick_sort_work(list, lo, pivot_index-1);

        quick_sort_work(list, next_hi+1, hi);


    }
    //wrapper for smart quick sort
    public static void smart_quick_sort(int[] list)
    {

        smart_quick_sort_work(list, 0 , list.length-1 );

    }


     public static void smart_quick_sort_work(int[] list, int lo, int hi)
     {
         // setting up the code
         Random sort_ran = new Random();
         int pivot;
         int pivot_index;
         int next_hi;
         if(lo >= hi)
         {
             return ;
         }
         pivot = list[lo];
         //random finder in array found with quick google search
         pivot_index = sort_ran.nextInt(hi - lo) + lo;
         swap(list, pivot_index,lo);
         pivot_index = lo;
         pivot = list[lo];
         next_hi = hi;
         //using the pivit index to go through the list and then swapping
         while(pivot_index < next_hi)
         {
             if(list[pivot_index+1] <= list[pivot_index])
             {
                 swap(list,pivot_index+1 , pivot_index);
                 pivot_index++;

             }
             else
             {

                 swap(list,pivot_index+1 , next_hi);
                 next_hi--;

             }

         }

        //going to where the pivot is
         smart_quick_sort_work(list, lo, pivot_index-1);
         smart_quick_sort_work(list, next_hi+1, hi);


     }








// swap function written as a shortcut in both quicksorts
    private static void swap(int list[],int i, int j)
    {
        int temp = 0;
               // System.out.println(i +" "+j);
        temp = list[j];
        list[j] = list[i];
        list[i] = temp;

    }


    public static void test()
    {
        //testing arrays used in program
        int[] intArray = new int[]{ 1,2,3,-3,4,6,5,7,-5,8,10,9};
        int[] testArray1 = new int []{ 11,99,22,33,66,77,44,88,55,0};
        int[] testArray2 = new int []{ 11,99,22,33,66,77,44,88,55,0};
        int[] testArray3 = new int []{ 11,99,22,33,66,77,44,88,55,0,102, -8};


        long sum = 0;
        int check_sorted;
        //cnt = count (sum, intArray  );
        bubble_sort(testArray1) ;
            System.out.println(Arrays.toString( (testArray1)) );
        if (verifysorted(testArray1) == 1 )
        {
            System.out.println("sorted");
        }
        else
        {
            System.out.println("Not sorted");
        }

        check_sorted = verifysorted(intArray);
        if (verifysorted(intArray) == 1 )
        {
            System.out.println("sorted");
        }
        else
        {
            System.out.println("Not sorted");
        }


        insertion_sort(testArray2);
          System.out.println(Arrays.toString(testArray2 ) );

       merge_sort(testArray3);
            System.out.println(Arrays.toString(testArray3 ) );


        System.out.println("Found   amount ");
    }

    public static int verifysorted(int list[])
    {
        int a;
        // looks through list length -2 because based zero so length would be one over and need to stop before end.
        for(a = 0; a < list.length-2; a++)
        {
            if(list[a] > list[a+1])
            {
                return 0;
            }
        }
        return 1;
    }


    public static void checkSortCorrectness()
    {
        // list of random integers to test
        int[] testList1 = createRandomListOfIntegers(10) ;
        int[] testList2 = createRandomListOfIntegers(10) ;
        int[] testList3 = createRandomListOfIntegers(10) ;
        int[] testList4 = createRandomListOfIntegers(10) ;
        int[] testList5 = createRandomListOfIntegers(10) ;

        // each sort gets it own sort list and verification
        System.out.println(Arrays.toString(testList1) );
        bubble_sort(testList1) ;
        System.out.println(Arrays.toString( (testList1)) );
        if (verifysorted(testList1) == 1 )
        {
            System.out.println("sorted");
        }
        else
        {
            System.out.println("Not sorted");
        }


        System.out.println(Arrays.toString(testList2) );
        insertion_sort(testList2);
        System.out.println(Arrays.toString(testList2 ) );
        if (verifysorted(testList2) == 1 )
        {
            System.out.println("sorted");
        }
        else
        {
            System.out.println("Not sorted");
        }



        System.out.println(Arrays.toString(testList3) );
        quick_sort(testList3);
        System.out.println(Arrays.toString(testList3) );
        if (verifysorted(testList3) == 1 )
        {
            System.out.println("sorted");
        }
        else
        {
            System.out.println("Not sorted");
        }

        System.out.println(Arrays.toString(testList4) );
        smart_quick_sort(testList4);
        System.out.println(Arrays.toString(testList4) );
        if (verifysorted(testList4) == 1 )
        {
            System.out.println("sorted");
        }
        else
        {
            System.out.println("Not sorted");
        }
        System.out.println(Arrays.toString(testList5) );
        merge_sort(testList5);
        System.out.println(Arrays.toString(testList5) );
        if (verifysorted(testList5) == 1 )
        {
            System.out.println("sorted");
        }
        else
        {
            System.out.println("Not sorted");
        }





        System.out.println("Found   amount ");



    }

    //Random integer list as usual
    public static int[] createRandomListOfIntegers(int size)
    {
        // random list generates used in many projects includes negative numbers as well
        int [] randomArray = new int[size];
        for(int j=0; j<size;j++)
        {
            randomArray[j] = (int)(Math.random() *(MAXV - MINV));
        }
        return randomArray;

    }
}
