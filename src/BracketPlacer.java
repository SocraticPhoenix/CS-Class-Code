import java.util.*;

public class BracketPlacer {
    private static Map<Character, Character> brackets = new HashMap<Character, Character>(){{
        put('(', ')');
        put('{', '}');
        put('[', ']');
    }};

    private static Map<Character, Character> cBrackets = new HashMap<Character, Character>(){{
        put(')', '(');
        put('}', '{');
        put(']', '[');
    }};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            String k = scanner.nextLine().replaceAll(" ", "");

            char missing = missing(k);
            List<Integer> positions = new ArrayList<>();
            for (int j = 0; j < k.length(); j++) {
                if (fits(missing, k, j)) {
                    String test = k.substring(0, j + 1) + missing;
                    if (j + 1 != k.length()) {
                        test += k.substring(j + 1, k.length());
                    }

                    if (balanced(test)) {
                        positions.add(j + 2);
                    }
                }
            }
            System.out.println(positions);
        }
    }

    private static boolean fits(char k, String eq, int pos) {
        //abcd --> pos 2
        //abc|d

        if (brackets.containsKey(k)) { //Opening bracket
            /*
              Opening brackets may not:
                - Be placed directly after a closing bracket
                - Be placed directly before a number which their closing bracket immediately follows
                - Be placed directly before a closing bracket
                - In the middle of a number
                - Directly before an op
             */
            if (cBrackets.containsKey(eq.charAt(pos))) {
                return false;
            } else if (pos != eq.length() - 1 && (cBrackets.containsKey(eq.charAt(pos + 1)) || (!Character.isDigit(eq.charAt(pos + 1)) && !cBrackets.containsKey(eq.charAt(pos + 1)) && !brackets.containsKey(eq.charAt(pos + 1))))) {
                return false;
            } else if (pos != 0 && pos != eq.length() - 1 && Character.isDigit(eq.charAt(pos)) && Character.isDigit(eq.charAt(pos + 1))) {
                return false;
            } else if (pos != eq.length() - 1) {
                char n = eq.charAt(pos + 1);
                for (int i = pos + 1; i < eq.length(); i++) {
                    n = eq.charAt(i);
                    if (!Character.isDigit(n)) {
                        break;
                    }
                }

                if (n == brackets.get(k)) {
                    return false;
                }
            }

            return true;
        } else { //Closing bracket
            /*
              Closing brackets may not:
                - Be placed directly after an opening bracket
                - Be placed directly after a number which their opening bracket is before
                - Be placed directly before an opening bracket
                - In the middle of a number
                - Directly after an op
             */
            if (brackets.containsKey(eq.charAt(pos)) || (!Character.isDigit(eq.charAt(pos)) && !cBrackets.containsKey(eq.charAt(pos)) && !brackets.containsKey(eq.charAt(pos)))) {
                return false;
            } else if (pos != eq.length() - 1 && brackets.containsKey(eq.charAt(pos + 1))) {
                return false;
            } else if (pos != 0 && pos != eq.length() - 1 && Character.isDigit(eq.charAt(pos)) && Character.isDigit(eq.charAt(pos + 1))) {
                return false;
            } else {
                char n = eq.charAt(pos);
                for (int i = pos; i >= 0; i--) {
                    n = eq.charAt(i);
                    if (!Character.isDigit(n)) {
                        break;
                    }
                }
                if (n == cBrackets.get(k)) {
                    return false;
                }
            }

            return true;
        }

        //abcd --> pos 2
        //abc|d
    }

    private static boolean balanced(String k) {
        Stack<Character> stack = new Stack<>();
        for (char c : k.toCharArray()) {
            if (brackets.containsKey(c)) {
                stack.push(c);
            } else if (cBrackets.containsKey(c)) {
                char close = cBrackets.get(c);
                if (stack.isEmpty() || stack.pop() != close) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static char missing(String k) {
        for (int i = 0; i < k.length(); i++) {
            String test = k.substring(0, i);
            if (i != k.length() - 1) {
                test += k.substring(i + 1, k.length());
            }

            if (balanced(test)) {
                char m = k.charAt(i);
                return cBrackets.containsKey(m) ? cBrackets.get(m) : brackets.get(m);
            }
        }

        return '\0';
    }

}
