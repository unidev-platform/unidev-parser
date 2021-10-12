package com.unidev.parser;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.lang3.StringUtils;

/**
 * Common parsing functionality
 */
public class Parser {

  /**
   * Clean string from spaces
   */
  public String clean(String page) {
    return StringUtils.trim(page.replace("\n", " ").replace("\t", " ").replaceAll(" +", " "));
  }

  /**
   * Return list of all string which are between "begin" "end" blocks
   */
  public Collection<String> collectValuesBetween(String content, String begin, String end) {
    Collection<String> result = new ArrayList<>();
    if (StringUtils.isBlank(content)) {
      return result;
    }
    String processedContent = content + "";
    while (true) {
      String value = StringUtils.substringBetween(processedContent, begin, end);
      if (StringUtils.isBlank(value)) {
        break;
      }
      processedContent = StringUtils.replace(processedContent, begin + value + end, "", 1);
      result.add(value);
    }
    return result;
  }

  /**
   * Remove first value between begin end, if pattern not found, return null
   */
  public String removeValueBetween(String content, String begin, String end, boolean includeBeginEnd) {
    String valueBetween = StringUtils.substringBetween(content, begin, end);
    if (StringUtils.isBlank(valueBetween)) {
      return null;
    }
    if (includeBeginEnd) {
      return StringUtils.replaceOnce(content, begin + valueBetween + end, "");
    }
    return StringUtils.replaceOnce(content, valueBetween, "");
  }

}
