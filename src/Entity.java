import java.util.Random;

public abstract class Entity implements Attackable {

    public static Random random = new Random();

    private final String name;
    public int levelHero = 1;
    public int stepForNextLevel = 10;
    public int countPotion;
    private int strength;
    private int agility;
    private int healthPoint;
    private int experience;
    private int gold;
    private int percentOfCriticalDamage;


    public Entity(String name, int strength, int agility, int healthPoint, int experience,
                  int gold, int percentOfCriticalDamage, int countPotion) {
        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.healthPoint = healthPoint;
        this.experience = experience;
        this.gold = gold;
        this.percentOfCriticalDamage = percentOfCriticalDamage;
        this.countPotion = countPotion;
    }

    @Override
    public int attack() {
        int randomNumber = random.nextInt(101);
        if ((agility * 3 >= randomNumber) && (percentOfCriticalDamage >= randomNumber)) {
            return 2 * strength;
        } else if (agility * 3 >= randomNumber) {
            return strength;
        } else {
            return 0;
        }
    }

    public int getLevelHero() {
        return levelHero;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getPercentOfCriticalDamage() {
        return percentOfCriticalDamage;
    }

    public void setPercentOfCriticalDamage(int percentOfCriticalDamage) {
        this.percentOfCriticalDamage = percentOfCriticalDamage;
    }

    public int getCountPotion() {
        return countPotion;
    }

    public void giveInfo() {
        System.out.printf("""
                        Имя - %s.
                        Уровень - %d.
                        Здоровья - %d из %d.
                        Силы - %d.
                        Ловкости - %d.
                        Шанс критической атаки - %d.
                        Голды - %d.
                        Опыта - %d из %d.
                        Зелий восстановления здоровья - %d.
                                                               
                        """, getName(), getLevelHero(), getHealthPoint(), AuthorOfStory.startHealthPoint, getStrength(), getAgility(),
                getPercentOfCriticalDamage(), getGold(), getExperience(), stepForNextLevel + 50, getCountPotion());
    }

    public void levelUp(Entity hero) {
        if (hero.getExperience() >= hero.stepForNextLevel + 50) {
            hero.stepForNextLevel += 10;
            hero.levelHero++;
            hero.setExperience(hero.getExperience() - hero.stepForNextLevel - 50);

            hero.setStrength((int) (AuthorOfStory.startStrength * (1 + hero.getLevelHero() * 0.07)));
            hero.setAgility((int) (AuthorOfStory.startAgility * (1 + hero.getLevelHero() * 0.07)));
            hero.setHealthPoint((int) (AuthorOfStory.startHealthPoint * (1 + hero.getLevelHero() * 0.07)));
            AuthorOfStory.startHealthPoint = hero.getHealthPoint();
            System.out.printf("-%s, ты получил %d-уровень! Твои характеристики увеличены, а здоровье восстановлено!",
                    hero.getName(), hero.getLevelHero());
        }
    }
}