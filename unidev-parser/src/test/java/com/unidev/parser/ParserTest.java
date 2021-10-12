package com.unidev.parser;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import org.junit.jupiter.api.Test;

public class ParserTest {

  @Test
  public void collectionValuesBetween() {
    Collection<String> strings = new Parser().collectValuesBetween(
        "qwe <u>a</u> 123231  <u>xxx</u> 1 <u>a</u> <u>x</u>yyy", "<u>", "</u>");

    assertThat(strings).isNotNull();
    assertThat(strings.size()).isEqualTo(4);
    assertThat(strings).hasSize(4).contains("a", "xxx", "a", "x");
  }

  @Test
  public void noMatchForCollection() {
    Collection<String> strings = new Parser().collectValuesBetween(
        "qwe <u>a</u> 123231  <u>xxx</u> 1 <u>a</u> <u>x</u>yyy", "<x>", "</x>");
    assertThat(strings).isNotNull();
    assertThat(strings).isEmpty();
  }

  @Test
  public void emptyArg() {
    Collection<String> strings = new Parser().collectValuesBetween(
        "", "<x>", "</x>");

    assertThat(strings).isNotNull();
    assertThat(strings).isEmpty();
  }

  @Test
  public void removeValueBetween() {
    String text = "ABC-1234-XYZ";
    String updatedText = new Parser().removeValueBetween(text, "ABC", "XYZ", false);
    assertThat(updatedText).isEqualTo("ABCXYZ");

    String message = "Message1<b>remove me</b>.Message2<b>remove me</b>.";
    String updatedMessage = new Parser().removeValueBetween(message, "<b>", "</b>", true);
    assertThat(updatedMessage).isEqualTo("Message1.Message2<b>remove me</b>.");

    String notFoundMessage = new Parser().removeValueBetween(message, "<p>", "</p>", true);
    assertThat(notFoundMessage).isNull();

  }



}
