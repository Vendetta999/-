import java.lang.Cloneable;
import java.lang.Math;

public class Rational implements Cloneable, Comparable<Rational> {
    private Integer numerator;
    private Integer denominator;

    public Integer getNumerator() {
        return numerator;
    }

    public Integer getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        if (denominator == 0) throw new ArithmeticException("Division by Zero Error");
        int e = (denominator > 0) ? 1 : -1;
        this.denominator = denominator * e;
        this.numerator = this.numerator * e;
    }

    public Rational(Integer numerator, Integer denominator) {
        Integer d = gcd(numerator, denominator);
        this.numerator = numerator / d;
        this.setDenominator(denominator / d);
    }

    public Rational(Integer numerator) {
        this.numerator = numerator;
        this.setDenominator(1);
    }

    public static Rational partsRational(String s) {
        String[] parts = s.split("/");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Не правильно");
        }
        int numerator = Integer.parseInt(parts[0]);
        int denominator = Integer.parseInt(parts[1]);
        return new Rational(numerator, denominator);
    }

    public void testMe() {
        System.out.println("Hello I'm a rational number " + getNumerator() + "/" + getDenominator());
    }

    private Integer gcd(Integer p, Integer q) {
        if (q == 0) return p;
        else return gcd(q, p % q);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Rational)) return false;
        if (obj == this) return true;
        Rational o = (Rational) obj;
        if ((o.getNumerator() == this.getNumerator()) && (o.getDenominator() == this.getDenominator())) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * numerator + denominator;
    }

    @Override
    public Object clone() {
        Rational dst = new Rational(this.numerator, this.denominator);
        return dst;
    }

    @Override
    public int compareTo(Rational o) {
        // ToDo: throw exception
        Rational that = (Rational) o;
        return this.numerator * that.denominator - this.denominator * that.numerator;
    }

    public int signum() {
        if (this.numerator == 0) return 0;
        return this.numerator / Math.abs(this.numerator);
    }

    public boolean isNegative() {
        return numerator < 0;
    }

    public boolean isPositive() {
        return numerator > 0;
    }

    public boolean isZero() {
        return numerator == 0;
    }

    public static Rational sum(Rational a, Rational b) {
        return new Rational(a.getNumerator() * b.getDenominator() + b.getNumerator() * a.getDenominator(),
                a.getDenominator() * b.getDenominator()
        );
    }

    public static Rational pow(Rational a, int power) {
        if (a.denominator == 0) { return null; }
        int numerator = (int)Math.pow(a.numerator, power);
        int denominator = (int)Math.pow(a.denominator, power);
        return new Rational(numerator, denominator);
    }

    private static double random(int min, int max){
        return Math.random()*(max-min)+min;
    }

    /// Summery
    public static Rational getRandomNumber(int digits, int from, int to, int percentInteger) {
        int maxDenominator = (int)Math.pow(10,digits) - 1;
        int sgn = (Math.random() > 0.5) ? 1 : -1;
        int denominator = (int)random(1,maxDenominator);

        int percent = (percentInteger > 100) ? 100 : percentInteger;
        percent = (percentInteger < 0) ? 0 : percentInteger;

        int numerator;
        if (Math.random() <= percent * 0.01) {
            int answer = (int)random(from, to);
            numerator = (int)(answer * denominator) * sgn;
        } else {
            numerator = (int) random(from * denominator, to * denominator) * sgn;
        }
        return  new Rational(numerator,denominator);
    }

    public static Rational subtract(Rational a, Rational b) {
        return new Rational(a.getNumerator() * b.getDenominator() - b.getNumerator() * a.getDenominator(),
                a.getDenominator() * b.getDenominator()
        );
    }

    public static Rational multiply(Rational a, Rational b) {
        return new Rational(a.getNumerator() * b.getNumerator(),
                a.getDenominator() * b.getDenominator());
    }

    public static Rational divison(Rational a, Rational b) {
        return new Rational(a.getNumerator() * b.getDenominator(),
                a.getDenominator() * b.getNumerator());
    }

    public static Rational reverse(Rational c) {
        return new Rational(c.getDenominator(), c.getNumerator());
    }

    public String toString() {
        if (getDenominator() == 1) {
            return getNumerator().toString();
        }
        return getNumerator() + "/" + getDenominator();
    }

    // надо будет переименовать это что-то типо обратного числа
    public Rational getBack() {
        return new Rational(this.getDenominator(), this.getNumerator());
    }

    public Rational getNegative() {
        return new Rational(-this.getNumerator(), this.getDenominator());
    }

    public static Rational ZERO= new Rational(0);
}

