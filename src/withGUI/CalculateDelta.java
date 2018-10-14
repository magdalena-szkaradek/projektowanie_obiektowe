package withGUI;

//public class hello {
//
//	public static void main(String[] args) {
//System.out.println("Hello");
//	}
//
//}


import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

public class CalculateDelta extends JFrame

{

    private JTextField CoA, CoB, CoC; 

    private JLabel Root1, Root2;

    private JLabel CoAx, CoBx, CoCx, Root1x, Root2x;

    private JButton calculateB, exitB;

    private CalculateButtonHandler cbHandler;

    private ExitButtonHandler ebHandler;

 

    public CalculateDelta()

    {

        setTitle("Quadratic Fun");        

        CoAx = new JLabel("a", SwingConstants.CENTER);

        CoBx = new JLabel("b", SwingConstants.CENTER);

        CoCx = new JLabel("c", SwingConstants.CENTER);

        Root1x = new JLabel("Root1", SwingConstants.CENTER);

        Root2x = new JLabel("Root2", SwingConstants.CENTER);


        CoA = new JTextField(10);

        CoB = new JTextField(10);

        CoC = new JTextField(10);

        Root1 = new JLabel("", SwingConstants.RIGHT);

        Root2 = new JLabel("", SwingConstants.RIGHT);             

        

        calculateB = new JButton("Calculate");

        cbHandler = new CalculateButtonHandler();

        calculateB.addActionListener(cbHandler);

       

        exitB = new JButton("Exit");

        ebHandler = new ExitButtonHandler();

        exitB.addActionListener(ebHandler);


        Container pane = getContentPane();//Get the container

        pane.setLayout(new GridLayout(6, 2));//Set the Layout

        //Place the components in the pane

        pane.add(CoAx);

        pane.add(CoA);

        pane.add(CoBx);

        pane.add(CoB);

        pane.add(CoCx);

        pane.add(CoC);

        pane.add(Root1x);

        pane.add(Root1);

        pane.add(Root2x);

        pane.add(Root2);

        pane.add(calculateB);

        pane.add(exitB);

 

        setSize(400,300);

        setVisible(true);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

 

    private class CalculateButtonHandler implements ActionListener//Creates a class on top of the interface

    {

        public void actionPerformed(ActionEvent e)

        {

            double a = Double.parseDouble(CoA.getText());

            double b = Double.parseDouble(CoB.getText());

            double c = Double.parseDouble(CoC.getText());

             Double delta = b* b - 4* a * c;

             System.out.println(delta);


          
             if(delta == 0){

                 Double x1 = (-1 * b)/ 2* a;
                 Double x2 = (-1 * b)/ 2* a;
                 
                 Root1.setText("" + x1 );

                 Root2.setText("" + x2);
                 System.out.println("Rozwiazanie to: " + "");

             }else if (delta > 0){



                 Double sqrt = Math.sqrt(delta);

                 Double x1 = ((-1 * b ) - sqrt)/ 2 * a;

                 Double x2 = ((-1 * b ) + sqrt)/ 2 * a;
                 Root1.setText("" + x1 );

                 Root2.setText("" + x2);

                 System.out.println("Rozwi¹zanie to: " + x1 + ", " + x2);

             }else if (delta < 0){

                 System.out.println("delta jest ujemna - brak rozwi¹zañ");

             }
         //   Root1.setText("" + a + " " + b + " " + c );
//
//
//            Root2.setText("" + x1 + " " + x2);

 

        }

    }

 

    private class ExitButtonHandler implements ActionListener//Creates a class on top of the interface

    {

        public void actionPerformed(ActionEvent e)

        {

            System.exit(0);

        }

    }

    public static void main ( String[] args) 

    {

    	CalculateDelta labwin = new CalculateDelta();

    }

 
}