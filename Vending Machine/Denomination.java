/**
 * @author BRODETT,Ram David
 * @author REGALADO, Luke M
 * @version 07/01/2023
 */

/**
 * This class contains attributes, which are the denominations.
 * The values inside these attributes are the amount of 'bills'.
 */
public class Denomination {
    /**
     * Represents amount of "one peso coins" in the denomination.
     */
    private int onePesoCoin;

    /**
     * Represents amount of "five peso coins" in the denomination.
     */
    private int fivePesoCoin;

    /**
     * Represents amount of "ten peso coins" in the denomination.
     */
    private int tenPesoCoin;

    /**
     * Represents amount of "twenty peso coins" in the denomination.
     */
    private int twentyPesoCoin;

    /**
     * Represents amount of "twenty peso bills" in the denomination.
     */
    private int twentyPesoBill;

    /**
     * Represents amount of "fifty peso bills" in the denomination.
     */
    private int fiftyPesoBill;

    /**
     * Represents amount of "one hundred peso bills" in the denomination.
     */
    private int oneHundredPesoBill;

    /**
     * Represents amount of "two hundred peso bills" in the denomination.
     */
    private int twoHundredPesoBill;

    /**
     * Represents amount of "five hundred peso bills" in the denomination.
     */
    private int fiveHundredPesoBill;

    /**
     * Represents amount of "one thousand peso bills" in the denomination.
     */
    private int thousandPesoBill;

    Denomination(){
        setOnePesoCoin(0);
        setFivePesoCoin(0);
        setTenPesoCoin(0);
        setTwentyPesoCoin(0);
        setTwentyPesoBill(0);
        setFiftyPesoBill(0);
        setOneHundredPesoBill(0);
        setTwoHundredPesoBill(0);
        setFiveHundredPesoBill(0);
        setThousandPesoBill(0);
    }

    /**
     * Creation of defined nuber of denomination of Philippine peso.
     * @param onePHP 1 pesos qty
     * @param fivePHP 5 pesos qty
     * @param tenPHP 10 pesos qty
     * @param twentyCPHP  20 pesos  coin qty
     * @param twentyBPHP 20 pesos bill qty
     * @param fiftyPHP 50 pesos bill qty
     * @param oneHPHP 100 pesos bill qty
     * @param twoHPHP 200 pesos bill qty
     * @param fiveHPHP 500 pesos bill qty
     * @param oneKPHP 1000 pesos bill qty
     */
    Denomination(int onePHP, int fivePHP, int tenPHP, int twentyCPHP, int twentyBPHP,
                 int fiftyPHP, int oneHPHP, int twoHPHP, int fiveHPHP, int oneKPHP){
        setOnePesoCoin(onePHP);
        setFivePesoCoin(fivePHP);
        setTenPesoCoin(tenPHP);
        setTwentyPesoCoin(twentyCPHP);
        setTwentyPesoBill(twentyBPHP);
        setFiftyPesoBill(fiftyPHP);
        setOneHundredPesoBill(oneHPHP);
        setTwoHundredPesoBill(twoHPHP);
        setFiveHundredPesoBill(fiveHPHP);
        setThousandPesoBill(oneKPHP);
    }
    /**
     * Setter for one peso coin. Assigns via increment.
     * @param qty Accepts amount to add to attribute.
     */
    public void setOnePesoCoin(int qty){
        this.onePesoCoin += qty;
    }

    /**
     * Setter for five peso coin. Assigns via increment.
     * @param qty Accepts amount to add to attribute.
     */
    public void setFivePesoCoin(int qty){
        this.fivePesoCoin += qty;
    }

    /**
     * Setter for ten peso coin. Assigns via increment.
     * @param qty Accepts amount to add to attribute.
     */
    public void setTenPesoCoin(int qty){
        this.tenPesoCoin+=qty;
    }

