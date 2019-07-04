package com.company;

import java.util.*;
import java.util.function.BiFunction;

public class Cifras {
    private int objectiu;
    private final Integer[] nom;
    private final HashMap<Character, BiFunction<Integer, Double, Double>> operators;
    private ArrayList<Operand> nombres;
    private ArrayList<Operand> calculats;
    private HashSet<Operand> usats;
    private ArrayDeque<String> operacions;
    private Operand max;
    private Operand act;

    class Operand {
        @Override
        public String toString() {
            return calcul + " = " + nombre;
        }

        int nombre;
        String calcul = "";

        public Operand(double nombre, String calcul) {
            this.nombre = (int) nombre;
            this.calcul = calcul;
        }
    }

    public Cifras(int p, Integer[] nom, HashMap<Character, BiFunction<Integer, Double, Double>> operators) {
        this.objectiu = p;
        this.nom = nom;
        this.operators = operators;
        this.nombres = new ArrayList<>();
        this.calculats = new ArrayList<>();
        this.usats = new HashSet<>();

        for (int n : nom) {
            nombres.add(new Operand(n, "" + n));
        }

        this.max = new Operand(0, "");
        this.act = new Operand(0, "");
    }

    public static void main(String[] args) {
        int P = 49;
        Integer[] n = { 2, 3, 4, 5};

        HashMap<Character, BiFunction<Integer, Double, Double>> operators = new HashMap<>();

        operators.put('+', (Integer o1, Double o2) -> o1 + o2);
        operators.put('*', (Integer o1, Double o2) -> o1 * o2);
        operators.put('-', (Integer o1, Double o2) -> o1 - o2);
        operators.put('/', (Integer o1, Double o2) -> o1 / o2);

        Cifras cifras = new Cifras(P, n, operators);
        cifras.busca();
    }

    private void busca() {
        if (act.nombre > max.nombre && act.nombre <= objectiu) {
            max = act;
            System.out.println(max);
        } else if (act.nombre != objectiu) {

            ArrayList<Operand> tots = new ArrayList<>(nombres);
            tots.addAll(calculats);

            for (Operand op1 : tots) {
                if (!usats.contains(op1)) {
                    usats.add(op1);
                    for (Character operator : operators.keySet()) {
                        for (Operand op2 : tots) {
                            if (!usats.contains(op2)) {
                                usats.add(op2);
                                if (esValida(op1.nombre, operator, op2.nombre)) {
                                    //System.out.printf("%d %s %d\n", op1.nombre, operator, op2.nombre);

                                    Double num = operators.get(operator).apply(op1.nombre, (double) op2.nombre);
                                    String str = String.format("(%s %s %s)", op1.calcul, operator, op2.calcul);

                                    Operand aux = new Operand(num, str);

                                    calculats.add(aux);
                                    act = aux;

                                    busca();

                                    calculats.remove(aux);

                                }
                                usats.remove(op2);
                            }
                        }
                    }
                    usats.remove(op1);
                }

            }
        }
    }

    private boolean esValida(int n1, Character operator, double n2) {
        double aux = operators.get(operator).apply(n1, n2);

        return aux >= 0 && aux == (int) aux;
    }
}