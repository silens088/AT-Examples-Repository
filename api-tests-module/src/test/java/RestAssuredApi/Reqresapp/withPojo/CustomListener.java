package RestAssuredApi.Reqresapp.withPojo;

import org.testng.ITestListener;
import org.testng.ITestResult;


/**
 * @Listeners Слушатели в TestNG предоставляют возможность вмешиваться в жизненный цикл тестов, что позволяет выполнять определенные действия в определенные моменты жизненного цикла теста.
 * <p>
 * Пример:
 * Перед запуском любого метода с аннотацией @Test.
 * После успешного завершения метода с аннотацией @Test.
 * Если тест завершился неудачей или был пропущен.
 * <p>
 * Вы можете добавлять дополнительные действия или обработку, например:
 * Логирование каждого запуска теста.
 * Создание скриншотов при неудачном выполнении теста.
 * Изменение поведения тестов на основе определенных условий.
 */


public class CustomListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test " + result.getName() + " --------------------- started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test " + result.getName() + " --------------------- passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test " + result.getName() + " --------------------- failed");
    }

    // ... другие методы можно реализовать по необходимости
}