package br.com.motoflash.courier.di.component

import br.com.motoflash.courier.di.annotations.PerActivity
import br.com.motoflash.courier.di.module.FragmentModule
import dagger.Component


@PerActivity
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {

}
