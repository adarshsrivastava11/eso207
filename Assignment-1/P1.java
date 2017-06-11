import java.util.Scanner;

public class Main {
    static Polynomial universal_polynomial = new Polynomial();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        int operations = sc.nextInt();
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
        int n = sc.nextInt();
        Complex[] coff = new Complex[n];
        int j = 0;
        for (j = 0; j < n; j++) {
            float a = sc.nextFloat();
            float b = sc.nextFloat();
            coff[j] = new Complex(a, b);
        }
        Polynomial p = new Polynomial(n, coff);
        Polynomial dft = recursive_FFT(p);
        universal_polynomial.printpolynomial(dft, n);

    }

    public static void handleFFT_inverse() throws Exception {
        int n = sc.nextInt();
        Complex[] coff = new Complex[n];
        int j;
        for (j = 0; j < n; j++) {
            float a = sc.nextFloat();
            float b = sc.nextFloat();
            coff[j] = new Complex(a, b);
        }
        Polynomial p = new Polynomial(n, coff);
        Polynomial dft_inverse = FFT_inverse(p);
        universal_polynomial.printpolynomial(dft_inverse,n);


    }

    public static Polynomial recursive_FFT(Polynomial p) {
        int n = p.length;
        if (n == 1)
            return p;
        Roots_unity cube = new Roots_unity(n);
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

    public static Polynomial FFT_inverse(Polynomial p) {
        int n = p.length;

        Polynomial dft_inverse = p;
        Complex c = new Complex();
        for(int i=0; i<n;i++) {
            dft_inverse.cofficients[i].a = p.cofficients[i].a;
            if(p.cofficients[i].b != 0)
                dft_inverse.cofficients[i].b = - p.cofficients[i].b;
        }
        dft_inverse = recursive_FFT(dft_inverse);
        for(int i=0; i<n;i++) {
            dft_inverse.cofficients[i].a = dft_inverse.cofficients[i].a;
            if(dft_inverse.cofficients[i].b != 0)
                dft_inverse.cofficients[i].b = - dft_inverse.cofficients[i].b;
        }

        for (int i = 0; i < n; i++) {
            dft_inverse.cofficients[i].a = (dft_inverse.cofficients[i].a)/n;
            if(dft_inverse.cofficients[i].b != 0)
                dft_inverse.cofficients[i].b = (dft_inverse.cofficients[i].b)/n;
            if(dft_inverse.cofficients[i].b < 0.000000000000002)
                dft_inverse.cofficients[i].b = Math.round(dft_inverse.cofficients[i].b * 100000) / 100000;  // 0
        }
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
    
    public static void printpolynomial(Polynomial p,int n){
        int len = p.length;
        int j=0;
        System.out.print(n+" ");
        for(j=0;j<len;j++)
        {

            if(p.cofficients[j].a == 0.0)
                System.out.print((int)p.cofficients[j].a+" ");
            else
                System.out.print(p.cofficients[j].a+" ");
            if(p.cofficients[j].b == 0.0)
                System.out.print((int)p.cofficients[j].b+" ");
            else
                System.out.print(p.cofficients[j].b+" ");
        }
        System.out.println();
    }
}

class Roots_unity extends Complex{
    public Roots_unity(float n){
        super.a = (float)Math.cos(2*Math.PI/n);
        super.b = (float)Math.sin(2*Math.PI/n);
    }

}


