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
        // TODO Hakikisha unapata user role na context
//        DatabaseHelper studentDatabase = new DatabaseHelper(this);
//        if (studentDatabase.authenticateUser(username, password)) {
//            return success;
//        }
        return null;
    }

    @Nullable
    Integer getError() {
        return error;
    }

}
