import javax.swing.*;

// nao sei chamar telas mim ser burra
public class Main extends JFrame{
     public Main(String title) {
         super("jorge");
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setBounds(0,0, 500,500);

         TelaInicial menu = new TelaInicial();
         this.setContentPane(menu);
         this.setVisible(true);
     }

     public static void main(String[] args){
        Main m = new Main("Jorge");
     }
}