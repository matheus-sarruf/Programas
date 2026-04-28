num_caracter = 0
num_espaco = 0
vetor = []
palavra = input("digite uma frase:")
for i in palavra:
 num_caracter += 1
 if i == " ":
  num_espaco += 1
print("tem" , num_caracter - num_espaco , "palavras e" , num_espaco , "espaço na frase digitada")