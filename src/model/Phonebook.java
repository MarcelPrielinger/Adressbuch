package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Phonebook
{
    public static ArrayList<Person> people = new ArrayList<>();

    public ArrayList<Person> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

    public int size()
    {
        return people.size();
    }

    public void save()
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("save.Phonebook.csv")))
        {
            for (int c = 0; c < this.size(); c++)
            {
                bw.write(people.get(c).toString());
                bw.newLine();
            }
            System.out.println("Speichern erfolgreich!");
        } catch (Exception e) {
            System.out.println("Fehler beim Speichern!");
        }
    }

    public void load()
    {
        people.clear();
        try(BufferedReader br = new BufferedReader(new FileReader("save.Phonebook.csv")))
        {
            String s = "";
            while ((s = br.readLine()) != null)
            {
                String split[] = s.split(",");
                people.add(new Person(split[0], split[1], Integer.parseInt(split[2])));
            }
            System.out.println("Erfolgreich geladen!");
        } catch (Exception e) {
            System.out.println("Fehler beim Laden!");
        }
    }

}
