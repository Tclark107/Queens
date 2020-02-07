//--------------------------------------------------------------------------------------------------------------------------
//
// Queens.java
//
// Finds a solution to the n Queens on an nxn board, by first finding a solution to the n Rooks problem.  
// We so that by finding all the spaces where each rook has its own row and column.  This is done by checking all
// permutions of the way n numbers can be organized in different spots in the array.  We then check if its a solutions
// by checking whether the any of the arrays are on the same diagonal.  And finally, printing the solutions.
//
//--------------------------------------------------------------------------------------------------------------------------
import java.lang.Math;

class Queens{

    int solnum = 0;

    // fact(n)
    // The directions specified that I couldn't use recursion to solve nextPermutation(A)
    // with recursion, however it didn't say anything about my helper functions.
    static int fact(int n){
        if(n > 1) {
            return n*fact(n - 1);
        } else {
            return n;
        }
    }

    // swap(Q, i, j)
    // helper function of reverse(T,start,end) that
    // swaps the elements at Q[i] and Q[j]
    static void swap(int[] Q, int i, int j){
        int temp = Q[i];
        Q[i] = Q[j];
        Q[j] = temp;
     }
    
    // reverse(T, start, end)
    // reverses the array at a indicated start and end point using the swap funcition
    static void reverse(int[] T, int start, int end){
        int i=start, j=end;
        while(i<j){
           swap(T, i, j);
           i++;
           j--;
        }
     }

    // nextPermutation(A)
    // finds the next permutation by appointing a pivot point and successor
    // then swapping the pivot and successor
    // and finally reversing the array at A[pivot+1]
    static void nextPermutation(int[] A){
        int n = A.length - 1;
        int pivot = 0;
        int successor = 0;

        // if A[i] is less than its right hand neighbor, that spot in the array is the pivot.
        for(int i = n-1; i > 0; --i){
            if(A[i] < A[i+1]){
                pivot = i;
                break;
            }
        }

        // if we get all the way to the right and no pivot was found, reverse the array and return.
        if(pivot == 0) {
            reverse(A,1,n);
            return;
        }

        // If the element in A[i] is > A[pivot], the index 'i' is the successor.
        for(int i = n; i > 0; --i){
            if(A[i] > A[pivot]){
                successor = i;
                break;
            }
        }
        swap(A, pivot, successor);
        reverse(A,pivot+1,n);
        return;
    }

    // printArray(P)
    // prints the array in a comma seperated line
    // example) (1, 2, 3, 4)\n
    static void printArray(int[] P){
        System.out.print("(");
        for(int i = 1; i < P.length - 1; ++i){
            System.out.print(P[i] + ", ");
        }
        System.out.println(P[P.length -1] + ")");
    }

    // isSolution(A)
    // It takes (n(n-1)/2) steps to complete this as it does not have to check
    // comparisons that have already been made.  It checks whether the 
    // indeces, i and j, representing the rows, and the elements in A[indeces], 
    // representing the columns, will be the same when subtracted.
    // if(absolutevalueof(A[i]-A[j]) == i - j).  If they are then they are on the 
    // same diagonal.
    static boolean isSolution(int[] A){
        int n = A.length - 1;
        for(int i = n; i > 1; --i){
            for(int j = n-1; j > 0; --j){
                if(j >= i) continue;
                if(Math.abs(A[i]-A[j]) == (i-j)){
                    return false;
                }
            }
        }
        return true;
    }

    // printErr()
    // Is a helper function that prints the error message and exits when the user enters the 
    // wrong thing at the command line.
    static void printErr(){
        System.err.println("Usage: Queens [-v] number");
        System.err.println("Option: -v  verbose output, print all solutions");
        System.exit(0);
    }
    public static void main( String[] args ){
        int num, f, solnum = 0;
        int n = args.length;
        int[] P;

        // If there are 2 command line arguments make sure the order is "-v"
        // and an integer, then execute the solution.  Otherwise print an 
        // error message and exit the program.
        if(n == 2){
            if(!args[0].startsWith("-v")){
                printErr();
            }
            try{
                num = Integer.parseInt(args[1]);
                P = new int[num+1];
                for(int i = 0; i <= num; ++i){
                    P[i] = i;
                }
                f = fact(num);
                for(int i = 0; i < f; ++i){
                    nextPermutation(P);
                    if(isSolution(P)) {
                        solnum++;
                        printArray(P);
                    }
                }
                System.out.println(num + "-Queens has " + solnum + " solutions");
            }catch(NumberFormatException e1){
                printErr();
            }
        
        // If there is one command line argument, then make sure it is an int
        // and then pring the number of solutions.  Other wise print the error message
        // and exit.
        } else if(n == 1){
            try{
                num = Integer.parseInt(args[0]);
                P = new int[num+1];
                for(int i = 0; i <= num; ++i){
                    P[i] = i;
                }
                f = fact(num);
                for(int i = 0; i < f; ++i){
                    nextPermutation(P);
                    if(isSolution(P)) {
                        solnum++;
                    }
                }
                System.out.println(num + "-Queens has " + solnum + " solutions");
            } catch(NumberFormatException e2){
                printErr();
            }

        // If there are more than 2 or less than 1 command line arguments then
        // print the error message and exit.
        } else {
            printErr();
        }
    }
}