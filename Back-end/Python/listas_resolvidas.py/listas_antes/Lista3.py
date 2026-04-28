
#20/04/2023 - Lista de exercícios 02
#Roberto Cesar Soltoski
#•
#20 de abr. de 2023 (editado: 27 de abr. de 2023)
#100 pontos
#lista de exercícios 2.pdf
#Seus trabalhos
#Entregue
#
#Lista3.py

##lista3/nu1
#num = float(input('digite um numero:'))
#if num > 10:
# print('seu numero e maior que 10')
#else:
# print('seu numero e menor que 10')

##lista3/nu2
#num = float(input('digite um numero:'))
#if num >= 0:
# print('seu numero e positivo')
#else:
# print('seu numero e negativo')

##lista3/nu3
#num = float(input('digite um numero de maçãs compradas:'))
#if num >= 12:
# print('R$:' , num * 1)
#else:
# print('R$:' , num * 1.30)

##lista3/nu4
#av1 = float(input('digite a sua av1:'))
#av2 = float(input('digite a sua av2:'))
#media = (av1+ av2) / 2
#if media >= 6 :
# print('voce foi aprovado, sua media e: ' , media)
#else:
# print('voce foi reprovado, sua media e: ' , media)

##lista3/nu5
#ano = int(input('digite o ano qua voce naceu:'))
#if (2023 - ano) >= 18 :
# print('voce pode votar e ser preso')
#else:
# print('voce nao pode votar (fazer merda)')

##lista3/nu6
#a = float(input('digite um numero:'))
#b = float(input('digite um numero:'))
#c = float(input('digite um numero:'))
#n = float(input('digite 1 para ordem cresente, 2 para decresente e 3 para colocar o maior no meio:'))
##etapa1/a>_==
#if n == 1:
# if a == b and a == c :
#  print(a, ',',a, ',',a)
# if a > b and a > c :
#  if b >= c :
#   print(c, ", ",b, ", ",a)
#  if c > b :
#   print(b, ", ",c ,", ",a)
##etapa1/b>
# if b > a and b > c :
#  if a >= c :
#   print(c, ", ",a, ", ",b)
#  if c > a :
#   print(a, ", ",c, ", ",b)
##etapa1/c>
# if c > a and c > b :
#  if a >= b :
#   print(b, ", ",a, ", ",c)
#  if b > a :
#   print(a, ", ",b, ", ",c)
##etapa2/a>_==
#elif n == 2 :
# if a == b and a == c :
#  print(a, ',',a, ',',a)
# if a > b and a > c :
#  if b >= c :
#   print(a, ", ",b, ", ",c)
#  if c > b :
#   print(a, ", ",c, ", ",b)
##etapa2/b>
# if b > a and b > c :
#  if a >= c :
#   print(b, ", ",a, ", ",c)
#  if c > a :
#   print(b, ", ",c, ", ",a)
##etapa2/c>
# if c > a and c > b :
#  if b >= a :
#   print(c, ", ",b, ", ",a)
#  if a > b :
#   print(c, ", ",a, ", ",b)
##etapa3/a>==
#elif n == 3 :
# if  a == b and a == c :
#  print(a, ',',a, ',',a)
# if  a > b and  a > c :
#  if  b >= c :
#   print(c, ", ",a, ", ",b)
#  if c > b :
#   print(b, ", ",a, ", ",c)
##etapa3/b>
# if b > a and b > c :
#  if a >= c :
#   print(c, ", ",b, ", ",a)
#  if c > a :
#   print(a, ", ",b, ", ",c)
##etapa3/c>
# if c > a and c > b :
#  if b >= a :
#   print(a, ", ",c, ", ",b)
#  if a > b :
#   print(b, ", ",c, ", ",a)

##lista3/nu7
#valor1 = float(input('digite um valor:'))
#valor2 = float(input('digite outro valor:'))
#if valor1 > valor2:
# print('o valor maior e o:' , valor1)
#else:
# print('o valor maior e o:' , valor2)

