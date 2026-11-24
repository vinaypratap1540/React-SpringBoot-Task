package com.esd.moviebooking;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class AssessmentTestWatcher implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.err.println("Assessment test failed: " + context.getDisplayName());
        if (cause != null) {
            System.err.println("Reason: " + cause.getMessage());
        }
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println("Assessment test passed: " + context.getDisplayName());
    }
}
