package StepDefinitions;

import controller.Control;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class DrillMinePutmineralback {
    Load load = new Load();
    @Given("Load {string} successfully")
    public void loadGameSuccessfully(String string) {
        load.the_running_game();
        load.the_user_tries_to_load_file(string);
        load.it_loads_successfully();
    }

    @When("active user {string}")
    public void activeUserDoesSomething(String string) {
        String[] pieces = string.split(" ");
        Control.Command cmd = Control.getCommands().getOrDefault(pieces[0], null);
        cmd.execute(pieces, load.control);
    }

    @Then("drill is successful")
    public void drillIsSuccessful() {
        String[] lines = load.getOutput().toString().replaceAll("(\\r)", "").split("(\\n)");
        assertEquals("loaded successfully", lines[0]);
        assertEquals("drilling successful", lines[1]);
        // Control.getGameFrame().dispose();
    }

    @Given("Load {string} unsuccessfully")
    public void loadGameTxtUnsuccessfully(String string) {
        load.the_running_game();
        load.the_user_tries_to_load_file(string);
        load.it_loads_unsuccessfully();
    }

    @Then("second drill is unsuccessful")
    public void secondDrillIsUnsuccessful() {
        String[] lines = load.getOutput().toString().replaceAll("(\\r)", "").split("(\\n)");
        assertEquals("loaded successfully", lines[0]);
        assertEquals("drilling successful", lines[1]);
        assertEquals("shell is now 0 unit(s) thick", lines[2]);
        assertEquals("drilling unsuccessful", lines[3]);
        assertEquals("the shell has already been drilled through", lines[4]);
    }

    @Then("mine is successful")
    public void mineIsSuccessful() {
        String[] lines = load.getOutput().toString().replaceAll("(\\r)", "").split("(\\n)");
        assertEquals("mining successful", lines[0]);
        assertEquals("asteroid is now empty", lines[2]);
    }

    @Then("mine is unsuccessful")
    public void mineIsUnsuccessful() {
        String[] lines = load.getOutput().toString().replaceAll("(\\r)", "").split("(\\n)");
        assertEquals("mining unsuccessful", lines[0]);
        assertEquals("asteroid is already empty", lines[1]);
    }

    @Then("putmineralback is successful")
    public void putmineralbackIsSuccessful() {
        String[] lines = load.getOutput().toString().replaceAll("(\\r)", "").split("(\\n)");
        assertEquals("loaded successfully", lines[0]);
        assertEquals("drilling successful", lines[1]);
        assertEquals("shell is now 0 unit(s) thick", lines[2]);
        assertEquals("move to a2 successful", lines[3]);
        assertEquals("mining successful", lines[4]);
        assertEquals("one unit of coal acquired", lines[5]);
        assertEquals("asteroid is now empty", lines[6]);
        assertEquals("mining unsuccessful", lines[7]);
        assertEquals("asteroid is already empty", lines[8]);
        lines[9] = lines[9].replaceAll("coal|ice|iron|uranium", "").trim();
        assertEquals("is now in the asteroid", lines[9]);
    }

    @Then("putmineralback is unsuccessful")
    public void putmineralbackIsUnsuccessful() {
        String[] lines = load.getOutput().toString().replaceAll("(\\r)", "").split("(\\n)");
        assertEquals("loaded successfully", lines[0]);
        assertEquals("putting back mineral unsuccessful", lines[1]);
        assertEquals("asteroid has other mineral", lines[2]);
    }
}
