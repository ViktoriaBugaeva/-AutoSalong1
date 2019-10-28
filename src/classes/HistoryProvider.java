/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import entity.Buyer;
import entity.Car;
import entity.History;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class HistoryProvider {
    Scanner scanner = new Scanner(System.in);
    public History createHistory(List<Car>listCars,List<Buyer>listBuyers){
        
        History history = new History();
        System.out.println("Список автомобилей: ");
        for(int i=0; i<listCars.size();i++){
            if(listCars.get(i).getCount() > 0){
                System.out.printf("%d. Модель: %s, марка: %s, год выпуска: %d%n"
                    ,i+1
                    ,listCars.get(i).getModel()
                    ,listCars.get(i).getMarka()
                    ,listCars.get(i).getYear()
                );
            }
            
        }
        System.out.print("Выберите номер автомобиля:"); 
        int takeCarNum = scanner.nextInt();
        Car car = listCars.get(takeCarNum-1);
        car.setCount(car.getCount()-1);
        System.out.println("Список клиентов: ");
        int i=0;
        for(Buyer b : listBuyers){
            System.out.printf("%d. Имя и фамилия клиента: %s %s, email: %s%n"
                    ,i+1
                    ,b.getName()
                    ,b.getLastname()
                    ,b.getEmail()
            );
            i++;
        }
        System.out.print("Выберите номер клиента:"); 
        int buyerNum = scanner.nextInt();
        Buyer buyer = listBuyers.get(buyerNum-1);
        buyer.setMoney(buyer.getMoney()-car.getPrice());
        history.setCar(car);
        history.setBuyer(buyer);
        history.setTakeOn(new Date());
        return history;
    }
    public void returnCar(List<History> listHistories){
        
        System.out.println("Список проданных автомобилей");
        int i=1;
        for(History history : listHistories){
            if(history.getReturnDate() == null 
                    && history.getCar().getCount() < history.getCar().getQuantity()){
                System.out.printf("%d. Клиент %s %s купил %s%n"
                    ,i
                    ,history.getBuyer().getName()
                    ,history.getBuyer().getLastname()
                    ,history.getCar().getModel()
                );
            }
            i++;
        }
        System.out.println("Выберите возвращаемую книгу: ");
        int numHistory = scanner.nextInt();
        listHistories.get(numHistory-1).getCar().setCount(listHistories.get(numHistory-1).getCar().getCount()+1);
        listHistories.get(numHistory-1).setReturnDate(new Date());
        System.out.println("Книга \""
                +listHistories.get(numHistory-1).getCar().getModel()
                +"\" возвращена. Осталось книг "+listHistories.get(numHistory-1).getCar().getCount()
        );
    }
}
/*public class HistoryProvider {
    Scanner scanner = new Scanner(System.in);

    public History createHistory(List<Car> listCars, List<Buyer> listBuyers) {
        History history = new History();

        System.out.println("Список автомобилей: ");
        int countCurrentCars = 0;
        for (int i = 0; i < listCars.size(); i++) {
            if (listCars.get(i).getCount() > 0) {
                System.out.printf("%d. Марка: %s, модель: %s, дата выпуска: %d, стоимость: %s%n",
                         i + 1,
                         listCars.get(i).getMarka(),
                         listCars.get(i).getModel(),
                         listCars.get(i).getYear(),
                         listCars.get(i).getPrice()
                );
                countCurrentCars++;
            }
        }
        if (countCurrentCars == 0) {
            System.out.println("Все автомобили проданы.");
            return null;
        }
        System.out.print("Выберите номер покупаемого автомобиля: ");
        int takeCarNum = scanner.nextInt();
        Car car = listCars.get(takeCarNum - 1);
        if (car.getCount() > 0) {
            car.setCount(car.getCount()-1);
            System.out.println("Список клиентов: ");
            int i=0;
            for(Buyer b : listBuyers){
                System.out.printf("%d. Имя и фамилия клиента: %s%s, email: %s количество денег: %s%n"
                ,i+1
                ,b.getName()
                ,b.getLastname()
                ,b.getEmail()
                ,b.getMoney()
                );
                i++;
            }
            System.out.println("Выберите номер клиента: ");
            int buyerNum = scanner.nextInt();
            Buyer buyer = listBuyers.get(buyerNum-1);
            buyer.setMoney(buyer.getMoney()-car.getPrice());
            history.setCar(car);
            history.setBuyer(buyer);
            history.setTakeOn(new Date());
            return history;
        }else{
            System.out.println("Автомобили \""
            +car.getModel()
            +"\" уже все проданы."
            );
            return null;
        }
        public void Car (List<History> listHistories){
        
        System.out.println("Список читаемых книг");
        int i=1;
        for(History history : listHistories){
            if(history.getTakeOn() == null 
                    && history.getCar().getCount() < history.getCar().getQuantity()){
                System.out.printf("%d. Читатель %s %s читает книгу %s%n"
                    ,i
                    ,history.getBuyer().getName()
                    ,history.getBuyer().getLastname()
                    ,history.getCar().getModel()
                );
            }
            i++;
        }
    }
}
*/