##lista3/nu8
#valor1 = float(input('digite um valor:'))
#valor2 = float(input('digite outro valor:'))
#if valor1 >= valor2:
# print(valor2 , " , " , valor1)
#else:
# print(valor1 , " , " , valor2)

##lista3/nu9
#jogo_ini = float(input('digite a hora incial da parida:'))
#jogo_fin = float(input('digite a hora final da partida de xadrez:'))
#duracao = jogo_ini - jogo_fin
#if duracao >= 24:
# print('voce estrapolo a duracao da partida')
#else:
# print(' sua partida durou ' , duracao , 'hora')

##lista3/nu10
#h = float(input('digite quantas horas que voce trabalhou nesse mes:'))
#salario_h = float(input('digite o seu salario por horas:'))
#if h > 16 :
# h_extra = h  - 160                         #isso e so para eu nao me confundir
# bonus =  salario_h * 150 / 100
# print ('seu salario e: ' , bonus * h_extra + (h - h_extra) * salario_h )
#else:
#  print('seu salario e; ' , h * salario_h)

##lista3/nu11
#nome = input('digite o seu nome:')
#altura = float(input('digite a sua altura:'))
#mf = input('digite o seu sexo, m para masculino e f para feminino:')
#if mf == m:
# peso_ideal = ((72.7 * altura) - 58)
#else:
# peso_ideal = ((62.1 * altura) - 44.7)
#print('o peso ideal de ' , nome , ' e: ' , peso_ideal)

##lista3/nu12
#salario = float(input('digite o seu salario'))
#comisao = float(input('digite o valor das sua vendas'))
#if comisao <= 1500:
# print('o total a ganhar:' , salario + comisao * 3 / 100)
#elif comisao > 1500:
# print('o total a ganhar:' , salario + (comisao - 1500) * 5 / 100 + 1500 * 3 / 100)

##lista3/nu13
#conta = float(input('digite o numero da sua conta'))
#saldo = float(input('digite o seu saldo'))
#credito = float(input('digite o valor a acresentar'))
#debito = float(input('digite o valor a subtrair'))
#if (saldo + credito - debito)  < 0:
# print('a conta' , conta , 'esta a derir' , saldo + credito - debito , 'R$')
#elif (saldo + credito - debito)  == 0:
# print('sua conta esta zerada') 
#else:
# print('a conta' , conta , 'esta com o saldo de:' , saldo + credito - debito , 'R$')

##lista3/nu14
#estoque = float(input('digite quantos a em estoque:'))
#maximo = float(input('digite o valor maximo que pode ser estocado:'))
#minimo = float(input('digite o valor maximo que pode ser estocado:'))
#if ((maximo + minimo) / 2) >= estoque and maximo >= minimo:
# print('e aconselhavel nao fazer mais compras')
#elif((maximo + minimo) / 2) < estoque and maximo >= minimo:
# print('e aconselhavel fazer mais compras')
#else:
# print('jumento')

##lista3/nu15
#nu = float(input('digite um valor qualquer:')
#if nu > 0
# print('seu nume e positivado')
#elif nu < 0:
# print('seu numero e negativado')   
#else:
# print('seu numero e zerado kkk')

##lista4/nu16
#nu1 = float(input('digite um numero:'))
#nu2 = float(input('digite um numero:'))
#nu3 = float(input('digite um numero:'))
#if nu1 > nu2 and nu1 > nu3:
# print(nu1)
#elif nu2 > nu1 and nu2 > nu3:
# print(nu2)
#else:
# print(nu3)

##lista3/nu17
#nu1 = float(input('digite um numero:'))
#nu2 = float(input('digite um numero:'))
#nu3 = float(input('digite um numero:'))
##passo1/n1
#if nu1 > nu2 and nu1 > nu3:
# if nu2 > nu3:
#  print(nu1 + nu2)
# elif nu3 > nu2:
#  print(nu1 + nu3)
##passo2/n2
#elif nu2 > nu1 and nu2 > nu3:
# if nu1 > nu3:
#  print(nu2 + nu1)
# elif nu3 > nu1:
#  print(nu2 + nu3)
##passo3/n3
#elif nu3 > nu1 and nu3 > nu2:
# if nu1 > nu2:
#  print(nu3 + nu1)
# elif nu2 > nu1:
#  print(nu3 + nu2)

