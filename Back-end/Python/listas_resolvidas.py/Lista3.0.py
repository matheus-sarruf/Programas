
 #exercicio_1/lista3
#nums = []
#numsQuadrado = []
#for i in range(10):
#    num = int(float(input('digite um numero:')))
#    nums.append(num)
#    numsQuadrado.append(num)
#    numsQuadrado[i] = numsQuadrado[i] ** 2
#print(nums ,'\n' , numsQuadrado)

 #exercicio_2/lista3
#A = [2,2,4,5,9,8]
#soma = 0
#for i in range (len(A)):
#    A[i] = A[i] ** 2
#i = 0 
#while i != len(A):         #amo complicar as coisas   
#    soma += A[i]
#    i += 1
#print(soma)

 #exercicio_3/lista3
#TA = int(input('digite quantos elementos que no vao te no vetor A:'))
#TB = int(input('digite quantos elementos que no vao te no vetor B:'))
#A = []
#B = []
#C = []
#for i in range (TA):
#    A.append(TA = float(input('digite um numero:')))
#for i in range (TB):
#    B.append(TB = float(input('digite um numeor:')))
#for i in range(len(A)):
#    for j in range(len(B)): 
#        if A[i] == B[j]:
#            C.append(A[i])
#print(C)

 #exercicio_4/lista3
#def decrecente(X , Y , num):
#    for i in range(len(X)):
#        for j in range(len(Y) - i):
#            print(i , j)
#            if X[i] > Y[j]:
#                num = num
#            elif X[i] < Y[j]:
#                print('nao esta em ordem decrecente')
#                exit()
#    print('o vetor esta em ordem crecente.')
#
#X = []
#Y = []
#num = int(float(input('digite o tamanha do seu vetor:')))
#for i in range (num):
#    X.append(float(input('digite um numero:')))
#for i in reversed (X):
#    Y.append(i)
#print(X , ' \n ' , Y)
#adeaba = decrecente(X , Y , num)

 #exercicio_5/lista3
#Z = []
#num = int(float(input('digite o tamanha do seu vetor:')))
#for i in range (num):
#    Z.append(float(input('digite um numero:')))
#numMaior = int
#num = 0
#quantidadeMaior = 0
#quantidade = 0 
#for i in range (len(Z)):
#    for j in range (len(Z)):
#        if Z[i] == Z[j]:
#            quantidade += 1
#            num = Z[i]
#    if quantidade > quantidadeMaior:
#        quantidadeMaior = quantidade
#        numMaior = num
#    quantidade = 0
#print(quantidadeMaior , ', pois temos' , quantidadeMaior , 'valores de' , numMaior , 'consecutivos')

 #exercicio_6/lista3
#numeros = []
#par = []
#impar = []
#for i in range (20):
#    numeros.append(int(float(input('digite um numero:'))))
#for i in range (len(numeros)):
#    if numeros[i] % 2 == 0: par.append(numeros[i])
#    else: impar.append(numeros[i])
#print('vetor com numeros pares:',par,'\n\n','vetor com os numeros impares:' , impar)