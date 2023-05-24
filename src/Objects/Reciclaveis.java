package Objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Reciclaveis extends Movimentavel{
    private String tipoReciclavel;

    public Reciclaveis(int x, int y, int largura, int altura, int velocidadeX, int velocidadeY){
        this.setX(x);
        this.setY(y);
        this.setLargura(largura);
        this.setAltura(altura);
        this.setVelocidadeX(velocidadeX);
        super.setVelocidadeY(velocidadeY);
        Random random = new Random();
        switch (random.nextInt(4)){
            case 0 -> this.setTipoReciclavel("vidro");
            case 1 -> this.setTipoReciclavel("plástico");
            case 2 -> this.setTipoReciclavel("metal");
            case 3 -> this.setTipoReciclavel("papel");
        }

    }
    @Override
    public void draw(Graphics g) {
        /*BufferedImage sprite;
        String tipoPlastico = null;
        String tipoMetal = null;
        String tipoVidro = null;
        String tipoPapel = null;*/

        switch (tipoReciclavel){
            case "plástico" -> g.setColor(Color.red);
            case "vidro" -> g.setColor(Color.green);
            case "papel" -> g.setColor(Color.blue);
            case "metal" -> g.setColor(Color.yellow);
        }
        g.fillOval(getX(), 65,50,50);
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
