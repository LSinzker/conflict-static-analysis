package br.unb.cic.analysis.samples;

public class ArrayPositionsDataFlowSample {

    public static void main(String[] args) {
        int[] arr = {0,0,0,0,0};

        arr[4] = 10;        //source 1

        int b = arr[3];     //sink 1

        System.out.println(b); //sink 2

    }

}
