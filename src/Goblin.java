public class Goblin extends Entity{
    public Goblin(String name, int strength, int agility, int healthPoint, int experience,
                  int gold, int percentOfCriticalDamage) {
        super(name, strength, agility, healthPoint, experience, gold, percentOfCriticalDamage, 0);
    }
}