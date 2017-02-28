package com.beecow.mobile.Cupid;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

import static com.beecow.component.Constant.URL;

/**
 * Created by Hoang Nguyen on 2/28/2017.
 */
public class CheatTestLink {
    public static void main(String[] args) throws TestLinkAPIException {
        CheatTestLink testLink = new CheatTestLink();
//        testLink.testLinkAndroid();
        testLink.testLinkIOS();

    }

    public void testLinkAndroid() throws TestLinkAPIException {
        for (int i = 1; i < 36; i++) {
            if(i==31 || i == 32 || i==34){
                continue;
            }
            System.out.println("Testcase: " + i + " by Hoang Nguyen");
            TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient("19d7f163ac7cbf5ffa3fd959149ad3a8", URL);
            testlinkAPIClient.reportTestCaseResult("IOS_DAT", "Test_Plan_Cupid", "IOS_DAT-" + i, "Sprint_03", null, TestLinkAPIResults.TEST_PASSED);
            System.out.println("End update result to Testlink");
        }
    }
    public void testLinkIOS() throws TestLinkAPIException {
        for (int i = 1; i < 23; i++) {
            if(i==31 || i == 32 || i==34){
                continue;
            }
            System.out.println("Testcase: " + i + " by Hoang Nguyen");
            TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient("19d7f163ac7cbf5ffa3fd959149ad3a8", URL);
            testlinkAPIClient.reportTestCaseResult("IOS_DAT", "Test_Plan_Cupid", "IOS_DAT-" + i, "Sprint_01", null, TestLinkAPIResults.TEST_PASSED);
            System.out.println("End update result to Testlink");
        }
    }
}
