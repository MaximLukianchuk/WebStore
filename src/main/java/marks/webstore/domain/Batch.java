package marks.webstore.domain;

import javax.persistence.*;

@Entity
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String date;
    private int amount;

    public Batch() {
    }

    public Batch(String date, int amount) {
        this.date = date;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setPrice(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
