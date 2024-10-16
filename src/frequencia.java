import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class frequencia {
    public HashMap<Character, Integer> charPosicao = new HashMap<Character, Integer>();
    public HashMap<Integer, Character> posicaoChar = new HashMap<Integer, Character>();
    public LinkedHashMap<Character, Double> proporcaoPortugues = new LinkedHashMap<Character, Double>();
    public LinkedHashMap<Character, Double> proporcaoPortuguesOrd = new LinkedHashMap<Character, Double>();
    public LinkedHashMap<Character, Double> proporcaoTexto = new LinkedHashMap<Character, Double>();
    public LinkedHashMap<Character, Double> proporcaoTextoOrd = new LinkedHashMap<Character, Double>();
    public LinkedHashMap<Integer, Integer> aparicaoChave = new LinkedHashMap<Integer, Integer>();
    public LinkedHashMap<Integer, Integer> aparicaoChaveOrd = new LinkedHashMap<Integer, Integer>();

    public void main(String[] args){
        StringBuilder texto = new StringBuilder();
        try{
            File arquivo = new File("C:/Users/rodri/Downloads/Trabalho 1- seguranca/src/texto.txt");
            Scanner scan = new Scanner(arquivo);
            while (scan.hasNextLine()) {
                texto.append(scan.nextLine());
            }
        }catch(FileNotFoundException e){
            System.out.println("Arquivo não encontrado");

        }
        this.montaHashs();
        this.montaProporcaoTexto(texto.toString());
        this.decifrarCesar(texto.toString(), this.frequencia());
    }

    public int frequencia(){
        List<Character> letrasTextoOrd = new ArrayList<Character>(proporcaoTextoOrd.keySet());
        List<Character> letrasPortOrd = new ArrayList<Character>(proporcaoPortuguesOrd.keySet());
        Collections.reverse(letrasPortOrd);
        Collections.reverse(letrasTextoOrd);
        int minimo = Math.min(letrasPortOrd.size(), letrasTextoOrd.size());
        int diferencaChave = 0;
        for (int i = 0; i < minimo; i++) {
            if(charPosicao.get(letrasTextoOrd.get(i)) > charPosicao.get(letrasPortOrd.get(i))){
                diferencaChave = 62 - Math.abs(charPosicao.get(letrasTextoOrd.get(i)) - charPosicao.get(letrasPortOrd.get(i)));
            }
            else{
                diferencaChave = Math.abs(charPosicao.get(letrasTextoOrd.get(i)) - charPosicao.get(letrasPortOrd.get(i)));
            }

            if(aparicaoChave.get(diferencaChave) != null){
                aparicaoChave.replace(diferencaChave, aparicaoChave.get(diferencaChave) + 1);
            }
            else{
                aparicaoChave.put(diferencaChave, 1);
            }
        }
        aparicaoChave.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEachOrdered(entry-> aparicaoChaveOrd.put(entry.getKey(), entry.getValue()));
        return aparicaoChaveOrd.lastEntry().getKey();
    }

    public void decifrarCesar(String texto, int k){
        char[] decifrada = texto.toCharArray();
        for(int i = 0; i < decifrada.length; i++){
            if(charPosicao.get(decifrada[i]) != null){
                decifrada[i] = posicaoChar.get((charPosicao.get(decifrada[i]) + k)%62);
            }
        }
        System.out.println(decifrada);
    }
    public void montaProporcaoTexto(String texto){
        char [] proporcao = texto.toCharArray();
        int totalLetras = 0;
        for (char c : proporcao) {
            if (charPosicao.get(c) != null) {
                if(proporcaoTexto.get(c) != null){
                    proporcaoTexto.replace(c, proporcaoTexto.get(c)+1);
                }
                else{
                    proporcaoTexto.put(c, 1.0);
                }
                totalLetras++;
            }
        }

        Set<Character> letras = proporcaoTexto.keySet();
        for(Character letra : letras){
            proporcaoTexto.replace(letra, (proporcaoTexto.get(letra)/totalLetras)*100);
        }
        proporcaoTexto.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEachOrdered(entry-> proporcaoTextoOrd.put(entry.getKey(), entry.getValue()));
    }
    public void montaHashs(){
        char [] alfabeto = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
        for(int i = 0; i < alfabeto.length; i++){
            charPosicao.put(alfabeto[i], i);
            posicaoChar.put(i, alfabeto[i]);
        }
        proporcaoPortugues.put('a', 14.63);
        proporcaoPortugues.put('b', 1.04);
        proporcaoPortugues.put('c', 3.88);
        proporcaoPortugues.put('d', 4.99);
        proporcaoPortugues.put('e', 12.57);
        proporcaoPortugues.put('f', 1.02);
        proporcaoPortugues.put('g', 1.30);
        proporcaoPortugues.put('h', 1.28);
        proporcaoPortugues.put('i', 6.18);
        proporcaoPortugues.put('j', 0.40);
        proporcaoPortugues.put('k', 0.02);
        proporcaoPortugues.put('l', 2.78);
        proporcaoPortugues.put('m', 4.74);
        proporcaoPortugues.put('n', 5.05);
        proporcaoPortugues.put('o', 10.73);
        proporcaoPortugues.put('p', 2.52);
        proporcaoPortugues.put('q', 1.20);
        proporcaoPortugues.put('r', 6.53);
        proporcaoPortugues.put('s', 7.81);
        proporcaoPortugues.put('t', 4.34);
        proporcaoPortugues.put('u', 4.63);
        proporcaoPortugues.put('v', 1.67);
        proporcaoPortugues.put('w', 0.01);
        proporcaoPortugues.put('x', 0.21);
        proporcaoPortugues.put('y', 0.01);
        proporcaoPortugues.put('z', 0.47);
        proporcaoPortugues.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEachOrdered(entry-> proporcaoPortuguesOrd.put(entry.getKey(), entry.getValue()));
    }
}
