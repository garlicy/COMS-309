package com.example.cardgames;

import static org.mockito.Mockito.mock;

import com.example.cardgames.GameDiscovery.Category;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class CategoryTest {

    private Category test = mock(Category.class);
    @Test
    public void Create_Numerous_Games() {
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                System.out.println("called with arguments: " + Arrays.toString(args));
                int i = 0;
                while(i < 1000) {
                    test.CreateGame(R.drawable.poker_icon, "Poker", "Hazlnuts");
                    i++;
                }
                return null;
            }
        }).when(test).CreateGame(R.drawable.poker_icon, "Poker", "Hazlnuts");
    }
}