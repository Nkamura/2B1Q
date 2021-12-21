


import java.awt.Dimension;
import javax.swing.JTextField;




public class SendWindow extends MainWindow {
    private JTextField text1 = new JTextField(30);
    private JTextField text2 = new JTextField(30);
    private JTextField text3 = new JTextField(30);
    
    public SendWindow(int width,int height){
        super(width,height,"Back","Connect","Close");
        panel.add(text1);
        panel.add(text2);
        panel.add(text3);
        
        text2.setVisible(false);
        text3.setVisible(false);
    }

    public String getTextContents(){
        return text1.getText();
    }
    
    public void changeTextFields(String mensagem1, String mensagem2, String mensagem3){
        text1.setText(mensagem1);
        text2.setText(mensagem2);
        text3.setText(mensagem3);
        
        text2.setVisible(true);
        text3.setVisible(true);
    }
    
    
    public void resizeWindow(int widht, int height){
       frame.setPreferredSize(new Dimension(1000,800));
       frame.setMaximumSize(new Dimension(1000,800));
       frame.setMinimumSize(new Dimension(1000,800));
    }
}
