
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;




public class TwoBOneQTransmitter {
    private Sender sender;
    private Reciever reciever;
    private MainWindow mainWindow;
    private Code2B1Q lineEncoder = new Code2B1Q();
    private Criptografia64 encrypt;
    private Converter conv;
    private String msgCript,msg2B1Q,msgBin,mensagem;
    
    public TwoBOneQTransmitter(){
        mainWindow = new MainWindow(400,300,"Send","Recieve"," ");
        sender = new Sender();
        reciever = new Reciever();
        encrypt = new Criptografia64();
        conv = new Converter();
        showWindow();
        setupButtons();   
    }
    
    public void showWindow(){
        mainWindow.frame.setVisible(true);
    }
    public void closeWindow(){
        mainWindow.frame.setVisible(false);
        mainWindow.frame.dispose();
    }
    public void setupButtons(){
         //gerenciador de eventos para botao "send"        
        mainWindow.button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
              closeWindow();
              sender.showWindow();
            }
        });
       
        //gerenciador de eventos para os botoes na janela do remetente
        sender.getWindow().button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
              sender.closeWindow();
              showWindow();
            }
        });
        sender.getWindow().button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ArrayList<Integer> coded_data = new ArrayList();
                ArrayList<Integer> teste = new ArrayList();
                msgCript = encrypt.criptografiaBase64Encoder(sender.getWindow().getTextContents());
                msgBin = conv.text2binary(msgCript);
                coded_data = lineEncoder.encode2b1q(convertString2Byte(msgBin));
                msg2B1Q = coded_data.toString();
                Grafico graph = new Grafico(coded_data);
                graph.setVisible(true);
                try {
                    sender.enviaMensagem(coded_data);
                } catch (IOException ex) {
                    Logger.getLogger(TwoBOneQTransmitter.class.getName()).log(Level.SEVERE, null, ex);
                }
                sender.nextWindow(msgCript,msgBin,msg2B1Q);
            }
        });
        sender.getWindow().button3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                 sender.closeWindow();
                 System.exit(1);
            }
        });
        
        //gerenciador de eventos para botao "recieve"  
        mainWindow.button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                closeWindow();
                reciever.showWindow();
           }
        });
        
        //gerenciador de eventos para os botoes na janela do destinatário
        reciever.getWindow().button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                reciever.closeWindow();
                showWindow();
            }
        });
        reciever.getWindow().button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               ArrayList<Integer> coded_data = new ArrayList();
                try {
                    coded_data = convertString2Integer(reciever.recebeMensagem());
                } catch (IOException ex) {
                    Logger.getLogger(TwoBOneQTransmitter.class.getName()).log(Level.SEVERE, null, ex);
                }
                msg2B1Q = coded_data.toString();
                Grafico graph = new Grafico(coded_data);
                graph.setVisible(true);
                msgBin = reasembleString(lineEncoder.decode2b1q(coded_data).toString());
                msgCript = conv.binary2text(msgBin);
                mensagem = encrypt.descriptografiaBase64Decode(msgCript);
                reciever.nextWindow(msgCript,msgBin,msg2B1Q,mensagem);
            }
        });
         reciever.getWindow().button3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                reciever.closeWindow();
                System.exit(1);
            }
        });

    }
    public ArrayList<Byte> convertString2Byte(String str){
        ArrayList<Byte> bin_data =  new ArrayList();
        char[] c = str.toCharArray();
        for(int i=0;i<c.length;i++){
            bin_data.add((byte) (c[i]-48));
        }
        return bin_data;
    }
    public ArrayList<Integer> convertString2Integer(String str){
        String[] string = str.replaceAll("\\[", "").replaceAll("]", "").replaceAll(" ","").split(",");
        ArrayList<Integer> int_data =  new ArrayList();
        int[] aux = new int[string.length]; 
        for(int i=0;i<aux.length;i++){
            if(string[i].equals("-")){
                aux[i] = Integer.valueOf(string[i+1])*(-1);
                int_data.add(aux[i]);
            }
            else{
                aux[i] = Integer.valueOf(string[i]);
                int_data.add(aux[i]);
            }
        }
        return int_data;
    }
    public String reasembleString(String str){
        String string = str.replaceAll("\\[", "").replaceAll("]", "").replaceAll(" ","").replaceAll(",","");
        return string;
    }
}
