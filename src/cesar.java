import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class cesar {
    public HashMap<Character, Integer> charPosicao = new HashMap<Character, Integer>();
    public HashMap<Integer, Character> posicaoChar = new HashMap<Integer, Character>();
    String textoDecifrado = "";
    String textoCifrado = "";

    public void main(String[] args) {
        this.montaHashs();
        StringBuilder texto = new StringBuilder();
        try{
            File arquivo = new File("src/texto.txt");
            Scanner scan = new Scanner(arquivo);
            while (scan.hasNextLine()) {
                texto.append(scan.nextLine());
            }
            System.out.println(texto);
        }catch(FileNotFoundException e){
            System.out.println("Arquivo não encontrado");

        }
        Scanner scan = new Scanner(System.in);
        String metodo = "";
        int k = 0;
        while (true){
            System.out.println("Deseja cifrar ou decifrar (digite sair para finalizar o programa):");
            metodo = scan.next();
            if(metodo.equals("cifrar")){
                System.out.println("Qual a chave?");
                k = scan.nextInt();
                if(textoDecifrado.isEmpty()){
                    System.out.println("Decifre o texto primeiro");
                }
                else{
                    System.out.println(this.textoDecifrado);
                    this.cesar(this.textoDecifrado, k, 1);
                }
            }
            else if(metodo.equals("decifrar")){
                System.out.println("Qual a chave?");
                k = scan.nextInt();
                this.cesar(texto.toString(), k, 0);
            }
            else if(metodo.equals("sair")){
                break;
            }
            else{
                System.out.println("Digite cifrar ou decifrar.");
            }
            scan.nextLine();
        }


    }
    public void montaHashs(){
        char [] alfabeto = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
        for(int i = 0; i < alfabeto.length; i++){
            charPosicao.put(alfabeto[i], i);
            posicaoChar.put(i, alfabeto[i]);
        }
    }

    public void cesar(String texto, int k, int cifra){
        char[] decifrada = texto.toCharArray();
        for(int i = 0; i < decifrada.length; i++) {
            if (charPosicao.get(decifrada[i]) != null) {
                if(charPosicao.get(decifrada[i]) - k < 0){
                    decifrada[i] = posicaoChar.get((charPosicao.get(decifrada[i]) - k + 62) % 62);
                }
                else{
                    decifrada[i] = posicaoChar.get((Math.abs(charPosicao.get(decifrada[i]) - k)) % 62);
                }
            }
        }
        if(cifra == 0){
            this.textoDecifrado = new String(decifrada);
            System.out.println("Texto decifrado com cesar com k igual a " + k + ": " + this.textoDecifrado);
        }
        else{
            this.textoCifrado = new String(decifrada);
            System.out.println("Texto cifrado com cesar com k igual a " + k + ": " + this.textoCifrado);
        }
    }
}