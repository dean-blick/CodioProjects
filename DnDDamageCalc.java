import java.lang.*;
import java.util.*;
import java.io.*;
import java.nio.*;
public class DnDDamageCalc{
    static int armorClass = 0;
    static int endRoll = 0;
    static String dCon = "";
    static int attackBonus = 0;
    static String aCon = "";
    static int rollOne = 0;
    static int rollTwo = 0;
    static int damage = 0;
    static int totalDamage = 0;
    static int conditionType = 0;
    static int attackStrength = 0;
    static boolean critical = false;
    static String firstInput = null;
    static String[] rolls = new String[2];
    static int finalAttacker = 0;
    public static void main(String[] args)throws Exception{
        Scanner reader;
        if(args.length > 0){
            File file = new File(args[0]);
            reader = new Scanner(file);
        }else{
            reader = new Scanner(System.in);
        }
            armorClass = Integer.parseInt(reader.nextLine());
            dCon = reader.nextLine();
            attackBonus = Integer.parseInt(reader.nextLine());
            aCon = reader.nextLine();
            rolls = reader.nextLine().split(" ");
            rollOne = Integer.parseInt(rolls[0]);
            rollTwo = Integer.parseInt(rolls[1]);
            damage = Integer.parseInt(reader.nextLine());
            conditionType = conditionResult(aCon, dCon);
            attackStrength = calculateAttackStrength(rollOne, rollTwo, attackBonus, conditionType);
            critical = attackCritical(rollOne, rollTwo, conditionType);
            totalDamage = damageDone(armorClass, attackStrength, critical, damage);
            printOutput(conditionType,attackStrength,armorClass,critical,totalDamage);
            
    }
    
    static int attackerConditionResult(String aCon){
        if(aCon.equals("blinded")){
            return -1;
        }else if(aCon.equals("enraged")){
            return 1;
        }else if(aCon.equals("invisible")){
            return 1;
        }else if(aCon.equals("poisoned")){
            return -1;
        }else if(aCon.equals("stunned")){
            return 0;
        }else{
            return 0;
        }
    }
    static int defenderConditionResult(String dCon){
        if(dCon.equals("blinded")){
            return 1;
        }else if(dCon.equals("enraged")){
            return 1;
        }else if(dCon.equals("invisible")){
            return -1;
        }else if(dCon.equals("poisoned")){
            return 0;
        }else if(dCon.equals("stunned")){
            return 1;
        }else{
            return 0;
        }
    }
    static int conditionResult(String aCon, String dCon){
        finalAttacker = attackerConditionResult(aCon) + defenderConditionResult(dCon);
        if(finalAttacker > 0){
            return 1;
        }else if(finalAttacker == 0){
            return 0;
        }else{
            return -1;
        }
    }
    static int calculateAttackStrength(int rollOne, int rollTwo, int attackBonus, int conditionType){
        
        
        if(conditionType == 1){
            if(rollOne > rollTwo){
                endRoll = rollOne;
            }else{
                endRoll = rollTwo;
            }
        }else if(conditionType == 0){
            endRoll = rollOne;
        }else if(conditionType == -1){
            if(rollOne < rollTwo){
                endRoll = rollOne;
            }else{
                endRoll = rollTwo;
            }
        }
        return endRoll + attackBonus;
    }
    static boolean attackCritical(int rollOne, int rollTwo, int conditionType){
        
        if(conditionType == 1){
            if(rollOne > rollTwo){
                endRoll = rollOne;
            }else{
                endRoll = rollTwo;
            }
        }else if(conditionType == 0){
            endRoll = rollOne;
        }else if(conditionType == -1){
            if(rollOne < rollTwo){
                endRoll = rollOne;
            }else{
                endRoll = rollTwo;
            }
        }
        if(endRoll == 20){
            return true;
        }else{
            return false;
        }
    }
    static int damageDone(int armorClass, int attackStrength, boolean critical, int damage){
        if(attackStrength > armorClass){
            if(critical){
                return damage*2;
            }else{
                return damage;
            }
        }else{
            return 0;
        }
    }
    static void printOutput(int conditionType, int attackStrength, int armorClass, boolean critical, int totalDamage){
        
        String attackTypeString = "";
        if(conditionType > 0){
            attackTypeString = "with advantage";
        }else if(conditionType == 0){
            attackTypeString = "normally";
        }else{
            attackTypeString = "with disadvantage";
        }
        System.out.println("The attacker attacks " + attackTypeString + ", attack strength " + attackStrength+".");
        System.out.println("The defender has armor class " + armorClass + ".");
        if(totalDamage <= 0){
            System.out.println("The attack is unsuccessful.");
        }else if(critical){
            System.out.println("The attack is critical, damage " + totalDamage+".");
        }else{
            System.out.println("The attack is successful, damage " + totalDamage+".");
        }
    }
}