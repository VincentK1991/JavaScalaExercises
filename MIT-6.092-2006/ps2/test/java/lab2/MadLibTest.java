package lab2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;

public class MadLibTest {

  MadLib ml;
  ShimUI ui;

  final static String response = "shimmy!";

  private String generatePrompt(String type) {
    return String.format("Please enter a %s: ",
			 type);
  }
  
//  public void setUp() {
//    ml = new MadLib("title");
//    ui = new ShimUI(response);
//  }

    public MadLibTest(){
      ml = new MadLib("title");
      ui = new ShimUI(response);
    }

  @Test
  public void testTitle() {
    assertEquals("title", ml.getTitle(),"ml.getTitle() did not return correct title");
    String s = "another title";
    MadLib ml2 = new MadLib(s);
    assertEquals(s, ml2.getTitle(), "ml2.getTitle() did not return correct title");
  }

  @Test
  public void testEmptyDoLib() {

    ml.doLib(ui);

    assertEquals(new ArrayList<String>(), ui.prompts, "Empty MadLib.doLib() should not prompt ui");

    List<String> expectedOutputs = new ArrayList<String>();
    expectedOutputs.add("");
    assertEquals(expectedOutputs, ui.outputs, "Empty MadLib.doLib() should write empty madlib");
  }

  @Test
  public void testEmptyPrintAsTemplate() {

    ml.printAsTemplate(ui);
    assertEquals(new ArrayList<String>(), ui.prompts, "Empty MadLib.printAsTemplate() should not prompt ui");

    List<String> expectedOutputs = new ArrayList<String>();
    expectedOutputs.add("");
    assertEquals(expectedOutputs, ui.outputs, "Empty MadLib.printAsTemplate() should write empty madlib");
  }

  @Test
  public void testTextDoLib() {

    String testString = "test string";
    ml.addString(testString);
    ml.doLib(ui);
    
    assertEquals(new ArrayList<String>(), ui.prompts, "Text-only MadLib.doLib() should not prompt ui");

    List<String> expectedOutputs = new ArrayList<String>();
    expectedOutputs.add(testString);
    assertEquals(expectedOutputs, ui.outputs, "Text-only MadLib.doLib() should write text.");
  }

  @Test
  public void testTextPrintAsTemplate() {

    String testString = "test string";
    ml.addString(testString);
    ml.printAsTemplate(ui);
    
    assertEquals(new ArrayList<String>(), ui.prompts, "Text-only MadLib.printAsTemplate() should not prompt ui");

    List<String> expectedOutputs = new ArrayList<String>();
    expectedOutputs.add(testString);
    assertEquals(expectedOutputs, ui.outputs, "Text-only MadLib.printAsTemplate() should write text.");
  }

  @Test
  public void testSlotDoLib() {

    String testString = "test string";
    ml.addSlot(testString);
    ml.doLib(ui);

    List<String> expectedPrompts = new ArrayList<String>();
    expectedPrompts.add(generatePrompt(testString));
    assertEquals(expectedPrompts, ui.prompts, "Slot-only MadLib.doLib() should prompt ui");

    List<String> expectedOutputs = new ArrayList<String>();
    expectedOutputs.add(response);
    assertEquals(expectedOutputs, ui.outputs, "Slot-only MadLib.doLib() should write slot.");
  }

  @Test
  public void testSlotPrintAsTemplate() {

    String testString = "test string";
    ml.addString(testString);
    ml.printAsTemplate(ui);
    
    assertEquals(new ArrayList<String>(), ui.prompts, "Slot-only MadLib.printAsTemplate() should not prompt ui");

    List<String> expectedOutputs = new ArrayList<String>();
    expectedOutputs.add(testString);
    assertEquals(expectedOutputs, ui.outputs, "Slot-only MadLib.printAsTemplate() should write slot.");
  }

