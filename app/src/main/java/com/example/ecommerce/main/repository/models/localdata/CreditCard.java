package com.example.ecommerce.main.repository.models.localdata;

public class CreditCard {
    private String Cardnumber;
    private String Expdate;
    private  String cvv;

    public CreditCard(String cardnumber, String expdate, String cvv) {
        Cardnumber = cardnumber;
        Expdate = expdate;
        this.cvv = cvv;
    }

    public String getCardnumber() {
        return Cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        Cardnumber = cardnumber;
    }

    public String getExpdate() {
        return Expdate;
    }

    public void setExpdate(String expdate) {
        Expdate = expdate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
