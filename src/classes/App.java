/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import entity.Car;
import entity.History;
import entity.Buyer;
import interfaces.Saveble;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class App {
    List<Car> listCars = new ArrayList<>();
    List<Buyer> listBuyers = new ArrayList<>();
    List<History> listHistories = new ArrayList<>();
    Saveble saveble;
    public App(String flag) {
        if(flag.equals("base")){
            saveble = new SaveToBase();
        }else if(flag.equals("file")){
            saveble = new SaveToFile();
        }else{
            saveble = new SaveToBase();
        }
        listCars = saveble.loadCars();
        listBuyers = saveble.loadBuyers();
        listHistories = saveble.loadHistories();
    }
    
    public void run(){
        Scanner scanner = new Scanner(System.in);
        
        HistoryProvider historyProvider = new HistoryProvider();
                           
        boolean flagExit = true;
        do{
            System.out.println("Список задач:");
            System.out.println("0. Закрыть программу");
            System.out.println("1. Новый автомобиль");
            System.out.println("2. Новый клиент");
            System.out.println("3. Список автомобилей");
            System.out.println("4. Список клиентов");
            System.out.println("5. Продать автомобиль");
            //System.out.println("6. Вернуть книгу");
            System.out.println("6. Список проданных автомобилей");
            System.out.println("Введите номер задачи:");
            String numberTask = scanner.nextLine();
            if(null != numberTask)
            switch (numberTask) {
                case "0":
                    flagExit = false;
                    System.out.println("Заканчиваем работу программы");
                    break;
                case "1":
                    System.out.println("Новый автомобиль.");
                    CarProvider carProvider = new CarProvider();
                    Car car = carProvider.createCar();
                    listCars.add(car);
                    saveble.saveCars(listCars);
                    for(Car c : listCars){
                       System.out.println(c.toString()); 
                    }
                    break;
                case "2":
                    System.out.println("Новый покупатель.");
                    BuyerProvider buyerProvider = new BuyerProvider();
                    Buyer buyer = buyerProvider.createBuyer();
                    listBuyers.add(buyer);
                    saveble.saveBuyers(listBuyers);
                    for(Buyer b : listBuyers){
                       System.out.println(b.toString()); 
                    }
                    break;
                case "3":
                    System.out.println("Список автомобилей:");
                    int i = 1;
                    for(Car c : listCars){
                        System.out.println(i+". "+c.toString());
                        i++;
                    }
                    break;
                case "4":
                    System.out.println("Список покупателей:");
                    for(int j=0;j<listBuyers.size();j++){
                        System.out.println(j+1+". "+listBuyers.get(j).toString());
                    }
                    break;
                case "5":
                    System.out.println("Продать автомобиль");
                    History history = historyProvider.createHistory(listCars, listBuyers);
                    listHistories.add(history);
                    saveble.saveHistories(listHistories);
                    break;
                /*case "6":
                    System.out.println("Возвращение книги");
                    historyProvider.returnCar(listHistories);
                    saveble.saveHistories(listHistories);
                    break;*/
                case "6":
                    System.out.println("Список проданных автомобилей");
                    i = 1;
                    for(History h : listHistories){
                        if(h.getReturnDate() == null){
                            System.out.println(i+". "+h.toString());
                            i++;
                        }
                    }
                    if(i < 2){
                        System.out.println("Нет проданных автомобилей");
                        System.out.println();
                    }
                    break;
            }
        }while(flagExit);
    }
}