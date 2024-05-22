package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private final int maxRetryCount = 2;

    @Override
    public boolean retry(ITestResult result) {

        System.out.printf("Retrying for count: %d %s%n", retryCount, result.getMethod().getDescription());
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}
