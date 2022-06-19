package HomeTask6.Task2;
/*Описати структуру коли людині може належати список акаунтів та зробити можливість
додати людину до списку;
додати акаунт до списку;
видалити акаунт для певної людини;
видалити людину;
показати всю мапу людей та їх акаунти.
Вважаємо, що людина - унікальний ключ, за значення value беремо List<Account>
Map<Person, List<Account> >

 */
import HomeTask6.Task1.Account;
import HomeTask6.Task1.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private static Map<Person, List<Account>> map = new HashMap<>();

    public static void main(String[] args) {

        // create person
        Person vasyl = new Person("Vasyl");
        // create accounts list
        List<Account> list1 = new ArrayList<>();
        // add accounts
        list1.add(new Account(1));
        list1.add(new Account(2));
        list1.add(new Account(3));
        List<Account> list3 = new ArrayList<>();
        list3.add(new Account(5));
        list3.add(new Account(7));

        // add person and account to map
        addPersonAndAccounts(vasyl, list1);
        // add person to map
        Person kolya = new Person("Kolya");
        addPersonAndAccounts(kolya, null);
        addPersonAndAccounts(new Person("Olena"), list3);
        printMyMap(map);

        //remove person
        removePerson(map, kolya);

        // get accounts list by person
        List<Account> list2 = map.get(vasyl);
        // remove an account in list
        list2.remove(1);
        // update edited list for the person
        addPersonAndAccounts(vasyl, list2);
        printMyMap(map);

    }

    private static void addPersonAndAccounts(Person person, List<Account> accountList) {
        map.put(person, accountList);
    }

    private static void removePerson(Map<Person, List<Account>> map, Person person) {
        map.remove(person);
        printMyMap(map);
    }

    private static void printMyMap(Map<Person, List<Account>> map) {
        map.forEach((person, accounts) -> {

            String text = "\nperson: %s accounts: %s ";
            if (accounts == null || accounts.isEmpty()) {
                System.out.printf(text, person.getName(), null);
            } else {
                StringBuffer accountsList = new StringBuffer();
                accounts.forEach(account -> {
                    accountsList.append("\naccount:");
                    accountsList.append(account.getId());
                });
                System.out.println();
                System.out.printf(text, person.getName(), accountsList);
            }

        });
        System.out.print("\n-----------------");

    }

}
