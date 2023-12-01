package socket.controller;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringHelperTest {

    @Test
    public void isRussianCharsPresent_NoRussianChars_ReturnFalse() {
        String text = "йцукенгшщзхфівапролджєячсмитьбюї";

        boolean actual = StringHelper.isRussianCharsPresent(text);

        assertFalse(actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isRussianCharsPresent_NullInputText_ThrowIllegalArgumentException() {
        StringHelper.isRussianCharsPresent(null);
    }

    @Test
    public void isRussianCharsPresent_HaveRussianChars_ReturnTrue() {
        String text = "йцукенгшщзхъфыівапролджэєячсмитьбюї";

        boolean actual = StringHelper.isRussianCharsPresent(text);

        assertTrue(actual);
    }
}