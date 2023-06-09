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

    public TelaJogo() {
        setSize(500, 500);
        setVisible(true);
        Thread thread = new Thread(this);
        try {
            backgroundImage = ImageIO.read(new File("src/Images","bg_old.jpg"));
            conveyor = ImageIO.read(new File("src/Images","conveyor.png"));
            containerGame = ImageIO.read(new File("src/Images", "container_game.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        thread.start();
    }

    private boolean rodando = true;
    private boolean jogando = true;

    ArrayList<Reciclavel> reciclaveis = new ArrayList<>();
    private int pontos = 0;
    private int level = 1;
    private int combo = 0;
    private Lixeira lixeira = new Lixeira(50, 80, 60, 60);

    BufferedImage backgroundImage;
    BufferedImage conveyor;
    BufferedImage containerGame;

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
                                reciclaveis.add(new Reciclavel(500, 65, 50, 50, c.getVelocidadeX(), c.getVelocidadeY()));
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
        g.drawImage(backgroundImage, 0,0, null);
        g.drawImage(conveyor, 100,106,null);
        g.drawImage(containerGame, 42,250, null);
        lixeira.draw(g);
        for (Reciclavel r : reciclaveis) {
            r.draw(g);
        }
        g.setColor(Color.black);
        Font fonte = new Font("Comic Sans MS", Font.BOLD, 24);
        g.setFont(fonte);
        if(level < 2){
            g.drawString("Utilize as teclas WASD",87,285);
            g.drawString("para controlar a lixeira!",87, 309);
        }
        if(!jogando){
            g.drawString("Jogo pausado", 57,433);
        }
        g.drawString("Pontuação: " + pontos, 245, 433);
        g.drawString("Nível: " + level, 245, 410);
    }
}
