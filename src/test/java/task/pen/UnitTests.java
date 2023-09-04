package task.pen;

import org.testng.Assert;
import org.testng.annotations.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;


public class UnitTests {
    public Field field;

    public void declaredFieldSetUp(String name) throws NoSuchFieldException {
        field = Pen.class.getDeclaredField(name);
        field.setAccessible(true);
    }

    @Test(dataProvider = "pen-settings", dataProviderClass = TestData.class)
    public void constructorWithInkContainerTest(int ink, int expected) throws NoSuchFieldException, IllegalAccessException {
        Pen pen = new Pen(ink);
        declaredFieldSetUp("inkContainerValue");
        Integer result = (Integer) field.get(pen);
        Assert.assertEquals(result, expected);
    }

    @Test(dependsOnMethods = {"constructorWithInkContainerTest"}, dataProvider = "pen-settings", dataProviderClass = TestData.class)
    public void constructorWithFontSizeTest(int ink, double fontSize, double expected) throws NoSuchFieldException, IllegalAccessException {
        Pen pen = new Pen(ink, fontSize);
        declaredFieldSetUp("sizeLetter");
        double result = (double) field.get(pen);
        Assert.assertEquals(result, expected);
    }

    @Test(dependsOnMethods = {"constructorWithInkContainerTest", "constructorWithFontSizeTest"}, dataProvider = "pen-settings", dataProviderClass = TestData.class)
    public void constructorWithInkColorTest(int ink, double fontSize, String inkColor, String expected) throws NoSuchFieldException, IllegalAccessException {
        Pen pen = new Pen(ink, fontSize, inkColor);
        declaredFieldSetUp("color");
        String result = (String) field.get(pen);
        Assert.assertEquals(result, expected);
    }

    @Test(dataProvider = "pen-settings", dataProviderClass = TestData.class)
    public void workabilityTest(int ink, boolean expected) {
        Pen pen = new Pen(ink);
        Boolean result = pen.isWork();
        Assert.assertEquals(result, expected);
    }

    @Test(dataProvider = "negative", dataProviderClass = TestData.class)
    public void workabilityNegativeTest(int ink, boolean expected) {
        Pen pen = new Pen(ink);
        Boolean result = pen.isWork();
        Assert.assertEquals(result, expected);
    }

    @Test(dataProvider = "pen-settings", dataProviderClass = TestData.class)
    public void writingTest(int ink, double fontSize, String input, String expectedWord) {
        Pen pen = new Pen(ink, fontSize);
        String result = pen.write(input);
        Assert.assertEquals(result, expectedWord);
    }

    @Test(dataProvider = "pen-settings", dataProviderClass = TestData.class)
    public void getColorTest(int ink, double fontSize, String inkColor, String expectedColor) {
        Pen pen = new Pen(ink, fontSize, inkColor);
        String result = pen.getColor();
        Assert.assertEquals(result, expectedColor);
    }

    private ByteArrayOutputStream output;

    @BeforeMethod
    public void setUpStreams() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @Test(dataProvider = "pen-settings", dataProviderClass = TestData.class)
    public void doSmthElseTest(int ink, double fontSize, String inkColor, String expectedColor) {
        Pen pen = new Pen(ink, fontSize, inkColor);
        pen.doSomethingElse();
        Assert.assertEquals(output.toString().trim(), expectedColor);
    }
}