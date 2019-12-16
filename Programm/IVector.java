public interface IVector extends Cloneable {
    Vector add(IVector b) throws ELinearException;
    Vector subtract(IVector b) throws ELinearException;
    Rational scalarProduct(IVector b) throws ELinearException;
    boolean isOrtho(IVector b) throws ELinearException;
    Vector multiplyByNumber(Rational number) throws ELinearException ;
    boolean isZero();
    boolean isNonNegative();
}