##lista3/nu18
#nu1 = float(input('digite um numero:'))
#nu2 = float(input('digite um numero:'))
#nu3 = float(input('digite um numero:'))
##passo1/n1
#if nu1 > nu2 and nu1 > nu3:
# if nu2 > nu3:
#  print(nu1 , ',' , nu2 , ',' , nu3)
# elif nu3 > nu2:
#  print(nu1 , ',' , nu3 , ',' , nu2)
##passo2/n2
#elif nu2 > nu1 and nu2 > nu3:
# if nu1 > nu3:
#  print(nu2 , ',' , nu1 , ',' , nu3)
# elif nu3 > nu1:
#  print(nu2 , ',' , nu3 , ',' , nu1)
##passo3/n3
#elif nu3 > nu1 and nu3 > nu2:
# if nu1 > nu2:
#  print(nu3 , ',' , nu1 , ',' , nu2)
# elif nu2 > nu1:
#  print(nu3 , ',' , nu2 , ',' , nu1)

##lista3/nu19
#a = float(input('digite um numero:'))
#b = float(input('digite um numero:'))
#c = float(input('digite um numero:'))
#if a < (b + c):
# if b < (c + a):
#  if c < (a + b):
#   print('e um triangulo')
#else:
# print('nao e um triangulo')

##lista3/nu20
#time1 = input('digite o nome do seu time:')
#pontos1 = float(input('digite quantos pontos fizeram:'))
#time2 = input('digite o nome do outro time:')
#pontos2 = float(input('digite o quantos pontos eles fizeram:'))
#if pontos1 > pontos2:
# print(time1 , 'e o vençedor com uma diferença de:' , (pontos1 - pontos2) , 'pontos')
#elif pontos2 > pontos1:
# print(time2 , 'e o vençedor com uma diferença de:' , (pontos2 - pontos1) , 'pontos')
#elif pontos1 == pontos2:
# print('empate')

##lista3/nu21
#nu1 = float(input('digite um numero:'))
#nu2 = float(input('digite um numero:'))
#if nu1 > nu2:
# print('o primero e o maior')
#elif nu2 > nu1:
# print('o segundo e maior')
#elif nu1 == nu2:
# print('numero iguais')

##lista3/nu22
#x = float(input('digite um numero:'))
#y = float(input('digite um numero:'))
#z = (x * y) + 5
#if z <= 0:
# resposta = "A"
#else:
# if z <= 100:
#  resposta = "B"
# else:
#   resposta = "C"
#print(z , ',' , resposta)
                                       #as variaveis. obs: que bosta, essa porcaria nao fica alhinhada
#    x    |  y  |  z  |  resposta|
#    3    |  2  |  11.0   |  B |
#  150  |  3  |  455.0  |  C |
#    7    | -1  |   -2.0   |  A |
#   -2   |   5  |   -5.0   |  A |
#   50   |  3  |  155.0  |  C |

##lista3/nu23
#combustivel = input('digite qual voce quer A para álcool, G para gasolina:')
#litros = float(input('digite quantos litros voce quer:'))
#if combustivel == "A":
# if (litros <= 20) > 0 :
#  print('a pagar:' , -1 * (((2.90 * 3) / 100) * litros - litros * 2.90) , 'R$')
# elif litros > 20:
#  print('a pagar:' , -1 * (((2.90 * 5) / 100) * litros - litros * 2.90) , 'R$')
#elif combustivel == "G":
# if (litros <= 20) > 0 :
#  print('a pagar:' , -1 * (((3.30 * 4) / 100) * litros - litros * 3.30) , 'R$')
# elif litros > 20:
#  print('a pagar:' , -1 * (((3.30 * 6) / 100) * litros - litros * 3.30) , 'R$')

