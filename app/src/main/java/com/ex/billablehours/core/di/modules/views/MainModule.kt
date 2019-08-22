package com.ex.billablehours.core.di.modules.views

import com.ex.billablehours.intro.viewmodel.MainIntroViewModelFactory
import com.ex.billablehours.login.viewModel.LoginViewModelFactory
import com.ex.billablehours.timecard.viewmodel.factory.GroupedTimeCardFactory
import com.ex.billablehours.timecard.viewmodel.factory.TimeCardFactory
import com.ex.billablehours.timecard.viewmodel.factory.TimeCardListFactory
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
    bind() from provider { LoginViewModelFactory(instance()) }
    bind() from provider { TimeCardListFactory(instance()) }
    bind() from provider { TimeCardFactory(instance(), instance()) }
    bind() from provider { GroupedTimeCardFactory(instance()) }
}