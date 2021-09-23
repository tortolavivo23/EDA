package test;

import static org.junit.jupiter.api.Assertions.*;
import complejos.Sumador;
import org.junit.Assert;
import org.junit.Test;

public class SumadorTest {
    @Test
    public void hello(){
        Sumador s = new Sumador();
        int r = s.add(3,4);
        //if(r!=7) fail("La suma es incorrecta");
        Assert.assertEquals("La suma es incorrecta", 7, r);
    }

}