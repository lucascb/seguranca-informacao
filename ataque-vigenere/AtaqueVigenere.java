import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class AtaqueVigenere {
	private static String textoCifrado;

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.err.println("java AtaqueVigenere [textoCifrado.txt]");
            System.exit(-1);
        }

        // Le o arquivo
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        StringBuffer texto = new StringBuffer();
        texto.append(br.readLine());
        br.close();

        textoCifrado = texto.toString();
        descobrirChave(Integer.parseInt(args[1]));
    }

    public static void descobrirTamanhoChave() throws Exception {
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

    public static void descobrirChave(int n) {
    	byte[] textoBytes = hexStringToByteArray(textoCifrado);
    	int[] freq = new int[256];
		byte maiorByte = 0;
		int maiorFreq = 0;
        int[] chave = new int[n];
        List<Byte> bytes = new ArrayList<Byte>();
        char[] letras = {'a', 'e', 'o', 's', 'r', 'i', 'n', 'd', 'm', 't', 'u', 'c', 'l', 'p', 'v', 'g', 'h', 'q', 'b', 'f', 'z', 'j', 'x', 'k', 'w', 'y'};

    	for (int k = 0; k < n; k++) {
    		maiorFreq = 0;
    		freq = new int[256];
    		for (int i = k; i < textoBytes.length; i += n) {
        		freq[textoBytes[i]]++;
        		if (freq[textoBytes[i]] > maiorFreq) {
        			maiorFreq = freq[textoBytes[i]];
        			maiorByte = textoBytes[i];
        		}
        	}
            bytes.add(new Byte(maiorByte, maiorFreq, k));
    		System.out.println("k:"+k+"-------");
    		System.out.println("Maior byte: " + maiorByte);
    		System.out.println("Freq: " + maiorFreq);
    		System.out.println("-----------");
    	}
    	bytes.sort(new Comparator<Byte>() {
    		public int compare(Byte b1, Byte b2) {
    			return b2.freq < b1.freq ? -1 : b2.freq == b1.freq ? 0 : 1;
    		}
    	});
    	int i = 0;
    	for (Byte b : bytes) { 	
    		chave[b.pos] = b.b ^ letras[i];
    		i++;
    	}
    	System.out.print("Chave: ");
    	for (i = 0; i < n; i++) {
    		System.out.print((char) chave[i]);
    	}
    	System.out.print("\n");
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

/*
private enum Letras {
    'a', 'e', 'o', 's', 'r', 'i', 'n', 'd', 'm', 't', 'u', 'c', 'l', 'p',
    'v', 'g', 'h', 'q', 'b', 'f', 'z', 'j', 'x', 'k', 'w', 'y'
} */
