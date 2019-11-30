package br.com.motoflash.client.di.component

import br.com.motoflash.client.di.annotations.PerActivity
import br.com.motoflash.client.di.module.FragmentModule
import dagger.Component

@PerActivity
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {

}