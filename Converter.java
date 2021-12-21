//bib do StandardCharsets  https://docs.oracle.com/javase/10/docs/api/java/nio/charset/StandardCharsets.html
import java.nio.charset.StandardCharsets;

public class Converter{


//ASCII 2 Binary/
public  String text2binary(String text){
    	//Pega a informação de entrada e coloca on vetor de bytes - tipo primitivo byte 8 bits
	byte[] bytes = text.getBytes(StandardCharsets.UTF_16LE);
    	StringBuilder binary = new StringBuilder();

	//For para preencher
	for (byte aux : bytes) {
		int val = aux;
        	for (int i = 0; i < 8; i++){
			//https://developer.mozilla.org/en-US/docs/Web/API/Element/append
			binary.append((val & 128) == 0 ? 0 : 1);
            		//Operador bit a bit (<<) deslocamento à esquerda
			val <<= 1;
        	}
	}
	//To string: https://www.guj.com.br/t/sobre-o-metodo-tostring/78518/3
	return binary.toString();
}



//Binary 2 ASCII/
public  String binary2text(String binaryString){
	//Tirando todos os espaços que podem existir
	String binary = binaryString.replaceAll(" ", "").replaceAll("\\[","").replaceAll(",","").replaceAll("]","");
	String binaryPer8Bits;
    	byte[] byteData;
	//1(um) byte é composto de 8(oito) bits;
    	byteData = new byte[binary.length() / 8];

    	for (int i = 0; i < binary.length() / 8; i++){
		//https://www.javatpoint.com/java-string-substring
		//.Substring para retornar só parte da string, no caso 8 bits
		//Parametros (começo,fim)
        	binaryPer8Bits = binary.substring(i * 8, (i + 1) * 8);
        	
		//https://developer.mozilla.org/pt-BR/docs/Web/JavaScript/Reference/Global_Objects/parseInt
		//parseInt() analisa um argumento string e retorna um inteiro na base especificada no caso 2(Binário)
        	Integer integer = Integer.parseInt(binaryPer8Bits, 2);
        	
		//Fazendo virar byte -> Juntando os valores binários 
		byteData[i] = integer.byteValue();        
	}
	
	//Retorna o resultado fazendo com que seja utf16 ao invés do 8
	return new String(byteData, StandardCharsets.UTF_16LE);
    	//return new String(byteData);
}

}
