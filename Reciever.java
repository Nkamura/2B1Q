
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JTextField;




public class Reciever {
    private RecieveWindow rw = new RecieveWindow(400,300);
    
    public String recebeMensagem() throws IOException {
        
        ServerSocket servidor = new ServerSocket(12345);
        System.out.println("Porta 12345 aberta!");

        Socket cliente = servidor.accept();
        System.out.println("Nova conexão com o cliente " +  
            cliente.getInetAddress().getHostAddress()
        );
        Scanner s = new Scanner(cliente.getInputStream()).useDelimiter("\\A");
        String message = s.hasNext() ? s.nextLine() : "";

        s.close();
        servidor.close();
        cliente.close();
        
        return message;
    }
    
    public RecieveWindow getWindow(){
        return rw;
    }
    
    public void showWindow(){
        rw.frame.setVisible(true);
    }

    public void closeWindow(){
        rw.frame.setVisible(false);
        rw.frame.dispose();
    }
    
    public void nextWindow(String msg1,String msg2,String msg3,String msg4){
       rw.button1.setVisible(false);
       rw.button2.setVisible(false);
       rw.button3.setVisible(true);
       
       rw.changeTextFields(msg1, msg2, msg3, msg4);
    }
}
