
 ##exercicio_1/lista2
#num = float(input('digite um numero:'))
#if num > 10:
#    print('o numero e maior que 10')
#else:
#    print('o numero nao e maior que 10')

 ##exercicio_2/lista2
#num = float(input('digite um numero:'))
#if num >= 0:
#    print('esse numero e positivo')
#else:
#    print('esse numero e negativo')

 ##exercicio_3/lista2
#quantidade = int(input('digite quantas macas compradas:'))
#macas = 1.30
#if quantidade >= 12:
#    macas = 1
#else:
#   print('a quantidade total da compra fiocou ' , macas * quantidade , 'R$')

 ##exercicio_4/lista2
#nota1 = float(input('digite a nota um do aluno:'))
#nota2 = float(input('digite a nota dois do aluno:'))
#media = (nota1 + nota2) / 2
#if media >= 6:
#    print('o aluno foi aprovado com a media' , media)
#else:
#    print('o aluno foi reprovado com a media' , media)

 ##exercicio_5/lista2
#ano = int(float('digite o ano de nacimento de tal:'))
#idade = 2024 - ano
#if idade == 16 or idade == 17 or idade >= 70:
#    print('tal pode votar')
#else:
#    print('tal tem votar')

 ##exercicio_6/lista2
#ordem = []
#def ordem_crecente(a,b,c):
#    if a == b and a == c:
#        ordem = [a , b , c]
#    elif a < b and a < c:
#        if b < c:
#            ordem = [a , b , c]
#        else:
#            ordem = [a , c , b]
#    elif b < a and b < c:
#        if a < c:
#            ordem = [b , a , c]
#        else:
#            ordem = [b , c , a]
#    elif c < a and c < b:
#        if a < b:
#            ordem = [c , a , b]
#        else:
#            ordem = [c , b , a]
#    return ordem
#def ordem_decrecente(a,b,c):
#    if a == b and a == c:
#        ordem = [a , b , c]    
#    elif a > b and a > c:
#        if b > c:
#            ordem = [a , b , c]
#        else:
#            ordem = [a , c , b]
#    elif b > a and b > c:
#        if a > c:
#            ordem = [b , a , c]
#        else:
#            ordem = [b , c , a]
#    elif c > a and c > b:
#        if a > b:
#            ordem = [c , a , b]
#        else:
#            ordem = [c , b , a]
#    return ordem
#def maior_meio(a ,b , c):
#    if a == b and a == c:
#        ordem = [a , b , c]    
#    elif a > b and a > c:
#        ordem = [b , a , c]
#    elif b > a and b > c:
#        ordem = [a , b , c]
#    elif c > b and c > a:
#        ordem = [b , c , a]
#    return ordem
#while True:
#    num = int(input('digite 1 para ordem crecente digite 2 para ordem decrecente e digite 3 para o maior no meio:\n'))
#    if num != 1 and num != 2 and num != 3:
#        print('ERR@! erro 156 return')
#    else:
#        break
#a = float(input('digite o valor de A:'))
#b = float(input('digite o valor de B:'))
#c = float(input('digite o valor de C:'))
#if num == 1:
#    orden = ordem_crecente(a,b,c)
#    print('a orden crecente e' , orden)
#if num == 2:
#    orden = ordem_decrecente(a , b , c)
#    print('a ordem decrecente e' , orden)
#if num == 1:
#    orden = ordem_crecente(a , b , c)
#    print('a ordem estranha e' , orden)

 ##exercicio_7/lista2
#num1 = float(input('digite um numero:'))
#num2 = float(input('digite outro numero:'))
#if num1 > num2:
#    print(num1)
#else:
#   print(num2)
 ##exercicio_8/lista2
#num1 = float(input('digite um numero:'))
#num2 = float(input('digite outro numero:'))
#if num1 > num2:
#    print(num1 , ' ' , num2)
#else:
#    print(num2 , ' ' , num1)

 ##exercicio_9/lista2
#Horainicial = int(input('digite a hora do comeco do jogo:'))
#Horafinal = int(input('digite a hora final do jogo'))
#print('o jogo demorou' , Horafinal - Horainicial)

 ##exercicio_10/lista2
#salarioHora = float(input('digite seu salario por hora:'))
#horasTrabalhadas = int(input('digite quantas horas foi trabalhadas nesse mes:'))
#if horasTrabalhadas <= 40 * 4:
#    salario = horasTrabalhadas * salarioHora
#else:
#    salario = (horasTrabalhadas - 40 * 4) * (salarioHora * 150 / 100) + 40 * 4 * salarioHora
#print('o salario do final do mes e de' , salario , 'R$')

 ##exercicio_11/lista2
#nome = input('digite o seu nome:')
#altura = float(input('digite a sua altura:'))
#while True:
#    sexo = input('digite o seu sexo, M para masculino e F para feminino:')
#   if sexo == "M":
#        pesoIdeal = (72.7 * altura) - 58
#        break
#    elif sexo == "F":
#        pesoIdeal = (52.1 * altura) - 44.7
#        break
#    else:
#        print('ERRo 179  \n\n\n                        digite novamente \n')
#print('o peso ideal e peso ideal de ' , nome, 'e ' , pesoIdeal)

 ##exercicio_12/lista2
