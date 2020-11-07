package br.com.kaz.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.kaz.R
import br.com.kaz.contract.ModuleContract
import br.com.kaz.presenter.ModulePresenter
import br.com.kaz.util.JsonManipulation.getCourseKaz
import br.com.kaz.util.LottieAnimation
import br.com.kaz.view.adapter.CourseKazAdapter
import kotlinx.android.synthetic.main.activity_modules.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ModulesActivity : AppCompatActivity(), ModuleContract.View {

    private val presenter: ModulePresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modules)

        setListeners()
    }

    override fun onResume() {
        super.onResume()

        configureAdapter()
    }

    override fun setListeners() {
        setLogoutButtonListener()
    }

    override fun handleAnimation(startAnimation: Boolean) {
        if (startAnimation) {
            modulesLogout.text = ""

            LottieAnimation.startAnimation(modulesLottieAnimation)
        } else {
            modulesLogout.text = getString(R.string.modulesLogout)

            LottieAnimation.cancelAnimation(modulesLottieAnimation)
        }
    }

    override fun configureAdapter() {
        val recyclerView = modulesList
        recyclerView.adapter =
            getCourseKaz(applicationContext)?.let { CourseKazAdapter(it, this) }
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun showSingOutUserError() {
        Toast.makeText(this, getString(R.string.modulesSingOutUserErrorText), Toast.LENGTH_LONG)
            .show()
    }

    override fun redirectToLoginScreen() {
        startActivity(Intent(this, LoginActivity::class.java))
        finishAffinity()
    }

    private fun setLogoutButtonListener() {
        modulesLogout!!.setOnClickListener {
            handleAnimation(true)
            presenter.singOutUser()
        }
    }

}