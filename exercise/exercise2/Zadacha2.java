package exercise2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Zadacha2 {

    public interface IObserver {
        void update(String stockSymbol, double newPrice);
    }

    public interface ISubject {
        void registerObserver(IObserver observer, String stockSymbol);
        void removeObserver(IObserver observer, String stockSymbol);
        void notifyObservers(String stockSymbol, double newPrice);
        Map<String, Double> getStockPrices();
    }


    public static class StockExchange implements ISubject {

        private final Map<String, Double> stockPrices = new HashMap<>();
        private final Map<String, List<IObserver>> observers = new HashMap<>();

        @Override
        public void registerObserver(IObserver observer, String stockSymbol) {
            observers.computeIfAbsent(stockSymbol, k -> new ArrayList<>()).add(observer);
            System.out.println("Наблюдатель добавлен для акции: " + stockSymbol);
        }

        @Override
        public void removeObserver(IObserver observer, String stockSymbol) {
            List<IObserver> stockObservers = observers.get(stockSymbol);
            if (stockObservers != null) {
                stockObservers.remove(observer);
                System.out.println("Наблюдатель удален с акции: " + stockSymbol);
            }
        }

        @Override
        public void notifyObservers(String stockSymbol, double newPrice) {
            List<IObserver> stockObservers = observers.get(stockSymbol);
            if (stockObservers != null) {
                for (IObserver observer : stockObservers) {
                    observer.update(stockSymbol, newPrice);
                }
            }
        }

        public void setStockPrice(String stockSymbol, double newPrice) {
            stockPrices.put(stockSymbol, newPrice);
            System.out.println("Цена акции " + stockSymbol + " изменена на " + newPrice);
            notifyObservers(stockSymbol, newPrice);
        }

        @Override
        public Map<String, Double> getStockPrices() {
            return stockPrices;
        }
    }


    public static class Trader implements IObserver {

        private final String name;

        public Trader(String name) {
            this.name = name;
        }

        @Override
        public void update(String stockSymbol, double newPrice) {
            System.out.println("Трейдер " + name + " уведомлен: Цена акции " + stockSymbol + " изменилась на " + newPrice);
        }
    }

    public static class TradingRobot implements IObserver {

        private final String name;
        private final double triggerPrice;

        public TradingRobot(String name, double triggerPrice) {
            this.name = name;
            this.triggerPrice = triggerPrice;
        }

        @Override
        public void update(String stockSymbol, double newPrice) {
            if (newPrice > triggerPrice) {
                System.out.println("Робот " + name + ": Цена акции " + stockSymbol + " выше " + triggerPrice + ". Покупка!");
            } else {
                System.out.println("Робот " + name + ": Цена акции " + stockSymbol + " ниже " + triggerPrice + ". Продажа!");
            }
        }
    }


    public static void main(String[] args) {
        // Создаем биржу
        StockExchange stockExchange = new StockExchange();


        Trader trader1 = new Trader("Иван");
        Trader trader2 = new Trader("Ольга");

        TradingRobot robot1 = new TradingRobot("Робот 1", 100.0);
        TradingRobot robot2 = new TradingRobot("Робот 2", 150.0);


        stockExchange.registerObserver(trader1, "AAPL");
        stockExchange.registerObserver(trader2, "GOOG");
        stockExchange.registerObserver(robot1, "AAPL");
        stockExchange.registerObserver(robot2, "GOOG");


        stockExchange.setStockPrice("AAPL", 120.0);
        stockExchange.setStockPrice("GOOG", 160.0);


        stockExchange.removeObserver(trader2, "GOOG");


        stockExchange.setStockPrice("AAPL", 80.0);
        stockExchange.setStockPrice("GOOG", 140.0);
    }
}
