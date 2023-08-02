import java.util.ArrayList;

/**
 * Special class to be implemented later on.
 */
public class SpecialVM extends VendingMachine {
    int specialsSold = 0;

    String[] sauce = {"Chocolate Sauce", "Strawberry Sauce", "Caramel Sauce"};
    String[] toppings = {"Rainbow Sprinkle"};


    public SpecialVM(){
        super();
        //0-8 is occupied by individual items
        for(int i = 9; i<13; i++){
            if(i < 12){
                productSlots.add(new Slot(sauce[i-9],15,15)); // add sauces on 9-11th index
            }
            else productSlots.add(new Slot(toppings[i-12],10,15)); // add rainbow sprinkle on 12th index
        }
    }

    //use @Override to any parent method to change how it is implemented.

    //methods specific to this file to be implemented later...


    @Override
    public boolean dispenseProduct(int index, int c){
        switch (c){
            case 1 ->{
                if(!(isSlotEmpty(index))){
                    if(productSlots.get(index).getBaseProductPrice()<= getTotalInsertedMoney()){
                        float change = (float) (getTotalInsertedMoney() - productSlots.get(index).getBaseProductPrice()); // needed change
                        addToDenomination(denomination,insertedMoney);
                        Denomination changeDenom = findDenomination(change,denomination);
                        if(!(changeDenom.getTotalMoney() - change != 0)){
                            System.out.println("Transaction successful.");
                            System.out.println("Dispensing " + productSlots.get(index).getBaseProductName());
                            dispenser(index);
                            differenceDenomination(denomination,changeDenom);
                            insertedMoney = changeDenom;
                            productSlots.get(index).setNumProductsSold(1);
                            System.out.printf("Your change is %.2f\n",change);
                            displayDenominations(changeDenom);
                            return true;
                        }
                        else System.out.println("Unable to give change. Unsuccessful transaction.\n");

                    }else System.out.println("Insufficient balance. Unsuccessful transaction.\n");

                }else System.out.println("Sorry! Item is out of stock.\n");

            }
            case 2 ->{
                if(index==8){   ///////CUSTOM ITEM

                }else
                    switch (index){
                        case 0 -> {
                            if(customProductChecker(0)){
                                String special = "Triple Chocolate";
                                transaction(150,special);
                                removeSpecialItem(0);
                                return true;
                            } else System.out.println("Sorry! Ingredients needed are out of stock.\n");
                        }
                        case 1 -> {
                            if(customProductChecker(1)){
                                String special = "VitaMax";
                                transaction(150,special);
                                removeSpecialItem(1);
                                return true;
                            } else System.out.println("Sorry! Ingredients needed are out of stock.\n");

                        }
                        case 2 -> {
                            if(customProductChecker(2)){
                                String special = "Strawberry Duo";
                                transaction(150,special);
                                removeSpecialItem(2);
                                return true;
                            } else System.out.println("Sorry! Ingredients needed are out of stock.\n");
                        }
                        case 3 -> {
                            if(customProductChecker(3)){
                                String special = "ChocoDuos";
                                transaction(150,special);
                                removeSpecialItem(3);
                                return true;
                            } else System.out.println("Sorry! Ingredients needed are out of stock.\n");
                        }
                        case 4 -> {
                            if(customProductChecker(4)){
                                String special = "ChocoMatcha Madness";
                                transaction(150,special);
                                removeSpecialItem(4);
                                return true;
                            } else System.out.println("Sorry! Ingredients needed are out of stock.\n");

                        }
                        case 5 -> {
                            if(customProductChecker(5)){
                                String special = "Mango Chocolate Glazed";
                                transaction(150,special);
                                removeSpecialItem(5);
                                return true;
                            } else System.out.println("Sorry! Ingredients needed are out of stock.\n");

                        }
                        case 6 -> {
                            if(customProductChecker(6)){
                                String special = "Berrylicious Crunchies";
                                transaction(150,special);
                                removeSpecialItem(6);
                                return true;
                            } else System.out.println("Sorry! Ingredients needed are out of stock.\n");

                        }
                        case 7 -> {
                            if(customProductChecker(7)){
                                String special = "Rainbow Yogurt";
                                transaction(150,special);
                                removeSpecialItem(7);
                                return true;
                            } else System.out.println("Sorry! Ingredients needed are out of stock.\n");
                        }
                    }

            }

        }

        return false;
    }

