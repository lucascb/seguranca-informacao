import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;

public class AtaqueVigenere {

    public static void main(String[] args) throws Exception {

        if (args.length < 2) {
            System.err.println("java AtaqueVigenere [tam|chave] [textoCifrado.txt] [t]");
            System.exit(-1);
        }

        // Le o arquivo
        BufferedReader br = new BufferedReader(new FileReader(args[1]));
        StringBuffer texto = new StringBuffer();
        texto.append(br.readLine());
        br.close();

        // tam para descobrir o tamanho da chave
		if (args[0].equals("tam")) {
			descobrirTamanhoChave(texto.toString());
        // chave para descobrir a chave
		} else {
			descobrirChave(texto.toString(), Integer.parseInt(args[2]));
		}

    }

    public static void descobrirTamanhoChave(String textoCifrado) {
    	int[] freq = new int[256];
    	byte[] textoBytes = hexStringToByteArray(textoCifrado);
    	double[] q = new double[256];
      	double s = 0.0;

    	//for(byte b : textoBytes) System.out.println("byte: "+b);
    	int m = textoBytes.length;

    	for (int n = 1; n <= 50; n++) {
	      	double total = Math.ceil(m / (n * 1.0));
	      	//System.out.println("n: " + n);
	      	//System.out.println("Total: " + total);
	      	freq = new int[256];

			for (int i = 0; i < textoBytes.length; i += n) {
				//System.out.println(textoBytes[i]);
	      		freq[textoBytes[i]]++;
	        }

			q = new double[256];
	        s = 0;
	        for (int i = 0; i < freq.length; i++) {
	        	if (freq[i] != 0) {
	      			q[i] = freq[i] / total;
	      			s += Math.pow(q[i], 2);
	      		}
	      	}
	      	System.out.println(s);
    	}

    }

    public static void descobrirChave(String textoCifrado, int n) {
        byte[] textoBytes = hexStringToByteArray(textoCifrado);
        char[] letras = {
            'a', 'e', 'o', 's', 'r', 'i', 'n', 'd', 'm', 't', 'u', 'c', 'l',
            'p', 'v', 'g', 'h', 'q', 'b', 'f', 'z', 'j', 'x', 'k', 'w', 'y', ' '
        };
        double maiorX = 0;
        char maiorLetra = 'a';
        char[] chave = new char[n];

        for (int i = 0; i < n; i++) {
            maiorX = 0;
            for (char letra : letras) {
                char[] q = new char[256];
                int cont = 0;
                for (int j = i; j < textoBytes.length; j += n) {
                    int l = textoBytes[j] ^ letra;
                    q[l]++;
                    cont++;
                }
                double x = 0;
                for (int j = 0; j < q.length; j++) {
                    if (q[j] != 0) {
                        x += p((char) j) * (q[j] / (cont * 1.0));
                    }
                }
                //System.out.println(x);
                if (x > maiorX) {
                    maiorLetra = letra;
                    maiorX = x;
                }
            }
            chave[i] = maiorLetra;
        }
        String ch = new String(chave);
        System.out.println(ch);
    }

    public static double p(char ch) {
        switch (ch) {
            case 'a':
                return 0.14634;
            case 'b':
                return 0.01043;
            case 'c':
                return 0.03882;
            case 'd':
                return 0.04992;
            case 'e':
                return 0.12570;
            case 'f':
                return 0.01023;
            case 'g':
                return 0.01303;
            case 'h':
                return 0.0781;
            case 'i':
                return 0.06186;
            case 'j':
                return 0.0397;
            case 'k':
                return 0.00015;
            case 'l':
                return 0.02779;
            case 'm':
                return 0.04738;
            case 'n':
                return 0.04446;
            case 'o':
                return 0.09735;
            case 'p':
                return 0.02523;
            case 'q':
                return 0.01204;
            case 'r':
                return 0.06530;
            case 's':
                return 0.06805;
            case 't':
                return 0.04336;
            case 'u':
                return 0.03639;
            case 'v':
                return 0.01575;
            case 'x':
                return 0.00253;
            case 'w':
                return 0.00037;
            case 'y':
                return 0.00006;
            case 'z':
                return 0.00470;
            case ' ':
                return 0.15384;
        }

        return 0;
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

}
