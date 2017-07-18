package com.mmk.customlisteners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.mmk.reader.LogWriter;

public class TestNGListeners implements ITestListener	
{

	@Override
	public void onTestStart(ITestResult result) {
		LogWriter.logger.info("--- Method Execution Started --- : "+result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		LogWriter.logger.info("--- Method Execution Finished --- : "+result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		 
		LogWriter.logger.info("-- Test is skipped: ---" + result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) 
	{
		LogWriter.logger.info("--- Test Started --- : "+context.getName());
		
	}

	@Override
	public void onFinish(ITestContext context) 
	{
		LogWriter.logger.info("--- Test Finished --- : "+context.getName());
		
	}

}
