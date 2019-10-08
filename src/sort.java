import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.io.*;

public class sort
{

public static void main(String[] args) {


    // test before hand
    test();
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
        int[] testArray3 = new int []{ 11,99,22,33,66,77,44,88,55,0};


        long sum = 0;
        long cnt;
        //cnt = count (sum, intArray  );
        bubble_sort(testArray1) ;
            System.out.println(Arrays.toString( (testArray1)) );

        insertion_sort(testArray2);
          System.out.println(Arrays.toString(testArray2 ) );

       quick_sort(testArray3);
            System.out.println(Arrays.toString(testArray3 ) );


        System.out.println("Found   amount ");
    }

}
