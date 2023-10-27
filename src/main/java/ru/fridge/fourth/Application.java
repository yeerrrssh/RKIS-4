package ru.fridge.fourth;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;

/**
 * Консольное приложение для работы с базой данных
 */
@Component
public class Application {
    /**
     * Сервис для работы с холодильниками
     */
    private final FridgeService fridgeService;

    /**
     * Конструктор приложения
     * @param fridgeService сервис для работы с холодильниками
     */
    public Application(FridgeService fridgeService) {
        this.fridgeService = fridgeService;
    }

    /**
     * Запускает консольное приложение
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1 - Добавить новый холодильник");
            System.out.println("2 - Вывести информаию об имеющихся холодильниках");
            System.out.println("3 - Изменить информаию о холодильнике");
            System.out.println("4 - Найти холодильник");
            System.out.println("5 - Удалить холодильник");
            System.out.println("6 - Завершить работу");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addFridge(scanner);
                case "2" -> showAllFridges();
                case "3" -> editFridge(scanner);
                case "4" -> searchFridges(scanner);
                case "5" -> deleteFridge(scanner);
                case "6" -> {
                    System.out.println("Завершение работы");
                    return;
                }
                default -> System.out.println("Некорректный ввод!");
            }
        }
    }

    /**
     * Считывает данные объекта из коммандной строки
     * @param scanner Объект класса Scanner
     * @return Новый холодильник
     */
    private Fridge getFridge(Scanner scanner){
        System.out.println("Введите модель холодильника:");
        String name = scanner.nextLine();
        System.out.println("Введите бренд холодильника:");
        String brand = scanner.nextLine();
        System.out.println("Введите цвет холодильника:");
        String color = scanner.nextLine();
        System.out.println("Введите стоиость:");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Введите количество:");
        int quantity = Integer.parseInt(scanner.nextLine());
        return new Fridge(name, brand, color, price, quantity);
    }

    /**
     * Добавляет новый холодильник в базу
     * @param scanner Объект класса Scanner
     */
    private void addFridge(Scanner scanner) {
        Fridge fridge = getFridge(scanner);
        fridgeService.addFridge(fridge);
        System.out.println("Новый холодильник был успешно добавлен!");
    }

    /**
     * Выводит в консоль информацию обо всех имеющихся холодильниках
     */
    private void showAllFridges() {
        List<Fridge> fridgeList = fridgeService.getAllFridges();
        if (fridgeList.isEmpty()) {
            System.out.println("Холодильники не найдены :(");
        } else {
            for (Fridge fridge : fridgeList) {
                System.out.println(fridge);
            }
        }
    }

    /**
     * Редактирует информацию о холодильнике
     * @param scanner Объект класса Scanner
     */
    private void editFridge(Scanner scanner) {
        showAllFridges();
        System.out.println("Введите ID объекта для редактирования: ");
        int id = Integer.parseInt(scanner.nextLine());
        Fridge updatedFridge = getFridge(scanner);
        fridgeService.updateFridge(id, updatedFridge);
        System.out.println("Данные о холодильнике были успешно обновлены!");
    }

    /**
     * Удаляет холодильник по id
     * @param scanner Объект класса Scanner
     */
    private void deleteFridge(Scanner scanner) {
        System.out.println("Введите ID холодильника для удаления:");
        int id = Integer.parseInt(scanner.nextLine());
        fridgeService.deleteFridge(id);
        System.out.println("Холодильник удален!");
    }

    /**
     * Ищет холодильник по одному из полей
     * @param scanner Объект класса Scanner
     */
    private void searchFridges(Scanner scanner) {
        System.out.println("Выберите критерий поиска: ");
        System.out.println("1 - Модель");
        System.out.println("2 - Бренд");
        System.out.println("3 - Цвет");
        String field;
        switch (scanner.nextLine()) {
            case "1" -> field = "model";
            case "2" -> field = "brand";
            case "3" -> field = "color";
            default -> {
                System.out.println("Некорректный ввод :(");
                return;
            }
        }
        System.out.println("Введите значение для поиска: ");
        String value = scanner.nextLine();
        List<Fridge> fridgeList = fridgeService.searchFridges(field, value);
        if (fridgeList.isEmpty()) {
            System.out.println("Данных о холодильниках нет");
        } else {
            for (Fridge fridge : fridgeList) {
                System.out.println(fridge);
            }
        }
    }
}