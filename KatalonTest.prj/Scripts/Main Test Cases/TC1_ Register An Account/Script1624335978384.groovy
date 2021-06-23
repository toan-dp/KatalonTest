import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.TearDown
import com.kms.katalon.core.annotation.TearDownIfFailed
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

"Story: Register An Account"

"Given that the user has the valid login information"

"Call testcase to navigate to 'Authentication' Page"
WebUI.callTestCase(findTestCase('Common Test Cases/Navigate to Authentication Page'), null, FailureHandling.STOP_ON_FAILURE)

"Input email to create an account"
WebUI.setText(findTestObject('Object Repository/Page_Authentication/txt_Email Address'), EmailNotRegistered, FailureHandling.STOP_ON_FAILURE)

"Click on 'Create an account' button"
WebUI.click(findTestObject('Object Repository/Page_Authentication/btn_Create An Account'), FailureHandling.STOP_ON_FAILURE)

"Verify navigation to 'Create An Account' Page "
WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Page_Create An Account/txa_title_Create An Account')).toLowerCase(), "create an account", false, FailureHandling.STOP_ON_FAILURE)

"Verify an email was input in step before"
WebUI.verifyMatch(WebUI.getAttribute(findTestObject('Object Repository/Page_Create An Account/txt_Email Address'), 'value'), EmailNotRegistered, false, FailureHandling.STOP_ON_FAILURE)


@TearDown(skipped = true)
def tearDown() {
	//WebUI.takeScreenshot()
	WebUI.closeBrowser()
}

@TearDownIfFailed(skipped = true)
def tearDownIfFailed() {
	//WebUI.takeScreenshot()
	WebUI.closeBrowser()
}	