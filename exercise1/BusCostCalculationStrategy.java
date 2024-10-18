// Стратегия для автобуса
class BusCostCalculationStrategy implements ICostCalculationStrategy {
    @Override
    public double calculateCost(double distance, String serviceClass, int passengers, boolean hasDiscount) {
        double basePrice = distance * 0.05; // базовая стоимость за км
        double serviceMultiplier = serviceClass.equals("business") ? 1.3 : 1.0;
        double discount = hasDiscount ? 0.8 : 1.0; // скидка 20%
        return basePrice * serviceMultiplier * passengers * discount;
    }
}
