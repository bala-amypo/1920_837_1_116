package com.example.demo.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestResultListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        // required by test framework
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // required by test framework
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // required by test framework
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // required by test framework
    }

    @Override
    public void onStart(ITestContext context) {
        // required by test framework
    }

    @Override
    public void onFinish(ITestContext context) {
        // required by test framework
    }
}
