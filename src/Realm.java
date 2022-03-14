import java.util.Scanner;

public class Realm {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("-Представься, Герой!");
        AuthorOfStory.command(scanner.nextLine());
    }
}
