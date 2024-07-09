package com.castle.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.Test;

public class ExtentReportsDemo {

    @Test
    public void extentTest(){
        ExtentReports reports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("index.html");
        reports.attachReporter(sparkReporter);
        reports.createTest("MyFirstTest")
                .log(Status.PASS,"This is logging event for test, and it passed.");


        reports.flush();
    }
}
