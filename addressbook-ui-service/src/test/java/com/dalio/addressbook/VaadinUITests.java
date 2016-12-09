package com.dalio.addressbook;

import static org.assertj.core.api.BDDAssertions.then;

import javax.annotation.PostConstruct;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.boot.VaadinAutoConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VaadinUITests.Config.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class VaadinUITests {

    @Autowired CustomersClient repository;
    VaadinRequest vaadinRequest = Mockito.mock(VaadinRequest.class);
    CustomerEditor editor;
    VaadinUI vaadinUI;

    @Before
    public void setup() {
        this.editor = new CustomerEditor();
        this.vaadinUI = new VaadinUI(editor);
    }

    @Test
    public void shouldInitializeTheGridWithCustomerRepositoryData() {
        int customerCount = (int) this.repository.count();

        vaadinUI.init(this.vaadinRequest);

        then(vaadinUI.grid.getColumns()).hasSize(3);
        then(vaadinUI.grid.getContainerDataSource().getItemIds()).hasSize(customerCount);
    }

    @Test
    public void shouldFillOutTheGridWithNewData() {
        int initialCustomerCount = (int) this.repository.count();
        this.vaadinUI.init(this.vaadinRequest);
        customerDataWasFilled(editor, "Dalibor", "Ninkovic");

        this.editor.save.click();

        then(vaadinUI.grid.getContainerDataSource().getItemIds()).hasSize(initialCustomerCount + 1);
        then((Customer) vaadinUI.grid.getContainerDataSource().lastItemId())
                .extracting("firstName", "lastName")
                .containsExactly("Dalibor", "Ninkovic");
    }

    @Test
    public void shouldFilterOutTheGridWithTheProvidedLastName() {
        this.vaadinUI.init(this.vaadinRequest);
        this.repository.save(new Customer("Josh", "Long","",""));

        vaadinUI.listCustomers("Long");

        then(vaadinUI.grid.getContainerDataSource().getItemIds()).hasSize(1);
        then((Customer) vaadinUI.grid.getContainerDataSource().lastItemId())
                .extracting("firstName", "lastName")
                .containsExactly("Josh", "Long");
    }

    @Test
    public void shouldInitializeWithInvisibleEditor() {
        this.vaadinUI.init(this.vaadinRequest);

        then(this.editor.isVisible()).isFalse();
    }

    @Test
    public void shouldMakeEditorVisible() {
        this.vaadinUI.init(this.vaadinRequest);
        Object itemId = this.vaadinUI.grid.getContainerDataSource().getItemIds().iterator().next();

        this.vaadinUI.grid.select(itemId);

        then(this.editor.isVisible()).isTrue();
    }

    private void customerDataWasFilled(CustomerEditor editor, String firstName, String lastName) {
        this.editor.firstName.setValue(firstName);
        this.editor.lastName.setValue(lastName);
        editor.editCustomer(new Customer(firstName, lastName,"",""));
    }

    @Configuration
    @EnableAutoConfiguration(exclude = VaadinAutoConfiguration.class)
    static class Config {

        @Autowired CustomersClient repository;

        @PostConstruct
        public void initializeData() {
            this.repository.save(new Customer("Jack", "Bauer","1213",""));
            this.repository.save(new Customer("Chloe", "O'Brian","341324124312",""));
            this.repository.save(new Customer("Kim", "Bauer","41241241212",""));
            this.repository.save(new Customer("David", "Palmer","231412342134",""));
            this.repository.save(new Customer("Michelle", "Dessler","2412412412411",""));
        }

    }

}
