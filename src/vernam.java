import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class vernam {
    public HashMap<Character, Integer> charPosicao = new HashMap<Character, Integer>();
    public HashMap<Integer, Character> posicaoChar = new HashMap<Integer, Character>();
    char [] alfabeto = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
    String chave = "";
    String textoCifrado = "";
    String textoDescifrado = "";
    public void main(String[] args){
        StringBuilder texto = new StringBuilder();
        try{
            File arquivo = new File("C:/Users/rodri/Downloads/T1-seguranca/src/texto-aberto.txt");
            Scanner scan = new Scanner(arquivo);
            while (scan.hasNextLine()) {
                texto.append(scan.nextLine());
            }
        }catch(FileNotFoundException e){
            System.out.println("Arquivo não encontrado");
        }
        this.montaHashs();
        this.montaChave(texto.length());
        System.out.println("Chave: " + chave);
        this.textoCifrado = this.cifrar(texto.toString(), this.chave);
        System.out.println("Texto cifrado: " + textoCifrado);
        this.textoDescifrado = this.descifrar(textoCifrado, chave);
        System.out.println("Texto descifrado: " + textoDescifrado);
    }

    public String descifrar(String textoCifrado, String chave){
        byte[] textoBytes = textoCifrado.getBytes();
        byte[] chaveBytes = chave.getBytes();
        byte[] cifrado = new byte[textoBytes.length];
        for (int i = 0; i < cifrado.length; i++) {
            cifrado[i] = (byte)(chaveBytes[i]^textoBytes[i]);
        }
        return new String(cifrado);
    }

    public String cifrar(String texto, String chave){
        if(texto.length() != chave.length()){
            System.out.println("O texto e a chave devem ter tamanhos iguais.");
            return "";
        }
        byte[] textoBytes = texto.getBytes();
        byte[] chaveBytes = chave.getBytes();
        byte[] cifrado = new byte[textoBytes.length];
        for (int i = 0; i < cifrado.length; i++) {
            cifrado[i] = (byte)(chaveBytes[i]^textoBytes[i]);
        }
        return new String(cifrado);
    }

    public void montaChave(int tamanho){
        if (tamanho < 0){
            System.out.println("Texto com tamanho igual a zero.");
            return;
        }
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            this.chave += alfabeto[random.nextInt(61)];
        }
    }
    public void montaHashs(){
        for(int i = 0; i < this.alfabeto.length; i++){
            charPosicao.put(this.alfabeto[i], i);
            posicaoChar.put(i, this.alfabeto[i]);
        }
    }

}
