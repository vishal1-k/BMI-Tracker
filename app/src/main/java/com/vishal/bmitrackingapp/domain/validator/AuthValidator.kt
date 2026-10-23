package com.vishal.bmitrackingapp.domain.validator

class AuthValidator{
    fun validateEmail(email: String): String?{
        if(email.isBlank()){
            return "Email is required"
        }
        if( !android.util.Patterns.EMAIL_ADDRESS
                .matcher(email)
                .matches()

            ){
            return "Enter a valid email"
        }
        return null
    }

    fun validatePassword(password: String): String?{
        if(password.isBlank()){
            return "Password is required"
        }
        if(password.length<6){
            return "Password must containt al least characters"
        }

        return null
    }

    fun validateConfirmPassword(
        password: String,
        confirmPassword: String
    ):String?{

        if(confirmPassword.isBlank()){
            return "Please confirm your password"
        }

        if(password != confirmPassword){
            return "Password do not match"
        }
        return null

    }
}