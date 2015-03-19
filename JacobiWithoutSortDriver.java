import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 *
 * @author Utkarsh Garg
 */
public class JacobiWithoutSortDriver extends JFrame{
    
    private ArrayList<Double> arrayList;
    private JacobiWithoutSort jacobi;
    
    
    public JacobiWithoutSortDriver() {
        setSize(800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.LIGHT_GRAY);
        jacobi = new JacobiWithoutSort();
        ArrayList<Double> list = jacobi.getOffBs();
        arrayList = new ArrayList<Double>();
        for (double item : list) {
            arrayList.add(Math.log(item));
        }
        
    }
    
    
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLUE);
        g.drawLine(0,(int)(-Math.log(jacobi.offA()) + 150),700, 75 - (int)Math.log(jacobi.offA())+150); 
        g.setColor(Color.BLACK);
        for (int i = 0; i < arrayList.size(); i++) {
            g.drawOval(i * 5, (int) (-arrayList.get(i) + 150),2,2);
        }
        
    }
    
    

        
    
    public static void main(String[] args) {
        JacobiWithoutSortDriver frame = new JacobiWithoutSortDriver();
        frame.setVisible(true);
               
        
    }
}