#salario = float(input('digite o valor do salario:'))
#vendas = float(input('digite o valor das vendas desse mes:'))
#if vendas < 1500:
#    comisao = (vendas - 1500) * 5 / 100 + 1500 * 3 / 100
#else:
#    comisao = vendas  * 3 / 100
#print('o valor total a ser pago sera de ' , comisao + salario , ' com uma comisao de' , comisao , 'R$')

 ##exercicio_13/lista2
#saldoAtual = float(input('digite o seu saldo atualmente:'))
#saldoDebito = float(input('digite o valor a ser debitado:'))
#saldoCredito = float(input('digite o valor a ser adicionado:'))
#saldoAtual = saldoAtual - saldoDebito + saldoCredito
#if saldoAtual >= 0:
#    estado = "positivo"
#else:  
#    estado = "negativo"
#print('o estado do seu saldo e ' , estado , 'com' , saldoAtual , 'R$')

 ##exercicio_14/lista2
#estoqueAtual = int(input('digite a quantidade de produtos no estoque:'))
#estoqueMaximo = int(input('digite a quantidade maxima do estoque:'))
#stoqueMinimo = int(input('digite a quantidade minima do estoque'))
#media = (estoqueMaximo + estoqueMinimo) / 2
#print('a media do estoque e' , media)
#if media <= estoqueAtual:
#    nessecidade = "nao necessita de"
#else:
#    nessecidade = "necessita de"
#print(nessecidade , 'reestoque')

 ##exercicio_15/lista2
#num = float(input('digite um numero:'))
#if num > 0:
#    tipo = "positivo"
#elif num < 0:
#    tipo = "negativo"
#if num == 0:
#    tipo = "nulo"
#print('seu numero e ' , tipo)

 ##exercicio_16/lista2
#i = 0
#while i != 3:
#    i += 1
#    num = float(input('digite um numero:'))
#    if i == 0:
#        maior = num
#    elif maior < num:
#        maior = num
#print('o maior numero digitado e o' , maior)

 ##exercicio_17/lista2
#i = 0
#while i != 3:
#    i += 1
#    num = float(input('digite um numero:'))
#    if i == 1:
#        Pmaior = num
#    if  num > Pmaior:
#        Smaior = Pmaior
#        Pmaior = num
#print('a soma dos dois maiores e:' , Pmaior + Smaior)

 ##exercicio_18/lista2
#for i in range (3):
#    num = float(input('digite um numero:'))
#    if i == 0:
#        P = num
#    elif i == 1:
#        if num < P:
#            S = P
#            P = num
#        else:
#            S = num
#    elif num < P:        ##o cara gosta de dificultar o facio era so da dois imput e dale
#        T = S 
#        S = P
#        P =  num
#    elif num < S:
#        T = S
#        S = num
#    else:
#        T = num
#print('ordem crecente dos numeros digitados e' , P , S , T)

 ##exercicio_19/lista2
#objeto = "e um triangulo"
#A = float(input('digite o valor de um dos lado do triangulo:'))
#B = float(input('digite o valor do outro lado do triangulo:'))
#C = float(input('digite o valor do utimo lado do triangulo:'))
#if A + B >= C:
#    objeto = "nao e um triangulo"
#if B + C >= A:
#    objeto = "nao e um triangulo"
#if C + A >= B:
#    "nao e um triangulo"
#print('o objeto' , objeto)

 ##exercicio_20/lista2
#time1 = input('digite o nome do time1:')
#gols1 = int(input('digite o numero de gols do time1 na partidade:'))
#time2 = input('digite o nome do time2:')
#gols2 = int(input('digite o numero de gols do time2 na partidade:'))
#if gols1 > gols2:
#    resultado = "o time " + time1 +" e o vencedor";
#if gols2 > gols1:
#    resultado = "o time " + time2 +" e o vencedor";
#if gols1 == gols2:
#    resultado = "empate com " + str(gols1) + " gols de ambos"
#print('o resultado do jogo e' , resultado)

 ##exercicio_21/lista2
#num1 = float(input('digite um numero:'))
#num2 = float(input('digite outro numero:'))
#if num1 > num2:
#    maior = num1
#if num2 > num1:
#    maior = num2
#if maior == 'nois':
#    print('os numeros sao iguais sendo eles' , num1)
#else:
#    print('o maior numero digitado e o' , maior)

 ##exercicio_22/lista2
#X = float(input('digite um valor de um X:'))
#Y = float(input('digite outro valor Y:'))
#Z = (X * Y) + 5
#if Z <= 0:
#    resposta = "A"
#else:
#    if Z <= 100:
#        resposta = "B"
#    else:
#        resposta = "C"
#print(Z , resposta)
 #resposta teste da 22:
##  X  |  Y  |  Z  |  R    
##  3  |  2  |  11 | "B"
## 150 |  3  | 455 | "C"
##  7  | -1  | -2  | "A"
## -2  |  5  | -5  | "A"
## 50  |  3  | 155 | "C"

 ##exercicio_23/lista2
