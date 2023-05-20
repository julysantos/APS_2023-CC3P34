import Objects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class TelaJogo extends JFrame implements Runnable {

    public TelaJogo(String title) {
        super(title);
        setSize(500, 500);
    }

    private boolean jogando = true;


    ArrayList<Reciclaveis> reciclaveis = new ArrayList<>();
    private int[] pontos = new int[]{0};
    private int level = 1;
    private int combo = 0;
    private Lixeira lixeira = new Lixeira(60, 60, 60, 60);

    // JPanel renderizando o jogo
    JPanel telaJogo = new JPanel() {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            lixeira.draw(g);
            for (Reciclaveis r : reciclaveis) {
                r.draw(g);
            }
            setVisible(true);
        }
    };


    public void erroJogo() {
        for (Reciclaveis c : reciclaveis) {
            combo = 0;
            System.out.println("pontuação: " + pontos[0]);
            reciclaveis.add(new Reciclaveis(500, 65, 50, 50, c.getVelocidadeX(), 0));
            reciclaveis.remove(c);
        }
    }

    // metódo gerando a logica do jogo
    public void logicaJogo() {
        while (getRodando()) {

            //nao apagar
            System.out.print("");
            while (getJogando()) {
                telaJogo.repaint();

                // movimenta os reciclaveis
                for (Reciclaveis c : reciclaveis) {
                    c.movimetar();

                    // verifica o contato dos reciclaveis com a lixeira (mecanica acerto/erro)
                    if (lixeira.contato(c)) { // acerto
                        if (c.getTipoCirculo().equals(lixeira.getEstado())) {
                            pontos[0]++;
                            combo++;
                            System.out.println("pontuação: " + pontos[0]);
                            reciclaveis.remove(c);
                            reciclaveis.add(new Reciclaveis(500, 65, 50, 50, c.getVelocidadeX(), 0));
                            // aumenta o nível e a velocidade do jogo
                            for (Reciclaveis co : reciclaveis) {
                                if (combo % 5 == 0 && level < 10) {
                                    level++;
                                    co.setVelocidadeX((-5) - pontos[0]);
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
                System.out.println("" + getJogando());
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
        return true;
    }

    @Override
    public void run() {
        this.getContentPane().add(telaJogo);
        this.setVisible(true);
        this.add(keyListener);
    }
}
