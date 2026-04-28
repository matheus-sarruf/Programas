#lista4/prova.nu1
#c = []
#for i in range(10):
#    num = int(input("Digite um número inteiro: "))
#    quadrado = num ** 2
#    c.append(quadrado)
#for v in c:
# print(v)

#lista4/prova.nu2
#quantidade = int(input('Digite a quantidade de repeticao:'))
#c = []
#for i in range(quantidade):
#    num = int(input('Digite os numeros que voce quer ao quadrado:'))
#    c.append(num)
#soma_quadrados = 0
#for v in c:
#    soma_quadrados += v ** 2
#print(soma_quadrados)

#lista4/prova.nu3
#a = []
#ta_a = int(input('Digite a quantidade de repeticao do vetor A:'))
#b = []
#ta_b = int(input('Digite a quantidade de repeticao do vetor B:'))
#for i in range(ta_a):
# nu_a = int(input('digite o numero para colocar no vetor A:'))
# a.append(nu_a)
#for i in range(ta_b):
# nu_b = int(input('digite o numero para colocar no vetor B:'))
# b.append(nu_b)
#intersecao = []
#for v in a:
# if v in b:
#  intersecao.append(v)
#print("a intersecao entre o vetor a e o b e:", intersecao)

#lista4/prova.nu4
#a = []
#ta_a = int(input('Digite a quantidade de repeticao do vetor A:'))
#for i in range(ta_a):
# nu_a = int(input('digite o numero para colocar no vetor A:'))
#ordem_D = True
#for i in range(len(a) - 1):
#    if a[i] < a[i + 1]:
#        ordem_D = False
#if ordem_D:
#    print("Os elementos estão em ordem decrescente.")
#else:
#    print("Os elementos não estão em ordem decrescente.")
          
#lista4/prova.nu5
#a = []
#ins_a = int(input('digite o tamanho do vetor:'))
#for i in range(ins_a):
# num = int(input('digite os numeros para colocar no vetor'))
# a.append(num)
#ta_a = 1
#ma_t = 1
#for i in range(1, len(a)):
#    if a[i] == a[i-1]:
#        ta_a += 1
#    else:
#        if ta_a > ma_t:
#            ma_t = ta_a
#        ta_a = 1
#if ta_a > ma_t:
#    ma_t = ta_a
#print("Tamanho da maior sequência consecutiva:", ma_t)

#lista4/prova.nu6
#par = []
#impar = []
#for i in range(20):
#    num = int(input('Digite os numeros inteiro: '))
#    if num % 2 == 0:
#        par.append(num)
#    else:
#        impar.append(num)
#print('pares:', par)
#print('impares:', impar)