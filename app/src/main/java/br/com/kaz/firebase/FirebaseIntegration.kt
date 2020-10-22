package br.com.kaz.firebase

import android.R
import android.content.Context
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*


object FirebaseIntegration {

    private var firebaseAuth: FirebaseAuth? = null

    fun initializeFirebase() {
        firebaseAuth = FirebaseAuth.getInstance()
    }

    fun getCurrentlyUserSignedIn(): FirebaseUser? {
        return firebaseAuth?.currentUser
    }

    fun createUser(context: Context, email: String, password: String, action: () -> Unit) {
        firebaseAuth?.createUserWithEmailAndPassword(email, password)
            ?.addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    action()
                } else {
                    verifyException(task, context)
                }
            }
    }

    fun loginWithUser(context: Context, email: String, password: String) {
        firebaseAuth?.signInWithEmailAndPassword(email, password)
            ?.addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    val user = firebaseAuth?.currentUser
                } else {
                    Toast.makeText(context, "Falha na autenticação.", Toast.LENGTH_LONG).show()
                }
            }
    }

    fun singOutUser() {
        firebaseAuth?.signOut()
    }

    private fun verifyException(task: Task<AuthResult>, context: Context) {
        try {
            throw task.exception!!
        } catch (e: FirebaseAuthWeakPasswordException) {
            Toast.makeText(
                context,
                context.getString(br.com.kaz.R.string.FirebaseAuthWeakPasswordException),
                Toast.LENGTH_LONG
            ).show()
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            Toast.makeText(
                context,
                context.getString(br.com.kaz.R.string.FirebaseAuthInvalidCredentialsException),
                Toast.LENGTH_LONG
            ).show()
        } catch (e: FirebaseAuthUserCollisionException) {
            Toast.makeText(
                context,
                context.getString(br.com.kaz.R.string.FirebaseAuthUserCollisionException),
                Toast.LENGTH_LONG
            ).show()
        } catch (e: Exception) {
            Toast.makeText(
                context,
                context.getString(br.com.kaz.R.string.FirebaseAuthOtherException),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}