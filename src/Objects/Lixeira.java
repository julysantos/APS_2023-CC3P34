package Objects;
import java.awt.*;

public class Lixeira extends Desenhavel {

    public Lixeira(int x, int y, int largura, int altura){
        this.setX(x);
        this.setY(y);
        this.setLargura(largura);
        this.setAltura(altura);
        this.estado="comum";
    }
    private String estado;


    @Override
    public void draw(Graphics g) {
        switch (estado) {
            case "metal" -> g.setColor(Color.yellow);
            case "vidro" -> g.setColor(Color.green);
            case "plástico" -> g.setColor(Color.red);
            case "papel" -> g.setColor(Color.blue);
            case "comum" -> g.setColor(Color.black);
        }
        g.fillRect(getX(), getY(), getLargura(), getAltura());

    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean contato(Reciclaveis reciclaveis){
        return reciclaveis.getX() + 10 < this.x + largura && reciclaveis.getX() + reciclaveis.getLargura() > this.x;
    }
}
