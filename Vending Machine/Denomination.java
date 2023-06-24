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
        this.onePesoCoin = onePCoin;
        this.fivePesoCoin = fivePCoin;
        this.tenPesoCoin = tenPCoin;
        this.twentyPesoCoin = twentyPCoin;
        this.twentyPesoBill = twentyPBill;
        this.fiftyPesoBill = fiftyPBill;
        this.oneHundredPesoBill = oneHPBill;
        this.twoHundredPesoBill = twoHPBill;
        this.fiveHundredPesoBill = fiveHPBill;
        this.thousandPesoBill = thousandPBill;
    }

    /* create individual setters and getters
     --
     --
     --
     --
     --
     --
     --
     --
     */

    public double getTotalMoney(){
        return this.onePesoCoin + (this.fivePesoCoin * 5) + (this.tenPesoCoin * 10) +
             ((this.twentyPesoCoin + this.twentyPesoBill) * 20) + (this.fiftyPesoBill * 50) +
              (this.oneHundredPesoBill * 100) + (this.twoHundredPesoBill * 200) +
              (this.fiveHundredPesoBill * 500) + (this.thousandPesoBill * 1000);
    }

}
