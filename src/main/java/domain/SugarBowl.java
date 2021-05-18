package domain;

public class SugarBowl extends Dishes{
    public SugarBowl(String name, Material m, int volume) {
        super(name, m,volume);
        this.isEmpty = false;

    }
    public void addSugar(Cup cup)  {
        if (!this.isEmpty) {
            cup.withSugar = true;
        }
    }
    @Override
    public String toString() {
        return this.getName() + "{" +
                "volume=" + this.measure +
                ", fullOfSugar=" + !isEmpty +
                '}';
    }
}