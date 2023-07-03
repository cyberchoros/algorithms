```
import java.util.Scanner;
import java.util.HashMap;

import ds.tree.*;

public class Test {
    public static void main (String[] args) {
        HashMap<String, Tree<Integer, String>> map = new HashMap<>();

        map.put("BST", new BST<>());
        map.put("AVL", new AVL<>());
        map.put("SPL", new SPL<>());
        map.put("RBT", new RBT<>());

        String param = args.length > 0 && map.containsKey(args[0]) ? args[0] : "BST";
        Tree<Integer, String> btree = map.get(param);

        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            String[] command = scanner.nextLine().split(" ");
            if (command[0].compareTo("exit") == 0) break;

            switch (command[0]) {
                case "insert": System.out.println(btree.insert(Integer.parseInt(command[1]), command[2])); break;
                case "delete": System.out.println(btree.delete(Integer.parseInt(command[1]))); break;
                case "update": System.out.println(btree.update(Integer.parseInt(command[1]), command[2])); break;
                case "search": System.out.println(btree.search(Integer.parseInt(command[1]))); break;
                case "desc":
                    for (TreeNode<Integer, String> node : btree)
                        System.out.println(node.getIndex() + " " + node.getValue());
                    System.out.println("===" + btree.size() + "===");
                    btree.forEach((index, value) -> { System.out.println(index + " " + value); });
                    break;
                case "clear": btree.clear(); break;
            }
        }
    }
}
```

Note: `key` should implements Comparable, and basic data type should be replaced with `wrapper class`.
