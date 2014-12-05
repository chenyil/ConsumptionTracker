package uvic.csc.chenyil.consumptiontracker.database;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by imacadmin on 2014-10-27.
 */
public class AppData {

    ArrayList<Profile> profiles = new ArrayList<Profile>();
    ArrayList<Food> foods = new ArrayList<Food>();
    ArrayList<Nutrient> nutrients = new ArrayList<Nutrient>();

    ArrayList<LimitsGoals> limitsGoals = new ArrayList<LimitsGoals>();
    ArrayList<RelationFoodNutrients> relationFoodNutrientses = new ArrayList<RelationFoodNutrients>();
    ArrayList<RelationProfileFood> relationProfileFoods = new ArrayList<RelationProfileFood>();

    public void initialize(){
        addProfile();
        addNutrients();
        //add food
        int fid=addFood("Pepsi","ml",355,710,"Drink");
        addRelationFN(0,fid,150);
        addRelationFN(5,fid,15);
        addRelationFN(6,fid,42);
        addRelationFN(8,fid,41);

        fid = addFood("Dempster's Whole wheat bread","grams",71,710,"Grains");
        addRelationFN(0,fid,170);
        addRelationFN(1,fid,2);
        addRelationFN(2,fid,Float.parseFloat("0.4"));
        addRelationFN(5,fid,350);
        addRelationFN(6,fid,32);
        addRelationFN(7,fid,4);
        addRelationFN(8,fid,2);
        addRelationFN(9,fid,7);
        addRelationFN(12,fid,Float.parseFloat("0.04"));
        addRelationFN(13,fid,Float.parseFloat("0.1"));
        
    }
    public void addProfile(){
        Profile profile=new Profile();
        profile.setId(1);
        profile.setWeight(150);
        profile.setHeight(180);
        profile.setGender("Male");
        profile.setName("Dad");
        profiles.add(profile);
    }

    public void addNutrients(){
        List<String> nutrientsName= Arrays.asList(
                "Calories",//0
                "Fat", //1
                "Saturated Fat",//2
                "Trans Fat",//3
                "Cholesterol",//4
                "Sodium",//5
                "Carbon",//6
                "Fibre",//7
                "Sugar",//8
                "Protein",//9
                "Vitamin A",//10
                "Vitamin C",//11
                "Calcium",//12
                "Iron");//13
        for (int i=0;i<nutrientsName.size();i++){
            Nutrient nutrient= new Nutrient(i,nutrientsName.get(i),"mg");
            nutrients.add(nutrient);
            Log.d("Info",nutrients.get(i).toString());
        }
    }

    public int addFood(String name, String unit, int servingSize, int packageSize,String category){
        int id=foods.size();
        Food food = new Food(id,name,unit,servingSize,packageSize,category);
        foods.add(food);
        return id;
    }

    public void addRelationFN(long nid,long fid,float amount){
        int id=relationFoodNutrientses.size();
        RelationFoodNutrients relation = new RelationFoodNutrients(id,nid,fid,amount);
        relationFoodNutrientses.add(relation);
    }

    public void addRelationPF(long pid,long fid,float amount,Date purchaseTime,Date finishTime){
        int id=relationProfileFoods.size();
        RelationProfileFood relation = new RelationProfileFood(id,pid,fid,amount,purchaseTime,finishTime);
        relationProfileFoods.add(relation);
    }

    public void addLG(long pid, long nid, float amount, String type){
        int id = limitsGoals.size();
        LimitsGoals lg=new LimitsGoals(id,pid,nid,amount,type);
        limitsGoals.add(lg);
    }


}