#combustivel = input('digite A para Alcool ou G para Gasolina:')
#litros = int(float(input('digite quantos litros do combustivel:')))
#while True:
#    if combustivel == "A":
#        if litros < 20:
#            desconto = 3
#        elif litros > 20:
#            desconto = 5
#        print('o total apagar sera de' , litros * (3.30 - (3.30 * desconto) / 100) , 'R$')
#        break
#    elif combustivel == "G":
#        if litros < 20:
#            desconto = 4
#        elif litros > 20:
#            desconto = 6
#        print('o total apagar sera de' , litros * (3.30 - (3.30 * desconto) / 100) , 'R$')
#        break
#    else:
#        print('\n\n                                         erro176 retur\n\n')
#        combustivel = input('digite A para Alcool ou G para Gasolina:')
#        litros = int(float(input('digite quantos litros do combustivel:')))

 ##exercicio_24/lista2
#H1 = int(float(input('digite a idade do homem1 :')))
#H2 = int(float(input('digite a idade do homem2 :')))
#M1 = int(float(input('digite a idade da mulher1 :')))
#M2 = int(float(input('digite a idade da mulher2 :')))
#if H1 > H2:
#    Hmaior = H1
#    Hmenor = H2
#else:
#    Hmaior = H2
#    Hmenor = H1
#if M1 > M2:
#    Mmaior = M1
#    Mmenor = M2
#else:
#    Mmaior = M2
#    Mmenor = M1
#print(Hmaior + Mmenor , '     ' , Hmenor * Mmaior)

 ##exercicio_25/lista2
#morangos = float(input('digite quantos Kg de morangos'))
#macas = float(input('digite quantos Kg de macas'))
#precoMorango = 2.50
#precoMaca = 1.80
#if morangos > 5:
#    precoMorango = 2.20
#if macas > 5:
#    precoMaca = 1.50
#precoMorango = precoMorango * morangos
#precoMaca = precoMaca * macas
#total = precoMaca + precoMorango
#if total > 25 or macas + morangos > 8:
#    print('o total a pagar sera de' , total - total * 10 / 100 , 'R$')
#else:
#    print('o total a pagar sera de' , total)

 ##exercicio_26/lista2
#ID = input('digite o ID do usuario:')
#if ID == "1234":
#    while True:
#        senha = input('digite a senha deste usuario:')
#        if senha == "0000":
#            print('acesso permitido')
#            break
#        else:
#            print('senha incorreta tente novamente:')

 ##exercicio_27/lista2
#nome = input('digite o nome do produto:')
#preco = float(input('digite o preco do produto:'))
#quantidade = int(input('digite a quabtidade adquirida:'))
#total = quantidade * preco
#print('o total sem desconto seria de' , total , 'R$')
#if quantidade > 10:
#    desconto = 5
#elif quantidade > 5 and quantidade <= 10:
#    desconto = 3
#elif quantidade <= 5:
#    desconto = 2
#print('o total a pagar com desconto sera de' , total - total * desconto / 100)

 ##exercicio_28/lista2
#nota1 = float(input('digite a nota1 do aluno:'))
#nota2 = float(input('digite a nota2 do aluno:'))
#nota3 = float(input('digite a nota3 do aluno:'))
#media = (nota1 + nota2 + nota3) / 3
#mediaPonderada = (nota1 + nota2 * 2 + nota3 * 3 + media)  / 7
#if mediaPonderada >= 9: conceito = "A"
#elif mediaPonderada >= 7.5 and mediaPonderada < 9: conceito = "B"
#elif mediaPonderada >= 6 and mediaPonderada < 7.5: conceito = "C"
#else: conceito = "D"
#print('o conceito do aluno e' , conceito)

 ##exercicio_29/lista2
#ID = input('digite o ID do funcionario:')
#nacimento = int(input('digite a data de nacimento do funcionario:'))
#anoIngresso = int(input('digite o ano em que o empregado entro na empresa:'))
#idade = 2024 - nacimento
#idadeAimpresa = 2024 - nacimento
#if idade >= 65: estado = "esta apto"
#elif idadeAimpresa >= 30: estado = "esta apto"
#elif idade >= 60 and idadeAimpresa >= 25: estado = "apto"
#else: estado = "nao esta apto"
#print('o funcionario portador do ID' , ID , estado , 'a se aposentar')

 ##exercicio_30/lista2
#while True:
#    A = float(input('digite o valor de um dos lados do triangulo:'))
#    B = float(input('digite o valor de outro lado do triangulo:'))
#    C = float(input('digite o valor do ultimo lado do triangulo:'))
#    if A < B + C and B < A + C and C < A +B:
#        if A == B and B == C:  
#            print('o triangulo e equilatero')
#            break
#        elif A == B or B == C or C == A:  
#            print('o triangulo e isosceles')
#            break
#        else:  
#            print('o triangulo e escaleno')
#            break
#    else: print('nao e um triangulo, tente novamente')
# #resposta teste da 30:
##  A  |  B  |  C  |  T
##  1  |  2  |  3  |  N
##  3  |  4  |  5  |  ES
##  2  |  2  |  4  |  N
##  4  |  4  |  4  |  EQ