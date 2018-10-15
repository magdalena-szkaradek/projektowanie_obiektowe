package withGUI;

import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

public class CalculateDelta extends JFrame

{

    private JTextField firstJTF, secondJTF, thirdJTF; 

    private JLabel Root1, Root2;

    private JLabel firstJL, secondJL, thirdJL, Root1x, Root2x, space, space1, space2;

    private JButton calculateB;

    private CalculateButtonHandler cbHandler;

    private ExitButtonHandler ebHandler;

    public CalculateDelta()

    {

        setTitle("");        

        firstJL = new JLabel("Podaj a", SwingConstants.CENTER);

        secondJL = new JLabel("Podaj b", SwingConstants.CENTER);

        thirdJL = new JLabel("Podaj c", SwingConstants.CENTER);
        

        firstJL.setFont(new Font("Courier New", Font.ITALIC, 12));

        secondJL.setFont(new Font("Courier New", Font.ITALIC, 12));

        thirdJL.setFont(new Font("Courier New", Font.ITALIC, 12));

        Root1x = new JLabel("Pierwiastek pierwszy:", SwingConstants.CENTER);

        Root2x = new JLabel("Pierwiastek drugi:", SwingConstants.CENTER);

        space = new JLabel("", SwingConstants.CENTER);

        space1 = new JLabel("", SwingConstants.CENTER);

        space2 = new JLabel("", SwingConstants.CENTER);

        final Color col = Color.CYAN;
        final Color colBlue = Color.BLUE;
        final Color colGrey = Color.lightGray;



        firstJTF = new JTextField(6);
        firstJTF.setBackground(col);

        secondJTF = new JTextField(6);
        secondJTF.setBackground(col);

        thirdJTF = new JTextField(6);
        thirdJTF.setBackground(col);

        Root1 = new JLabel("", SwingConstants.LEFT);

        Root2 = new JLabel("", SwingConstants.LEFT);             

        space = new JLabel("", SwingConstants.RIGHT);
        space1 = new JLabel("", SwingConstants.RIGHT);

        space2 = new JLabel("", SwingConstants.RIGHT);



        calculateB = new JButton("Oblicz pierwiastki");
        
        calculateB.setBackground(colBlue);
        calculateB.setForeground(col);
        calculateB.setFont(new Font("Courier New", Font.ITALIC, 12));

        cbHandler = new CalculateButtonHandler();

        calculateB.addActionListener(cbHandler);


        Container pane = getContentPane();

        pane.setLayout(new GridLayout(8, 6));
        pane.setBackground(colGrey);
       

        pane.add(space1);
        pane.add(space2);
        pane.add(firstJL);

        pane.add(firstJTF);

        pane.add(secondJL);

        pane.add(secondJTF);

        pane.add(thirdJL);

        pane.add(thirdJTF);

        pane.add(Root1x);

        pane.add(Root1);

        pane.add(Root2x);

        pane.add(Root2);

        pane.add(space);
        
        pane.add(calculateB);

 

        setSize(800,500);

        setVisible(true);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

 

    private class CalculateButtonHandler implements ActionListener

    {

        public void actionPerformed(ActionEvent e)

        {

            double a = Double.parseDouble(firstJTF.getText());

            double b = Double.parseDouble(secondJTF.getText());

            double c = Double.parseDouble(thirdJTF.getText());

             Double delta = b* b - 4* a * c;

             System.out.println(delta);


          
             if(delta == 0){

                 Double x1 = (-1 * b)/ 2* a;
                // Double x2 = (-1 * b)/ 2* a;
                 
                 Root1.setText("Jest jeden podwójny pierwiastek: " + x1 );

                 Root2.setText("");
                 System.out.println("Rozwiazanie to: " + "");

             }else if (delta > 0){



                 Double sqrt = Math.sqrt(delta);

                 Double x1 = ((-1 * b ) - sqrt)/ 2 * a;

                 Double x2 = ((-1 * b ) + sqrt)/ 2 * a;
                 Root1.setText("" + x1 );

                 Root2.setText("" + x2);

                 System.out.println("Rozwi¹zanie to: " + x1 + ", " + x2);

             }else if (delta < 0){
            	 
            	 Root1.setText("" + "Delta jest mniejsza od  0" );

                 Root2.setText("" + "Delta jest mniejsza od  0 ");

                 System.out.println("delta jest ujemna - brak rozwi¹zañ");

             }


        }

    }

 

    private class ExitButtonHandler implements ActionListener

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