package Objects;

import java.awt.*;

public abstract class Movimentavel extends Desenhavel {

    protected int velocidadeX = 0;
    protected int velocidadeY = 0;

    public abstract void draw(Graphics g);

    public abstract void movimetar();

    public int getVelocidadeX() {
        return velocidadeX;
    }

    public void setVelocidadeX(int velocidadeX) {
        this.velocidadeX= velocidadeX;
    }

    public int getVelocidadeY() {
        return velocidadeY;
    }

    public void setVelocidadeY(int velocidadeY) {
        this.velocidadeY = velocidadeY;
    }
}