    private void removeSpecialItem(int index){
        switch (index) {

            case 0 -> {
                dispenser(1);
                dispenser(9);
                dispenser(3);
            }
            case 1 -> {
                dispenser(0);
                dispenser(5);
                dispenser(4);
            }
            case 2 -> {
                dispenser(0);
                dispenser(7);
                dispenser(10);

            }
            case 3 -> {
                dispenser(0);
                dispenser(1);
                dispenser(9);
            }
            case 4 -> {
                dispenser(2);
                dispenser(9);
                dispenser(3);
            }
            case 5 -> {
                dispenser(8);
                dispenser(9);
            }
            case 6 -> {
                dispenser(6);
                dispenser(4);
            }
            case 7 -> {
                dispenser(0);
                dispenser(12);
            }
        }
    }

    /*
    " 0 Vanilla", " 1 Chocolate", " 2 Matcha", " 3 Choco Chips", " 4 Cereals",
                    "5 Mixed Fruit Bits", "6 Raspberry", "7 Starberry","8 Mango"
     "9 Chocolate Sauce", "10 Strawberry Sauce", "11 Caramel Sauce", "12 Rainbow Sprinkle"
     */
    private boolean customProductChecker(int index){
        if((index == 0) && ((productSlots.get(1).getProductQuantity() > 0) &&
                (productSlots.get(9).getProductQuantity()>0)&&
                (productSlots.get(3).getProductQuantity()>0))){
            return true;
            //triple chocolate
        }
        else if((index == 1) && ((productSlots.get(0).getProductQuantity() > 0) &&
                (productSlots.get(5).getProductQuantity()>0)&&
                (productSlots.get(4).getProductQuantity()>0))){
            //vitamax
            return true;
        }
        else if((index == 2) && ((productSlots.get(0).getProductQuantity() > 0) &&
                (productSlots.get(7).getProductQuantity()>0)&&
                (productSlots.get(10).getProductQuantity()>0))){
            //strawberry duo
            return true;
        }
        else if((index == 3) && ((productSlots.get(0).getProductQuantity() > 0) &&
                (productSlots.get(1).getProductQuantity()>0)&&
                (productSlots.get(9).getProductQuantity()>0))){
            //choco duo
            return true;
        }
        else if((index == 4) && ((productSlots.get(2).getProductQuantity() > 0) &&
                (productSlots.get(9).getProductQuantity()>0)&&
                (productSlots.get(3).getProductQuantity()>0))){
            //ChocoMatcha Madness
            return true;
        }
        else if((index == 5) && ((productSlots.get(8).getProductQuantity() > 0) &&
                (productSlots.get(9).getProductQuantity()>0))){
            //Mango Chocolate Glazed
            return true;
        }
        else if((index == 6) && ((productSlots.get(6).getProductQuantity() > 0) &&
                (productSlots.get(4).getProductQuantity()>0))){
            //Berrylicious Crunchies
            return true;
        }
        else if((index == 7) && ((productSlots.get(0).getProductQuantity() > 0) &&
                (productSlots.get(12).getProductQuantity()>0))){
            //Rainbow Yogurt
            return true;
        }
        return false;
    }

