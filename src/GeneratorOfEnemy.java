public class GeneratorOfEnemy {

    public static int countOfCreator = 0;

    static Entity createEnemy() {
        countOfCreator++;
        double k = 1 + countOfCreator * 0.07;
        int randomPoint = Entity.random.nextInt(5) + 1;
        if (randomPoint % 4 == 0) {
            return new Dragon("Дракон-Велзевул-" + countOfCreator, (int) (30 * k), (int) (20 * k), (int) (80 * k),
                    (int) (50 * k), (int) (150 * k), 10);
        } else if (randomPoint % 3 == 0) {
            return new Spider("Паук-Арахний-" + countOfCreator, (int) (20 * k), (int) (30 * k), (int) (70 * k),
                    (int) (50 * k), (int) (100 * k), 10);
        } else if (randomPoint % 2 == 0) {
            return new Goblin("Гоблин-Аркадий-" + countOfCreator, (int) (20 * k), (int) (20 * k), (int) (70 * k),
                    (int) (50 * k), (int) (70 * k), 10);
        } else {
            return new Skeleton("Скелет-Константин-" + countOfCreator, (int) (20 * k), (int) (20 * k),
                    (int) (70 * k), (int) (50 * k), (int) (50 * k), 10);
        }
    }
}
