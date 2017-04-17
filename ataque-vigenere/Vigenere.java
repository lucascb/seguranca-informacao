import java.io.*;
import java.util.Scanner;

public class Vigenere {

    static String encripta(String texto, String chave) throws Exception {

        byte[] bytes = texto.getBytes();

        int k = 0;
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] ^= chave.charAt(k);
            k = (++k) % chave.length();
        }

        return Util.byteArrayToHexString(bytes);
    }

    static String decripta(String textoCifrado, String chave) throws Exception {

        byte[] bytes = Util.hexStringToByteArray(textoCifrado);

        int k = 0;
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] ^= chave.charAt(k);
            k = (++k) % chave.length();
        }

        return new String(bytes, "ASCII");
    }


    public static void main(String args[]) throws Exception {

      if (args.length < 2) { 
        System.err.println("java Vigenere [encripta|decripta] [textoemclaro.txt ou textocifrado.txt] [chave.txt]");
        System.exit(-1);
      }

        if (args[0].startsWith("enc") ) {

           BufferedReader br = new BufferedReader(new FileReader(args[1]));
           StringBuffer textoEmClaro = new StringBuffer();
           while (br.ready()) {
            textoEmClaro.append(br.readLine()+"\n");
           }
           br.close();

           //System.out.println(textoEmClaro.toString().length());
           BufferedReader cFile = new BufferedReader(new FileReader(args[2])); 
           String chave = cFile.readLine();

           String textoCifrado = encripta(textoEmClaro.toString(), chave);
           System.out.println(textoCifrado);

        } else if (args[0].startsWith("dec")) {

           BufferedReader br = new BufferedReader(new FileReader(args[1]));
           StringBuffer textoCifrado = new StringBuffer();
           textoCifrado.append(br.readLine());
           br.close();

           BufferedReader cFile = new BufferedReader(new FileReader(args[2])); 
           String chave = cFile.readLine();

           String textoEmClaro = decripta(textoCifrado.toString(), chave);
           System.out.print(textoEmClaro);
        }
    }

}


class Util {

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }


    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String byteArrayToHexString(byte[] bytes) throws Exception {

        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
