import br.com.joaopedroafluz.Calculadora;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CalculadoraTest {

    @Test
    public void deveSomar() {
        Calculadora calc = new Calculadora();
        var resultado = calc.somar(2, 3);
        
        Assertions.assertEquals(5, resultado);
    }

    @Test
    public void deveRetornarNumeroInteiroNaDivisao() {
        Calculadora calc = new Calculadora();
        var resultado = calc.dividir(4, 2);

        Assertions.assertEquals(2, resultado);
    }

    @Test
    public void deveRetornarNumeroNegativoNaDivisao() {
        Calculadora calc = new Calculadora();
        var resultado = calc.dividir(4, -2);

        Assertions.assertEquals(-2, resultado);
    }

    @Test
    public void deveRetornarNumeroDecimalNaDivisao() {
        Calculadora calc = new Calculadora();
        var resultado = calc.dividir(10, 3);

        Assertions.assertEquals(3.33, resultado, 0.01);
    }

    @Test
    public void deveCompararValoresIguais() {
        var valor1 = 10;
        var valor2 = 10;

        Assertions.assertEquals(valor1, valor2);
    }

    @Test
    public void deveCompararValoresDiferentes() {
        var valor1 = 10;
        var valor2 = 20;

        Assertions.assertNotEquals(valor1, valor2);
    }

    @Test
    public void deveCompararObjetosDiferentes() {
        var valor1 = new ArrayList<>();
        var valor2 = new ArrayList<>();

        Assertions.assertNotSame(valor1, valor2);
    }

    @Test
    public void deveVerificarValorNulo() {
        Integer valor = null;

        Assertions.assertNull(valor);
    }

}
