public class Dragon extends Entity{

    public Dragon(String name, int strength, int agility, int healthPoint, int experience,
                  int gold, int percentOfCriticalDamage) {
        super(name, strength, agility, healthPoint, experience, gold, percentOfCriticalDamage, 0);
    }
}