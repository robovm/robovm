public class Main {
  static public void main(String[] args) throws Exception {
    switch (0x7fffffff) {
    case 0x7fffffff:
      System.err.println("good");
      break;
    default:
      throw new AssertionError();
    }
  }
}
