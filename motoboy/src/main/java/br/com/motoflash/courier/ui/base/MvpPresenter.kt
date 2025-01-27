package br.com.motoflash.courier.ui.base

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
interface MvpPresenter<in V : MvpView> {
    fun onAttach(mvpView: V)
    fun onDetach()
    val TAG: String
}
