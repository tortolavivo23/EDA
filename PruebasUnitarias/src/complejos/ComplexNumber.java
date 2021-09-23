package complejos;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Objects;

/**
 *
 * @author mayte
 */

public class ComplexNumber{
    private double real, imaginary;


    /**
     * Constructor of the ComplexNumber
     * @param a real part
     * @param b imaginary part
     */
    public ComplexNumber(double a, double b){
        real = a;
        imaginary = b;
    }

    /**
     *
     * @return the real part of the ComplexNumber
     */
    public double realPart(){
        return real;
    }

    /**
     *
     * @return the imaginary part of the ComplexNumber
     */
    public double imaginaryPart(){
        return imaginary;
    }

    /**
     * Adds c to the ComplexNumber
     *
     * @param c
     *        number to add
     * @return
     *        this + c
     */
    public ComplexNumber add (ComplexNumber c){
        double r = this.real + c.real;
        double i = this.imaginary + c.imaginary;
        return new ComplexNumber(r, i);
    }

    /**
     * Returns the result of subtracting c from the ComplexNumber
     *
     * @param c
     *        subtracting
     * @return
     *        this - c
     */
    public ComplexNumber subtract (ComplexNumber c){
        double r = this.real - c.real;
        double i = this.imaginary - c.imaginary;
        return new ComplexNumber(r, i);
    }

    /**
     * Returns multiplication of c and the ComplexNumber
     *
     * @param c
     *        multiplying
     * @return
     *          this * c2
     */
    public ComplexNumber multiply (ComplexNumber c){
        double r = this.real * c.real - this.imaginary * c.imaginary;
        double i = this.imaginary * c.real+ this.real*c.imaginary;
        return new ComplexNumber(r, i);
    }

    /**
     * Returns the division of the ComplexNumber by c
     *
     * @param c
     *        divider
     * @return
     *        this / c
     */
    public ComplexNumber division (ComplexNumber c){
        double r = (this.real*c.real+ this.imaginary*c.imaginary)/(c.real*c.real+ c.imaginary*c.imaginary);
        double i = (this.imaginary*c.real- this.real*c.imaginary)/(c.real*c.real+ c.imaginary*c.imaginary);
        return new ComplexNumber(r,i);
    }

    /**
     * Returns the conjugate of the ComplexNumber
     *
     * @return
     *        a - ib
     */
    public ComplexNumber conjugate (){
        return new ComplexNumber(real, -imaginary);
    }

    /**
     * Returns the module of the ComplexNumber
     *
     * @return
     *      sqrt (a² + b²)
     */
    public double module (){
        return Math.sqrt(real*real+imaginary*imaginary);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexNumber that = (ComplexNumber) o;
        return Double.compare(that.real, real) == 0 && Double.compare(that.imaginary, imaginary) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imaginary);
    }
}
