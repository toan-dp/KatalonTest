import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.TearDown as TearDown
import com.kms.katalon.core.annotation.TearDownIfFailed as TearDownIfFailed
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

'Call test case to navigate to \'Authentication\' Page'
WebUI.callTestCase(findTestCase('Common Test Cases/Navigate to Authentication Page'), null, FailureHandling.STOP_ON_FAILURE)

'Navigate to \'Forgot your password\' Page'
WebUI.click(findTestObject('Object Repository/Page_Authentication/lnk_Forgot Your Password'))

'Verify was redirected to \'Forgot your password\' Page '
WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Page_Forgot Your Password/txa_title_Forgot Your Password')).toLowerCase(), 
    'forgot your password?', false, FailureHandling.STOP_ON_FAILURE)

'Click button \'Retrieve Password\' with no data'
WebUI.click(findTestObject('Object Repository/Page_Forgot Your Password/btn_Retrieve Password'), FailureHandling.STOP_ON_FAILURE)

'Verify alert'
WebUI.verifyMatch(WebUI.getText(findTestObject('Page_Forgot Your Password/msg_Alert')).toLowerCase(), 'there is 1 error\ninvalid email address.', 
    false, FailureHandling.STOP_ON_FAILURE)

'Input an valid email which was not registered before'
WebUI.setText(findTestObject('Object Repository/Page_Forgot Your Password/txt_Email Address'), EmailNotRegistered)

'Click button \'Retrieve Password\' with Email was not registered'
WebUI.click(findTestObject('Object Repository/Page_Forgot Your Password/btn_Retrieve Password'))

'Verify alert'
WebUI.verifyMatch(WebUI.getText(findTestObject('Page_Forgot Your Password/msg_Alert')).toLowerCase(), 'there is 1 error\nthere is no account registered for this email address.', 
    false, FailureHandling.STOP_ON_FAILURE)

'Input an invalid email address'
WebUI.setText(findTestObject('Object Repository/Page_Forgot Your Password/txt_Email Address'), InvalidEmail)

'Click button \'Retrieve Password\' with invalid email'
WebUI.click(findTestObject('Object Repository/Page_Forgot Your Password/btn_Retrieve Password'))

'Verify alert'
WebUI.verifyMatch(WebUI.getText(findTestObject('Page_Forgot Your Password/msg_Alert')).toLowerCase(), 'there is 1 error\ninvalid email address.', 
    false, FailureHandling.STOP_ON_FAILURE)

'Input an email was registered'
WebUI.setText(findTestObject('Object Repository/Page_Forgot Your Password/txt_Email Address'), EmailRegistered)

'Click button Retrieve Password with an email wasn\'t registered'
WebUI.click(findTestObject('Object Repository/Page_Forgot Your Password/btn_Retrieve Password'))

'Verify alert'
WebUI.verifyMatch(WebUI.getText(findTestObject('Page_Forgot Your Password/msg_Alert')).toLowerCase(), 'a confirmation email has been sent to your address: ' + 
    EmailRegistered, false, FailureHandling.STOP_ON_FAILURE)

