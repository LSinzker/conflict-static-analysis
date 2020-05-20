package br.unb.cic.analysis.samples;

public class ArrayWithTransitivitySample {
    public static void main(String[] args) {
        int arr[] = {0,0,0,0,0};

        arr[1] = 2;     //source

        int a = arr[2];

        System.out.println(a);  //sink

    }
}
