import java.io.File;
import java.util.Scanner;

public class Goblin {
	private String Name = "Goblin";
	private int HP;
	private int DEF;
	private int ATK;
	private int DMG;
	private boolean isDefending;

	public void resetMonster() {
		try {
			Scanner file = new Scanner(new File("goblinStats"));
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
		return "Goblin is defending, DEF has risen\n";
	}

	public int makeAttack() {
		return (this.ATK + (int) (Math.random() * 20));
	}

	public String specialAbility() {
		this.DEF -= 5;
		this.DMG += 5;
		return "Goblin used 'Berserk', Goblin DEF has fallen, Goblin DMG has risen\n";
	}

	public String clearBuff() {
		if (this.isDefending) {
			this.DEF -= 2;
			this.isDefending = false;
			return "DEF buff of Goblin was cleared\n";
		} else {
			return "";
		}
	}

	public String takeAttack(int atkRoll, int dmg) {
		if (atkRoll > this.DEF) {
			this.HP -= dmg;
			return "Goblin took " + dmg + "dmg.\n";
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