    private boolean transaction(float price,String name){
        if(price <= getTotalInsertedMoney()){
            float change = (float) (getTotalInsertedMoney()-price);
            addToDenomination(denomination,insertedMoney);
            Denomination changeDenom = findDenomination(change,denomination);
            if(!(changeDenom.getTotalMoney()-change!=0)){
                switch (name){
                    case "Triple Chocolate" ->{
                        System.out.println("Preparing Special Pre-set Yogurt");
                        System.out.println("Dispensing cup ...");
                        System.out.println("Adding " + productSlots.get(1).getBaseProductName()+" yogurt ...");
                        System.out.println("Dispensing " + productSlots.get(9).getBaseProductName()+" ...");
                        System.out.println("Dispensing " + productSlots.get(3).getBaseProductName()+" ...");
                        System.out.println("Yogurt done");
                    }
                    case "VitaMax" ->{
                        System.out.println("Preparing Special Pre-set Yogurt");
                        System.out.println("Dispensing cup ...");
                        System.out.println("Adding " + productSlots.get(0).getBaseProductName()+" yogurt ...");
                        System.out.println("Dispensing " + productSlots.get(5).getBaseProductName()+" ...");
                        System.out.println("Dispensing " + productSlots.get(4).getBaseProductName()+" ...");
                        System.out.println("Yogurt done");
                    }
                    case "Strawberry Duo" ->{
                        System.out.println("Preparing Special Pre-set Yogurt");
                        System.out.println("Dispensing cup ...");
                        System.out.println("Adding 1/2 " + productSlots.get(0).getBaseProductName()+" yogurt ...");
                        System.out.println("Adding 1/2 " + productSlots.get(7).getBaseProductName()+" yogurt ...");
                        System.out.println("Dispensing " + productSlots.get(10).getBaseProductName()+" ...");
                        System.out.println("Yogurt done");
                    }
                    case "ChocoDuos" ->{
                        System.out.println("Preparing Special Pre-set Yogurt");
                        System.out.println("Dispensing cup ...");
                        System.out.println("Adding 1/2 " + productSlots.get(0).getBaseProductName()+" yogurt ...");
                        System.out.println("Adding 1/2" + productSlots.get(1).getBaseProductName()+" yogurt ...");
                        System.out.println("Dispensing " + productSlots.get(9).getBaseProductName()+" ...");
                        System.out.println("Yogurt done");

                    }
                    case "ChocoMatcha Madness" ->{
                        System.out.println("Preparing Special Pre-set Yogurt");
                        System.out.println("Dispensing cup ...");
                        System.out.println("Adding " + productSlots.get(2).getBaseProductName()+" yogurt ...");
                        System.out.println("Dispensing " + productSlots.get(9).getBaseProductName()+" ...");
                        System.out.println("Dispensing " + productSlots.get(3).getBaseProductName()+" ...");
                        System.out.println("Yogurt done");

                    }
                    case "Mango Chocolate Glazed" ->{
                        System.out.println("Preparing Special Pre-set Yogurt");
                        System.out.println("Dispensing cup ...");
                        System.out.println("Adding " + productSlots.get(8).getBaseProductName()+" yogurt ...");
                        System.out.println("Dispensing " + productSlots.get(9).getBaseProductName()+" ...");
                        System.out.println("Yogurt done");

                    }
                    case "Berrylicious Crunchies" ->{
                        System.out.println("Preparing Special Pre-set Yogurt");
                        System.out.println("Dispensing cup ...");
                        System.out.println("Adding " + productSlots.get(6).getBaseProductName()+" yogurt ...");
                        System.out.println("Dispensing " + productSlots.get(4).getBaseProductName()+" ...");
                        System.out.println("Yogurt done");

                    }
                    case "Rainbow Yogurt" ->{
                        System.out.println("Preparing Special Pre-set Yogurt");
                        System.out.println("Dispensing cup ...");
                        System.out.println("Adding " + productSlots.get(0).getBaseProductName()+" yogurt ...");
                        System.out.println("Dispensing " + productSlots.get(12).getBaseProductName()+" ...");
                        System.out.println("Yogurt done");
                    }
                }
                System.out.println("Transaction successful.");
                System.out.println("Dispensing " + name);
                differenceDenomination(denomination,changeDenom);
                insertedMoney = changeDenom;
                specialsSold++;
                System.out.printf("Your change is %.2f\n",change);
                displayDenominations(changeDenom);
                return true;
            }else System.out.println("Insufficient balance. Unsuccessful transaction.\n");

        }

        return false;
    }

}