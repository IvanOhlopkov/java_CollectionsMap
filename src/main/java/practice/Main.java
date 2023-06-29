package practice;

import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();
        while (true) {
            System.out.println("Введите номер, имя или команду:");
            String input = scanner.nextLine().trim();
            if (phoneBook.checkPhoneNumber(input).equals(input)) {
                if (phoneBook.getContactByPhone(input).isEmpty()) {
                    System.out.println("Такого номера нет в телефонной книге.");
                    System.out.println("Введите имя абонента для номера \"" + input + "\":");
                    String inputName = scanner.nextLine();
                    phoneBook.addContact(input, inputName);
                    System.out.println("Контакт сохранен!" + "\n");
                } else {
                    System.out.println(phoneBook.getContactByName(input));
                }

            } else if (phoneBook.checkNameContact(input).equals(input)) {
                TreeSet<String> treeSet = new TreeSet<>(phoneBook.getAllContacts());
                if (treeSet.isEmpty()) {
                    System.out.println("Такого имени нет в телефонной книге.");
                    System.out.println("Введите номер телефона для абонента \"" + input + "\":");
                    String inputPhone = scanner.nextLine();
                    phoneBook.addContact(inputPhone, input);
                    System.out.println("Контакт сохранен!" + "\n");
                } else {
                    System.out.println(phoneBook.getContactByName(input));
                }
            } else if ((!phoneBook.checkPhoneNumber(input).equals(input) ||
                    !phoneBook.checkNameContact(input).equals(input)) && !input.equals("LIST")) {
                System.out.println("Неверный формат ввода" + "\n");
            }

            if (input.equals("LIST")) {
                System.out.println(phoneBook.getAllContacts());
                return;
            }
        }
    }
}
