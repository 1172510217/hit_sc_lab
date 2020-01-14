package debug;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class RemoveCommentsTest {

  /**
   * testing strategy : ���Զ���ע�͵ġ�/ *���͡�* /.
   */
  @Test void testMutiLine() {
    String[] sources = new String[7];
    sources[0] = "/*Test program */";
    sources[1] = "int main()";
    sources[2] = "{";
    sources[3] = "/* This is a test multiline";
    sources[4] = "comment for testing*/";
    sources[5] = "int a = 10;";
    sources[6] = "}";
    List<String> res = new ArrayList<>();
    res.add("int main()");
    res.add("{");
    res.add("int a = 10;");
    res.add("}");
    RemoveComments removeComments = new RemoveComments();
    assertEquals(res, removeComments.removeComments(sources));
  }

  /**
   * testing strategy : ���Ե���ע�͵�//.
   */
  @Test void testLineComment() {
    String[] sources = new String[5];
    sources[0] = "//Test program";
    sources[1] = "int main()";
    sources[2] = "{";
    sources[3] = "int a = 10;";
    sources[4] = "}";
    List<String> res = new ArrayList<>();
    res.add("int main()");
    res.add("{");
    res.add("int a = 10;");
    res.add("}");
    RemoveComments removeComments = new RemoveComments();
    assertEquals(res, removeComments.removeComments(sources));
  }

  /**
   * testing strategy : ����һ���г�����//��/*.
   */
  @Test void testLevel1() {
    String[] sources = new String[5];
    sources[0] = "//Test program/*this is a test/*";
    sources[1] = "int main()";
    sources[2] = "{";
    sources[3] = "int a = 10;";
    sources[4] = "}";
    List<String> res = new ArrayList<>();
    res.add("int main()");
    res.add("{");
    res.add("int a = 10;");
    res.add("}");
    RemoveComments removeComments = new RemoveComments();
    assertEquals(res, removeComments.removeComments(sources));
  }

  /**
   * testing strategy : ����һ���г�����//��/*.
   */
  @Test void testLevel2() {
    String[] sources = new String[5];
    sources[0] = "/*this is a test*///Test program";
    sources[1] = "int main()";
    sources[2] = "{";
    sources[3] = "int a = 10;";
    sources[4] = "}";
    List<String> res = new ArrayList<>();
    res.add("int main()");
    res.add("{");
    res.add("int a = 10;");
    res.add("}");
    RemoveComments removeComments = new RemoveComments();
    assertEquals(res, removeComments.removeComments(sources));
  }
}
