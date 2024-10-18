package exercise1;

class TravelBookingContext {
    private ICostCalculationStrategy costCalculationStrategy;


    public void setCostCalculationStrategy(ICostCalculationStrategy costCalculationStrategy) {
        this.costCalculationStrategy = costCalculationStrategy;
    }


    public double calculateCost(double distance, String serviceClass, int passengers, boolean hasDiscount) {
        if (costCalculationStrategy == null) {
            throw new IllegalStateException("Стратегия расчета стоимости не установлена.");
        }
        return costCalculationStrategy.calculateCost(distance, serviceClass, passengers, hasDiscount);
    }
}
