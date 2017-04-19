import java.io.BufferedReader;
import java.io.FileReader;

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
    		System.out.println("k:"+k+"-------");
    		System.out.println("Maior byte: " + maiorByte);
    		System.out.println("Freq: " + maiorFreq);
    		System.out.println("-----------");
    	}

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
