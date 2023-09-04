package task.pen;
import org.testng.annotations.DataProvider;
import java.lang.reflect.Method;

public class TestData {

    @DataProvider(name = "pen-settings")
    public static Object[][] testData(Method m) {
        if (m.getName().toString() == "writingTest") {
            return new Object[][]{
                    {10, 3.0, "12", "12"},
                    {10, 3.0, "1234", "1234"},
                    {1, 1.0, "Test", "T"},
                    {0, 1.0, "SDFVCXCVsadfsdf@#$23123", ""},
            };
        }
        if (m.getName().toString() == "workabilityTest") {
            return new Object[][]{
                    {2147483647, true},
                    {'x', true},
                    {0101, true},
                    {-2147483647, false},
                    {0, false},
            };
        }
        if (m.getName().toString() == "constructorWithinkContainerTest") {
            return new Object[][]{
                    {1000, 1000},
                    {500, 500},
                    {1, 1},
                    {0, 0},
            };
        }
        if (m.getName().toString() == "constructorWithFontSizeTest") {
            return new Object[][]{
                    {1000, 1.0, 1.0},
                    {500, 10, 10},
                    {1, 1.0, 1.0},
                    {0, 1.0, 1.0},
            };
        } else {
            return new Object[][]{
                    {1000, 1.0, "BLUE", "BLUE",},
                    {1000, 1.0, "BLACK", "BLACK",},
                    {1000, 1.0, "GREY", "GREY",},
                    {1000, 1.0, "GREEN", "GREEN",},
            };
        }
    }

    @DataProvider(name = "negative")
    public static Object[][] testDataNegative() {
        return new Object[][]{
                {null, false},
                {"asd", false},
                {0.1, false},
        };

    }
}
