import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyListener;
import java.security.Key;
import javax.swing.*;
public class TelaInicial extends JPanel{

    public TelaInicial(){
        
        this.setSize(500, 500);

        Container container = this;
        container.setLayout(new FlowLayout());

        JLabel label = new JLabel("jorginho");

        JButton okButton = new JButton("Jogar");
        okButton.addActionListener(e -> {
            JRootPane geleia = this.getRootPane();
            TelaJogo jogo = new TelaJogo();
            geleia.setContentPane(jogo);
        });

        container.add(okButton);
        container.add(label);

        this.setVisible(true);
    }

}
