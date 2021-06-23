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
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.exception.StepFailedException
import internal.GlobalVariable as GlobalVariable

"Open Browser and navigate to Home Page"
WebUI.openBrowser(GlobalVariable.G_SiteURL)

"Maximize Window"
WebUI.maximizeWindow()

"Click on 'Contact us' in Main Menu"
WebUI.click(findTestObject('Object Repository/Page_Home/btn_Contact Us'), FailureHandling.STOP_ON_FAILURE)

"Verify navigation to 'Custom Service - Contact Us' Page"
WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Page_Customer Service - Contact Us/txa_title_Customer Service - Contact Us')).toLowerCase(), "customer service - contact us", false, FailureHandling.STOP_ON_FAILURE)

"Select 'Customer Service' in Subject Heading dropdown list "
WebUI.selectOptionByLabel(findTestObject('Object Repository/Page_Customer Service - Contact Us/cbo_Subject Heading'), "Customer service", false, FailureHandling.CONTINUE_ON_FAILURE)

"Input a valid email address into Email field"
WebUI.setText(findTestObject('Object Repository/Page_Customer Service - Contact Us/txt_Email Address'), EmailRegistered, FailureHandling.CONTINUE_ON_FAILURE)

"Input a random value into Order Reference field"
WebUI.setText(findTestObject('Object Repository/Page_Customer Service - Contact Us/txt_Order Reference'), "Random data", FailureHandling.CONTINUE_ON_FAILURE)

"Get file Path of image"
def filePath = RunConfiguration.getProjectDir() + '/KMS-Logo.png'

"Upload an image into Attach File"
//WebUI.uploadFile(findTestObject('Object Repository/Page_Customer Service - Contact Us/img_Upload'), filePath, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.sendKeys(findTestObject('Object Repository/Page_Customer Service - Contact Us/img_Upload'), filePath, FailureHandling.STOP_ON_FAILURE)

"Input value into Message area"
WebUI.setText(findTestObject('Object Repository/Page_Customer Service - Contact Us/txa_Message'), "Random data", FailureHandling.CONTINUE_ON_FAILURE)

"Click on 'Send' button"
WebUI.click(findTestObject('Object Repository/Page_Customer Service - Contact Us/btn_Send'), FailureHandling.STOP_ON_FAILURE)

"Verify arlert"
WebUI.verifyMatch(WebUI.getText(findTestObject('Page_Customer Service - Contact Us/msg_Alert')).toLowerCase(), "your message has been successfully sent to our team.", false, FailureHandling.STOP_ON_FAILURE)

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