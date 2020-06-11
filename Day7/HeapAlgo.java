import java.util.ArrayList;

public class HeapAlgo {
    ArrayList<int[]> resultArray = new ArrayList<>();
    void printArr(int a[], int n)
    {
        resultArray.add(a);
        System.out.println(resultArray.size());
    }

    //Generating permutation using Heap Algorithm
    void heapPermutation(int a[], int size, int n)
    {
        // if size becomes 1 then prints the obtained
        // permutation
        if (size == 1)
            printArr(a,n);

        for (int i=0; i<size; i++)
        {
            heapPermutation(a, size-1, n);

            // if size is odd, swap first and last
            // element
            if (size % 2 == 1)
            {
                int temp = a[0];
                a[0] = a[size-1];
                a[size-1] = temp;
            }

            // If size is even, swap ith and last
            // element
            else
            {
                int temp = a[i];
                a[i] = a[size-1];
                a[size-1] = temp;
            }
        }
    }
}
