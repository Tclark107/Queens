# Queens

Finds a solution to the n Queens on an nxn board, by first finding a solution to the n Rooks problem.  
We do that by finding all the spaces where each rook has its own row and column.  This is done by checking all
permutions of the way n numbers can be organized in different spots in the array.  We then check if its a solutions
by checking whether the any of the arrays are on the same diagonal.  And finally, printing the solutions.