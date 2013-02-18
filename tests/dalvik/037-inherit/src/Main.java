public class Main {
    static void arrayCluster(IMagic[] magicArray) {
        int i;

        for (i = 0; i < magicArray.length; i++)
            System.out.println(" " + i + ": " + magicArray[i].getSomeData());
    }

    public static void main(String args[]) {
        MagicClass magic = new MagicClass();

        System.out.print("magic is ");
        System.out.println(magic.getSomeData());

        MagicClass magicArray[] = new MagicClass[2];
        magicArray[0] = new MagicClass();
        magicArray[1] = new MagicClass();
        arrayCluster(magicArray);
    }
}

class IntSource {
    public int getMagicInt() { return 64; }
}

interface IMagic {
    public double getSomeData();

    IntSource mIntSource = new IntSource();
    public int MAGIC_INT = mIntSource.getMagicInt();
}

class MagicClass implements IMagic {
    public double getSomeData() {
        return this.MAGIC_INT;
    }
}
