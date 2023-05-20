import javax.swing.*;

// nao sei chamar telas mim ser burra
public class Main extends JFrame{
     public Main(String title) {
         super("jorge");
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setContentPane(new TelaJogo("jorge"));
         this.pack();
     }

     public static void main(String[] args){

     }
}