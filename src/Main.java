import javax.swing.*;
import java.awt.*;

// nao sei chamar telas mim ser burra
public class Main extends JFrame{
     public Main(String title) {
         super("jorge");
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.pack();

         Container container = this.getRootPane();
         container.setLayout(new FlowLayout());

         JTextField textField = new JTextField();
         textField.setPreferredSize(new Dimension(150, 25));

         JLabel label = new JLabel("");

         JButton okButton = new JButton("OK");
         okButton.addActionListener(e -> {
             label.setText("eu te amo");
             TelaJogo tj = new TelaJogo("jorge");
         });

         container.add(textField);
         container.add(okButton);
         container.add(label);

         this.setVisible(true);
     }

     public static void main(String[] args){
        Main m = new Main("Jorge");
     }
}