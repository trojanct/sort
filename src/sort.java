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

        if(lo >= hi)
        {
            return;
        }
        pivot = list[lo];
        pivot_index = lo;
        swap(list, list[pivot_index],list[lo]);
        pivot = list[lo];
        pivot_index = lo;
        next_hi = hi;
        while(pivot_index < next_hi)
        {
                if(list[pivot_index+1] <= list[pivot_index])
                {
                    swap(list,list[pivot_index+1] , list[pivot_index]);
                    pivot_index++;
                }
                else
                {
                    swap(list,list[pivot_index+1] , list[next_hi]);
                    next_hi--;
                }

        }
        quick_sort_work(list, lo, pivot_index+1);
        quick_sort_work(list, next_hi+1, hi);


    }

    private static void swap(int list[],int i, int j)
    {
        int temp;
        temp = list[j];
        list[j] = list[i];
        list[i] = temp;

    }


    public static void test()
    {
        //testing array currently modded for the 3sum
        int[] intArray = new int[]{ 1,2,3,-3,4,6,5,7,-5,8,10,9};
        //int[] testArray1 = new int []{ 11,99,22,33,66,77,44,88,55,0};
        //int[] testArray2 = new int []{ 11,99,22,33,66,77,44,88,55,0};
        //int[] testArray3 = new int []{ 11,99,22,33,66,77,44,88,55,0};


        long sum = 0;
        long cnt;
        //cnt = count (sum, intArray  );
        //bubble_sort(intArray) ;
       //      System.out.println(Arrays.toString( intArray) );

        insertion_sort(intArray);
          System.out.println(Arrays.toString(intArray ) );

       // quick_sort(intArray);
          //   System.out.println(Arrays.toString(intArray ) );


        System.out.println("Found   amount ");
    }

}
