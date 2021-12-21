




import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


//Temporáriamente aqui para testas as funcionalidades
//Será movido para outra classe caso seja necessário
public class MainWindow {
    protected JButton button1;
    protected JButton button2;
    protected JButton button3;
    protected JButton button4;
    protected JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    
    public MainWindow(int width,int height,String str1,String str2,String str3){
        button1 = new JButton(str1);
        button2 = new JButton(str2);
        button3 = new JButton(str3);
        //criando o tamanho da janela 
        frame.setPreferredSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));
        

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        
        button3.setVisible(false);
        
        frame.add(panel);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
    }
}
