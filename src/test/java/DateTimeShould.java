import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DateTimeShould {
    @Test
    public void return_the_date_of_now_as_a_string() {
        DateTime dateTime = new DateTime();

        assertThat(dateTime.getTimeString(), is("17/01/2017"));
    }
}
