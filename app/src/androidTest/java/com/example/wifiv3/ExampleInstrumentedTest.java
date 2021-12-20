package com.example.wifiv3;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.wifiv3.data.langOptions;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    langOptions bruh = new langOptions(InstrumentationRegistry.getInstrumentation().getTargetContext());

    @Test
    public void changingLang() throws IOException {
        bruh.setLang("ru");

        assertEquals(bruh.getLang(), "ru");
    }
}