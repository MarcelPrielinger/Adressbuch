package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Phonebook
{
    private ArrayList<Person> people = new ArrayList<>();

    public void addPerson(Person person) {
        this.people.add(person);
    }

    public int size()
    {
        return people.size();
    }

    public void save(boolean isAdd)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\src\\save\\Phonebook.csv",isAdd))) {
            for (int i = 0; i < people.size(); i++) {
                bw.write(people.get(i).toString());
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
        try(BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\save\\Phonebook.csv")))
        {
            String s;
            while ((s = br.readLine()) != null)
            {
                String split[] = s.split(";");
                people.add(new Person(split[0], split[1], split[2]));
            }
            System.out.println("Erfolgreich geladen!");
        } catch (Exception e) {
            System.out.println("Fehler beim Laden!");
        }
    }

    public String getName(int index)
    {
        return this.people.get(index).getName();
    }

    public String getAddress(int index)
    {
        return this.people.get(index).getAddress();
    }

    public String getTelephone(int index)
    {
        return this.people.get(index).getTelephone();
    }

    public void delete(int index)
    {
        people.remove(index);
    }



}
