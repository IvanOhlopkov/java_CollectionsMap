package practice;

import java.util.*;

public class PhoneBook {
    private final HashMap<String, String> phoneList;

    public PhoneBook() {
        this.phoneList = new HashMap<>();
    }

    public void addContact(String phone, String name) {
        if (!checkNameContact(name).isEmpty()) {
            if (!phoneList.containsValue(name)) {
                if (Objects.equals(phone, "")) {
                    phoneList.clear();
                } else if (checkPhoneNumber(phone).isEmpty()) {
                    phoneList.clear();
                } else {
                    /*выбрал ключ - номер телефона, так как он уникален, а имя может быть не уникальным,
                    так логичней (ТЗ). Если бы ключом выбрал имя, то две Маши перезаписывали бы друг друга,
                    и телефон первой Маши терялся бы.
                    Плюс: можно перезаписывать имя при совпадении телефона,
                    следовательно одна запись в коллекции. Телефоны не теряются
                    Минус: когда имя имеет два номера и более, ключ приходится обновлять
                     */
                    phoneList.put(phone, name);
                }
            } else {
                for (Map.Entry<String, String> entry : phoneList.entrySet()) {
                    String key = entry.getKey();
                    phoneList.put(key + ", " + phone, phoneList.remove(key));
                }
            }
        } else if (!checkPhoneNumber(phone).isEmpty()) {
            if (!phoneList.containsKey(phone)) {
                if (Objects.equals(name, "")) {
                    phoneList.clear();
                } else if (checkNameContact(name).isEmpty()) {
                    phoneList.clear();
                } else {
                    phoneList.put(phone, name);
                }
            }
        }
    }

    public String getContactByPhone(String phone) {
        for (Map.Entry<String, String> entry : phoneList.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (phone.equals(key)) {
                return value + " - " + key;
            }
        }
        return "";
    }

    public Set<String> getContactByName(String name) {
        for (Map.Entry<String, String> entry : phoneList.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (name.equals(value)) {
                return Collections.singleton(value + " - " + key);
            }
        }
        return new TreeSet<>();
    }

    public Set<String> getAllContacts() {
        TreeSet<String> contacts = new TreeSet<>();
        for (Map.Entry<String, String> entry : phoneList.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            contacts.add(value + " - " + key);

        }
        return contacts;
    }

    public String checkNameContact(String name) {
        String regex = "[А-Я]{1}[а-я]+";
        if (name.matches(regex)) {
            return name;
        }
        return "";
    }

    public String checkPhoneNumber(String phone) {
        String regex = "[0-9]+";
        if (phone.matches(regex)) {
            return phone;
        }
        return "";
    }
}