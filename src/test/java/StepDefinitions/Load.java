package StepDefinitions;

import controller.Control;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class Load {
    // Load, successful
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    public ByteArrayOutputStream getOutput() { return outputStreamCaptor; }
    public Control control = new Control();
    @Given("the running game")
    public void the_running_game() {
        control.testMain();
        control.setOutput(new PrintStream(outputStreamCaptor));
    }
    @When("the user tries to load {string}")
    public void the_user_tries_to_load_file(String string) {
        String[] pieces = new String[] {"load", string};
        control.parseCommandTest(pieces);
    }
    @Then("it loads successfully")
    public void it_loads_successfully() {
        assertEquals("loaded successfully", outputStreamCaptor.toString().trim().replaceAll("(\\r|\\n)", ""));
    }
    // Load, unsuccessful
    @Then("it loads unsuccessfully")
    public void it_loads_unsuccessfully() {
        assertEquals("load unsuccessful", outputStreamCaptor.toString().trim().replaceAll("(\\r|\\n)", ""));
    }
}
