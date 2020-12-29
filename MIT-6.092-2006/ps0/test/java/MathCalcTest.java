import static org.junit.jupiter.api.Assertions.assertEquals;

import main.java.MathCalc;
import org.junit.jupiter.api.Test;

public class MathCalcTest {

    @Test
    public void volumeFromDiameterTest(){
        MathCalc tester = new MathCalc();

        assertEquals(392199, tester.volumeFromDiameter(100), " volume of sphere with 100 diameter must be ...");
    }

}
