package guru.springframework;

public class Money implements Expression {
    protected int amount;

    private final String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    protected String currency() {
        return this.currency;
    }

    public Expression times(int multiplier) {
        return new Money(amount * multiplier, this.currency());
    }

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    public boolean equals(Object object) {
        if(!(object instanceof Money)) {
            return false;
        }
        Money money = (Money) object;
        return amount == money.amount
                && this.currency.equals(money.currency);
    }

    public Expression plus(Expression addmend) {
        //return new Money(amount + addmend.amount, currency);
        return new Sum(this, addmend);
    }

    @Override
    public Money reduce(Bank bank, String to) {
        return new Money(amount / bank.rate(this.currency, to), to);
    }
}
