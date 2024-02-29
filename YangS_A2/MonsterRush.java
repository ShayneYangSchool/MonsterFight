
/*
Name:
Teacher:

*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class MonsterRush {
	int playerHP = 100;
	int playerDef = 30;
	int playerAtk = 30;
	int playerDmg = 20;
	boolean isDefending;
	int[] monsterOrder = { 1, 2, 2, 4, 3, 2, 4, 3, 1, 3 };
	Scanner sc;
	Goblin gob = new Goblin();
	GoblinThief gobT = new GoblinThief();
	GoblinRanger gobR = new GoblinRanger();
	GoblinMage gobM = new GoblinMage();

	MonsterRush() {
		sc = new Scanner(System.in);
	}

	public void game() {
		int encounterNum = -1;
		int monsterHP = 100;
		while (encounterNum < 9 && playerHP > 0) {
			encounterNum++;
			newMonster(monsterOrder[encounterNum]);
			if (monsterOrder[encounterNum] == 1) {
				monsterHP = gob.getHP();
			} else if (monsterOrder[encounterNum] == 2) {
				monsterHP = gobT.getHP();
			} else if (monsterOrder[encounterNum] == 3) {
				monsterHP = gobR.getHP();
			} else if (monsterOrder[encounterNum] == 4) {
				monsterHP = gobM.getHP();
			}
			while (playerHP > 0 && monsterHP > 0) {
				printStats(monsterOrder[encounterNum]);
				System.out.println("Enter 1 to attack, enter 2 to defend");
				int playerAction = sc.nextInt();
				double randAction = (Math.random() * 10.0);
				if (playerAction == 2) {
					System.out.println(defend());
				}
				if (randAction <= 4) {
					if (monsterOrder[encounterNum] == 1) {
						System.out.print(gob.defend());
					} else if (monsterOrder[encounterNum] == 2) {
						System.out.print(gobT.defend());
					} else if (monsterOrder[encounterNum] == 3) {
						System.out.print(gobR.defend());
					} else if (monsterOrder[encounterNum] == 4) {
						System.out.print(gobM.defend());
					}
				}
				if (playerAction == 1) {
					if (monsterOrder[encounterNum] == 1) {
						System.out.print(gob.takeAttack((int) (Math.random() * 20) + playerAtk, playerDmg));
					} else if (monsterOrder[encounterNum] == 2) {
						System.out.print(gobT.takeAttack((int) (Math.random() * 20) + playerAtk, playerDmg));
					} else if (monsterOrder[encounterNum] == 3) {
						System.out.print(gobR.takeAttack((int) (Math.random() * 20) + playerAtk, playerDmg));
					} else if (monsterOrder[encounterNum] == 4) {
						System.out.print(gobM.takeAttack((int) (Math.random() * 20) + playerAtk, playerDmg));
					}
				}
				if (randAction > 9) {
					if (monsterOrder[encounterNum] == 1 && gob.getHP() > 0) {
						System.out.println(gob.specialAbility());
					} else if (monsterOrder[encounterNum] == 2 && gobT.getHP() > 0) {
						System.out.println(gobT.specialAbility());
					} else if (monsterOrder[encounterNum] == 3 && gobR.getHP() > 0) {
						System.out.println(gobR.specialAbility());
					} else if (monsterOrder[encounterNum] == 4 && gobM.getHP() > 0) {
						System.out.println(gobM.specialAbility());
					}
				} else if (randAction > 4) {
					if (monsterOrder[encounterNum] == 1 && gob.getHP() > 0) {
						if (gob.makeAttack() > playerDef) {
							playerHP -= gob.getDMG();
							System.out.println("Player took " + gob.getDMG() + "dmg.");
						} else {
							System.out.println("Monster attack failed");
						}
					} else if (monsterOrder[encounterNum] == 2 && gobT.getHP() > 0) {
						if (gobT.makeAttack() > playerDef) {
							playerHP -= gobT.getDMG();
							System.out.println("Player took " + gobT.getDMG() + "dmg.");
						} else {
							System.out.println("Attack failed");
						}
					} else if (monsterOrder[encounterNum] == 3 && gobR.getHP() > 0) {
						if (gobR.makeAttack() > playerDef) {
							playerHP -= gobR.getDMG();
							System.out.println("Player took " + gobR.getDMG() + "dmg.");
						} else {
							System.out.println("Attack failed");
						}
					} else if (monsterOrder[encounterNum] == 4 && gobM.getHP() > 0) {
						if (gobM.makeAttack() > playerDef) {
							playerHP -= gobM.getDMG();
							System.out.println("Player took " + gobM.getDMG() + "dmg.");
						} else {
							System.out.println("Attack failed");
						}
					}
				}
				if (monsterOrder[encounterNum] == 1) {
					monsterHP = gob.getHP();
				} else if (monsterOrder[encounterNum] == 2) {
					monsterHP = gobT.getHP();
				} else if (monsterOrder[encounterNum] == 3) {
					monsterHP = gobR.getHP();
				} else if (monsterOrder[encounterNum] == 4) {
					monsterHP = gobM.getHP();
				}

				clearDef();
			}
			System.out.println("Monsters Dead: " + (encounterNum + 1));
			if (playerHP > 0) {
				playerHP = 100;
				System.out.println("Player was healed to full HP");
			} else {
				System.out.println("U DIED!!!");
			}
		}
		if (playerHP>0) {
			System.out.println("YOU WON!!!");
		}
	}

	private String defend() {
		playerDef += 2;
		isDefending = true;
		return "Player defending, DEF+";
	}

	private void clearDef() {
		System.out.print(gob.clearBuff());
		System.out.print(gobT.clearBuff());
		System.out.print(gobR.clearBuff());
		System.out.print(gobM.clearBuff());
		if (isDefending) {
			playerDef -= 2;
			isDefending = false;
			System.out.println("Player stopped defending, DEF-");
		}
	}

	private void printStats(int monsterType) {
		if (monsterType == 1) {
			System.out.printf("Player    | Goblin\n");
			System.out.printf("HP:  %-3d  | %-3d\n", playerHP, gob.getHP());
			System.out.printf("DEF: %-3d  | %-3d\n", playerDef, gob.getDEF());
			System.out.printf("ATK: %-3d  | %-3d\n", playerAtk, gob.getATK());
			System.out.printf("DMG: %-3d  | %-3d\n", playerDmg, gob.getDMG());
		} else if (monsterType == 2) {
			System.out.printf("Player    | Goblin Thief\n");
			System.out.printf("HP:  %-3d  | %-3d\n", playerHP, gobT.getHP());
			System.out.printf("DEF: %-3d  | %-3d\n", playerDef, gobT.getDEF());
			System.out.printf("ATK: %-3d  | %-3d\n", playerAtk, gobT.getATK());
			System.out.printf("DMG: %-3d  | %-3d\n", playerDmg, gobT.getDMG());
		} else if (monsterType == 3) {
			System.out.printf("Player    | Goblin Ranger\n");
			System.out.printf("HP:  %-3d  | %-3d\n", playerHP, gobR.getHP());
			System.out.printf("DEF: %-3d  | %-3d\n", playerDef, gobR.getDEF());
			System.out.printf("ATK: %-3d  | %-3d\n", playerAtk, gobR.getATK());
			System.out.printf("DMG: %-3d  | %-3d\n", playerDmg, gobR.getDMG());

		} else if (monsterType == 4) {
			System.out.printf("Player    | Goblin Mage\n");
			System.out.printf("HP:  %-3d  | %-3d\n", playerHP, gobM.getHP());
			System.out.printf("DEF: %-3d  | %-3d\n", playerDef, gobM.getDEF());
			System.out.printf("ATK: %-3d  | %-3d\n", playerAtk, gobM.getATK());
			System.out.printf("DMG: %-3d  | %-3d\n", playerDmg, gobM.getDMG());

		}
	}

	private void newMonster(int monsterType) {
		if (monsterType == 1) {
			System.out.println("Attempted reset");
			gob.resetMonster();
		} else if (monsterType == 2) {
			gobT.resetMonster();
		} else if (monsterType == 3) {
			gobR.resetMonster();
		} else if (monsterType == 4) {
			gobM.resetMonster();
		}
	}
}