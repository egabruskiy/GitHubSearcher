package com.egabruskiy.githubsearcher.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.egabruskiy.githubsearcher.R
import com.egabruskiy.githubsearcher.databinding.GoogleSignInFragmentBinding
import com.egabruskiy.githubsearcher.databinding.SearchFragmentBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import kotlinx.android.synthetic.main.google_sign_in_fragment.*
import org.koin.android.ext.android.bind
import timber.log.Timber

class GoogleSignInFragment : Fragment() {

    private lateinit var viewModel: GoogleSignInViewModel
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 9001


    private val viewBinding by viewBinding(GoogleSignInFragmentBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.google_sign_in_fragment, container, false)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO check if we have token
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken("469935103217-tkejo9reenvqp62on576o9mpdepcbnrh.apps.googleusercontent.com")
            .requestIdToken("469935103217-g6nt9lj9gsek4cqf0mrqm1qidmjrcss5.apps.googleusercontent.com")
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.googleLoginBtn.setOnClickListener {
            signIn()

//            findNavController().navigate(R.id.login_fragment_to_main_fragment)

        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(
                ApiException::class.java
            )


            // Signed in successfully
            val googleId = account?.id ?: ""
            Timber.v("Google ID"+googleId)



            val googleIdToken = account?.idToken ?: ""
            Timber.v("Google ID Token"+ googleIdToken)

            findNavController().navigate(R.id.login_fragment_to_main_fragment)

            // TODO save token
        } catch (e: ApiException) {

            Toast.makeText(activity,"Ошибка авторизации  "+ e.statusCode,Toast.LENGTH_LONG).show()
            Timber.e("failed code=" +  e.statusCode.toString())
        }
    }


    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(
            signInIntent, RC_SIGN_IN
        )
    }

//    private fun signOut() {
//        mGoogleSignInClient.signOut()
//            .addOnCompleteListener(this) {
//
//            }
//    }


//    ..and to revoke access (This can be used when you have the ‘Delete my account’ option in the user’s profile):

//    private fun revokeAccess() {
//        mGoogleSignInClient.revokeAccess()
//            .addOnCompleteListener(this) {
//                // Update your UI here
//            }
//    }

}