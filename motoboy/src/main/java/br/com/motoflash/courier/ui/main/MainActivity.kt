package br.com.motoflash.courier.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.motoflash.core.data.network.model.Courier
import br.com.motoflash.courier.R
import br.com.motoflash.courier.ui.base.BaseActivity
import br.com.motoflash.courier.ui.base.BaseFragment
import br.com.motoflash.courier.ui.history.HistoryFragment
import br.com.motoflash.courier.ui.home.HomeFragment
import br.com.motoflash.courier.ui.profile.ProfileFragment
import br.com.motoflash.courier.ui.splash.SplashActivity
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.firebase.ui.auth.AuthUI
import com.google.android.libraries.places.api.Places
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainMvpView {

    private lateinit var courier: Courier

    private var load = false

    @Inject
    lateinit var presenter: MainMvpPresenter<MainMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent.inject(this)
        presenter.onAttach(this)

        setUp()
    }

    override fun setUp() {
        showLoading()

        nav_view.add(MeowBottomNavigation.Model(1, R.drawable.ic_history))
        nav_view.add(MeowBottomNavigation.Model(2, R.drawable.ic_home))
        nav_view.add(MeowBottomNavigation.Model(3, R.drawable.ic_profile))

        nav_view.setOnClickMenuListener{
            val fragment: BaseFragment = when(it.id){
                1 -> {
                    HistoryFragment()
                }
                2 -> {
                    HomeFragment()
                }
                3 -> {
                    ProfileFragment()
                }
                else -> {
                    ProfileFragment()
                }
            }

            val fragmentTransaction = supportFragmentManager.beginTransaction().replace(
                frame.id,
                fragment
            )
                .addToBackStack(fragment.getCustomTag())
            fragmentTransaction.commit()
        }

        presenter.doGetCourier()
    }

    override fun onGetCourier(courier: Courier) {
        this.courier = courier
        if(load){
            load = true

            Places.initialize(applicationContext, getString(R.string.google_maps_key))

            FirebaseMessaging.getInstance().subscribeToTopic(FirebaseAuth.getInstance().currentUser!!.uid)

            val fragment = HomeFragment()
            nav_view.show(2)
            val fragmentTransaction = supportFragmentManager.beginTransaction().replace(
                frame.id,
                fragment
            )
                .addToBackStack(fragment.getCustomTag())
            fragmentTransaction.commit()
        }
    }

    override fun onLogoutCourier() {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(FirebaseAuth.getInstance().currentUser!!.uid)
        AuthUI.getInstance().signOut(this).addOnCompleteListener {
            showMessage("Usuário Desconectado!")
            FirebaseAuth.getInstance().signOut()
            finish()
        }
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}
