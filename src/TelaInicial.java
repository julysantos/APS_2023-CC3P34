import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
public class TelaInicial extends JPanel{

    private BufferedImage container_img;
    public TelaInicial(){
        
        this.setSize(500, 500);

        try {
            container_img = ImageIO.read(new File("src/Images","container_main.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
        g.drawImage(container_img, 45,30, null);
        g.setColor(Color.black);
        Font fonte = new Font("Comic Sans MS",Font.BOLD, 54);
        g.setFont(fonte);
        g.drawString("Jogo APS", 120, 160);
        g.drawString("UNIP 2023/1", 70, 230);

    }
}
