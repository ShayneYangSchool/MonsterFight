import java.io.File;
import java.util.Scanner;

public class GoblinMage {
	private String Name = "Goblin Mage";
	private int HP;
	private int DEF;
	private int ATK;
	private int DMG;
	private boolean isDefending;

	public void resetMonster() {
		try {
			Scanner file = new Scanner(new File("goblinMageStats"));
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
		return "Goblin Mage is defending, DEF has risen\n";
	}

	public int makeAttack() {
		return (this.ATK + (int) (Math.random() * 20));
	}

	public String specialAbility() {
		this.HP -= 10;
		this.DMG += 15;
		return "Goblin Mage used 'Blood Sacrifice', Goblin Mage HP has fallen, Goblin Mage DMG has risen immensely\n";
	}

	public String clearBuff() {
		if (this.isDefending) {
			this.DEF -= 2;
			this.isDefending = false;
			return "DEF buff of Goblin Mage was cleared\n";
		} else {
			return "";
		}
	}

	public String takeAttack(int atkRoll, int dmg) {
		if (atkRoll > this.DEF) {
			this.HP -= dmg;
			return "Goblin Mage took " + dmg + "dmg.\n";
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
