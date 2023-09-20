package DemoqaApp.tests;

import AcmeStoreApp.tests.BaseTest;
import DemoqaApp.pages.RegistrationPage;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static DemoqaApp.tests.BaseTest.*;

public class PracticeFormTests extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();

    @Owner("velichkovv")
    @Test(description = "test for fill PracticeForm")
    void fillPracticeForm() {

        registrationPage.openPage();
        //registrationPage.typeFirstName("Piter");
        registrationPage.typeFirstName(randomName);
        registrationPage.typeLastName("Parker");
        //registrationPage.typeEmail("PParker@gmail.com");
        registrationPage.typeEmail(RandomUtilsForTests.getRandomEmail());
        registrationPage
                .selectGender("Male")
                .selectGender("Other");
        //registrationPage.typeNumber("2224449991");
        registrationPage.typeNumber(randomTelephone);
        //registrationPage.selectDateOfBirth("November", "1991", "28"); //работает
        registrationPage.calendar.setName("25", "1991", "November"); //запуск через компонент календарь
        registrationPage.typeSubjects("hindi");
        registrationPage
                .selectHobbies("Reading")
                .selectHobbies("Music");
        registrationPage.uploadPicture();
        registrationPage.typeAddress("Lenina str 1");
        registrationPage.selectStateSity("Haryana", "Panipat");
        registrationPage.selectSubmit();
        registrationPage
                .checkResultsValue("Student Name", randomName + " Parker")
                .checkResultsValue("Student Email", RandomUtilsForTests.getRandomEmail())
                .checkResultsValue("Gender", "Other")
                .checkResultsValue("Mobile", randomTelephone)
                .checkResultsValue("Date of Birth", "25 November,1991")
                .checkResultsValue("Subjects", "Hindi")
                .checkResultsValue("Hobbies", "Reading, Music")
                .checkResultsValue("Picture", "cat.jpeg")
                .checkResultsValue("Address", "Lenina str 1")
                .checkResultsValue("State and City", "Haryana Panipat");
    }
}
