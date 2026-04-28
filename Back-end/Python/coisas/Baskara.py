import math
print("\nbaskar aultomatico, por favor prencha os campos a seguir.")
a = float(input("digite o A:"))
b = float(input("digite o B:"))
c = float(input("digite o C:"))
delta = b**2 - 4 * a * c
if 0 >= delta:
    print("\n delta é negativo, impossivel resouver baskara")
elif 0 < delta:
    x1 = b * -1 + math.sqrt(delta)
    x2 = b * -1 - math.sqrt(delta)
    print("x1:" , x1 , "x2:" , x2)
else:
    print("jamanta")