import java.util.Scanner;

public class Test {

    public static void print(Object line) {
        System.out.println(line);
    }


    public static void main(String[] args) {
     /*   try (Scanner in = new Scanner(System.in)) {
            Rational a = Rational.partsRational(in.nextLine());
            Rational b = Rational.partsRational(in.nextLine());
            a.testMe();
            b.testMe();
            System.out.println(Rational.summ(a, b));
            System.out.println(Rational.multiplicat(a, a));
            System.out.println(b.signum());
            System.out.println(a.compareTo(b));
        }
      */
        //  Rational a = Rational.getRandomNumber(3,2,10);
        //  Rational b = Rational.getRandomNumber(3,2,10);

        Rational[] s1 = new Rational[]{
                Rational.getRandomNumber(3, 2, 10, 100),
                Rational.getRandomNumber(3, 2, 10, 100),
                Rational.getRandomNumber(3, 2, 10, 100),
                Rational.getRandomNumber(3, 2, 10, 10)
        };

        Vector v1 = new Vector(s1);
        Vector v2 = new Vector(s1);
        VectorSystem vs = new VectorSystem(5,3,1,1,5,100);
        Rational r1 = new Rational(0, 5);
        System.out.println(vs.toString());
      /*  try {
            vs.combination(1,2,new Rational(3));
        } catch (ELinearException error){
            System.err.print(error);
        }
        */
        vs.includeInBasis(2,4);
        System.out.println(vs.toString());

        // System.out.println(Rational.sum(a, b));
        //   System.out.println(Rational.sum(a, b));
        //   System.out.println(Rational.multiply(a, a));
        //   System.out.println(b.signum());
        //   System.out.println(a.compareTo(b));
    }
}

