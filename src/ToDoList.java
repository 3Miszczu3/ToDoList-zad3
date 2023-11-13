import java.util.ArrayList;
import java.util.Scanner;

class Zadanie {
    private String nazwa;
    private String opis;
    private boolean czyZakonczone;

    public Zadanie(String nazwa, String opis) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.czyZakonczone = false;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public boolean czyZakonczone() {
        return czyZakonczone;
    }

    public void oznaczJakoZakonczone() {
        czyZakonczone = true;
    }
}

public class ListaZadan {
    private ArrayList<Zadanie> zadania;
    private Scanner scanner;

    public ListaZadan() {
        this.zadania = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        ListaZadan listaZadan = new ListaZadan();
        listaZadan.uruchom();
    }

    private void uruchom() {
        int wybor;
        do {
            wyswietlMenu();
            System.out.print("Wybierz opcję (1/2/3/4/5): ");
            wybor = scanner.nextInt();
            scanner.nextLine();

            switch (wybor) {
                case 1:
                    dodajZadanie();
                    break;
                case 2:
                    oznaczZadanieJakoZakonczone();
                    break;
                case 3:
                    usunZadanie();
                    break;
                case 4:
                    wyswietlZadania();
                    break;
                case 5:
                    System.out.println("Koniec programu.");
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        } while (wybor != 5);
    }

    private void wyswietlMenu() {
        System.out.println("1. Dodaj nowe zadanie");
        System.out.println("2. Oznacz zadanie jako zakończone");
        System.out.println("3. Usuń zadanie");
        System.out.println("4. Wyświetl listę zadań");
        System.out.println("5. Wyjście");
    }

    private void dodajZadanie() {
        System.out.print("Podaj nazwę zadania: ");
        String nazwa = scanner.nextLine();
        System.out.print("Podaj opis zadania: ");
        String opis = scanner.nextLine();

        Zadanie zadanie = new Zadanie(nazwa, opis);
        zadania.add(zadanie);

        System.out.println("Zadanie \"" + nazwa + "\" zostało dodane do listy.");
    }

    private void oznaczZadanieJakoZakonczone() {
        System.out.print("Podaj numer zadania do oznaczenia jako zakończone: ");
        int numerZadania = scanner.nextInt();
        scanner.nextLine(); // Skonsumuj znak nowej linii

        if (jestPoprawnymNumeremZadania(numerZadania)) {
            Zadanie zadanie = zadania.get(numerZadania - 1);
            zadanie.oznaczJakoZakonczone();
            System.out.println("Zadanie \"" + zadanie.getNazwa() + "\" zostało oznaczone jako zakończone.");
        } else {
            System.out.println("Nieprawidłowy numer zadania.");
        }
    }

    private void usunZadanie() {
        System.out.print("Podaj numer zadania do usunięcia: ");
        int numerZadania = scanner.nextInt();
        scanner.nextLine();

        if (jestPoprawnymNumeremZadania(numerZadania)) {
            Zadanie zadanie = zadania.remove(numerZadania - 1);
            System.out.println("Zadanie \"" + zadanie.getNazwa() + "\" zostało usunięte z listy.");
        } else {
            System.out.println("Nieprawidłowy numer zadania.");
        }
    }

    private void wyswietlZadania() {
        System.out.println("Lista zadań:");
        for (int i = 0; i < zadania.size(); i++) {
            Zadanie zadanie = zadania.get(i);
            String status = zadanie.czyZakonczone() ? "[x]" : "[ ]";
            System.out.println((i + 1) + ". " + status + " " + zadanie.getNazwa() + ": " + zadanie.getOpis());
        }

        if (zadania.isEmpty()) {
            System.out.println("(brak zadań)");
        }
    }

    private boolean jestPoprawnymNumeremZadania(int numerZadania) {
        return numerZadania >= 1 && numerZadania <= zadania.size();
    }
}
