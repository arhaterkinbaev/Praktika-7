// Стратегия для самолета
class AirplaneCostCalculationStrategy implements ICostCalculationStrategy {
    @Override
    public double calculateCost(double distance, String serviceClass, int passengers, boolean hasDiscount) {
        double basePrice = distance * 0.15; // базовая стоимость за км
        double serviceMultiplier = serviceClass.equals("business") ? 2.0 : 1.0; // множитель за класс обслуживания
        double discount = hasDiscount ? 0.9 : 1.0; // скидка 10% если есть скидка
        return basePrice * serviceMultiplier * passengers * discount;
    }
}
