public class Denomination {
    private int onePesoCoin;
    private int fivePesoCoin;
    private int tenPesoCoin;
    private int twentyPesoCoin;
    private int twentyPesoBill;
    private int fiftyPesoBill;
    private int oneHundredPesoBill;
    private int twoHundredPesoBill;
    private int fiveHundredPesoBill;
    private int thousandPesoBill;

    public void refillMoney(int onePCoin, int fivePCoin, int tenPCoin,
                            int twentyPCoin, int twentyPBill, int fiftyPBill,
                            int oneHPBill, int twoHPBill, int fiveHPBill, int thousandPBill){
        this.onePesoCoin += onePCoin;
        this.fivePesoCoin += fivePCoin;
        this.tenPesoCoin += tenPCoin;
        this.twentyPesoCoin += twentyPCoin;
        this.twentyPesoBill += twentyPBill;
        this.fiftyPesoBill += fiftyPBill;
        this.oneHundredPesoBill += oneHPBill;
        this.twoHundredPesoBill += twoHPBill;
        this.fiveHundredPesoBill += fiveHPBill;
        this.thousandPesoBill += thousandPBill;
    }

    /* create individual setters and getters
     */
    public void setOnePesoCoin(int qty){
        this.onePesoCoin += qty;
    }
    public void setFivePesoCoin(int qty){
        this.fivePesoCoin += qty;
    }
    public void setTenPesoCoin(int qty){
        this.tenPesoCoin+=qty;
    }
    public void setTwentyPesoCoin(int qty){
        this.twentyPesoCoin +=qty;
    }
    public void setTwentyPesoBill(int qty){
        this.twentyPesoBill +=qty;
    }
    public void setFiftyPesoBill(int qty){
        this.fiftyPesoBill +=qty;
    }
    public void setOneHundredPesoBill(int qty){
        this.oneHundredPesoBill +=qty;
    }
    public void setTwoHundredPesoBill(int qty){
        this.twoHundredPesoBill +=qty;
    }
    public void setFiveHundredPesoBill(int qty){
        this.fiveHundredPesoBill +=qty;
    }
    public void setThousandPesoBill(int qty){
        this.thousandPesoBill+=qty;
    }

    public int getOnePesoCoin() {
        return onePesoCoin;
    }
    public int getFivePesoCoin() {
        return fivePesoCoin;
    }
    public int getTenPesoCoin() {
        return tenPesoCoin;
    }
    public int getTwentyPesoCoin() {
        return twentyPesoCoin;
    }
    public int getTwentyPesoBill() {
        return twentyPesoBill;
    }
    public int getFiftyPesoBill() {
        return fiftyPesoBill;
    }
    public int getOneHundredPesoBill() {
        return oneHundredPesoBill;
    }
    public int getTwoHundredPesoBill() {
        return twoHundredPesoBill;
    }
    public int getFiveHundredPesoBill() {
        return fiveHundredPesoBill;
    }
    public int getThousandPesoBill() {
        return thousandPesoBill;
    }


    public int getTotalMoney(){
        return this.onePesoCoin + (this.fivePesoCoin * 5) + (this.tenPesoCoin * 10) +
             ((this.twentyPesoCoin + this.twentyPesoBill) * 20) + (this.fiftyPesoBill * 50) +
              (this.oneHundredPesoBill * 100) + (this.twoHundredPesoBill * 200) +
              (this.fiveHundredPesoBill * 500) + (this.thousandPesoBill * 1000);
    }

}
