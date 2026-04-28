import random
def geraNumeros():
    vet =[]
    for i in range(10):
        n = random.randint(1 , 10)
        vet.append(n)
    return vet
vetor = geraNumeros()
print(vetor)