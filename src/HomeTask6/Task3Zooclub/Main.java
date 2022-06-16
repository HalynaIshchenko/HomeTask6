package HomeTask6.Task3Zooclub;
/*Створити структуру Зооклуб.
        Створити клас Person , який описати двома полями : ім’я , вік.
        Створити клас Animal , який описати двома полями : тип тваринки(кіт,пес), ім’я тваринки.
        Кожна людина може мати багато тваринок. Але одна тваринка належить лише одній людині.
        Map<Person, List<Animal>> map;
        Реалізувати консольне меню(НЕ обов’язково), таким чином щоб можна було:
        Додати людину до клубу
        Додати тваринку до людини
        Видалити(забрати) тваринку від людини
        Видалити людину з клубу
        Вивести на екран зооклуб
        Вийти з програми
        Використати для побудови меню Switch.
 */

import java.util.*;

public class Main {
    public static void main(String[] args) throws CustomApplicationException {
        Map<Person, List<Animal>> map = new HashMap<>();

        Person mika = new Person("Mika", 23);
        Person sonya = new Person("Sonya", 31);
        Person igor = new Person("Igor", 32);
        Person taras = new Person("Taras", 28);
        List<Animal> list1 = new ArrayList<>();
        list1.add(new Animal("cat", "Myrchuk"));
        list1.add(new Animal("cat", "Pushok"));
        List<Animal> list2 = new ArrayList<>();
        list2.add(new Animal("dog", "Tyzik"));
        List<Animal> list3 = new ArrayList<>();
        list3.add(new Animal("cat", "Myrzik"));
        List<Animal> list4 = new ArrayList<>();
        list4.add(new Animal("dog", "Linda"));
        list4.add(new Animal("dog", "Bilka"));
        list4.add(new Animal("cat", "Vaska"));
        map.put(mika, list1);
        map.put(sonya, list2);
        map.put(igor, list3);
        map.put(taras, list4);
        map.put(new Person("Nina", 17), null);

        startApplication(map);

    }

    private static void startApplication(Map<Person, List<Animal>> map) throws CustomApplicationException {
        System.out.printf("Якщо Ви хочете:\n" +
                "Додати людину до клубу, введіть число %s\n" +
                "Додати тваринку до людини, введіть число %s\n" +
                "Видалити(забрати) тваринку від людини, введіть число %s\n" +
                "Видалити людину з клубу, введіть число %s\n" +
                "Вивести на екран зооклуб, введіть число %s\n" +
                "Вийти з програми, введіть число %s\n", 1, 2, 3, 4, 5, 6);
        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.println("Введіть вибране число:");
        int a = sc.nextInt();
        switch (a) {
            case 1 -> {
                addPerson(map, sc);
                startApplication(map);
            }
            case 2 -> {
                addAnimal(map, sc);
                startApplication(map);
            }
            case 3 -> {
                removeAnimal(map, sc);
                startApplication(map);
            }
            case 4 -> {
                removePerson(map, sc);
                startApplication(map);
            }
            case 5 -> {
                printMyMap1(map);
                startApplication(map);
            }
            case 6 -> exitProgram();
            default -> {
                System.out.println("Ви ввели невірне число.\n");
                startApplication(map);
            }
        }
    }

    private static void printMyMap1(Map<Person, List<Animal>> map) {
        map.forEach((person, animals) -> {

            String text = "\nPerson: %s  animals: %s";
            if (animals == null || animals.isEmpty()) {
                System.out.printf(text, person.getName(), null);
            } else {
                StringBuffer animalsList = new StringBuffer();
                animals.forEach(animal -> {
                    animalsList.append("\nAnimal:");
                    animalsList.append(animal.getType());
                    animalsList.append(animal.getName());
                });
                System.out.println();
                System.out.printf(text, person.getName(), animalsList);
            }

        });
        System.out.print("\n-----------------");

    }

    private static void removePerson(Map<Person, List<Animal>> map, Scanner sc) throws CustomApplicationException {
        System.out.println("Введіть ім'я людини:");
        String name = sc.next();
        Person person = findPersonByName(map, name);
        if (person == null) {
            throw new CustomApplicationException(String.format("Людина %s відсутня в списку", name));
        }
        map.remove(person);
        System.out.printf("Користувач %s успішно видалений.\n", name);
    }

    /*
     * @return Person
     *     if method didn't find object in list, return null
     */
    private static Person findPersonByName(Map<Person, List<Animal>> map, String name) {
        Person foundPerson = null;
        for (Person person : map.keySet()) {
            if (person.getName().equalsIgnoreCase(name)) {
                foundPerson = person;
                break;
            }
        }
        return foundPerson;

    }

    private static void removeAnimal(Map<Person, List<Animal>> map, Scanner sc) throws CustomApplicationException {
        System.out.println("Введіть ім'я людини:");
        String name = sc.next();
        Person person = findPersonByName(map, name);
        if (person == null) {
            throw new CustomApplicationException("Людина відсутня в списку\n");
        }
        System.out.println("Введіть ім'я тварини яку ви хочете видалити:");
        String animalName = sc.next();
        removeAnimalByName(map, person, animalName);

    }

    private static void removeAnimalByName(Map<Person, List<Animal>> map, Person person, String animalName) throws CustomApplicationException {
        List<Animal> animalsList = map.get(person);
        if (animalsList == null || animalsList.isEmpty()) {
            throw new CustomApplicationException("Ця людина не має жодної тварини\n");
        }
        Animal animal = findAnimalByName(animalsList, animalName);
        if (animal == null) {
            throw new CustomApplicationException("Ця людина не має тварини з ім'ям: " + animalName);
        }
        animalsList.remove(animal);
        System.out.println("Тварину успішно забрали.\n");

    }

    /*
     * @return Animal
     *     if method didn't find object in list, return null
     */
    private static Animal findAnimalByName(List<Animal> animalList, String animalName) {
        Animal foundAnimal = null;
        for (Animal animal : animalList) {
            if (animal.getName().equalsIgnoreCase(animalName)) {
                foundAnimal = animal;
                break;
            }
        }
        return foundAnimal;
    }

    private static void addAnimal(Map<Person, List<Animal>> map, Scanner sc) throws CustomApplicationException {
        System.out.println("Введіть ім'я людини:");
        String name = sc.next();
        Person person = findPersonByName(map, name);
        if (person == null) {
            throw new CustomApplicationException("Людина відсутня в списку\n");
        }
        List<Animal> animalList = map.get(person);
        System.out.println("Введіть назву тварини:");
        String animalName = sc.next();
        Animal animal = findAnimalByName(animalList, animalName);
        if (animal != null) {
            throw new CustomApplicationException("Ця тварина вже є в списку\n");
        }
        System.out.println("Введіть тип тварини:");
        String type = sc.next();
        Animal newAnimal = new Animal(type, animalName);
        animalList.add(newAnimal);
        map.put(person, animalList);
        System.out.printf("Тварина %s успішно додана до людини %s.\n", animalName, name);
    }

    private static void addPerson(Map<Person, List<Animal>> map, Scanner sc) throws CustomApplicationException {
        System.out.println("Введіть ім'я людини:");
        String name = sc.next();
        Person foundPerson = findPersonByName(map, name);
        if (foundPerson != null) {
            throw new CustomApplicationException("Ця людина вже є в списку\n");
        }
        System.out.println("Введіть вік людини:");
        int age = sc.nextInt();
        Person person = new Person(name, age);
        map.put(person, null);
        System.out.printf("Користувач %s успішно доданий.\n", name);
    }

    private static void exitProgram() {
        System.exit(1);
    }

}
