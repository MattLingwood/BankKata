import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SimpleAccountShould {

    private static final String LINE_ENDING = System.getProperty("line.separator");
    private static final String TABLE_HEADERS = "DATE | AMOUNT | BALANCE" + LINE_ENDING;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @Mock
    DateTime dateTime;
    private SimpleAccount account;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        account = new SimpleAccount(dateTime);
    }

    @After
    public void cleanUp() {
        System.setOut(null);
    }

    @Test
    public void print_headers_above_output_when_print_statement_is_called() {
        account.printStatement();

        assertThat(outContent.toString(), is(TABLE_HEADERS));
    }

    @Test
    public void print_a_statement_of_deposits_and_withdrawals(){
        Mockito.when(dateTime.getTimeString())
                .thenReturn("01/04/2014")
                .thenReturn("02/04/2014")
                .thenReturn("10/04/2014");

        account.deposit(1000);
        account.withdraw(100);
        account.deposit(500);
        account.printStatement();

        assertThat(outContent.toString(), is(
                TABLE_HEADERS
                        + "10/04/2014 | 500 | 1400" + LINE_ENDING
                        + "02/04/2014 | -100 | 900" + LINE_ENDING
                        + "01/04/2014 | 1000 | 1000" + LINE_ENDING));
    }
}
