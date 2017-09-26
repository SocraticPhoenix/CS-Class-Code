package quinnipiac._2006;

import java.util.Scanner;

public class DNAandRNA {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String dna = scanner.nextLine();
        for (int i = 0; i < dna.length(); i++) {
            switch (dna.charAt(i)) {
                case 'A':
                    System.out.print('U');
                    break;
                case 'T':
                    System.out.print('A');
                    break;
                case 'C':
                    System.out.print('G');
                    break;
                case 'G':
                    System.out.print('C');
                    break;
            }
            if ((i + 1) % 3 == 0) {
                System.out.print(" ");
            }
        }
    }

}