    /**
     * Setter for twenty peso coin. Assigns via increment.
     * @param qty Accepts amount to add to attribute.
     */
    public void setTwentyPesoCoin(int qty){
        this.twentyPesoCoin +=qty;
    }

    /**
     * Setter for twenty peso bill. Assigns via increment.
     * @param qty Accepts amount to add to attribute.
     */
    public void setTwentyPesoBill(int qty){
        this.twentyPesoBill +=qty;
    }

    /**
     * Setter for fifty peso bill. Assigns via increment.
     * @param qty Accepts amount to add to attribute.
     */
    public void setFiftyPesoBill(int qty){
        this.fiftyPesoBill +=qty;
    }

    /**
     * Setter for one hundred peso bill. Assings via increment.
     * @param qty Accepts amount to add to attribute.
     */
    public void setOneHundredPesoBill(int qty){
        this.oneHundredPesoBill +=qty;
    }

    /**
     * Setter for two hundred peso bill. Assings via increment.
     * @param qty Accepts amount to add to attribute.
     */
    public void setTwoHundredPesoBill(int qty){
        this.twoHundredPesoBill +=qty;
    }

    /**
     * Setter for five hundred peso bill. Assings via increment.
     * @param qty Accepts amount to add to attribute.
     */
    public void setFiveHundredPesoBill(int qty){
        this.fiveHundredPesoBill +=qty;
    }

    /**
     * Setter for one thousand peso bill. Assings via increment.
     * @param qty Accepts amount to add to attribute.
     */
    public void setThousandPesoBill(int qty){
        this.thousandPesoBill+=qty;
    }

    /**
     * Getter for one peso coin.
     * @return Returns value of attribute. Returns value of attribute
     */
    public int getOnePesoCoin() {
        return onePesoCoin;
    }

    /**
     * Getter for five peso coin.
     * @return Returns value of attribute.
     */
    public int getFivePesoCoin() {
        return fivePesoCoin;
    }

    /**
     * Getter for ten peso coin.
     * @return Returns value of attribute.
     */
    public int getTenPesoCoin() {
        return tenPesoCoin;
    }

    /**
     * Getter for twenty peso coin.
     * @return Returns value of attribute.
     */
    public int getTwentyPesoCoin() {
        return twentyPesoCoin;
    }

    /**
     * Getter for twenty peso bill.
     * @return Returns value of attribute.
     */
    public int getTwentyPesoBill() {
        return twentyPesoBill;
    }

    /**
     * Getter for fifty peso bill.
     * @return Returns value of attribute.
     */
    public int getFiftyPesoBill() {
        return fiftyPesoBill;
    }

    /**
     * Getter for one hundred peso bill.
     * @return Returns value of attribute.
     */
    public int getOneHundredPesoBill() {
        return oneHundredPesoBill;
    }

    /**
     * Getter for two hundred peso bill.
     * @return Returns value of attribute.
     */
    public int getTwoHundredPesoBill() {
        return twoHundredPesoBill;
    }

    /**
     * Getter for five hundred peso bill.
     * @return Returns value of attribute.
     */
    public int getFiveHundredPesoBill() {
        return fiveHundredPesoBill;
    }

    /**
     * Getter for one thousand peso bill.
     * @return Returns value of attribute.
     */
    public int getThousandPesoBill() {
        return thousandPesoBill;
    }

    /**
     * Calculates for the total money in the denomination, where it returns the value
     * in attribute * its corresponding denomination.
     * @return Returns total money in the denomination.
     */
    public float getTotalMoney(){
        return this.onePesoCoin + (this.fivePesoCoin * 5) + (this.tenPesoCoin * 10) +
             ((this.twentyPesoCoin + this.twentyPesoBill) * 20) + (this.fiftyPesoBill * 50) +
              (this.oneHundredPesoBill * 100) + (this.twoHundredPesoBill * 200) +
              (this.fiveHundredPesoBill * 500) + (this.thousandPesoBill * 1000);
    }

}
