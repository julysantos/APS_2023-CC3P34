import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
public class TelaInicial extends JPanel{

    public TelaInicial(){

        JPanel menu = new JPanel();
        menu.setSize(500, 400);

        Container container = menu.getRootPane();
        container.setLayout(new FlowLayout());

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(150, 25));

        JLabel label = new JLabel("Input will appear here");

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            String input = textField.getText();
            System.out.println("Input: " + input);

            label.setText(input);
        });

        container.add(textField);
        container.add(okButton);
        container.add(label);

        menu.setVisible(true);
    }

}
