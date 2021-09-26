package test;

import complejos.ComplexNumber;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ComplexNumberTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }


    @Test
    void realPart() {
        ComplexNumber c = new ComplexNumber(2.3, 5);
        assertEquals(2.3, c.realPart(), "Real");
    }

    @Test
    void imaginaryPart() {
        ComplexNumber c = new ComplexNumber(4.2, 9.1);
        assertEquals(9.1, c.imaginaryPart(), "Imaginario");
    }

    @Test
    void add() {
        ComplexNumber c = new ComplexNumber(3, 4);
        assertEquals(new ComplexNumber(5,11), c.add(new ComplexNumber(2,7)), "Sumar");
    }

    @Test
    void subtract() {
        ComplexNumber c = new ComplexNumber(9, 5);
        assertEquals(new ComplexNumber(5,-2), c.subtract(new ComplexNumber(4,7)), "Restar");
    }

    @Test
    void multiply() {
        ComplexNumber c = new ComplexNumber(5, 6);
        assertEquals(new ComplexNumber(3,28), c.multiply(new ComplexNumber(3,2)), "Multiplicar");
    }

    @Test
    void division() {
        ComplexNumber c = new ComplexNumber(13, 1);
        assertEquals(new ComplexNumber(1,4), c.division(new ComplexNumber(1,-3)), "division");
    }

    @Test
    void conjugate() {
        ComplexNumber c = new ComplexNumber(8, -2);
        assertEquals(new ComplexNumber(8,2), c.conjugate(), "Conjugado");
    }

    @Test
    void module() {
        ComplexNumber c = new ComplexNumber(4, -3);
        assertEquals(5, c.module(), "Módulo");
    }
}