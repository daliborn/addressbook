package com.dalio.addressbook;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.dalio.addressbook.Customer;
import com.dalio.addressbook.CustomerEditor;
import com.dalio.addressbook.CustomerRepository;

import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class CustomerEditorTests {

    private static final String FIRST_NAME = "Dalibor";
    private static final String LAST_NAME = "Ninkovic";
    private static final String PHONE_NUMBER = "+381 65 5606943";
    private static final String EMAIL = "daliborn@gmail.com";
    

    @Mock CustomerRepository customerRepository;
    @InjectMocks CustomerEditor editor;

    @Test
    public void shouldStoreCustomerInRepoWhenEditorSaveClicked() {
        this.editor.firstName.setValue(FIRST_NAME);
        this.editor.lastName.setValue(LAST_NAME);
        customerDataWasFilled();

        this.editor.save.click();

        then(this.customerRepository).should().save(argThat(customerMatchesEditorFields()));
    }

    @Test
    public void shouldDeleteCustomerFromRepoWhenEditorDeleteClicked() {
        this.editor.firstName.setValue(FIRST_NAME);
        this.editor.lastName.setValue(LAST_NAME);
        this.editor.lastName.setValue(LAST_NAME);
        customerDataWasFilled();

        editor.delete.click();

        then(this.customerRepository).should().delete(argThat(customerMatchesEditorFields()));
    }

    private void customerDataWasFilled() {
        this.editor.editCustomer(new Customer(FIRST_NAME, LAST_NAME,PHONE_NUMBER,EMAIL));
    }

    private TypeSafeMatcher<Customer> customerMatchesEditorFields() {
        return new TypeSafeMatcher<Customer>() {
            @Override public void describeTo(Description description) {

            }

            @Override protected boolean matchesSafely(Customer item) {
                return FIRST_NAME.equals(item.getFirstName()) &&
                        LAST_NAME.equals(item.getLastName());
            }
        };
    }

}
