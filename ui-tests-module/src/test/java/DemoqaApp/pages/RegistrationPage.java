package DemoqaApp.pages;

import DemoqaApp.pages.components.CalendarComponent;
import com.codeborne.selenide.SelenideElement;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage { //представляем страницу в виде обьекта
    //elements q locators
    private final String FORM_TITLE = "Student Registration Form";
    private SelenideElement
            formTitle = $(".practice-form-wrapper"),
            mainHeader = $(".practice-form-wrapper"),
            firstNameInput = $("[id=firstName]"), //явно видно что это id
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderRadiobaton = $("[id=genterWrapper]"),
            numberInput = $("#userNumber"),
            subjectsInput = $("#subjectsInput"),
            hobbiesWrapper = $("#hobbiesWrapper"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            dateOfBirthInputMouth = $(".react-datepicker__month-select"),
            dateOfBirthInputYear = $(".react-datepicker__year-select"),
            //dateOfBirthInputDay = $(".react-datepicker__day--025:not(.react-datepicker__day--outside-month)"),
            pictureSelect = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            selectState = $("#state"),
            selectSity = $("#city"),
            selectWrapper = $("#stateCity-wrapper"),
            submittingForm = $("#example-modal-sizes-title-lg"),
            selectSubmit = $("#submit"),
            resultsTable = $(".table-responsive");

    public CalendarComponent calendar = new CalendarComponent();

    //actions methods
    public void openPage() {
        open("https://demoqa.com/automation-practice-form");
        formTitle.shouldHave(text(FORM_TITLE));
        //mainHeader.shouldHave(text(MAIN_HEADER));
    }

    public void typeFirstName(String valueFirstName) {
        firstNameInput.setValue(valueFirstName);
    }

    public void typeLastName(String valueLastName) {
        lastNameInput.setValue(valueLastName);
    }

    public void typeEmail(String Email) {
        emailInput.setValue(Email);
    }

    public RegistrationPage selectGender(String gender) {
        genderRadiobaton
                .$(byText(gender)).click();                                     //выбор радиобатона - по тексту

        return this;
    }

    public void selectDateOfBirth(String month, String year, String day) {
        dateOfBirthInput.click();
        dateOfBirthInputMouth.selectOption(month);
        dateOfBirthInputYear.selectOption(year);

        SelenideElement dateOfBirthInputDay = $(".react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month)");
        dateOfBirthInputDay.click();
    }

    public void typeNumber(String value) {
        numberInput.setValue(value);
    }

    public void typeSubjects(String value) {
        subjectsInput.setValue(value).pressEnter();
    }

    public RegistrationPage selectHobbies(String value) {
        hobbiesWrapper.$(byText(value)).click();

        return this;
    }

    public void uploadPicture() {
        pictureSelect.uploadFile(new File("src/test/resources/img/cat.jpeg"));
    }

    public void typeAddress(String value) {
        addressInput.setValue(value).scrollTo();
    }

    public void selectStateSity(String state, String sity) {
        selectState.click();
        selectWrapper.$(byText(state)).click();
        selectSity.click();
        selectWrapper.$(byText(sity)).scrollTo().click();
    }

    public void selectSubmit() {
        selectSubmit.click();
    }

    public RegistrationPage checkResultsValue(String key, String value) {
        final String SUBMITTING_FORM = "Thanks for submitting the form";
        submittingForm.shouldHave(text(SUBMITTING_FORM));
        resultsTable.$(byText(key))
                .parent().shouldHave(text(value));                              //поднимаемся к родителю и ищем сверху по тексту.

        return this;
    }
}
