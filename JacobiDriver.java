import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 *
 * @author Utkarsh Garg
 */
public class JacobiDriver extends JFrame{
    private ArrayList<Double> myArray;
    private Jacobi jacobi;
    private static ArrayList<Double> list;
    
    public JacobiDriver() {
    	super("JacobiGraph");
        setSize(800,800);
        setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jacobi = new Jacobi();
        Jacobi jacobiCopy = jacobi;
        list = jacobi.offBCalculate();
        myArray = new ArrayList<Double>();
        for (double unit : list) {
            myArray.add(Math.log(unit));
        }
        
    }
    
    
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.drawLine(0,(int)(-Math.log(jacobi.offAInitial()) + 150),300, 32 - (int)Math.log(jacobi.offAInitial())+150); 
        g.setColor(Color.GRAY);
        for (int i = 0; i < myArray.size(); i++) {
            g.drawOval(i * 5, (int) (-myArray.get(i) + 150),2,2);
        }
        
    }
    
    

        
    
    public static void main(String[] args) {
    	
        JacobiDriver jGraph = new JacobiDriver();        
        jGraph.pack();
        jGraph.setVisible(true);
               
        
    }
}
