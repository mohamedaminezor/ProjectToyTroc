package com.mobelite.toytroc.SignUp

import android.content.Context
import android.widget.*
import com.mobelite.toytroc.R

class SignUpPresenter(private val view: SignUpContract.View, private val context: Context) : SignUpContract.Presenter {
    fun isAllLetters(inputString: String): Boolean {
        for (char in inputString) {
            if (!char.isLetter() && !char.isWhitespace()) {
                return false
            }
        }
        return true
    }

    fun isEmail(inputString: String): Boolean {
        val emailRegex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
        return emailRegex.matches(inputString)
    }

    fun isEmptyy(user: User): Boolean {
        var result:Boolean
        if (user.name.isEmpty() || user.email.isEmpty() || user.lastname.isEmpty() || user.pass.isEmpty() || user.num.isEmpty()){result=false} else {result=true}
        return result
    }

    fun isNumber(number: String) :Boolean{
        val pattern = Regex("^\\d{8}\$")
        var result : Boolean
        if (!pattern.matches(number)){result = false} else {result=true}
        return result
    }

    override fun inscription(user : User) : Boolean {
        if (!isEmail(user.email)) {
            view.Showmessage(context.getString(R.string.realG))
            return false
        } else if(!isEmptyy(user)){
            view.Showmessage(context.getString(R.string.empty))
            return false
        }
        else if(!isAllLetters(user.name)) {
            view.Showmessage(context.getString(R.string.realN))
            return false
        }
            else if(!isAllLetters(user.lastname)){
                view.Showmessage(context.getString(R.string.realL))
                return false
        }
        else if(!isNumber(user.num)){
            view.Showmessage(context.getString(R.string.realNb))
            return false }
        return true
}}
