package com.ex.billablehours.core.di.modules.views

import com.ex.billablehours.intro.viewmodel.MainIntroViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-18
 */

val mainModule = Kodein.Module("Main Module") {
    bind() from provider { MainIntroViewModelFactory(instance()) }
}