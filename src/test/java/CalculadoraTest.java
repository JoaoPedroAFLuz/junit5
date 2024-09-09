import br.com.joaopedroafluz.Calculadora;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {

    @Test
    public void testSomar() {
        Calculadora calc = new Calculadora();

        Assertions.assertEquals(5, calc.somar(2, 3));
    }

}
