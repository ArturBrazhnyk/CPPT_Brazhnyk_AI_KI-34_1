# main.py

from vehicles.car import Car
from vehicles.Truck import Truck

def main():
    # Створюємо об'єкт класу Car
    машина = Car("Toyota", "Camry", 2020)
    машина.показати_інформацію()
    машина.запустити_двигун()

    print("\n")

    # Створюємо об'єкт класу Truck
    вантажівка = Truck("Volvo", "FH", 2019, 20000)
    вантажівка.показати_інформацію()
    вантажівка.запустити_двигун()
    вантажівка.завантажити_вантаж(15000)
    вантажівка.завантажити_вантаж(25000)  # Перевищує максимальну вантажопідйомність

if __name__ == "__main__":
    main()
