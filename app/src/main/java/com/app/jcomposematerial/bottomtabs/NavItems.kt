package com.app.jcomposematerial.bottomtabs

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.app.jcomposematerial.bottomtabs.Routes.NAV_FAV
import com.app.jcomposematerial.bottomtabs.Routes.NAV_FEED
import com.app.jcomposematerial.bottomtabs.Routes.NAV_HOME
import com.app.jcomposematerial.bottomtabs.Routes.NAV_PROFILE
import com.app.jcomposematerial.R

sealed class NavItems(
    @StringRes var title: Int,
    @DrawableRes var icon: Int,
    val navRoute: String
) {
    object Home : NavItems(R.string.home, R.drawable.ic_home, NAV_HOME)
    object FAV : NavItems(R.string.fav, R.drawable.ic_favorite, NAV_FAV)
    object FEED : NavItems(R.string.feed, R.drawable.ic_feed, NAV_FEED)
    object PROFILE : NavItems(R.string.profile, R.drawable.ic_profile, NAV_PROFILE)
}