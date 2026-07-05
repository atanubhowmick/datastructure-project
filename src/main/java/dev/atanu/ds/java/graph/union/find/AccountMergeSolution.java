package dev.atanu.ds.java.graph.union.find;

import java.util.*;

public class AccountMergeSolution {

    public static void main(String[] args) {
        AccountMergeSolution solution = new AccountMergeSolution();
        List<List<String>> accounts = List.of(
                List.of("John","johnsmith@mail.com","john_newyork@mail.com"),
                List.of("John","johnsmith@mail.com","john00@mail.com"),
                List.of("Mary","mary@mail.com", "mary.k@mail.com"),
                List.of("John","johnnybravo@mail.com","johnny_bravo1@mail.com"),
                List.of("Mary","mary001@mail.com", "mary.k@mail.com", "mary.kate@mail.com")
        );
        System.out.println(solution.accountsMerge(accounts));
    }

    // Using Union find. Check other solution for different algorithm.
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();
        Map<String, String> parents = new HashMap<>();

        for (List<String> acc : accounts) {
            String name = acc.get(0);

            // Iterating over emails from index 1
            for (int i = 1; i < acc.size(); i++) {
                // putting the parent as itself
                parents.put(acc.get(i), acc.get(i));

                // Setting name for each email for future reference
                emailToName.put(acc.get(i), name);
            }
        }

        for (List<String> acc : accounts) {
            String firstEmail = acc.get(1);
            String parent = find(firstEmail, parents);

            // Building the graph
            for (int i = 2; i < acc.size(); i++) {
                parents.put(find(acc.get(i), parents), parent);
            }
        }

        // Create the union
        Map<String, TreeSet<String>> unions = union(accounts, parents);

        List<List<String>> result = new ArrayList<>();
        for (String parent : unions.keySet()) {
            List<String> emails = new ArrayList<>(unions.get(parent));

            String name = emailToName.get(parent);
            emails.add(0, name);
            result.add(emails);
        }
        return result;
    }

    private String find(String email, Map<String, String> parents) {
        return parents.get(email).equals(email) ? email : find(parents.get(email), parents);
    }

    private Map<String, TreeSet<String>> union(List<List<String>> accounts,
                                               Map<String, String> parents) {
        Map<String, TreeSet<String>> unions = new HashMap<>();
        for(List<String> acc : accounts) {
            String firstEmail = acc.get(1);
            String parent = find(firstEmail, parents);

            // Insert / Update to union
            if (!unions.containsKey(parent)) {
                unions.put(parent, new TreeSet<>());
            }

            for (int i = 1; i < acc.size(); i++) {
                unions.get(parent).add(acc.get(i));
            }
        }
        return unions;
    }
}
