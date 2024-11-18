from calculation_module import calculate_y, write_to_text_file, write_to_binary_file, read_from_text_file, read_from_binary_file, log_error

def get_input():
    try:
        x = float(input("Введіть значення x: "))
        y = calculate_y(x)
        print(f"Обчислене значення y = {y} для x = {x}")
        return [y]

    except ValueError as e:
        print(f"Помилка: {e}")
        return []
    except Exception as e:
        log_error(f"Невідома помилка в calculate_y: {e}")
        return None


user_data = get_input()

if user_data:
    print("\nЗначення, які будуть записані у файл:")
    print(user_data)

    write_to_text_file("results.txt", user_data)
    write_to_binary_file("results.bin", user_data)

    # Читання результатів з текстового файлу
    text_data = read_from_text_file("results.txt")
    print("\nТекстові дані з файлу:")
    print(text_data)

    # Читання результатів з двійкового файлу
    binary_data = read_from_binary_file("results.bin")
    print("\nДвійкові дані з файлу:")
    print(binary_data)
