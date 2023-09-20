package DemoqaApp.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

//компоненты используют для описания отдельного блока функционала
//например опишем блок выбора даты

public class CalendarComponent {
    private SelenideElement dateInput = $("#dateOfBirthInput");

    public void setName(String day, String year, String month) {
        dateInput.click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month)").click();
    }
}
