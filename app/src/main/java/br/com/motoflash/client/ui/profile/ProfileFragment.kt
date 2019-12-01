package br.com.motoflash.client.ui.profile


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.motoflash.client.R
import br.com.motoflash.client.ui.base.BaseFragment
import br.com.motoflash.client.ui.login.LoginMvpView
import br.com.motoflash.client.ui.main.MainActivity
import br.com.motoflash.client.ui.splash.SplashActivity
import br.com.motoflash.core.data.network.model.User
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : BaseFragment(), ProfileMvpView {

    @Inject
    lateinit var presenter: ProfileMvpPresenter<ProfileMvpView>

    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentComponent.inject(this)
        presenter.onAttach(this)

        setUp()
    }

    override fun setUp() {
        btnSingOut.setOnClickListener {
            presenter.doSingOutUser()
        }

        user = (activity!! as MainActivity).getCurrentUser()
    }

    override fun onSingOutUser() {
        activity!!.startActivity(Intent(activity!!, SplashActivity::class.java))
        activity!!.finish()
    }

    override fun onSingOutFail() {

    }

}
