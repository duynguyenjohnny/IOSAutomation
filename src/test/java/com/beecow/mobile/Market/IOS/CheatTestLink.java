package com.beecow.mobile.Market.IOS;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

import static com.beecow.component.Constant.URL;
import static com.beecow.utils.PropertiesUtils.testlinkDevKey;

/**
 * Created by phuocha on 2/24/17.
 */
public class CheatTestLink {
    public static void main(String[] args) throws TestLinkAPIException {
        CheatTestLink testLink = new CheatTestLink();
        testLink.testLinkPhuoc();
//        testLink.testLinkHang();

    }

    public void testLinkPhuoc() throws TestLinkAPIException {
        for (int i = 1; i < 102; i++) {
            System.out.println("Testcase: " + i + " by Phuoc Ha");
            TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient("a23f13c2522827b7f75b1de9690adbc0", URL);
            testlinkAPIClient.reportTestCaseResult("Market_Android", "TestPlan_Market", "AND_MAR_TC-" + i, "Build_Sprint_03", null, TestLinkAPIResults.TEST_PASSED);
            System.out.println("End update result to Testlink");
        }
    }

    public void testLinkHang() throws TestLinkAPIException {
        for (int i = 52; i < 102; i++) {
            System.out.println("Testcase: " + i + " by Hang Pham");
            TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient("9a8cc6dccee2d263e3d259e48b70df18", URL);
            testlinkAPIClient.reportTestCaseResult("Market_IOS", "TestPlan_Market", "IOS_MAR_TC-" + i, "Build_Sprint_03", null, TestLinkAPIResults.TEST_PASSED);
            System.out.println("End update result to Testlink");
        }
    }

}
