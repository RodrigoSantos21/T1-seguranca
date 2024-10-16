import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class cesar {
    public HashMap<Character, Integer> charPosicao = new HashMap<Character, Integer>();
    public HashMap<Integer, Character> posicaoChar = new HashMap<Integer, Character>();

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
        this.decifrarCesar(texto.toString(), k);
    }
    public void montaHashs(){
        char [] alfabeto = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
        for(int i = 0; i < alfabeto.length; i++){
            charPosicao.put(alfabeto[i], i);
            posicaoChar.put(i, alfabeto[i]);
        }
        System.out.println(charPosicao.get('a') + " " + charPosicao.get('r'));
    }
    public void decifrarCesar(String texto, int k){
        char[] decifrada = texto.toCharArray();
        for(int i = 0; i < decifrada.length; i++){
            if(charPosicao.get(decifrada[i]) != null){
                if(posicaoChar.get(charPosicao.get(decifrada[i]) + k) == null){
                    decifrada[i] = posicaoChar.get(charPosicao.get(decifrada[i]) + k - 62);
                }
                else {
                    decifrada[i] = posicaoChar.get(charPosicao.get(decifrada[i]) + k);
                }
            }
        }
        System.out.println(decifrada);
    }
}