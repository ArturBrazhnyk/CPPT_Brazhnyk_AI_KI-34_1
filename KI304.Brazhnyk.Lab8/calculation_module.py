import math
import pickle

# Функція для обчислення y = 2x / sin(x)
def calculate_y(x):
    if math.sin(x) == 0:
        raise ValueError("Значення x не може дорівнювати значенню, при якому sin(x) = 0.")
    return 2 * x / math.sin(x)

def log_error(error_message):
    try:
        with open("error_log.txt", 'a') as file:
            file.write(f"{error_message}\n")
    except Exception as e:
        print(f"Не вдалося записати помилку в файл: {e}")

# Функція для запису результатів у текстовий файл
def write_to_text_file(filename, data):
    """Записує дані у текстовий файл."""
    with open(filename, 'w') as file:
        for y in data:
            file.write(f"{y}\n")

# Функція для запису результатів у двійковий файл
def write_to_binary_file(filename, data):
    """Записує дані у двійковий файл за допомогою pickle."""
    with open(filename, 'wb') as file:
        pickle.dump(data, file)

# Функція для читання даних з текстового файлу
def read_from_text_file(filename):
    """Читає дані з текстового файлу та повертає список значень y."""
    data = []
    with open(filename, 'r') as file:
        for line in file:
            y = float(line.strip())  # Перетворюємо рядок в число (y)
            data.append(y)
    return data

# Функція для читання даних з двійкового файлу
def read_from_binary_file(filename):
    """Читає дані з двійкового файлу за допомогою pickle."""
    with open(filename, 'rb') as file:
        data = pickle.load(file)
    return data
