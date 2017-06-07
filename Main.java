package com.company;
import java.io.*;



public class Main {
    static Polynomial universal_polynomial = new Polynomial();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        System.out.println("Which operation do you want to carry out - ");
        int operations = Integer.parseInt(br.readLine());
        switch (operations) {
            case 0:
                handleFFT();
                break;
            case 1:
                handleFFT_inverse();
                break;
        }
    }

    public static void handleFFT() throws Exception {
        int n = Integer.parseInt(br.readLine());
        Complex[] coff = new Complex[n];
        int j = 0;
        for (j = 0; j < n; j++) {
            float a = Float.parseFloat(br.readLine());
            float b = Float.parseFloat(br.readLine());
            coff[j] = new Complex(a, b);
        }
        Polynomial p = new Polynomial(n, coff);
        universal_polynomial.printpolynomial(p); //Debugging Purposes
        Polynomial dft = recursive_FFT(p);
        universal_polynomial.printpolynomial(dft);

    }

    public static void handleFFT_inverse() throws Exception {
        int n = Integer.parseInt(br.readLine());
        Complex[] coff = new Complex[n];
        int j = 0;
        for (j = 0; j < n; j++) {
            float a = Float.parseFloat(br.readLine());
            float b = Float.parseFloat(br.readLine());
            coff[j] = new Complex(a, b);
        }
        Polynomial p = new Polynomial(n, coff);
        universal_polynomial.printpolynomial(p); //Debugging Purposes
        Polynomial dft_inverse = recursive_FFT_inverse(p);
        universal_polynomial.printpolynomial(dft_inverse);


    }

    public static Polynomial recursive_FFT(Polynomial p) {
        int n = p.length;
        if (n == 1)
            return p;
        Cuberoots_unity cube = new Cuberoots_unity(n);
        Complex omega = new Complex(1, 0);
        Complex[] even_coff = new Complex[(n / 2)];
        Complex[] odd_coff = new Complex[(n / 2)];
        int j, k = 0, l = 0;
        for (j = 0; j < n; j++) {
            if (j % 2 == 0) {
                even_coff[l] = p.cofficients[j];
                l++;
            } else {
                odd_coff[k] = p.cofficients[j];
                k++;
            }
        }
        Polynomial even = new Polynomial((n / 2), even_coff);
        Polynomial odd = new Polynomial((n / 2), odd_coff);

        Polynomial dft_even = recursive_FFT(even);
        Polynomial dft_odd = recursive_FFT(odd);

        Complex[] dft_coff = new Complex[n];
        Complex c = new Complex();
        for (k = 0; k <= ((n / 2) - 1); k++) {
            dft_coff[k] = c.Add(dft_even.cofficients[k], (c.Multiply(omega, dft_odd.cofficients[k])));
            dft_coff[k + (n / 2)] = c.Sub(dft_even.cofficients[k], (c.Multiply(omega, dft_odd.cofficients[k])));
            omega = c.Multiply(omega, cube);
        }
        Polynomial dft = new Polynomial(n, dft_coff);
        return dft;
    }

    public static Polynomial recursive_FFT_inverse(Polynomial p) {
        int n = p.length;
        if (n == 1)
            return p;
        Cuberoots_unity cube = new Cuberoots_unity(-n);
        System.out.println(-n);
        Complex omega = new Complex(1, 0);
        Complex[] even_coff = new Complex[(n / 2)];
        Complex[] odd_coff = new Complex[(n / 2)];
        int j, k = 0, l = 0;
        for (j = 0; j < n; j++) {
            if (j % 2 == 0) {
                even_coff[l] = p.cofficients[j];
                l++;
            } else {
                odd_coff[k] = p.cofficients[j];
                k++;
            }
        }
        Polynomial even = new Polynomial((n / 2), even_coff);
        Polynomial odd = new Polynomial((n / 2), odd_coff);

        Polynomial dft_even = recursive_FFT_inverse(even);
        Polynomial dft_odd = recursive_FFT_inverse(odd);

        Complex[] dft_coff = new Complex[n];
        float tt = (float) n;
        System.out.println(tt);
        Complex t = new Complex((float) (1.0/tt),0);
        Complex c = new Complex();
        for (k = 0; k <= ((n / 2) - 1); k++) {
            dft_coff[k] = c.Multiply(c.Add(dft_even.cofficients[k], (c.Multiply(omega, dft_odd.cofficients[k]))),t);
            dft_coff[k + (n / 2)] =c.Multiply(c.Sub(dft_even.cofficients[k], (c.Multiply(omega, dft_odd.cofficients[k]))),t);
            omega = c.Multiply(omega, cube);
        }

        Polynomial dft_inverse = new Polynomial(n,dft_coff);
        return  dft_inverse;
    }
}
class Complex{
    float a;
    float b;
    public Complex(){
        a = 0.0f;
        b = 0.0f;
    }
    public Complex(float a,float b ){
        this.a = a;
        this.b = b;
    }
    public static Complex Add(Complex num1, Complex num2){
        float real_sum = num1.a+num2.a;
        float imaginary_sum = num1.b+num2.b;
        Complex sum = new Complex(real_sum,imaginary_sum);
        return sum;
    }
    public static Complex Sub(Complex num1, Complex num2){
        float real_diff = num1.a-num2.a;
        float imaginary_diff = num1.b-num2.b;
        Complex diff = new Complex(real_diff,imaginary_diff);
        return  diff;
    }
    public static Complex Multiply(Complex num1, Complex num2){
        float real_product = (num1.a*num2.a)-(num1.b*num2.b);
        float imaginary_product = (num1.a*num2.b)+(num1.b*num2.a);
        Complex product =  new Complex(real_product,imaginary_product);
        return product;
    }


}
class Polynomial{
    int n;
    Complex[] cofficients;
    int length;
    public Polynomial(){
        n = 0;
        cofficients = new Complex[0];
        length = 0;
    }
    public Polynomial(int n,Complex cofficients[]){
        this.n = n;
        this.cofficients = cofficients;
        this.length = this.n;
    }
    public static void printpolynomial(Polynomial p){
        int len = p.length;
        int j=0;
        System.out.println();
        for(j=0;j<len;j++)
        {
            System.out.print(p.cofficients[j].a + " "+p.cofficients[j].b+",");
        }
        System.out.println();
    }
}
class Cuberoots_unity extends Complex{
    public Cuberoots_unity(int n){
        super.a = (float)Math.cos(2*Math.PI/n);
        super.b = (float)Math.sin(2*Math.PI/n);
    }
}

