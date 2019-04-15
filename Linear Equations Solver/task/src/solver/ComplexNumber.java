package solver;

public class ComplexNumber {
    private double re;
    private double im;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public void add(ComplexNumber complexNumber){
        this.re = this.re + complexNumber.re;
        this.im = this.im + complexNumber.im;
    }

    public void multiply(ComplexNumber complexNumber){
        double re = this.re * complexNumber.re - this.im * complexNumber.im;
        double im = this.re * complexNumber.im + this.im * complexNumber.re;
        this.re = re;
        this.im = im;
    }

    public void conjugate(){
        this.im = (-1) * this.im;
    }

    public void divide(ComplexNumber complexNumber){
        double re = (this.re * complexNumber.re + this.im * complexNumber.im) / (Math.pow(complexNumber.re, 2) + Math.pow(complexNumber.im, 2));
        double im = (this.im * complexNumber.re - this.re * complexNumber.im) / (Math.pow(complexNumber.re, 2) + Math.pow(complexNumber.im, 2));
        this.im = im;
        this.re = re;
    }

    public String toString(){
        String answer;
        if (this.im == 0){
            return String.valueOf(this.re);
        }
        if (this.re == 0){
            return String.valueOf(this.im) + 'i';
        }
        return String.valueOf(this.re) + (this.im > 0 ? '+': '-') + Math.abs(this.im) + 'i';
    }
}
