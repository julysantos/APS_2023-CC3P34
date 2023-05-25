import Objects.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TelaJogo extends JPanel implements Runnable {

    private final JLabel background = new JLabel("bg_old");

    public TelaJogo() {
        setSize(500, 500);
        setVisible(true);
        Thread thread = new Thread(this);
        thread.start();
    }

    private boolean rodando = true;
    private boolean jogando = true;

    ArrayList<Reciclavel> reciclaveis = new ArrayList<>();
    private int pontos = 0;
    private int level = 1;
    private int combo = 0;
    private Lixeira lixeira = new Lixeira(60, 60, 60, 60);


    public void erroJogo() {
        for (Reciclavel c : reciclaveis) {
            combo = 0;
            System.out.println("pontuação: " + pontos);
            reciclaveis.add(new Reciclavel(500, 65, 50, 50, c.getVelocidadeX(), 0));
            reciclaveis.remove(c);
        }
    }

    // metódo gerando a logica do jogo
    public void logicaJogo() {
        while (getRodando()) {
            //nao apagar
            System.out.print("");
            try {
                while (getJogando()) {
                    this.repaint();

                    // movimenta os reciclaveis
                    for (Reciclavel c : reciclaveis) {
                        c.movimetar();

                        // verifica o contato dos reciclaveis com a lixeira (mecanica acerto/erro)
                        if (lixeira.contato(c)) { // acerto
                            if (c.getTipoReciclavel().equals(lixeira.getEstado())) {
                                pontos++;
                                combo++;
                                System.out.println("pontuação: " + pontos);
                                reciclaveis.remove(c);
                                reciclaveis.add(new Reciclavel(500, 65, 50, 50, c.getVelocidadeX(), 0));
                                // aumenta o nível e a velocidade do jogo
                                for (Reciclavel co : reciclaveis) {
                                    if (combo % 5 == 0 && level < 5) {
                                        level++;
                                        co.setVelocidadeX((-5) - pontos);
                                    } else if (combo % 5 == 0 && level < 10){
                                        level++;
                                        co.setVelocidadeX((-3) -  pontos);
                                    }
                                }
                                System.out.println("nível: " + level);
                                System.out.println("velocidade: " + c.getVelocidadeX());
                            } else { // errar ao escolher a cor errada da lixeira
                                if (!lixeira.getEstado().equals("comum")) {
                                    erroJogo();
                                }
                            }
                        }
                        if (c.getX() <= 0) { // errar ao deixar o reciclavel passar
                            erroJogo();
                        }
                        // delay no jogo pra ele nao ser ultra rapido (quase invisivel)
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            this.repaint();
        }
    }

    // KeyListener pegando os inputs do teclado
    KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            // setando lixeira
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A -> lixeira.setEstado("plástico");
                case KeyEvent.VK_D -> lixeira.setEstado("papel");
                case KeyEvent.VK_S -> lixeira.setEstado("metal");
                case KeyEvent.VK_W -> lixeira.setEstado("vidro");
            }
            // tecla pause
            if (keyCode == KeyEvent.VK_ESCAPE) {
                if (getJogando()) {
                    setJogando(false);
                    System.out.println("jogo pausado");
                } else {
                    setJogando(true);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            lixeira.setEstado("comum");
        }
    };

    public void setJogando(boolean jogoOn) {
        this.jogando = jogoOn;
    }

    public boolean getJogando() {
        return jogando;
    }

    public boolean getRodando() {
        return rodando;
    }

    @Override
    public void run() {
        reciclaveis.add(new Reciclavel(500, 65, 50, 50, -5, 0));
        this.addKeyListener(keyListener);
        this.setFocusable(true);
        this.requestFocus();
        this.setJogando(true);
        this.setVisible(true);
        this.logicaJogo();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        File path = new File("src/Images");
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path,"bg_old.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(image, 0,0, null);
        lixeira.draw(g);
        for (Reciclavel r : reciclaveis) {
            r.draw(g);
        }
        g.setColor(Color.black);
        Font fonte = new Font("Comic Sans MS", Font.BOLD, 32);
        g.setFont(fonte);
        if(!jogando){
            g.drawString("Jogo pausado", 10,450);
        }
        g.drawString("Pontuação: " + pontos, 250, 450);
        g.drawString("Nível: " + level, 250, 415);
    }
}
