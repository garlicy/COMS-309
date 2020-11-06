package com.example.cardgames;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import com.example.cardgames.StartScreens.SignUp;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class SignUpTest {


    @Test
    public void TestFragment(){
        SignUp test = mock(SignUp.class);

        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                System.out.println("called with arguments: " + Arrays.toString(args));

                return null;
            }
        }).when(test).sendID(anyInt());

        assertThat(test.validInformation(), is(false));

    }
}