// Контекст для использования различных стратегий
class TravelBookingContext {
    private ICostCalculationStrategy costCalculationStrategy;

    // Метод для установки стратегии
    public void setCostCalculationStrategy(ICostCalculationStrategy costCalculationStrategy) {
        this.costCalculationStrategy = costCalculationStrategy;
    }

    // Метод для расчета стоимости
    public double calculateCost(double distance, String serviceClass, int passengers, boolean hasDiscount) {
        if (costCalculationStrategy == null) {
            throw new IllegalStateException("Стратегия расчета стоимости не установлена.");
        }
        return costCalculationStrategy.calculateCost(distance, serviceClass, passengers, hasDiscount);
    }
}
