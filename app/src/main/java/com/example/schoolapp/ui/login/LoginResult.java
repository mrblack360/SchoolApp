package com.example.schoolapp.ui.login;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
class LoginResult {
    @Nullable
    private LoggedInUserView success;
    @Nullable
    private Integer error;

    LoginResult(@Nullable Integer error) {
        this.error = error;
    }

    LoginResult(@Nullable LoggedInUserView success) {
        this.success = success;
    }

    @Nullable
    LoggedInUserView getSuccess(String username, String password) {
        if (username == "AD000" && password == "admiN0") {
            return success;
        }
        return null;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}
