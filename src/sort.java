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
    static int MAXINPUTSIZE  = (int) Math.pow(2,11);
    static int MININPUTSIZE  =  1;

    public static void main(String[] args) {


    // test before hand
    test();
    checkSortCorrectness();
    // run the whole experiment at least twice, and expect to throw away the data from the earlier runs, before java has fully optimized
    //runFullExperiment("thre-Exp1-ThrowAway.txt");
    //runFullExperiment("thre-Exp2.txt");
    //runFullExperiment("three-Exp3.txt");

}






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
        int mid = 0;
        if ( lo < hi) {

            mid = (lo + hi) / 2;

            merge_sort_work(list, lo, mid);
            System.out.println("test1");
            merge_sort_work(list, mid + 1, hi);
            System.out.println("test2");
            merge(list, lo, mid, hi);
        }

    }

    public static void merge(int list[], int lo,int mid,int hi)
    {
        int length_a = mid - lo + 1;
        int length_b = hi - mid;
        int iA = 0;
        int iB = 0;
        int iM = 0;

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
        while(iA < length_a && iB < length_b) {
            if (low_list[iA] < hi_list[iB]) {
                list[iM] = low_list[iA];
                iA++;

            } else {
                list[iM] = low_list[iB];
                iB++;
            }
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
            //System.out.println(lo + " lo" );
            // System.out.println(hi + " hi" );
        if(lo >= hi)
        {     // System.out.println("return");
            return ;
        }
        pivot = list[lo];
        pivot_index = lo;
        swap(list, pivot_index,lo);
        //System.out.println("test");
        pivot_index = lo;
        pivot = list[lo];
            //  System.out.println(Arrays.toString(list ) );
                // System.out.println(pivot_index);
        next_hi = hi;
        //System.out.println(next_hi);

        while(pivot_index < next_hi)
        {
                if(list[pivot_index+1] <= list[pivot_index])
                {
                    swap(list,pivot_index+1 , pivot_index);
                    pivot_index++;
                     //System.out.println(Arrays.toString(list ) );
                       //   System.out.println(pivot_index);
                                //  System.out.println("boom 1");

                }
                else
                {

                    swap(list,pivot_index+1 , next_hi);
                    next_hi--;
                     //System.out.println(Arrays.toString(list ) );
                                       // System.out.println(next_hi);
                                         //    System.out.println("boom2");
                }

        }
        //System.out.println("exit loop");

        quick_sort_work(list, lo, pivot_index-1);
           // System.out.println("second");
        quick_sort_work(list, next_hi+1, hi);


    }

    public static void smart_quick_sort(int[] list)
    {

        quick_sort_work(list, 0 , list.length-1 );

    }


     public static void smart_quick_sort_work(int[] list, int lo, int hi)
     {
         Random sort_ran = new Random();
         int pivot;
         int pivot_index;
         int next_hi;
         //System.out.println(lo + " lo" );
         // System.out.println(hi + " hi" );
         if(lo >= hi)
         {     // System.out.println("return");
             return ;
         }
         pivot = list[lo];

         pivot_index = sort_ran.nextInt(hi - lo) + lo;
         swap(list, pivot_index,lo);
         //System.out.println("test");
         pivot_index = lo;
         pivot = list[lo];
         //  System.out.println(Arrays.toString(list ) );
         // System.out.println(pivot_index);
         next_hi = hi;
         //System.out.println(next_hi);

         while(pivot_index < next_hi)
         {
             if(list[pivot_index+1] <= list[pivot_index])
             {
                 swap(list,pivot_index+1 , pivot_index);
                 pivot_index++;
                 //System.out.println(Arrays.toString(list ) );
                 //   System.out.println(pivot_index);
                 //  System.out.println("boom 1");

             }
             else
             {

                 swap(list,pivot_index+1 , next_hi);
                 next_hi--;
                 //System.out.println(Arrays.toString(list ) );
                 // System.out.println(next_hi);
                 //    System.out.println("boom2");
             }

         }
         //System.out.println("exit loop");

         smart_quick_sort_work(list, lo, pivot_index-1);
         // System.out.println("second");
         smart_quick_sort_work(list, next_hi+1, hi);


     }









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
        //testing array currently modded for the 3sum
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
        int[] testList1 = createRandomListOfIntegers(10) ;
        int[] testList2 = createRandomListOfIntegers(10) ;
        int[] testList3 = createRandomListOfIntegers(10) ;
        int[] testList4 = createRandomListOfIntegers(10) ;

        System.out.println(Arrays.toString(testList3) );
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


        System.out.println(Arrays.toString(testList3) );
        insertion_sort(testList2);
        System.out.println(Arrays.toString(testList2 ) );



        System.out.println(Arrays.toString(testList3) );
        quick_sort(testList3);
        System.out.println(Arrays.toString(testList3) );

        System.out.println(Arrays.toString(testList4) );
        smart_quick_sort(testList3);
        System.out.println(Arrays.toString(testList4) );




        System.out.println("Found   amount ");



    }

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
