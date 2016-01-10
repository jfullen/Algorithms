/*
 * Author: Jason Fullen
 * Date: 01-10-2016
 * Purpose: Answers to common array interview questions like printing in different patterns
 *          and rotating array values. 
 */
public class ArrayFunctions {
	
	/*
	 * Prints all of the values in an MxN array as a clockwise spiral.
	 *
	 * Sample Output:
	 * Input = {{ 1, 2, 3 },    
	 *          { 4, 5, 6 },     => 1  2  3  6  9  8  7  4  5
	 *          { 7, 8, 9 }};
	 *
	 * numRows - number of rows in array
	 * numCols - number of columns in array
	 * data    - array to be printed in a clockwise spiral
	 */	
	public static void printArraySpiral(int numRows, int numCols, int[][] data) {		
		int i, startRow = 0, startCol = 0, endRow = numRows - 1, endCol = numCols - 1;
		
		while (startRow <= endRow && startCol <= endCol) {
			
			//Print row left to right
			for (i = startCol; i <= endCol; ++i) {
				System.out.print(data[startRow][i] + " ");
			}
			startRow++;

			//Print column top to bottom
			for (i = startRow; i <= endRow; ++i) {
				System.out.print(data[i][endCol] + " ");
			}
			endCol--;

			//Print row right to left
			if (startRow < endRow) {
				for (i = endCol; i >= startCol; --i) {
					System.out.print(data[endRow][i] + " ");
				}
				endRow--;
			}
			
			//Print col bottom to top
			if (startCol < endCol) {
				for (i = endRow; i >= startRow; --i) {
					System.out.print(data[i][startCol] + " ");
				}
				startCol++;
			}
		}
	}

	/*
	 * Prints all of the values in an MxN array as a row-wise zig-zag. Even number rows are printed
	 * normally and odd number rows are printed in reverse
	 *
	 * Sample Output:
	 * Input = {{ 1, 2, 3 },    
	 *          { 4, 5, 6 },     => 1  2  3  6  5  4  7  8  9
	 *          { 7, 8, 9 }};
	 *
	 * numRows - number of rows in array
	 * numCols - number of columns in array
	 * data    - array to be printed row-wise
	 */
	public static void printArrayRowWise(int numRows, int numCols, int[][] data) {
		for (int i = 0; i < numRows; ++i) {	
			int j;
			if (i % 2 == 0) {
				//Even rows print left to right
				for (j = 0; j < numCols; j++) {
					System.out.print(data[i][j] + " ");
				}
			}
			else {
				//Odd rows print right to left
				for (j = numCols - 1; j >= 0; --j) {
					System.out.print(data[i][j] + " ");
				}
			}
		}
	}
	
	/*
	 * Prints all of the values in an MxN array as a column-wise zig-zag. Even number columns are printed
	 * normally and odd number columns are printed in reverse
	 *
	 * Sample Output:
	 * Input = {{ 1, 2, 3 },    
	 *          { 4, 5, 6 },     => 1  4  7  8  5  2  3  6  9 
	 *          { 7, 8, 9 }};
	 *
	 * numRows - number of rows in array
	 * numCols - number of columns in array
	 * data    - array to be printed column-wise
	 */
	public static void printArrayColWise(int numRows, int numCols, int[][] data) {
		for (int i = 0; i < numCols; ++i) {	
			int j;
			if (i % 2 == 0) {
				//Even columns print top to bottom
				for (j = 0; j < numRows; j++) {
					System.out.print(data[j][i] + " ");
				}
			}
			else {
				//Odd columns print bottom to top
				for (j = numRows - 1; j >= 0; --j) {
					System.out.print(data[j][i] + " ");
				}
			}
		}
	}
	
