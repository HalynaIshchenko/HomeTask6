package HomeTask6.Task1;
/*Описати структуру коли людині належить акаунт та зробити можливість
        додати акаунт до списку;
        видалити акаунт для певної людини;
        видалити людину;
        показати всю мапу людей та їх акаунти.
        Вважаємо, що людина - унікальний ключ, за значення value беремо Account
        Map<Person, Account>

 */

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Person, Account> map = new HashMap<>();

        Person vasyl = new Person("Vasyl");
        Account value = new Account(1);
        map.put(vasyl, value);
        Person kolya = new Person("Kolya");
        map.put(kolya, new Account(2));
        map.put(new Person("Olya"), new Account(3));
        map.put(new Person("Olena"), new Account(4));
        printMyMap(map);
        //remove account for person Vasyl
        map.put(vasyl, null);
        printMyMap(map);
        //remove person
        map.remove(vasyl);
        printMyMap(map);


    }

    private static void printMyMap(Map<Person, Account> map) {
        map.forEach((person, account) -> {
            Integer accountId = null;
            if (account != null) {
                accountId = account.getId();
            }
            System.out.printf("person: %s account: %s \n", person.getName(), accountId);
        });
        System.out.println();
    }
}
