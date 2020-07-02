package br.unb.cic.analysis.samples;

public class InterproceduralSourceSinkSample {
    public void main(String[] args) {
        int x = 0;
        int y = 10;

        x = secret(y);

        printIt(x);
    }

    int secret(int a) {
        return a*2;        //source
    }

    void printIt(int b) {
        System.out.println(b);      //sink
    }
}
