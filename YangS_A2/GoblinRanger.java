import java.io.File;
import java.util.Scanner;

public class GoblinRanger {
	private String Name = "Goblin Ranger";
	private int HP;
	private int DEF;
	private int ATK;
	private int DMG;
	private boolean isDefending;

	public void resetMonster() {
		try {
			Scanner file = new Scanner(new File("goblinRangerStats"));
			this.HP = file.nextInt();
			this.DEF = file.nextInt();
			this.ATK = file.nextInt();
			this.DMG = file.nextInt();
		} catch (Exception e) {
		}

	}

	public String defend() {
		this.DEF += 2;
		this.isDefending = true;
		return "Goblin Ranger is defending, DEF has risen\n";
	}

	public int makeAttack() {
		return (this.ATK + (int) (Math.random() * 20));
	}

	public String specialAbility() {
		this.DMG += 10;
		return "Goblin Ranger used 'Critical Sight', Goblin Ranger DMG has risen greatly\n";
	}

	public String clearBuff() {
		if (this.isDefending) {
			this.DEF -= 2;
			this.isDefending = false;
			return "DEF buff of Goblin Ranger was cleared\n";
		} else {
			return "";
		}
	}

	public String takeAttack(int atkRoll, int dmg) {
		if (atkRoll > this.DEF) {
			this.HP -= dmg;
			return "Goblin Ranger took " + dmg + "dmg.\n";
		} else {
			return "Player attack failed\n";
		}
	}

	public String getName() {
		return this.Name;
	}

	public int getHP() {
		return this.HP;
	}

	public int getDEF() {
		return this.DEF;
	}

	public int getATK() {
		return this.ATK;
	}

	public int getDMG() {
		return this.DMG;
	}

}