	/*
	 * Prints all of the values in an NxN array diagonally as a zig-zag. 
	 *
	 * Sample Output:
	 * Input = {{ 1, 2, 3 },    
	 *          { 4, 5, 6 },     => 1  2  4  7  5  3  6  8  9
	 *          { 7, 8, 9 }};
	 *
	 * length - dimension of array
	 * data   - array to be printed as a zig-zag diagonally
	 */
	public static void printArrayZigZag(int length, int[][] data) {
		int i = 0, j = 0;
		boolean goUp = true;
		
		while(i < length && j < length) {
			System.out.print(data[i][j] + " ");

			//Check if we are going upward and on an edge of the array
			if (goUp && (i <= 0 || j >= length - 1)) {
				if (i <= 0) {
					if (j >= length - 1) {
						//Hit the upper right corner of the array, start moving down the arrays last column
						i++;
					} else {
						j++;
					}
				}
				else if (j >= length - 1) {
					i++;
				}
				goUp = false;
			}
			//Check if we are going downward and on an edge of the array
			else if (!goUp && (j <= 0 || i >= length - 1)) {
				if (j <= 0) {
					if (i >= length - 1) {
						//Hit the lower left corner of the array, start moving across the arrays last row
						j++;
					} else {
						i++;
					}
				}					
				else if (i >= length - 1) {
					j++;
				}
				goUp = true;
			}
			//Otherwise, move to the next index to be printed
			else {
				if (goUp) {
					i--;
					j++;
				} else {
					i++;
					j--;
				}
			}
		}
	}
	
	/*
	 * Rotates the values of an NxN array by 90 degrees.
	 *
	 * This algorithm treats the array as a series of circles (N/2 circles) that need to be rotated
	 * 90 degrees. After rotating, row i of the array becomes column N-i-1 of the array.
	 *
	 * Sample Output:
	 * Input = {{ 1, 2, 3 },           Output = {{ 7, 4, 1 },    
	 *          { 4, 5, 6 },     =>              { 8, 5, 2 },
	 *          { 7, 8, 9 }};                    { 9, 6, 3 }};
	 *
	 * data   - array to be rotated 90 degrees
	 * length - size of the array
	 */
	public static void rotateArray(int[][] data, int length) {
		for (int i = 0; i < length / 2; ++i) {
			for (int j = i; j < length - i - 1; ++j) {
				int tmpUpperLeft = data[i][j]; //Save UpperLeft
				data[i][j] = data[length - 1 - j][i]; //UpperLeft = LowerLeft
				data[length - 1 - j][i] = data[length - 1 - i][length - 1 - j]; //LowerLeft = LowerRight
				data[length - 1 - i][length - 1 - j] = data[j][length - 1 - i]; //LowerRight = UpperRight
				data[j][length - 1 - i] = tmpUpperLeft; //UpperRight = UpperLeft
			}
		}
	}
	
