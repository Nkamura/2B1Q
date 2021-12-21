//https://marquesfernandes.com/self/o-que-e-base64-para-que-serve-e-como-funciona/
//Base64 é um algoritmo de codificação (encoding) 
import java.util.Base64;

public class Criptografia64{

/** Criptografando */
public  String criptografiaBase64Encoder(String pValor) {
	return new String(Base64.getEncoder().encode(pValor.getBytes()));
}

/** Realizando o inverso */
public  String descriptografiaBase64Decode(String pValor) {
	return new String(Base64.getDecoder().decode(pValor.getBytes()));
}

}
