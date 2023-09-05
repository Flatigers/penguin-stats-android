package org.penguin_stats.android.ui.detailed

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.penguin_stats.android.data.Repository
import org.penguin_stats.android.data.ResponseStages
import org.penguin_stats.android.util.millsToMinSec
import org.penguin_stats.android.util.periodTimeString

class DetailedStageViewModel : ViewModel() {
    private var stageId: String = ""
    private var stage: ObservableField<ResponseStages> = ObservableField()

    val interval: ObservableField<String> = ObservableField("0..0")
    val count: ObservableField<String> = ObservableField("0")
    val sanityConsumed: ObservableField<String> = ObservableField("0")
    val minClearTime: ObservableField<String> = ObservableField("0 m 0 s")

    fun setSanity(sanity: Int) {
        this.sanityConsumed.set(sanity.toString())
    }

    fun setTime(time: Long) {
        this.minClearTime.set(time.millsToMinSec())
    }

    fun setStageId(id: String) {
        stageId = id
    }

    fun getStage(): ResponseStages {
        return if (stage.get() == null) {
            runBlocking(Dispatchers.IO) {
                val mStage = Repository.readStageById(stageId)
                stage.set(mStage)
                mStage
            }
        } else {
            stage.get()!!
        }
    }

    fun setInterval() {
        MainScope().launch(Dispatchers.IO) {
            val patterns = getPatterns()
            val str = periodTimeString(patterns[0].start, patterns[0].end)
            withContext(Dispatchers.Main) {
                this@DetailedStageViewModel.interval.set(str)
            }
        }
    }

    fun setCount() {
        MainScope().launch(Dispatchers.Default) {
            val patterns = getPatterns()
            withContext(Dispatchers.Main) {
                this@DetailedStageViewModel.count.set(patterns[0].times.toString())
            }
        }
    }

    private suspend fun getPatterns() = coroutineScope {
        Repository.readPatternsByStageId(stageId)
    }

}
