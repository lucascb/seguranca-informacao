import java.io.BufferedReader;
import java.io.FileReader;

public class AtaqueVigenere {
	private String textoCifrado;
	
	public AtaqueVigenere(String textoCifrado) {
		this.textoCifrado = textoCifrado;
	}
 
    public static void main(String[] args) throws Exception {
        if (args.length < 1) { 
            System.err.println("java AtaqueVigenere [textoCifrado.txt]");
            System.exit(-1);
        }
        
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        StringBuffer texto = new StringBuffer();
        texto.append(br.readLine());
        br.close();
        
        new AtaqueVigenere(texto.toString()).quebrarCifra();
    }
    
    public String quebrarCifra() throws Exception {
    	int[] freq = new int[256];
    	byte[] textoBytes = hexStringToByteArray(textoCifrado);
    	double[] q = new double[256];
    	
    	int m = textoBytes.length;
    	
    	for (int n = 1; n <= m / 2; n++) {
        	int total = Math.floorDiv(m, n);
        	System.out.println("n: " + n);
        	//System.out.println("Total: " + total);
        	freq = new int[256];
        	
    		for (int i = 0; i < textoBytes.length; i += n) {
        		freq[textoBytes[i]]++;
        	}
        	
    		q = new double[256];
        	double s = 0;
        	for (int i = 0; i < freq.length; i++) {
        		if (freq[i] != 0) {
        			q[i] = freq[i] / (total * 1.0);
        			s += Math.pow(q[i], 2);
        		}
        	}
        	System.out.println("s: " + s);
    	}
    	
    	
    	return "";
    }
    
    public byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
    
}