package com.mobelite.toytroc.SignUp

interface SignUpContract {
    interface Presenter{
 fun inscription(user : User) : Boolean
    }
    interface View {
     fun Showmessage(input : String)
    }

}