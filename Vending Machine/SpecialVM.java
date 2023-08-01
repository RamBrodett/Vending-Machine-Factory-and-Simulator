import java.util.ArrayList;

/**
 * Special class to be implemented later on.
 */
public class SpecialVM extends VendingMachine {
    int specialsSold = 0;


    public SpecialVM(){
        super();
        String[] sauce = {"Chocolate Sauce", "Strawberry Sauce", "Caramel Sauce"};
        String[] toppings = {"Rainbow Sprinkle"};
        //0-8 is occupied by yogurt
        for(int i = 9; i<13; i++){
            if(i < 12){
                productSlots.add(new Slot(sauce[i-9],15,15));
            }
            else productSlots.add(new Slot(toppings[i-12],10,15));
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
                if(index==8){

                }else
                    switch (index){
                        case 0 -> {
                            if(customProductChecher(0)){
                                String special = "Triple Chocolate";
                                transaction(150,special);
                                return true;
                            }
                        }
                        case 1 -> {
                            if(customProductChecher(1)){
                                String special = "VitaMax";
                                transaction(150,special);
                                return true;
                            }

                        }
                        case 2 -> {
                            if(customProductChecher(2)){
                                String special = "Strawberry Duo";
                                transaction(150,special);
                                return true;
                            }
                        }
                        case 3 -> {
                            if(customProductChecher(3)){
                                String special = "ChocoDuos";
                                transaction(150,special);
                                return true;
                            }
                        }
                        case 4 -> {
                            if(customProductChecher(4)){
                                String special = "ChocoMatcha Madness";
                                transaction(150,special);
                                return true;
                            }

                        }
                        case 5 -> {
                            if(customProductChecher(5)){
                                String special = "Mango Chocolate Glazed";
                                transaction(150,special);
                                return true;
                            }

                        }
                        case 6 -> {
                            if(customProductChecher(6)){
                                String special = "Berrylicious Crunchies";
                                transaction(150,special);
                                return true;
                            }

                        }
                        case 7 -> {
                            if(customProductChecher(7)){
                                String special = "Rainbow Yogurt";
                                transaction(150,special);
                                return true;
                            }
                        }
                    }

            }

        }

        return false;
    }
/*
" 0 Vanilla", " 1 Chocolate", " 2 Matcha", " 3 Choco Chips", " 4 Cereals",
                "5 Mixed Fruit Bits", "6 Raspberry", "7 Starberry","8 Mango"
 "9 Chocolate Sauce", "10 Strawberry Sauce", "11 Caramel Sauce", "12 Rainbow Sprinkle"
 */
    private boolean customProductChecher(int index){
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
                System.out.println("Transaction successful.");
                System.out.println("Dispensing " + name);
                differenceDenomination(denomination,changeDenom);
                insertedMoney = changeDenom;
                specialsSold++;
                System.out.printf("Your change is %.2f\n",change);
                displayDenominations(changeDenom);
                return true;
            }else System.out.println("Insufficient balance. Unsuccessful transaction.\n");

        } System.out.println("Sorry! Ingredients needed are out of stock.\n");

        return false;
   }

}