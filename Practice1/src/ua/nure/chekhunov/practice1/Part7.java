package ua.nure.chekhunov.practice1;

public class Part7 {

    private static final String POINTER = " ==> ";

    public static int str2int(String number) {

        int res = 0;
        final int constantBitSystem = 64;
        final int calculusSystemsInM = 26;

        for (int i = 0, j = number.length() - 1; i < number.length(); i++, j--) {
            res += (number.charAt(j) - constantBitSystem) * Math.pow(calculusSystemsInM, i);
        }
        return res;
    }

    public static String int2str(int number) {
        final int constantBitSystem = 64;
        final int calculusSystems = 26;
        int modul;
        int divident = number;
        StringBuilder buildChars = new StringBuilder();

        while (divident != 0) {
            modul = divident % calculusSystems;
            if (modul == 0) {
                buildChars.append("Z");
                divident = (divident - 1) / calculusSystems;
            } else {
                buildChars.append((char) (modul + constantBitSystem));
                divident = (divident - modul) / calculusSystems;
            }
        }
        StringBuilder bld = new StringBuilder();
        for (int i = 0; i < buildChars.length(); ++i) {
            bld.append(buildChars.charAt(buildChars.length() - i - 1));
        }
        return bld.toString();

    }

    public static String rightColumn(String number) {

        String chars = "";
        int num;
        num = str2int(number);
        num++;
        chars = int2str(num);
        return chars;
    }

    public static void main(String[] args) {

        System.out.println("A" + POINTER + "1" + POINTER + "A");
        System.out.println("B" + POINTER + "2" + POINTER + "B");
        System.out.println("Z" + POINTER + "26" + POINTER + "Z");
        System.out.println("AA" + POINTER + "27" + POINTER + "AA");
        System.out.println("AZ" + POINTER + "52" + POINTER + "AZ");
        System.out.println("BA" + POINTER + "53" + POINTER + "BA");
        System.out.println("ZZ" + POINTER + "702" + POINTER + "ZZ");
        System.out.println("AAA" + POINTER + "703" + POINTER + "AAA");

    }

}
