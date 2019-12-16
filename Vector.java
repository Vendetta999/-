public class Vector implements IVector, Cloneable {

    public int dimention;
    private Rational[] components;

    public Vector(Rational[] components) {
        this.dimention = components.length;
        this.components = components;

    }



    public Vector(Integer dimension, int digits, int from, int to, int percentInteger){
        this(new Rational[dimension]);
        for (int i=0; i<components.length; i++)
        {
            components[i] =  Rational.getRandomNumber(digits,  from,  to,  percentInteger);
           // System.out.println(r);
        }
    }

    public Vector(Integer dimension){
        this(new Rational[dimension]);
        for (int i=0; i<components.length; i++){
            components[i]=  Rational.ZERO;
        }
    }

    private Vector toVector(IVector b) throws ELinearException {
        if (b == null) throw new NullPointerException();
        if (!(b instanceof Vector)) {
            throw new ELinearException("Несоответствие типов");
        }
        Vector v = (Vector)b;
        if (this.dimention != v.dimention) {throw new ELinearException("Несоответствие размерности"); }
        return v;
    }

    public void setComponentAt(int index, Rational value) {
        components[index] = value;
    }

    public Rational getComponentAt(int index) {
        return components[index];
    }

    public Vector add(IVector b) throws ELinearException {
        Vector  newVector = toVector(b);
        int length = this.dimention;
        Rational[] components = new Rational[length];
        for(int i = 0; i < length; i++) {
            components[i] = Rational.sum(this.getComponentAt(i),newVector.getComponentAt(i));
        }
        return new Vector(components);
    }

    public  Vector subtract(IVector b)  throws ELinearException {
        Vector  newVector = toVector(b);
        int length = this.dimention;
        Rational[] components = new Rational[length];
        for(int i = 0; i < length; i++) {
            components[i] = Rational.subtract(this.getComponentAt(i),newVector.getComponentAt(i));
        }
        return new Vector(components);
    }

    public Rational scalarProduct(IVector b) throws ELinearException {
        Vector  newVector = toVector(b);

        int length = this.dimention;
        Rational sum = new Rational(0,1);
        for(int i = 0; i < length; i++) {
            Rational multiply = Rational.multiply(this.getComponentAt(i), newVector.getComponentAt(i));
            sum = Rational.sum(sum, multiply);
        }
        return sum;
    }

    public boolean isOrtho(IVector b)  throws ELinearException {
        Rational scalar = scalarProduct(b);
        if (scalar == null || scalar.getNumerator() != 0) {
            return false;
        }
        return true;
    }

    public Vector multiplyByNumber(Rational number) throws ELinearException {
        int length = this.dimention;
        Rational components[] = new Rational[length];
        for(int i = 0; i < length; i++) {
            components[i] = Rational.multiply(this.getComponentAt(i), number);
        }
        return new Vector(components);
    }

    public boolean isZero() {
        for (Rational number: components) {
            if (!number.isZero()) {
                return false;
            }
        }
        return true;
    }

    public boolean isNonNegative() {
        for (Rational number: components) {
            if (number.compareTo(Rational.ZERO)<0) {
                return false;
            }
        }
        return true;
    }

    public  boolean isEquals( IVector b)  throws ELinearException {
        Vector  newVector = toVector(b);
        int length = this.dimention;
        for(int i = 0; i < length; i++) {
            if (this.getComponentAt(i) != newVector.getComponentAt(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String temp = "(";
        for(int i = 0; i < dimention; i++) {
            temp += components[i].toString();
            temp += ((i != dimention - 1) ? ", " : ")");
        }
        return temp;
        // (432/234, 234/24, 342/4)
    }

    @Override
    public Vector clone(){
        Vector newVector=new Vector(dimention);
        for (int i =0 ; i<dimention; i++){
            newVector.components[i] = (Rational)this.components[i].clone();
        }
        return newVector;
    }


    /*  public String toHumanString() {
        String numerator = "/";
        String temp = "|";
        String denominator = "\\";
        for(int i = 0; i < dimention; i++) {
            temp += components[i].toString();

            temp += (i != dimention - 1) ? ", " : ")";
        }
        return temp;
        // / 432  234  342 \
        //|  ---, ---, ---  |
        // \  23   34   2   /
    }
    */
}