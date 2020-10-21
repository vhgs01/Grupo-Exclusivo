package br.com.kaz.firebase

import android.content.Context
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


object FirebaseIntegration {

    private var firebaseAuth: FirebaseAuth? = null

    fun initializeFirebase() {
        firebaseAuth = FirebaseAuth.getInstance()
    }

    fun getCurrentlyUserSignedIn(): FirebaseUser? {
        return firebaseAuth?.currentUser
    }

    fun createUser(context: Context, email: String, password: String): Boolean {
        initializeFirebase()
        var userCreated = false

        firebaseAuth?.createUserWithEmailAndPassword(email, password)
            ?.addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    userCreated = true
                } else {
                    Toast.makeText(context, "Falha ao criar usuário.", Toast.LENGTH_LONG).show()
                }
            }

        return userCreated
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
}