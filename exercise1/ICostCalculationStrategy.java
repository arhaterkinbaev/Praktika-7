// Интерфейс стратегии расчета стоимости
public interface ICostCalculationStrategy {
    double calculateCost(double distance, String serviceClass, int passengers, boolean hasDiscount);
}
