
import javax.swing.JTextField;




public class RecieveWindow extends MainWindow {
    private JTextField text1 = new JTextField(30);
    private JTextField text2 = new JTextField(30);
    private JTextField text3 = new JTextField(30);
    private JTextField text4 = new JTextField(30);
    
    public RecieveWindow(int width,int height){
        super(width,height,"Back","Connect","Close");
        
        panel.add(text1);
        panel.add(text2);
        panel.add(text3);
        panel.add(text4);
        
        text1.setVisible(false);
        text2.setVisible(false);
        text3.setVisible(false);
        text4.setVisible(false);
    }
    
    public void changeTextFields(String mensagem1, String mensagem2, String mensagem3,String mensagem4){
        text1.setText(mensagem1);
        text2.setText(mensagem2);
        text3.setText(mensagem3);
        text4.setText(mensagem4);
        
        text1.setVisible(true);
        text2.setVisible(true);
        text3.setVisible(true);
        text4.setVisible(true);
    }
   
}
