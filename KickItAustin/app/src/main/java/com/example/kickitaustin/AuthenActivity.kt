package com.example.kickitaustin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kickitaustin.temporaryPictures.Companion.picList
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

class AuthenActivity : AppCompatActivity() {
    companion object {
        val rcSignIn = 17
        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var picListSize = picList.size
        val randomNum = Random.nextInt(0, picListSize)

        //Authentication
        if (FirebaseAuth.getInstance().currentUser == null) {
            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .setLogo(R.drawable.ic_isolated_monochrome_black)
                    .setTheme(R.style.AuthenTheme)
                    .setIsSmartLockEnabled(false)
                    .setTosAndPrivacyPolicyUrls(
                        "https://generator.lorem-ipsum.info/terms-and-conditions",
                        "https://ifaketextmessage.com/privacy/"
                    )
                    .build(),
                rcSignIn
            )

            val user = FirebaseAuth.getInstance().currentUser
            if (user!!.photoUrl == null) {
//            Log.d("XXX", "URI of current user: " + user.photoUrl.toString())
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setPhotoUri(picList[randomNum])
                    .build()

                user?.updateProfile(profileUpdates)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            //Log.d("XXX", "User profile updated.")
                            //Log.d("XXX", "URI of current user: " + user.photoUrl.toString())
                        }
                    }
            }
        }
        finish()
    }

    // If we need to log in, activity puts us here.  Do what we need to and finish(),
    // unless we have another callback (in setDisplayNameByEmail)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == rcSignIn) {
            val response = IdpResponse.fromResultIntent(data)

            Log.d(javaClass.simpleName, "activity result $resultCode")
            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                if(true) {
                    finish()
                }
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                Log.d(MainActivity.TAG, "Error signing in", response?.error)
                finish()
            }
        }
    }
}
