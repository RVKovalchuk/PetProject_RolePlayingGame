public class Arena {

    public void fight(Entity hero, Entity enemy, FightCallback fightCallback) {
        Runnable runnable = () -> {
            int turn = 1;
            boolean isFightEnded = false;
            while (!isFightEnded) {
                System.out.printf("----Сейчас #%d ход ----\n", turn);
                if (turn++ % 2 != 0) {
                    isFightEnded = makeDamage(enemy, hero, fightCallback);
                } else {
                    isFightEnded = makeDamage(hero, enemy, fightCallback);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private boolean makeDamage(Entity attacker, Entity defender, FightCallback fightCallback) {
        int damage = attacker.attack();
        int defenderHealth = defender.getHealthPoint() - damage;

        if (damage != 0) {
            System.out.printf("-%s атаковал %s и нанес %d урона.\n", attacker.getName(), defender.getName(), damage);
            System.out.printf("-%s сейчас с %d ХитПоинтами.\n", defender.getName(), defenderHealth);
        } else {
            System.out.printf("-%s промахнулся.\n", attacker.getName());
        }

        if (defenderHealth <= 0 && defender instanceof Hero) {
            System.out.printf("-%s пал в бою.\n", defender.getName());
            fightCallback.fightLost();
            return true;
        } else if (defenderHealth <= 0) {
            System.out.printf("-%s победил в этой битве! За победу ты получил %d голды и %d опыта.\n",
                    attacker.getName(), defender.getGold(), defender.getExperience());
            attacker.setGold(attacker.getGold() + defender.getGold());
            attacker.setExperience(attacker.getExperience() + defender.getExperience());

            attacker.levelUp(attacker);
            fightCallback.fightWin();
            return true;
        } else {
            defender.setHealthPoint(defenderHealth);
            return false;
        }
    }
}
