import javax.swing.JFrame;

public class Main extends JFrame{
     public Main(String title) {
         super("jorge");
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setBounds(500,120, 500,500);

         TelaInicial menu = new TelaInicial();
         this.setContentPane(menu);
         this.setVisible(true);
         this.setResizable(false);
         this.setFocusable(true);
         this.requestFocus();
     }

     public static void main(String[] args){
        Main m = new Main("Jorge");
     }
}