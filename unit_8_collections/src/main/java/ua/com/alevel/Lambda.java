package ua.com.alevel;

public class Lambda {

    public void sum() {
        MatSum matSum1 = new MatSum() {
            @Override
            public int sum(int a, int b) {
                return 0;
            }
        };

        Print print = new Print() {
            @Override
            public void print() {

            }
        };

        print = () -> {};

        MatSum matSum2 = (a, b) -> a + b;

        System.out.println(matSum2.sum(2, 8));
        MatSum matSum3 = Integer::sum;
    }

    public void test2() {
        Some1Ifc some1Ifc = test();
    }

    public Some1Ifc test() {
        Some1IfcImpl some1Ifc = new Some1IfcImpl();
        Some1Ifc some1Ifc1 = new Some1IfcImpl();
        Some1Ifc anonimSome1Ifc = new Some1Ifc()
        {
            @Override
            public void a() {

            }

            @Override
            public void b() {

            }
        };

        return new Some1Ifc()
        {
            @Override
            public void a() {

            }

            @Override
            public void b() {

            }

            public void bla() {

            }
        };
    }
}
