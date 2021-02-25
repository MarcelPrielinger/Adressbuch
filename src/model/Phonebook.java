package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Phonebook
{
    private ArrayList<Person> people = new ArrayList<>();

    public ArrayList<Person> getPeople() {
        return people;
    }

    public void addPerson(Person person) {
        this.people.add(person);
    }

    public int size()
    {
        return people.size();
    }

    public void save(boolean isAdd)
    {
        if (isAdd == false) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\src\\save\\Phonebook.csv"))) {
                for (int c = 0; c < people.size(); c++) {
                    bw.write(people.get(c).toString());
                    bw.newLine();
                }
                bw.close();
                System.out.println("Speichern erfolgreich!");
            } catch (Exception e) {
                System.out.println("Fehler beim Speichern!");
            }
        }
        else {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\src\\save\\Phonebook.csv",true))) {
                for (int c = 0; c < people.size(); c++) {
                        bw.write(people.get(c).toString());
                        bw.newLine();
                }
                bw.close();
                System.out.println("Speichern erfolgreich!");
            } catch (Exception e) {
                System.out.println("Fehler beim Speichern!");
            }
        }
    }

    public void load()
    {
        people.clear();
        try(BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\save\\Phonebook.csv")))
        {
            String s = "";
            while ((s = br.readLine()) != null)
            {
                String split[] = s.split(";");
                people.add(new Person(split[0], split[1], Integer.parseInt(split[2])));
            }
            br.close();
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

    public int getTelephone(int index)
    {
        return this.people.get(index).getTelephone();
    }



}
