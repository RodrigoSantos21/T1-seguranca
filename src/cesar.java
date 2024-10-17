import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class cesar {
    public HashMap<Character, Integer> charPosicao = new HashMap<Character, Integer>();
    public HashMap<Integer, Character> posicaoChar = new HashMap<Integer, Character>();
    String textoDescifrado = "";
    String textoCifrado = "";

    public void main(String[] args) {
        this.montaHashs();
        StringBuilder texto = new StringBuilder();
        try{
            File arquivo = new File("C:/Users/rodri/Downloads/Trabalho 1- seguranca/src/texto.txt");
            Scanner scan = new Scanner(arquivo);
            while (scan.hasNextLine()) {
                texto.append(scan.nextLine());
            }
            System.out.println(texto);
        }catch(FileNotFoundException e){
            System.out.println("Arquivo não encontrado");

        }
        int k = 45;
        this.cesar(texto.toString(), k, 0);
        this.cesar(this.textoDescifrado, 62-k, 1);
    }
    public void montaHashs(){
        char [] alfabeto = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
        for(int i = 0; i < alfabeto.length; i++){
            charPosicao.put(alfabeto[i], i);
            posicaoChar.put(i, alfabeto[i]);
        }
        System.out.println(charPosicao.get('P') + " " + charPosicao.get('g'));
    }

    public void cesar(String texto, int k, int cifra){
        char[] decifrada = texto.toCharArray();
        for(int i = 0; i < decifrada.length; i++) {
            if (charPosicao.get(decifrada[i]) != null) {
                    decifrada[i] = posicaoChar.get((Math.abs(charPosicao.get(decifrada[i]) + k)) % 62);
            }
        }
        if(cifra == 0){
            this.textoDescifrado = new String(decifrada);
            System.out.println("Texto descifrado com cesar com k igual a " + k + ": " + this.textoDescifrado);
        }
        else{
            this.textoCifrado = new String(decifrada);
            System.out.println("Texto cifrado com cesar com k igual a " + k + ": " + this.textoCifrado);
        }
    }
}