##lista3/nu24
#m1 = float(input('digite a idade do homen1:'))
#m2 = float(input('digite a idade do homen2:'))
#f1 = float(input('digite a idade do mulher1:'))
#f2 = float(input('digite a idade do mulher2:'))
#if m1 > m2:
# if f1 > f2:
#  soma = m1 + f2
#  multiplacacao = m2 * f1 
# elif f2 > f1:
#  soma = m1 + f1
#  multiplacacao = m2 * f2
#elif m2 > m1:
# if f1 > f2:
#  soma = m2 + f2
#  multiplacacao = m1 * f1 
# elif f2 > f1:
#  soma = m2 + f1
#  multiplacacao = m1 * f2
#print(soma , ',' , multiplacacao)

##lista3/nu25
#macas = float(input('digite quantos quilos voce comprou de macas:'))
#morangos = float(input('digite quantos quilos voce comprou de morangos:'))
#kg = morangos + macas
#if morangos > 5:
# morango_add = morangos * 2.20
#if morangos <= 5:
# morangos_add = morangos * 2.50
#if macas > 5:
# macas_add = macas * 1.50
#if macas <= 5:
# macas_add = macas * 1.80
#total = macas_add + morangos_add
#if kg > 8 or total > 25:
# print('o seu total e de:' ,total -  total * 10 / 100)
#else:
# print('o seu total e de:' , total)


##lista3/nu26
#usuario = input('digite o seu usuario')
#senha = input('digite a senha do seu usuario')
#if usuario == "matheus":
# if senha == "m37843737":
#  print('seja bem vindo sarruf')
# else:
#  print('senha invalida')
#elif usuario == "alunoadm":
# if senha == "alunoadm":
#  print('seja bem vindo aluno')
# else:
#   print('senha invalida')
#else:
# print('usuario nao encontrado')

##lista3/nu27
#produto = input('digite o nome do seu produto:')
#quantidade = float(input('digite a quantidade de produto que voce comprou:'))
#preco = float(input('digite o preco por unidade do seu produto:'))
#total = quantidade * preco
#if quantidade <= 5:
# print('o total de ' , produto , 'pagar e de:' , total - total * 2 /100)
#elif quantidade 5 > and quantidade <= 10:
# print('o total de ' , produto , 'pagar e de:' , total - total * 3 /100)
#elif quantidade > 10:
# print('o total de ' , produto , 'pagar e de:' , total - total * 5 /100)

##lista3/nu28
#av1 = input('digite a  av1 do seu aluno')
#av2 = input('digite a  av2 do seu a aluno')
#av3 = input('digite a  av3 do seu a aluno')
#media = (av1 + av2 + av3) / 3
#mediaadd = (av1 + av2 * 2 +av3 * 3 + media) / 7
#if mediaadd >= 9:
# print('A')
#elif mediaadd >= 7.5 and mediaadd < 9:
# print('B')
#elif mediaadd >= 6 and mediaadd < 7.5:
# print('C')
#elif  mediaadd < 6:
# print('D')

##lista3/nu29
#codigo = input('digite o codigo de empregaçao:')
#nacimento = float(input('digite o ano em que voce naceu:'))
#dataT = float(input('digite o ano em que voce  engressou na area de trabalho:'))
#data = 2023 - nacimento
#if codigo == 'chama pro bico':
# if  data > 65and (2023 - dataT) > 30:
#  print('requer aposentadoria')
# elif data > 60 and (2023 - dataT) > 25:
#  print('requer aposentadoria')
# else:
 # print('nao requer aposentadoria')

##lista3/nu30
#a = float(input('digite um numero'))
#b = float(input('digite outro numero'))
#c = float(input('digite mais um numero'))
#if (a < b + c) and (b < a + c) and (c < a + b):
# if a == b and b == c:
#  mens = "triangulo equilatero"
# else:
#  if a == b or b == c or a == c:
#   mens = "triangulo isosceles"
#  else:
#   mens = "triangulo escaleno"
#else:
# mens = 'Não e possível formar um triângulo'
#print(mens)
#    x    |  y  |  z  |  resposta|
#    1    |  2  |  3  |  NE |
#    3    |  4  |  5  |  escaleno |
#    2    |  2  |  4  |  NE |
#    4    |  4  |  4  |  equilatero|
#    5    |  3  |  3  |  isosceles |