import java.util.ArrayList;

/**
 * Special class to be implemented later on.
 */
public class SpecialVM extends VendingMachine {

    private Denomination denomination;
    private ArrayList<Slot> productSlots;
    private Denomination insertedMoney;


    public SpecialVM(){
        super();
    }

    //use @Override to any parent method to change how it is implemented.

    //methods specific to this file to be implemented later...

    @Override
    public boolean dispenseProduct(int index){
        if(index==8){

        }else
            switch (index){
                case 0 -> {
                    boolean available = customProductChecher(0);
                }
                case 1 -> {
                    boolean available = customProductChecher(1);

                }
                case 2 -> {
                    boolean available = customProductChecher(2);

                }
                case 3 -> {
                    boolean available = customProductChecher(3);

                }
                case 4 -> {
                    boolean available = customProductChecher(4);

                    return true;
                }
                case 5 -> {
                    boolean available = customProductChecher(5);

                    return true;
                }
                case 6 -> {
                    boolean available = customProductChecher(6);

                    return true;
                }
                case 7 -> {
                    boolean available = customProductChecher(7);

                    return true;
                }

            }

        return false;
    }

    private boolean customProductChecher(int index){
        if(index==0 && (this.productSlots.get(0).getProductQuantity()>0)){
            boolean hehe = false; // filler lang muna
        }

        return false;
    }

   // private

}