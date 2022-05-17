package StepDefinitions;

import controller.Control;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class DrillAndMine {
    Load load = new Load();
    @Given("Load {string} successfully")
    public void loadGameSuccessfully(String string) {
        load.the_running_game();
        load.the_user_tries_to_load_file(string);
        load.it_loads_successfully();
    }

    @When("active user {string}")
    public void activeUserDoesSomething(String string) {
        String[] pieces = new String[]{string};
        Control.Command cmd = Control.getCommands().getOrDefault(pieces[0], null);
        cmd.execute(pieces);
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
        assertEquals("asteroid is already empty", lines[0]);
    }
}
