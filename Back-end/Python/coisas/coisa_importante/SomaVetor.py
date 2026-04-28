print("\nsomando o vetor:")
vetor1 = [1,2,3]
soma = 0
for i in range(len(vetor1)):
  soma += vetor1[i]
print(soma)

print("\nsomando os vetores:")
vetor2 = [3,2,1]
soma = []
if (len(vetor1) == len(vetor2)):
  for i in range(len(vetor1)):
    for j in range(len(vetor2)):
      if (i == j):
        soma.append(vetor1[i] + vetor2[j])
print(soma)

print("\ncriando matrizes:")
num_linha = int(input("Digite quantas linhas tem a matriz: "))
num_coluna = int(input("Digite quantas colunas tem a matriz: "))
linha = []
matriz1 = []

for l in range(num_linha):
  linha = []
  for c in range(num_coluna):
    linha.append(int(input(f"Digite o numero da linha {l} e coluna {c}: ")))
  matriz1.append(linha)
for i in range(len(matriz1)):
  print(matriz1[i])

print("\nsomando a matriz:")
soma = 0
for i in matriz1:
  for j in i:
    soma += j
print(soma)


num_linha = int(input("Digite quantas linhas tem a outra matriz: "))
num_coluna = int(input("Digite quantas colunas tem a outra matriz: "))
linha = []
matriz2 = []

for l in range(num_linha):
  linha = []
  for c in range(num_coluna):
    linha.append(int(input(f"Digite o numero da linha {l} e coluna {c}: ")))
  matriz2.append(linha)
for i in matriz1:
  print(i)
for i in matriz2:
  print(i)

print("\nsomando as matrizes:")
soma = []
if (len(matriz1) == len(matriz2) and len(matriz1[0]) == len(matriz2[0])):
  for i in range(len(matriz1)):
    linha = []
    for j in range(len(matriz1[0])):
      linha.append(matriz1[i][j] + (matriz2[i][j]))
    soma.append(linha)
for i in soma:
  print(i)