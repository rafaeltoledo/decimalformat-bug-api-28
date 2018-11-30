package net.rafaeltoledo.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private DecimalFormat format;

    @Before
    public void setUp() {
        format = (DecimalFormat) NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        final DecimalFormatSymbols decimalSymbols = format.getDecimalFormatSymbols();
        decimalSymbols.setCurrencySymbol("");
        format.setMinimumFractionDigits(2);
        format.setDecimalFormatSymbols(decimalSymbols);
        format.setNegativePrefix("-");
        format.setNegativeSuffix("");
        format.setPositivePrefix("");
        format.setParseBigDecimal(true);
    }

    @Test
    @SdkSuppress(minSdkVersion = 28)
    public void moneyFormatSuccessfulRunsOnApi28() {
        final String value = "1.000.000,00";

        BigDecimal bigDecimal = (BigDecimal) format.parse(value, new ParsePosition(0));
        assertEquals("1000000.00", bigDecimal.toPlainString());
    }

    @Test
    @SdkSuppress(maxSdkVersion = 27)
    public void moneyFormatSuccessfulRunsOnApi27() {
        final String value = "1.000.000,00";

        BigDecimal bigDecimal = (BigDecimal) format.parse(value, new ParsePosition(0));
        assertEquals("1000000.00", bigDecimal.toPlainString());
    }
}
