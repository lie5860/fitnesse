package fitnesse.testsystems;

import fitnesse.testrunner.WikiPageDescriptor;

import java.io.IOException;
import java.util.regex.Matcher;

public abstract class ClientBuilder<T> {

  protected final Descriptor descriptor;
  protected boolean fastTest;
  protected boolean manualStart;
  protected boolean remoteDebug;

  public ClientBuilder(Descriptor descriptor) {
    this.descriptor = descriptor;
  }

  protected String buildCommand(String commandPattern, String testRunner, String classPath) {
    String command = replace(commandPattern, "%p", classPath);
    command = replace(command, "%m", testRunner);
    return command;
  }


  protected static String replace(String value, String mark, String replacement) {
    return value.replaceAll(mark, Matcher.quoteReplacement(replacement));
  }

  public ClientBuilder<T> withFastTest(boolean fastTest) {
    this.fastTest = fastTest;
    return this;
  }

  public ClientBuilder<T> withManualStart(boolean manualStart) {
    this.manualStart = manualStart;
    return this;
  }

  public ClientBuilder<T> withRemoteDebug(boolean remoteDebug) {
    this.remoteDebug = remoteDebug;
    return this;
  }

  public abstract T build() throws IOException;

}
