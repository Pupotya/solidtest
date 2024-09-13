package core.ui;

import org.openqa.selenium.By;

public class ByDataTestId extends By.ByCssSelector {

    private static final String DATA_TESTID = "[data-testid='%s']";

    public ByDataTestId(String dataTestId) {
        super(String.format(DATA_TESTID, dataTestId));
    }

    public static By id(String dataTestId) {
        return new ByDataTestId(dataTestId);
    }
}
