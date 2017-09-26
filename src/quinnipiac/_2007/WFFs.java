package quinnipiac._2007;

import java.util.Scanner;

public class WFFs {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line;
        while (!(line = scanner.nextLine()).isEmpty()) {
            WFFParser parser = new WFFParser(0, line.replace(" ", ""));
            if (parser.isWff()) {
                System.out.println(line + " is a WFF");
            } else {
                System.out.println(line + " is not a WFF");
            }
        }
    }

    public enum Type {
        SINGLE,
        SINGLE_FOLLOWING,
        DOUBLE_FOLLOWING,
        INVALID
    }

    public static class WFFParser {
        private int index;
        private String content;

        public WFFParser(int index, String content) {
            this.index = index;
            this.content = content;
        }

        public boolean isWff() {
            return parseWff() && index == content.length();
        }

        public boolean parseWff() {
            boolean k;
            if (isSingleNext()) {
                k = parseSingle();
            } else if (isSingleFollowNext()) {
                k = parseSingeFollow();
            } else if (isDoubleFollowNext()) {
                k = parseDoubleFollow();
            } else {
                k = false;
            }

            return k;
        }

        public boolean parseSingle() {
            if (!hasNext()) {
                return false;
            }
            next();
            return true;
        }

        public boolean parseSingeFollow() {
            if (!hasNext()) {
                return false;
            }
            this.index++;
            return parseWff();
        }

        public boolean parseDoubleFollow() {
            if (!hasNext()) {
                return false;
            }
            this.index++;
            return parseWff() && parseWff();
        }

        public boolean isSingleNext() {
            return current() == Type.SINGLE;
        }

        public boolean isSingleFollowNext() {
            return current() == Type.SINGLE_FOLLOWING;
        }

        public boolean isDoubleFollowNext() {
            return current() == Type.DOUBLE_FOLLOWING;
        }

        public Type current() {
            char c = this.content.charAt(index);
            if (c == 'p' || c == 'q' || c == 'r' || c == 's') {
                return Type.SINGLE;
            } else if (c == 'N') {
                return Type.SINGLE_FOLLOWING;
            } else if (c == 'A' || c == 'C' || c == 'E' || c == 'K') {
                return Type.DOUBLE_FOLLOWING;
            } else {
                return Type.INVALID;
            }
        }

        public Type next() {
            Type t = current();
            this.index++;
            return t;
        }

        public boolean hasNext() {
            return this.index < this.content.length();
        }

    }

}
