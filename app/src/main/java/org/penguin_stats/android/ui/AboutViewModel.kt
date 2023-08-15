package org.penguin_stats.android.ui

import androidx.lifecycle.ViewModel
import org.penguin_stats.android.app.AppConfig

class AboutViewModel : ViewModel() {
    var versionName = "Ver: " + AppConfig.versionName
}