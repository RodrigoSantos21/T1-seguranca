# Trabalho 1 - Segurança de Redes e Sistemas

# Descrição
Repositório para o trabalho 1 da disciplina de Segurança de Redes e Sistemas da UTFPR. O trabalho contempla a implementação de um cifrador de César, analisador de frequência e um cifrador de Vernam.

# Sobre o projeto

- ### César: Implementação com o alfabeto "hardcoded".
  - O mais simples dos cifradores simétricos, cada letra do texto é deslocada em "k" posições com relação ao alfabeto.
  - Extremamente vulnerável ao analisador de frequência.
---
- ### Analisador de frequência
  - É feita uma análise da frequência dos caracteres do texto cifrado e comparado com a frequência de caracteres no português.
  - Com o caractere mais frequente no português e com o mais frequente no texto cifrado, é checado o quanto foi deslocado, e assim por diante com os outros caracteres.
  - Ao final temos o deslocamento mais recorrente, e por conseguinte, a chave utilizada na cifra.
---
- ### Cifra de vernam
  - Um cifrador mais robusto e confiável, desde a sua geração de chave até sua decifração.
  - Para gerar uma chave, ela deve ser primeiro do mesmo tamanho do texto, e para garantir segurança é selecionado aleatoriamente um caractere do alfabeto para cada caractere do texto a ser cifrado, incluindo espaços e vírgulas, pois dificulta o reconhecimento de padrões.
  - Para cifrar o texto, é realizada uma operação XOR bit a bit de cada par caractere alfabeto-caractere texto.
  - Como o XOR é uma involução, se realizarmos XOR com o texto cifrado e a mesma chave usada para cifrar, temos o texto decifrado.