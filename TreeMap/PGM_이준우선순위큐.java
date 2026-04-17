import java.util.*;

class Solution {
    static TreeMap<Integer, Integer> tree;

    public int[] solution(String[] operations) {
        tree = new TreeMap<>();
        int[] answer = new int[2];

        for (String op : operations) {
            String[] str = op.split(" ");

            if (str[0].equals("I")) {
                add(Integer.parseInt(str[1]));
            } else {
                int num = Integer.parseInt(str[1]);
                if (num == 1) {
                    removeMax();
                } else {
                    removeMin();
                }
            }
        }

        if (!tree.isEmpty()) {
            answer[0] = tree.lastKey();
            answer[1] = tree.firstKey();
        }

        return answer;
    }

    private static void add(int a) {
        tree.put(a, tree.getOrDefault(a, 0) + 1);
    }

    private static void removeMin() {
        if (tree.isEmpty()) return;

        Map.Entry<Integer, Integer> entry = tree.firstEntry();

        if (entry.getValue() == 1) {
            tree.pollFirstEntry();
        } else {
            tree.put(entry.getKey(), entry.getValue() - 1);
        }
    }

    private static void removeMax() {
        if (tree.isEmpty()) return;

        Map.Entry<Integer, Integer> entry = tree.lastEntry();

        if (entry.getValue() == 1) {
            tree.pollLastEntry();
        } else {
            tree.put(entry.getKey(), entry.getValue() - 1);
        }
    }
}
