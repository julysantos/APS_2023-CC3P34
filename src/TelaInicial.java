import java.awt.*;
import java.awt.event.KeyListener;
import java.security.Key;
import javax.swing.*;
public class TelaInicial extends JPanel{
    public TelaInicial(){
        
        this.setSize(500, 500);

        Container container = this;
        container.setLayout(new FlowLayout());

        JButton okButton = new JButton("Jogar");
        okButton.addActionListener(e -> {
            JRootPane geleia = this.getRootPane();
            TelaJogo jogo = new TelaJogo();
            geleia.setContentPane(jogo);
        });

        container.add(okButton);

        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        setBackground(new Color(105, 209, 150));
        g.setColor(Color.black);
        Font fonte = new Font("Comic Sans MS",Font.BOLD, 64);
        g.setFont(fonte);
        g.drawString("Jorginho", 120, 160);

    }
}
