import java.util.Scanner;

public class AuthorOfStory {
    private static final Scanner scanner = new Scanner(System.in);
    public static int startStrength = 30;
    public static int startAgility = 30;
    public static int startHealthPoint = 100;
    private static Entity hero = null;
    private static Seller seller = null;
    private static Arena arena = null;

    public static void printNavigation() {
        System.out.println("""
                -Ты можешь отправиться:
                1. в магазин.
                2. на арену.
                                
                -Так же ты можешь:
                *Покинуть игру (выйти).
                *Получить информацию о себе (инфо).
                """);
        if (hero.getHealthPoint() < AuthorOfStory.startHealthPoint && hero.countPotion > 0) {
            System.out.println("*Полечить себя (лечение).");
        }
    }

    private static void commitFight() {
        arena.fight(hero, GeneratorOfEnemy.createEnemy(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.printf("-У тебя %d опыта, %d голды и  количество ХитПоинтов - %d.\n",
                        hero.getExperience(), hero.getGold(), hero.getHealthPoint());
                System.out.println("-Хочешь продолжить сражаться? (да/нет)");
            }

            @Override
            public void fightLost() {
                System.out.println("-Битва окончена.");
                command("выйти");
            }
        });
    }

    public static void command(String string) {
        if (arena == null) {
            arena = new Arena();
        }
        if (seller == null) {
            seller = new Seller();
        }
        if (hero == null) {
            hero = new Hero(string, AuthorOfStory.startStrength, AuthorOfStory.startAgility,
                    AuthorOfStory.startHealthPoint, 0, 0, 10,
                    GeneratorOfEnemy.countOfCreator);
            System.out.printf("-Нашего Героя зовут %s! Да благославят его приключения звезды!\n", hero.getName());

            printNavigation();
        }
        switch (string) {
            case "1" -> {
                seller.infoOfAssortment((Hero) hero);
                seller.sell((Hero) hero, scanner.nextLine());
            }
            case "2", "да" -> commitFight();
            case "выйти" -> System.exit(1);
            case "нет" -> {
                printNavigation();
                command(scanner.nextLine());
            }
            case "лечение" -> {
                if (hero.getHealthPoint() + 50 >= AuthorOfStory.startHealthPoint) {
                    hero.countPotion--;
                    hero.setHealthPoint(AuthorOfStory.startHealthPoint);
                    System.out.printf("-Сейчас у тебя %d ХитПоинтов. Осталось %d зелий восстановления здоровья.\n",
                            hero.getHealthPoint(), hero.getCountPotion());
                    printNavigation();
                } else if (hero.getHealthPoint() + 50 < AuthorOfStory.startHealthPoint) {
                    hero.countPotion--;
                    hero.setHealthPoint(hero.getHealthPoint() + 50);
                    System.out.printf("-Сейчас у тебя %d ХитПоинтов. Осталось %d зелий восстановления здоровья.\n",
                            hero.getHealthPoint(), hero.getCountPotion());
                    printNavigation();
                }
            }
            case "инфо" -> {
                hero.giveInfo();
                printNavigation();
            }
        }
        command(scanner.nextLine());
    }
}
