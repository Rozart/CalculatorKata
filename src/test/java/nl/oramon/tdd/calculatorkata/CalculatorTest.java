package nl.oramon.tdd.calculatorkata;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;


public class CalculatorTest {

    private Calculator calculator = new Calculator();

    @Test
    public void calculate_EmptyString_ShouldReturnZero() throws CalculationException {
        assertThat(calculator.calculate("")).isEqualTo("0");
    }

    @Test
    public void calculate_SingleNumberOne_ShouldReturnSingleValueOne() throws CalculationException {
        assertThat(calculator.calculate("1")).isEqualTo("1");
    }

    @Test
    public void calculate_TwoNumbersCommaSeparated_ShouldReturnSum() throws CalculationException {
        assertThat(calculator.calculate("1,3")).isEqualTo("4");
    }

    @Test
    public void calculate_TwoNumbersNewlineSeparated_ShouldReturnSum() throws CalculationException {
        assertThat(calculator.calculate("1\n3")).isEqualTo("4");
    }

    @Test
    public void calculate_ThreeNumbersNewlineSeparated_ShouldReturnSum() throws CalculationException {
        assertThat(calculator.calculate("1\n3\n5")).isEqualTo("9");
    }

    @Test
    public void calculate_ThreeNumbersCommaSeparated_ShouldReturnSum() throws CalculationException {
        assertThat(calculator.calculate("1,3,5")).isEqualTo("9");
    }

    @Test
    public void calculate_NegativeNumbers_ShouldThrowException() {
        try{
            calculator.calculate("-1,6,4");
            failBecauseExceptionWasNotThrown(CalculationException.class);
        } catch (Exception e){
            assertThat(e).isInstanceOf(CalculationException.class);
        }
    }

    @Test
    public void calculate_OneOfTheNumbersGreaterThan1000_ShouldBeIgnoredAndShouldReturnSumOfOthers() throws CalculationException {
        assertThat(calculator.calculate("1,1111,2")).isEqualTo("3");
    }

    @Test
    public void calculate_DefineNewSeparatorInTheFirstLine_ShouldReturnSum() throws CalculationException {
        assertThat(calculator.calculate("//#\n1#3#5")).isEqualTo("9");
    }

    @Test
    public void calculate_DefineNewMulticharSeparatorInTheFirstLine_ShouldReturnSum() throws CalculationException {
        assertThat(calculator.calculate("//[###]\n1###3###5")).isEqualTo("9");
    }

}
