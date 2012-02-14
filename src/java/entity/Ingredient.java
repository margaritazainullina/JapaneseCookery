package entity;

public class Ingredient {
    private String amount;
    private String unit;
    private String content;
    
    public Ingredient(){}
    public Ingredient(String amount, String unit, String content){
        setAmount(amount);
        setUnit(unit);
        setContent(content);
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}