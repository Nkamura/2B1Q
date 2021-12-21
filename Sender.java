
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class Sender {
    private SendWindow sw = new  SendWindow(400,300);
    
    public void enviaMensagem(ArrayList<Integer> mensagem) throws UnknownHostException, IOException {
        Socket cliente = new Socket("25.82.86.132", 12345);
        System.out.println("O cliente se conectou ao servidor!");

        PrintStream saida = new PrintStream(cliente.getOutputStream());
        saida.println(mensagem);

        saida.close();
        cliente.close();
    }
    
    public SendWindow getWindow(){
        return sw;
    }
    
    public void showWindow(){
        sw.frame.setVisible(true);
    }
    
    public void closeWindow(){
        sw.frame.setVisible(false);
        sw.frame.dispose();
    }
    
    public void nextWindow(String msg1, String msg2, String msg3){
       sw.button1.setVisible(false);
       sw.button2.setVisible(false);
       sw.button3.setVisible(true);
       
       sw.changeTextFields(msg1, msg2, msg3);
    }
     
}
