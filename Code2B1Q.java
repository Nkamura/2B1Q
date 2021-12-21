
import static java.lang.Math.abs;
import java.util.ArrayList;
//interface para o funcionamento das funções lambda dentro de decode2b1q
interface bitAux{
    byte execute(int x);
}

public class Code2B1Q {
//codifica uma lista de binários para uma lista "2B1Q", preenchida com valores -3,3,-1,1
    public ArrayList<Integer> encode2b1q(ArrayList<Byte> bin_data){
        
        ArrayList<Integer> coded_data  = new ArrayList();
        
        //laço de loop que acessa apenas as entradas ímpares da lista de binarios
        //assim percorrendo a lista de 2 em 2 entradas
        for(int i=0;i<bin_data.size();i++){
            if(i%2!=0){
                
                coded_data.add(1+2*bin_data.get(i));
                //inverte o valor na lista codificada se o valor anterior na lista binária for 1
                if(bin_data.get(i-1) == 1){
                    coded_data.set((int)(i/2), -coded_data.get((int)(i/2)));
                }
                //inverte o valor na lista codificada com base em seu valor anterior
                if(i>=2 && coded_data.get((int)(i/2 -1))<0){
                    coded_data.set((int)(i/2), -coded_data.get((int)(i/2)));
                }
            }
        }
        return coded_data;
    }
//decodifica uma lista "2B1Q" para uma lista de binários    
    public ArrayList<Byte> decode2b1q(ArrayList<Integer> coded_data){
    
       ArrayList<Byte> decoded_data = new ArrayList();
       //funções lambda que tratam de retornar os valores necessários para a lista
       bitAux bit_p = x -> {if(x>0) return 0; else return 1;};
       bitAux bit_i = x ->{if(abs(x)>1) return 1; else return 0;};
       bitAux invert = x->{if(x==0) return 1; else return 0;};
       
       for(int i=0;i<coded_data.size();i++){
           decoded_data.add(bit_p.execute(coded_data.get(i)));
           
           if(i>0 && coded_data.get(i-1)<0)
               decoded_data.set(i*2,invert.execute(decoded_data.get(i*2)));
           
           decoded_data.add(bit_i.execute(coded_data.get(i)));
       }
       return decoded_data;
    }
}