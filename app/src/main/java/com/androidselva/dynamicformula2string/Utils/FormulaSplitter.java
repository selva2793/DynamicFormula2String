package com.androidselva.dynamicformula2string.Utils;

import android.content.Context;
import android.util.Log;

import org.mariuszgromada.math.mxparser.Constant;
import org.mariuszgromada.math.mxparser.Expression;
import java.util.ArrayList;
import java.util.List;

public class FormulaSplitter {
    String formula1answer = "";
    List<Constant> combinedConstants;
    List<String> valuesList;
    Expression e = null;
    String strMainFormula = "";
    String shortformula = "";
    String mainformula = "";
    String finalcalculatedvalue = "0";
    Context context;

    public String main(String formula, String splittertext,List<String> values, Context cont) {
        this.context = cont;
        strMainFormula = formula;
        valuesList = values;

        String[] arrSplit = strMainFormula.split(splittertext);
//        Log.d("arrSplit", "main: "+arrSplit.length +" arrSplit "+arrSplit +" splittertext "+splittertext);
        List<String> filteredargumentVariables = new ArrayList<>();
        for (int i = 0; i < arrSplit.length; i++) {
            Log.d("itearazuyitm", "main: " + i);
            if (i == arrSplit.length - 1) {//
                Log.d("lensize44", "onClick: " + arrSplit[i]);
                mainformula = arrSplit[i];
            } else if (i == arrSplit.length - 2) {
                Log.d("lensize4463", "onClick: " + arrSplit[i]);
                shortformula = arrSplit[i];
            } else {
                Log.d("lensize445", "onClick: " + arrSplit[i]);
                filteredargumentVariables.add(arrSplit[i]);
            }
            Log.d("lensize", "onClick: " + arrSplit[i]);
        }


//       argumentVAriables(filteredargumentVariables);

        return calculatevalue(argumentVAriables(filteredargumentVariables));
    }

    private List<Constant> argumentVAriables( List<String> filteredargumentVariables ){
        List<String> Filterdvariables = new ArrayList<String>();

        for (int i =0; i<filteredargumentVariables.size();i++){
            if (i < valuesList.size()){
                Filterdvariables.add(filteredargumentVariables.get(i)+ " = "+valuesList.get(i));
            }
            else{
                if (!filteredargumentVariables.get(i).equalsIgnoreCase("score"))
                    Filterdvariables.add(filteredargumentVariables.get(i)+ " = 0");
            }
        }

        List<Constant> formulaConstantVariables = new ArrayList<Constant>();
        Log.d("jfgndfjkghdfgh", "loadConstants: "+Filterdvariables +   "      "+valuesList);
        for (String a : Filterdvariables) {
            Constant c1 = new Constant(a);
            formulaConstantVariables.add(c1);
        }

        return formulaConstantVariables;
    }

    private String calculatevalue(List<Constant> formulaVaria) {
        combinedConstants = new ArrayList<Constant>();
        combinedConstants.addAll(formulaVaria);

//////////////////////*****************************************************************************/////////////////Calcualtion Happens here//////////////////////////*****************************************************************************
        e = new Expression(shortformula);
        e.addConstants(combinedConstants);
        e.calculate();

        formula1answer = String.valueOf(e.calculate());
        String scorevalue = "score = " + formula1answer;
        Constant c1 = new Constant(scorevalue);
        combinedConstants.add(c1);
        Log.d("klfglo", "calculatevalue: "+c1.toString()+"     "+shortformula);
        Expression e1 = new Expression(mainformula);
        e1.addConstants(combinedConstants);
        e1.calculate();
        finalcalculatedvalue = String.valueOf(e1.calculate());
        Log.d("calcualdtevaoik", "onClick: " + "     " + finalcalculatedvalue + "   " + formula1answer+"       "+scorevalue);
        if (finalcalculatedvalue.isEmpty() || finalcalculatedvalue.equals("NaN") || finalcalculatedvalue.equals(null)){
            return "0";
        }
        else{
            Float value = Float.valueOf(finalcalculatedvalue);
            int fval = Math.round(value);      ////   round of function
            return String.valueOf(fval);
        }
    }
}
