package quinnipiac._2006;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiConsumer;

public class ComputerSim {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<BiConsumer<Computer, Program>> instructions = new ArrayList<>();
        int count = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < count; i++) {
            String line = scanner.nextLine();
            String[] instr = line.trim().split(" +");
            switch (instr[0]) {
                case "SET": {
                    int reg = Integer.parseInt(instr[1]);
                    int val = Integer.parseInt(instr[2]);
                    instructions.add((comp, prog) -> {
                        comp.setVal(reg, val);
                        if (!comp.isValid(reg)) {
                            prog.invalidate();
                        }
                        prog.setIndex(prog.getIndex() + 1);
                    });
                    break;
                }
                case "COPY": {
                    int reg1 = Integer.parseInt(instr[1]);
                    int reg2 = Integer.parseInt(instr[2]);
                    instructions.add((comp, prog) -> {
                        if (!comp.isKnown(reg1)) {
                            prog.invalidate();
                        } else {
                            comp.setVal(reg2, comp.getVal(reg1));
                        }
                        prog.setIndex(prog.getIndex() + 1);
                    });
                    break;
                }
                case "ADD": {
                    int reg1 = Integer.parseInt(instr[1]);
                    int reg2 = Integer.parseInt(instr[2]);
                    int reg3 = Integer.parseInt(instr[3]);
                    instructions.add((comp, prog) -> {
                        if (!comp.isKnown(reg1) || !comp.isKnown(reg2)) {
                            prog.invalidate();
                        } else {
                            comp.setVal(reg3, comp.getVal(reg1) + comp.getVal(reg2));
                            if (!comp.isValid(reg3)) {
                                prog.invalidate();
                            }
                        }
                        prog.setIndex(prog.getIndex() + 1);
                    });
                    break;
                }
                case "SUB": {
                    int reg1 = Integer.parseInt(instr[1]);
                    int reg2 = Integer.parseInt(instr[2]);
                    int reg3 = Integer.parseInt(instr[3]);
                    instructions.add((comp, prog) -> {
                        if (!comp.isKnown(reg1) || !comp.isKnown(reg2)) {
                            prog.invalidate();
                        } else {
                            comp.setVal(reg3, comp.getVal(reg1) - comp.getVal(reg2));
                            if (!comp.isValid(reg3)) {
                                prog.invalidate();
                            }
                        }
                        prog.setIndex(prog.getIndex() + 1);
                    });
                    break;
                }
                case "SKIPEQ": {
                    int reg1 = Integer.parseInt(instr[1]);
                    int reg2 = Integer.parseInt(instr[2]);
                    instructions.add((comp, prog) -> {
                        if (comp.getVal(reg1) == comp.getVal(reg2)) {
                            prog.setIndex(prog.getIndex() + 2);
                        } else {
                            prog.setIndex(prog.getIndex() + 1);
                        }
                    });
                    break;
                }
                case "SKIPLT": {
                    int reg1 = Integer.parseInt(instr[1]);
                    int reg2 = Integer.parseInt(instr[2]);
                    instructions.add((comp, prog) -> {
                        if (comp.getVal(reg1) < comp.getVal(reg2)) {
                            prog.setIndex(prog.getIndex() + 2);
                        } else {
                            prog.setIndex(prog.getIndex() + 1);
                        }
                    });
                    break;
                }
                case "JUMP": {
                    int ind = Integer.parseInt(instr[1]);
                    instructions.add((comp, prog) -> {
                        prog.setIndex(prog.getIndex() + ind);
                    });
                    break;
                }
                case "END": {
                    instructions.add((comp, prog) -> {
                        prog.setEnded(true);
                    });
                    break;
                }
            }
        }

        Computer computer = new Computer();
        Program program = new Program(instructions);
        program.run(computer);
    }

    static class Computer {
        private Integer[] memory = new Integer[8];

        {
            Arrays.fill(memory, null);
        }

        public void setVal(int register, int val) {
            this.memory[register - 1] = val;
        }

        public int getVal(int register) {
            return memory[register - 1];
        }

        public boolean isKnown(int register) {
            return memory[register - 1] != null;
        }

        public boolean isValid(int register) {
            return !isKnown(register) || (getVal(register) >= 0 && getVal(register) <= 255);
        }

        public String memoryToString() {
            StringBuilder builder = new StringBuilder();
            for (int i = 1; i <= 8; i++) {
                if (isKnown(i)) {
                    builder.append(getVal(i)).append(" ");
                } else {
                    builder.append("U ");
                }
            }
            return builder.toString().trim();
        }

    }

    static class Program {
        private List<BiConsumer<Computer, Program>> instructions;
        private int index;
        private boolean ended;

        public Program(List<BiConsumer<Computer, Program>> instructions) {
            this.instructions = instructions;
            this.index = 0;
            this.ended = false;
        }

        public List<BiConsumer<Computer, Program>> getInstructions() {
            return this.instructions;
        }

        public Program setInstructions(List<BiConsumer<Computer, Program>> instructions) {
            this.instructions = instructions;
            return this;
        }

        public void run(Computer computer) {
            int count = 0;
            while (this.isRunning()) {
                instructions.get(this.index).accept(computer, this);
                count++;
                if ((!this.isRunning() || count > 255) && !this.ended) {
                    System.out.println(computer.memoryToString());
                    System.out.println("RUNTIME ERROR");
                    return;
                }
            }
            System.out.println(computer.memoryToString());
        }

        public boolean isRunning() {
            return !this.ended && this.index >= 0 && this.index < this.instructions.size();
        }

        public void invalidate() {
            this.index = Integer.MIN_VALUE;
            this.ended = false;
        }

        public int getIndex() {
            return this.index;
        }

        public Program setIndex(int index) {
            if (this.isRunning()) {
                this.index = index;
            }
            return this;
        }

        public boolean isEnded() {
            return this.ended;
        }

        public Program setEnded(boolean ended) {
            this.ended = ended;
            return this;
        }

    }

}
