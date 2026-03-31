package com.qacart.todo.pages;

import com.qacart.todo.base.BasePage;
import com.qacart.todo.config.EndPoint;
import com.qacart.todo.utils.ConfigUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TodoPage extends BasePage {

    public TodoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-testid=\"welcome\"]")
    private WebElement welcomeMessage;

    @FindBy(css = "[data-testid=\"add\"]")
    private WebElement addButton;

    @FindBy(css = "[data-testid=\"todo-item\"]")
    private WebElement todoItem;

    @FindBy(css = "[data-testid=\"delete\"]")
    private WebElement deleteButton;

    @FindBy(css = "[data-testid=\"no-todos\"]")
    private WebElement notodosMessage;

    public TodoPage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.TODO_PAGE_ENDPOINT);
        return this;
    }

    public boolean isWelcomeMessageDisplayed(){
        return welcomeMessage.isDisplayed();
    }

    public NewTodPage clickOnPlusButton(){
        addButton.click();

        return new NewTodPage(driver);
    }

    public String getTodoText() {
        return todoItem.getText();
    }

    public TodoPage clickOnDeleteButton(){
        deleteButton.click();

        return new TodoPage(driver);
    }

    public boolean isNoTodosMessageDisplayed() {
        return notodosMessage.isDisplayed();
    }
}
