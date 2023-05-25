package Objects;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Lixeira extends Desenhavel {

    private final BufferedImage plasticBinImg;
    private final BufferedImage commonBinImg;
    private final BufferedImage glassBinImg;
    private final BufferedImage paperBinImg;
    private final BufferedImage metalBinImg;




    public Lixeira(int x, int y, int largura, int altura){
        this.setX(x);
        this.setY(y);
        this.setLargura(largura);
        this.setAltura(altura);
        this.estado="comum";
        try{
            String imagesPath = "src/Images";

            String paperBinName = "paper_bin.png";
            paperBinImg= ImageIO.read(new File(imagesPath, paperBinName));

            String plasticBinName = "plastic_bin.png";
            plasticBinImg= ImageIO.read(new File(imagesPath, plasticBinName));

            String metalBinName = "metal_bin.png";
            metalBinImg= ImageIO.read(new File(imagesPath, metalBinName));

            String glassBinName = "glass_bin.png";
            glassBinImg= ImageIO.read(new File(imagesPath, glassBinName));

            String commonBinName = "common_bin.png";
            commonBinImg= ImageIO.read(new File(imagesPath, commonBinName));
        }catch (IOException e){
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    private String estado;


    @Override
    public void draw(Graphics g) {
        BufferedImage spriteAtual;
        switch (estado) {
            case "metal" -> spriteAtual=metalBinImg;
            case "vidro" -> spriteAtual=glassBinImg;
            case "plástico" -> spriteAtual=plasticBinImg;
            case "papel" -> spriteAtual=paperBinImg;
            case "comum" -> spriteAtual=commonBinImg;
            default ->  throw new IllegalStateException();
        }
        g.drawImage(spriteAtual,getX(), getY(),getLargura(),getAltura(), null);
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean contato(Reciclavel reciclavel){
        return reciclavel.getX() + 10 < this.x + largura && reciclavel.getX() + reciclavel.getLargura() > this.x;
    }
}
