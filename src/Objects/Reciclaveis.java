package Objects;

import java.awt.*;
import java.util.Random;

public class Reciclaveis extends Movimentavel{
    private String tipoCirculo;

    public Reciclaveis(int x, int y, int largura, int altura, int velocidadeX, int velocidadeY){
        this.setX(x);
        this.setY(y);
        this.setLargura(largura);
        this.setAltura(altura);
        this.setVelocidadeX(velocidadeX);
        super.setVelocidadeY(velocidadeY);
        Random random = new Random();
        switch (random.nextInt(4)){
            case 0 -> this.setTipoCirculo("vidro");
            case 1 -> this.setTipoCirculo("plástico");
            case 2 -> this.setTipoCirculo("metal");
            case 3 -> this.setTipoCirculo("papel");
        }
    }
    @Override
    public void draw(Graphics g) {
        switch (tipoCirculo){
            case "plástico" -> g.setColor(Color.red);
            case "vidro" -> g.setColor(Color.green);
            case "papel" -> g.setColor(Color.blue);
            case "metal" -> g.setColor(Color.yellow);
        }
        g.fillOval(getX(), getY(), getLargura(), getAltura());
    }

    @Override
    public void movimetar() {
        this.setX(getX() + getVelocidadeX());
        this.setY(getY() + getVelocidadeY());
    }

    public void setVelocidadeY(int velocidadeY){
        super.setVelocidadeY(velocidadeY);
    }

    public String getTipoCirculo() {
        return tipoCirculo;
    }

    public void setTipoCirculo(String tipoCirculo) {
        this.tipoCirculo = tipoCirculo;
    }
}
