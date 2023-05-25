package Objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Reciclavel extends Movimentavel{
    private String tipoReciclavel;

    private BufferedImage imagemSelecionada;

    private final String[] spritesPaper = {"cardboard_box.png"};
    private final String[] spritesPlastic = {"bottle1.png"};
    private final String[] spritesMetal = {"can.png"};
    private final String[] spritesGlass = {"glass_jar.png","glass_jar2.jpg"};



    public Reciclavel(int x, int y, int largura, int altura, int velocidadeX, int velocidadeY){
        this.setX(x);
        this.setY(y);
        this.setLargura(largura);
        this.setAltura(altura);
        this.setVelocidadeX(velocidadeX);
        super.setVelocidadeY(velocidadeY);
        Random random = new Random();
        try {
            String imagesPath = "src/Images";

            switch (random.nextInt(4)) {
                case 0 -> {
                    this.setTipoReciclavel("vidro");
                    int tamanhoSprite = spritesGlass.length;
                    imagemSelecionada =
                            ImageIO.read(
                                new File(
                                        imagesPath,
                                        spritesGlass[random.nextInt(tamanhoSprite)]
                                )
                            );
                }
                case 1 -> {
                    this.setTipoReciclavel("plástico");
                    int tamanhoSprite = spritesPlastic.length;
                    imagemSelecionada =
                            ImageIO.read(
                                    new File(
                                            imagesPath,
                                            spritesPlastic[random.nextInt(tamanhoSprite)]
                                    )
                            );
                }
                case 2 -> {
                    this.setTipoReciclavel("metal");
                    int tamanhoSprite = spritesMetal.length;
                    imagemSelecionada =
                            ImageIO.read(
                                    new File(
                                            imagesPath,
                                            spritesMetal[random.nextInt(tamanhoSprite)]
                                    )
                            );
                }
                case 3 -> {
                    this.setTipoReciclavel("papel");
                    int tamanhoSprite = spritesPaper.length;
                    imagemSelecionada =
                            ImageIO.read(
                                    new File(
                                            imagesPath,
                                            spritesPaper[random.nextInt(tamanhoSprite)]
                                    )
                            );
                }

            }
        }catch (IOException e){
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }
    @Override
    public void draw(Graphics g) {
        g.drawImage(imagemSelecionada,getX(), 65,getLargura(),getAltura(),null);
    }

    @Override
    public void movimetar() {
        this.setX(getX() + getVelocidadeX());
        this.setY(getY() + getVelocidadeY());
    }

    public void setVelocidadeY(int velocidadeY){
        super.setVelocidadeY(velocidadeY);
    }

    public String getTipoReciclavel() {
        return tipoReciclavel;
    }

    public void setTipoReciclavel(String tipoReciclavel) {
        this.tipoReciclavel = tipoReciclavel;
    }
}
