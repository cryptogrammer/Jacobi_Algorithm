import java.util.Random;
import Jama.*;
import java.util.ArrayList;


/**
 *
 * @author Utkarsh Garg
 */

public class Jacobi {
    private Matrix myArray;
    private Matrix initial;
    private Random rand;

    public Jacobi() {

	rand = new Random();
    myArray = Matrix.random(5,5);
	myArray = myArray.transpose().times(myArray);  //creates a symmetric matrix
    initial = myArray;
    }

    
   
    public ArrayList<Double> offBCalculate() {
        ArrayList<Double> values = new ArrayList<Double>();
        Matrix temporary = new Matrix(5,5);
        while (offB(myArray) > .000000001 ) {
        values.add(offB(myArray));
        temporary = matrixBCalc(myArray);
        myArray = temporary;
        }
        
        return values;
    }
    
    
   
    public double offAInitial() {
        return offB(initial);
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



   
    private Matrix get2X2Matrix(Matrix matrix) {
	Matrix retMatrix = new Matrix(2,2);
	int[] max = maximum(matrix);
	retMatrix.set(0,1,matrix.get(max[0],max[1]));
	retMatrix.set(1,0,matrix.get(max[1],max[0]));
	retMatrix.set(0,0,matrix.get(max[0],max[0]));
	retMatrix.set(1,1,matrix.get(max[1],max[1]));

	return retMatrix;
    }

    
    private Matrix eigenVectGetter(Matrix matrix) {
        EigenvalueDecomposition eigVect = matrix.eig();
	Matrix eigVectMatrix = eigVect.getV();
	return eigVectMatrix;
    }


    
    private Matrix getGivens(Matrix matrix) {
	Matrix mtIdentity = Matrix.identity(matrix.getRowDimension(),matrix.getColumnDimension());
	Matrix subVals = eigenVectGetter(get2X2Matrix(matrix));
	int[] maximum = maximum(matrix);
	mtIdentity.set(maximum[0],maximum[0], subVals.get(0,0));
	mtIdentity.set(maximum[0],maximum[1], subVals.get(0,1));
	mtIdentity.set(maximum[1],maximum[0], subVals.get(1,0));
	mtIdentity.set(maximum[1],maximum[1], subVals.get(1,1));
	return mtIdentity;
    }
 

    
    private Matrix matrixBCalc(Matrix matrix) {
	Matrix g = getGivens(matrix);
	Matrix gTranspose = g.transpose();
	Matrix result = gTranspose.times(matrix);
	result = result.times(g);
	return result;
    }


    
    private double offB(Matrix matrix) {
	double offValues = 0;
	for (int i = 0; i < matrix.getRowDimension(); i++) {
	    for (int j = 0; j < matrix.getColumnDimension(); j++) {
		if (i != j) {
		    offValues += Math.pow(matrix.get(i,j),2);
		}
	    }
	}
	System.out.println(offValues);
	return offValues;
    }
}
