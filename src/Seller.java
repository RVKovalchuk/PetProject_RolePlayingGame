import java.util.Scanner;

public class Seller implements Sellable {
    private int countOfAmulet = 1;
    @Override
    public void sell(Hero hero, String string) {
        switch (string) {
            case "1" -> {
                if (hero.getGold() >= 20) {
                    System.out.println("-Оп! Держи зелье восстановления здоровья!");
                    hero.countPotion++;
                    hero.setGold(hero.getGold()-20);
                    infoOfAssortment(hero);
                    sell(hero, new Scanner(System.in).nextLine());
                } else {
                    infoAboutFailure(hero);
                }
            }
            case "2" -> {
                if (hero.getGold() >= 150) {
                    System.out.println("-Оп! Держи амулет убийцы!");
                    countOfAmulet--;
                    hero.setPercentOfCriticalDamage(hero.getPercentOfCriticalDamage() + 20);
                    hero.setGold(hero.getGold()-150);
                    infoOfAssortment(hero);
                    sell(hero, new Scanner(System.in).nextLine());
                } else {
                    infoAboutFailure(hero);
                }
            }
            case "пока" -> {
                AuthorOfStory.printNavigation();
                AuthorOfStory.command(new Scanner(System.in).nextLine());
            }
        }
    }

    public void infoOfAssortment(Hero hero) {
        System.out.printf("""
                                    -Надо что-нибудь, %s? Ты можешь купить:
                                    1. Зелье восстановления здоровья.
                                    Стоимость - 20 голды.
                                    При использовании восстановит тебе 50 ХитПоинтов.
                                    """,
                hero.getName());
        if (countOfAmulet > 0) {
            System.out.println("""
                                2. Амулет убийцы.
                                Стоимость - 150 голды.
                                Повышает шанс критической атаки на 20%.
                                """);
        }
        System.out.println("*(Сказать 'пока' продавцу.)");
    }

    private void infoAboutFailure(Hero hero) {
        System.out.printf("-Так не пойдет, у тебя только %d голды, этого недостаточно.\n", hero.getGold());
        infoOfAssortment(hero);
        sell(hero, new Scanner(System.in).nextLine());
    }
}