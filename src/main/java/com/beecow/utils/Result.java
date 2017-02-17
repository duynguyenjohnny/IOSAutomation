package com.beecow.utils;

import org.testng.ITestResult;
import org.testng.Reporter;

import static com.beecow.model.CommonElement.passed;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by HangPham on 12/18/2016.
 */
public class Result {
    private String result;
    private String observation;
    private String expectation;
    private String fileReport;


    private ArrayList<String> sResult = new ArrayList<String>();
    private ArrayList<String> sObservation = new ArrayList<String>();


    public Result() {
        this.result = passed;
        this.observation = "";
        this.expectation = "";
    }

    //data setter method
    public void setResult(String result) {
        this.result = result;
//        sResult.add(result);
    }

    public void setObservation(String observation) {
        this.observation = observation;
//        sObservation.add(observation);
    }

    public void setExpectation(String expectation) {
        this.expectation = expectation;
    }

    //data getter method
    public String getResult() {
        return result;
    }

    private String getObservation() {
        return observation;
    }

    private String getExpectation() {
        return expectation;
    }

    public void addLog(String text) {
        Reporter.log(text + "</br>", true);
    }

//    public void check() {
//        String getResult = getResult();
//        if (getResult.equalsIgnoreCase(passed)) {
//            addLog("Observation: " + observation);
//            addLog("Result: " + ANSI_GREEN + result + ANSI_RESET);
//        } else {
//            for (int i = 0; i < sResult.size(); i++) {
//                addLog("Observation: " + sObservation.get(i));
//                addLog("Expectation: " + ANSI_RED + sResult.get(i) + ANSI_RESET);
//            }
//            addLog("Expectation: " + getExpectation());
//        }
////        Assert.assertEquals(getResult, passed);
//    }


    public void check(){
        if(getResult().equals(passed)){
            addLog("Observation: " + getExpectation());
            addLog("Result: " + getResult());
        }
        else {
            addLog("Observation: " + getObservation());
            addLog("Expectation: " + getExpectation());
            addLog("Result: " + getResult());
        }
    }

    public void stringToFile(String logmsg, String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write("==========" + getExpectation() + "==========");
            bw.newLine();
            bw.write(logmsg);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println("error: " + e);
            e.printStackTrace();

        }
    }

    public void reportResult() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        //get current date time with Date
        Date date = new Date();
        fileReport = "Report//Error" + dateFormat.format(date).toString() + ".txt";
        stringToFile(getObservation(), fileReport);
    }

    public static void Fail(String kwName, String Message) throws Exception{
        try{
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy HH:mm:ss");
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            throw new Exception("[" + ft.format(dNow) + "][" + kwName + "] " + Message);
        }catch (ExecutionException ex){

        }
    }
}
