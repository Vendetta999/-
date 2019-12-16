public class VectorSystem {

    Vector[] vectors;
    Integer dimension;
    Integer vectorCount;

    public VectorSystem(Integer dimension, Integer vectorCount, int digits, int from, int to, int percentInteger) {
        this.dimension = dimension;
        this.vectorCount = vectorCount;
        vectors = new Vector[vectorCount + dimension];
        for (int i = 0; i < vectorCount; i++) {
            vectors[i] = new Vector(dimension, digits, from, to, percentInteger);
        }
        for (int i = 0; i < dimension; i++) {
            vectors[vectorCount + i] = new Vector(dimension);
            vectors[vectorCount + i].setComponentAt(i, new Rational(1));
        }

    }


    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < getStringLength(); j++) {
                str += (vectors[j].getComponentAt(i) + "   ");
            }
            str += "\r\n";
        }
        return str;
    }

    private int getStringLength() {
        return vectorCount + dimension;
    }

    public void multiplyByNumber(int row, Rational scalar) throws ELinearException {
        if (scalar.isZero()) {
            throw new ELinearException("Значение параметра 'scalar' является нулевым\r\n");
        }
        for (int j = 0; j < getStringLength(); j++) {
            var v = vectors[j];
            v.setComponentAt(row, Rational.multiply(v.getComponentAt(row), scalar));
            Integer a = 4 + 5;
        }
    }

    public void combination(int targetRow, int byRow, Rational scalar) throws ELinearException {
        if (scalar.isZero()) {
            throw new ELinearException("Значение параметра 'scalar' является нулевым\r\n");
        }
        for (int j = 0; j < getStringLength(); j++) {
            var v = vectors[j];
            var newValue = Rational.multiply(v.getComponentAt(byRow), scalar);
            newValue = Rational.sum(newValue, v.getComponentAt(targetRow));
            v.setComponentAt(targetRow, newValue);
        }

    }

    public void includeInBasis(int targetVector, int inBasis) {
        // targetColumn = 0
        // inBasis = 8
        //var row = getStringLength() - inBasis - 1;


        var byDivisionScalar = vectors[targetVector].getComponentAt(inBasis).getBack();
        try {
            this.multiplyByNumber(inBasis, byDivisionScalar);
        } catch (ELinearException error) {}

        System.out.println(this);

        for (int i = 0; i < dimension; i++) {
            if (i != inBasis) {
                try {
                    Rational quotient = vectors[targetVector].getComponentAt(i).getNegative();
                    System.out.println("q="+quotient);
                    this.combination(i,inBasis,quotient);
                } catch (ELinearException error) {}
            }
        }
    }
}