  @Test
  public void testBiggerLib() {

    String testString = "test string";
    ml.addString("This is a ");
    ml.addSlot("noun");
    ml.addString(" all about how ");
    ml.addSlot("pronoun");
    
    ml.printAsTemplate(ui);
    List<String> expectedPrompts = new ArrayList<String>();
    assertEquals(expectedPrompts, ui.prompts, "Bigger MadLib.printAsTemplate() should not prompt ui");

    List<String> expectedOutputs = new ArrayList<String>();
    expectedOutputs.add(String.format("This is a %s all about how %s",
					 "<noun>",
					 "<pronoun>"));
    assertEquals(expectedOutputs, ui.outputs, "Bigger MadLib.printAsTemplate() should write correctly.");

    // now do the MadLib, keeping the previous output
    ml.doLib(ui);

    expectedPrompts.add(generatePrompt("noun"));
    expectedPrompts.add(generatePrompt("pronoun"));
    assertEquals(expectedPrompts, ui.prompts, "Bigger MadLib.doLib() should prompt ui twice");

    expectedOutputs.add(String.format("This is a %s all about how %s", response, response));
    assertEquals(expectedOutputs, ui.outputs, "Bigger MadLib.doLib() should write correctly.");
  }

  @Test
  public void testMultiResponse() {
    List<String> responses = new ArrayList<String>();
    responses.add("alpha");
    responses.add("beta");
    responses.add("GAMMA");
    MultiShimUI mui = new MultiShimUI(responses);

    ml.addString("A ");
    ml.addSlot("first letter");
    ml.addString(" B ");
    ml.addSlot("second letter");
    ml.addString(" C ");
    ml.addSlot("third letter");

    ml.printAsTemplate(mui);
    List<String> expectedPrompts = new ArrayList<String>();
    assertEquals(expectedPrompts, mui.prompts, "MultiResponse MadLib.printAsTemplate() should not prompt ui");
    List<String> expectedOutputs = new ArrayList<String>();
    expectedOutputs.add(String.format("A %s B %s C %s",
				      "<first letter>",
				      "<second letter>",
				      "<third letter>"));
    assertEquals(expectedOutputs, mui.outputs, "MultiResponse MadLib.printAsTemplate() should write correctly.");

    // again, now do the MadLib
    ml.doLib(mui);

    expectedPrompts.add(generatePrompt("first letter"));
    expectedPrompts.add(generatePrompt("second letter"));
    expectedPrompts.add(generatePrompt("third letter"));
    assertEquals(expectedPrompts, mui.prompts, "MultiResponse MadLib.doLib() should prompt ui thrice");

    expectedOutputs.add(String.format("A %s B %s C %s",
				      "alpha",
				      "beta",
				      "GAMMA"));

    assertEquals(expectedOutputs, mui.outputs, "MultiResponse MadLib.doLib() should write correctly.");
  }

}
				
class ShimUI implements UserInterface {

  /** The prompts this has seen **/
  List<String> prompts;
  /** The Strings this has been asked to output **/
  List<String> outputs;
  /** The response that this gives from the "user" **/
  String response;
  
  
  public ShimUI(String response) {
    prompts = new ArrayList<String>();
    outputs = new ArrayList<String>();
    this.response = response;
  }

  public String promptUser(String prompt) {
    prompts.add(prompt);
    return response;
  }

  public void writeString(String s) {
    //System.out.println("writeString(" + s + ") called");
    outputs.add(s);
  }
}

/** Allows multiple response types from a single shim. **/
class MultiShimUI extends ShimUI {

  List<String> responses;
  int responseIndex;
  
  public MultiShimUI(List<String> responses) {
    super(null);
    this.responses = responses;
    responseIndex = 0;
  }

  public String getResponse() {
    String rval = this.responses.get(responseIndex % this.responses.size());
    responseIndex++;
    return rval;
  }

  public String promptUser(String prompt) {
    super.promptUser(prompt);
    return getResponse();
  }
    
}

//interface UserInterface2 {
//
//  /**
//   * Prompt the user with the given prompt and return the user's
//   * response.
//   **/
//  public String promptUser(String prompt);
//
//  /**
//   * Write s to the user.
//   **/
//  public void writeString(String s);
//}