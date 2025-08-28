package org.example;

import org.example.entity.Employee;

import java.util.*;

/** Giriş noktası ve liste işlemleri */
public class Main {

    public static void main(String[] args) {
        LinkedList<Employee> employees = new LinkedList<>();
        employees.add(new Employee(1, "Dogancan", "Kinik"));
        employees.add(new Employee(1, "Dogancan", "Kinik"));
        employees.add(new Employee(2, "Seyyit Battal", "Arvas"));
        employees.add(new Employee(2, "Seyyit Battal", "Arvas"));
        employees.add(new Employee(3, "Anil", "Ensari"));
        employees.add(new Employee(3, "Anil", "Ensari"));
        employees.add(new Employee(4, "Burak", "Cevizli"));
        employees.add(null);

        System.out.println("Duplicates: " + findDuplicates(employees));
        System.out.println("Uniques: " + findUniques(employees));
        System.out.println("Removed dups: " + removeDuplicates(employees));
    }

    /** Tekrar eden çalışanları (ilk tekrar anından itibaren) döner. Null öğeleri yok sayar. */
    public static List<Employee> findDuplicates(List<Employee> list) {
        Set<Employee> seen = new HashSet<>();
        List<Employee> duplicates = new ArrayList<>();
        for (Employee e : list) {
            if (e == null) continue;
            if (!seen.add(e)) {
                duplicates.add(e);
            }
        }
        return duplicates;
    }

    /**
     * Tekrar edenlerden yalnızca BİR tanesini (ilk görüleni) ve tekrar etmeyenleri döndürür.
     * id -> Employee şeklinde map’e koyar. Null öğeleri yok sayar.
     */
    public static Map<Integer, Employee> findUniques(List<Employee> list) {
        Map<Integer, Employee> map = new LinkedHashMap<>();
        Set<Employee> seen = new HashSet<>();
        for (Employee e : list) {
            if (e == null) continue;
            // İlk kez gördüğümüz Employee’yi id’si ile kaydet (putIfAbsent → ilk görüleni korur)
            if (seen.add(e)) {
                map.putIfAbsent(e.getId(), e);
            }
        }
        return map;
    }

    /**
     * Birden fazla geçen TÜM kayıtları listeden ele; yalnızca 1 kez geçenleri
     * orijinal sırayı koruyarak döndürür. Null öğeleri tamamen yok sayar.
     */
    public static List<Employee> removeDuplicates(List<Employee> list) {
        Map<Employee, Integer> count = new HashMap<>();
        for (Employee e : list) {
            if (e == null) continue;
            count.put(e, count.getOrDefault(e, 0) + 1);
        }
        List<Employee> result = new ArrayList<>();
        for (Employee e : list) {
            if (e == null) continue;
            if (count.getOrDefault(e, 0) == 1) {
                result.add(e); // sırayı korur
            }
        }
        return result;
    }
}
