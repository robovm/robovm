public class Main {
    static public void main(String[] args) throws Exception {
        boolean timing = (args.length >= 1) && args[0].equals("--timing");
        ManyInterfaces.run(timing);
    }
}
