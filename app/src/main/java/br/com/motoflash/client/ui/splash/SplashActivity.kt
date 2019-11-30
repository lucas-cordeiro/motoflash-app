package br.com.motoflash.client.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import androidx.core.view.ViewCompat
import br.com.motoflash.client.R
import br.com.motoflash.client.ui.base.BaseActivity
import br.com.motoflash.client.ui.base.MvpPresenter
import br.com.motoflash.client.ui.base.MvpView
import br.com.motoflash.core.ui.util.CommonsUtil.Companion.convertPxToDp
import br.com.motoflash.core.ui.util.showItemsWithMove
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashMvpView {

    @Inject
    lateinit var presenter: SplashMvpPresenter<SplashMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        activityComponent.inject(this)
        presenter.onAttach(this)
        setUp()
    }

    override fun setUp() {
        animate()

        presenter.doVerifyUser()
    }

    override fun onVerfyUser(logged: Boolean) {
        showMessage("Logado: $logged")
    }

    private fun animate() {

        ViewCompat.animate(imgLogo)
            .translationY(-convertPxToDp(this, 70f))
            .setStartDelay(250L)
            .setDuration(600L).setInterpolator(
                DecelerateInterpolator(1.5f)
            ).start()

        container.showItemsWithMove()
    }

    override fun hideLoading() {

    }

    override fun showLoading() {

    }
}