	/*
	 * Prints an MxN array
	 *
	 * data    - array to be printed
	 * numRows - number of rows in array
	 * numCols - number of columns in array
	 */
	public static void printArray(int[][] data, int numRows, int numCols) {
		for (int i = 0; i < numRows; ++i) {
			for (int j = 0; j < numCols; ++j) {
				System.out.print(data[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	/*
	 * ArrayFunctions main function
	 */
	public static void main(String[] args) {
		
		int data1[][] = {{ 1,  2,  3 },
		    			 { 4,  5,  6 },
		    			 { 7,  8,  9 }};
		
		int data2[][] = {{ 1,  2,  3,  4 },
    		        	 { 5,  6,  7,  8 },
    		        	 { 9,  10, 11, 12 },
    		        	 { 13, 14, 15, 16 }};
	
		int data3[][]={{ 1,  2,  3,  4,  5 }, 
			      	   { 6,  7,  8,  9,  10 },
			      	   { 11, 12, 13, 14, 15 }, 
			      	   { 16, 17, 18, 19, 20 }, 
			      	   { 21, 22, 23, 24, 25 }};
		
		int data4[][] = {{ 1,  2,  3,  4,  5,  6 },
	                 	 { 7,  8,  9,  10, 11, 12 },
	                 	 { 13, 14, 15, 16, 17, 18 }};
		
		int data5[][] = {{ 1,  2,  3 },
				     	 { 4,  5,  6 },
                     	 { 7,  8,  9 },
                     	 { 10, 11, 12 },
                     	 { 13, 14, 15 },
                     	 { 16, 17, 18 }};

		//Test all functions using a 3x3 array
		System.out.println("Print Array (3x3)");
		printArray(data1, data1.length, data1[0].length);
		System.out.println("\nPrint Array (3x3) as a clockwise spiral");
		printArraySpiral(data1.length, data1[0].length, data1);
		System.out.println("\n\nPrint Array (3x3) row-wise zig-zag");
		printArrayRowWise(data1.length, data1[0].length, data1);
		System.out.println("\n\nPrint Array (3x3) column-wise zig-zag");
		printArrayColWise(data1.length, data1[0].length, data1);
		System.out.println("\n\nPrint Array (3x3) as a diagonal zig-zag");
		printArrayZigZag(data1.length, data1);		
		rotateArray(data1, data1.length);
		System.out.println("\n\nPrint Array (3x3) after rotating 90 degrees");
		printArray(data1, data1.length, data1[0].length);
		
		//Test all functions using a 4x4 array
		System.out.println("\n----------------------------------------------------------\n");
		System.out.println("Print Array (4x4)");
		printArray(data2, data2.length, data2[0].length);
		System.out.println("\nPrint Array (4x4) as a clockwise spiral");
		printArraySpiral(data2.length, data2[0].length, data2);
		System.out.println("\n\nPrint Array (4x4) row-wise zig-zag");
		printArrayRowWise(data2.length, data2[0].length, data2);
		System.out.println("\n\nPrint Array (4x4) column-wise zig-zag");
		printArrayColWise(data2.length, data2[0].length, data2);
		System.out.println("\n\nPrint Array (4x4) as a diagonal zig-zag");
		printArrayZigZag(data2.length, data2);		
		rotateArray(data2, data2.length);
		System.out.println("\n\nPrint Array (4x4) after rotating 90 degrees");
		printArray(data2, data2.length, data2[0].length);
		
		//Test all functions using a 5x5 array
		System.out.println("\n----------------------------------------------------------\n");
		System.out.println("Print Array (5x5)");
		printArray(data3, data3.length, data3[0].length);
		System.out.println("\nPrint Array (5x5) as a clockwise spiral");
		printArraySpiral(data3.length, data3[0].length, data3);
		System.out.println("\n\nPrint Array (5x5) row-wise zig-zag");
		printArrayRowWise(data3.length, data3[0].length, data3);
		System.out.println("\n\nPrint Array (5x5) column-wise zig-zag");
		printArrayColWise(data3.length, data3[0].length, data3);
		System.out.println("\n\nPrint Array (5x5) as a diagonal zig-zag");
		printArrayZigZag(data3.length, data3);		
		rotateArray(data3, data3.length);
		System.out.println("\n\nPrint Array (5x5) after rotating 90 degrees");
		printArray(data3, data3.length, data3[0].length);
		
		//Test all functions using a 3x6 array
		System.out.println("\n----------------------------------------------------------\n");
		System.out.println("Print Array (3x6)");
		printArray(data4, data4.length, data4[0].length);
		System.out.println("\nPrint Array (3x6) as a clockwise spiral");
		printArraySpiral(data4.length, data4[0].length, data4);
		System.out.println("\n\nPrint Array (3x6) row-wise zig-zag");
		printArrayRowWise(data4.length, data4[0].length, data4);
		System.out.println("\n\nPrint Array (3x6) column-wise zig-zag");
		printArrayColWise(data4.length, data4[0].length, data4);
		System.out.println();
		
		//Test all functions using a 6x3 array
		System.out.println("\n----------------------------------------------------------\n");
		System.out.println("Print Array (6x3)");
		printArray(data5, data5.length, data5[0].length);
		System.out.println("\nPrint Array (6x3) as a clockwise spiral");
		printArraySpiral(data5.length, data5[0].length, data5);
		System.out.println("\n\nPrint Array (6x3) row-wise zig-zag");
		printArrayRowWise(data5.length, data5[0].length, data5);
		System.out.println("\n\nPrint Array (6x3) column-wise zig-zag");
		printArrayColWise(data5.length, data5[0].length, data5);
		System.out.println("\n");
	}
}