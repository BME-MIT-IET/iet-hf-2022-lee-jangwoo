package StepDefinitions;

import controller.Control;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class Steps {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @Given("the running game")
    public void the_running_game() {
        Control control = new Control();
        control.testMain();
        control.setOutput(new PrintStream(outputStreamCaptor));
    }
    @When("the user tries to load game5.txt")
    public void the_user_tries_to_load_game5_txt() {
        String[] pieces = "load game5.txt".split(" ");
        Control.Command cmd = Control.getCommands().getOrDefault(pieces[0], null);
        cmd.execute(pieces);
    }
    @Then("it loads successfully")
    public void it_loads_successfully() {
        //assertEquals("load", outputStreamCaptor.toString());
        assertEquals("loaded successfully", outputStreamCaptor.toString().trim());
    }

    // unsuccessful
    @When("the user tries to load game5fail.txt")
    public void the_user_tries_to_load_game5fail_txt() {
        String[] pieces = "load game5fail.txt".split(" ");
        Control.Command cmd = Control.getCommands().getOrDefault(pieces[0], null);
        cmd.execute(pieces);
    }
    @Then("it loads unsuccessfully")
    public void it_loads_unsuccessfully() {
        assertEquals("load unsuccessful", outputStreamCaptor.toString().trim());
    }

}
