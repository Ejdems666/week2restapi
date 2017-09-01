package data;

import entitites.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 31/08/2017.
 */
public class PeopleRepository {
    private static List<Person> people;
    static {
        people = new ArrayList<>();
        people.add(new Person(1l,"Adam","Becvar", 20));
        people.add(new Person(2l,"Adam2","Becvar2", 22));
    }

    public static List<Person> getAll() {
        return people;
    }

    public static Person getById(long id) {
        for (Person person : people) {
            if (person.getId().equals(id)) {
                return person;
            }
        }
        return null;
    }

    public static void addPerson(Person person) {
        person.setId(people.size()+1l);
        people.add(person);
    }
}
