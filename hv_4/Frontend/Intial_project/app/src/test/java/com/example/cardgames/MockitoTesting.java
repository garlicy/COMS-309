package com.example.cardgames;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.cardgames.GameDesign.GameDesignInputReading;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MockitoTesting {


    @Test
    public void testChecksPositive()
    {
        String testText1 = "tested";
        String testText2 = "bested";
        String testText3 = "too Tired to think of a rhyme";

        RadioGroup mockedRadios = mock(RadioGroup.class);

        RadioButton mock1 = mock(RadioButton.class);
        RadioButton mock2 = mock(RadioButton.class);
        RadioButton mock3 = mock(RadioButton.class);

        when(mockedRadios.getChildCount()).thenReturn(3);

        when(mockedRadios.getChildAt(0)).thenReturn(mock1);
        when(mockedRadios.getChildAt(1)).thenReturn(mock2);
        when(mockedRadios.getChildAt(2)).thenReturn(mock3);

        when(mock1.getText()).thenReturn(testText1);
        when(mock2.getText()).thenReturn(testText2);
        when(mock3.getText()).thenReturn(testText3);

        Assert.assertSame(GameDesignInputReading.getRadioToCheck(testText2, mockedRadios), mock2);

    }

    @Test
    public void testChecksNegative()
    {
        String testText1 = "tested";
        String testText2 = "bested";
        String testText3 = "too Tired to think of a rhyme";
        String testText4 = "not in there";

        RadioGroup mockedRadios = mock(RadioGroup.class);

        RadioButton mock1 = mock(RadioButton.class);
        RadioButton mock2 = mock(RadioButton.class);
        RadioButton mock3 = mock(RadioButton.class);

        when(mockedRadios.getChildCount()).thenReturn(3);

        when(mockedRadios.getChildAt(0)).thenReturn(mock1);
        when(mockedRadios.getChildAt(1)).thenReturn(mock2);
        when(mockedRadios.getChildAt(2)).thenReturn(mock3);

        when(mock1.getText()).thenReturn(testText1);
        when(mock2.getText()).thenReturn(testText2);
        when(mock3.getText()).thenReturn(testText3);

        Assert.assertSame(GameDesignInputReading.getRadioToCheck(testText4, mockedRadios), null);

    }
}
