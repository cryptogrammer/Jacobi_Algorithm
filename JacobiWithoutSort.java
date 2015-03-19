import Jama.*;
import java.util.Random;
import java.util.ArrayList;

/**
 *
 * @author Utkarsh Garg
 */
public class JacobiWithoutSort {
    
    private Matrix myArray;
    private Matrix initialMatrix;
    private Random rand;
    private int[][] coordinates = {{0,1},{0,2},{0,3},{0,4},{1,2},{1,3},{1,4},{2,3},{2,4},{3,4}};
    private int count = 0;
    
    public JacobiWithoutSort() {

	rand = new Random();
    myArray = Matrix.random(5,5);
	myArray = myArray.transpose().times(myArray);  //creates a symmetric matrix
    initialMatrix = myArray;

    }

    
    public ArrayList<Double> getOffBs() {
        ArrayList<Double> offBvals = new ArrayList<Double>();
        Matrix temporary = new Matrix(5,5);
        while (offB(myArray) > .000000001 ) {
            offBvals.add(offB(myArray));
            temporary = matrixB(myArray);
            myArray = temporary;
            count++;
            if (count >= coordinates.length){
                count = 0;
            }
        }
        
        return offBvals;
    }
    
    
    public double offA() {
        return offB(initialMatrix);
    }

    
    private int[] maximum(Matrix matrix) {
	int[] max = {0,1};
	for (int i = 0; i < matrix.getRowDimension(); i++) {
	    for (int j = i; j < matrix.getColumnDimension(); j++) {
                if (Math.abs(matrix.get(i,j)) > Math.abs(matrix.get(max[0], max[1]))) {
	            if (i != j) {
		            max[0] = i;
		            max[1] = j;
	            }
	        }
            }
        }
	return max;
    }



     private Matrix Matrix2X2(Matrix matrix) {
	Matrix matrix2X2 = new Matrix(2,2);
    int[] maximum = coordinates[count];
	matrix2X2.set(0,1,matrix.get(maximum[0],maximum[1]));
	matrix2X2.set(1,0,matrix.get(maximum[1],maximum[0]));
	matrix2X2.set(0,0,matrix.get(maximum[0],maximum[0]));
	matrix2X2.set(1,1,matrix.get(maximum[1],maximum[1]));


	return matrix2X2;
    }

    private Matrix EigenVectorGetter(Matrix matrix) {
    EigenvalueDecomposition eigenVector = matrix.eig();
	Matrix eigen = eigenVector.getV();
	return eigen;
    }


    

    private Matrix matrixB(Matrix matrix) {
	Matrix g = givenValGetter(matrix);
	Matrix gTranspose = g.transpose();
	Matrix result = gTranspose.times(matrix);
	result = result.times(g);
	return result;
    }


   
    private double offB(Matrix matrix) {
	double offVals = 0;
	for (int i = 0; i < matrix.getRowDimension(); i++) {
	    for (int j = 0; j < matrix.getColumnDimension(); j++) {
		if (i != j) {
		    offVals += Math.pow(matrix.get(i,j),2);
		}
	    }
	}
	System.out.println(offVals);
	return offVals;
    }
    
   
    private Matrix givenValGetter(Matrix matrix) {
	Matrix identity = Matrix.identity(matrix.getRowDimension(),matrix.getColumnDimension());
	Matrix subVal = EigenVectorGetter(Matrix2X2(matrix));
	int[] maximum = maximum(matrix);
	identity.set(maximum[0],maximum[0], subVal.get(0,0));
	identity.set(maximum[0],maximum[1], subVal.get(0,1));
	identity.set(maximum[1],maximum[0], subVal.get(1,0));
	identity.set(maximum[1],maximum[1], subVal.get(1,1));
	return identity;
    }
 
}
