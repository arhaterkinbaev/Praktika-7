// Стратегия для поезда
class TrainCostCalculationStrategy implements ICostCalculationStrategy {
    @Override
    public double calculateCost(double distance, String serviceClass, int passengers, boolean hasDiscount) {
        double basePrice = distance * 0.08; // базовая стоимость за км
        double serviceMultiplier = serviceClass.equals("business") ? 1.5 : 1.0;
        double discount = hasDiscount ? 0.85 : 1.0; // скидка 15%
        return basePrice * serviceMultiplier * passengers * discount;
    }
}
