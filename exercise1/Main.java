import java.util.Scanner;

// Клиентский код
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод параметров поездки
        System.out.println("Введите расстояние (в км):");
        double distance = scanner.nextDouble();

        System.out.println("Выберите класс обслуживания (economy/business):");
        String serviceClass = scanner.next();

        System.out.println("Введите количество пассажиров:");
        int passengers = scanner.nextInt();

        System.out.println("Есть ли скидка?:");
        boolean hasDiscount = scanner.nextBoolean();

        // Выбор типа транспорта
        System.out.println("Выберите транспорт (1 - самолет, 2 - поезд, 3 - автобус):");
        int transportType = scanner.nextInt();

        // Контекст бронирования
        TravelBookingContext context = new TravelBookingContext();

        // Устанавливаем стратегию в зависимости от выбранного транспорта
        switch (transportType) {
            case 1:
                context.setCostCalculationStrategy(new AirplaneCostCalculationStrategy());
                break;
            case 2:
                context.setCostCalculationStrategy(new TrainCostCalculationStrategy());
                break;
            case 3:
                context.setCostCalculationStrategy(new BusCostCalculationStrategy());
                break;
            default:
                System.out.println("Неверный выбор транспорта.");
                return;
        }

        // Расчет и вывод стоимости
        try {
            double totalCost = context.calculateCost(distance, serviceClass, passengers, hasDiscount);
            System.out.printf("Общая стоимость поездки: %.2f%n", totalCost);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        scanner.close();
    }
}
