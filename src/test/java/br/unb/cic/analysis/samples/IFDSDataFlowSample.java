package br.unb.cic.analysis.samples;

public class IFDSDataFlowSample {

    public void main(String[] args) {
    int x = secret();

    int y = 0;

    y = foo(x);

    System.out.println(y);
    }

    int foo(int p) {
        return p;
    }

    int secret() {
        return 10;
    }

}
