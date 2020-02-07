//-----------------------------------------------------------------------------
//   CommandLineArguments.java
//   Illustrates reading and parsing command line arguments as int, double
//   and String. This also illustrates the fact that nothing need be done
//   with an Exception once it is caught.
//-----------------------------------------------------------------------------

class CommandLineArguments{
   
    public static void main( String[] args ){
      System.out.println("args.length is: "+ args.length);
       int i, n = args.length;
       System.out.println("args length is: " + n);
       System.out.println("am i here?");
       for(i=0; i<n; i++){
          System.out.println("in loop: + i");
          try{
             Integer.parseInt(args[i]);
             System.out.println(args[i]+" is an int");
          }catch(NumberFormatException e1){
            try{
                Double.parseDouble(args[i]);
                System.out.println(args[i]+" is a double");
            }catch(NumberFormatException e2){
                System.out.println(args[i]+" is a non-numeric string");
            }
         }  
      }
      System.out.println("how bout after the for loop?");
 
   }
 }