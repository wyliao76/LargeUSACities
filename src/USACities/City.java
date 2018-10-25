package USACities;

import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class City {
    private String name ;
    private float population ;
    private float medianIncome ;
    private float percentLocal ;
    private float advancedDegrees;

public City (){
        name ="";
        population = 0;
        medianIncome = 0;
        percentLocal = 0;
        advancedDegrees =0;
}
public City (String name, float population, float medianIncome, float percentLocal, float advancedDegrees ){
    this.name= name ;
    this.population = population ;
    this.medianIncome = medianIncome;
    this.percentLocal = percentLocal ;
    this.advancedDegrees = advancedDegrees ;
}
public City ( City anotherCity){
    name = anotherCity.name ;
    population = anotherCity.population;
    medianIncome = anotherCity.medianIncome;
    percentLocal = anotherCity.percentLocal;
    advancedDegrees = anotherCity.advancedDegrees;
}
    public String getName() {
        return name;
    }

    public void setName(String city) {
        this.name = city;
    }

    public float  getPopulation() {
        return population;
    }

    public void setPopulation(float population) {
        this.population = population;
    }

    public float  getMedianIncome() {
        return medianIncome;
    }

    public void setMedianIncome(float medianIncome) {
        this.medianIncome = medianIncome;
    }

    public float  getPercentLocal() {
        return percentLocal;
    }

    public void setPercentLocal(float percentLocal) {
        this.percentLocal = percentLocal;
    }

    public float  getAdvancedDegrees() {
        return advancedDegrees;
    }

    public void setAdvancedDegrees(float advancedDegrees) {
        this.advancedDegrees = advancedDegrees;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    
    public boolean equals(City city) {
        if (this.getName().equalsIgnoreCase(city.getName()) && closeEnough(this.getPopulation(), city.getPopulation()))
            return true;
        else 
             return false;
    }
 private boolean closeEnough(float x , float y){
     final double EOPSILON = 1E-9;
     return (Math.abs(x-y)< EOPSILON);
         
 }
    @Override
    public String toString() {
        return "City{" + "name=" + name + ", population=" + population + ", medianIncome=" + medianIncome + ", percentLocal=" + percentLocal + ", advancedDegrees=" + advancedDegrees + '}';
    }

   

}

